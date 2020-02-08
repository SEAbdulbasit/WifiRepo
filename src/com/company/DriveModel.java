package com.company;

import com.google.gson.Gson;

import java.io.Console;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DriveModel {
    private static Logger logger = LogManager.getLogManager().getLogger(DriveModel.class.getName());

    private Settings _settings;

    public Versions version = new Versions();

    class Settings {
        public boolean is_setup;
        public boolean is_identify;
        public int operating_mode;
        public int control_mode;
        public int runstop_mode;
        public int startup_mode;
        public int forward_direction;
        public int directions_allowed;
        public int acceleration_time;
        public int deceleration_time;
        public int s_curve_time;
        public int starting_torque;
        public int min_speed;
        public int max_speed;
        public int brake_mode;
        public int status_bits;
        public int manual_mode_run;
        public int manual_mode_speed;
        public int manual_mode_direction;
        public int restart_mode;
        public int restart_attempts;
        public int restart_interval;
        public int restart_alert;
        public int skip_speed_1;
        public boolean skip_speed_enable_1;
        public int skip_speed_2;
        public boolean skip_speed_enable_2;
        public int skip_speed_3;
        public boolean skip_speed_enable_3;
        public int skip_width;
        public int overload_mode;
        public int overload_tolerance;
        public int overspeed_mode;
        public int overspeed_tolerance;
        public int speeddev_mode;
        public int speeddev_tolerance;
        public int error_response;
        public int warning_response;
        public int warning_timeout;
        public int di_mode_1;
        public int di_mode_2;
        public int di_mode_3;
        public int di_mode_4;
        public int di_mode_5;
        public int di_mode_6;
        public int di_mode_7;
        public int di_mode_8;
        public int di_mode_9;
        public int do_mode_1;
        public int tap_speed_1;
        public int tap_speed_2;
        public int tap_speed_3;
        public int tap_speed_4;
        public int tap_speed_5;
        public int di_simulate;
        public int di_value_set;
        public int ai_simulate;
        public int fan_mode;
    }

    public class operatingData {
        public int dc_bus_current;
        public int dc_bus_voltage;
        public int reference_current;
        public int phase_a_current;
        public int phase_b_current;
        public int phase_c_current;
        public boolean is_active;
        public boolean is_alert;
        public int status_bits;
        public boolean is_running;
        public int requested_speed;
        public int speed;
        public int torque;
        public int direction;
        public int fault_count;
        public int warning_count;
        public int winding_temp_1;
        public int winding_temp_2;
        public int enclosure_temp;
        public int heatsink_temp;
        public int di_value;
        public float power_in;
        public float power_kw;
    }

    public static class ModbusDataType {
        public String type;
        public modbus startValue;
        public modbus endValue;

        ModbusDataType(String type,
                       modbus startValue,
                       modbus endValue) {
            this.type = type;
            this.startValue = startValue;
            this.endValue = endValue;

        }
    }

    private static HashMap<String, ModbusDataType> readOptions = new HashMap<String, ModbusDataType>() {
        {
            put("op_data", new ModbusDataType(operatingData.class.getTypeName(), modbus.modbus_address, modbus.power_out));
            put("settings", new ModbusDataType(Settings.class.getTypeName(), modbus.modbus_address, modbus.power_out));
        }
    };

    private static HashMap<String, ModbusDataType> writeOptions = new HashMap<>() {
        {
            put("settings", new ModbusDataType(Settings.class.getTypeName(), modbus.is_setup, modbus.ai_simulate));
            put("clear_faults", new ModbusDataType(Integer.class.getTypeName(), modbus.reset_alerts, modbus.reset_alerts));
            put("reset_device", new ModbusDataType(Integer.class.getTypeName(), modbus.reset_CPUs, modbus.reset_CPUs));
            put("clear_faults", new ModbusDataType(Integer.class.getTypeName(), modbus.reset_alerts, modbus.reset_alerts));
            put("clear_faults", new ModbusDataType(Integer.class.getTypeName(), modbus.reset_alerts, modbus.reset_alerts));
            put("clear_faults", new ModbusDataType(Integer.class.getTypeName(), modbus.reset_alerts, modbus.reset_alerts));
            put("clear_faults", new ModbusDataType(Integer.class.getTypeName(), modbus.reset_alerts, modbus.reset_alerts));
            put("clear_faults", new ModbusDataType(Integer.class.getTypeName(), modbus.reset_alerts, modbus.reset_alerts));
            put("clear_faults", new ModbusDataType(Integer.class.getTypeName(), modbus.reset_alerts, modbus.reset_alerts));
            put("is_setup", new ModbusDataType(Boolean.class.getTypeName(), modbus.is_setup, modbus.is_setup));
            put("is_identify", new ModbusDataType(Boolean.class.getTypeName(), modbus.is_identify, modbus.is_identify));
            put("operating_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.operating_mode, modbus.operating_mode));
            put("control_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.motor_speed_ctl, modbus.motor_speed_ctl));
            put("runstop_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.runStop_ctl, modbus.runStop_ctl));
            put("startup_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.motor_startup, modbus.motor_startup));
            put("forward_direction", new ModbusDataType(Integer.class.getTypeName(), modbus.Motor_direction, modbus.Motor_direction));
            put("directions_allowed", new ModbusDataType(Integer.class.getTypeName(), modbus.motor_direction_allow, modbus.motor_direction_allow));
            put("acceleration_time", new ModbusDataType(Integer.class.getTypeName(), modbus.acceleration_time, modbus.acceleration_time));
            put("deceleration_time", new ModbusDataType(Integer.class.getTypeName(), modbus.deceleration_time, modbus.deceleration_time));
            put("s_curve_time", new ModbusDataType(Integer.class.getTypeName(), modbus.s_curve_time, modbus.s_curve_time));
            put("starting_torque", new ModbusDataType(Integer.class.getTypeName(), modbus.starting_torque_low, modbus.starting_torque_high));
            put("min_speed", new ModbusDataType(Integer.class.getTypeName(), modbus.min_rpm_low, modbus.min_rpm_low));
            put("max_speed", new ModbusDataType(Integer.class.getTypeName(), modbus.max_rpm_low, modbus.max_rpm_high));
            put("brake_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.brake_mode, modbus.brake_mode));
            put("status_bits", new ModbusDataType(Integer.class.getTypeName(), modbus.is_maintenance, modbus.is_maintenance));
            put("manual_mode_run", new ModbusDataType(Integer.class.getTypeName(), modbus.motor_state_maintenance, modbus.motor_state_maintenance));
            put("manual_mode_speed", new ModbusDataType(Integer.class.getTypeName(), modbus.requested_rpm_maint_low, modbus.requested_rpm_maint_low));
            put("manual_mode_direction", new ModbusDataType(Integer.class.getTypeName(), modbus.motor_direction_mt, modbus.motor_direction_mt));
            put("restart_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.restart_mode, modbus.restart_mode));
            put("restart_attempts", new ModbusDataType(Integer.class.getTypeName(), modbus.restart_attempts, modbus.restart_attempts));
            put("restart_interval", new ModbusDataType(Integer.class.getTypeName(), modbus.restart_interval, modbus.restart_interval));
            put("restart_alert", new ModbusDataType(Integer.class.getTypeName(), modbus.restart_alert, modbus.restart_alert));
            put("skip_width", new ModbusDataType(Integer.class.getTypeName(), modbus.skip_width, modbus.skip_width));
            put("overload_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.overload_mode, modbus.overload_mode));
            put("overload_tolerance", new ModbusDataType(Integer.class.getTypeName(), modbus.overload_tol, modbus.overload_tol));
            put("overspeed_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.overspeed_mode, modbus.overspeed_mode));
            put("overspeed_tolerance", new ModbusDataType(Integer.class.getTypeName(), modbus.overspeed_tol, modbus.overspeed_tol));
            put("speeddev_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.speeddev_mode, modbus.speeddev_mode));
            put("speeddev_tolerance", new ModbusDataType(Integer.class.getTypeName(), modbus.speeddev_tol, modbus.speeddev_tol));
            put("error_response", new ModbusDataType(Integer.class.getTypeName(), modbus.error_response, modbus.error_response));
            put("warning_response", new ModbusDataType(Integer.class.getTypeName(), modbus.warn_response, modbus.warn_response));
            put("warning_timeout", new ModbusDataType(Integer.class.getTypeName(), modbus.warn_timeout, modbus.warn_timeout));
            put("di_mode_1", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_1, modbus.di_mode_1));
            put("di_mode_2", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_2, modbus.di_mode_2));
            put("di_mode_3", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_3, modbus.di_mode_3));
            put("di_mode_4", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_4, modbus.di_mode_4));
            put("di_mode_5", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_5, modbus.di_mode_5));
            put("di_mode_6", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_6, modbus.di_mode_6));
            put("di_mode_7", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_7, modbus.di_mode_7));
            put("di_mode_8", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_8, modbus.di_mode_8));
            put("di_mode_9", new ModbusDataType(Integer.class.getTypeName(), modbus.di_mode_9, modbus.di_mode_9));
            put("do_mode_1", new ModbusDataType(Integer.class.getTypeName(), modbus.do_mode_1, modbus.do_mode_1));
            put("tap_speed_1", new ModbusDataType(Integer.class.getTypeName(), modbus.tap_speed_1_low, modbus.tap_speed_1_high));
            put("tap_speed_2", new ModbusDataType(Integer.class.getTypeName(), modbus.tap_speed_2_low, modbus.tap_speed_2_high));
            put("tap_speed_3", new ModbusDataType(Integer.class.getTypeName(), modbus.tap_speed_3_low, modbus.tap_speed_3_high));
            put("tap_speed_4", new ModbusDataType(Integer.class.getTypeName(), modbus.tap_speed_4_low, modbus.tap_speed_4_high));
            put("tap_speed_5", new ModbusDataType(Integer.class.getTypeName(), modbus.tap_speed_5_low, modbus.tap_speed_5_high));
            put("di_simulate", new ModbusDataType(Integer.class.getTypeName(), modbus.di_simulate, modbus.di_simulate));
            put("di_value_set", new ModbusDataType(Integer.class.getTypeName(), modbus.di_values, modbus.di_values));
            put("ai_simulate", new ModbusDataType(Integer.class.getTypeName(), modbus.ai_simulate, modbus.ai_simulate));
            put("fan_mode", new ModbusDataType(Integer.class.getTypeName(), modbus.fan_mode, modbus.fan_mode));
        }

    };

    public static String ReadOptions() {
        String msg = "\n";
     /*   foreach(String key in readOptions.Keys) {
            msg += key + Environment.NewLine;
        }
     */
        for (Map.Entry<String, ModbusDataType> entry : readOptions.entrySet()) {
            msg += entry.getKey() + "\n";
        }
        return msg;
    }

    public static String WriteOptions() {
        String msg = "\n";

        for (Map.Entry<String, ModbusDataType> entry : writeOptions.entrySet()) {
            msg += entry.getKey() + "\n";
        }
        return msg;
    }

    public static boolean IsValidRead(String readString) {
        return readOptions.containsKey(readString);
    }

    public static boolean IsValidWrite(String writeString) {
        return writeOptions.containsKey(writeString);
    }

    public byte[] GetReadMessage(String readString) {
        if (readString == "version") {
            return modbusReadBlock(500, 515);
        } else if (version.isSupported != 0) {
            logger.info("Cannot create read messages till version is set");
            return null;
        }
        if (!readOptions.containsKey(readString)) return null;
        return modbusReadBlock((int) version.Addresses.ValueOf(readOptions.get(readString).startValue), (int) version.Addresses.ValueOf(readOptions.get(readString).endValue));
    }

    public byte[] GetWriteMessage(String writeString) {
        if (version.isSupported != 0) {
            logger.info("Cannot create read messages till version is set");
            return null;
        }
        if (!writeOptions.containsKey(writeString)) return null;
        ModbusDataType writeType = writeOptions.get(writeString);
        boolean validData = false;
        if (writeType.type == Settings.class.getTypeName()) {
            Object settings = _settings;
            settings = fillObject(settings);
            byte[] data = settingsToBytes((Settings) settings);
            return modbusWriteBlock((int) version.Addresses.ValueOf(writeType.startValue), data);
        } else if (writeType.type == Boolean.class.getTypeName()) {
            boolean result = false;
            while (!validData) {
                System.out.println(String.format("{0}(boolean):", writeString));
               /* String input = System.read();
                validData = Boolean.parseBoolean(input, out result);*/
            }
            return modbusWriteReg((int) version.Addresses.ValueOf(writeType.startValue), result ? 1 : 0);
        } else if (writeType.type == Integer.class.getTypeName()) {
            int result = 0;
            while (!validData) {
                System.out.println(String.format("{0}(int):", writeString));
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                validData = Boolean.parseBoolean(input);
            }
            return modbusWriteReg((int) version.Addresses.ValueOf(writeType.startValue), result);
        } else if (writeType.type == Integer.class.getTypeName()) {
            int result = 0;
            while (!validData) {
                System.out.println(String.format("{0}(int):", writeString));
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                validData = Boolean.parseBoolean(input);
            }
            byte[] data = new byte[4];
            writeint(result, data, 0);
            return modbusWriteBlock((int) version.Addresses.ValueOf(writeType.startValue), data);
        } else {
            logger.entering("Writing data type {0} is not supported", writeType.type.toString());
            return null;
        }
    }

    private Object fillObject(Object obj) {

        for (int i = 0; i < obj.getClass().getDeclaredFields().length; i++) {
            boolean validData = false;
            Field currentFiled = obj.getClass().getDeclaredFields()[i];

            while (!validData) {
                try {
                    System.out.println(String.format("{0}({1})[{2}]:", currentFiled.getName(), currentFiled.getType().getTypeName(), currentFiled.get(this).toString()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input == "") {
                    //    Console.SetCursorPosition(0, Console.CursorTop - 1);
                    //  Console.WriteLine(field.GetValue(obj).ToString());
                    validData = true;
                    continue;
                }
                Method parse = null;
                try {
                    parse = currentFiled.getType().getMethod("Parse", String.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                if (parse != null) {
                    try {
                        Object result = parse.invoke(null, input);
                        validData = true;
                        currentFiled.set(obj, result);
                    } catch (Exception e) {
                        System.out.println(String.format("Entry {0} was not a {1}", input, currentFiled.getType().getTypeName()));
                    }
                }
            }
        }
        return obj;
    }

    public String DriveResponseParser(String option, byte[] data) {
        if (!IsValidRead(option)) {
            return "Bad Parser Option";
        }
        ModbusDataType dataType = readOptions.get(option);
        if (dataType.type == Settings.class.getTypeName()) {
            _settings = getSettings(data);
            Gson gson = new Gson();
//make temp object
            //  YourObject tempStorage = (YourObject) gson.fromJson(new FileReader(theJsonFile), YourObject.class);

            return new Gson().toJson(_settings);//JsonConvert.SerializeObject(_settings, Formatting.Indented);
        } else if (dataType.type == operatingData.class.getTypeName()) {
            operatingData op_data = getOpData(data);
            return new Gson().toJson(op_data);
        } else if (dataType.type == Integer.class.getTypeName()) {
            return String.valueOf(readint(data, 0));
        } else {
            return "Unhandled data type";
        }
    }

    private operatingData getOpData(byte[] data_array) {
        operatingData op_data = new operatingData();
        try {
            op_data.dc_bus_current = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.dc_bus_current));
            op_data.dc_bus_voltage = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.dc_bus_voltage));
            op_data.reference_current = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.reference_current));
            op_data.phase_a_current = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.phase_a_current));
            op_data.phase_b_current = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.phase_b_current));
            op_data.phase_c_current = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.phase_c_current));
            op_data.is_active = (readint(data_array, (int) version.Addresses.ValueOf(dataReceive.is_active)) == 1);
            op_data.is_alert = (readint(data_array, (int) version.Addresses.ValueOf(dataReceive.is_alert)) == 1);
            op_data.status_bits = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.is_maintenance));
            op_data.is_running = (readint(data_array, (int) version.Addresses.ValueOf(dataReceive.motor_state)) == 1);
            op_data.requested_speed = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.requested_speed));
            op_data.speed = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.speed));
            op_data.torque = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.torque));
            op_data.direction = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.direction));
            op_data.fault_count = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.fault_count));
            op_data.warning_count = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.warning_count));
            op_data.winding_temp_1 = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.winding_temp_1));
            op_data.winding_temp_2 = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.winding_temp_2));
            op_data.enclosure_temp = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.enclosure_temp));
            op_data.heatsink_temp = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.heatsink_temp));
            op_data.di_value = readint(data_array, (int) version.Addresses.ValueOf(dataReceive.di_value));
            op_data.power_in = (float) readint(data_array, (int) version.Addresses.ValueOf(dataReceive.power_in)) / 1000;
            op_data.power_kw = (float) readint(data_array, (int) version.Addresses.ValueOf(dataReceive.power_out)) / 1000;
        } catch (Exception e) {
            logger.info("Data could not be parsed into OperatingData");
        }
        return op_data;
    }

    public static int toInt32_2(byte[] bytes, int index) {
        int a = (int) ((int) (0xff & bytes[index]) << 32 | (int) (0xff & bytes[index + 1]) << 40 | (int) (0xff & bytes[index + 2]) << 48 | (int) (0xff & bytes[index + 3]) << 56);
        // int a = (int)((int)(0xff & bytes[index]) << 56 | (int)(0xff & bytes[index + 1]) << 48 | (int)(0xff & bytes[index + 2]) << 40 | (int)(0xff & bytes[index + 3]) << 32);
        //Array.Resize;
        return a;
    }

    private byte[] settingsToBytes(Settings settings) {
        Integer start = version.Addresses.ValueOf(modbus.is_setup);
        byte[] modbusData = new byte[(version.Addresses.ValueOf(modbus.is_maintenance) + 1 - version.Addresses.ValueOf(modbus.is_setup)) * 2];
        modbusData[(version.Addresses.ValueOf(modbus.is_setup) - start) * 2 + 1] = (byte) ((settings.is_setup) ? 1 : 0);
        modbusData[(version.Addresses.ValueOf(modbus.is_identify) - start) * 2 + 1] = (byte) ((settings.is_identify) ? 1 : 0);
        modbusData[(version.Addresses.ValueOf(modbus.operating_mode) - start) * 2 + 1] = (byte) settings.operating_mode;
        modbusData[(version.Addresses.ValueOf(modbus.motor_speed_ctl) - start) * 2 + 1] = (byte) settings.control_mode;
        modbusData[(version.Addresses.ValueOf(modbus.runStop_ctl) - start) * 2 + 1] = (byte) settings.runstop_mode;
        modbusData[(version.Addresses.ValueOf(modbus.motor_startup) - start) * 2 + 1] = (byte) settings.startup_mode;
        modbusData[(version.Addresses.ValueOf(modbus.motor_direction_initial) - start) * 2 + 1] = (byte) settings.forward_direction;
        modbusData[(version.Addresses.ValueOf(modbus.motor_direction_allow) - start) * 2 + 1] = (byte) settings.directions_allowed;
        //  BitConverter.GetBytes((int) (settings.acceleration_time)).Reverse().ToArray().CopyTo(modbusData, (version.Addresses.ValueOf(modbus.acceleration_time) - start) * 2);
        // BitConverter.GetBytes((int) (settings.deceleration_time)).Reverse().ToArray().CopyTo(modbusData, (version.Addresses.ValueOf(modbus.deceleration_time) - start) * 2);
        // BitConverter.GetBytes((int) (settings.s_curve_time)).Reverse().ToArray().CopyTo(modbusData, (version.Addresses.ValueOf(modbus.s_curve_time) - start) * 2);
        writeint(settings.starting_torque, modbusData, (version.Addresses.ValueOf(modbus.starting_torque_low) - start) * 2);
        writeint(settings.min_speed, modbusData, (version.Addresses.ValueOf(modbus.min_rpm_low) - start) * 2);
        writeint(settings.max_speed, modbusData, (version.Addresses.ValueOf(modbus.max_rpm_low) - start) * 2);
        modbusData[(version.Addresses.ValueOf(modbus.brake_mode) - start) * 2 + 1] = (byte) settings.brake_mode;

        modbusData[(version.Addresses.ValueOf(modbus.restart_mode) - start) * 2 + 1] = (byte) settings.restart_mode;
        modbusData[(version.Addresses.ValueOf(modbus.restart_attempts) - start) * 2 + 1] = (byte) settings.restart_attempts;
        modbusData[(version.Addresses.ValueOf(modbus.restart_interval) - start) * 2 + 1] = (byte) settings.restart_interval;
        modbusData[(version.Addresses.ValueOf(modbus.restart_alert) - start) * 2 + 1] = (byte) settings.restart_alert;
        int tmpSpeed = settings.skip_speed_1;
        if (settings.skip_speed_enable_1) {
            tmpSpeed |= 0x80000000;
        }
        writeint(tmpSpeed, modbusData, (version.Addresses.ValueOf(modbus.skip_speed_1_low) - start) * 2);
        tmpSpeed = settings.skip_speed_2;
        if (settings.skip_speed_enable_2) {
            tmpSpeed |= 0x80000000;
        }
        writeint(tmpSpeed, modbusData, (version.Addresses.ValueOf(modbus.skip_speed_2_low) - start) * 2);
        tmpSpeed = settings.skip_speed_3;
        if (settings.skip_speed_enable_3) {
            tmpSpeed |= 0x80000000;
        }
        writeint(tmpSpeed, modbusData, (version.Addresses.ValueOf(modbus.skip_speed_3_low) - start) * 2);

        //intToByteArray((int) (settings.skip_width)).toString().CopyTo(modbusData, (version.Addresses.ValueOf(modbus.skip_width) - start) * 2);

        modbusData[(version.Addresses.ValueOf(modbus.overload_mode) - start) * 2 + 1] = (byte) settings.overload_mode;
        //BitConverter.GetBytes((int) (settings.overload_tolerance)).Reverse().ToArray().CopyTo(modbusData, (version.Addresses.ValueOf(modbus.overload_tol) - start) * 2);

        modbusData[(version.Addresses.ValueOf(modbus.overspeed_mode) - start) * 2 + 1] = (byte) settings.overspeed_mode;
        //BitConverter.GetBytes((int) (settings.overspeed_tolerance)).Reverse().ToArray().CopyTo(modbusData, (version.Addresses.ValueOf(modbus.overspeed_tol) - start) * 2);

        modbusData[(version.Addresses.ValueOf(modbus.speeddev_mode) - start) * 2 + 1] = (byte) settings.speeddev_mode;
        //  ((int) (settings.speeddev_tolerance)).Reverse().ToArray().CopyTo(modbusData, (version.Addresses.ValueOf(modbus.speeddev_tol) - start) * 2);

        modbusData[(version.Addresses.ValueOf(modbus.error_response) - start) * 2 + 1] = (byte) settings.error_response;

        modbusData[(version.Addresses.ValueOf(modbus.warn_response) - start) * 2 + 1] = (byte) settings.warning_response;
        modbusData[(version.Addresses.ValueOf(modbus.warn_timeout) - start) * 2 + 1] = (byte) settings.warning_timeout;

        modbusData[(version.Addresses.ValueOf(modbus.di_mode_1) - start) * 2 + 1] = (byte) settings.di_mode_1;
        modbusData[(version.Addresses.ValueOf(modbus.di_mode_2) - start) * 2 + 1] = (byte) settings.di_mode_2;
        modbusData[(version.Addresses.ValueOf(modbus.di_mode_3) - start) * 2 + 1] = (byte) settings.di_mode_3;
        modbusData[(version.Addresses.ValueOf(modbus.di_mode_4) - start) * 2 + 1] = (byte) settings.di_mode_4;
        modbusData[(version.Addresses.ValueOf(modbus.di_mode_5) - start) * 2 + 1] = (byte) settings.di_mode_5;
        modbusData[(version.Addresses.ValueOf(modbus.di_mode_6) - start) * 2 + 1] = (byte) settings.di_mode_6;
        modbusData[(version.Addresses.ValueOf(modbus.di_mode_7) - start) * 2 + 1] = (byte) settings.di_mode_7;
        modbusData[(version.Addresses.ValueOf(modbus.di_mode_8) - start) * 2 + 1] = (byte) settings.di_mode_8;
        modbusData[(version.Addresses.ValueOf(modbus.di_mode_9) - start) * 2 + 1] = (byte) settings.di_mode_9;

        modbusData[(version.Addresses.ValueOf(modbus.do_mode_1) - start) * 2 + 1] = (byte) settings.do_mode_1;

        writeint(settings.tap_speed_1, modbusData, (version.Addresses.ValueOf(modbus.tap_speed_1_low) - start) * 2);
        writeint(settings.tap_speed_2, modbusData, (version.Addresses.ValueOf(modbus.tap_speed_2_low) - start) * 2);
        writeint(settings.tap_speed_3, modbusData, (version.Addresses.ValueOf(modbus.tap_speed_3_low) - start) * 2);
        writeint(settings.tap_speed_4, modbusData, (version.Addresses.ValueOf(modbus.tap_speed_4_low) - start) * 2);
        writeint(settings.tap_speed_5, modbusData, (version.Addresses.ValueOf(modbus.tap_speed_5_low) - start) * 2);
        modbusData[(version.Addresses.ValueOf(modbus.fan_mode) - start) * 2 + 1] = (byte) settings.fan_mode;

        writeint(settings.status_bits, modbusData, (version.Addresses.ValueOf(modbus.is_maintenance) - start) * 2);

        return modbusData;
    }

    private Settings getSettings(byte[] data_array) {
        Settings settings_data = new Settings();
        try {
            settings_data.is_setup = (readint(data_array, (int) version.Addresses.ValueOf(modbus.is_setup) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) == 1);
            settings_data.is_identify = (readint(data_array, ((int) version.Addresses.ValueOf(modbus.is_identify) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2) == 1);
            settings_data.operating_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.operating_mode) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.control_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.motor_speed_ctl) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.runstop_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.runStop_ctl) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.startup_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.motor_startup) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.forward_direction = readint(data_array, ((int) version.Addresses.ValueOf(modbus.motor_direction_initial) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.directions_allowed = readint(data_array, ((int) version.Addresses.ValueOf(modbus.motor_direction_allow) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.acceleration_time = readint(data_array, ((int) version.Addresses.ValueOf(modbus.acceleration_time) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.deceleration_time = readint(data_array, ((int) version.Addresses.ValueOf(modbus.deceleration_time) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.s_curve_time = readint(data_array, ((int) version.Addresses.ValueOf(modbus.s_curve_time) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.starting_torque = readint(data_array, ((int) version.Addresses.ValueOf(modbus.starting_torque_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.min_speed = readint(data_array, ((int) version.Addresses.ValueOf(modbus.min_rpm_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.max_speed = readint(data_array, ((int) version.Addresses.ValueOf(modbus.max_rpm_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.brake_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.brake_mode) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.status_bits = readint(data_array, ((int) version.Addresses.ValueOf(modbus.is_maintenance) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.manual_mode_run = readint(data_array, ((int) version.Addresses.ValueOf(modbus.motor_state_maintenance) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.manual_mode_speed = readint(data_array, ((int) version.Addresses.ValueOf(modbus.requested_rpm_maint_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.manual_mode_direction = readint(data_array, ((int) version.Addresses.ValueOf(modbus.motor_direction_mt) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.restart_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.restart_mode) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.restart_attempts = readint(data_array, ((int) version.Addresses.ValueOf(modbus.restart_attempts) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.restart_interval = readint(data_array, ((int) version.Addresses.ValueOf(modbus.restart_interval) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.restart_alert = readint(data_array, ((int) version.Addresses.ValueOf(modbus.restart_alert) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            int tmpSpeed = readint(data_array, ((int) version.Addresses.ValueOf(modbus.skip_speed_1_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.skip_speed_1 = 0x7FFFFFFF & tmpSpeed;
            settings_data.skip_speed_enable_1 = ((0x80000000 & tmpSpeed) != 0);
            tmpSpeed = readint(data_array, ((int) version.Addresses.ValueOf(modbus.skip_speed_2_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.skip_speed_2 = 0x7FFFFFFF & tmpSpeed;
            settings_data.skip_speed_enable_2 = ((0x80000000 & tmpSpeed) != 0);
            tmpSpeed = readint(data_array, ((int) version.Addresses.ValueOf(modbus.skip_speed_3_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.skip_speed_3 = 0x7FFFFFFF & tmpSpeed;
            settings_data.skip_speed_enable_3 = ((0x80000000 & tmpSpeed) != 0);
            settings_data.skip_width = readint(data_array, ((int) version.Addresses.ValueOf(modbus.skip_width) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.overload_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.overload_mode) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.overload_tolerance = readint(data_array, ((int) version.Addresses.ValueOf(modbus.overload_tol) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.overspeed_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.overspeed_mode) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.overspeed_tolerance = readint(data_array, ((int) version.Addresses.ValueOf(modbus.overspeed_tol) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.speeddev_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.speeddev_mode) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.speeddev_tolerance = readint(data_array, ((int) version.Addresses.ValueOf(modbus.speeddev_tol) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.error_response = readint(data_array, ((int) version.Addresses.ValueOf(modbus.error_response) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.warning_response = readint(data_array, ((int) version.Addresses.ValueOf(modbus.warn_response) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.warning_timeout = readint(data_array, ((int) version.Addresses.ValueOf(modbus.warn_timeout) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_1 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_1) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_2 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_2) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_3 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_3) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_4 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_4) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_5 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_5) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_6 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_6) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_7 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_7) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_8 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_8) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_mode_9 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_mode_9) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.do_mode_1 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.do_mode_1) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.tap_speed_1 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.tap_speed_1_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.tap_speed_2 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.tap_speed_2_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.tap_speed_3 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.tap_speed_3_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.tap_speed_4 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.tap_speed_4_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.tap_speed_5 = readint(data_array, ((int) version.Addresses.ValueOf(modbus.tap_speed_5_low) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_simulate = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_simulate) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.di_value_set = readint(data_array, ((int) version.Addresses.ValueOf(modbus.di_values) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.ai_simulate = readint(data_array, ((int) version.Addresses.ValueOf(modbus.ai_simulate) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
            settings_data.fan_mode = readint(data_array, ((int) version.Addresses.ValueOf(modbus.fan_mode) -
                    (int) version.Addresses.ValueOf(modbus.is_setup)) * 2);
        } catch (Exception e) {
            logger.info("Data could not be parsed to settings structure");
        }

        return settings_data;
    }

    private Integer readInt16(byte[] data, int offset) {
        if (data.length < offset + 2) return 0;
        byte[] temp = new byte[2];
        temp[0] = data[offset + 1];
        temp[1] = data[offset];
        return ByteBuffer.wrap(temp).order(ByteOrder.LITTLE_ENDIAN).getInt();//BitConverter.ToInt16(temp, 0);
    }

    private int readint(byte[] data, int offset) {
        if (data.length < offset + 2) return 0;
        byte[] temp = new byte[2];
        temp[0] = data[offset + 1];
        temp[1] = data[offset];
        return ByteBuffer.wrap(temp).order(ByteOrder.LITTLE_ENDIAN).getInt();//BitConverter.Toint(temp, 0);
    }
/*
    private int readint(byte[] data, int offset) {
        byte[] temp = new byte[4];
        if (data.length < offset + 2) return 0;
        if (data.length < offset + 4) return 1;//BitConverter.Toint(data, offset);
        temp[0] = data[offset + 1];
        temp[1] = data[offset];
        temp[2] = data[offset + 3];
        temp[3] = data[offset + 2];
        return ByteBuffer.wrap(temp).order(ByteOrder.LITTLE_ENDIAN).getInt();//BitConverter.Toint(temp, 0);
    }*/

    private float readFloat(byte[] data, int offset) {
        if (data.length < offset + 4) return 0;
        byte[] temp = new byte[4];
        temp[0] = data[offset];
        temp[1] = data[offset + 1];
        temp[2] = data[offset + 2];
        temp[3] = data[offset + 3];
        return ByteBuffer.wrap(temp).order(ByteOrder.LITTLE_ENDIAN).getFloat();//BitConverter.ToSingle(temp, 0);
    }

    private float readFloatFlip(byte[] data, int offset) {
        if (data.length < offset + 4) return 0;
        byte[] temp = new byte[4];
        temp[1] = data[offset];
        temp[0] = data[offset + 1];
        temp[3] = data[offset + 2];
        temp[2] = data[offset + 3];
        return ByteBuffer.wrap(temp).order(ByteOrder.LITTLE_ENDIAN).getFloat();//BitConverter.ToSingle(temp, 0);
    }

  /*  private void writeint(int intValue, byte[] byteArray, int offset) {
        byte[] _val =intToByteArray(intValue);
        byteArray[offset] = _val[1];
        byteArray[offset + 1] = _val[0];
    }*/

    private void writeint(int intValue, byte[] byteArray, int offset) {
        byte[] _val = intToByteArray(intValue);
        byteArray[offset] = _val[1];
        byteArray[offset + 1] = _val[0];
        byteArray[offset + 2] = _val[3];
        byteArray[offset + 3] = _val[2];

    }

    public static final byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

    private byte[] modbusReadBlock(int registerStart, int registerEnd) {
        int readLength = registerEnd + 1 - registerStart;
        if (readLength <= 0 || readLength > 240) {
            readLength = 1;
        }

        byte[] msg = new byte[6];
        msg[0] = (byte) 0xF7;
        msg[1] = 0x03;
        msg[2] = (byte) ((registerStart >> 8) & 0xFF);
        msg[3] = (byte) (registerStart & 0xFF);
        msg[4] = (byte) ((readLength >> 8) & 0xFF);
        msg[5] = (byte) (readLength & 0xFF);
        return AppendCrc(msg);
    }

    private byte[] modbusWriteReg(int register, int value) {
        byte[] msg = new byte[6];
        msg[0] = 0;
        msg[1] = 0x06;
        msg[2] = (byte) ((register >> 8) & 0xFF);
        msg[3] = (byte) (register & 0xFF);
        msg[4] = (byte) ((value >> 8) & 0xFF);
        msg[5] = (byte) (value & 0xFF);
        return AppendCrc(msg);
    }

    private byte[] modbusWriteBlock(int registerStart, byte[] data) {
        if (data.length <= 0) {
            logger.info("Cannot write modbus block with empty data");
            return null;
        }
        if (data.length % 2 != 0) {
            logger.info("Cannot write modbus block with odd number of bytes");
            return null;
        }
        if (data.length > 240) {
            logger.info("Cannot write modbus block greater than 240 bytes");
            return null;
        }
        int headerLength = 7;
        int registerCount = data.length / 2;
        byte[] msg = new byte[headerLength + data.length];
        msg[0] = 0;
        msg[1] = 0x10;
        msg[2] = (byte) ((registerStart >> 8) & 0xFF);
        msg[3] = (byte) (registerStart & 0xFF);
        msg[4] = (byte) ((registerCount >> 8) & 0xFF);
        msg[5] = (byte) (registerCount & 0xFF);
        msg[6] = (byte) (data.length);
        System.arraycopy(data, 0, msg, 7, data.length);
        return AppendCrc(msg);
    }

    public static byte[] AppendCrc(byte[] msgIn) {
        byte crcHi = (byte) 0xFF;
        byte crcLo = (byte) 0xFF;
        int uIndex;
        byte[] msgOut = new byte[msgIn.length + 2];

        for (int x = 0; x < msgIn.length; x++) {
            msgOut[x] = msgIn[x];
            uIndex = crcHi ^ msgIn[x];
            crcHi = (byte) (crcLo ^ crcHiTable[uIndex]);
            crcLo = crcLoTable[uIndex];
        }
        msgOut[msgIn.length] = crcHi;
        msgOut[msgIn.length + 1] = crcLo;

        return msgOut;
    }

    private static byte[] crcHiTable = new byte[]{
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40
    };

    private static byte[] crcLoTable = new byte[]{
            0x00, (byte) 0xC0, (byte) 0xC1, 0x01, (byte) 0xC3, 0x03, 0x02, (byte) 0xC2, (byte) 0xC6, 0x06, 0x07, (byte) 0xC7, 0x05, (byte) 0xC5, (byte) 0xC4, 0x04,
            (byte) 0xCC, 0x0C, 0x0D, (byte) 0xCD, 0x0F, (byte) 0xCF, 0xCE, 0x0E, 0x0A, 0xCA, 0xCB, 0x0B, 0xC9, 0x09, 0x08, 0xC8,
            0xD8, 0x18, 0x19, 0xD9, 0x1B, 0xDB, 0xDA, 0x1A, 0x1E, 0xDE, 0xDF, 0x1F, 0xDD, 0x1D, 0x1C, 0xDC,
            0x14, 0xD4, 0xD5, 0x15, 0xD7, 0x17, 0x16, 0xD6, 0xD2, 0x12, 0x13, 0xD3, 0x11, 0xD1, 0xD0, 0x10,
            0xF0, 0x30, 0x31, 0xF1, 0x33, 0xF3, 0xF2, 0x32, 0x36, 0xF6, 0xF7, 0x37, 0xF5, 0x35, 0x34, 0xF4,
            0x3C, 0xFC, 0xFD, 0x3D, 0xFF, 0x3F, 0x3E, 0xFE, 0xFA, 0x3A, 0x3B, 0xFB, 0x39, 0xF9, 0xF8, 0x38,
            0x28, 0xE8, 0xE9, 0x29, 0xEB, 0x2B, 0x2A, 0xEA, 0xEE, 0x2E, 0x2F, 0xEF, 0x2D, 0xED, 0xEC, 0x2C,
            0xE4, 0x24, 0x25, 0xE5, 0x27, 0xE7, 0xE6, 0x26, 0x22, 0xE2, 0xE3, 0x23, 0xE1, 0x21, 0x20, 0xE0,
            0xA0, 0x60, 0x61, 0xA1, 0x63, 0xA3, 0xA2, 0x62, 0x66, 0xA6, 0xA7, 0x67, 0xA5, 0x65, 0x64, 0xA4,
            0x6C, 0xAC, 0xAD, 0x6D, 0xAF, 0x6F, 0x6E, 0xAE, 0xAA, 0x6A, 0x6B, 0xAB, 0x69, 0xA9, 0xA8, 0x68,
            0x78, 0xB8, 0xB9, 0x79, 0xBB, 0x7B, 0x7A, 0xBA, 0xBE, 0x7E, 0x7F, 0xBF, 0x7D, 0xBD, 0xBC, 0x7C,
            0xB4, 0x74, 0x75, 0xB5, 0x77, 0xB7, 0xB6, 0x76, 0x72, 0xB2, 0xB3, 0x73, 0xB1, 0x71, 0x70, 0xB0,
            0x50, 0x90, 0x91, 0x51, 0x93, 0x53, 0x52, 0x92, 0x96, 0x56, 0x57, 0x97, 0x55, 0x95, 0x94, 0x54,
            0x9C, 0x5C, 0x5D, 0x9D, 0x5F, 0x9F, 0x9E, 0x5E, 0x5A, 0x9A, 0x9B, 0x5B, 0x99, 0x59, 0x58, 0x98,
            0x88, 0x48, 0x49, 0x89, 0x4B, 0x8B, 0x8A, 0x4A, 0x4E, 0x8E, 0x8F, 0x4F, 0x8D, 0x4D, 0x4C, 0x8C,
            0x44, 0x84, 0x85, 0x45, 0x87, 0x47, 0x46, 0x86, 0x82, 0x42, 0x43, 0x83, 0x41, 0x81, 0x80, 0x40
    };
}

