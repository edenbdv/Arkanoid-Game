// 323133496 Eden Ben David

package Mechanic;

import Levels.LevelInformation;
import View.AnimationRunner;
import View.Counter;
import View.EndScreen;
import View.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * this class can create GameFlow's objects and run their levels.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter lives;
    private Counter score;
    private boolean isDead;

    /**
     * constructor -this function create GameFlow's object.
     * @param ar - AnimationRunner
     * @param ks - KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.lives = new Counter(7);
        this.score = new Counter(0);
        this.isDead = false;
    }


    /**
     * this function runs the levels of the game.
     * @param levels - list of LevelInformation
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor, this.animationRunner, this.lives, this.score);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }
            if (this.lives.getValue() == 0) {
                this.isDead = true;
                break;
            }

            this.score.increase(100);
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new EndScreen(!isDead, score.getValue())));

        animationRunner.getGui().close();


    }
}

