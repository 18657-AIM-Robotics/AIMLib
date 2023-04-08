package com.aimrobotics.aimlib.mechanism;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * ServoHand class is modular framework for servo-based intakes
 * Houses methods for standard intake movements.
 *
 * @author Nate Schmelkin
 */

public class ContinuousServoIntake {

    CRServo leftServo;
    CRServo rightServo;

    /**
     * Constructor
     *
     * @param hwMap references the robot's hardware map
     * @param leftServoName hardware ID of left servo
     * @param rightServoName hardware ID of right servo
     */
    public void initialize(HardwareMap hwMap, String leftServoName, String rightServoName) {
        leftServo = hwMap.get(CRServo.class, leftServoName);
        rightServo = hwMap.get(CRServo.class, rightServoName);
    }


    /**
     * Sets direction of intake servos
     *
     * @param leftDirection left servo direction
     * @param rightDirection right servo direction
     */
    public void setDirections(DcMotorSimple.Direction leftDirection, DcMotorSimple.Direction rightDirection) {
        leftServo.setDirection(leftDirection);
        rightServo.setDirection(rightDirection);
    }

    /**
     * Turns intake on with specified power
     *
     * @param power power to run servos at (0-1)
     */
    public void intake(double power) {
        leftServo.setPower(Math.abs(power));
        rightServo.setPower(Math.abs(power));
    }

    /**
     * Turns outtake on with specified power
     *
     * @param power power to run servos at (0-1)
     */
    public void outtake(double power) {
        leftServo.setPower(-Math.abs(power));
        rightServo.setPower(-Math.abs(power));
    }

    /**
     * Stops servos
     */
    public void stop() {
        leftServo.setPower(0);
        rightServo.setPower(0);
    }

}
