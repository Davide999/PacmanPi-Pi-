/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Ostacoli {
    private int x, y;
    private Image image;
    private boolean visible;
    private int larghezza;
    private int altezza;

    private final static int VELOCITA_OSTACOLI = -4;

    public Ostacoli(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("sprites/ghost/ghost1.png"));
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
        x += VELOCITA_OSTACOLI;
        if (x < 0)
            visible = false;
    }

}

