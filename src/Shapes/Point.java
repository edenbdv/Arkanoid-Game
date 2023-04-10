// 323133496 Eden Ben David
package Shapes;


/**
 * this class can create Point's objects ,measure the distance to other points, and if it is equal to another point.
 */
public class Point {
    //Fields:
    private double x;
    private double y;

    /**
     * this function is the constructor.
     *
     * @param x double - coordinate of the point on X axis.
     * @param y double - coordinate of the point on Y axis.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    // accessors:

    /**
     * this function allows get to the field "x" in class Point from outside the class.
     *
     * @return double - the x value of this point
     */
    public double getX() {
        return x;
    }

    /**
     * this function allows get to the field "y" in class Point from outside the class.
     *
     * @return double -the y value of this point
     */
    public double getY() {
        return y;
    }


    // setters:

    /**
     * this function allow set the field "x" in class Point from outside the class.
     *
     * @param x double - the x value of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * this function allow set the field "y" in class Point from outside the class.
     *
     * @param y double - the y value of this point
     */
    public void setY(double y) {
        this.y = y;
    }

    //Methods:

    /**
     * this function returns the distance of this point to the other point.
     *
     * @param other Point
     * @return double - the distance of this point to the other point.
     */
    public double distance(Point other) {
        double x1 = this.x;
        double y1 = this.y;
        double x2 = other.x;
        double y2 = other.y;
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * this function returns check if two points are equal.
     *
     * @param other Point
     * @return boolean - true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        // in case we checked the intersection point and got null (isn't exist)
        if (other == null) {
            return false;
        }
        if ((this.x == other.x) && (this.y == other.y)) {
            return true;
        }
        return false;
    }


}


