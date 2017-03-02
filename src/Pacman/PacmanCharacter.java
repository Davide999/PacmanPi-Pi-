package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class PacmanCharacter implements ActionListener {

    // singleton instance
    public static PacmanCharacter instance = new PacmanCharacter();

    // images of the sprite
    private Image[] images;
    private int currentImage = 0;
    private int deltaCurrentImage = 1;
    
    // position and movement
    private int horiz = 200;
    private int vert = 250;
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
            images[i] = new ImageIcon(this.getClass().getResource("sprites/pacman/pacman"+i+".png"))
                    .getImage();

        // set character dimension
        width = images[0].getWidth(null);
        height = images[0].getHeight(null);

        // start timer timeout
        Timer timer = new Timer(30, this);
        timer.start();
    }

    public Rectangle getDimensionRectangle() {
        return new Rectangle(horiz, vert, width, height);
    }


    public void move() {
        horiz += deltaHoriz;
        vert += deltaVert;
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
            case KeyEvent.VK_A:
                deltaHoriz = -2;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                deltaHoriz = 2;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                deltaVert = -2;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                deltaVert = 2;
                break;
            // case KeyEvent.VK_SPACE: - future
        }
    }

    public void handleKeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            case KeyEvent.VK_A:
                deltaHoriz = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                deltaVert = 0;
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(currentImage == images.length-1) deltaCurrentImage = -1;
        if(currentImage == 0) deltaCurrentImage = 1;
        currentImage += deltaCurrentImage;
    }
}
