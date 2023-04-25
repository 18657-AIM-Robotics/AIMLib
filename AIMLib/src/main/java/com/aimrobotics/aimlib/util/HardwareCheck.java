package com.aimrobotics.aimlib.util;

import com.qualcomm.robotcore.hardware.HardwareDevice;

/**
 * HardwareCheck is a class that contains methods to analyze hardware
 * @author Nate Schmelkin
 */

public class HardwareCheck {

    /**
     *
     * @param hardwareDevice device to check connection
     * @param deviceName device name
     * @return String is the device is connected
     */

    public String checkHardwareDeviceConnection(HardwareDevice hardwareDevice, String deviceName) {
        if (hardwareDevice.getConnectionInfo() != null) {
            return deviceName + " is connected";
        } else {
            return deviceName + " is not connected";
        }
    }
}
