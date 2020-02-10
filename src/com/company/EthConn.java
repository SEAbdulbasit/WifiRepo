package com.company;


import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.Semaphore;

public class EthConn {
    private int commandId = 0;
    public static int size = 1024;
    private int comm_version = -1;
    public String username;
    public String password;

    ServerSocket serverSocket;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Semaphore commSemaphore = new Semaphore(1);
    //private Socket _socket;
    private Socket _client;
    // private ICryptoTransform encrypt;
    // private ICryptoTransform decrypt;
    private byte[] key;
    private byte[] ivIn;
    private byte[] ivOut;
    //  AesCryptoServiceProvider aes;
    // private RijndaelManaged crypt = new RijndaelManaged();
    // private EndPoint _contEndPoint;

    Cipher cipher;

    {
        try {
            cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    private boolean _isConnected = false;
    public boolean isConnected;

    public boolean isConnected() {
        return _isConnected;
    }

    public void setConnected(boolean connected) {
        if (connected == false) {
            commandId = 0;
            if (_client != null && _client.isConnected()) {
                try {
                    _client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        _isConnected = connected;
    }


    public InetAddress address;
    Socket socket;

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) throws IOException {
        isConnected = false;
        this.address = address;
        socket = new Socket(address, port);
    }

    public int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public enum ConnectResult {
        SUCCESS,
        FAILED,
        BUSY,
        E_BADSALT,
        E_UNABLE,
        E_DECRYPT,
        E_UNKNOWN,
    }

    public enum ResultCode {
        SUCCESS,
        NOOP,
        TIMEOUT,
        NOCONN,
        UNREACHABLE,
        ERROR,
    }

    public class Response {
        public ResultCode result;
        public String message;
        public byte[] raw;
        public byte[] sent;
    }

    public EthConn(String username, String password, InetAddress address, int port) {
        this.address = address;
        this.username = username;
        this.password = password;
        this.port = port;

        if (address != null) {
            try {
                serverSocket = new ServerSocket(port, 0, address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ForceRelease() {
        if (commSemaphore.getQueueLength() == 0) {
            commSemaphore.release();
        }
    }

    private static byte[] doubletoBytes(double dblValue) {
        long data = Double.doubleToRawLongBits(dblValue);
        return new byte[]{
                (byte) ((data >> 56) & 0xff),
                (byte) ((data >> 48) & 0xff),
                (byte) ((data >> 40) & 0xff),
                (byte) ((data >> 32) & 0xff),
                (byte) ((data >> 24) & 0xff),
                (byte) ((data >> 16) & 0xff),
                (byte) ((data >> 8) & 0xff),
                (byte) ((data >> 0) & 0xff),
        };
    }

    public void Disconnect() {
        isConnected = false;
    }

    public ConnectResult Connect() {
        //Clear old connection
        isConnected = false;
        //Enable communication
        isConnected = true;

        try {

            serverSocket = new ServerSocket(port, 0, address);
            serverSocket.setSoTimeout(200);// = 200;
            socket = serverSocket.accept();
        } catch (Exception e) {
            e.printStackTrace();
            isConnected = false;
            return ConnectResult.E_UNABLE;
        }

        //Send username hash
        double rng = Math.random();
        byte[] data = new byte[16];
        data = doubletoBytes(rng);
        String salt1 = new String(data, StandardCharsets.UTF_8);//Convert.ToBase64String(data);
        String salt = "";
        int attempts = 0;

        Response resp = SendMessage("LOGIN:" + salt1.substring(0, 16), false);
        attempts = 0;
        while (resp.result == ResultCode.NOOP) {
            resp = SendMessage("LOGIN:" + salt1.substring(0, 16), false);
            attempts++;
            if (attempts > 100) {
                isConnected = false;
                return ConnectResult.BUSY;
            }
        }

        if (resp.result != ResultCode.SUCCESS) {
            if (resp.result == ResultCode.UNREACHABLE) {
                isConnected = false;
                return ConnectResult.E_UNABLE;
            } else {
                isConnected = false;
                return ConnectResult.E_UNKNOWN;
            }
        } else if (resp.message.length() != 16) {
            //Incorrect Salt length
            return ConnectResult.E_BADSALT;
        } else {
            salt = salt1.substring(0, 16) + resp.message;
            comm_version = 1;
        }

       /* HashAlgorithm _hasher = SHA512.Create();
        String usrMsg = salt.substring(8, 8) + _usr + salt.substring(16, 8);
        byte[] _cUserName = _hasher.ComputeHash(Encoding.UTF8.GetBytes(usrMsg));
*/
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String usrMsg = salt.substring(8, 8) + username + salt.substring(16, 8);


        byte[] _cUserName = md.digest(usrMsg.getBytes());
        resp = SendMessage(_cUserName, false);
        attempts = 0;
        while (resp.result == ResultCode.NOOP) {
            resp = SendMessage(_cUserName, false);
            attempts++;
            if (attempts > 100) {
                isConnected = false;
                return ConnectResult.BUSY;
            }
        }

        if (resp.result != ResultCode.SUCCESS) {
            //Incorrect Salt length
            return ConnectResult.E_BADSALT;
        }
        String cryptPwd = EncryptPassword(password, resp.message);
        String pwdMsg = salt.substring(24, 8) + cryptPwd + salt.substring(0, 8);
        //Send encrypted pwd
        byte[] message = null; //;_hasher.ComputeHash(Encoding.UTF8.GetBytes(pwdMsg));
        try {
            message = md.digest(usrMsg.getBytes(pwdMsg));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resp = SendMessage(message, false);
        attempts = 0;
        while (resp.result == ResultCode.NOOP) {
            resp = SendMessage(pwdMsg, false);
            attempts++;
            if (attempts > 100) {
                isConnected = false;
                return ConnectResult.BUSY;
            }
        }
        //Generate Encryption Keys
        byte[] hash = md.digest((salt.substring(0, 16) + cryptPwd + salt.substring(16, 16)).getBytes());
        key = Arrays.copyOfRange(hash, 0, 32);
        ivIn = Arrays.copyOfRange(hash, 32, 32 + 16);// hash.ToList().Skip(32).Take(16).ToArray();
        ivOut = Arrays.copyOfRange(hash, 48, 48 + 16);//hash.ToList().Skip(48).Take(16).ToArray();
        //Setup AES Algorithm
   /*     aes = new AesCryptoServiceProvider();
        aes.KeySize = 256;
        aes.Mode = CipherMode.CBC;
        aes.Padding = System.Security.Cryptography.PaddingMode.PKCS7;
        encrypt = aes.CreateEncryptor(key, ivOut);
        decrypt = aes.CreateDecryptor(key, ivIn);*/
        if (resp.result != ResultCode.SUCCESS) {
            isConnected = false;
            return ConnectResult.E_UNKNOWN;
        }
        try {
            String response = decodeAndDecrypt(resp.raw.toString()); //Manually decrypt response because message was not encrypted
            //Console.WriteLine(response);
            if (response == "access granted") {
                _client.setSoTimeout(5000);
                return ConnectResult.SUCCESS;
            } else {
                //Should never be reached.
                isConnected = false;
                return ConnectResult.FAILED;
            }
        } catch (Exception e) {
            //Decrypt failed
            isConnected = false;
            return ConnectResult.FAILED;
        }
    }

    public Response SendMessage(String command, byte[] packet, boolean encrypted) {
        String safeMessage = command + ":";
        if (encrypted) {
            commandId++;
            safeMessage = commandId + ":" + command + ":";
        }
        byte[] safeMessageArray = safeMessage.getBytes(StandardCharsets.UTF_8);

        byte[] rawMessage = new byte[safeMessage.length() + packet.length];

        System.arraycopy(safeMessageArray, 0, rawMessage, 0, rawMessage.length);
        System.arraycopy(packet, 0, rawMessage, safeMessage.length(), rawMessage.length);

        return SendMessage(rawMessage, encrypted);
    }


    public Response SendMessage(String message, boolean encrypted) {
        String safeMessage = message;
        if (encrypted) {
            commandId++;
            safeMessage = commandId + ":" + message;
        }
        return SendMessage(new String(safeMessage.getBytes(StandardCharsets.UTF_8)), encrypted);
    }

    public Response SendMessage(byte[] message, boolean encrypted) {
        int cmdId = 0;
        Response result = new Response();
        if (!(commSemaphore.availablePermits() < 1)) {
            result.message = "The port is busy.";
            result.result = ResultCode.NOOP;
            return result;
        }
        if (isConnected) {
            try {

                _client.setSoTimeout(5000);
                OutputStream stream = _client.getOutputStream();

                if (encrypted) {
                    cmdId = findCommandId(message);
                    if (cmdId == -1) {
                        System.out.println("ERROR the encrypted command sent did not contain an id");
                    }
                    result.sent = EncryptMessage(message);
                    stream.write(result.sent, 0, result.sent.length);
                } else {
                    result.sent = message;
                    stream.write(message, 0, message.length);
                }

                stream.flush();

                boolean recvResp = false;
                while (!recvResp) {
                    result = AttemptRead(socket.getInputStream());// AttemptRead(stream);
                    if (result.result != ResultCode.SUCCESS) {
                        break;
                    }

                    if (encrypted) {
                        result.raw = DecryptReponseBytes(result.raw);
                        if (cmdId < 0) {
                            recvResp = true;
                        } else {
                            recvResp = (cmdId == findCommandId(result.raw));
                            if (recvResp) {
                                //Remove the command id from the results string
                                result.raw = result.raw.toString().getBytes();
                                result.message = result.message;//Encoding.UTF8.GetString(result.raw, 0, result.raw.Length);
                            }
                        }
                    } else {
                        result.message = String.valueOf(result.raw.length);//Encoding.UTF8.GetString(result.raw, 0, result.raw.Length);
                        recvResp = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
              /*  //TODO: Parse exception and pass different error codes
                if (e.c == 10065) {
                    result.result = ResultCode.UNREACHABLE;
                    result.message = e.Message;
                } else {
                    result.result = ResultCode.ERROR;
                    result.message = e.Message;
                }
            } catch (CryptographicException e) {
                Console.WriteLine("Crypto Error.");
            } catch (IOException e) {
                result.result = ResultCode.ERROR;
                Console.WriteLine("IO Error: " + e.Message);
            } catch (Exception e) {
                result.result = ResultCode.ERROR;
                Console.WriteLine("Error: " + e.Message);*/
            } finally {
                if (commSemaphore.availablePermits() == 0) commSemaphore.release();
            }
        } else {
            result.result = ResultCode.NOCONN;
            result.message = "You are not connected to a controller.";
            if (commSemaphore.availablePermits() == 0) commSemaphore.release();
        }
        if (result.message != null)
            result.message = result.message;//.trim("\0");
        return result;
    }

    private int findCommandId(byte[] message) {
        int endOfCmd = -1;

        for (int i = 0; i < message.length; i++) {
            if (message[i] == ':') {
                endOfCmd = i;
                break;
            }
        }

        try {
            int result = message[message.length - 1];// (int) UInt32.Parse(Encoding.ASCII.GetString(message, 0, endOfCmd));
            return result;
        } catch (Exception e) {
            return -1;
        }

    }

    private Response AttemptRead(InputStream stream) {
        Response result = new Response();
        byte[] resp = new byte[size];
        int numBytes = 0;
        try {
            numBytes = stream.read(resp, 0, size);
            if (numBytes == 0) {
                result.result = ResultCode.TIMEOUT;
                result.message = "The controller did not respond.";
                return result;
            }
            result.result = ResultCode.SUCCESS;
            result.raw = new byte[numBytes];
            for (int i = 0; i < numBytes; i++) {
                result.raw[i] = resp[i];
            }

            return result;
        } catch (IOException e) {
            //TODO: Parse exception and pass different error codes
            if (e.getMessage() == "Unable to read data from the transport connection: A connection attempt failed because the connected party did not properly respond after a period of time, or established connection failed because connected host has failed to respond.") {
                result.result = ResultCode.TIMEOUT;
                result.message = e.getMessage();
            } else {
                result.result = ResultCode.ERROR;
                result.message = e.getMessage();
            }
            return result;
        }
    }

    public byte[] EncryptMessage(byte[] message) {
        // Check arguments.
        if (message == null || message.length <= 0)
            throw new IllegalArgumentException("plainText");
        byte[] raw;
        byte[] encrypted;

        byte padBytes = (byte) (16 - message.length % 16);
        //Old comms did not strip padding in all cases
        if (comm_version == 0 && (padBytes == 0 || padBytes == 16)) {
            raw = message;
        } else {
            //New comms version always strips crypto padding
            raw = new byte[message.length + (padBytes)];
            for (int i = 0; i < raw.length; i++) {
                if (i < message.length) {
                    raw[i] = message[i];
                } else {
                    raw[i] = padBytes;
                }
            }
        }
        encrypted = message;
        // Create an Rijndael object
        // with the specified key and IV.
        // Create the streams used for encryption.
      /*  using(MemoryStream msEncrypt = new MemoryStream())
        {
            using(CryptoStream csEncrypt = new CryptoStream(msEncrypt, encrypt, CryptoStreamMode.Write))
            {
                //Write all data to the stream.
                csEncrypt.Write(raw, 0, raw.Length);
                csEncrypt.Flush();

                encrypted = msEncrypt.ToArray();
            }
        }*/
        // Return the encrypted bytes from the memory stream.
        return encrypted;

    }

    private String DecryptReponse(byte[] response) {
        return response.toString();//Encoding.UTF8.GetString(DecryptReponseBytes(response)).Trim('\0');
    }

    private byte[] DecryptReponseBytes(byte[] response) {
        // Check arguments.
        if (response == null || response.length <= 0)
            throw new NullPointerException("cipherText");

        byte[] buffer = new byte[response.length];

        // Create the streams used for decryption.
    /*    using(MemoryStream msDecrypt = new MemoryStream(response))
        {
            decrypt = aes.CreateDecryptor(key, ivIn);
            using(CryptoStream csDecrypt = new CryptoStream(msDecrypt, decrypt, CryptoStreamMode.Read))
            {
                csDecrypt.Read(buffer, 0, response.Length);
                csDecrypt.Flush();
            }
        }*/
        return buffer;
    }

    private String EncryptPassword(String password, String salt) {
        String fmtSalt = "$6$" + salt;
       /* String result = Crypter.Sha512.Crypt(password, fmtSalt);
        String[] res = result.Split('$');
       */
        return password;//res[3];
    }

    private static String IV = "IV_VALUE_16_BYTE";
    private static String PASSWORD = "PASSWORD_VALUE";
    private static String SALT = "SALT_VALUE";

    public String encryptAndEncode(String raw) {
        try {
            Cipher c = getCipher(Cipher.ENCRYPT_MODE);
            byte[] encryptedVal = c.doFinal(getBytes(raw));
            String s = getString(Base64.getEncoder().encode(encryptedVal));
            return s;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public String decodeAndDecrypt(String encrypted) throws Exception {
        byte[] decodedValue = Base64.getDecoder().decode(getBytes(encrypted));
        Cipher c = getCipher(Cipher.DECRYPT_MODE);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }

    private String getString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, "UTF-8");
    }

    private byte[] getBytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8");
    }

    private Cipher getCipher(int mode) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = getBytes(IV);
        c.init(mode, generateKey(), new IvParameterSpec(iv));
        return c;
    }

    private Key generateKey() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        char[] password = PASSWORD.toCharArray();
        byte[] salt = getBytes(SALT);

        KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        byte[] encoded = tmp.getEncoded();
        return new SecretKeySpec(encoded, "AES");
    }
}

