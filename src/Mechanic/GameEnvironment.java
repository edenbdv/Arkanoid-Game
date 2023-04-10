// 323133496 Eden Ben David

package Mechanic;

import java.util.List;
import java.util.ArrayList;
import Shapes.Line;
import Shapes.Point;

/**
 * this class is the collection of collidables objects.
 */
public class GameEnvironment {

    //Fields
    private List<Collidable> collidables;

    /**
     * constructor - create new array of collidables objects.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    // accessors:

    /**
     * this function allows get to the field "collidables" in class GameEnvironment from outside the class.
     *
     * @return list of collidables.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    // methods:

    /**
     * this function adds the given collidable to the environment.
     *
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * this function removes the given collidable from the environment.
     *
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * this function assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory Line
     * @return CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidables.isEmpty()) {
            return null;
        }
        // initialize closetPoint to the closest intersection
        Point closestPoint = null;
        int closetRectangleIndex = 0;
        for (int i = 0; i < collidables.size(); i++) {
            closestPoint = trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
            if (closestPoint != null) {
                closetRectangleIndex = i;
                break;
            }
        }
        // check if there aren't any collision points
        if (closestPoint == null) {
            return null;
        }

        Point currentPoint = closestPoint;


        for (int i = 1; i < collidables.size(); i++) {
            Point p = trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
            if (p != null) {
                currentPoint = p;
            }
            // check if the currentPoint is more close to start of the line than closestPoint
            if (trajectory.start().distance(currentPoint) < trajectory.start().distance(closestPoint)) {
                closestPoint = currentPoint;
                closetRectangleIndex = i;
            }
        }
        return new CollisionInfo(closestPoint, collidables.get(closetRectangleIndex));

    }

}
