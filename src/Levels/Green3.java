// 323133496 Eden Ben David

package Levels;

import Shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class can create Green3's objects and implement "LevelInformation" interface.
 */
public class Green3 implements LevelInformation {
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
     * constructor - creates Green3's object.
     */
    public Green3() {
        this.numberOfBalls = 2;
        this.levelName = "Green 3";
        this.paddleSpeed = 8;
        this.paddleWidth = 100;
        this.blocks = new java.util.ArrayList<View.Block>();
        this.createBlockPattern(blocks);
        this.initialBallVelocities = new ArrayList<Mechanic.Velocity>();
        this.initialBallVelocities.add(Mechanic.Velocity.fromAngleAndSpeed(315, 8));
        this.initialBallVelocities.add(Mechanic.Velocity.fromAngleAndSpeed(45, 8));

        Color color = new Color(150, 200, 100);
        this.background = new Background(color);
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
    public void createBlockPattern(java.util.ArrayList<View.Block> blocks) {
        double widthRect = 50;
        double heightRect = 15;
        // add regular blocks to the game:
        // row 1:
        Shapes.Point p1 = new Shapes.Point(WIDTH - widthRect - BORDER, 100);
        Shapes.Rectangle rect1 = new Shapes.Rectangle(p1, widthRect, heightRect);
        createRow(rect1, Color.GRAY, 12);

        // row 2 :
        Shapes.Point p2 = new Shapes.Point(WIDTH - widthRect - BORDER, 100 + heightRect);
        Shapes.Rectangle rect2 = new Shapes.Rectangle(p2, widthRect, heightRect);
        createRow(rect2, Color.red, 11);


        // row 3 :
        Shapes.Point p3 = new Shapes.Point(WIDTH - widthRect - BORDER, 100 + 2 * heightRect);
        Shapes.Rectangle rect3 = new Shapes.Rectangle(p3, widthRect, heightRect);
        createRow(rect3, Color.yellow, 10);

        // row 4 :
        Shapes.Point p4 = new Shapes.Point(WIDTH - widthRect - BORDER, 100 + 3 * heightRect);
        Shapes.Rectangle rect4 = new Shapes.Rectangle(p4, widthRect, heightRect);
        createRow(rect4, Color.blue, 9);


        // row 5 :
        Shapes.Point p5 = new Shapes.Point((WIDTH - widthRect - BORDER), 100 + 4 * heightRect);
        Shapes.Rectangle rect5 = new Shapes.Rectangle(p5, widthRect, heightRect);
        createRow(rect5, Color.PINK, 8);

        // row 6 :
        Shapes.Point p6 = new Shapes.Point(WIDTH - widthRect - BORDER, 100 + 5 * heightRect);
        Shapes.Rectangle rect6 = new Shapes.Rectangle(p6, widthRect, heightRect);
        createRow(rect6, Color.green, 7);

    }

    /**
     * side function - this function creates a single row of blocks.
     *
     * @param rectangle Rectangle
     * @param color     Color
     * @param size      int - size of the array
     */
    public void createRow(Rectangle rectangle, Color color, int size) {
        Shapes.Point firstRect = rectangle.getUpperLeft();

        for (int i = 0; i < size; i++) {
            Shapes.Point point = new Shapes.Point(firstRect.getX() - rectangle.getWidth() * i, firstRect.getY());
            rectangle = new Rectangle(point, rectangle.getWidth(), rectangle.getHeight());
            this.blocks.add(i, new View.Block(rectangle, color));
        }

    }
}
