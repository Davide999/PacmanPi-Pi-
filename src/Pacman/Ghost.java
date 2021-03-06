/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import java.awt.*;
import javax.swing.ImageIcon;


public class Ghost extends Thing {
    private int x, y;
    private Image image;
    private boolean visible;
    private int larghezza;
    private int altezza;
    private int v_y = 1;

    private static int VELOCITA_FANTASMI = -5;

    public Ghost(int x, int y) {
    	int nGhost=(int) (Math.floor(Math.random()*4)+1);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("sprites/ghost/ghost"+nGhost+".png"));
        image = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        this.v_y = (int) Math.floor(Math.random() * 5);
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
        x += VELOCITA_FANTASMI;
        if (x < -70)
            visible = false;
        this.y += this.v_y;
        if (y > PlayFrame.instance.getHeight() - altezza || y < 0) {
            this.v_y *= -1;
        }
    }

    @Override
    public void paintImage(Graphics2D buffer) {
        buffer.drawImage(image, x, y, null);
    }

    public static void changeSpeed(int i) {
        VELOCITA_FANTASMI = i - 2;
        if (Music.instance.getVelocita() == 0) {
            VELOCITA_FANTASMI = -3;
        }
    }
}

