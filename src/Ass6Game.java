// 323133496 Eden Ben David

import Mechanic.GameFlow;
import View.AnimationRunner;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is the main class of assignment 5.
 */
public class Ass6Game {
    /**
     * this function is the main.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(ar, ks);

        //create array with all off the levels.
        List<Levels.LevelInformation> levelsArr = new ArrayList<Levels.LevelInformation>();
        levelsArr.add(new Levels.DirectHit());
        levelsArr.add(new Levels.WideEasy());
        levelsArr.add(new Levels.Green3());
        levelsArr.add(new Levels.FinalFour());

        // create empty array - it will be filled with the additional arguments, and if there aren't it will be filled
        // with all the levels in their regular order.
        List<Levels.LevelInformation> levelsOrder = new java.util.ArrayList<Levels.LevelInformation>();

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if ((args[i].length() == 1) && (Character.isDigit(args[i].charAt(0)))) {
                    int index = Integer.parseInt(args[i]);
                    if (index >= 1 && index <= 4) {
                        levelsOrder.add(levelsArr.get(index - 1));
                    }
                }
            }
        }
        if(levelsOrder.isEmpty()) {
            levelsOrder.addAll(levelsArr);
        }
        gameFlow.runLevels(levelsOrder);

    }

}
