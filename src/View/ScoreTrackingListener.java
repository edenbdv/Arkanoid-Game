// 323133496 Eden Ben David

package View;

/**
 * this class can create ScoreTrackingListener's objects and implement "HitListener" interface.
 */
public class ScoreTrackingListener implements Mechanic.HitListener {

    private View.Counter currentScore;

    /**
     * constructor - create ScoreTrackingListener instance according to scoreCounter.
     * @param scoreCounter
     */
    public ScoreTrackingListener(View.Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * this function allows get to the field "currentScore" in class ScoreTrackingListener from outside the class.
     * @return Counter
     */
    public View.Counter getCurrentScore() {
        return  this.currentScore;
    }

    /**
     * this function allow set the field "currentScore" in classScoreTrackingListener from outside the class.
     * @param score Counter
     */
    public void setCurrentScore(View.Counter score) {
        this.currentScore = score;
    }

    /**
     * this function This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - Block
     * @param hitter   - Ball
     */
    @Override
    public void hitEvent(View.Block beingHit, Shapes.Ball hitter) {
        this.currentScore.setCount(this.currentScore.getValue() + 5);

    }
}
