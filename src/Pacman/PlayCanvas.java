package Pacman;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Collisioni.Asteroide;
import Collisioni.Esplosione;

public class PlayCanvas extends java.awt.Canvas
        implements ActionListener {

    public static PlayCanvas instance = new PlayCanvas();
    private PacmanCharacter pacmanCharacter;
    private int scrollPosition = 0;
    private int scrollPosition2 = 0;
    private int scrollPosition3 = 0;
    private int scrollPosition4 = 0;
    private int livelloPunti;
    private int generaPunti;
    private Esplosione esplosione;
    private Vector<Food> foodVettore;
    public final int REFRESH_TIME = 6;
    private Timer timer; // timeout

    private PlayCanvas() {
        foodVettore = new Vector<>();
        livelloPunti = 1;
        generaPunti = 0;
        // start timer timeout
        timer = new Timer(REFRESH_TIME, this);
        timer.start();
    }

    public int getScrollPosition() {
        return scrollPosition;
    }

    public int getScrollPosition2() {
        return scrollPosition2;
    }

    public int getScrollPosition3() {
        return scrollPosition3;
    }
    
    public int getScrollPosition4() {
        return scrollPosition4;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);

        //si gestisce lo spazio da memorizzare nel buffer, ricavandolo da tutta l'area visualizzabile
        Image workspace = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) workspace.getGraphics();

        //si disegnano gli elementi nel buffer esterno
        Image sfondo = new ImageIcon(this.getClass().getResource("sprites/background/bup.png")).getImage();
        buffer.drawImage(sfondo, 0, 0, this);

        scrollPosition += 3;
        scrollPosition2 += 2;
        scrollPosition3 += 1;
        scrollPosition4 += 3;
        // draws background according to pacman
        Background.instance.paintImage(buffer);

        // moves pacman and draws it
        PacmanCharacter.instance.move();
        PacmanCharacter.instance.paintImage(buffer);

        //Gesione punti
        for (Food p : foodVettore) {
            buffer.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }

        //si visualizza l'immagine del buffer esterno
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(workspace, 0, 0, this);

        //per liberare spazio in memoria si elimina l'immagine precedentemente memorizzata
        buffer.dispose();
    }

    /**
     * Updates background each time the timer expires
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	PacmanCharacter.instance.move();
        creaPunti();
        for (int i = 0; i < foodVettore.size(); i++) {
            Food p = foodVettore.get(i);
            if (p.isVisible()) {
                p.move();
            } else {
                foodVettore.remove(i);
            }
        }
        rilevaCollisioni();
        repaint();
    }
    
    private void rilevaCollisioni() {
    	/*if (!pacmanCharacter.isEsplosa()){
            Rectangle ra=pacmanCharacter.getDimensione();
     
             for (int i=0;i<foodVettore.size();i++){
                Food p=foodVettore.get(i);
                Rectangle rs=p.getDimensione();
               if (ra.intersects(rs)){
            	   pacmanCharacter.setMorte(true);
                   esplosione=new Esplosione(astronave.getX(), astronave.getY());
               }
       }
    
     }*/
}

    private void creaPunti() {
        generaPunti++;
        if (generaPunti >= 100 - ((livelloPunti + 1) * 10)) {
            int y_p = (int) (Math.random() * PlayFrame.instance.getHeight());
            Food f = new Food(PlayFrame.instance.getWidth(), y_p);
            foodVettore.add(f);
            generaPunti = 0;
        }
    }
}
