package com.company;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;

class EnumsV1 extends EnumsBase {

    @Override
    public Integer ValueOf(modbus key) {
        return null;
    }

    @Override
    public Integer ValueOf(settingsSend key) {
        return null;
    }

    @Override
    public Integer ValueOf(settingsReceive key) {
        return null;
    }

    @Override
    public Integer ValueOf(dataReceive key) {
        return null;
    }

    @Override
    public Integer ValueOf(alertReceive key) {
        return null;
    }

    @Override
    public Integer ValueOf(faults key) {
        return null;
    }

    @Override
    public Integer ValueOf(warnings key) {
        return null;
    }
}

//package com.company;
//
//import java.util.Dictionary;
//import java.util.HashMap;
//import java.util.HashSet;
//
//class EnumsV1 extends EnumsBase {
//  /*  public override ModbusEnum
//
//    ValueOf(modbus key) {
//        if (modbusValues.ContainsKey(key)) {
//            return modbusValues[key];
//        } else {
//            return  0xFFFF;
//        }
//    }
//
//    public override SettingsSendEnum
//
//    ValueOf(settingsSend key) {
//        if (settingsSendValues.ContainsKey(key)) {
//            return settingsSendValues[key];
//        } else {
//            return  0xFFFF;
//        }
//    }
//
//    public override SettingsReceiveEnum
//
//    ValueOf(settingsReceive key) {
//        if (settingsReceiveValues.ContainsKey(key)) {
//            return settingsReceiveValues[key];
//        } else {
//            return (SettingsReceiveEnum) 0xFFFF;
//        }
//    }
//
//    public override DataReceiveEnum
//
//    ValueOf(dataReceive key) {
//        if (dataReceiveValues.ContainsKey(key)) {
//            return dataReceiveValues[key];
//        } else {
//            return (DataReceiveEnum) 0xFFFF;
//        }
//    }
//
//    public override AlertReceiveEnum
//
//    ValueOf(alertReceive key) {
//        if (alertReceiveValues.ContainsKey(key)) {
//            return alertReceiveValues[key];
//        } else {
//            return (AlertReceiveEnum) 0xFFFF;
//        }
//    }
//
//    public override FaultsEnum
//
//    ValueOf(faults key) {
//        if (faultsValues.ContainsKey(key)) {
//            return faultsValues[key];
//        } else {
//            return (FaultsEnum) 0xFFFF;
//        }
//    }
//
//    public override WarningsEnum
//
//    ValueOf(warnings key) {
//        if (warningsValues.ContainsKey(key)) {
//            return warningsValues[key];
//        } else {
//            return (WarningsEnum) 0xFFFF;
//        }
//    }*/
//
//    private HashMap<modbus, Integer> modbusValues = new HashMap<>() {
//        {
//            put(modbus.modbus_address, 0);
//            put(modbus.DC_Bus_current_adc, 1);
//            put(modbus.DC_Bus_voltage, 2);
//            put(modbus.Reference_current_adc, 3);
//            put(modbus.Phase_A_current_adc, 4);
//            put(modbus.Phase_B_current_adc, 5);
//            put(modbus.Phase_C_current_adc, 6);
//            put(modbus.is_active, 7);
//            put(modbus.is_alert, 8);
//            put(modbus.is_maintenance2, 9);
//            put(modbus.is_setup2, 10);
//            put(modbus.Motor_state, 12);
//            put(modbus.Requested_rpm_low, 13);
//            put(modbus.Requested_rpm_high, 14);
//            put(modbus.Cur_rpm_low, 15);
//            put(modbus.Cur_rpm_high, 16);
//            put(modbus.is_identify2, 11);
//            put(modbus.Motor_direction, 17);
//            put(modbus.Torque_low, 18);
//            put(modbus.Torque_high, 19);
//            put(modbus.error_count, 20);
//            put(modbus.warning_count, 21);
//            put(modbus.is_identify2, 11);
//            put(modbus.Motor_temperature1, 22);
//            put(modbus.Motor_temperature2, 23);
//            put(modbus.Inverter_temperature, 24);
//            put(modbus.Heat_Sink_temperature, 25);
//            put(modbus.Search_coil_A2, 26);
//            put(modbus.Search_coil_C2, 27);
//            put(modbus.Search_coil_B2, 28);
//            put(modbus.Search_coil_A1, 29);
//            put(modbus.Search_coil_C1, 30);
//            put(modbus.Search_coil_B1, 31);
//            put(modbus.dig_inputs, 32);
//            put(modbus.power_in, 33);
//            put(modbus.power_out, 34);
//            put(modbus.is_setup, 60);
//            put(modbus.is_identify, 61
//            );
//            put(modbus.operating_mode, 62);
//            put(modbus.motor_speed_ctl, 63);
//            put(modbus.runStop_ctl, 64);
//            put(modbus.motor_startup, 65);
//            put(modbus.motor_direction_initial, 66);
//            put(modbus.motor_direction_allow, 67);
//            put(modbus.acceleration_time, 68);
//            put(modbus.deceleration_time, 69);
//            put(modbus.s_curve_time, 70);
//            put(modbus.starting_torque_low, 71);
//            put(modbus.starting_torque_high, 72);
//            put(modbus.min_rpm_low, 73);
//            put(modbus.min_rpm_high, 74);
//            put(modbus.max_rpm_low, 75);
//            put(modbus.max_rpm_high, 76);
//            put(modbus.brake_mode, 77);
//            put(modbus.restart_mode, 78);
//            put(modbus.restart_attempts, 79);
//            put(modbus.restart_interval, 80);
//            put(modbus.restart_alert, 81);
//            put(modbus.skip_speed_1_low, 82);
//            put(modbus.skip_speed_1_high, 83);
//            put(modbus.skip_speed_2_low, 84);
//            put(modbus.skip_speed_2_high, 85);
//            put(modbus.skip_speed_3_low, 86);
//            put(modbus.skip_speed_3_high, 87);
//            put(modbus.skip_width, 88);
//            put(modbus.overload_mode, 89);
//            put(modbus.overload_tol, 90);
//            put(modbus.overspeed_mode, 91);
//            put(modbus.overspeed_tol, 92);
//            put(modbus.speeddev_mode, 93);
//            put(modbus.speeddev_tol, 94);
//            put(modbus.error_response, 95);
//            put(modbus.warn_response, 96);
//            put(modbus.warn_timeout, 97);
//            put(modbus.di_mode_1, 98);
//            put(modbus.di_mode_2, 99);
//            put(modbus.di_mode_3, 100);
//            put(modbus.di_mode_4, 101);
//            put(modbus.di_mode_5, 102);
//            put(modbus.di_mode_6, 103);
//            put(modbus.di_mode_8, 105
//            );
//            put(modbus.di_mode_7, 104);
//            put(modbus.di_mode_9, 106);
//            put(modbus.do_mode_1, 107);
//            put(modbus.tap_speed_1_low, 108);
//            put(modbus.tap_speed_1_high, 109);
//            put(modbus.tap_speed_2_low, 110);
//            put(modbus.tap_speed_2_high, 111);
//            put(modbus.tap_speed_3_low, 112);
//            put(modbus.tap_speed_3_high, 113);
//            put(modbus.tap_speed_4_low, 114);
//            put(modbus.tap_speed_4_high, 115);
//            put(modbus.requested_rpm_maint_low, 132);
//            put(modbus.motor_state_maintenance, 131);
//            put(modbus.is_maintenance, 130);
//            put(modbus.fan_mode, 118);
//            put(modbus.tap_speed_5_high, 117);
//            put(modbus.tap_speed_5_low, 116);
//            put(modbus.requested_rpm_maint_high, 133);
//            put(modbus.motor_direction_mt, 134);
//            put(modbus.jog_maint_mode, 135);
//            put(modbus.jog_maint_rpm_low, 136);
//            put(modbus.jog_maint_rpm_high, 137);
//            put(modbus.di_simulate, 138);
//            put(modbus.di_values, 139);
//            put(modbus.ai_simulate, 140);
//            put(modbus.is_alert2, 150);
//            put(modbus.error_count2, 151);
//            put(modbus.warning_count2, 152);
//            put(modbus.err_word_1, 153);
//            put(modbus.err_word_2, 154);
//            put(modbus.err_word_3, 155);
//            put(modbus.warn_word_1, 156);
//            put(modbus.warn_word_2, 157);
//            put(modbus.warn_word_3, 158);
//            put(modbus.uptime_hours, 159);
//            put(modbus.is_online, 185);
//            put(modbus.motor_id_0, 186);
//            put(modbus.motor_id_1, 187);
//            put(modbus.motor_id_2, 188);
//            put(modbus.motor_id_3, 189);
//            put(modbus.motor_id_4, 190);
//            put(modbus.motor_id_5, 191);
//            put(modbus.flash_write_control, 192);
//            put(modbus.flash_update_control, 193);
//            put(modbus.logic_update_control, 195);
//            put(modbus.reset_alerts, 196);
//            put(modbus.mfg_mode_setting, 197);
//            put(modbus.mfg_mode_dig_out, 198);
//            put(modbus.board_type, 199);
//            put(modbus.induct_profile_idx, 200);
//            put(modbus.tune_cmd, 201);
//            put(modbus.tune_data, 202);
//            put(modbus.inductance, 203);
//            put(modbus.inductance_ready, 204);
//            put(modbus.firing_angle, 205);
//            put(modbus.eeprom_write_cmd, 599);
//            put(modbus.inverter_eeprom_base, 600);
//            put(modbus.pcb_fw_version, 799);
//            put(modbus.motor_eeprom_base, 800);
//            put(modbus.read_induct_prf, 1200);
//            put(modbus.settings_default_start, 1400);
//            put(modbus.settings_default_end, 1477);
//            put(modbus.debug_value_0, 4000);
//            put(modbus.debug_value_89, 4089);
//            put(modbus.index_pulses_start, 550);
//            put(modbus.index_pulses_end, 589);
//            put(modbus.reset_CPUs, 600);
//            put( modbus.modbus_config, 595);
//        }
//    };
//    private HashMap<settingsSend, Integer> settingsSendValues = new HashMap<>() {
//        {
//            put(settingsSend.updated1, 0);
//            // Indicates user data changed : bit indicates the respective variable
//
//            put(settingsSend.updated2, 2);
//            // Indicates user data changed : bit indicates the respective variable
//
//            put(settingsSend.updated3, 4);
//            // Indicates user data changed : bit indicates the respective variable
//
//            put(settingsSend.updated4, 6);
//            // Indicates user data changed : bit indicates the respective variable
//
//            put(settingsSend.updated5, 8);
//            // Indicates user data changed : bit indicates the respective variable
//
//            put(
//                    settingsSend.updated6, 10
//            );                    // Indicates user data changed : bit indicates the respective variable
//
//            put(
//                    settingsSend.is_setup, 12
//            );                    // If true, indicates the device has been setup.
//
//            put(
//                    settingsSend.is_identify, 14
//            );                 // If true, turns on the LED indicator to allow the user to identify the motor.
//
//            put(
//                    settingsSend.operating_mode, 16
//            );       // Operating mode (0=normal [default], 1=quiet)
//
//            {
//                settingsSend.motor_speed_ctl, 18
//            },             // How the speed is regulated (0, 0-10v [default], 1, modbus, 2, 0-20mA, 3, 4-20mA)
//
//            {
//                settingsSend.runStop_ctl, 20
//            },                 // How stops and starts are regulated (0, digital input [default], 1, modbus, 2, digital input && modbus (i.e. both must be active))
//
//            {
//                settingsSend.motor_startup, 22
//            },               // How to startup when the motor is turned on (0=immediate, 1=on command active [default], 2, on command cycled, i.e. detected transition from off to on)
//
//            {
//                settingsSend.motor_direction_initial, 24
//            },     // Defines what direction is "forwards" for the motor looking from the drive end (i.e. with the shaft furthest from you). (0, Clockwise [default], 1, Anti-Clockwise)
//
//            {
//                settingsSend.motor_direction_allow, 26
//            },       // Which directions are allowed (0, forward and reverse [default], 1=forward only)
//
//            {
//                settingsSend.acceleration_time, 28
//            },           // Time to accelerate from stop to full speed, or from current speed to a new speed. (0 [default], as fast as permitted by the hardware design)
//
//            {
//                settingsSend.deceleration_time, 30
//            },           // Time to decelerate to stop from full speed, or from current speed to a new speed. (0 [default], as fast as permitted by the hardware design)
//
//            {
//                settingsSend.s_curve_time, 32
//            },                // Time added to Acceleration_time/Deceleration_time to create a smoother ramp (1/2 at start of ramp, 1/2 at end). (0 [default])
//
//            {
//                settingsSend.empty_address_1, 34
//            },
//
//            {
//                settingsSend.starting_torque, 36
//            },             // Maximum torque when starting (Nm) (0 [default], as high as permitted by the hardware design)
//
//            {
//                settingsSend.min_rpm, 40
//            },                     // The minimum speed in RPM (if running) [0 [default], motor nameplate minimum speed]
//
//            {
//                settingsSend.max_rpm, 44
//            },                     // The maximum speed in RPM (if running) [0 [default], motor nameplate maximum speed]
//
//            {
//                settingsSend.brake_mode, 48
//            },                  // Whether the motor uses active braking for deceleration (0=coast [default], 1=active ramp)
//
//            {
//                settingsSend.is_maintenance, 50
//            },              // If true, indicates the device is in maintenance mode. In maintenance mode, the device is manually controlled (ignores other input signals) [Default false]
//
//            {
//                settingsSend.motor_State_maintenance, 52
//            },     // Whether the motor is running (1=running, 2=stopped [default]) (while in maintenance mode only)
//
//            {
//                settingsSend.empty_address_2, 54
//            },
//
//            {
//                settingsSend.requested_rpm_ma, 56
//            },         // Requested speed (RPM) [default 0] (while in maintenance mode only)
//
//            {
//                settingsSend.motor_direction_mt, 60
//            },          // Direction Forward(0) [default] or Reverse(1) (while in maintenance mode only)
//
//            {
//                settingsSend.jog_maint_mode, 62
//            },              // Whether the jog is active, which moves the motor forward slowly at the jog speed (1=running, 2=stopped [default]). Jog only functions while in maintenance mode, when the motor_state_maintenance is stopped (2).
//
//            {
//                settingsSend.jog_maint_rpm, 64
//            },               // Requested speed (RPM) of the jog speed [default 300RPM] (while in maintenance mode only)
//
//            {
//                settingsSend.restart_mode, 68
//            },                // Restart mode (0=count restart attempts [default], 1=count successful restarts)
//
//            {
//                settingsSend.restart_attempts, 70
//            },            // Restart attempts: the number of attempts to restart the motor after a power failure [default 2]
//
//            {
//                settingsSend.restart_interval, 72
//            },            // Restart interval, the number of seconds between restart attempts [default 10].
//
//            {
//                settingsSend.restart_alert, 74
//            },               // Restart alert (0=no alarm on restart [default], 1=alarm while restarting)
//
//            {
//                settingsSend.skip_speed_1, 76
//            },                // The first prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, but accelerates and decelerates the motor through the prohibited bandwidth.
//
//            {
//                settingsSend.skip_speed_2, 80
//            },                // The second prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, but accelerates and decelerates the motor through the prohibited bandwidth.
//
//            {
//                settingsSend.skip_speed_3, 84
//            },                // The third prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, but accelerates and decelerates the motor through the prohibited bandwidth.
//
//            {
//                settingsSend.skip_width, 88
//            },                  // The dead-band interval around each selected skip speed reference point. Speeds between the skip speed - 0.5 * dead band and skip speed + 0.5 * dead band will be prohibited. (Default 60 RPM.)
//
//            {
//                settingsSend.overload_mode, 90
//            },               // How to respond to current overload on the motor. 0= Limit to rated, no overload: Don't deliver more than the rated current to the motor. 1, Shutdown at overload: Deliver up to the overload tolerance of the motor, shutdown above this. 2, Shutdown at safety limit: Deliver up to the overload tolerance of the motor, shutdown at thermal safety limit.
//
//            {
//                settingsSend.overload_tol, 92
//            },                // How far above the rated current the motor will run, as a percentage of the nameplate current rating (default=15, 15% above the rated current).
//
//            {
//                settingsSend.overspeed_mode, 94
//            },              // How to respond when the motor runs faster than the motor's user-entered maximum speed. 0= Continue [default], 1=Stop
//
//            {
//                settingsSend.overspeed_tol, 96
//            },               // How far above the rated speed the motor will run, as a percentage of the user-entered maximum speed.(default=15).
//
//            {
//                settingsSend.speeddev_mode, 98
//            },               // How to respond when the speed commanded is significantly different from the observed speed. 0= Continue [default], 1=Stop
//
//            {
//                settingsSend.speeddev_tol, 100
//            },                // How far above or below the commanded speed is permitted before an alarm is raised (RPM), default, 60.
//
//            {
//                settingsSend.error_response, 102
//            },              // The action taken when a critical fault is reported. 0, Coast to stop [default]: stop the motor by turning off input power. 2, Ramp to stop: actively brake the motor to a stop.
//
//            {
//                settingsSend.warn_response, 104
//            },              // The action taken when a warning fault is reported. 0, Continue with alarm: generate an alert, but continue running [default]. 1, Coast to stop: stop the motor by turning off input power.2, Ramp to stop: actively brake the motor to a stop.
//
//            {
//                settingsSend.warn_timeout, 106
//            },               // Time a warning must be inactive before it is automatically reset
//
//            {
//                settingsSend.di_mode_1, 108
//            },                  // Defines the action taken when digital input 1 is open or closed. 0=Disable, 1=Run [default], 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
//
//            {
//                settingsSend.di_mode_2, 110
//            },                  // Defines the action taken when digital input 2 is open or closed. 0=Disable, 1=Run, 2=Reverse [default], 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
//
//            {
//                settingsSend.di_mode_3, 112
//            },                  // Defines the action taken when digital input 3 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop [default], 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
//
//            {
//                settingsSend.di_mode_4, 114
//            },                  // Defines the action taken when digital input 4 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault [default], 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
//
//            {
//                settingsSend.di_mode_5, 116
//            },                  // Defines the action taken when digital input 5 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset [default], 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
//
//            {
//                settingsSend.di_mode_6, 118
//            },                  // Defines the action taken when digital input 6 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3
//
//            {
//                settingsSend.di_mode_7, 120
//            },                  // Defines the action taken when digital input 7 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1 [default], 8=Speed tap setting 2, 9=Speed tap setting 3
//
//            {
//                settingsSend.di_mode_8, 122
//            },                  // Defines the action taken when digital input 8 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2 [default], 9=Speed tap setting 3
//
//            {
//                settingsSend.di_mode_9, 124
//            },                  // Defines the action taken when digital input 9 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2 [default], 9=Speed tap setting 3
//
//            {
//                settingsSend.do_mode_1, 126
//            },                  // Defines the purpose of the digital out. 0=Disable, 1=Active, 2=Running, 3=Fault [default], 4=Alert (either Warning or Fault)
//
//            {
//                settingsSend.tap_speed_1, 128
//            },                // If a digital input is set to "Speed tap setting 1", defines the speed that the motor will run at.
//
//            {
//                settingsSend.tap_speed_2, 132
//            },                // If a digital input is set to "Speed tap setting 2", defines the speed that the motor will run at.
//
//            {
//                settingsSend.tap_speed_3, 136
//            },                // If a digital input is set to "Speed tap setting 3", defines the speed that the motor will run at.
//
//            {
//                settingsSend.tap_speed_4, 142
//            },                // If a digital input is set to "Speed tap setting 4", defines the speed that the motor will run at.
//
//            {
//                settingsSend.tap_speed_5, 146
//            },                // If a digital input is set to "Speed tap setting 5", defines the speed that the motor will run at.
//
//            {
//                settingsSend.di_simulate, 150
//            },                // Whether digital input is simulated: 0, use real input [default], 1, use simulated value.  One bit for each input.
//
//            {
//                settingsSend.di_value, 152
//            },                   // If digital input is simulated, whether that value is open (0) or closed (1).  One bit for each input
//
//        }
//
//        ;
//
//        private Dictionary<settingsReceive, SettingsReceiveEnum> settingsReceiveValues = new Dictionary<settingsReceive, SettingsReceiveEnum>() {
//            //{ settingsReceive.firmware_ver, (SettingsReceiveEnum)2 },
//            //{ settingsReceive.ver_length, (SettingsReceiveEnum)16 },
//            //{ settingsReceive.staging_ver, (SettingsReceiveEnum)18 },
//            //{ settingsReceive.staging_length, (SettingsReceiveEnum)16 },
//            {
//                settingsReceive.updated1, (SettingsReceiveEnum) 4
//            },
//
//            {
//                settingsReceive.updated2, (SettingsReceiveEnum) 6
//            },
//
//            {
//                settingsReceive.updated3, (SettingsReceiveEnum) 8
//            },
//
//            {
//                settingsReceive.updated4, (SettingsReceiveEnum) 10
//            },
//
//            {
//                settingsReceive.updated5, (SettingsReceiveEnum) 12
//            },
//
//            {
//                settingsReceive.updated6, (SettingsReceiveEnum) 14
//            },
//
//            {
//                settingsReceive.is_setup, (SettingsReceiveEnum) 16
//            },
//
//            {
//                settingsReceive.is_identify, (SettingsReceiveEnum) 18
//            },
//
//            {
//                settingsReceive.op_mode, (SettingsReceiveEnum) 20
//            },
//
//            {
//                settingsReceive.ctrl_mode, (SettingsReceiveEnum) 22
//            },
//
//            {
//                settingsReceive.runstop_mode, (SettingsReceiveEnum) 24
//            },
//
//            {
//                settingsReceive.startup_mode, (SettingsReceiveEnum) 26
//            },
//
//            {
//                settingsReceive.rotational_direction, (SettingsReceiveEnum) 28
//            },
//
//            {
//                settingsReceive.directions_allowed, (SettingsReceiveEnum) 30
//            },
//
//            {
//                settingsReceive.acceleration_time, (SettingsReceiveEnum) 32
//            },
//
//            {
//                settingsReceive.deceleration_time, (SettingsReceiveEnum) 34
//            },
//
//            {
//                settingsReceive.s_curve_time, (SettingsReceiveEnum) 36
//            },
//
//            {
//                settingsReceive.starting_torque, (SettingsReceiveEnum) 40
//            },
//
//            {
//                settingsReceive.min_speed, (SettingsReceiveEnum) 44
//            },
//
//            {
//                settingsReceive.max_speed, (SettingsReceiveEnum) 48
//            },
//
//            {
//                settingsReceive.brake_mode, (SettingsReceiveEnum) 52
//            },
//
//            {
//                settingsReceive.is_maintenance, (SettingsReceiveEnum) 54
//            },
//
//            {
//                settingsReceive.is_running_mt, (SettingsReceiveEnum) 56
//            },
//
//            {
//                settingsReceive.speed_mt, (SettingsReceiveEnum) 60
//            },
//
//            {
//                settingsReceive.direction_mt, (SettingsReceiveEnum) 64
//            },
//
//            {
//                settingsReceive.is_jog_mt, (SettingsReceiveEnum) 66
//            },
//
//            {
//                settingsReceive.speed_jog_mt, (SettingsReceiveEnum) 68
//            },
//
//            {
//                settingsReceive.restart_mode, (SettingsReceiveEnum) 72
//            },
//
//            {
//                settingsReceive.restart_attempts, (SettingsReceiveEnum) 74
//            },
//
//            {
//                settingsReceive.restart_interval, (SettingsReceiveEnum) 76
//            },
//
//            {
//                settingsReceive.restart_alert, (SettingsReceiveEnum) 78
//            },
//
//            {
//                settingsReceive.skip_speed_1, (SettingsReceiveEnum) 80
//            },
//
//            {
//                settingsReceive.skip_speed_2, (SettingsReceiveEnum) 84
//            },
//
//            {
//                settingsReceive.skip_speed_3, (SettingsReceiveEnum) 88
//            },
//
//            {
//                settingsReceive.skip_width, (SettingsReceiveEnum) 92
//            },
//
//            {
//                settingsReceive.overload_mode, (SettingsReceiveEnum) 94
//            },
//
//            {
//                settingsReceive.overload_tol, (SettingsReceiveEnum) 96
//            },
//
//            {
//                settingsReceive.overspeed_mode, (SettingsReceiveEnum) 98
//            },
//
//            {
//                settingsReceive.overspeed_tol, (SettingsReceiveEnum) 100
//            },
//
//            {
//                settingsReceive.speeddev_mode, (SettingsReceiveEnum) 102
//            },
//
//            {
//                settingsReceive.speeddev_tol, (SettingsReceiveEnum) 104
//            },
//
//            {
//                settingsReceive.error_resp, (SettingsReceiveEnum) 106
//            },
//
//            {
//                settingsReceive.warn_resp, (SettingsReceiveEnum) 108
//            },
//
//            {
//                settingsReceive.warn_timeout, (SettingsReceiveEnum) 110
//            },
//
//            {
//                settingsReceive.di_mode_1, (SettingsReceiveEnum) 112
//            },
//
//            {
//                settingsReceive.di_mode_2, (SettingsReceiveEnum) 114
//            },
//
//            {
//                settingsReceive.di_mode_3, (SettingsReceiveEnum) 116
//            },
//
//            {
//                settingsReceive.di_mode_4, (SettingsReceiveEnum) 118
//            },
//
//            {
//                settingsReceive.di_mode_5, (SettingsReceiveEnum) 120
//            },
//
//            {
//                settingsReceive.di_mode_6, (SettingsReceiveEnum) 122
//            },
//
//            {
//                settingsReceive.di_mode_7, (SettingsReceiveEnum) 124
//            },
//
//            {
//                settingsReceive.di_mode_8, (SettingsReceiveEnum) 126
//            },
//
//            {
//                settingsReceive.di_mode_9, (SettingsReceiveEnum) 128
//            },
//
//            {
//                settingsReceive.do_mode_1, (SettingsReceiveEnum) 130
//            },
//
//            {
//                settingsReceive.tap_speed_1, (SettingsReceiveEnum) 132
//            },
//
//            {
//                settingsReceive.tap_speed_2, (SettingsReceiveEnum) 136
//            },
//
//            {
//                settingsReceive.tap_speed_3, (SettingsReceiveEnum) 130
//            },
//
//            {
//                settingsReceive.tap_speed_4, (SettingsReceiveEnum) 144
//            },
//
//            {
//                settingsReceive.tap_speed_5, (SettingsReceiveEnum) 148
//            },
//
//            {
//                settingsReceive.di_simulate, (SettingsReceiveEnum) 152
//            },
//
//            {
//                settingsReceive.di_value_set, (SettingsReceiveEnum) 154
//            },               // If digital input is simulated, whether that value is open (0) or closed (1).  One bit for each input
//
//        };
//
//        private Dictionary<dataReceive, DataReceiveEnum> dataReceiveValues = new Dictionary<dataReceive, DataReceiveEnum>() {
//            {
//                dataReceive.dc_bus_current, (DataReceiveEnum) 2
//            },
//
//            {
//                dataReceive.dc_bus_voltage, (DataReceiveEnum) 4
//            },
//
//            {
//                dataReceive.reference_current, (DataReceiveEnum) 6
//            },
//
//            {
//                dataReceive.phase_a_current, (DataReceiveEnum) 8
//            },
//
//            {
//                dataReceive.phase_b_current, (DataReceiveEnum) 10
//            },
//
//            {
//                dataReceive.phase_c_current, (DataReceiveEnum) 12
//            },
//
//            {
//                dataReceive.is_active, (DataReceiveEnum) 14
//            },
//
//            {
//                dataReceive.is_alert, (DataReceiveEnum) 16
//            },
//
//            {
//                dataReceive.is_maintenance, (DataReceiveEnum) 18
//            },
//
//            {
//                dataReceive.is_setup, (DataReceiveEnum) 20
//            },            //No corresponding modbus address
//
//            {
//                dataReceive.is_identify, (DataReceiveEnum) 22
//            },         //No corresponding modbus address
//
//            {
//                dataReceive.motor_state, (DataReceiveEnum) 24
//            },
//
//            {
//                dataReceive.requested_speed, (DataReceiveEnum) 26
//            },
//
//            {
//                dataReceive.speed, (DataReceiveEnum) 30
//            },
//
//            {
//                dataReceive.direction, (DataReceiveEnum) 34
//            },
//
//            {
//                dataReceive.torque, (DataReceiveEnum) 36
//            },
//
//            {
//                dataReceive.fault_count, (DataReceiveEnum) 40
//            },
//
//            {
//                dataReceive.warning_count, (DataReceiveEnum) 42
//            },
//
//            {
//                dataReceive.winding_temp_1, (DataReceiveEnum) 44
//            },
//
//            {
//                dataReceive.winding_temp_2, (DataReceiveEnum) 46
//            },
//
//            {
//                dataReceive.enclosure_temp, (DataReceiveEnum) 48
//            },
//
//            {
//                dataReceive.heatsink_temp, (DataReceiveEnum) 50
//            },
//
//            {
//                dataReceive.search_coil_sa2, (DataReceiveEnum) 52
//            },
//
//            {
//                dataReceive.search_coil_sc2, (DataReceiveEnum) 54
//            },
//
//            {
//                dataReceive.search_coil_sb2, (DataReceiveEnum) 56
//            },
//
//            {
//                dataReceive.search_coil_sa1, (DataReceiveEnum) 58
//            },
//
//            {
//                dataReceive.search_coil_sc1, (DataReceiveEnum) 60
//            },
//
//            {
//                dataReceive.search_coil_sb1, (DataReceiveEnum) 62
//            },
//
//            {
//                dataReceive.di_value, (DataReceiveEnum) 64
//            },
//
//            {
//                dataReceive.power_in, (DataReceiveEnum) 66
//            },
//
//            {
//                dataReceive.power_out, (DataReceiveEnum) 68
//            }
//        };
//        private HashMap<alertReceive, AlertReceiveEnum> alertReceiveValues = new Dictionary<alertReceive, AlertReceiveEnum>() {
//            {
//                alertReceive.is_alert, (AlertReceiveEnum) 0
//            },
//
//            {
//                alertReceive.fault_count, (AlertReceiveEnum) 2
//            },
//
//            {
//                alertReceive.warning_count, (AlertReceiveEnum) 4
//            },
//
//            {
//                alertReceive.faults_start, (AlertReceiveEnum) 6
//            },
//
//            {
//                alertReceive.faults_length, (AlertReceiveEnum) 6
//            },
//
//            {
//                alertReceive.warnings_start, (AlertReceiveEnum) 12
//            },
//
//            {
//                alertReceive.warning_length, (AlertReceiveEnum) 6
//            }
//        };
//        private HashMap<faults, FaultsEnum> faultsValues = new HashMap<>() {
//            {
//                faults.fault_current_overload, (FaultsEnum) 0
//            },
//
//            {
//                faults.fault_power_loss, (FaultsEnum) 1
//            },
//
//            {
//                faults.fault_overspeed, (FaultsEnum) 2
//            },
//
//            {
//                faults.fault_motor_temp, (FaultsEnum) 3
//            },
//
//            {
//                faults.fault_heatsink_temp, (FaultsEnum) 4
//            },
//
//            {
//                faults.fault_enclosure_temp, (FaultsEnum) 5
//            },
//
//            {
//                faults.fault_bus_current, (FaultsEnum) 6
//            },
//
//            {
//                faults.fault_bus_voltage, (FaultsEnum) 7
//            },
//
//            {
//                faults.fault_external_fault, (FaultsEnum) 8
//            },
//
//            {
//                faults.fault_no_motor, (FaultsEnum) 9
//            },
//
//            {
//                faults.fault_communication, (FaultsEnum) 10
//            },
//
//            {
//                faults.fault_eeprom, (FaultsEnum) 11
//            },
//
//            {
//                faults.fault_software, (FaultsEnum) 12
//            },
//
//            {
//                faults.fault_incompatible, (FaultsEnum) 13
//            },
//
//            {
//                faults.fault_restarts, (FaultsEnum) 14
//            }
//        };
//        private HashMap<warnings, WarningsEnum> warningsValues = new HashMap<>() {
//            {
//                warnings.warn_current_overload, (WarningsEnum) 0
//            },
//
//            {
//                warnings.warn_phase, (WarningsEnum) 1
//            },
//
//            {
//                warnings.warn_phase_loss, (WarningsEnum) 2
//            },
//
//            {
//                warnings.warn_overspeed, (WarningsEnum) 3
//            },
//
//            {
//                warnings.warn_speed_dev, (WarningsEnum) 4
//            },
//
//            {
//                warnings.warn_motor_temp, (WarningsEnum) 5
//            },
//
//            {
//                warnings.warn_heatsink_temp, (WarningsEnum) 6
//            },
//
//            {
//                warnings.warn_enclosure_temp, (WarningsEnum) 7
//            },
//
//            {
//                warnings.warn_bus_current, (WarningsEnum) 8
//            },
//
//            {
//                warnings.warn_bus_voltage, (WarningsEnum) 9
//            },
//
//            {
//                warnings.warn_communication, (WarningsEnum) 10
//            },
//
//            {
//                warnings.warn_firmware, (WarningsEnum) 11
//            }
//        };
//
//    }