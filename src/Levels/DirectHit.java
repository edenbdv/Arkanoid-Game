// 323133496 Eden Ben David

package Levels;

import Shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class can create DirectHit's objects and implement "LevelInformation" interface.
 */
public class DirectHit implements LevelInformation {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int BORDER = 20;

    private int numberOfBalls;
    private String levelName;
    private int paddleSpeed;
    private int paddleWidth;
    private ArrayList<View.Block> blocks;
    private List<Mechanic.Velocity> initialBallVelocities;
    private View.Sprite background;
    private int numberOfBlocksToRemove;

    /**
     * constructor - creates DirectHit's object.
     */
    public DirectHit() {
        this.numberOfBalls = 1;
        this.paddleSpeed = 10;
        this.levelName = "Direct Hit";
        this.paddleWidth = 100;
        this.blocks = new java.util.ArrayList<View.Block>();
        this.createBlockPattern(blocks);
        this.initialBallVelocities = new ArrayList<Mechanic.Velocity>();
        this.initialBallVelocities.add(new Mechanic.Velocity(0, -5));
        this.background = new Background(java.awt.Color.black);
        this.numberOfBlocksToRemove = 1;

    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * this function returns the initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return List of Velocities
     */
    @Override
    public java.util.List<Mechanic.Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * this function returns the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * this function returns a sprite with the background of the level.
     *
     * @return Sprite
     */
    @Override
    public View.Sprite getBackground() {
        return this.background;
    }

    /**
     * this function returns the Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return List is Blocks
     */
    @Override
    public List<View.Block> blocks() {
        return this.blocks;
    }

    /**
     * this function returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }


    @Override
    public void createBlockPattern(java.util.ArrayList<View.Block> blocks) {
        double widthRect = 30;
        double heightRect = 30;

        Shapes.Point centerP = new Shapes.Point((WIDTH / 2) - (widthRect / 2), 100);
        Shapes.Rectangle rect = new Rectangle(centerP, widthRect, heightRect);
        View.Block block = new View.Block(rect, Color.RED);
        blocks.add(block);
    }

}
