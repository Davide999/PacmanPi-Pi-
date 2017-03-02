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
    private Vector montagne;
    private Timer timer; // timeout
    private int livello;
    private int genera;
    private PlayCanvas() {
        // start timer timeout
        timer = new Timer(4, this);
        timer.start();
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
        
        buffer.drawImage(PacmanCharacter.instance.getCurrentImage(),
                PacmanCharacter.instance.getHoriz(),
                PacmanCharacter.instance.getVert(),
                this);
        
        for (int i=0;i<montagne.size();i++){
            Sfondi a=(Sfondi)montagne.get(i);
            buffer.drawImage(a.getImage(), a.getX(), a.getY(), this);
        }
        
        //si visualizza l'immagine del buffer esterno
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(workspace, 0, 0, this);
        //per liberare spazio in memoria si elimina l'immagine precedentemente memorizzata
        buffer.dispose();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	creaAsteroidi();
        
        for (int i=0;i<montagne.size();i++){
        	Sfondi a=(Sfondi)montagne.get(i);
            if (a.isVisible()) {
                a.move();
            }
            else {
                montagne.remove(i);
            }
        PacmanCharacter.instance.move();
        repaint();
    }
    }
    
        private void creaAsteroidi() {
            genera++;
            
            if (genera>=100-((livello+1)*10)){
                int x_a=(int) (Math.random()*getWidth());
                montagne.add(new Asteroide(x_a,0));
                genera=0;
            }
            
        }
   



}
