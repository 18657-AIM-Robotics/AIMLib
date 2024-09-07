package com.aimrobotics.aimlib.OpModes.simpleHardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * DcMotorTest class is a simple teleop program to test a DcMotor
 *
 * @Author Nate Schmelkin
 */

@TeleOp(name="DcMotorTest", group="Samples")

public class DcMotorTest extends OpMode {

    private DcMotorEx motor;
    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > 0) {
            motor.setPower(gamepad1.right_trigger);
        } else if (gamepad1.left_trigger > 0) {
            motor.setPower(-gamepad1.left_trigger);
        }

        if (gamepad1.dpad_up) {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
        } else if (gamepad1.dpad_down) {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }
}
