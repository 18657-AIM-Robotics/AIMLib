package com.aimrobotics.aimlib.OpModes.mecanum;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * MecanumDrivebase class is a hardware and method framework for a
 * Mecanum Drivebase to run in Field-Centric, Arcade, or Direction Debugging mode
 *
 * @Author Nate Schmelkin
 */

public class MecanumDrivebase extends Mechanism {

    public DcMotorEx leftFront; // front left motor
    public DcMotorEx rightFront; // front right motor
    public DcMotorEx leftRear; // back left motor
    public DcMotorEx rightRear; // back right motor
    public IMU imu = null; // IMU sensor

    private List<IMU.Parameters> imuParametersList; // List of all possible IMU parameters
    private int currentIndex; // Index of the current IMU parameter

    enum DriveMode {
        ARCADE, // Robo-centric
        FIELD_CENTRIC, // Field-centric
        DIRECTION_DEBUGGING // Debugging mode
    }

    DriveMode activeDriveMode = DriveMode.ARCADE; // Set default drive mode to arcade

    double Y_MULTIPLIER; // Multiplier for forward/backward movement
    double X_MULTIPLIER; // Multiplier for left/right movement
    double RX_MULTIPLIER; // Multiplier for rotation

    DcMotorEx activeDebugMotor; // Active motor for debugging

    MecanumDrivebase(double y_mult, double x_mult, double rx_mult) {
        Y_MULTIPLIER = y_mult;
        X_MULTIPLIER = x_mult;
        RX_MULTIPLIER = rx_mult;
    }

    /**
     * Initialize the mecanum drivebase. Assumed that IMU is oriented Logo Up, USB Forward
     * @param hwMap references the robot's hardware map
     */
    @Override
    public void init(HardwareMap hwMap) {
        // Initialize motors
        leftFront = hwMap.get(DcMotorEx.class, "fld");
        rightFront = hwMap.get(DcMotorEx.class, "frd");
        leftRear = hwMap.get(DcMotorEx.class, "bld");
        rightRear = hwMap.get(DcMotorEx.class, "brd");

        // Initialize IMU
        imu = hwMap.get(IMU.class, "imu");
        imuParametersList = getAllIMUParameters();
        currentIndex = 0;
        IMU.Parameters parameters = imuParametersList.get(currentIndex);
        imu.initialize(parameters);

        // Set motor directions and behaviors
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        activeDebugMotor = leftFront;
        setZeroPowerBehaviors(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    /**
     * Loop method to switch between drive modes
     * @param gamepad references AIMPad in slot one
     */
    @Override
    public void loop(AIMPad gamepad) {
        switch (activeDriveMode) {
            case ARCADE:
                arcadeDrive(gamepad);
                break;
            case FIELD_CENTRIC:
                fieldCentricDrive(gamepad);
                break;
            case DIRECTION_DEBUGGING:
                directionDebugging(gamepad);
                break;
        }
    }

    /**
     * Set the zero power behaviors for all motors in the mecanum drivebase
     * @param behavior the zero power behavior to apply to the motors
     */
    void setZeroPowerBehaviors(DcMotor.ZeroPowerBehavior behavior) {
        leftFront.setZeroPowerBehavior(behavior);
        rightFront.setZeroPowerBehavior(behavior);
        leftRear.setZeroPowerBehavior(behavior);
        rightRear.setZeroPowerBehavior(behavior);
    }

    /**
     * Set the motor powers for the mecanum drive
     * @param lfp left front power
     * @param rfp right front power
     * @param lrp left rear power
     * @param rrp right rear power
     */
    void setMotorPowers(double lfp, double rfp, double lrp, double rrp) {
        leftFront.setPower(lfp);
        rightFront.setPower(rfp);
        leftRear.setPower(lrp);
        rightRear.setPower(rrp);
    }

    /**
     * Arcade drive method for the mecanum drivebase
     * @param gamepad reference for AIMPad input
     */
    void arcadeDrive(AIMPad gamepad) {
        if (gamepad.isLeftBumperPressed() && gamepad.isRightBumperPressed()) {
            activeDriveMode = DriveMode.FIELD_CENTRIC;
        } else if (gamepad.isDPadUpPressed() || gamepad.isDPadRightPressed() || gamepad.isDPadLeftPressed() || gamepad.isDPadDownPressed()) { //TODO change for isAnyDPadPressed
            activeDriveMode = DriveMode.DIRECTION_DEBUGGING;
        }

        double leftFrontPower;
        double leftRearPower;
        double rightFrontPower;
        double rightRearPower;

        double y = -gamepad.getLeftStickY() * Y_MULTIPLIER;
        double x = gamepad.getLeftStickX() * X_MULTIPLIER;
        double rx = gamepad.getRightStickX() * RX_MULTIPLIER;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        leftFrontPower = (y + x + rx) / denominator;
        leftRearPower = (y - x + rx) / denominator;
        rightFrontPower = (y - x - rx) / denominator;
        rightRearPower = (y + x - rx) / denominator;

        setMotorPowers(leftFrontPower, rightFrontPower, leftRearPower, rightRearPower);
    }

    /**
     * Field centric drive method for the mecanum drivebase
     * @param gamepad reference for AIMPad input
     */
    void fieldCentricDrive(AIMPad gamepad) {
        if (gamepad.isLeftBumperPressed() && gamepad.isRightBumperPressed()) {
            activeDriveMode = DriveMode.ARCADE;
        } else if (gamepad.isDPadUpPressed() || gamepad.isDPadRightPressed() || gamepad.isDPadLeftPressed() || gamepad.isDPadDownPressed()) { //TODO change for isAnyDPadPressed
            activeDriveMode = DriveMode.DIRECTION_DEBUGGING;
        }

        double leftFrontPower;
        double leftRearPower;
        double rightFrontPower;
        double rightRearPower;

        double y = -gamepad.getLeftStickY();
        double x = gamepad.getLeftStickX();
        double rx = gamepad.getRightStickX() * RX_MULTIPLIER;
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


        // Rotate the movement direction counter to the bot's rotation
        double rotX = (x * Math.cos(-botHeading) - y * Math.sin(-botHeading)) * X_MULTIPLIER;
        double rotY = (x * Math.sin(-botHeading) + y * Math.cos(-botHeading)) * Y_MULTIPLIER;

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

        leftFrontPower = (rotY + rotX + rx) / denominator;
        leftRearPower = (rotY - rotX + rx) / denominator;
        rightFrontPower = (rotY - rotX - rx) / denominator;
        rightRearPower = (rotY + rotX - rx) / denominator;

        setMotorPowers(leftFrontPower, rightFrontPower, leftRearPower, rightRearPower);
    }

    /**
     * Debugging method to test the direction of the motors
     * @param gamepad reference for AIMPad input
     */
    void directionDebugging(AIMPad gamepad) {

        if (gamepad.isDPadUpPressed()) {
            activeDebugMotor = leftFront;
        } else if (gamepad.isDPadRightPressed()) {
            activeDebugMotor = rightFront;
        } else if (gamepad.isDPadLeftPressed()) {
            activeDebugMotor = leftRear;
        } else if (gamepad.isDPadDownPressed()) {
            activeDebugMotor = rightRear;
        }

        if (gamepad.isYPressed()) {
            activeDebugMotor.setPower(0.7);
        } else {
            activeDebugMotor.setPower(0);
        }
        if (gamepad.isAPressed()) {
            if (activeDebugMotor.getDirection() == DcMotor.Direction.REVERSE)
                activeDebugMotor.setDirection(DcMotor.Direction.FORWARD);
            else if (activeDebugMotor.getDirection() == DcMotor.Direction.FORWARD) {
                activeDebugMotor.setDirection(DcMotor.Direction.REVERSE);
            }
        }

        if (gamepad.isBPressed()) {
            currentIndex = (currentIndex + 1) % imuParametersList.size();
            IMU.Parameters nextParameters = imuParametersList.get(currentIndex);
            imu.initialize(nextParameters);
        }
    }

    /**
     * Telemetry method to display key information
     * @param telemetry references local telemetry
     */
    @Override
    public void telemetry(Telemetry telemetry) {
        switch (activeDriveMode) {
            case ARCADE:
                telemetry.addData("Drive Mode: ", "Arcade");
                telemetry.addLine("Press the left bumper to switch to Field Centric");
                telemetry.addLine("Press any dpad button to switch to Direction Debugging");
                break;
            case FIELD_CENTRIC:
                telemetry.addData("Drive Mode: ", "Field Centric");
                telemetry.addLine("Press the left bumper to switch to Arcade");
                telemetry.addLine("Press any dpad button to switch to Direction Debugging");
                break;
            case DIRECTION_DEBUGGING:
                telemetry.addData("Drive Mode: ", "Direction Debugging");
                telemetry.addLine("Press dpad to select motor");
                telemetry.addLine("Press Y to run motor");
                telemetry.addLine("Press A to toggle motor direction");
                telemetry.addData("Active Motor: ", activeDebugMotor.toString());
                telemetry.addData("Active Motor Direction: ", activeDebugMotor.getDirection().toString());

                // Telemetry to show the IMU data
                telemetry.addData("IMU YAW: ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
                telemetry.addData("IMU Pitch: ", imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES));
                telemetry.addData("IMU Roll: ", imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES));
                break;
        }
    }

    public List<IMU.Parameters> getAllIMUParameters() {
        List<IMU.Parameters> imuParametersList = new ArrayList<>();

        for (RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection : RevHubOrientationOnRobot.LogoFacingDirection.values()) {
            for (RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection : RevHubOrientationOnRobot.UsbFacingDirection.values()) {
                if (!logoFacingDirection.toString().equals(usbFacingDirection.toString()) && !isOpposite(logoFacingDirection, usbFacingDirection)) {
                    RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);
                    IMU.Parameters parameters = new IMU.Parameters(revHubOrientationOnRobot);
                    imuParametersList.add(parameters);
                }
            }
        }

        return imuParametersList;
    }

    private boolean isOpposite(RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection, RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection) {
        return (logoFacingDirection == RevHubOrientationOnRobot.LogoFacingDirection.UP && usbFacingDirection == RevHubOrientationOnRobot.UsbFacingDirection.DOWN) ||
                (logoFacingDirection == RevHubOrientationOnRobot.LogoFacingDirection.DOWN && usbFacingDirection == RevHubOrientationOnRobot.UsbFacingDirection.UP) ||
                (logoFacingDirection == RevHubOrientationOnRobot.LogoFacingDirection.LEFT && usbFacingDirection == RevHubOrientationOnRobot.UsbFacingDirection.RIGHT) ||
                (logoFacingDirection == RevHubOrientationOnRobot.LogoFacingDirection.RIGHT && usbFacingDirection == RevHubOrientationOnRobot.UsbFacingDirection.LEFT) ||
                (logoFacingDirection == RevHubOrientationOnRobot.LogoFacingDirection.FORWARD && usbFacingDirection == RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD) ||
                (logoFacingDirection == RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD && usbFacingDirection == RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
    }
}

