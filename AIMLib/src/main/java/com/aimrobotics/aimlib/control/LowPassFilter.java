package com.aimrobotics.aimlib.control;

/**
 * LowPassFilter class filters out high frequency noise from a signal
 *
 * @Author Nate Schmelkin
 * Inspiration from ThermalEquilibrium
 */

public class LowPassFilter {

    private final double gain;

    private double prevOutput = 0;

    /**
     * Constructor for LowPassFilter
     * @param gain gain of the filter
     */
    public LowPassFilter(double gain) {
        this.gain = gain;
    }

    /**
     * Filters the input signal
     * @param input input signal
     * @return filtered signal
     */
    public double filter(double input) {
        double output = gain * prevOutput + (1 - gain) * input;
        prevOutput = output;
        return output;
    }
}
