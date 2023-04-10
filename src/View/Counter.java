// 323133496 Eden Ben David

package View;

/**
 * this class is a counter, it can be increased and decreased.
 */
public class Counter {

    private int count;

    /**
     * constructor - creates Counter's object by count.
     * @param count  int - count
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * add number to current count.
     * @param number - int
     */
    public void increase(int number) {
        this.count += number;
        //System.out.println("counter:" + count);
    }

    /**
     *  subtract number from current count.
     * @param number - int
     */
    public void decrease(int number) {
        this.count -= number;
       // System.out.println("counter:" + count);
    }

    /**
     *  get current count.
     * @return count - int
     */
    public int getValue() {
        return this.count;
    }

    /**
     *  this function allow set the field "count" in class Counter from outside the class.
     * @param number - int
     */
    public void setCount(int number) {
        this.count = number;
    }


}
