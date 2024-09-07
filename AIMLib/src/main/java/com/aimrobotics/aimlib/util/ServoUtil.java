package com.aimrobotics.aimlib.util;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoUtil {

    /**
     * Check if a servo is close to a specific position with a given proximity
     * @param servo the servo to check
     * @param pos the position to check
     * @param proximity the proximity threshold
     * @return true if the servo is close to the position, false otherwise
     */
    public static boolean isClose(Servo servo, double pos, double proximity) {
        return servo != null && Math.abs(servo.getPosition() - pos) < proximity;
    }

    // Incrementally adjust positions of two servos

    /**
     * Incrementally adjust positions of two servos
     * @param leftServo left servo
     * @param rightServo right servo
     * @param incrementAmount the amount to increment by
     */
    public static void increment(Servo leftServo, Servo rightServo, double incrementAmount) {
        if (leftServo != null && rightServo != null) {
            leftServo.setPosition(leftServo.getPosition() + incrementAmount);
            rightServo.setPosition(rightServo.getPosition() + incrementAmount);
        }
    }

    /**
     * Incrementally adjust position of one servo
     * @param servo the servo to adjust
     * @param incrementAmount the amount to increment by
     */
    public static void increment(Servo servo, double incrementAmount) {
        if (servo != null) {
            servo.setPosition(servo.getPosition() + incrementAmount);
        }
    }
}
