// 323133496 Eden Ben David

package View.Indicators;

import Shapes.Rectangle;

import java.awt.Color;

/**
 * this class can create ScoreIndicator's objects and implement "Sprite" interface.
 */
public class LivesIndicator implements View.Sprite {

    private View.Counter remainingLives;
    private Rectangle rectangle;

    /**
     * constructor - creates LivesIndicator's object by remainingLives.
     * @param remainingLives - Counter
     */
    public LivesIndicator(View.Counter remainingLives) {
        this.remainingLives = remainingLives;
        Shapes.Point p = new Shapes.Point(0, 0);
        this.rectangle = new Shapes.Rectangle(p, 300, 20);
    }

    /**
     * this function allows get to the field "remainingLives" in class LivesIndicator from outside the class.
     * @return remainingLives - Counter
     */
    public View.Counter getRemainingLives() {
        return this.remainingLives;
    }

    /**
     * subtract number of remainingLives from current count.
     */
    public void decreaseRemainingLives() {
        this.remainingLives.decrease(1);
    }

    /**
     * this function draws the sprite to the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(biuoop.DrawSurface d) {
        d.setColor(java.awt.Color.white);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawText((int) (this.rectangle.getUpperLeft().getX() + 100),
                (int) this.rectangle.getUpperLeft().getY() + 20,
                "Lives:" + Integer.toString(this.remainingLives.getValue()), 15);
    }

    /**
     * this function notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * this function adds the Score Indicator to the game.
     *
     * @param g - Game
     */
    public void addToGame(Mechanic.GameLevel g) {
        g.addSprite(this);
    }
}
