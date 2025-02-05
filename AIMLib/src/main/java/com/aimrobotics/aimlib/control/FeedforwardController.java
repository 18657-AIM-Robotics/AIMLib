package com.aimrobotics.aimlib.control;


/**
 * FeedforwardController class implements feedforward control on a system
 * Has expanded usages for arm and slide systems with cosine and gravity compensation
 *
 * @Author Nate Schmelkin
 * Inspiration from 479 StuyFusion and ThermalEquilibrium
 */
public class FeedforwardController {

    private double kV; // velocity proportional constant
    private double kA; // acceleration proportional constant
    private double kStatic; // static friction constant
    private double kCos; // cosine compensation constant
    private double kG; // gravity compensation constant


    /**
     * Constructor for FeedforwardController
     * @param kV velocity proportional constant
     * @param kA acceleration proportional constant
     * @param kStatic static friction constant
     * @param kCos cosine compensation constant in degrees
     * @param kG gravity compensation constant
     */
    public FeedforwardController(double kV, double kA, double kStatic, double kCos, double kG) {
        this.kV = kV;
        this.kA = kA;
        this.kStatic = kStatic;
        this.kCos = kCos;
        this.kG = kG;
    }

    /**
     * Calculate the feedforward output
     * @param targetPos target position
     * @param referenceVelocity reference velocity
     * @param referenceAcceleration reference acceleration
     * @return feedforward output
     */
    public double calculate(double targetPos, double referenceVelocity, double referenceAcceleration) {
        return (kV * referenceVelocity) + (kA * referenceAcceleration) + (Math.cos(Math.toRadians(targetPos)) * kCos) + kG;
    }
}
