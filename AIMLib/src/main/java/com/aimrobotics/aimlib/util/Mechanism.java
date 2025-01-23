package com.aimrobotics.aimlib.util;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Mechanism class is framework used for all mechanisms on robot.
 * Houses methods and variables common to all mechanisms on robot.
 *
 * @author Nate Schmelkin
 * Inspiration from Paul Serbanescu 310 Stuy Fission
 */

public abstract class Mechanism {

    /**
     * Initialize robot hardware. Gets configurations as well as sets up hardware settings
     *
     * @param hwMap references the robot's hardware map
     */

    public abstract void init(HardwareMap hwMap);

    /**
     * Method to handle AIMPad input and relate to corresopnding hardware output
     * Only use with single gampad
     *
     * @param gamepad references AIMPad in slot one
     */

    public void loop(AIMPad gamepad) {}

    /** Method to handle gampad input and relate to corresopnding hardware output
     * Use with multiple gampads
     *
     * @param gamepad1 references AIMPad in slot one
     * @param gamepad2 references AIMPad in slot two
     */

    public void loop(AIMPad gamepad1, AIMPad gamepad2) { }

    /**
     * Method to handle all telemetry for a given mechanism
     *
     * @param telemetry references local telemetry
     */

    public void telemetry(Telemetry telemetry) { }
}