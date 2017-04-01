/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Ghost {
    private int x, y;
    private Image image;
    private boolean visible;
    private int larghezza;
    private int altezza;
    private int v_y = 2;
    
    private final static int VELOCITA_FANTASMI = -6;

    public Ghost(int x, int y) {
        Object o = this.getClass().getResource("sprites/ghost/ghost1.png");
        ImageIcon ii = new ImageIcon(this.getClass().getResource("sprites/ghost/ghost1.png"));
        image = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        this.v_y=(int) Math.floor(Math.random()*5);
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
        x += VELOCITA_FANTASMI;
        if (x < -70)
            visible = false;
        this.y += this.v_y;
        if(y>PlayFrame.instance.getHeight()-altezza || y<0)
        {
        	this.v_y*=-1;
        	
        }
    }
    
    
}

