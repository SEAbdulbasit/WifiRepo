package com.company;

import java.util.Dictionary;
import java.util.HashMap;

class InverterEnumsV2 extends InverterEnumsBase {
   /* public override InverterEEPROMStartEnum

    ValueOf(inverterEEPROMStart key) {
        if (inverterEEPROMStartValues.ContainsKey(key)) {
            return inverterEEPROMStartValues[key];
        } else {
            return  0xFFFF;
        }
    }

    public override InverterEEPROMLengthEnum

    ValueOf(inverterEEPROMLength key) {
        if (inverterEEPROMLengthValues.ContainsKey(key)) {
            return inverterEEPROMLengthValues[key];
        } else {
            return  0xFFFF;
        }
    }*/

    private HashMap<inverterEEPROMStart, Integer> inverterEEPROMStartValues = new HashMap<>() {
        {
            put(inverterEEPROMStart.inverter_checksum, 0);
            put(inverterEEPROMStart.inverter_version, 1);
            put(inverterEEPROMStart.inverter_csum_high, 2);
            put(inverterEEPROMStart.inverter_csum_low, 3);
            put(inverterEEPROMStart.inverter_model, 4);
            put(inverterEEPROMStart.inverter_hardware_version, 16);
            put(inverterEEPROMStart.inverter_mfg_date, 20);
            put(inverterEEPROMStart.inverter_serial, 28);
            put(inverterEEPROMStart.inverter_mfgr_name, 40);
            put(inverterEEPROMStart.power_board_serial, 72);
            put(inverterEEPROMStart.power_board_mfg_name, 84);
            put(inverterEEPROMStart.enclosure_serial, 116);
            put(inverterEEPROMStart.enclosure_mfg_name, 128);
            put(inverterEEPROMStart.inverter_voltage_in_1, 160);
            put(inverterEEPROMStart.inverter_voltage_in_2, 164);
            put(inverterEEPROMStart.inverter_freq_max_in, 170);
            put(inverterEEPROMStart.inverter_current_nom_in_V1_1, 172);
            put(inverterEEPROMStart.inverter_current_nom_in_V1_2, 176);
            put(inverterEEPROMStart.inverter_current_max_out_V1, 180);
            put(inverterEEPROMStart.inverter_crowbar_V1, 184);
            put(inverterEEPROMStart.inverter_current_nom_in_V2_1, 188);
            put(inverterEEPROMStart.inverter_current_max_out_V2, 196);
            put(inverterEEPROMStart.inverter_crowbar_V2, 200);
            put(inverterEEPROMStart.inverter_phase_power_in, 204);
            put(inverterEEPROMStart.inverter_power_rating, 208);
            put(inverterEEPROMStart.inverter_acdc, 214);
            put(inverterEEPROMStart.inverter_corr_fac_1, 216);
            put(inverterEEPROMStart.inverter_corr_fac_2, 220);
            put(inverterEEPROMStart.inverter_int_hp, 224);
            put(inverterEEPROMStart.inverter_adc_offset, 226);
            put(inverterEEPROMStart.inverter_adc_scalar, 228);
            put(inverterEEPROMStart.inverter_dac_offset, 230);
            put(inverterEEPROMStart.inverter_dac_scalar, 232);
        }
    };

    private HashMap<inverterEEPROMLength, Integer> inverterEEPROMLengthValues = new HashMap<>() {
        {
            put(inverterEEPROMLength.inverter_checksum, 1);
            put(inverterEEPROMLength.inverter_version, 1
            );
            put(inverterEEPROMLength.inverter_csum_low, 1);
            put(inverterEEPROMLength.inverter_csum_high, 1);
            put(inverterEEPROMLength.inverter_model, 12);
            put(inverterEEPROMLength.inverter_hardware_version, 4);
            put(inverterEEPROMLength.inverter_mfg_date, 8);
            put(inverterEEPROMLength.inverter_serial, 12);
            put(inverterEEPROMLength.inverter_mfgr_name, 32);
            put(inverterEEPROMLength.power_board_serial, 12);
            put(inverterEEPROMLength.power_board_mfg_name, 32);
            put(inverterEEPROMLength.enclosure_serial, 12);
            put(inverterEEPROMLength.enclosure_mfg_name, 32);
            put(inverterEEPROMLength.inverter_voltage_in_1, 4);
            put(inverterEEPROMLength.inverter_voltage_in_2, 4);
            put(inverterEEPROMLength.inverter_freq_min_in, 2);
            put(inverterEEPROMLength.inverter_freq_max_in, 2);
            put(inverterEEPROMLength.inverter_current_nom_in_V1_1, 4);
            put(inverterEEPROMLength.inverter_current_nom_in_V1_2, 4);
            put(inverterEEPROMLength.inverter_crowbar_V1, 4);
            put(inverterEEPROMLength.inverter_current_nom_in_V2_1, 4);
            put(inverterEEPROMLength.inverter_current_nom_in_V2_2, 4);
            put(inverterEEPROMLength.inverter_current_max_out_V2, 4);
            put(inverterEEPROMLength.inverter_crowbar_V2, 4);
            put(inverterEEPROMLength.inverter_power_rating, 6);
            put(inverterEEPROMLength.inverter_phase_power_in, 4);
            put(inverterEEPROMLength.inverter_acdc, 2);
            put(inverterEEPROMLength.inverter_corr_fac_1, 4);
            put(inverterEEPROMLength.inverter_current_max_out_V1, 4);
            put(inverterEEPROMLength.inverter_corr_fac_2, 4);
            put(inverterEEPROMLength.inverter_int_hp, 2);
            put(inverterEEPROMLength.inverter_adc_offset, 2);
            put(inverterEEPROMLength.inverter_adc_scalar, 2);
            put(inverterEEPROMLength.inverter_dac_offset, 2);
            put(inverterEEPROMLength.inverter_dac_scalar, 2);
        }
    };

    @Override
    public Integer ValueOf(inverterEEPROMStart key) {
        if (inverterEEPROMStartValues.containsKey(key)) {
            return inverterEEPROMStartValues.get(key);
        } else {
            return 0xFFFF;
        }
    }

    @Override
    public Integer ValueOf(inverterEEPROMLength key) {
        if (inverterEEPROMLengthValues.containsKey(key)) {
            return inverterEEPROMLengthValues.get(key);
        } else {
            return 0xFFFF;
        }
    }
}

