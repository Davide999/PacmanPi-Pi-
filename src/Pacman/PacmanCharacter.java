package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class PacmanCharacter implements ActionListener {

    // singleton instance
    public static PacmanCharacter instance = new PacmanCharacter();

    // images of the sprite
    private Image[] images;
    private int currentImage = 0;
    public final int speed = 2;
    private int points = 0;
    private int deltaCurrentImage = 1;
    
    // position and movement
    private int horiz = 200;
    private int vert = 250;
    private int deltaHoriz = 0;
    private int deltaVert = 0;
    private int width;
    private int height;
    private boolean morto=false;

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
        Timer timer = new Timer(33, this);
        timer.start();
    }

    public Rectangle getDimensionRectangle() {
        return new Rectangle(horiz, vert, width, height);
    }

    public void moveAndHandleCollisions() {
        horiz += deltaHoriz;
        vert += deltaVert;

        Vector<Food> foodVector = PlayCanvas.instance.getFoodVector();

        Rectangle pacmanRect = getDimensionRectangle();

        for (Food f : foodVector) {
            if(f.getDimensionRectangle().intersects(pacmanRect)) {
                foodVector.remove(f);
                points += 10;
                System.out.println(points);
                break;
            }
        }
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

    public void paintImage(Graphics2D buffer) {
        buffer.drawImage(images[currentImage], horiz, vert, null);
    }

    public void handleKeyPressed(KeyEvent e) {
    	if(horiz<10)
    	{
    		deltaHoriz=0;
    		horiz=10;
    	}
    	if(horiz>PlayFrame.instance.getWidth()-width)
    	{
    		deltaHoriz=0;
    		horiz=PlayFrame.instance.getWidth()-width;
    	}
    	if(vert<10)
    	{
    		deltaVert=0;
    		vert=10;
    	}
    	if(vert>PlayFrame.instance.getHeight()-height)
    	{
    		deltaVert=0;
    		vert=PlayFrame.instance.getHeight()-height;
    	}
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                deltaHoriz = -speed;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                deltaHoriz = speed;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                deltaVert = -speed;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                deltaVert = speed;
                break;
            // case KeyEvent.VK_SPACE: - future                        
        }
    }

    public void handleKeyReleased(KeyEvent e) {
    	if(horiz<10)
    	{
    		deltaHoriz=0;
    		horiz=10;
    	}
    	if(horiz>PlayFrame.instance.getWidth()-width)
    	{
    		deltaHoriz=0;
    		horiz=PlayFrame.instance.getWidth()-width;
    	}
    	if(vert<10)
    	{
    		deltaVert=0;
    		vert=10;
    	}
    	if(vert>PlayFrame.instance.getHeight()-height)
    	{
    		deltaVert=0;
    		vert=PlayFrame.instance.getHeight()-height;
    	}
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
    
    public boolean isEsplosa() {
        return morto;
    }
    
    public void setMorte(boolean morto) {
        this.morto = morto;
    }
}
