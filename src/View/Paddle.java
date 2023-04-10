// 323133496 Eden Ben David

package View;

import Mechanic.Collidable;
import Mechanic.Velocity;
import Shapes.Ball;
import Shapes.Point;
import Shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * this class can create Paddle's objects and implement "Collidable" and "Sprite" interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private Mechanic.GameLevel gameLevel;
    private int speed;



    /**
     * constructor - create Paddle instance according to gameLevel, rectangle and color and speed.
     * @param gameLevel - GameLevel
     * @param rectangle - Rectangle
     * @param color - Color
     * @param speed - int
     */
    public Paddle(Mechanic.GameLevel gameLevel, Rectangle rectangle, Color color, int speed) {
        this.keyboard = gameLevel.getRunner().getGui().getKeyboardSensor();
        this.gameLevel = gameLevel;
        this.rectangle = rectangle;
        this.color = color;
        this.speed = speed;
    }

    // accessors:

    /**
     * this function allows get to the field "rectangle" in class Paddle from outside the class.
     *
     * @return Rectangle
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * this function allows get to the field "keyboard" in class Paddle from outside the class.
     *
     * @return Rectangle
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }


    //methods:

    /**
     * this function move the paddle one step to the left if left key keyboard was pressed.
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && (this.rectangle.getUpperLeft().getX() - this.speed >= this.gameLevel.getBORDER())) {
            Point newPoint = new Point(this.rectangle.getUpperLeft().getX()
                    - this.speed, this.rectangle.getUpperLeft().getY());
            this.rectangle = new Rectangle(newPoint, this.rectangle.getWidth(), this.rectangle.getHeight());
        }

    }

    /**
     * this function move the paddle one step to the right if left key keyboard was pressed.
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + this.speed
                <= this.gameLevel.getWIDTH() - this.gameLevel.getBORDER())) {
            Point newPoint = new Point(this.rectangle.getUpperLeft().getX()
                    + this.speed, this.rectangle.getUpperLeft().getY());
            this.rectangle = new Rectangle(newPoint, this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    // Sprite


    /**
     * this function call moveLeft and moveRight function in order to move the paddle according to keyboard's input.
     */
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }

    /**
     * this function draws the Paddle on the surface.
     *
     * @param d DrawSurface - is an object of drawing surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    // Collidable

    /**
     * this function returns the "collision shape" of the object.
     *
     * @return Rectangle - "collision shape" of the object
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * this function returns the new velocity expected after the hit (based on the position of the collision point).
     *
     * @param hitter          - Ball
     * @param collisionPoint  - Point
     * @param currentVelocity - Velocity
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVel;
        double xCol = collisionPoint.getX();
        double yCol = collisionPoint.getY();
        double width = this.rectangle.getWidth();
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));


        // check if the ball is under the paddle
        if (collisionPoint.getY() != this.rectangle.getUpperLeft().getY()) {
            // check collision on vertical lines:
            if ((xCol == rectangle.getUpperLeft().getX()) || (xCol == rectangle.getUpperLeft().getX() + width)) {
                //change the horizontal direction
                newVel = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            } else {
                // collision on horizontal lines:change vertical direction
                newVel = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            }
            return newVel;
        }


        // speed^2 = (dx)^2 + (dy)^2

        //check in witch part of the paddle the collision point at:

        if (xCol <= (width / 5)) {
            //part 1
            newVel = currentVelocity.fromAngleAndSpeed(300, speed);

        } else if ((xCol > (width / 5)) && (xCol <= 2 * (width / 5))) {
            //part 2
            newVel = currentVelocity.fromAngleAndSpeed(330, speed);

        } else if ((xCol > (width / 5) * 2) && (xCol <= 3 * (width / 5))) {
            //part 3
            newVel = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        } else if ((xCol > (width / 5) * 3) && (xCol <= 4 * (width / 5))) {
            //part 3
            newVel = currentVelocity.fromAngleAndSpeed(30, speed);
        } else {
            newVel = currentVelocity.fromAngleAndSpeed(60, speed);
        }
        return newVel;


    }


    /**
     * this function adds the paddle to the game.
     *
     * @param g - Game
     */
    public void addToGame(Mechanic.GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * this function removes the paddle from the game.
     *
     * @param g - Game
     */
    public void removeFromGame(Mechanic.GameLevel g) {
        g.removeSprite(this);
        g.addCollidable(this);
    }
}
