package com.aimrobotics.aimlib.control;


/**
 * FeedforwardController class implements feedforward control on a system
 * Has expanded usages for arm and slide systems with cosine and gravity compensation
 *
 * @Author Nate Schmelkin
 * Inspiration from 479 StuyFusion and ThermalEquilibrium
 */
public class FeedforwardController {

    private double kV;
    private double kA;
    private double kStatic;
    private double kCos;
    private double kG;

    public FeedforwardController(double kV, double kA, double kStatic, double kCos, double kG) {
        this.kV = kV;
        this.kA = kA;
        this.kStatic = kStatic;
        this.kCos = kCos;
        this.kG = kG;
    }

    public double calculate(double targetPos, double referenceVelocity, double referenceAcceleration) {
        return (kV * referenceVelocity) + (kA * referenceAcceleration) + (Math.cos(targetPos) * kCos) + kG;
    }
}
