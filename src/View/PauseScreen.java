// 323133496 Eden Ben David

package View;

import java.awt.Color;

/**
 * this class can create PauseScreen's objects and implement "Animation" interface.
 */
public class PauseScreen implements View.Animation {


    /**
     * this function runs One Frame of the animation.
     * it has option to pause the game when pressing the p key.
     *
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(biuoop.DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);

    }

    /**
     * this function returns if the animation need to be stopped.
     *
     * @return boolean
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
