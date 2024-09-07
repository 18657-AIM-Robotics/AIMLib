package com.aimrobotics.aimlib.control;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * PIDController class is a framework used for PID control on a system
 *
 * @Author Nate Schmelkin
 * Inspiration from 479 StuyFusion and ThermalEquilibrium
 */

public class PIDController {

    private ElapsedTime timer = new ElapsedTime(); // timer for calculating change in time

    private boolean hasStarted = false; // has the timer started
    private double integralSum = 0; // sum of the integral
    private double previousError = 0; // previous error

    private final double kP; // proportional constant
    private final double kI; // integral constant
    private final double kD; // derivative constant
    private LowPassFilter lowPassFilter; // low pass filter for the derivative
    private double integralSumMax; // maximum value of the integral sum

    /**
     * Constructor for PIDController
     * @param kP proportional constant
     * @param kI integral constant
     * @param kD derivative constant
     * @param derivativeLowPassGain gain for the low pass filter on the derivative
     * @param integralSumMax maximum value of the integral sum
     */
    public PIDController(double kP, double kI, double kD, double derivativeLowPassGain, double integralSumMax) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.lowPassFilter = new LowPassFilter(derivativeLowPassGain);
        this.integralSumMax = integralSumMax;
    }

    /**
     * Calculates the output of the PID controller
     * @param target target value of the PID controller
     * @param current current value of the system
     * @return output of the PID controller
     */
    public double calculate(double target, double current) {
        double dt = getDT();
        double error = target - current;
        double derivative = calculateDerivative(error, dt);
        integrate(error, dt);
        previousError = error;
        return (kP * error) + (kI * integralSum) + (kD * derivative);
    }

    /**
     * Gets the change in time since the last call
     * @return change in time
     */
    public double getDT() {
        if (!hasStarted) {
            timer.reset();
            hasStarted = true;
        }
        double dt = timer.seconds();
        timer.reset();
        return dt;
    }


    /**
     * Calculates the derivative of the error then filters the result
     * @param error error of the system
     * @param dt change in time
     * @return filtered derivative of the error
     */
    private double calculateDerivative(double error, double dt) {
        double derivative = (error - getPreviousError()) / dt;
        derivative = lowPassFilter.filter(derivative);
        return derivative;
    }

    /**
     * Integrates the error and caps the sum. Resets the sum if the error crosses zero
     * @param error error of the system
     * @param dt change in time
     */
    private void integrate(double error, double dt) {
        if (hasErrorCrossedZero(error)) {
            integralSum = 0;
        }
        integralSum += error * dt;
        if (Math.abs(integralSum) > integralSumMax) {
            integralSum = Math.signum(integralSum) * integralSumMax;
        }
    }

    /**
     * Checks if the error has crossed zero
     * @param error error of the system
     * @return true if the error has crossed zero in the last loop
     */
    private boolean hasErrorCrossedZero(double error) {
        return (error > 0 && getPreviousError() < 0) || (error < 0 && getPreviousError() > 0);
    }

    /**
     * Gets the previous error
     * @return previous error
     */
    private double getPreviousError() {
        return previousError;
    }
}
