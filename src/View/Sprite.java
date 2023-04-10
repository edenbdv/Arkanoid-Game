// 323133496 Eden Ben David

package View;
import biuoop.DrawSurface;

/**
 * this interface will be used by objects that can be drawn to the screen.
 */
public interface Sprite {

    /**
     * this function draws the sprite to the screen.
     *
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * this function notify the sprite that time has passed.
     */
    void timePassed();

}
