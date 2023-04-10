// 323133496 Eden Ben David
package Mechanic;

import Levels.LevelInformation;
import Shapes.Ball;
import Shapes.Point;
import Shapes.Rectangle;
import View.AnimationRunner;
import View.Block;
import View.Counter;
import View.Indicators.LevelIndicator;
import View.Indicators.LivesIndicator;
import View.Paddle;
import View.Sprite;
import View.SpriteCollection;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * this class can create Game's objects, initialize and run them.
 */
public class GameLevel implements View.Animation {

    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int BORDER = 20;


    //Fields:
    private SpriteCollection sprites;
    private Mechanic.GameEnvironment environment;
    private Block[] bounds;
    private View.Counter remainingBlocks;
    private View.Counter remainingBalls;
    private Block deathRegion;
    private View.ScoreTrackingListener score;
    private View.Indicators.ScoreIndicator scoreIndicator;
    private View.AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private Levels.LevelInformation levelInfo;
    private LivesIndicator lives;
    private LevelIndicator levels;
    private KeyboardSensor keyboardSensor;


    /**
     * constructor -this function create Game's object.
     *
     * @param levelInfo       - LevelInformation
     * @param keyboardSensor  - KeyboardSensor
     * @param animationRunner - AnimationRunner
     * @param lives           - Counter
     * @param score           - Counter
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter lives, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new Mechanic.GameEnvironment();
        this.bounds = createBounds();
        this.remainingBlocks = new View.Counter(0);
        this.remainingBalls = new View.Counter(0);
        Point point = new Point(0, HEIGHT - 0.5);
        Rectangle rectangle = new Rectangle(point, WIDTH, 0.5);
        this.deathRegion = new Block(rectangle, java.awt.Color.darkGray);
        this.score = new View.ScoreTrackingListener(score);
        this.scoreIndicator = new View.Indicators.ScoreIndicator(this.score);
        this.runner = animationRunner;
        this.running = true;
        this.levelInfo = levelInfo;
        this.lives = new View.Indicators.LivesIndicator(lives);
        this.levels = new LevelIndicator(levelInfo);
        this.keyboardSensor = keyboardSensor;

    }


    // accessors:

    /**
     * this function allows get to the field "WIDTH" in class GAME from outside the class.
     *
     * @return WIDTH - int, the width of the game's frame.
     */
    public int getWIDTH() {
        return this.WIDTH;
    }

    /**
     * this function allows get to the field "HEIGHT" in class GAME from outside the class.
     *
     * @return HEIGHT - int,  the height of the game's frame.
     */
    public int getHEIGHT() {
        return this.HEIGHT;
    }

    /**
     * this function allows get to the field "BORDER" in class GAME from outside the class.
     *
     * @return BORDER - int, the size of the border between the frame's bounds to the area of the game.
     */
    public int getBORDER() {
        return this.BORDER;
    }

    /**
     * this function allows get to the field "runner" in class GAME from outside the class.
     *
     * @return runner - AnimationRunner
     */
    public View.AnimationRunner getRunner() {
        return runner;
    }


    /**
     * this function allows get to the field "deathRegion" in class GAME from outside the class.
     *
     * @return gui - GUI
     */
    public Block getDeathRegion() {
        return this.deathRegion;
    }


    /**
     * this function allows get to the field "remainingBalls" in class GAME from outside the class.
     *
     * @return remainingBalls - Counter
     */
    public View.Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * this function allows get to the field "remainingBlocks" in class GAME from outside the class.
     *
     * @return remainingBlocks - Counter
     */
    public View.Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    //methods:

    /**
     * this function adds the given collidable to the environment.
     *
     * @param c Collidable
     */
    public void addCollidable(Mechanic.Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * this function adds the given collidable to the environment.
     *
     * @param s Collidable
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * this function removes the given collidable from the environment.
     *
     * @param c Collidable
     */
    public void removeCollidable(Mechanic.Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * this function removes the given collidable from the environment.
     *
     * @param s Collidable
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * side function - this function create "bounds" of the frame:
     * it creates 4 blocks- one for each border.
     *
     * @return Block[] - array of blocks
     */
    public Block[] createBounds() {
        Block[] bounds = new Block[3];

        //upper side
        Point ps1 = new Point(0, 20);
        Rectangle rs1 = new Rectangle(ps1, WIDTH, 20);
        bounds[0] = new Block(rs1, java.awt.Color.gray);


        //right side
        Point ps2 = new Point(WIDTH - 20, 20);
        Rectangle rs2 = new Rectangle(ps2, 20, HEIGHT);
        bounds[1] = new Block(rs2, Color.gray);

        //left side
        Point ps3 = new Point(0, 20);
        Rectangle rs3 = new Rectangle(ps3, 20, HEIGHT);
        bounds[2] = new Block(rs3, Color.gray);

        return bounds;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and adds them to the game.
     */
    public void initialize() {
        View.BlockRemover blockRemover = new View.BlockRemover(this, this.remainingBlocks);
        this.deathRegion.addToGame(this);
        this.sprites.addSprite(this.levelInfo.getBackground());
        initIndicators();
        initPaddle();
        initBlocks(blockRemover);
        initBounds();
        View.BallRemover ballRemover = new View.BallRemover(this, this.remainingBalls);
        createBallsOnTopOfPaddle(this.levelInfo.numberOfBalls(), this.levelInfo.initialBallVelocities());
        //register the BallRemover class as a listener of the death-region.
        deathRegion.addHitListener(ballRemover);
    }


    /**
     * this function initialize the indicators of this game level.
     */
    void initIndicators() {
        lives.addToGame(this);
        levels.addToGame(this);
        this.scoreIndicator.addToGame(this);
    }


    /**
     * this function initialize the paddle of this game level.
     */
    void initPaddle() {
        int paddleSpeed = this.levelInfo.paddleSpeed();
        int paddleWidth = this.levelInfo.paddleWidth();
        Point paddlePoint = new Point((WIDTH / 2) - (paddleWidth / 2), HEIGHT - 2 * BORDER);
        Rectangle paddleRect = new Rectangle(paddlePoint, paddleWidth, 20);
        this.paddle = new Paddle(this, paddleRect, Color.ORANGE, this.levelInfo.paddleSpeed());
        this.paddle.addToGame(this);
    }


    /**
     * this function initialize the blocks of this game level.
     * @param blockRemover - BlockRemover
     */
    void initBlocks(View.BlockRemover blockRemover) {
        java.util.List<View.Block> blocks = this.levelInfo.blocks();
        for (Block block : blocks
        ) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            this.remainingBlocks.increase(1);
            block.addHitListener(this.score);
        }
    }

    /**
     * this function initialize the bounds of this game level.
     */
    void initBounds() {
        // add the bounds to the game
        for (Block bound : bounds
        ) {
            bound.addToGame(this);
        }
    }


    /**
     * this function creates and adds the ball to the game.
     *
     * @param num        - int
     * @param velocities - list of Velocities
     */
    public void createBallsOnTopOfPaddle(int num, List<Mechanic.Velocity> velocities) {
        for (int i = 0; i < num; i++) {
            Mechanic.Velocity velocity = this.levelInfo.initialBallVelocities().get(i);
            Ball ball = new Ball(WIDTH / 2,
                    this.paddle.getRectangle().getUpperLeft().getY() - 10, 5, Color.white,
                    this.environment, paddle, this);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);

            this.remainingBalls.increase(1);
        }
    }

    /**
     * this function run this GameLevel.
     */
    public void run() {
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * this function runs One Frame of the animation.
     *
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(biuoop.DrawSurface d) {
        long startTime = System.currentTimeMillis(); // timing

        if (this.paddle.getKeyboard().isPressed("p")) {
            this.runner.run(new View.KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new View.PauseScreen()));
        }

        // game-specific logic
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // stopping condition
        if (this.remainingBalls.getValue() == 0) {
            this.lives.decreaseRemainingLives();
            if (this.lives.getRemainingLives().getValue() == 0) {
                this.running = false;
                return;
            }
            rePlay();
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.running = false;
        }

    }


    /**
     * this function reset the paddle and the ball for the next turn.
     */
    public void rePlay() {
        createBallsOnTopOfPaddle(this.levelInfo.numberOfBalls(), this.levelInfo.initialBallVelocities());
        this.paddle.removeFromGame(this);
        initPaddle();
    }

    /**
     * this function returns if the animation need to be stopped.
     *
     * @return boolean
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}

