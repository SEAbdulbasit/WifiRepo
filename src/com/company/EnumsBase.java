package com.company;

public abstract class EnumsBase {
    public abstract Integer ValueOf(modbus key);

    public abstract Integer ValueOf(settingsSend key);

    public abstract Integer ValueOf(settingsReceive key);

    public abstract Integer ValueOf(dataReceive key);

    public abstract Integer ValueOf(alertReceive key);

    public abstract Integer ValueOf(faults key);

    public abstract Integer ValueOf(warnings key);
}

