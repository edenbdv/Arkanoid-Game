// 323133496 Eden Ben David

package Shapes;

import java.util.List;



/**
 * this class can create Line's objects, measure length, middle, start and end of the line.
 * In addition, can check if line is intersect with other line,
 * calculate the intersection point and check if the lines are equal/
 */
public class Line {
    //Fields:
    private Point start;
    private Point end;
    private boolean isParallelYaxis;

    // constructors

    /**
     * constructor 1 - creates line from the points.
     *
     * @param start Point - includes x and y attributes of start of the line.
     * @param end   Point - includes x and y attributes of end of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        if (start.getX() == end.getX()) { //in case we got the same points or parallel line to y's axis
            if (start.getY() == end.getY()) { //the same point
                this.end = new Point(end.getX(), end.getY() + 1);
                this.isParallelYaxis = true;
            } else {
                // the line is parallel to y's axis
                this.end = end;
                this.isParallelYaxis = true;
            }

        } else {
            this.end = end;
            this.isParallelYaxis = false;
        }
    }

    /**
     * constructor 2 - creates line from 4 coordinates.x1,y1 for start's point and the x2,y2 for end's point.
     *
     * @param x1 double - coordinate of start's point on X axis.
     * @param y1 double - coordinate of start's point on Y axis.
     * @param x2 double - coordinate of end's point on X axis.
     * @param y2 double - coordinate of end's point on Y axis.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        if (x1 == x2) { //in case we got the same points or parallel line to y's axis
            if (y1 == y2) { //the same point
                this.end = new Point(x2, y2 + 1);
                this.isParallelYaxis = true;
            } else {
                // the line is parallel to y's axis
                this.end = new Point(x2, y2);
                this.isParallelYaxis = true;
            }
        } else {
            this.end = new Point(x2, y2);

            this.isParallelYaxis = false;
        }
    }


    //accessors:

    /**
     * this function returns the start point of the line.
     *
     * @return Point - the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * this function returns the end point of the line.
     *
     * @return Point - the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * this function returns if the line is parallel to y's axis.
     *
     * @return boolean -  true if the line is parallel to y's axis, false otherwise.
     */
    public boolean isParallelYaxis() {
        return this.isParallelYaxis;
    }

    //Methods:

    /**
     * this function calculate the distance between the start and the end of the line and returns the length of it.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this function returns the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2.0;
        double y = (this.start.getY() + this.end.getY()) / 2.0;
        Point point = new Point(x, y);
        return point;
    }


    /**
     * side function - returns the slope of this line.
     *
     * @return double - the slope of the line.
     */
    public double slope() {
        if (this.isParallelYaxis) {
            return 0;
        }
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * side function - returns the b value of the equation y=mx+b of the line.
     *
     * @return double - b value of the equation y=mx+b of the line.
     */
    public double bLine() {
        if (this.isParallelYaxis) {
            return 0;
        }
        return this.start.getY() - this.slope() * this.start.getX();
    }

    /**
     * side function - returns true if the point is in the border's line, false otherwise.
     *
     * @param point - Point
     * @return true if the point is in the segment(between start's point and end's point), false otherwise.
     */
    public boolean isInBorderLine(Point point) {
        //check if in line borders:
        // x borders:
        if ((point.getX() > Math.max(this.start.getX(), this.end.getX()))
                || (point.getX() < Math.min(this.start.getX(), this.end.getX()))) {
            return false;
        }
        //y borders:
        if ((point.getY() > Math.max(this.start.getY(), this.end.getY()))
                || (point.getY() < Math.min(this.start.getY(), this.end.getY()))) {
            return false;
        }
        return true;
    }

    /**
     * side function - this function put the points in the line by their order.
     *
     * @return Line
     */
    public Line pointByOrder() {
        Point start, end;
        if (this.start.getX() > this.end.getX()) {
            start = new Point(this.end.getX(), this.end.getY());
            end = new Point(this.start.getX(), this.start.getY());
            return new Line(start, end);
        } else {
            return this;
        }
    }

    /**
     * side function - this function check if one line contained by the other.
     *
     * @param other - Line
     * @return boolean - true if one line contained by the other, false otherwise
     */
    public boolean isConsolidated(Line other) {
        Line l1 = this.pointByOrder();
        Line l2 = other.pointByOrder();
        if (l1.slope() == l2.slope()) {
            if (l1.start.equals(l2.start) && !(l1.end.equals(l2.end))) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * this function returns true if the lines intersect, false otherwise.
     *
     * @param other Line
     * @return boolean - true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {

        if (!(this.isParallelYaxis && other.isParallelYaxis)) {
            if ((Math.max(this.start.getX(), this.end.getX()) < Math.min(other.start.getX(), other.end.getX()))
                    || (Math.min(this.start.getX(), this.end.getX())
                    > Math.max(other.start.getX(), other.end.getX()))) {

                // the segments have to be strangers to each other
                return false;
            }
        }

        if (this.isParallelYaxis && other.isParallelYaxis) {
            if (this.start.getX() == other.start.getX()) {
                // the segments are on the same line, need to check if they are consolidated.
                if ((Math.max(this.start.getY(), this.end.getY()) < Math.min(other.start.getY(), other.end.getY()))
                        || (Math.min(this.start.getY(), this.end.getY())
                        > Math.max(other.start.getY(), other.end.getY()))) {
                    // the segments have to be strangers to each other
                    return false;
                }
                // he segments are on the same line and consolidated.
                return true;
            }
            // the segments are parallel
            return false;
        }
        //in case only one of the line os parallel to y's axis
        if ((this.isParallelYaxis && !(other.isParallelYaxis)) || (!(this.isParallelYaxis) && other.isParallelYaxis)) {
            double x, y;
            if (this.isParallelYaxis && !(other.isParallelYaxis)) {
                x = this.start.getX();
                y = other.slope() * x + other.bLine();
                if ((Math.max(this.start.getY(), this.end.getY()) < y)
                        || (y < Math.min(this.start.getY(), this.end.getY()))) {
                    return false;
                }
            } else if ((!(this.isParallelYaxis) && other.isParallelYaxis)) {
                x = other.start.getX();
                y = this.slope() * x + this.bLine();
                if ((Math.max(other.start.getY(), other.end.getY()) < y)
                        || (y < Math.min(other.start.getY(), other.end.getY()))) {
                    return false;
                }
            }
            return true;
        }

        // the lines are not parallel to y's axis
        if (this.slope() == other.slope()) {
            if (this.bLine() == this.bLine()) {
                // in case the segments are on the same line and consolidated.
                return true;
            }
            // the segments are parallel
            return false;
        }

        // the lines have to intersect, but the segments not - we need to check it:
        Point point = this.intersectionWith(other);
        /* at this point of the function, if we got null at point, it because the intersection point is not in
        the border in at least one segment. */
        if (point == null) {
            return false;
        }
        return true;
    }


    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     *
     * @param other Line
     * @return the intersection point if the lines intersect,and null otherwise
     */
    public Point intersectionWith(Line other) {
        Point point;
        double x, y;
        double m1 = this.slope();
        double m2 = other.slope();
        double b1 = this.bLine();
        double b2 = other.bLine();

        if (this.isParallelYaxis && other.isParallelYaxis) {
            // check the case one segment is a direct continue of the other
            if ((this.end.getX() == other.start.getX()) && (this.end.getY() == other.start.getY())) {
                point = new Point(this.end.getX(), this.end.getY());
                return point;
            } else if ((this.start.getX() == other.end.getX()) && (this.start.getY() == other.end.getY())) {
                point = new Point(this.start.getX(), this.start.getY());
                return point;
            }

            // the segments are strangers/consolidated or equal
            return null;
        }

        // in case only one of them is parallel to y's axis:
        if (this.isParallelYaxis && !(other.isParallelYaxis)) {
            x = this.start.getX();
            y = m2 * x + b2;
            point = new Point(x, y);
        } else if (!(this.isParallelYaxis) && other.isParallelYaxis) {
            x = other.start.getX();
            y = m1 * x + b1;
            point = new Point(x, y);

            // none of the line is parallel to y's axis:
        } else if (m1 == m2) { /* in case the lines are parallel/equal/consolidated
                           or if one is contained/continuation of the other:*/

            // check the case the segments on the same line in the opposite side
            if ((this.start.getX() == other.end.getX()) && (this.start.getY() == this.end.getY())
                    && (this.end.getX() == other.start.getX()) && (this.end.getY() == other.start.getY())) {
                return null;
            }

            // check the case one segment is contained by the other
            if (this.isConsolidated(other)) {
                return null;
            }

            // check the case one segment is a direct continue of the other
            if ((this.end.getX() == other.start.getX()) && (this.end.getY() == other.start.getY())) {
                point = new Point(this.end.getX(), this.end.getY());
                return point;
            } else if ((this.start.getX() == other.end.getX()) && (this.start.getY() == other.end.getY())) {
                point = new Point(this.start.getX(), this.start.getY());
                return point;
            }
            // the segments are strangers/consolidated or equal
            return null;
        } else { //the basic case:
            // y1=y2 -> y1=m1*x+b1 , y2=m2*x+b2 -> m1*x+b1=m2*x+b2 -> (m1-m2)*x=(b2-b1) -> x=(b2-b1)/(m1-m2)
            x = (b2 - b1) / (m1 - m2);
            // substituting the value of x into the first line's equation
            y = m1 * x + b1;
            point = new Point(x, y);
        }

        if (this.isInBorderLine(point) && other.isInBorderLine(point)) {
            return point;
        }
        return null;
    }


    /**
     * this function returns true is the lines are equal, false otherwise.
     *
     * @param other Line
     * @return boolean - true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (((this.slope() == other.slope()) && (this.bLine() == other.bLine()))
                && ((this.start.equals(other.start) && this.end.equals(other.end)) || ((this.end.equals(other.start))
                && (this.start.equals(other.end))))) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect - Rectangle
     * @return Point - the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        }
        Point minPoint = list.get(0);
        double minDistance = list.get(0).distance(this.start);
        double currentDistance;
        for (int i = 0; i < list.size(); i++) {
            currentDistance = list.get(i).distance(this.start);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                minPoint = list.get(i);
            }
        }
        return minPoint;

    }
}


