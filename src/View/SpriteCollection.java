// 323133496 Eden Ben David

package View;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;


/**
 * this class is the collection of sprites objects.
 */
public class SpriteCollection {

    //Fields
    private List<Sprite> sprites;

    /**
     * constructor - create new array of sprites objects.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    // accessors:

    /**
     * this function allows get to the field "sprites" in  SpriteCollection class from outside the class.
     *
     * @return List of sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    // methods:

    /**
     * this function adds the given sprite to the sprites.
     *
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * this function removes the given sprite from the sprites.
     *
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * this function calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * this function call drawOn(d) on all sprites.
     *
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites
        ) {
            sprite.drawOn(d);
        }
    }


}