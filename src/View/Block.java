// 323133496 Eden Ben David

package View;

import Mechanic.Collidable;
import Mechanic.HitNotifier;
import Mechanic.Velocity;
import Shapes.Ball;
import Shapes.Line;
import Shapes.Point;
import Shapes.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * this class can create Block's objects and implement "Collidable", "Sprite" and "HitNotifier" interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private int hits;
    private Color color;
    private List<Mechanic.HitListener> hitListeners;


    /**
     * constructor - creates Block's object by rectangle and color.
     *
     * @param rectangle - Rectangle
     * @param color     - Color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.hits = 0;
        this.color = color;
        this.hitListeners = new ArrayList<Mechanic.HitListener>();
    }

    // accessors:

    /**
     * this function returns the "collision shape" of the object.
     *
     * @return Rectangle - "collision shape" of the object
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * this function returns the "hits" number of the object.
     *
     * @return int - hits number
     */
    public int getHits() {
        return this.hits;
    }

    //setters:

    /**
     * this function allow set the field "hits" in class Block from outside the class.
     *
     * @param hits int
     */
    public void setHits(int hits) {
        this.hits = hits;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity
     * and call to the function "notifyHit".
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          - Ball
     * @param collisionPoint  - Point
     * @param currentVelocity - Velocity
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.hits++;
        this.notifyHit(hitter);
        Line[] rectLines = this.rectangle.getRectLines();
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // check collision on vertical lines:
        if (this.rectangle.isOnRectangleLine(rectLines, 0, collisionPoint)
                || (this.rectangle.isOnRectangleLine(rectLines, 1, collisionPoint))) {
            //change the horizontal direction
            newVelocity.setDx(-1 * newVelocity.getDx());
        }
        // check collision on horizontal lines:
        if (this.rectangle.isOnRectangleLine(rectLines, 2, collisionPoint)
                || this.rectangle.isOnRectangleLine(rectLines, 3, collisionPoint)) {
            //change vertical direction
            newVelocity.setDy(-1 * newVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * this function draws the Block on the surface.
     *
     * @param surface DrawSurface - is an object of drawing surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());

    }


    /**
     *
     */
    public void timePassed() {
        return;
    }

    /**
     * this function adds the block to the game.
     *
     * @param g - Game
     */
    public void addToGame(Mechanic.GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }


    /**
     * this function removes the block from the game.
     * @param gameLevel - GameLevel
     */
    public void removeFromGame(Mechanic.GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);

    }


    /**
     * this function adds hl as a listener to hit events of the block.
     *
     * @param hl - HitListener
     */
    @Override
    public void addHitListener(Mechanic.HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * this function removes hl from the list of listeners to hit events of the block.
     *
     * @param hl - HitListener
     */
    @Override
    public void removeHitListener(Mechanic.HitListener hl) {
        this.hitListeners.remove(hl);
    }


    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<Mechanic.HitListener> listeners = new ArrayList<Mechanic.HitListener>(this.hitListeners);

        if (this.equals(hitter.getGame().getDeathRegion())) {
            this.hitListeners.get(0).hitEvent(this, hitter);
        } else {
            // Notify all listeners about a hit event:
            for (Mechanic.HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }

    }
}






