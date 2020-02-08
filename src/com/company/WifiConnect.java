package com.company;


import javax.sound.sampled.AudioFormat;
import java.net.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class WiFiViewModel {

    private static Logger logger = LogManager.getLogManager().getLogger(DriveModel.class.getName());

    private String SubStringToMatchControllerAPName = "smc-";
    private byte[] s1 = {0x22, 0x67, 0x5b, 0x4c, 0x79, 0x4b, 0x6d, 0x5b, 0x78, 0x3c, 0x5e, 0x29, 0x40, 0x4d, 0x4f, 0x51, 0x50, 0x6c, 0x77, 0x4d, 0x54, 0x40, 0x32, 0x7d, 0x48, 0x71, 0x73, 0x52, 0x74, 0x4d};
    private byte[] s2 = {0x75, 0x2e, 0x0b, 0x1f, 0x00, 0x75, 0x2e, 0x35, 0x3f, 0x5e, 0x72, 0x48, 0x7c, 0x7f, 0x0e, 0x3a, 0x33, 0x19, 0x54, 0x0e, 0x65, 0x61, 0x67, 0x04, 0x3c, 0x2e, 0x27, 0x14, 0x2f, 0x72};

    /// <summary>
    /// Interface for WLAN data.
    /// </summary>
    //private Wifi client;
    //public bool WiFiEnabled;

    /// <summary>
    /// List of SSIDs that are on the network.
    /// </summary>


    /// <summary>
    /// The access point SSID to connect to.
    /// </summary>
    public String CurrentAccessPoint;
    private String _CurrentAccessPoint;

    public void setCurrentAccessPoint(String currentAccessPoint) {
        if (_CurrentAccessPoint == currentAccessPoint) return;
        _CurrentAccessPoint = currentAccessPoint;
    }

    public String getCurrentAccessPoint() {
        return CurrentAccessPoint;
    }


    /// <summary>
    /// The key for the access point to connect to.
    /// </summary>
    public String CurrentAccessPointKey;
    private String _CurrentAccessPointKey;

    public String getCurrentAccessPointKey() {
        return _CurrentAccessPointKey;
    }

    public void setCurrentAccessPointKey(String currentAccessPointKey) {
        if (_CurrentAccessPointKey == currentAccessPointKey) return;
        _CurrentAccessPoint = currentAccessPointKey;
    }

    /// <summary>
/// Wether or not to use the default password
/// </summary>
    public int passwordSet;
    private int _passwordSet = 0;


    public int getPasswordSet() {
        return _passwordSet;
    }

    public void setPasswordSet(int passwordSet) {
        if (_passwordSet == passwordSet) return;
        _passwordSet = passwordSet;
    }


    /// <summary>
/// Set settings for Both/Controller/Inverter
/// </summary>
    public int deviceType;
    private int _deviceType = 0;

    public int getDeviceType() {
        return _deviceType;
    }

    public void setDeviceType(int deviceType) {
        if (_deviceType == deviceType) return;
        _deviceType = deviceType;
    }

    private int _logIndex;

    public int logIndex;

    public int getLogIndex() {
        return _logIndex;
    }

    public void setLogIndex(int logIndex) {
        if (_logIndex == logIndex)
            return;
        _logIndex = logIndex;
    }


    /// <summary>
/// Wether or not to use the default password
/// </summary>
    public String password;


    private String _password = "";

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        if (_password == password)
            return;
        _password = password;

    }


    private String _info_message = "";
    public String info_message;

    public String getinfo_message() {
        return _info_message;
    }

    public void setinfo_message(String info_message) {
        if (_info_message != info_message) {
            _info_message = info_message;
        }
    }


    private InetAddress _address;

    {
        try {
            _address = InetAddress.getByName("192.168.4.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private InetAddress address;

    public InetAddress getAddress() {
        return _address;
    }

    public void setAddress(InetAddress address) {
        this._address = address;
    }


    private DriveModel _drive = new DriveModel();

    public WiFiViewModel() {

    }

    public enum Mode {
        SET_CONFIG,
        CLEAR_CONFIG,
        SHOW_CONFIG,
        GET_LOG,
        GET_LOG_INDEXED,
        READ_DATA,
        WRITE_DATA

    }


    public void attemptConnect(Mode mode, String dspData) {
        //Attempt to login to port 5000
        InetAddress address = this.address;
        String msg;
        Socket tcpClient = new Socket();

        //
        //using(TcpClient tcpClient = new TcpClient()

        int port = 5000;
        boolean success = false;
        //IAsyncResult result;
        SocketAddress sockaddr = new InetSocketAddress(address, port);


        try {
            if (!success) {
                if (deviceType == 0 || deviceType == 1) {
                    port = 5500;
                    logger.info("WiFi: Checking Port " + port);
                    //result = tcpClient.BeginConnect("192.168.4.1", port, null, null);
                    //success = result.AsyncWaitHandle.WaitOne(2000);
                    Thread.sleep(1000);
                    tcpClient.connect(sockaddr);


                    //tcpClient.Connect("192.168.4.1", port);
                    success = tcpClient.isConnected();
                    //tcpClient.EndConnect(result);
                }
            }
        } catch (Exception e) {
            logger.info("WiFi: Exception for port 5500: " + e.getMessage() + ":");
        } finally {
            try {
                if (success) {
                    logger.info("WiFi: Port " + port + " open");
                    String pwd = "";
                    if (passwordSet > 0) {
                        pwd = password;
                    } else {
                        byte[] s3 = new byte[30];
                        for (int i = 0; i < 30; i++) {
                            s3[i] = (byte) (s1[i] ^ s2[i]);
                        }
                        pwd = new String(s3, StandardCharsets.UTF_8);
                    }
                    EthConn conn = new EthConn("smcAdmUsr", pwd, address, port);
                    EthConn.Response resp = null;
                    logger.info("WiFi: Attempting login");
                    EthConn.ConnectResult res = conn.Connect();
                    if (res == EthConn.ConnectResult.SUCCESS) {
                        if (password != "" && passwordSet == 0) {
                            //Connected configure Password info
                            resp = conn.SendMessage("PASS:" + password, true);

                            if (resp.result == EthConn.ResultCode.SUCCESS) {
                                logger.info("WiFi: Sent Password Success");
                            } else {
                                logger.info("WiFi: Password Failed: " + resp.message);
                            }
                        }
                        //Connected do work
                        switch (mode) {
                            case CLEAR_CONFIG:
                                logger.info("Sending NETCLEAR");
                                resp = conn.SendMessage("NETCLEAR", true);
                                break;
                            case SHOW_CONFIG:
                                logger.info("Sening NETSTAT");
                                resp = conn.SendMessage("NETSTAT", true);
                                break;
                            case SET_CONFIG:
                                logger.info("Sending NET ");
                                resp = conn.SendMessage("NET:" + CurrentAccessPoint + ":" + CurrentAccessPointKey, true);
                                break;
                            case GET_LOG:
                                logger.info("Sending RLOG");
                                resp = conn.SendMessage("RLOG", true);
                                break;
                            case GET_LOG_INDEXED:
                                logger.info("Sending RLOG:" + logIndex);
                                resp = conn.SendMessage("RLOG:" + logIndex, true);
                                break;
                            case READ_DATA:
                                if (!getVersion(conn)) {
                                    // fake response to allow handling code below to work
                                    resp.result = EthConn.ResultCode.ERROR;
                                    resp.message = "Could not read version information";
                                    resp.raw = null;
                                    break;
                                }
                                byte[] readMsg = getModbusMsg(mode, dspData);
                                if (readMsg == null) {
                                    // fake response to allow handling code below to work
                                    resp.result = EthConn.ResultCode.ERROR;
                                    resp.message = "Could not create modbus message";
                                    resp.raw = null;
                                    break;
                                }
                                resp = conn.SendMessage("MODBUS", readMsg, true);
                                break;
                            case WRITE_DATA:
                                if (!getVersion(conn)) {
                                    // fake response to allow handling code below to work
                                    resp.result = EthConn.ResultCode.ERROR;
                                    resp.message = "Could not read version information";
                                    resp.raw = null;
                                    break;
                                }
                                byte[] modbusMessage = null;
                                if (dspData == "settings") {
                                    // do pre-read to fill in settings structure
                                    modbusMessage = getModbusMsg(Mode.READ_DATA, dspData);
                                    resp = conn.SendMessage("MODBUS", modbusMessage, true);
                                    if (resp.result == EthConn.ResultCode.SUCCESS) {
                                        parseModbusMsg(Mode.READ_DATA, dspData, resp.raw);
                                    }
                                }
                                modbusMessage = getModbusMsg(mode, dspData);
                                if (modbusMessage == null) {
                                    // fake response to allow handling code below to work
                                    resp.result = EthConn.ResultCode.ERROR;
                                    resp.message = "Could not create modbus message";
                                    resp.raw = null;
                                    break;
                                }
                                resp = conn.SendMessage("MODBUS", modbusMessage, true);
                                break;
                            default:
                                resp = conn.SendMessage("NETSTAT", true);
                                break;
                        }


                        if (resp.result == EthConn.ResultCode.SUCCESS) {
                            switch (mode) {
                                case SET_CONFIG:
                                    msg = "Station set config result: " + "\n";
                                    break;
                                case CLEAR_CONFIG:
                                    msg = "Clear config result: " + "\n";
                                    break;
                                case SHOW_CONFIG:
                                    msg = "Station get config result: " + "\n";
                                    break;
                                case GET_LOG:
                                    msg = "Get Log result: " + "\n";
                                    break;
                                case GET_LOG_INDEXED:
                                    msg = "Get Log At Index Result: " + "\n";
                                    break;
                                case READ_DATA:
                                    msg = "Read data - " + dspData + ":" + "\n";
                                    resp.message = parseModbusMsg(mode, dspData, resp.raw);
                                    break;
                                case WRITE_DATA:
                                    msg = "Write data - " + dspData + ":" + "\n";
                                    resp.message = parseModbusMsg(mode, dspData, resp.raw);
                                    break;
                                default:
                                    msg = "unknown mode";
                                    break;
                            }

                            logger.info(msg + resp.message);
                        } else {
                            logger.info("WiFi info: " + resp.message);
                        }
                        //ensure we always try to disconnect
                        try {
                            conn.Disconnect();
                        } catch (Exception e) {
                            logger.info("info disconnecting MC socket: {0}" + e.getMessage());
                        }
                    } else {
                        logger.info("WiFi: Could not log in");
                    }
                } else {
                    logger.info("WiFi: Port closed");
                }

            } catch (Exception e) {
                logger.info("WiFi: info completing login attempt" + e);
            }
        }

    }


    private boolean getVersion(EthConn conn) {
        byte[] modbusMessage = _drive.GetReadMessage("version");
        if (modbusMessage == null) return false;
        EthConn.Response resp = conn.SendMessage("MODBUS", modbusMessage, true);
        if (resp.result == EthConn.ResultCode.SUCCESS) {
            byte[] data = new byte[resp.raw.length - 7];
            System.arraycopy(resp.raw, 10, data, 0, resp.raw.length - 10);
            if (data[8] != '.') {
                byte[] slice = Arrays.copyOfRange(data, 0, 16);
                slice[8] = '.';

                _drive.version.version = new String(slice);//slice.toString();//AudioFormat.Encoding.ASCII.GetString(data, 0, 16).Insert(8, ".");
            } else {
                byte[] slice = Arrays.copyOfRange(data, 0, 16);
                _drive.version.version = new String(slice);//AudioFormat.Encoding.ASCII.GetString(data, 0, 16);
            }
            return _drive.version.isSupported == 0;
        }
        return false;
    }

    private byte[] getModbusMsg(Mode mode, String dspData) {
        if (mode == Mode.READ_DATA) {
            return _drive.GetReadMessage(dspData);
        } else if (mode == Mode.WRITE_DATA) {
            return _drive.GetWriteMessage(dspData);
        } else {
            return null;
        }
    }

    private String parseModbusMsg(Mode mode, String dspData, byte[] message) {
        if (message.length < 8) return "Response length too short";
        byte[] data = new byte[message.length - 7];
        System.arraycopy(message, 7, data, 0, data.length);
        if (mode == Mode.READ_DATA) {
            if (data.length < data[2] + 5) {
                logger.info("Read response too short");
            } else {
                byte[] readPacket = new byte[data[2] + 3];
                System.arraycopy(data, 0, readPacket, 0, readPacket.length);
                readPacket = DriveModel.AppendCrc(readPacket);
                if (readPacket[readPacket.length - 2] == data[readPacket.length - 2] &&
                        readPacket[readPacket.length - 1] == data[readPacket.length - 1]) {
                    System.arraycopy(data, 3, readPacket, 0, readPacket.length - 3);
                    return _drive.DriveResponseParser(dspData, readPacket);
                } else {
                    logger.info("Read response bad checksum");
                }
            }
            return "Data read failed";
        } else if (mode == Mode.WRITE_DATA) {
            if (data[1] == 0x06 || data[1] == 0x10) {
                if (data.length < 8) {
                    logger.info("Write response too short");
                } else {
                    byte[] writePacket = new byte[6];
                    System.arraycopy(data, 0, writePacket, 0, writePacket.length);
                    writePacket = DriveModel.AppendCrc(writePacket);
                    if (writePacket[6] == data[6] && writePacket[7] == data[7]) {
                        return "Write of " + dspData + " successful";
                    }
                }
            }
            return "Data write failed";
        } else {
            return "Invalid mode for modbus packet parsing";
        }
    }
}
