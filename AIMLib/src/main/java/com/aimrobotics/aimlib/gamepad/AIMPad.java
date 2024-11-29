package com.aimrobotics.aimlib.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;


/**
 * AIMPad class is a class that allows for more advanced gamepad controls
 *
 * @Author Nate Schmelkin
 */

public class AIMPad {

    private Gamepad currentState;
    private Gamepad previousState;

    /**
     * Constructor for AIMPad
     * @param gamepad gamepad to be used
     */
    public AIMPad(Gamepad gamepad) {
        currentState = new Gamepad();
        previousState = new Gamepad();
    }

    /**
     * Updates the gamepad states
     * @param gamepad gamepad to be used
     */
    public void update(Gamepad gamepad) {
        previousState.copy(currentState);
        currentState.copy(gamepad);
    }

    /**
     * Checks if a button is pressed
     * @param currentButtonState current state of the button
     * @param previousButtonState previous state of the button
     * @return true if the button is pressed
     */
    private boolean isButtonPressed(boolean currentButtonState, boolean previousButtonState) {
        return currentButtonState && !previousButtonState;
    }

    /**
     * Checks if a button is released
     * @param currentButtonState current state of the button
     * @param previousButtonState previous state of the button
     * @return true if the button is released
     */
    private boolean isButtonReleased(boolean currentButtonState, boolean previousButtonState) {
        return !currentButtonState && previousButtonState;
    }

    /**
     * Checks if a button is held
     * @param currentButtonState current state of the button
     * @param previousButtonState previous state of the button
     * @return true if the button is held
     */
    private boolean isButtonHeld(boolean currentButtonState, boolean previousButtonState) {
        return currentButtonState && previousButtonState;
    }

    /**
     * Gets the current gamepad state
     * @return current gamepad state
     */
    public Gamepad getCurrentState() {
        return currentState;
    }

    /**
     * Gets the previous gamepad state
     * @return previous gamepad state
     */
    public Gamepad getPreviousState() {
        return previousState;
    }

    //
    // BUTTONS PRESSED
    //

    /**
     * Checks if the A button is pressed
     * @return true if the A button is pressed
     */
    public boolean isAPressed() {
        return isButtonPressed(currentState.a, previousState.a);
    }

    /**
     * Checks if the B button is pressed
     * @return true if the B button is pressed
     */
    public boolean isBPressed() {
        return isButtonPressed(currentState.b, previousState.b);
    }

    /**
     * Checks if the X button is pressed
     * @return true if the X button is pressed
     */
    public boolean isXPressed() {
        return isButtonPressed(currentState.x, previousState.x);
    }

    /**
     * Checks if the Y button is pressed
     * @return true if the Y button is pressed
     */
    public boolean isYPressed() {
        return isButtonPressed(currentState.y, previousState.y);
    }

    /**
     * Checks if the DPad Up button is pressed
     * @return true if the DPad Up button is pressed
     */
    public boolean isDPadUpPressed() {
        return isButtonPressed(currentState.dpad_up, previousState.dpad_up);
    }

    /**
     * Checks if the DPad Down button is pressed
     * @return true if the DPad Down button is pressed
     */
    public boolean isDPadDownPressed() {
        return isButtonPressed(currentState.dpad_down, previousState.dpad_down);
    }

    /**
     * Checks if the DPad Left button is pressed
     * @return true if the DPad Left button is pressed
     */
    public boolean isDPadLeftPressed() {
        return isButtonPressed(currentState.dpad_left, previousState.dpad_left);
    }

    /**
     * Checks if the DPad Right button is pressed
     * @return true if the DPad Right button is pressed
     */
    public boolean isDPadRightPressed() {
        return isButtonPressed(currentState.dpad_right, previousState.dpad_right);
    }

    /**
     * Checks if the Left Bumper button is pressed
     * @return true if the Left Bumper button is pressed
     */
    public boolean isLeftBumperPressed() {
        return isButtonPressed(currentState.left_bumper, previousState.left_bumper);
    }

    /**
     * Checks if the Right Bumper button is pressed
     * @return true if the Right Bumper button is pressed
     */
    public boolean isRightBumperPressed() {
        return isButtonPressed(currentState.right_bumper, previousState.right_bumper);
    }

    /**
     * Checks if the Left Stick button is pressed
     * @return true if the Left Stick button is pressed
     */
    public boolean isLeftStickPressed() {
        return isButtonPressed(currentState.left_stick_button, previousState.left_stick_button);
    }

    /**
     * Checks if the Right Stick button is pressed
     * @return true if the Right Stick button is pressed
     */
    public boolean isRightStickPressed() {
        return isButtonPressed(currentState.right_stick_button, previousState.right_stick_button);
    }

    /**
     * Checks if the Start button is pressed
     * @return true if the Start button is pressed
     */
    public boolean isStartPressed() {
        return isButtonPressed(currentState.start, previousState.start);
    }

    /**
     * Checks if the Back button is pressed
     * @return true if the Back button is pressed
     */
    public boolean isBackPressed() {
        return isButtonPressed(currentState.back, previousState.back);
    }

    /**
     * Checks if any DPad button is pressed
     * @return true if any DPad button is pressed
     */
    public boolean isAnyDPadPressed() {
        return isButtonPressed(currentState.dpad_up, previousState.dpad_up) ||
                isButtonPressed(currentState.dpad_down, previousState.dpad_down) ||
                isButtonPressed(currentState.dpad_left, previousState.dpad_left) ||
                isButtonPressed(currentState.dpad_right, previousState.dpad_right);
    }

    /**
     * Checks if any bumper button is pressed
     * @return true if any bumper button is pressed
     */
    public boolean isAnyBumperPressed() {
        return isButtonPressed(currentState.left_bumper, previousState.left_bumper) ||
                isButtonPressed(currentState.right_bumper, previousState.right_bumper);
    }



    //
    // BUTTONS RELEASED
    //

    /**
     * Checks if the A button is released
     * @return true if the A button is released
     */
    public boolean isAReleased() {
        return isButtonReleased(currentState.a, previousState.a);
    }

    /**
     * Checks if the B button is released
     * @return true if the B button is released
     */
    public boolean isBReleased() {
        return isButtonReleased(currentState.b, previousState.b);
    }

    /**
     * Checks if the X button is released
     * @return true if the X button is released
     */
    public boolean isXReleased() {
        return isButtonReleased(currentState.x, previousState.x);
    }

    /**
     * Checks if the Y button is released
     * @return true if the Y button is released
     */
    public boolean isYReleased() {
        return isButtonReleased(currentState.y, previousState.y);
    }

    /**
     * Checks if the DPad Up button is released
     * @return true if the DPad Up button is released
     */
    public boolean isDPadUpReleased() {
        return isButtonReleased(currentState.dpad_up, previousState.dpad_up);
    }

    /**
     * Checks if the DPad Down button is released
     * @return true if the DPad Down button is released
     */
    public boolean isDPadDownReleased() {
        return isButtonReleased(currentState.dpad_down, previousState.dpad_down);
    }

    /**
     * Checks if the DPad Left button is released
     * @return true if the DPad Left button is released
     */
    public boolean isDPadLeftReleased() {
        return isButtonReleased(currentState.dpad_left, previousState.dpad_left);
    }

    /**
     * Checks if the DPad Right button is released
     * @return true if the DPad Right button is released
     */
    public boolean isDPadRightReleased() {
        return isButtonReleased(currentState.dpad_right, previousState.dpad_right);
    }

    /**
     * Checks if the Left Bumper button is released
     * @return true if the Left Bumper button is released
     */
    public boolean isLeftBumperReleased() {
        return isButtonReleased(currentState.left_bumper, previousState.left_bumper);
    }

    /**
     * Checks if the Right Bumper button is released
     * @return true if the Right Bumper button is released
     */
    public boolean isRightBumperReleased() {
        return isButtonReleased(currentState.right_bumper, previousState.right_bumper);
    }

    /**
     * Checks if the Left Stick button is released
     * @return true if the Left Stick button is released
     */
    public boolean isLeftStickReleased() {
        return isButtonReleased(currentState.left_stick_button, previousState.left_stick_button);
    }

    /**
     * Checks if the Right Stick button is released
     * @return true if the Right Stick button is released
     */
    public boolean isRightStickReleased() {
        return isButtonReleased(currentState.right_stick_button, previousState.right_stick_button);
    }

    /**
     * Checks if the Start button is released
     * @return true if the Start button is released
     */
    public boolean isStartReleased() {
        return isButtonReleased(currentState.start, previousState.start);
    }

    /**
     * Checks if the Back button is released
     * @return true if the Back button is released
     */
    public boolean isBackReleased() {
        return isButtonReleased(currentState.back, previousState.back);
    }

    /**
     * Checks if any DPad button is released
     * @return true if any DPad button is released
     */
    public boolean isAnyDPadReleased() {
        return isButtonReleased(currentState.dpad_up, previousState.dpad_up) ||
                isButtonReleased(currentState.dpad_down, previousState.dpad_down) ||
                isButtonReleased(currentState.dpad_left, previousState.dpad_left) ||
                isButtonReleased(currentState.dpad_right, previousState.dpad_right);
    }

    /**
     * Checks if any bumper button is released
     * @return true if any bumper button is released
     */
    public boolean isAnyBumperReleased() {
        return isButtonReleased(currentState.left_bumper, previousState.left_bumper) ||
                isButtonReleased(currentState.right_bumper, previousState.right_bumper);
    }

    //
    // BUTTONS HELD
    //

    /**
     * Checks if the A button is held
     * @return true if the A button is held
     */
    public boolean isAHeld() {
        return isButtonHeld(currentState.a, previousState.a);
    }

    /**
     * Checks if the B button is held
     * @return true if the B button is held
     */
    public boolean isBHeld() {
        return isButtonHeld(currentState.b, previousState.b);
    }

    /**
     * Checks if the X button is held
     * @return true if the X button is held
     */
    public boolean isXHeld() {
        return isButtonHeld(currentState.x, previousState.x);
    }

    /**
     * Checks if the Y button is held
     * @return true if the Y button is held
     */
    public boolean isYHeld() {
        return isButtonHeld(currentState.y, previousState.y);
    }

    /**
     * Checks if the DPad Up button is held
     * @return true if the DPad Up button is held
     */
    public boolean isDPadUpHeld() {
        return isButtonHeld(currentState.dpad_up, previousState.dpad_up);
    }

    /**
     * Checks if the DPad Down button is held
     * @return true if the DPad Down button is held
     */
    public boolean isDPadDownHeld() {
        return isButtonHeld(currentState.dpad_down, previousState.dpad_down);
    }

    /**
     * Checks if the DPad Left button is held
     * @return true if the DPad Left button is held
     */
    public boolean isDPadLeftHeld() {
        return isButtonHeld(currentState.dpad_left, previousState.dpad_left);
    }

    /**
     * Checks if the DPad Right button is held
     * @return true if the DPad Right button is held
     */
    public boolean isDPadRightHeld() {
        return isButtonHeld(currentState.dpad_right, previousState.dpad_right);
    }

    /**
     * Checks if the Left Bumper button is held
     * @return true if the Left Bumper button is held
     */
    public boolean isLeftBumperHeld() {
        return isButtonHeld(currentState.left_bumper, previousState.left_bumper);
    }

    /**
     * Checks if the Right Bumper button is held
     * @return true if the Right Bumper button is held
     */
    public boolean isRightBumperHeld() {
        return isButtonHeld(currentState.right_bumper, previousState.right_bumper);
    }

    /**
     * Checks if the Left Stick button is held
     * @return true if the Left Stick button is held
     */
    public boolean isLeftStickHeld() {
        return isButtonHeld(currentState.left_stick_button, previousState.left_stick_button);
    }

    /**
     * Checks if the Right Stick button is held
     * @return true if the Right Stick button is held
     */
    public boolean isRightStickHeld() {
        return isButtonHeld(currentState.right_stick_button, previousState.right_stick_button);
    }

    /**
     * Checks if the Start button is held
     * @return true if the Start button is held
     */
    public boolean isStartHeld() {
        return isButtonHeld(currentState.start, previousState.start);
    }

    /**
     * Checks if the Back button is held
     * @return true if the Back button is held
     */
    public boolean isBackHeld() {
        return isButtonHeld(currentState.back, previousState.back);
    }

    /**
     * Checks if any DPad button is held
     * @return true if any DPad button is held
     */
    public boolean isAnyDPadHeld() {
        return isButtonHeld(currentState.dpad_up, previousState.dpad_up) ||
                isButtonHeld(currentState.dpad_down, previousState.dpad_down) ||
                isButtonHeld(currentState.dpad_left, previousState.dpad_left) ||
                isButtonHeld(currentState.dpad_right, previousState.dpad_right);
    }

    /**
     * Checks if any bumper button is held
     * @return true if any bumper button is held
     */
    public boolean isAnyBumperHeld() {
        return isButtonHeld(currentState.left_bumper, previousState.left_bumper) ||
                isButtonHeld(currentState.right_bumper, previousState.right_bumper);
    }

    //
    // TRIGGERS
    //

    /**
     * Gets the left trigger value
     * @return left trigger value
     */
    public double getLeftTrigger() {
        return currentState.left_trigger;
    }

    /**
     * Gets the right trigger value
     * @return right trigger value
     */
    public double getRightTrigger() {
        return currentState.right_trigger;
    }

    /**
     * Checks if the left trigger is pressed
     * @return true if the left trigger is pressed
     */
    public boolean isLeftTriggerPressed() {
        return isButtonPressed(currentState.left_trigger > 0, previousState.left_trigger > 0);
    }

    /**
     * Checks if the right trigger is pressed
     * @return true if the right trigger is pressed
     */
    public boolean isRightTriggerPressed() {
        return isButtonPressed(currentState.right_trigger > 0, previousState.right_trigger > 0);
    }

    /**
     * Checks if the left trigger is released
     * @return true if the left trigger is released
     */
    public boolean isLeftTriggerReleased() {
        return isButtonReleased(currentState.left_trigger > 0, previousState.left_trigger > 0);
    }

    /**
     * Checks if the right trigger is released
     * @return true if the right trigger is released
     */
    public boolean isRightTriggerReleased() {
        return isButtonReleased(currentState.right_trigger > 0, previousState.right_trigger > 0);
    }

    /**
     * Checks if the left trigger is held
     * @return true if the left trigger is held
     */
    public boolean isLeftTriggerHeld() {
        return isButtonHeld(currentState.left_trigger > 0, previousState.left_trigger > 0);
    }

    /**
     * Checks if the right trigger is held
     * @return true if the right trigger is held
     */
    public boolean isRightTriggerHeld() {
        return isButtonHeld(currentState.right_trigger > 0, previousState.right_trigger > 0);
    }

    /**
     * Checks if any trigger is pressed
     * @return true if any trigger is pressed
     */
    public boolean isAnyTriggerPressed() {
        return isButtonPressed(currentState.left_trigger > 0, previousState.left_trigger > 0) ||
                isButtonPressed(currentState.right_trigger > 0, previousState.right_trigger > 0);
    }

    /**
     * Checks if any trigger is released
     * @return true if any trigger is released
     */
    public boolean isAnyTriggerReleased() {
        return isButtonReleased(currentState.left_trigger > 0, previousState.left_trigger > 0) ||
                isButtonReleased(currentState.right_trigger > 0, previousState.right_trigger > 0);
    }

    /**
     * Checks if any trigger is held
     * @return true if any trigger is held
     */
    public boolean isAnyTriggerHeld() {
        return isButtonHeld(currentState.left_trigger > 0, previousState.left_trigger > 0) ||
                isButtonHeld(currentState.right_trigger > 0, previousState.right_trigger > 0);
    }

    //
    // STICKS
    //

    /**
     * Gets the left stick x value
     * @return left stick x value
     */
    public double getLeftStickX() {
        return currentState.left_stick_x;
    }

    /**
     * Gets the left stick y value
     * @return left stick y value
     */
    public double getLeftStickY() {
        return currentState.left_stick_y;
    }

    /**
     * Gets the right stick x value
     * @return right stick x value
     */
    public double getRightStickX() {
        return currentState.right_stick_x;
    }

    /**
     * Gets the right stick y value
     * @return right stick y value
     */
    public double getRightStickY() {
        return currentState.right_stick_y;
    }

    /**
     * Checks if the left stick movement is engaged
     * @return true if the left stick movement is engaged
     */
    public boolean isLeftStickMovementEngaged() {
        return isButtonPressed(currentState.left_stick_x != 0 || currentState.left_stick_y != 0, previousState.left_stick_x != 0 || previousState.left_stick_y != 0);
    }

    /**
     * Checks if the right stick movement is engaged
     * @return true if the right stick movement is engaged
     */
    public boolean isRightStickMovementEngaged() {
        return isButtonPressed(currentState.right_stick_x != 0 || currentState.right_stick_y != 0, previousState.right_stick_x != 0 || previousState.right_stick_y != 0);
    }

    /**
     * Checks if the left stick movement is released
     * @return true if the left stick movement is released
     */
    public boolean isLeftStickMovementReleased() {
        return isButtonReleased(currentState.left_stick_x != 0 || currentState.left_stick_y != 0, previousState.left_stick_x != 0 || previousState.left_stick_y != 0);
    }

    /**
     * Checks if the right stick movement is released
     * @return true if the right stick movement is released
     */
    public boolean isRightStickMovementReleased() {
        return isButtonReleased(currentState.right_stick_x != 0 || currentState.right_stick_y != 0, previousState.right_stick_x != 0 || previousState.right_stick_y != 0);
    }

    /**
     * Checks if the left stick movement is held
     * @return true if the left stick movement is held
     */
    public boolean isLeftStickMovementHeld() {
        return isButtonHeld(currentState.left_stick_x != 0 || currentState.left_stick_y != 0, previousState.left_stick_x != 0 || previousState.left_stick_y != 0);
    }

    /**
     * Checks if the right stick movement is held
     * @return true if the right stick movement is held
     */
    public boolean isRightStickMovementHeld() {
        return isButtonHeld(currentState.right_stick_x != 0 || currentState.right_stick_y != 0, previousState.right_stick_x != 0 || previousState.right_stick_y != 0);
    }

    //
    // WHOLE GAMEPAD
    //

    /**
     * Checks if any button is pressed
     * @return true if any button is pressed
     */
    public boolean isAnyButtonPressed() {
        return isAPressed() ||
                isBPressed() ||
                isXPressed() ||
                isYPressed() ||
                isAnyBumperPressed() ||
                isAnyDPadPressed() ||
                isLeftStickPressed() ||
                isRightStickPressed() ||
                isStartPressed() ||
                isBackPressed() ||
                isAnyTriggerPressed() ||
                isLeftStickMovementEngaged() ||
                isRightStickMovementEngaged();
    }

    /**
     * Checks if any button is released
     * @return true if any button is released
     */
    public boolean isAnyButtonReleased() {
        return isAReleased() ||
                isBReleased() ||
                isXReleased() ||
                isYReleased() ||
                isAnyBumperReleased() ||
                isAnyDPadReleased() ||
                isLeftStickReleased() ||
                isRightStickReleased() ||
                isStartReleased() ||
                isBackReleased() ||
                isAnyTriggerReleased() ||
                isLeftStickMovementReleased() ||
                isRightStickMovementReleased();
    }

    /**
     * Checks if any button is held
     * @return true if any button is held
     */
    public boolean isAnyButtonHeld() {
        return isAHeld() ||
                isBHeld() ||
                isXHeld() ||
                isYHeld() ||
                isAnyBumperHeld() ||
                isAnyDPadHeld() ||
                isLeftStickHeld() ||
                isRightStickHeld() ||
                isStartHeld() ||
                isBackHeld() ||
                isAnyTriggerHeld() ||
                isLeftStickMovementHeld() ||
                isRightStickMovementHeld();
    }
}
