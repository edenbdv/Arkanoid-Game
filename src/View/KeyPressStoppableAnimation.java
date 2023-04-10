// 323133496 Eden Ben David

package View;

import biuoop.KeyboardSensor;


/**
 * decorator class, can create KeyPressStoppableAnimation's objects and implement "Animation" interface.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;


    /**
     * constructor - creates KeyPressStoppableAnimation's object by sensor,key and animation.
     * @param sensor - KeyboardSensor
     * @param key - String
     * @param animation - Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * this function runs One Frame of the animation.
     *
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(biuoop.DrawSurface d) {
        if (this.sensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
    }

    /**
     * this function returns if the animation need to be stopped.
     *
     * @return boolean
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
