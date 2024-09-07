package com.aimrobotics.aimlib.OpModes.simpleHardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * ServoTest class is a simple teleop program to test a Servo
 *
 * @Author Nate Schmelkin
 */

@TeleOp(name="ServoTest", group="Samples")

public class ServoTest extends OpMode {

    private Servo servo;
    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > 0) {
            servo.setPosition(Math.min(servo.getPosition() + 0.0025, 1));
        } else if (gamepad1.left_trigger > 0) {
            servo.setPosition(Math.max(servo.getPosition() - 0.0025, 0));
        }
    }
}
