package com.aimrobotics.aimlib.util;

import androidx.annotation.NonNull;

public class HardwareInterface{
    String deviceName;
    boolean isExpansionHub;
    int port;

    public HardwareInterface(String deviceName, boolean isExpansionHub, int port) {
        this.deviceName = deviceName;
        this.isExpansionHub = isExpansionHub;
        this.port = port;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public boolean isExpansionHub() {
        return isExpansionHub;
    }

    public int getPort() {
        return port;
    }

    @NonNull
    public String toString() {
        return "Device Name: " + deviceName + " is in the control hub: " + !isExpansionHub + ", and is in port: " + port;
    }
}
