// 323133496 Eden Ben David

package Levels;

import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {


    /**
     * this function returns the number of balls in the level.
     * @return int - number of balls
     */
    int numberOfBalls();

    /**
     * this function returns the initial velocity of each ball.
     *  Note that initialBallVelocities().size() == numberOfBalls()
     * @return  List of Velocities
     */
    List<Mechanic.Velocity> initialBallVelocities();

    /**
     * this function returns the paddle's speed in the level.
     * @return int - paddle's speed
     */
    int paddleSpeed();

    /**
     * this function returns the paddle's width in the level.
     * @return int - paddle's width
     */
    int paddleWidth();

    /**
     * this function returns the level name will be displayed at the top of the screen.
     * @return String
     */
    String levelName();

    /**
     * this function returns a sprite with the background of the level.
     * @return Sprite
     */
    View.Sprite getBackground();


    /**
     * this function returns the Blocks that make up this level, each block contains
     * its size, color and location.
     * @return List is Blocks
     */
    List<View.Block> blocks();


    /**
     * this function returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return int
     */
    int numberOfBlocksToRemove();

    /**
     * this function creates the block pattern in the level.
     * @param blocks - Blocks
     */
    void createBlockPattern(java.util.ArrayList<View.Block> blocks);

}
