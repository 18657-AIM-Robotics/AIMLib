package com.aimrobotics.aimlib.control;

/**
 * SimpleControlSystem class implements a simple control system with PID, feedforward, and low pass filter
 *
 * @Author Nate Schmelkin
 */
public class SimpleControlSystem {

    private final PIDController pidController; // PID controller for the system
    private final FeedforwardController feedforwardController; // feedforward controller for the system
    private final LowPassFilter lowPassFilter; // low pass filter for the system

    private double target; // target value of the system

    /**
     * Constructor for SimpleControlSystem
     * @param pidController PID controller for the system
     * @param feedforwardController feedforward controller for the system
     * @param lowPassFilter low pass filter for the system
     */
    public SimpleControlSystem(PIDController pidController, FeedforwardController feedforwardController, LowPassFilter lowPassFilter) {
        this.pidController = pidController;
        this.feedforwardController = feedforwardController;
        this.lowPassFilter = lowPassFilter;
    }

    /**
     * Updates the control system exclusively with current state
     * @param state current state/position/value of the system
     * @return system output according to state alone
     */
    public double update(double state) {
        return update(state, 0, 0);
    }

    /**
     * Updates the control system with current state and reference velocity
     * @param state current state/position/value of the system
     * @param referenceVelocity reference velocity of the system
     * @return system output according to state and reference velocity
     */
    public double update(double state, double referenceVelocity) {
        return update(state, referenceVelocity, 0);
    }


    /**
     * Updates the control system with current state, reference velocity, and reference acceleration
     * @param state current state/position/value of the system
     * @param referenceVelocity reference velocity of the system
     * @param referenceAcceleration reference acceleration of the system
     * @return system output according to state, reference velocity, and reference acceleration
     */
    public double update(double state, double referenceVelocity, double referenceAcceleration) {
        double pidOutput = pidController.calculate(target, lowPassFilter.filter(state));
        double feedforwardOutput = feedforwardController.calculate(target, referenceVelocity, referenceAcceleration);
        return pidOutput + feedforwardOutput;
    }

    /**
     * Sets the target value of the system
     * @param target target value of the system
     */
    public void setTarget(double target) {
        this.target = target;
    }
}
