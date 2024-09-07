package com.aimrobotics.aimlib.OpModes.mecanum;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * MecanumDriveTests class is a simple teleop program to test a MecanumDrivebase
 *
 * @Author Nate Schmelkin
 */

@TeleOp(name="mecanumDriveTests", group="Samples")

public class MecanumDriveTests extends OpMode {

    MecanumDrivebase mecanumDrivebase = new MecanumDrivebase(0.7, 0.7, 0.5);

    AIMPad gamepad = new AIMPad(new Gamepad());

    @Override
    public void init() {
        mecanumDrivebase.init(hardwareMap);
        gamepad = new AIMPad(gamepad1);
    }


    @Override
    public void init_loop() {
    }


    @Override
    public void start() {
    }


    @Override
    public void loop() {
        gamepad.update(gamepad1);
        mecanumDrivebase.loop(gamepad);
        mecanumDrivebase.telemetry(telemetry);
    }

    @Override
    public void stop() {
    }

}