/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import java.awt.*;
import javax.swing.ImageIcon;

public class Obstacle implements Movable, Drawable, Boundable {
    private int x, y;
    private Image image;
    private boolean visible;
    private int larghezza;
    private int altezza;

    private final static int VELOCITA_OSTACOLI = -4;

    public Obstacle(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("sprites/obstacle/obstacle.png"));
        image = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        larghezza = image.getWidth(null);
        altezza = image.getHeight(null);
    }

    @Override
    public Bounds getBounds() {
        return new BoundingBox(x, y, larghezza, altezza);
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void move() {
        x += VELOCITA_OSTACOLI;
        if (x < -70)
            visible = false;
    }

    @Override
    public void paintImage(Graphics2D buffer) {
        buffer.drawImage(image, x, y, null);
    }
}

