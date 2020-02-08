package com.company;


import javax.sound.sampled.AudioFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class EthConn {
    private int commandId = 0;
    public static int size = 1024;
    private String _usr;
    private int comm_version = -1;
    public String username;
    private String _pwd;
    public String password;

    public String getUsername() {
        return _usr;
    }

    public void setUsername(String username) {
        this._usr = username;
    }

    public String getPassword() {
        return _pwd;
    }

    public void setPassword(String password) {
        this._pwd = password;
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


    private InetAddress _address;
    public InetAddress address;
    Socket socket;

    public InetAddress getAddress() {
        return _address;
    }

    public void setAddress(InetAddress address) throws IOException {
        isConnected = false;
        this._address = address;
        socket = new Socket(_address, port);
    }

    private int _port = 5000;
    public int port;

    public int getPort() {
        return _port;
    }

    public void setPort(int port) {
        this._port = port;
        try {
            socket = new Socket(_address, port);
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
        _address = address;
        _usr = username;
        _pwd = password;
        _port = port;
        if (address != null) {
            try {
                socket = new Socket(_address, _port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ForceRelease() {
        if (commSemaphore.availablePermits() == 0) {
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
            _client = new Socket(_address, _port);
            _client.setSoTimeout(200);// = 200;
        } catch (Exception e) {
            e.printStackTrace();
            isConnected = false;
            return ConnectResult.E_UNABLE;
        }

        //Send username hash
        double rng = Math.random();
        byte[] data = new byte[16];
        data = doubletoBytes(rng);
        String salt1 = data.toString();//Convert.ToBase64String(data);
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
        byte[] _cUserName = doubletoBytes(1212121);
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
        String cryptPwd = EncryptPassword(_pwd, resp.message);
        String pwdMsg = salt.substring(24, 8) + cryptPwd + salt.substring(0, 8);
        //Send encrypted pwd
        //byte[] message = _hasher.ComputeHash(Encoding.UTF8.GetBytes(pwdMsg));
        resp = SendMessage(pwdMsg, false);
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
       /* byte[] hash = _hasher.ComputeHash(Encoding.UTF8.GetBytes(salt.Substring(0, 16) + cryptPwd + salt.Substring(16, 16)));
        key = hash.ToList().Take(32).ToArray();
        ivIn = hash.ToList().Skip(32).Take(16).ToArray();
        ivOut = hash.ToList().Skip(48).Take(16).ToArray();
        //Setup AES Algorithm
        aes = new AesCryptoServiceProvider();
        aes.KeySize = 256;
        aes.Mode = CipherMode.CBC;
        aes.Padding = System.Security.Cryptography.PaddingMode.PKCS7;
        encrypt = aes.CreateEncryptor(key, ivOut);
        decrypt = aes.CreateDecryptor(key, ivIn);
       */
        if (resp.result != ResultCode.SUCCESS) {
            isConnected = false;
            return ConnectResult.E_UNKNOWN;
        }
        try {
            String response = DecryptReponse(resp.raw); //Manually decrypt response because message was not encrypted
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
        byte[] rawMessage = new byte[safeMessage.length() + packet.length];
        //AudioFormat.Encoding.UTF8.GetBytes(safeMessage).CopyTo(rawMessage, 0);
        //  packet.(rawMessage, safeMessage.length());
        return SendMessage(rawMessage, encrypted);
    }


    public Response SendMessage(String message, boolean encrypted) {
        String safeMessage = message;
        if (encrypted) {
            commandId++;
            safeMessage = commandId + ":" + message;
        }
        return SendMessage(safeMessage/*Encoding.UTF8.GetBytes(safeMessage)*/, encrypted);
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
            int result =message[message.length-1];// (int) UInt32.Parse(Encoding.ASCII.GetString(message, 0, endOfCmd));
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
       */ return password;//res[3];
    }
}

