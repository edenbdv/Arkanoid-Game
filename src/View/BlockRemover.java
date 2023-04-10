// 323133496 Eden Ben David

package View;

import Mechanic.HitListener;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Mechanic.GameLevel gameLevel;
    private View.Counter remainingBlocks;

    /**
     * constructor - creates BlockRemover's object by game and remainingBlocks.
     * @param gameLevel - Game
     * @param removedBlocks - Counter
     */
    public BlockRemover(Mechanic.GameLevel gameLevel, View.Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks =  removedBlocks;
    }

    /**
     * this function removes from the game blocks that are hit.
     * it's also removes this listener from the block that is being removed from the game.
     * @param beingHit - Block
     * @param hitter   - Ball
     */
    @Override
    public void hitEvent(View.Block beingHit, Shapes.Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
