// 323133496 Eden Ben David

package Mechanic;

/**
 * this interface will be used by things Objects that want to be notified of hit events.
 */
public interface HitListener {

    /**
     * this function This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - Block
     * @param hitter - Ball
     */
     void hitEvent(View.Block beingHit, Shapes.Ball hitter);

}
