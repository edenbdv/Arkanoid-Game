// 323133496 Eden Ben David

package Levels;

import Mechanic.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 *  this class can create WideEasy's objects and implement "LevelInformation" interface.
 */
public class WideEasy implements LevelInformation {
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
     * constructor - creates FinalFour's object.
     */
    public WideEasy() {
        this.numberOfBalls = 10;
        this.paddleSpeed = 5;
        this.levelName = "Wide Easy";
        this.paddleWidth = WIDTH / 2;
        this.blocks = new java.util.ArrayList<View.Block>();
        this.createBlockPattern(blocks);
        this.initialBallVelocities = new ArrayList<Mechanic.Velocity>();
        for (int i = 0; i < numberOfBalls; i++) {
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(280 + (i * 18), +7));
        }
        this.background = new Background(java.awt.Color.pink);
        this.numberOfBlocksToRemove = this.blocks.size();
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
    public java.util.List<View.Block> blocks() {
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
    public void createBlockPattern(ArrayList<View.Block> blocks) {
        double widthRect = 100;
        double heightRect = 20;
        Shapes.Point p1 = new Shapes.Point(WIDTH - widthRect - BORDER, 200);
        Shapes.Rectangle rectangle = new Shapes.Rectangle(p1, widthRect, heightRect);
        Shapes.Point firstRect = rectangle.getUpperLeft();
        View.Block[] row = new View.Block[8];
        for (int i = 0; i < row.length; i++) {
            Shapes.Point point = new Shapes.Point(firstRect.getX() - rectangle.getWidth() * i, firstRect.getY());
            rectangle = new Shapes.Rectangle(point, rectangle.getWidth(), rectangle.getHeight());
            row[i] = new View.Block(rectangle, java.awt.Color.white);
        }
        for (View.Block block : row
        ) {
            this.blocks.add(block);
        }
    }
}
