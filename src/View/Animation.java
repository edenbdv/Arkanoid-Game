
// 323133496 Eden Ben David

package View;

import biuoop.DrawSurface;

/**
 * this interface will be used by objects that can run animation.
 */
public interface Animation {
    /**
     * this function runs One Frame of the animation.
     * @param d DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * this function returns if the animation need to be stopped.
     * @return boolean
     */
    boolean shouldStop();
}
