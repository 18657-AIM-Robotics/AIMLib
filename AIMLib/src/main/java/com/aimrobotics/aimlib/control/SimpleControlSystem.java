package com.aimrobotics.aimlib.control;

public class SimpleControlSystem {

    private final PIDController pidController;
    private final FeedforwardController feedforwardController;
    private final LowPassFilter lowPassFilter;

    private double target;

    public SimpleControlSystem(PIDController pidController, FeedforwardController feedforwardController, LowPassFilter lowPassFilter) {
        this.pidController = pidController;
        this.feedforwardController = feedforwardController;
        this.lowPassFilter = lowPassFilter;
    }

    public double update(double state) {
        return update(state, 0, 0);
    }

    public double update(double state, double referenceVelocity) {
        return update(state, referenceVelocity, 0);
    }

    public double update(double state, double referenceVelocity, double referenceAcceleration) {
        double pidOutput = pidController.calculate(target, lowPassFilter.filter(state));
        double feedforwardOutput = feedforwardController.calculate(target, referenceVelocity, referenceAcceleration);
        return pidOutput + feedforwardOutput;
    }

    public void setTarget(double target) {
        this.target = target;
    }
}
