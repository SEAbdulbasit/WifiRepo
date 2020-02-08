package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here

        WiFiViewModel wiFiViewModel = new WiFiViewModel();
        wiFiViewModel.attemptConnect(WiFiViewModel.Mode.WRITE_DATA, "settings");
    }
}
