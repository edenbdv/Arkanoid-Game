// 323133496 Eden Ben David

package View;

import Mechanic.HitListener;

/**
 * this class can create BallRemover's objects and implement "HitListener" interface.
 */
public class BallRemover implements HitListener {
    private Mechanic.GameLevel gameLevel;
    private View.Counter remainingBalls;

    /**
     * constructor - creates BallRemover's object by game and remainingBalls.
     * @param gameLevel - Game
     * @param remainingBalls - Counter
     */
    public BallRemover(Mechanic.GameLevel gameLevel, View.Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;

    }

    /**
     * this function removes from the game balls that hit the "death region".
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - Block
     * @param hitter   - Ball
     */
    @Override
    public void hitEvent(View.Block beingHit, Shapes.Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
