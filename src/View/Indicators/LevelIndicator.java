// 323133496 Eden Ben David

package View.Indicators;
import Levels.LevelInformation;
import Shapes.Rectangle;

/**
 * this class can create LevelIndicator's objects and implement "Sprite" interface.
 */
public class LevelIndicator implements View.Sprite {

    private LevelInformation levelInformation;
    private Rectangle rectangle;

    /**
     * constructor - creates LevelIndicator's object by levelInformation.
     * @param levelInformation - LevelInformation
     */
    public LevelIndicator(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
        Shapes.Point p = new Shapes.Point(500, 0);
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
                "Level Name:" + this.levelInformation.levelName().toString(), 15);
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
