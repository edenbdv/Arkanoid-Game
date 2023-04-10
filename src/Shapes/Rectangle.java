// 323133496 Eden Ben David

package Shapes;


import java.util.List;
import java.util.ArrayList;

/**
 * this class can create Rectangle's objects, return the rectangle's intersections points with other lines
 * and check if a specific point found on one of the rectangle's lines.
 */
public class Rectangle {

    //Fields:
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] rectLines;

    /**
     * constructor - Create a new rectangle with location and width/height.
     *
     * @param upperLeft Point
     * @param width     double
     * @param height    double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.rectLines = this.linesOfRectangle();
    }

    // accessors:

    /**
     * this function allows get to the field "width" in class Rectangle from outside the class.
     *
     * @return double - width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * this function allows get to the field "height" in class Rectangle from outside the class.
     *
     * @return double- height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * this function allows get to the field "upperLeft" in class Rectangle from outside the class.
     *
     * @return Point -upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * this function allows get to the field "rectLines" in class Rectangle from outside the class.
     * @return Line[] - array of rectangle's lines
     */
    public Line[] getRectLines() {
        return this.rectLines;
    }

    /**
     * this function returns a (possibly empty) List of intersection points with the specified line.
     *
     * @param line Line
     * @return List of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        Line[] rectLines = linesOfRectangle();
        Point p;
        for (int i = 0; i < rectLines.length; i++) {
            if (rectLines[i].isIntersecting(line)) {
                p = rectLines[i].intersectionWith(line);
                if (p != null) {
                    list.add(p);
                }
            }
        }
        return list;
    }

    /**
     * side function - create an array of border's lines of the rectangle.
     * the order of the lines in the array is: {left,right,top,bottom}
     *
     * @return Line[] - array of Line's objects
     */
    public Line[] linesOfRectangle() {
        Line[] lines = new Line[4];
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
        // the vertical lines:
        lines[0] = new Line(this.upperLeft, lowerLeft);
        lines[1] = new Line(upperRight, lowerRight);
        // the horizontal lines:
        lines[2] = new Line(upperLeft, upperRight);
        lines[3] = new Line(lowerLeft, lowerRight);
        return lines;
    }
    /**
     *  side function - this function check if the point is on one of the rectangle's lines.
     *  the function returns true if the point found on one of the line, otherwise- false
     * @param rectLines Line[] - array of rectangle's lines
     * @param index int - index in array of the line
     * @param point Point
     * @return boolean - true if the point found on one of the line, otherwise- false
     */
    public boolean isOnRectangleLine(Line[] rectLines, int index, Point point) {

            if (index < 2) { //vertical line
                if ((point.getX() == rectLines[index].start().getX())
                        && (rectLines[index].isInBorderLine(point))) {
                    return true;
                }
            } else { //horizontal line
                if ((point.getY() == rectLines[index].start().getY())
                        && (rectLines[index].isInBorderLine(point))) {
                    return true;
                }
            }
       return  false;
    }
}
