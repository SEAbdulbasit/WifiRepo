package com.company;

class EnumsV3 extends EnumsBase {
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
/*
package com.company;

class EnumsV3 extends EnumsBase {
    public override ModbusEnum

    ValueOf(modbus key) {
        if (modbusValues.ContainsKey(key)) {
            return modbusValues[key];
        } else {
            return (ModbusEnum) 0xFFFF;
        }
    }

    public override SettingsSendEnum

    ValueOf(settingsSend key) {
        if (settingsSendValues.ContainsKey(key)) {
            return settingsSendValues[key];
        } else {
            return (SettingsSendEnum) 0xFFFF;
        }
    }

    public override SettingsReceiveEnum

    ValueOf(settingsReceive key) {
        if (settingsReceiveValues.ContainsKey(key)) {
            return settingsReceiveValues[key];
        } else {
            return (SettingsReceiveEnum) 0xFFFF;
        }
    }

    public override DataReceiveEnum

    ValueOf(dataReceive key) {
        if (dataReceiveValues.ContainsKey(key)) {
            return dataReceiveValues[key];
        } else {
            return (DataReceiveEnum) 0xFFFF;
        }
    }

    public override AlertReceiveEnum

    ValueOf(alertReceive key) {
        if (alertReceiveValues.ContainsKey(key)) {
            return alertReceiveValues[key];
        } else {
            return (AlertReceiveEnum) 0xFFFF;
        }
    }

    public override FaultsEnum

    ValueOf(faults key) {
        if (faultsValues.ContainsKey(key)) {
            return faultsValues[key];
        } else {
            return (FaultsEnum) 0xFFFF;
        }
    }

    public override WarningsEnum

    ValueOf(warnings key) {
        if (warningsValues.ContainsKey(key)) {
            return warningsValues[key];
        } else {
            return (WarningsEnum) 0xFFFF;
        }
    }

    private Dictionary<modbus, ModbusEnum> modbusValues = new Dictionary<modbus, ModbusEnum>() {
        {
            modbus.modbus_address, (ModbusEnum) 0
        },

        {
            modbus.DC_Bus_current_adc, (ModbusEnum) 1
        },

        {
            modbus.DC_Bus_voltage, (ModbusEnum) 2
        },

        {
            modbus.Reference_current_adc, (ModbusEnum) 3
        },

        {
            modbus.Phase_A_current_adc, (ModbusEnum) 4
        },

        {
            modbus.Phase_B_current_adc, (ModbusEnum) 5
        },

        {
            modbus.Phase_C_current_adc, (ModbusEnum) 6
        },

        {
            modbus.is_active, (ModbusEnum) 7
        },

        {
            modbus.is_alert, (ModbusEnum) 8
        },

        {
            modbus.is_maintenance2, (ModbusEnum) 9
        },

        {
            modbus.is_setup2, (ModbusEnum) 10
        },

        {
            modbus.is_identify2, (ModbusEnum) 11
        },

        {
            modbus.Motor_state, (ModbusEnum) 12
        },

        {
            modbus.Requested_rpm_low, (ModbusEnum) 13
        },

        {
            modbus.Requested_rpm_high, (ModbusEnum) 14
        },

        {
            modbus.Cur_rpm_low, (ModbusEnum) 15
        },

        {
            modbus.Cur_rpm_high, (ModbusEnum) 16
        },

        {
            modbus.Motor_direction, (ModbusEnum) 17
        },

        {
            modbus.Torque_low, (ModbusEnum) 18
        },

        {
            modbus.Torque_high, (ModbusEnum) 19
        },

        {
            modbus.error_count, (ModbusEnum) 20
        },

        {
            modbus.warning_count, (ModbusEnum) 21
        },

        {
            modbus.Motor_temperature1, (ModbusEnum) 22
        },

        {
            modbus.Motor_temperature2, (ModbusEnum) 23
        },

        {
            modbus.Inverter_temperature, (ModbusEnum) 24
        },

        {
            modbus.Heat_Sink_temperature, (ModbusEnum) 25
        },

        {
            modbus.Search_coil_A2, (ModbusEnum) 26
        },

        {
            modbus.Search_coil_C2, (ModbusEnum) 27
        },

        {
            modbus.Search_coil_B2, (ModbusEnum) 28
        },

        {
            modbus.Search_coil_A1, (ModbusEnum) 29
        },

        {
            modbus.Search_coil_C1, (ModbusEnum) 30
        },

        {
            modbus.Search_coil_B1, (ModbusEnum) 31
        },

        {
            modbus.dig_inputs, (ModbusEnum) 32
        },

        {
            modbus.power_in, (ModbusEnum) 33
        },

        {
            modbus.power_out, (ModbusEnum) 34
        },

        {
            modbus.is_setup, (ModbusEnum) 60
        },

        {
            modbus.is_identify, (ModbusEnum) 61
        },

        {
            modbus.operating_mode, (ModbusEnum) 62
        },

        {
            modbus.motor_speed_ctl, (ModbusEnum) 63
        },

        {
            modbus.runStop_ctl, (ModbusEnum) 64
        },

        {
            modbus.motor_startup, (ModbusEnum) 65
        },

        {
            modbus.motor_direction_initial, (ModbusEnum) 66
        },

        {
            modbus.motor_direction_allow, (ModbusEnum) 67
        },

        {
            modbus.acceleration_time, (ModbusEnum) 68
        },

        {
            modbus.deceleration_time, (ModbusEnum) 69
        },

        {
            modbus.s_curve_time, (ModbusEnum) 70
        },

        {
            modbus.starting_torque_low, (ModbusEnum) 71
        },

        {
            modbus.starting_torque_high, (ModbusEnum) 72
        },

        {
            modbus.min_rpm_low, (ModbusEnum) 73
        },

        {
            modbus.min_rpm_high, (ModbusEnum) 74
        },

        {
            modbus.max_rpm_low, (ModbusEnum) 75
        },

        {
            modbus.max_rpm_high, (ModbusEnum) 76
        },

        {
            modbus.brake_mode, (ModbusEnum) 77
        },

        {
            modbus.restart_mode, (ModbusEnum) 78
        },

        {
            modbus.restart_attempts, (ModbusEnum) 79
        },

        {
            modbus.restart_interval, (ModbusEnum) 80
        },

        {
            modbus.restart_alert, (ModbusEnum) 81
        },

        {
            modbus.skip_speed_1_low, (ModbusEnum) 82
        },

        {
            modbus.skip_speed_1_high, (ModbusEnum) 83
        },

        {
            modbus.skip_speed_2_low, (ModbusEnum) 84
        },

        {
            modbus.skip_speed_2_high, (ModbusEnum) 85
        },

        {
            modbus.skip_speed_3_low, (ModbusEnum) 86
        },

        {
            modbus.skip_speed_3_high, (ModbusEnum) 87
        },

        {
            modbus.skip_width, (ModbusEnum) 88
        },

        {
            modbus.overload_mode, (ModbusEnum) 89
        },

        {
            modbus.overload_tol, (ModbusEnum) 90
        },

        {
            modbus.overspeed_mode, (ModbusEnum) 91
        },

        {
            modbus.overspeed_tol, (ModbusEnum) 92
        },

        {
            modbus.speeddev_mode, (ModbusEnum) 93
        },

        {
            modbus.speeddev_tol, (ModbusEnum) 94
        },

        {
            modbus.error_response, (ModbusEnum) 95
        },

        {
            modbus.warn_response, (ModbusEnum) 96
        },

        {
            modbus.warn_timeout, (ModbusEnum) 97
        },

        {
            modbus.di_mode_1, (ModbusEnum) 98
        },

        {
            modbus.di_mode_2, (ModbusEnum) 99
        },

        {
            modbus.di_mode_3, (ModbusEnum) 100
        },

        {
            modbus.di_mode_4, (ModbusEnum) 101
        },

        {
            modbus.di_mode_5, (ModbusEnum) 102
        },

        {
            modbus.di_mode_6, (ModbusEnum) 103
        },

        {
            modbus.di_mode_7, (ModbusEnum) 104
        },

        {
            modbus.di_mode_8, (ModbusEnum) 105
        },

        {
            modbus.di_mode_9, (ModbusEnum) 106
        },

        {
            modbus.do_mode_1, (ModbusEnum) 107
        },

        {
            modbus.tap_speed_1_low, (ModbusEnum) 108
        },

        {
            modbus.tap_speed_1_high, (ModbusEnum) 109
        },

        {
            modbus.tap_speed_2_low, (ModbusEnum) 110
        },

        {
            modbus.tap_speed_2_high, (ModbusEnum) 111
        },

        {
            modbus.tap_speed_3_low, (ModbusEnum) 112
        },

        {
            modbus.tap_speed_3_high, (ModbusEnum) 113
        },

        {
            modbus.tap_speed_4_low, (ModbusEnum) 114
        },

        {
            modbus.tap_speed_4_high, (ModbusEnum) 115
        },

        {
            modbus.tap_speed_5_low, (ModbusEnum) 116
        },

        {
            modbus.tap_speed_5_high, (ModbusEnum) 117
        },

        {
            modbus.fan_mode, (ModbusEnum) 118
        },

        {
            modbus.is_maintenance, (ModbusEnum) 130
        },

        {
            modbus.motor_state_maintenance, (ModbusEnum) 131
        },

        {
            modbus.requested_rpm_maint_low, (ModbusEnum) 132
        },

        {
            modbus.requested_rpm_maint_high, (ModbusEnum) 133
        },

        {
            modbus.motor_direction_mt, (ModbusEnum) 134
        },

        {
            modbus.jog_maint_mode, (ModbusEnum) 135
        },

        {
            modbus.jog_maint_rpm_low, (ModbusEnum) 136
        },

        {
            modbus.jog_maint_rpm_high, (ModbusEnum) 137
        },

        {
            modbus.di_simulate, (ModbusEnum) 138
        },

        {
            modbus.di_values, (ModbusEnum) 139
        },

        {
            modbus.ai_simulate, (ModbusEnum) 140
        },

        {
            modbus.ai_value_1_r1, (ModbusEnum) 250
        },

        {
            modbus.ai_value_1_r2, (ModbusEnum) 251
        },

        {
            modbus.ai_value_1_v1, (ModbusEnum) 252
        },

        {
            modbus.ai_value_1_v2, (ModbusEnum) 253
        },

        {
            modbus.ai_value_1_c1, (ModbusEnum) 254
        },

        {
            modbus.ai_value_1_a2, (ModbusEnum) 255
        },

        {
            modbus.ai_value_2_r1, (ModbusEnum) 256
        },

        {
            modbus.ai_value_2_r2, (ModbusEnum) 257
        },

        {
            modbus.ai_value_2_v1, (ModbusEnum) 258
        },

        {
            modbus.ai_value_2_v2, (ModbusEnum) 259
        },

        {
            modbus.ai_value_2_c1, (ModbusEnum) 260
        },

        {
            modbus.ai_value_2_a2, (ModbusEnum) 261
        },

        {
            modbus.ai_value_3_r1, (ModbusEnum) 262
        },

        {
            modbus.ai_value_3_r2, (ModbusEnum) 263
        },

        {
            modbus.ai_value_3_v1, (ModbusEnum) 264
        },

        {
            modbus.ai_value_3_v2, (ModbusEnum) 265
        },

        {
            modbus.ai_value_3_c1, (ModbusEnum) 266
        },

        {
            modbus.ai_value_3_a2, (ModbusEnum) 267
        },

        {
            modbus.ai_value_4_r1, (ModbusEnum) 268
        },

        {
            modbus.ai_value_4_r2, (ModbusEnum) 269
        },

        {
            modbus.ai_value_4_v1, (ModbusEnum) 270
        },

        {
            modbus.ai_value_4_v2, (ModbusEnum) 271
        },

        {
            modbus.ai_value_4_c1, (ModbusEnum) 272
        },

        {
            modbus.ai_value_4_a2, (ModbusEnum) 273
        },

        {
            modbus.ao_value_1_v1, (ModbusEnum) 340
        },

        {
            modbus.ao_value_1_v2, (ModbusEnum) 341
        },

        {
            modbus.is_alert2, (ModbusEnum) 150
        },

        {
            modbus.error_count2, (ModbusEnum) 151
        },

        {
            modbus.warning_count2, (ModbusEnum) 152
        },

        {
            modbus.err_word_1, (ModbusEnum) 153
        },

        {
            modbus.err_word_2, (ModbusEnum) 154
        },

        {
            modbus.err_word_3, (ModbusEnum) 155
        },

        {
            modbus.warn_word_1, (ModbusEnum) 156
        },

        {
            modbus.warn_word_2, (ModbusEnum) 157
        },

        {
            modbus.warn_word_3, (ModbusEnum) 158
        },

        {
            modbus.uptime_hours, (ModbusEnum) 159
        },

        {
            modbus.is_online, (ModbusEnum) 185
        },

        {
            modbus.motor_id_0, (ModbusEnum) 186
        },

        {
            modbus.motor_id_1, (ModbusEnum) 187
        },

        {
            modbus.motor_id_2, (ModbusEnum) 188
        },

        {
            modbus.motor_id_3, (ModbusEnum) 189
        },

        {
            modbus.motor_id_4, (ModbusEnum) 190
        },

        {
            modbus.motor_id_5, (ModbusEnum) 191
        },

        {
            modbus.flash_write_control, (ModbusEnum) 192
        },

        {
            modbus.flash_update_control, (ModbusEnum) 193
        },

        {
            modbus.logic_update_control, (ModbusEnum) 195
        },

        {
            modbus.reset_alerts, (ModbusEnum) 196
        },

        {
            modbus.mfg_mode_setting, (ModbusEnum) 197
        },

        {
            modbus.mfg_mode_dig_out, (ModbusEnum) 198
        },

        {
            modbus.board_type, (ModbusEnum) 199
        },

        {
            modbus.induct_profile_idx, (ModbusEnum) 200
        },

        {
            modbus.tune_cmd, (ModbusEnum) 201
        },

        {
            modbus.tune_data, (ModbusEnum) 202
        },

        {
            modbus.inductance, (ModbusEnum) 203
        },

        {
            modbus.inductance_ready, (ModbusEnum) 204
        },

        {
            modbus.firing_angle, (ModbusEnum) 205
        },

        {
            modbus.eeprom_write_cmd, (ModbusEnum) 599
        },

        {
            modbus.inverter_eeprom_base, (ModbusEnum) 600
        },

        {
            modbus.pcb_fw_version, (ModbusEnum) 799
        },

        {
            modbus.motor_eeprom_base, (ModbusEnum) 800
        },

        {
            modbus.read_induct_prf, (ModbusEnum) 1200
        },

        {
            modbus.settings_default_start, (ModbusEnum) 1400
        },

        {
            modbus.settings_default_end, (ModbusEnum) 1477
        },

        {
            modbus.debug_value_0, (ModbusEnum) 4000
        },

        {
            modbus.debug_value_89, (ModbusEnum) 4089
        },

        {
            modbus.index_pulses_start, (ModbusEnum) 550
        },

        {
            modbus.index_pulses_end, (ModbusEnum) 589
        },

        {
            modbus.reset_CPUs, (ModbusEnum) 600
        },

        {
            modbus.modbus_config, (ModbusEnum) 595
        },

        {
            modbus.logic_header_start, (ModbusEnum) 1800
        },

        {
            modbus.logic_header_end, (ModbusEnum) 1923
        },

        {
            modbus.logic_av_start, (ModbusEnum) 2000
        },

        {
            modbus.logic_av_end, (ModbusEnum) 2139
        },

        {
            modbus.logic_bv_start, (ModbusEnum) 2500
        },

        {
            modbus.logic_bv_end, (ModbusEnum) 2515
        },
    };
    private Dictionary<settingsSend, SettingsSendEnum> settingsSendValues = new Dictionary<settingsSend, SettingsSendEnum>() {
        {
            settingsSend.updated1, (SettingsSendEnum) 0
        },                     // Indicates user data changed : bit indicates the respective variable

        {
            settingsSend.updated2, (SettingsSendEnum) 2
        },                     // Indicates user data changed : bit indicates the respective variable

        {
            settingsSend.updated3, (SettingsSendEnum) 4
        },                     // Indicates user data changed : bit indicates the respective variable

        {
            settingsSend.updated4, (SettingsSendEnum) 6
        },                     // Indicates user data changed : bit indicates the respective variable

        {
            settingsSend.updated5, (SettingsSendEnum) 8
        },                     // Indicates user data changed : bit indicates the respective variable

        {
            settingsSend.updated6, (SettingsSendEnum) 10
        },                    // Indicates user data changed : bit indicates the respective variable

        {
            settingsSend.is_setup, (SettingsSendEnum) 12
        },                    // If true, (SettingsSendEnum)indicates the device has been setup.

        {
            settingsSend.is_identify, (SettingsSendEnum) 14
        },                 // If true, turns on the LED indicator to allow the user to identify the motor.

        {
            settingsSend.operating_mode, (SettingsSendEnum) 16
        },              // Operating mode (0=normal [default], 1=quiet)

        {
            settingsSend.motor_speed_ctl, (SettingsSendEnum) 18
        },             // How the speed is regulated (0, 0-10v [default], 1, modbus, 2, 0-20mA, 3, 4-20mA)

        {
            settingsSend.runStop_ctl, (SettingsSendEnum) 20
        },                 // How stops and starts are regulated (0, digital input [default], 1, modbus, 2, digital input && modbus (i.e. both must be active))

        {
            settingsSend.motor_startup, (SettingsSendEnum) 22
        },               // How to startup when the motor is turned on (0=immediate, 1=on command active [default], 2, on command cycled, i.e. detected transition from off to on)

        {
            settingsSend.motor_direction_initial, (SettingsSendEnum) 24
        },     // Defines what direction is "forwards" for the motor looking from the drive end (i.e. with the shaft furthest from you). (0, Clockwise [default], 1, Anti-Clockwise)

        {
            settingsSend.motor_direction_allow, (SettingsSendEnum) 26
        },       // Which directions are allowed (0, forward and reverse [default], 1=forward only)

        {
            settingsSend.acceleration_time, (SettingsSendEnum) 28
        },           // Time to accelerate from stop to full speed, or from current speed to a new speed. (0 [default], as fast as permitted by the hardware design)

        {
            settingsSend.deceleration_time, (SettingsSendEnum) 30
        },           // Time to decelerate to stop from full speed, or from current speed to a new speed. (0 [default], as fast as permitted by the hardware design)

        {
            settingsSend.s_curve_time, (SettingsSendEnum) 32
        },                // Time added to Acceleration_time/Deceleration_time to create a smoother ramp (1/2 at start of ramp, 1/2 at end). (0 [default])

        {
            settingsSend.empty_address_1, (SettingsSendEnum) 34
        },

        {
            settingsSend.starting_torque, (SettingsSendEnum) 36
        },             // Maximum torque when starting (Nm) (0 [default], as high as permitted by the hardware design)

        {
            settingsSend.min_rpm, (SettingsSendEnum) 40
        },                     // The minimum speed in RPM (if running) [0 [default], motor nameplate minimum speed]

        {
            settingsSend.max_rpm, (SettingsSendEnum) 44
        },                     // The maximum speed in RPM (if running) [0 [default], motor nameplate maximum speed]

        {
            settingsSend.brake_mode, (SettingsSendEnum) 48
        },                  // Whether the motor uses active braking for deceleration (0=coast [default], 1=active ramp)

        {
            settingsSend.is_maintenance, (SettingsSendEnum) 50
        },              // If true, indicates the device is in maintenance mode. In maintenance mode, the device is manually controlled (ignores other input signals) [Default false]

        {
            settingsSend.motor_State_maintenance, (SettingsSendEnum) 52
        },     // Whether the motor is running (1=running, 2=stopped [default]) (while in maintenance mode only)

        {
            settingsSend.empty_address_2, (SettingsSendEnum) 54
        },

        {
            settingsSend.requested_rpm_ma, (SettingsSendEnum) 56
        },         // Requested speed (RPM) [default 0] (while in maintenance mode only)

        {
            settingsSend.motor_direction_mt, (SettingsSendEnum) 60
        },          // Direction Forward(0) [default] or Reverse(1) (while in maintenance mode only)

        {
            settingsSend.jog_maint_mode, (SettingsSendEnum) 62
        },              // Whether the jog is active, (SettingsSendEnum)which moves the motor forward slowly at the jog speed (1=running, (SettingsSendEnum)2=stopped [default]). Jog only functions while in maintenance mode, (SettingsSendEnum)when the motor_state_maintenance is stopped (2).

        {
            settingsSend.jog_maint_rpm, (SettingsSendEnum) 64
        },               // Requested speed (RPM) of the jog speed [default 300RPM] (while in maintenance mode only)

        {
            settingsSend.restart_mode, (SettingsSendEnum) 68
        },                // Restart mode (0=count restart attempts [default], (SettingsSendEnum)1=count successful restarts)

        {
            settingsSend.restart_attempts, (SettingsSendEnum) 70
        },            // Restart attempts: the number of attempts to restart the motor after a power failure [default 2]

        {
            settingsSend.restart_interval, (SettingsSendEnum) 72
        },            // Restart interval, (SettingsSendEnum)the number of seconds between restart attempts [default 10].

        {
            settingsSend.restart_alert, (SettingsSendEnum) 74
        },               // Restart alert (0=no alarm on restart [default], (SettingsSendEnum)1=alarm while restarting)

        {
            settingsSend.skip_speed_1, (SettingsSendEnum) 76
        },                // The first prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, (SettingsSendEnum)but accelerates and decelerates the motor through the prohibited bandwidth.

        {
            settingsSend.skip_speed_2, (SettingsSendEnum) 80
        },                // The second prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, (SettingsSendEnum)but accelerates and decelerates the motor through the prohibited bandwidth.

        {
            settingsSend.skip_speed_3, (SettingsSendEnum) 84
        },                // The third prohibited speed reference points for eliminating problems with resonant vibration of the motor / machine. This feature does not eliminate the selected speed values, (SettingsSendEnum)but accelerates and decelerates the motor through the prohibited bandwidth.

        {
            settingsSend.skip_width, (SettingsSendEnum) 88
        },                  // The dead-band interval around each selected skip speed reference point. Speeds between the skip speed - 0.5 * dead band and skip speed + 0.5 * dead band will be prohibited. (Default 60 RPM.)

        {
            settingsSend.overload_mode, (SettingsSendEnum) 90
        },               // How to respond to current overload on the motor. 0= Limit to rated, (SettingsSendEnum)no overload: Don't deliver more than the rated current to the motor. 1, (SettingsSendEnum)Shutdown at overload: Deliver up to the overload tolerance of the motor, (SettingsSendEnum)shutdown above this. 2, (SettingsSendEnum)Shutdown at safety limit: Deliver up to the overload tolerance of the motor, (SettingsSendEnum)shutdown at thermal safety limit.

        {
            settingsSend.overload_tol, (SettingsSendEnum) 92
        },                // How far above the rated current the motor will run, (SettingsSendEnum)as a percentage of the nameplate current rating (default=15, (SettingsSendEnum)15% above the rated current).

        {
            settingsSend.overspeed_mode, (SettingsSendEnum) 94
        },              // How to respond when the motor runs faster than the motor's user-entered maximum speed. 0= Continue [default], (SettingsSendEnum)1=Stop

        {
            settingsSend.overspeed_tol, (SettingsSendEnum) 96
        },               // How far above the rated speed the motor will run, (SettingsSendEnum)as a percentage of the user-entered maximum speed.(default=15).

        {
            settingsSend.speeddev_mode, (SettingsSendEnum) 98
        },               // How to respond when the speed commanded is significantly different from the observed speed. 0= Continue [default], (SettingsSendEnum)1=Stop

        {
            settingsSend.speeddev_tol, (SettingsSendEnum) 100
        },                // How far above or below the commanded speed is permitted before an alarm is raised (RPM), (SettingsSendEnum)default, (SettingsSendEnum)60.

        {
            settingsSend.error_response, (SettingsSendEnum) 102
        },              // The action taken when a critical fault is reported. 0, Coast to stop [default]: stop the motor by turning off input power. 2, (SettingsSendEnum)Ramp to stop: actively brake the motor to a stop.

        {
            settingsSend.warn_response, (SettingsSendEnum) 104
        },              // The action taken when a warning fault is reported. 0, (SettingsSendEnum)Continue with alarm: generate an alert, (SettingsSendEnum)but continue running [default]. 1, (SettingsSendEnum)Coast to stop: stop the motor by turning off input power.2, (SettingsSendEnum)Ramp to stop: actively brake the motor to a stop.

        {
            settingsSend.warn_timeout, (SettingsSendEnum) 106
        },              // Seconds until warnings timeout

        {
            settingsSend.di_mode_1, (SettingsSendEnum) 108
        },                  // Defines the action taken when digital input 1 is open or closed. 0=Disable, 1=Run [default], 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3

        {
            settingsSend.di_mode_2, (SettingsSendEnum) 110
        },                  // Defines the action taken when digital input 2 is open or closed. 0=Disable, 1=Run, 2=Reverse [default], 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3

        {
            settingsSend.di_mode_3, (SettingsSendEnum) 112
        },                  // Defines the action taken when digital input 3 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop [default], 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3

        {
            settingsSend.di_mode_4, (SettingsSendEnum) 114
        },                  // Defines the action taken when digital input 4 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault [default], 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3

        {
            settingsSend.di_mode_5, (SettingsSendEnum) 116
        },                  // Defines the action taken when digital input 5 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset [default], 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3

        {
            settingsSend.di_mode_6, (SettingsSendEnum) 118
        },                  // Defines the action taken when digital input 6 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2, 9=Speed tap setting 3

        {
            settingsSend.di_mode_7, (SettingsSendEnum) 120
        },                  // Defines the action taken when digital input 7 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1 [default], 8=Speed tap setting 2, 9=Speed tap setting 3

        {
            settingsSend.di_mode_8, (SettingsSendEnum) 122
        },                  // Defines the action taken when digital input 8 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2 [default], 9=Speed tap setting 3

        {
            settingsSend.di_mode_9, (SettingsSendEnum) 124
        },                  // Defines the action taken when digital input 9 is open or closed. 0=Disable, 1=Run, 2=Reverse, 3=Safety Stop, 4=External Fault, 5=Fault Reset, 6=Reset to factory defaults, 7=Speed tap setting 1, 8=Speed tap setting 2 [default], 9=Speed tap setting 3

        {
            settingsSend.do_mode_1, (SettingsSendEnum) 126
        },                  // Defines the purpose of the digital out. 0=Disable, 1=Active, 2=Running, 3=Fault [default], 4=Alert (either Warning or Fault)

        {
            settingsSend.tap_speed_1, (SettingsSendEnum) 128
        },                // If a digital input is set to "Speed tap setting 1", defines the speed that the motor will run at.

        {
            settingsSend.tap_speed_2, (SettingsSendEnum) 132
        },                // If a digital input is set to "Speed tap setting 2", defines the speed that the motor will run at.

        {
            settingsSend.tap_speed_3, (SettingsSendEnum) 136
        },                // If a digital input is set to "Speed tap setting 3", defines the speed that the motor will run at.

        {
            settingsSend.tap_speed_4, (SettingsSendEnum) 142
        },                // If a digital input is set to "Speed tap setting 4", defines the speed that the motor will run at.

        {
            settingsSend.tap_speed_5, (SettingsSendEnum) 146
        },                // If a digital input is set to "Speed tap setting 5", defines the speed that the motor will run at.

        {
            settingsSend.di_simulate, (SettingsSendEnum) 150
        },                // Whether digital input is simulated: 0, use real input [default], 1, use simulated value.  One bit for each input.

        {
            settingsSend.di_value, (SettingsSendEnum) 152
        },                   // If digital input is simulated, whether that value is open (0) or closed (1).  One bit for each input

    };

    private Dictionary<settingsReceive, SettingsReceiveEnum> settingsReceiveValues = new Dictionary<settingsReceive, SettingsReceiveEnum>() {
        {
            settingsReceive.is_setup, (SettingsReceiveEnum) 4
        },

        {
            settingsReceive.is_identify, (SettingsReceiveEnum) 6
        },

        {
            settingsReceive.op_mode, (SettingsReceiveEnum) 8
        },

        {
            settingsReceive.ctrl_mode, (SettingsReceiveEnum) 10
        },

        {
            settingsReceive.runstop_mode, (SettingsReceiveEnum) 12
        },

        {
            settingsReceive.startup_mode, (SettingsReceiveEnum) 14
        },

        {
            settingsReceive.rotational_direction, (SettingsReceiveEnum) 16
        },

        {
            settingsReceive.directions_allowed, (SettingsReceiveEnum) 18
        },

        {
            settingsReceive.acceleration_time, (SettingsReceiveEnum) 20
        },

        {
            settingsReceive.deceleration_time, (SettingsReceiveEnum) 22
        },

        {
            settingsReceive.s_curve_time, (SettingsReceiveEnum) 24
        },

        {
            settingsReceive.brake_mode, (SettingsReceiveEnum) 26
        },

        {
            settingsReceive.starting_torque, (SettingsReceiveEnum) 28
        },

        {
            settingsReceive.min_speed, (SettingsReceiveEnum) 32
        },

        {
            settingsReceive.max_speed, (SettingsReceiveEnum) 36
        },

        {
            settingsReceive.is_maintenance, (SettingsReceiveEnum) 40
        },

        {
            settingsReceive.is_running_mt, (SettingsReceiveEnum) 42
        },

        {
            settingsReceive.speed_mt, (SettingsReceiveEnum) 44
        },

        {
            settingsReceive.direction_mt, (SettingsReceiveEnum) 48
        },

        {
            settingsReceive.restart_mode, (SettingsReceiveEnum) 50
        },

        {
            settingsReceive.restart_attempts, (SettingsReceiveEnum) 52
        },

        {
            settingsReceive.restart_interval, (SettingsReceiveEnum) 54
        },

        {
            settingsReceive.restart_alert, (SettingsReceiveEnum) 56
        },

        {
            settingsReceive.skip_width, (SettingsReceiveEnum) 58
        },

        {
            settingsReceive.skip_speed_1, (SettingsReceiveEnum) 60
        },

        {
            settingsReceive.skip_speed_2, (SettingsReceiveEnum) 64
        },

        {
            settingsReceive.skip_speed_3, (SettingsReceiveEnum) 68
        },

        {
            settingsReceive.tap_speed_1, (SettingsReceiveEnum) 72
        },

        {
            settingsReceive.tap_speed_2, (SettingsReceiveEnum) 76
        },

        {
            settingsReceive.tap_speed_3, (SettingsReceiveEnum) 80
        },

        {
            settingsReceive.tap_speed_4, (SettingsReceiveEnum) 84
        },

        {
            settingsReceive.tap_speed_5, (SettingsReceiveEnum) 88
        },

        {
            settingsReceive.overload_mode, (SettingsReceiveEnum) 92
        },

        {
            settingsReceive.overload_tol, (SettingsReceiveEnum) 94
        },

        {
            settingsReceive.overspeed_mode, (SettingsReceiveEnum) 96
        },

        {
            settingsReceive.overspeed_tol, (SettingsReceiveEnum) 98
        },

        {
            settingsReceive.speeddev_mode, (SettingsReceiveEnum) 100
        },

        {
            settingsReceive.speeddev_tol, (SettingsReceiveEnum) 102
        },

        {
            settingsReceive.error_resp, (SettingsReceiveEnum) 104
        },

        {
            settingsReceive.warn_resp, (SettingsReceiveEnum) 106
        },

        {
            settingsReceive.warn_timeout, (SettingsReceiveEnum) 108
        },

        {
            settingsReceive.di_mode_1, (SettingsReceiveEnum) 110
        },

        {
            settingsReceive.di_mode_2, (SettingsReceiveEnum) 112
        },

        {
            settingsReceive.di_mode_3, (SettingsReceiveEnum) 114
        },

        {
            settingsReceive.di_mode_4, (SettingsReceiveEnum) 116
        },

        {
            settingsReceive.di_mode_5, (SettingsReceiveEnum) 118
        },

        {
            settingsReceive.di_mode_6, (SettingsReceiveEnum) 120
        },

        {
            settingsReceive.di_mode_7, (SettingsReceiveEnum) 122
        },

        {
            settingsReceive.di_mode_8, (SettingsReceiveEnum) 124
        },

        {
            settingsReceive.di_mode_9, (SettingsReceiveEnum) 126
        },

        {
            settingsReceive.do_mode_1, (SettingsReceiveEnum) 128
        },

        {
            settingsReceive.modbus_config, (SettingsReceiveEnum) 130
        },

        {
            settingsReceive.modbus_address, (SettingsReceiveEnum) 132
        },

        {
            settingsReceive.ai_value_1, (SettingsReceiveEnum) 134
        },

        {
            settingsReceive.ai_value_2, (SettingsReceiveEnum) 136
        },

        {
            settingsReceive.ai_value_3, (SettingsReceiveEnum) 138
        },

        {
            settingsReceive.ai_value_4, (SettingsReceiveEnum) 140
        },

        {
            settingsReceive.ao_value_1, (SettingsReceiveEnum) 142
        },

        {
            settingsReceive.ai_simulate, (SettingsReceiveEnum) 144
        },

        {
            settingsReceive.di_simulate, (SettingsReceiveEnum) 146
        },

        {
            settingsReceive.di_value_set, (SettingsReceiveEnum) 148
        },               // If digital input is simulated, whether that value is open (0) or closed (1).  One bit for each input

    };

    private Dictionary<dataReceive, DataReceiveEnum> dataReceiveValues = new Dictionary<dataReceive, DataReceiveEnum>() {
        {
            dataReceive.dc_bus_current, (DataReceiveEnum) 2
        },

        {
            dataReceive.dc_bus_voltage, (DataReceiveEnum) 4
        },

        {
            dataReceive.reference_current, (DataReceiveEnum) 6
        },

        {
            dataReceive.phase_a_current, (DataReceiveEnum) 8
        },

        {
            dataReceive.phase_b_current, (DataReceiveEnum) 10
        },

        {
            dataReceive.phase_c_current, (DataReceiveEnum) 12
        },

        {
            dataReceive.is_active, (DataReceiveEnum) 14
        },

        {
            dataReceive.is_alert, (DataReceiveEnum) 16
        },

        {
            dataReceive.is_maintenance, (DataReceiveEnum) 18
        },

        {
            dataReceive.is_setup, (DataReceiveEnum) 20
        },            //No corresponding modbus address

        {
            dataReceive.is_identify, (DataReceiveEnum) 22
        },         //No corresponding modbus address

        {
            dataReceive.motor_state, (DataReceiveEnum) 24
        },

        {
            dataReceive.requested_speed, (DataReceiveEnum) 26
        },

        {
            dataReceive.speed, (DataReceiveEnum) 30
        },

        {
            dataReceive.direction, (DataReceiveEnum) 34
        },

        {
            dataReceive.torque, (DataReceiveEnum) 36
        },

        {
            dataReceive.fault_count, (DataReceiveEnum) 40
        },

        {
            dataReceive.warning_count, (DataReceiveEnum) 42
        },

        {
            dataReceive.winding_temp_1, (DataReceiveEnum) 44
        },

        {
            dataReceive.winding_temp_2, (DataReceiveEnum) 46
        },

        {
            dataReceive.enclosure_temp, (DataReceiveEnum) 48
        },

        {
            dataReceive.heatsink_temp, (DataReceiveEnum) 50
        },

        {
            dataReceive.search_coil_sa2, (DataReceiveEnum) 52
        },

        {
            dataReceive.search_coil_sc2, (DataReceiveEnum) 54
        },

        {
            dataReceive.search_coil_sb2, (DataReceiveEnum) 56
        },

        {
            dataReceive.search_coil_sa1, (DataReceiveEnum) 58
        },

        {
            dataReceive.search_coil_sc1, (DataReceiveEnum) 60
        },

        {
            dataReceive.search_coil_sb1, (DataReceiveEnum) 62
        },

        {
            dataReceive.di_value, (DataReceiveEnum) 64
        },

        {
            dataReceive.power_in, (DataReceiveEnum) 66
        },

        {
            dataReceive.power_out, (DataReceiveEnum) 68
        }
    };
    private Dictionary<alertReceive, AlertReceiveEnum> alertReceiveValues = new Dictionary<alertReceive, AlertReceiveEnum>() {
        {
            alertReceive.is_alert, (AlertReceiveEnum) 0
        },

        {
            alertReceive.fault_count, (AlertReceiveEnum) 2
        },

        {
            alertReceive.warning_count, (AlertReceiveEnum) 4
        },

        {
            alertReceive.faults_start, (AlertReceiveEnum) 6
        },

        {
            alertReceive.faults_length, (AlertReceiveEnum) 6
        },

        {
            alertReceive.warnings_start, (AlertReceiveEnum) 12
        },

        {
            alertReceive.warning_length, (AlertReceiveEnum) 6
        }
    };
    private Dictionary<faults, FaultsEnum> faultsValues = new Dictionary<faults, FaultsEnum>() {
        {
            faults.fault_current_overload, (FaultsEnum) 0
        },

        {
            faults.fault_power_loss, (FaultsEnum) 1
        },

        {
            faults.fault_overspeed, (FaultsEnum) 2
        },

        {
            faults.fault_motor_temp, (FaultsEnum) 3
        },

        {
            faults.fault_heatsink_temp, (FaultsEnum) 4
        },

        {
            faults.fault_enclosure_temp, (FaultsEnum) 5
        },

        {
            faults.fault_bus_current, (FaultsEnum) 6
        },

        {
            faults.fault_bus_voltage, (FaultsEnum) 7
        },

        {
            faults.fault_external_fault, (FaultsEnum) 8
        },

        {
            faults.fault_no_motor, (FaultsEnum) 9
        },

        {
            faults.fault_communication, (FaultsEnum) 10
        },

        {
            faults.fault_eeprom, (FaultsEnum) 11
        },

        {
            faults.fault_software, (FaultsEnum) 12
        },

        {
            faults.fault_incompatible, (FaultsEnum) 13
        },

        {
            faults.fault_restarts, (FaultsEnum) 14
        }
    };
    private Dictionary<warnings, WarningsEnum> warningsValues = new Dictionary<warnings, WarningsEnum>() {
        {
            warnings.warn_current_overload, (WarningsEnum) 0
        },

        {
            warnings.warn_phase, (WarningsEnum) 1
        },

        {
            warnings.warn_phase_loss, (WarningsEnum) 2
        },

        {
            warnings.warn_overspeed, (WarningsEnum) 3
        },

        {
            warnings.warn_speed_dev, (WarningsEnum) 4
        },

        {
            warnings.warn_motor_temp, (WarningsEnum) 5
        },

        {
            warnings.warn_heatsink_temp, (WarningsEnum) 6
        },

        {
            warnings.warn_enclosure_temp, (WarningsEnum) 7
        },

        {
            warnings.warn_bus_current, (WarningsEnum) 8
        },

        {
            warnings.warn_bus_voltage, (WarningsEnum) 9
        },

        {
            warnings.warn_communication, (WarningsEnum) 10
        },

        {
            warnings.warn_firmware, (WarningsEnum) 11
        }
    };

}*/
