/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstacle {
    private int x, y;
    private Image image;
    private boolean visible;
    private int larghezza;
    private int altezza;

    private final static int VELOCITA_OSTACOLI = -4;

    public Obstacle(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("sprites/blocchi/blocchi.png"));
        image = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        larghezza = image.getWidth(null);
        altezza = image.getHeight(null);
    }

    public Bounds getBounds() {
        return new BoundingBox(x, y, larghezza, altezza);
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
        if (x < -70)
            visible = false;
    }

}

