package com.aimrobotics.aimlib.subsystems.sds;

public class ServoState {

    private double position;

    public ServoState(double position) {
        this.position = position;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
}
