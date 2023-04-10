// 323133496 Eden Ben David

package Levels;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class can create Background's objects and implement "Sprite" interface.
 */
public class Background implements View.Sprite {

    static final int WIDTH = 800;
    static final int HEIGHT = 600;


    private Color color;


    /**
     * constructor - creates Background's object by color.
     * @param color Color
     */
    public Background(Color color) {
        this.color = color;
    }

    /**
     * this function draws the Background to the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 40, WIDTH, HEIGHT - 40);
    }

    /**
     * this function notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * this function adds the Background to the game.
     *
     * @param g - Game
     */
    public void addToGame(Mechanic.GameLevel g) {
        g.addSprite(this);
    }
}
