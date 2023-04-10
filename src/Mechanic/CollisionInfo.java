// 323133496 Eden Ben David

package Mechanic;
import Shapes.Point;

/**
 * this class create CollisionInfo's objects.
 */
public class CollisionInfo {

    //Fields:
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * constructor - this function create CollisionInfo's object from collision point and collidable object.
     * @param collisionPoint Point
     * @param collisionObject Collidable
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    //accessors:

    /**
     * this function allows get to the field "collisionPoint" in class CollisionInfo from outside the class.
     * @return Point - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * this function allows get to the field "collisionObject" in class CollisionInfo from outside the class.
     * @return Collidable - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}
