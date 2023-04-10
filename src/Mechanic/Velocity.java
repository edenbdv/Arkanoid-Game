// 323133496 Eden Ben David

package Mechanic;

import Shapes.Point;

/**
 * this class can create Velocity's objects and apply to point velocity and.
 */
public class Velocity {
    //Fields:
    private double dx;
    private double dy;

    /**
     * this function is a constructor.
     *
     * @param dx double - the change in position on the `x`
     * @param dy double - the change in position on the `y`
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    //accessors:

    /**
     * this function allows get to the field "dx" in class Velocity from outside the class.
     *
     * @return double - the value of dx attribute
     */
    public double getDx() {
        return dx;
    }

    /**
     * this function allows get to the field "dy" in class Velocity from outside the class.
     *
     * @return double - the value of dy attribute
     */
    public double getDy() {
        return dy;
    }

    //setters:


    /**
     * this function allow set the field "dx" in class Velocity from outside the class.
     *
     * @param dx double - the change in position on the `x`
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * this function allow set the field "dy" in class Velocity from outside the class.
     *
     * @param dy double - the change in position on the `y`
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    //methods:

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p Point
     * @return Point - the point after the new position
     */
    public Point applyToPoint(Point p) {
        Point point = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return point;

    }

    /**
     * this function is kind of a constructor, but in this method velocity terms are angle and speed.
     * this function creates new  Velocity's instances, instead of the constructor.
     *
     * @param angle double - the direction of the velocity
     * @param speed double - the units of the velocity
     * @return Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radAngle = Math.toRadians(angle - 90); //convert degrees to radians
        double dx = Math.cos(radAngle) * speed;
        double dy = Math.sin(radAngle) * speed;
        return new Velocity(dx, dy);
    }
}
