package com.company;

public class Versions {
    public EnumsBase Addresses;
    public InverterEnumsBase Inverter;
    public MotorEnumsBase Motor;

    public int EnumVersion = -1;
    public int EnumVersionInverter = -1;
    public int EnumVersionMotor = -1;

    public String minVersion;
    public String version;
    public String maxVersion;


    public String getMinVersion() {
        return "01.00.00";
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

    private String _version = "";
    public int isSupported;
    private int _isSupported = -1;  //Flag for inverter code compatible, -1 = Not Checked, 0 = Compatible, 1 = Inverter version to high, 2 = Inverter version to low, 3 = lab version


    public int getIsSupported() {
        return _isSupported;
    }


    public int minVersionInverter = 1;

    public int maxVersionInverter = 2;


    public int versionInverter;

    public int getVersionInverter() {
        return _versionInverter;
    }

    public void setVersionInverter(int versionInverter) {
        this._versionInverter = versionInverter;
        setSupportedInverter();

    }

    private int _versionInverter = -1;
    public int isSupportedInverter;
    private int _isSupportedInverter = -1;  //Flag for inverter code compatible, -1 = Not Checked, 0 = Compatible, 1 = Inverter version to high, 2 = Inverter version to low, 3 = lab version


    public int getIsSupportedInverter() {
        return _isSupportedInverter;
    }


    public int minVersionMotor = 1;

    public int maxVersionMotor = 1;


    public int versionMotor;

    public int getVersionMotor() {
        return _versionMotor;
    }

    public void setVersionMotor(int versionMotor) {
        this._versionMotor = versionMotor;
        setSupportedMotor();

    }


    private int _versionMotor = -1;
    public int isSupportedMotor;

    public int getIsSupportedMotor() {
        return _isSupportedMotor;
    }

    private int _isSupportedMotor = -1;  //Flag for inverter code compatible, -1 = Not Checked, 0 = Compatible, 1 = Inverter version to high, 2 = Inverter version to low, 3 = lab version

    private void setSupported() {
        String[] minVersionString = minVersion.split(".");
        int[] allowedMin = new int[minVersionString.length];
        for (int i = 0; i < minVersionString.length; i++) {
            allowedMin[i] = Integer.parseInt(minVersionString[i]);
        }
        String[] maxVersionString = maxVersion.split(".");
        int[] allowMax = new int[minVersionString.length];
        for (int i = 0; i < maxVersionString.length; i++) {
            allowMax[i] = Integer.parseInt(maxVersionString[i]);
        }
        //  int[] allowedMin = minVersion.split(".").//.f=>{} Select(n = > Convert.ToInt32(n)).ToArray();
        // int[] allowedMax = maxVersion.split(".") (n = > Convert.ToInt32(n)).ToArray();
        try {
            String[] trimmedVersion = version.split(String.valueOf('-'));
            // int[] currentVersion = trimmedVersion[0].split(".") (n = > Convert.ToInt32(n)).ToArray();

            String[] currentVersionString = trimmedVersion[0].split(".");
            int[] currentVersion = new int[currentVersionString.length];
            for (int i = 0; i < minVersionString.length; i++) {
                currentVersion[i] = Integer.parseInt(currentVersionString[i]);
            }
            int loadVersion = -1;

            //Check Major
            if (currentVersion[0] > allowMax[0]) {
                _isSupported = 1;
            } else if (currentVersion[0] < allowedMin[0]) {
                _isSupported = 2;
            } else if (currentVersion[0] == 0) {
                loadVersion = 0;
            } else {
                //Major version is supported
                switch (currentVersion[0]) {
                    case 1:  //Major version 1
                        switch (currentVersion[1]) {
                            //Handle minor version
                            case 0: //Minor version 0
                            case 1:
                            case 2:
                                loadVersion = 1;
                                break;
                            case 3:
                                loadVersion = 2;
                                break;
                            case 4:
                                if (currentVersion[2] >= 5) {
                                    // Analog inputs panel will not work properly prior to V1.4.5
                                    loadVersion = 3;
                                } else {
                                    loadVersion = 2;
                                }
                                break;
                            default:
                                //Usupported Minor version
                                if (currentVersion[1] > 89) {
                                    loadVersion = 0; //Testing version minor revisions in the 90s are used for personal builds
                                } else if (currentVersion[1] > 0) {
                                    _isSupported = 1;
                                } else {
                                    _isSupported = 2;
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (currentVersion[1]) {
                            //Handle minor version
                            case 0: //Minor version 0
                            case 1:
                                loadVersion = 2;
                                break;
                            case 2:
                            case 3:
                                loadVersion = 3;
                                break;
                            default:
                                //Usupported Minor version
                                if (currentVersion[1] > 89) {
                                    loadVersion = 0; //Testing version minor revisions in the 90s are used for personal builds
                                } else if (currentVersion[1] > 0) {
                                    _isSupported = 1;
                                } else {
                                    _isSupported = 2;
                                }
                                break;
                        }
                        break;
                }
            }

            //ALL BUILDS ARE ALLOWED
            switch (loadVersion) {
                case 0:
                    _isSupported = 3; //testing version of code
                    EnumVersion = 2;
                    Addresses = new EnumsV2();
                    break;
                case 1:
                    _isSupported = 0;
                    EnumVersion = 1;
                    Addresses = new EnumsV1();
                    break;
                case 2:
                    _isSupported = 0;
                    EnumVersion = 2;
                    Addresses = new EnumsV2();
                    break;
                case 3:
                    _isSupported = 0;
                    EnumVersion = 3;
                    Addresses = new EnumsV3();
                    break;
            }
        } catch (Exception e) {
            _isSupported = 0;
            Addresses = new EnumsV1();
            EnumVersion = 1;
            return;
        }
    }

    private void setSupportedInverter() {
        if (versionInverter == 0 || versionInverter == 0xFF) {
            //An unprogrammed or lab version of the inverter connect with warning
            _isSupportedInverter = 3;
            Inverter = new InverterEnumsV2();
        } else if (versionInverter > maxVersionInverter) {
            _isSupportedInverter = 1;
        } else if (versionInverter < minVersionInverter) {
            _isSupportedInverter = 2;
        } else {
            _isSupportedInverter = 0;
            switch (versionInverter) {
                case 0:
                    Inverter = new InverterEnumsV1();
                    EnumVersionInverter = 1;
                    break;
                case 1:
                    Inverter = new InverterEnumsV1();
                    EnumVersionInverter = 1;
                    break;
                case 2:
                    Inverter = new InverterEnumsV2();
                    EnumVersionInverter = 2;
                    break;
            }
        }
    }

    private void setSupportedMotor() {
        if (versionMotor == 0 || versionMotor == 0xFF) {
            //An unprogrammed or lab version of the inverter connect with warning
            _isSupportedMotor = 3;
            Motor = new MotorEnumsV1();
        } else if (versionMotor > maxVersionMotor) {
            _isSupportedMotor = 1;
        } else if (versionMotor < minVersionMotor) {
            _isSupportedMotor = 2;
        } else {
            _isSupportedMotor = 0;
            switch (versionMotor) {
                case 0:
                    Motor = new MotorEnumsV1();
                    EnumVersionMotor = 1;
                    break;
                case 1:
                    Motor = new MotorEnumsV1();
                    EnumVersionMotor = 1;
                    break;
            }
        }
    }
}

enum ModbusEnum {

}

enum modbus {
    modbus_address,
    DC_Bus_current_adc,
    DC_Bus_voltage,
    Reference_current_adc,
    Phase_A_current_adc,
    Phase_B_current_adc,
    Phase_C_current_adc,
    is_active,
    is_alert,
    is_maintenance2,
    is_setup2,
    is_identify2,
    Motor_state,
    Requested_rpm_low,
    Requested_rpm_high,
    Cur_rpm_low,
    Cur_rpm_high,
    Motor_direction,
    Torque_low,
    Torque_high,
    error_count,
    warning_count,
    Motor_temperature1,
    Motor_temperature2,
    Inverter_temperature,
    Heat_Sink_temperature,
    Search_coil_A2,
    Search_coil_C2,
    Search_coil_B2,
    Search_coil_A1,
    Search_coil_C1,
    Search_coil_B1,
    dig_inputs,
    power_in,
    power_out,

    is_setup,
    is_identify,
    operating_mode,
    motor_speed_ctl,
    runStop_ctl,
    motor_startup,
    motor_direction_initial,
    motor_direction_allow,
    acceleration_time,
    deceleration_time,
    s_curve_time,
    starting_torque_low,
    starting_torque_high,
    min_rpm_low,
    min_rpm_high,
    max_rpm_low,
    max_rpm_high,
    brake_mode,
    restart_mode,
    restart_attempts,
    restart_interval,
    restart_alert,
    skip_speed_1_low,
    skip_speed_1_high,
    skip_speed_2_low,
    skip_speed_2_high,
    skip_speed_3_low,
    skip_speed_3_high,
    skip_width,
    overload_mode,
    overload_tol,
    overspeed_mode,
    overspeed_tol,
    speeddev_mode,
    speeddev_tol,
    error_response,
    warn_response,
    warn_timeout,
    di_mode_1,
    di_mode_2,
    di_mode_3,
    di_mode_4,
    di_mode_5,
    di_mode_6,
    di_mode_7,
    di_mode_8,
    di_mode_9,
    do_mode_1,
    tap_speed_1_low,
    tap_speed_1_high,
    tap_speed_2_low,
    tap_speed_2_high,
    tap_speed_3_low,
    tap_speed_3_high,
    tap_speed_4_low,
    tap_speed_4_high,
    tap_speed_5_low,
    tap_speed_5_high,
    fan_mode,

    is_maintenance,
    motor_state_maintenance,
    requested_rpm_maint_low,
    requested_rpm_maint_high,
    motor_direction_mt,
    jog_maint_mode,
    jog_maint_rpm_low,
    jog_maint_rpm_high,
    di_simulate,
    di_values,
    ai_simulate,
    ai_value_1_r1,
    ai_value_1_r2,
    ai_value_1_v1,
    ai_value_1_v2,
    ai_value_1_c1,
    ai_value_1_a2,
    ai_value_2_r1,
    ai_value_2_r2,
    ai_value_2_v1,
    ai_value_2_v2,
    ai_value_2_c1,
    ai_value_2_a2,
    ai_value_3_r1,
    ai_value_3_r2,
    ai_value_3_v1,
    ai_value_3_v2,
    ai_value_3_c1,
    ai_value_3_a2,
    ai_value_4_r1,
    ai_value_4_r2,
    ai_value_4_v1,
    ai_value_4_v2,
    ai_value_4_c1,
    ai_value_4_a2,
    ao_value_1_v1,
    ao_value_1_v2,

    is_alert2,
    error_count2,
    warning_count2,
    err_word_1,
    err_word_2,
    err_word_3,
    warn_word_1,
    warn_word_2,
    warn_word_3,
    is_online,
    motor_id_0,
    motor_id_1,
    motor_id_2,
    motor_id_3,
    motor_id_4,
    motor_id_5,
    flash_write_control,
    flash_update_control,
    logic_update_control,
    reset_alerts,
    mfg_mode_setting,
    mfg_mode_dig_out,
    board_type,
    uptime_hours,
    induct_profile_idx,
    tune_cmd,
    tune_data,
    inductance,
    inductance_ready,
    firing_angle,
    eeprom_write_cmd,
    inverter_eeprom_base,
    pcb_fw_version,
    motor_eeprom_base,
    read_induct_prf,
    settings_default_start,
    settings_default_end,
    debug_value_0,
    debug_value_89,
    index_pulses_start,
    index_pulses_end,
    reset_CPUs,
    modbus_config,
    logic_header_start,
    logic_header_end,
    logic_av_start,
    logic_av_end,
    logic_bv_start,
    logic_bv_end,

}

enum SettingsSendEnum {}

enum settingsSend {
    updated1,                    // Indicates user data changed : bit indicates the respective variable
    updated2,                    // Indicates user data changed : bit indicates the respective variable
    updated3,                    // Indicates user data changed : bit indicates the respective variable
    updated4,                    // Indicates user data changed : bit indicates the respective variable
    updated5,                    // Indicates user data changed : bit indicates the respective variable
    updated6,                   // Indicates user data changed : bit indicates the respective variable
    is_setup,                   // If true, indicates the device has been setup.
    is_identify,                // If true, turns on the LED indicator to allow the user to identify the motor.
    operating_mode,             // Operating mode (0=normal [default], 1=quiet)
    motor_speed_ctl,            // How the speed is regulated (0, 0-10v [default], 1, modbus, 2, 0-20mA, 3, 4-20mA)
    runStop_ctl,                // How stops and starts are regulated (0, digital input [default], 1, modbus, 2, digital input && modbus (i.e. both must be active))
    motor_startup,              // How to startup when the motor is turned on (0=immediate, 1=on command active [default], 2, on command cycled, i.e. detected transition from off to on)
    motor_direction_initial,    // Defines what direction is "forwards" for the motor looking from the drive end (i.e. with the shaft furthest from you). (0, Clockwise [default], 1, Anti-Clockwise)
    motor_direction_allow,      // Which directions are allowed (0, forward and reverse [default], 1=forward only)
    acceleration_time,          // Time to accelerate from stop to full speed, or from current speed to a new speed. (0 [default], as fast as permitted by the hardware design)
    deceleration_time,          // Time to decelerate to stop from full speed, or from current speed to a new speed. (0 [default], as fast as permitted by the hardware design)
    s_curve_time,               // Time added to Acceleration_time/Deceleration_time to create a smoother ramp (1/2 at start of ramp, 1/2 at end). (0 [default])
    empty_address_1,
    starting_torque,            // Maximum torque when starting (Nm) (0 [default], as high as permitted by the hardware design)
    min_rpm,                    // The minimum speed in RPM (if running) [0 [default],  motor nameplate minimum speed]
    max_rpm,                    // The maximum speed in RPM (if running) [0 [default],  motor nameplate maximum speed]
    brake_mode,                 // Whether the motor uses active braking for deceleration (0=coast [default], 1=active ramp)
    is_maintenance,             // If true, indicates the device is in maintenance mode. In maintenance mode, the device is manually controlled (ignores other input signals) [Default false]
    motor_State_maintenance,    // Whether the motor is running (1=running, 2=stopped [default]) (while in maintenance mode only)
    empty_address_2,
    requested_rpm_ma,          // Requested speed (RPM) [default 0] (while in maintenance mode only)
    motor_direction_mt,         // Direction Forward(0) [default] or Reverse(1) (while in maintenance mode only)
    jog_maint_mode,             // Whether the jog is active, which moves the motor forward slowly at the jog speed (1=running, 2=stopped [default]). Jog only functions while in maintenance mode, when the motor_state_maintenance is stopped (2).
    jog_maint_rpm,              // Requested speed (RPM) of the jog speed [default 300RPM] (while in maintenance mode only)
    restart_mode,               // Restart mode (0=count restart attempts [default], 1=count successful restarts)
    restart_attempts,           // Restart attempts: the number of attempts to restart the motor after a power failure [default 2]
    restart_interval,           // Restart interval, the number of seconds between restart attempts [default 10].
    restart_alert,              // Restart alert (0=no alarm on restart [default], 1=alarm while restarting)
    skip_speed_1,               // The first prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, but accelerates and decelerates the motor through the prohibited bandwidth.
    skip_speed_2,               // The second prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, but accelerates and decelerates the motor through the prohibited bandwidth.
    skip_speed_3,               // The third prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, but accelerates and decelerates the motor through the prohibited bandwidth.
    skip_width,                 // The dead-band interval around each selected skip speed reference point. Speeds between the skip speed - 0.5 * dead band and skip speed + 0.5 * dead band will be prohibited. (Default 60 RPM.)
    overload_mode,              // How to respond to current overload on the motor. 0= Limit to rated, no overload: Don't deliver more than the rated current to the motor. 1, Shutdown at overload: Deliver up to the overload tolerance of the motor, shutdown above this. 2, Shutdown at safety limit: Deliver up to the overload tolerance of the motor, shutdown at thermal safety limit.
    overload_tol,               // How far above the rated current the motor will run, as a percentage of the nameplate current rating (default=15, 15% above the rated current).
    overspeed_mode,             // How to respond when the motor runs faster than the motor's user-entered maximum speed. 0= Continue [default], 1=Stop
    overspeed_tol,              // How far above the rated speed the motor will run, as a percentage of the user-entered maximum speed.(default=15).
    speeddev_mode,              // How to respond when the speed commanded is significantly different from the observed speed. 0= Continue [default], 1=Stop
    speeddev_tol,               // How far above or below the commanded speed is permitted before an alarm is raised (RPM), default, 60.
    error_response,           // The action taken when a critical fault is reported. 0,  Coast to stop [default]: stop the motor by turning off input power. 2, Ramp to stop: actively brake the motor to a stop.
    warn_response,             // The action taken when a warning fault is reported. 0, Continue with alarm: generate an alert, but continue running [default]. 1, Coast to stop: stop the motor by turning off input power.2, Ramp to stop: actively brake the motor to a stop.
    warn_timeout,             // Seconds until warnings timeout
    di_mode_1,                 // Defines the action taken when digital input 1 is open or closed. 0=Disable, 1=Run [default], 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
    di_mode_2,                 // Defines the action taken when digital input 2 is open or closed. 0=Disable, 1=Run, 2=Reverse [default], 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
    di_mode_3,                 // Defines the action taken when digital input 3 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop [default], 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
    di_mode_4,                 // Defines the action taken when digital input 4 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault [default], 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
    di_mode_5,                 // Defines the action taken when digital input 5 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset [default], 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
    di_mode_6,                 // Defines the action taken when digital input 6 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
    di_mode_7,                 // Defines the action taken when digital input 7 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1 [default], 8=Speed tap setting 2, 9=Speed tap setting 3
    di_mode_8,                 // Defines the action taken when digital input 8 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2 [default], 9=Speed tap setting 3
    di_mode_9,                 // Defines the action taken when digital input 9 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2 [default], 9=Speed tap setting 3
    do_mode_1,                 // Defines the purpose of the digital out. 0=Disable, 1=Active, 2=Running, 3=Fault [default], 4=Alert (either Warning or Fault)
    tap_speed_1,               // If a digital input is set to "Speed tap setting 1", defines the speed that the motor will run at.
    tap_speed_2,               // If a digital input is set to "Speed tap setting 2", defines the speed that the motor will run at.
    tap_speed_3,               // If a digital input is set to "Speed tap setting 3", defines the speed that the motor will run at.
    tap_speed_4,               // If a digital input is set to "Speed tap setting 4", defines the speed that the motor will run at.
    tap_speed_5,               // If a digital input is set to "Speed tap setting 5", defines the speed that the motor will run at.
    di_simulate,               // Whether digital input is simulated: 0, use real input [default], 1, use simulated value.  One bit for each input.
    di_value,
    fan_mode                   // Inverter fan mode 1=Protection only, 0=Always On
}

enum SettingsReceiveEnum {}

enum settingsReceive {
    firmware_ver,
    ver_length,
    staging_ver,
    staging_length,
    updated1,
    updated2,
    updated3,
    updated4,
    updated5,
    updated6,
    is_setup,
    is_identify,
    op_mode,
    ctrl_mode,
    runstop_mode,
    startup_mode,
    rotational_direction,
    directions_allowed,
    acceleration_time,
    deceleration_time,
    s_curve_time,
    starting_torque,
    min_speed,
    max_speed,
    brake_mode,
    is_maintenance,
    is_running_mt,
    speed_mt,
    direction_mt,
    is_jog_mt,
    speed_jog_mt,
    restart_mode,
    restart_attempts,
    restart_interval,
    restart_alert,
    skip_speed_1,
    skip_speed_2,
    skip_speed_3,
    skip_width,
    overload_mode,
    overload_tol,
    overspeed_mode,
    overspeed_tol,
    speeddev_mode,
    speeddev_tol,
    error_resp,
    warn_resp,
    warn_timeout,
    di_mode_1,
    di_mode_2,
    di_mode_3,
    di_mode_4,
    di_mode_5,
    di_mode_6,
    di_mode_7,
    di_mode_8,
    di_mode_9,
    do_mode_1,
    tap_speed_1,
    tap_speed_2,
    tap_speed_3,
    tap_speed_4,
    tap_speed_5,
    di_simulate,
    di_value_set,
    modbus_config,
    modbus_address,
    ai_simulate,
    ai_value_1,
    ai_value_2,
    ai_value_3,
    ai_value_4,
    ao_value_1,
    fan_mode                   // Inverter fan mode 1=Protection only, 0=Always On
}

enum DataReceiveEnum {}

enum dataReceive {
    dc_bus_current,
    dc_bus_voltage,
    reference_current,
    phase_a_current,
    phase_b_current,
    phase_c_current,
    is_active,
    is_alert,
    is_maintenance,
    is_setup,
    is_identify,
    motor_state,
    requested_speed,
    speed,
    direction,
    torque,
    fault_count,
    warning_count,
    winding_temp_1,
    winding_temp_2,
    enclosure_temp,
    heatsink_temp,
    search_coil_sa2,
    search_coil_sc2,
    search_coil_sb2,
    search_coil_sa1,
    search_coil_sc1,
    search_coil_sb1,
    di_value,
    power_in,
    power_out,
}

enum AlertReceiveEnum {}

enum alertReceive {
    is_alert,
    fault_count,
    warning_count,
    faults_start,
    faults_length,
    warnings_start,
    warning_length,
}

enum FaultsEnum {}

enum faults {
    fault_current_overload,
    fault_power_loss,
    fault_overspeed,
    fault_motor_temp,
    fault_heatsink_temp,
    fault_enclosure_temp,
    fault_bus_current,
    fault_bus_voltage,
    fault_external_fault,
    fault_no_motor,
    fault_communication,
    fault_eeprom,
    fault_software,
    fault_incompatible,
    fault_restarts,
}

enum WarningsEnum {}

enum warnings {
    warn_current_overload,
    warn_phase,
    warn_phase_loss,
    warn_overspeed,
    warn_speed_dev,
    warn_motor_temp,
    warn_heatsink_temp,
    warn_enclosure_temp,
    warn_bus_current,
    warn_bus_voltage,
    warn_communication,
    warn_firmware,
}

enum MotorEEPROMStartEnum {}

enum motorEEPROMStart {
    motor_checksum,
    motor_version,
    motor_csum_high,
    motor_csum_low,
    motor_model,
    motor_hardware_version,
    motor_serial,
    motor_mfg_date,
    motor_mfgr_name,
    pcb_serial,
    pcb_mfg_date,
    pcb_mfgr_name,
    motor_speed_min,
    motor_speed_nom,
    motor_speed_max,
    motor_current_nom,
    motor_current_max,
    motor_service_factor,
    motor_power_rating,
    motor_duty,
    motor_max_ambient_temp,
    motor_voltage_rating,
}

enum MotorEEPROMLengthEnum {}

enum motorEEPROMLength {
    motor_checksum,
    motor_version,
    motor_csum_low,
    motor_csum_high,
    motor_model,
    motor_hardware_version,
    motor_serial,
    motor_mfg_date,
    motor_mfgr_name,
    pcb_serial,
    pcb_mfg_date,
    pcb_mfgr_name,
    motor_speed_min,
    motor_speed_nom,
    motor_speed_max,
    motor_current_nom,
    motor_current_max,
    motor_service_factor,
    motor_power_rating,
    motor_duty,
    motor_max_ambient_temp,
    motor_voltage_rating,
}

enum InverterEEPROMStartEnum {}

enum inverterEEPROMStart {
    inverter_checksum,
    inverter_version,
    inverter_csum_high,
    inverter_csum_low,
    inverter_model,
    inverter_hardware_version,
    inverter_mfg_date,
    inverter_serial,
    inverter_mfgr_name,
    power_board_serial,
    power_board_mfg_name,
    enclosure_serial,
    enclosure_mfg_name,
    inverter_voltage_in_1,
    inverter_voltage_in_2,
    inverter_freq_min_in,
    inverter_freq_max_in,
    inverter_current_nom_in_V1_1,
    inverter_current_nom_in_V1_2,  //Only used if inverter supports 1/3 phase operations
    inverter_current_max_out_V1,
    inverter_crowbar_V1,
    inverter_current_nom_in_V2_1,
    inverter_current_nom_in_V2_2,  //Only used if inverter supports 1/3 phase operations
    inverter_current_max_out_V2,
    inverter_crowbar_V2,
    inverter_phase_power_in,
    inverter_power_rating,
    inverter_acdc,
    inverter_current_nom_out_V1,    //Depricated - field does not exist after eeprom version 1
    inverter_current_nom_out_V2,    //Depricated - field does not exist after eeprom version 1
    inverter_corr_fac_1,
    inverter_corr_fac_2,
    inverter_int_hp,
    inverter_adc_offset,
    inverter_adc_scalar,
    inverter_dac_offset,
    inverter_dac_scalar,
}

enum InverterEEPROMLengthEnum {}

enum inverterEEPROMLength {
    inverter_checksum,
    inverter_version,
    inverter_csum_low,
    inverter_csum_high,
    inverter_model,
    inverter_hardware_version,
    inverter_mfg_date,
    inverter_serial,
    inverter_mfgr_name,
    power_board_serial,
    power_board_mfg_name,
    enclosure_serial,
    enclosure_mfg_name,
    inverter_voltage_in_1,
    inverter_voltage_in_2,
    inverter_freq_min_in,
    inverter_freq_max_in,
    inverter_current_nom_in_V1_1,
    inverter_current_nom_in_V1_2,  //Only used if inverter supports 1/3 phase operations
    inverter_current_max_out_V1,
    inverter_crowbar_V1,
    inverter_current_nom_in_V2_1,
    inverter_current_nom_in_V2_2,  //Only used if inverter supports 1/3 phase operations
    inverter_current_max_out_V2,
    inverter_crowbar_V2,
    inverter_phase_power_in,
    inverter_power_rating,
    inverter_acdc,
    inverter_current_nom_out_V1,    //Depricated - field does not exist after eeprom version 1
    inverter_current_nom_out_V2,    //Depricated - field does not exist after eeprom version 1
    inverter_corr_fac_1,
    inverter_corr_fac_2,
    inverter_int_hp,
    inverter_adc_offset,
    inverter_adc_scalar,
    inverter_dac_offset,
    inverter_dac_scalar,
}

