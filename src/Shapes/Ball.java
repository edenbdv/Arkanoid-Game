// 323133496 Eden Ben David

package Shapes;

import Mechanic.CollisionInfo;
import Mechanic.GameEnvironment;
import Mechanic.Velocity;
import View.Paddle;
import View.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class can create Ball's objects and draw them.
 */
public class Ball implements Sprite {

    //Fields:
    private Point point;
    private int size;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;
    private Mechanic.GameLevel gameLevel;


    // constructors

    /**
     * constructor 1 - create ball from point, radius and color (with default velocity).
     *
     * @param center Point - the center of the ball
     * @param r      int - the radius of the ball
     * @param color  Color - the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.point = center;
        this.size = r;
        this.color = color;
        this.velocity = velocity;
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * constructor 2 - create ball from x and y coordinates, radius and color (with default velocity).
     *
     * @param x     double - coordinate of the center's point on X axis.
     * @param y     double - coordinate of the  center's point on Y axis.
     * @param r     int - the radius of the ball
     * @param color Color - the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
        this.velocity = velocity;
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * constructor 3 - create ball from x and y coordinates,
     * radius ,color and game enviroment (with default velocity).
     *
     * @param x               double - coordinate of the center's point on X axis.
     * @param y               double - coordinate of the  center's point on Y axis.
     * @param r               int - the radius of the ball
     * @param color           Color - the color of the ball
     * @param gameEnvironment GameEnviroment
     * @param paddle          Paddle
     * @param gameLevel       Game
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gameEnvironment,
                Paddle paddle, Mechanic.GameLevel gameLevel) {

        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        this.paddle = paddle;
        this.gameLevel = gameLevel;
        this.velocity = new Velocity(1, 1);
    }

    // accessors:

    /**
     * this function allows get to the field "x" in class Ball from outside the class.
     *
     * @return the x value of this center's point
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * this function allows get to the field "y" in class Ball from outside the class.
     *
     * @return the y value of this center's point
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * this function allows get to the field "size" in class Ball from outside the class.
     *
     * @return int - size, the radius of the ball
     */
    public int getSize() {
        return this.size;
    }

    /**
     * this function allows get to the field "color" in class Ball from outside the class.
     *
     * @return Color - the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this function allows get to the field "velocity" in class Ball from outside the class.
     *
     * @return Velocity - velocity of the ball
     */


    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * this function allows get to the field "GameEnviroment" in class Ball from outside the class.
     *
     * @return GameEnviroment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }


    /**
     * this function allows get to the field "game" in class Ball from outside the class.
     *
     * @return Game
     */
    public Mechanic.GameLevel getGame() {
        return this.gameLevel;
    }

    //setters:

    /**
     * this function allow set the field "Point" in class Ball from outside the class.
     *
     * @param p - Point
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    /**
     * this function allow set the field "velocity" in class Ball from outside the class.
     *
     * @param v - Velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * this function allow set the field "velocity" in class Ball from outside the class.
     *
     * @param dx double - the change in position on the `x`
     * @param dy double - the change in position on the `y`
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }


    /**
     * this function allow set "GameEnviroment" in class Ball from outside the class.
     *
     * @param gameEnvironment - GameEnviroment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }


    /**
     * thus function draws the ball on the surface according to the ball color, center and size.
     *
     * @param surface DrawSurface - is an object of drawing surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * side function - this function check if the ball is inside the paddle.
     *
     * @return boolean - true if the ball is inside the paddle, false otherwise.
     */
    boolean isInPaddle() {
        double xPoint = this.point.getX();
        double yPoint = this.point.getY();

        Point upperL = this.paddle.getRectangle().getUpperLeft();

        if ((xPoint > upperL.getX()) && (xPoint < upperL.getX() + this.paddle.getRectangle().getWidth())
                && (yPoint > upperL.getY()) && (yPoint < upperL.getY() + this.paddle.getRectangle().getHeight())) {
            return true;
        }
        return false;

    }


    /**
     * this function move the ball one step, if there is a collision it calls the function "hit" and update the new
     * velocity according to hit's result.
     */
    public void moveOneStep() {

        Velocity newVel;
        //check if the ball is inside the paddle
        if (this.isInPaddle()) {
            this.point.setY(this.paddle.getRectangle().getUpperLeft().getY());
            // in case the ball move toward the paddle:
            if (this.velocity.getDy() > 0) {
                this.velocity.setDy(-1 * this.velocity.getDy());
            }
            // update the new position
            this.point = this.velocity.applyToPoint(this.point);
        } else {

            // 1) compute the ball trajectory
            Point next = this.getVelocity().applyToPoint(this.point);
            Line trajectory = new Line(this.point, next);

            // 2) Check if moving on this trajectory will hit anything.
            CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
            if (info == null) {
                //2.1) If no, then move the ball to the end of the trajectory.
                this.point = next;
            } else { //2.2) Otherwise (there is a hit):
                //2.2.2) move the ball to "almost" the hit point, but just slightly before it.
                Point point = new Point(info.collisionPoint().getX() - this.velocity.getDx(),
                        info.collisionPoint().getY() - this.velocity.getDy());
                this.point = point;
                //2.2.3) notify the hit object that a collision occurred.
                newVel = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);

                //2.2.4) update the velocity to the new velocity returned by the hit() method.
                this.velocity = newVel;
            }
        }
    }


    /**
     * this function call moveOneStep.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * this function adds the ball to the game.
     *
     * @param g - Game
     */
    public void addToGame(Mechanic.GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this function removes the ball from the game.
     *
     * @param g - Game
     */
    public void removeFromGame(Mechanic.GameLevel g) {
        g.removeSprite(this);
    }

}


