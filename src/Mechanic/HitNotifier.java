// 323133496 Eden Ben David

package Mechanic;

/**
 * The HitNotifier interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {

    /**
     * this function adds hl as a listener to hit events.
     * @param hl - HitListener
     */
    void addHitListener(HitListener hl);

    /**
     * this function removes hl from the list of listeners to hit events.
     * @param hl - HitListener
     */
    void removeHitListener(HitListener hl);

}
