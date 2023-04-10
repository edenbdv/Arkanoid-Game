// 323133496 Eden Ben David

package Mechanic;

/**
 * this interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * this function returns the "collision shape" of the object.
     * @return Rectangle - "collision shape" of the object
     */
    Shapes.Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter - Ball
     * @param collisionPoint - Point
     * @param currentVelocity - Velocity
     * @return Velocity
     */
    Velocity hit(Shapes.Ball hitter, Shapes.Point collisionPoint, Velocity currentVelocity);
}
