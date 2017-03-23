/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Food {
    private int x, y;
    private Image image;
    private boolean visible;
    private int larghezza;
    private int altezza;

    private final static int VELOCITA_PUNTI = -4;

    public Food(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("sprites/Punti/food.png"));
        image = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        larghezza = image.getWidth(null);
        altezza = image.getHeight(null);
    }

    public Rectangle getDimensione() {
        return new Rectangle(x, y, larghezza, altezza);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void move() {
        x += VELOCITA_PUNTI;
        if (x < -70)
            visible = false;
    }

}

