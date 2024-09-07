package com.aimrobotics.aimlib.OpModes.simpleHardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * ServoTest class is a simple teleop program to test a CRServo
 *
 * @Author Nate Schmelkin
 */

@TeleOp(name="CRServoTest", group="Samples")

public class CRServoTest extends OpMode {

    private CRServo servo;
    @Override
    public void init() {
        servo = hardwareMap.get(CRServo.class, "crservo");
        servo.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > 0) {
            servo.setPower(gamepad1.right_trigger);
        } else if (gamepad1.left_trigger > 0) {
            servo.setPower(-gamepad1.left_trigger);
        }

        if (gamepad1.dpad_up) {
            servo.setDirection(DcMotorSimple.Direction.FORWARD);
        } else if (gamepad1.dpad_down) {
            servo.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }
}
