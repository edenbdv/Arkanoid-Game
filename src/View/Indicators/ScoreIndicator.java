// 323133496 Eden Ben David

package View.Indicators;

/**
 * this class can create ScoreIndicator's objects and implement "Sprite" interface.
 */
public class ScoreIndicator implements View.Sprite {

    private View.ScoreTrackingListener score;
    private Shapes.Rectangle rectangle;

    /**
     * constructor - creates ScoreIndicator's object by score.
     * @param score - ScoreTrackingListener
     */
    public ScoreIndicator(View.ScoreTrackingListener score) {
        this.score = score;
        Shapes.Point p = new Shapes.Point(300, 0);
        this.rectangle = new Shapes.Rectangle(p, 30, 20);
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
        d.setColor(java.awt.Color.black);
        d.drawText((int) (this.rectangle.getUpperLeft().getX() + (rectangle.getWidth() / 2)),
                (int) this.rectangle.getUpperLeft().getY() + 20,
                "Score:" + Integer.toString(this.score.getCurrentScore().getValue()), 15);
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
