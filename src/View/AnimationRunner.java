// 323133496 Eden Ben David

package View;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * this class takes an Animation object and runs it.
 */
public class AnimationRunner {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor -this function create AnimationRunner's object by gui, framesPerSecond and sleeper.
     */
    public AnimationRunner() {
        this.gui = new GUI("game", WIDTH, HEIGHT);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * this function allows get to the field "gui" in class AnimationRunner from outside the class.
     *
     * @return gui - GUI
     */
    public biuoop.GUI getGui() {
        return gui;
    }

    /**
     * this function takes an Animation object and runs it.
     *
     * @param animation Animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(java.awt.Color.DARK_GRAY);
            d.fillRectangle(0, 0, WIDTH, HEIGHT);
            d.setColor(java.awt.Color.white);
            d.fillRectangle(0, 0, WIDTH, 20);
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }
}
