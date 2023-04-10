// 323133496 Eden Ben David

package View;

import biuoop.DrawSurface;

/**
 * this class can create EndScreen's objects and implement "Animation" interface.
 */
public class EndScreen implements View.Animation {

    private boolean isWin;
    private int score;

    /**
     * constructor - creates EndScreen's object by isWin and score.
     * @param isWin boolean
     * @param score int
     */
    public EndScreen(boolean isWin, int score) {
        this.isWin = isWin;
        this.score = score;
    }


    /**
     * this function runs One Frame of the animation.
     * it has option to pause the game when pressing the p key.
     * //......................................................................
     *
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(java.awt.Color.white);
        if (isWin) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
            /**if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }**/
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
        }
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
