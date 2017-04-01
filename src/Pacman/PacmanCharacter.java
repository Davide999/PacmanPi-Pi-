package Pacman;

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.Vector;

public class PacmanCharacter implements ActionListener {

    // singleton instance
    public static PacmanCharacter instance = new PacmanCharacter();

    // images of the sprite
    private Image[] images;
    private int currentImage = 0;
    public final int speed = 3;
    private int points = 0;
    private int deltaCurrentImage = 1;
    
    // position and movement
    private int radius = 125;
    //private int vert = 250;
    private int deltaRadius = 0;
    //private int deltaVert = 0;
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

    public Circle getDimensionCircle() {
        return new Circle(radius, width, height);
    }

    public void moveAndHandleCollisions() {
        radius += deltaRadius;
        //vert += deltaVert;

        Vector<Food> foodVector = PlayCanvas.instance.getFoodVector();
        Vector<Obstacle> obstacleVector = PlayCanvas.instance.getObstacleVector();
        Vector<Ghost> ghostVector = PlayCanvas.instance.getGhostVector();

        Circle pacmanCirc = getDimensionCircle();

        for (Food f : foodVector) {
            if(pacmanCirc.intersects(f.getBounds())) {
                foodVector.remove(f);
                points += 10;
                System.out.println(points);
                break;
            }
        }

        for (Obstacle o : obstacleVector) {
            if(pacmanCirc.intersects(o.getBounds())) {
                die();
                break;
            }
        }
        
        for (Ghost g : ghostVector) {
            if(pacmanCirc.intersects(g.getBounds())) {
                die();
                break;
            }
        }
    }

    public int getRadius() {
        return radius;
    }

   /* public int getVert() {
        return vert;
    }*/

    public Image getCurrentImage() {
        return images[currentImage];
    }

    public void paintImage(Graphics2D buffer) {
        buffer.drawImage(images[currentImage], AffineTransform.getRotateInstance(radius), null);
    }

    public void handleKeyPressed(KeyEvent e) {
    	if(radius<10)
    	{
    		deltaRadius=0;
    		radius=10;
    	}
    	if(radius>PlayFrame.instance.getWidth()-width)
    	{
    		deltaRadius=0;
    		radius=PlayFrame.instance.getWidth()-width;
    	}
    	/*if(vert<10)
    	{
    		deltaVert=0;
    		vert=10;
    	}
    	if(vert>PlayFrame.instance.getHeight()-height)
    	{
    		deltaVert=0;
    		vert=PlayFrame.instance.getHeight()-height;
    	}*/
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                deltaRadius = -speed;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                deltaRadius = speed;
                break;
           /* case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                deltaVert = -speed;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                deltaVert = speed;
                break;*/
            // case KeyEvent.VK_SPACE: - future                        
        }
    }

    public void handleKeyReleased(KeyEvent e) {
    	if(radius<10)
    	{
    		deltaRadius=0;
    		radius=10;
    	}
    	if(radius>PlayFrame.instance.getWidth()-width)
    	{
    		deltaRadius=0;
    		radius=PlayFrame.instance.getWidth()-width;
    	}
    	/*if(vert<10)
    	{
    		deltaVert=0;
    		vert=10;
    	}
    	if(vert>PlayFrame.instance.getHeight()-height)
    	{
    		deltaVert=0;
    		vert=PlayFrame.instance.getHeight()-height;
    	}*/
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            case KeyEvent.VK_A:
                deltaRadius = 0;
                break;
            /*case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                deltaVert = 0;
                break;*/
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
    
    public void die() {
        this.morto = true;
        PlayCanvas.instance.stopGame();
    }
}
