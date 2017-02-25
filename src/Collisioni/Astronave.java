/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Collisioni;

/**
 * Questa classe rappresenta l'astronave. 
 * Vengono gestite le immagini dell'astronave e le sue coordinate. 
 * I metodi keyPressed() and keyReleased() controllano se lo sprite � in movimento o fermo. 
 * @author depietro
 */

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.ImageIcon;

public class Astronave {

    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image[];
    private int numImmagine;
    private int larghezza;
    private int altezza;
    private boolean esplosa=false;
    
    //si aggiunge un Vector per gestire i missili sparati
    private Vector missili;

    public Astronave() {
        ImageIcon ii; 
        image=new Image[3];        
        ii=new ImageIcon(this.getClass().getResource("../Pacman/sprites/pacman/pacman0.png"));
        image[0]=ii.getImage();
        ii=new ImageIcon(this.getClass().getResource("../Pacman/sprites/pacman/pacman0.png"));
        image[1]=ii.getImage();
        ii=new ImageIcon(this.getClass().getResource("../Pacman/sprites/pacman/pacman0.png"));
        image[2]=ii.getImage();
        
        x = 300;
        y = 400;
        numImmagine=1;
        
        missili=new Vector();
    }
    
     public Rectangle getDimensione(){
       return new Rectangle(x,y,larghezza,altezza);
   }


    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
          larghezza=image[numImmagine].getWidth(null);
        altezza=image[numImmagine].getHeight(null);
        return image[numImmagine];
    }

    public Vector getMissili() {
        return missili;
    }
    
    

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
            numImmagine=0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
            numImmagine=2;
        }

        if (key == KeyEvent.VK_UP) {
            // decommentare per il movimento in alto
            
        	dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            // decommentare per il movimento in basso
            dy = 1;
        }
        
         if (key == KeyEvent.VK_SPACE) {
            // spazio per sparare missili
           spara();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            numImmagine=1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            numImmagine=1;
        }

        if (key == KeyEvent.VK_UP) {            
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    private void spara() {
        
        System.out.println("hai premuto il tasto SPAZIO");
      
    }

    public boolean isEsplosa() {
        return esplosa;
    }

    public void setEsplosa(boolean esplosa) {
        this.esplosa = esplosa;
    }
      
}

