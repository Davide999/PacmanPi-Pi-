package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PacmanCharacter {

    // singleton instance
    public static PacmanCharacter instance = new PacmanCharacter();

    // images of the sprite
    private Image[] images;
    private int currentImage = 0;
    private int horiz = 300;

    // position and movement
    private int vert = 400;
    private int deltaHoriz = 0;
    private int deltaVert = 0;
    private int width;
    private int height;

    private PacmanCharacter() {
        // number of images for the sprite
        final int numImages = 13;

        // initialize images array
        images = new Image[numImages];
        for (int i = 0; i < numImages; i++)
            images[i] = new ImageIcon(this.getClass().getResource("sprites/pacman/pacman0.png"))
                    .getImage();

        // set character dimension
        width = images[0].getWidth(null);
        height = images[0].getHeight(null);
    }

    public Rectangle getDimensionRectangle() {
        return new Rectangle(horiz, vert, width, height);
    }


    public void move() {
        horiz += deltaHoriz;
        vert += deltaVert;
        System.out.println(horiz + " " + vert);
    }

    public int getHoriz() {
        return horiz;
    }

    public int getVert() {
        return vert;
    }

    public Image getCurrentImage() {
        return images[currentImage];
    }

    public void handleKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                deltaHoriz = -2;
                break;
            case KeyEvent.VK_RIGHT:
                deltaHoriz = 2;
                break;
            case KeyEvent.VK_UP:
                deltaVert = -2;
                break;
            case KeyEvent.VK_DOWN:
                deltaVert = 2;
                break;
            // case KeyEvent.VK_SPACE: - future
        }
    }

    public void handleKeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                deltaHoriz = 0;
                break;
            case KeyEvent.VK_RIGHT:
                deltaHoriz = 0;
                break;
            case KeyEvent.VK_UP:
                deltaVert = 0;
                break;
            case KeyEvent.VK_DOWN:
                deltaVert = 0;
                break;
        }
    }
}
