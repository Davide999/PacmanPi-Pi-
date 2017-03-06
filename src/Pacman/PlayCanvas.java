package Pacman;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Pacman.Punti;

public class PlayCanvas extends java.awt.Canvas
        implements ActionListener {

    public static PlayCanvas instance = new PlayCanvas();
    private int livelloPunti;
    private int generaPunti;
    private Vector<Punti> PuntiVettore;
    private Timer timer; // timeout

    private PlayCanvas() {
    	
    	PuntiVettore=new Vector<Punti>();
        livelloPunti=1;
        generaPunti=0;
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
        Image img = new ImageIcon(this.getClass().getResource("../images/sfondo.jpg")).getImage();
        buffer.drawImage(img, 0, 0, this);

        buffer.drawImage(PacmanCharacter.instance.getCurrentImage(),
                PacmanCharacter.instance.getHoriz(),
                PacmanCharacter.instance.getVert(),
                this);
        
        
        //Gesione punti
        for (int i=0;i<PuntiVettore.size();i++){
            Punti p=(Punti)PuntiVettore.get(i);
            buffer.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }
        
        //si visualizza l'immagine del buffer esterno
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(workspace, 0, 0, this);
        //per liberare spazio in memoria si elimina l'immagine precedentemente memorizzata
        buffer.dispose();
    }

    //Timer
    @Override
    public void actionPerformed(ActionEvent e) {
    	creaPunti();
    	for (int i=0;i<PuntiVettore.size();i++){
            Punti p=(Punti)PuntiVettore.get(i);
            if (p.isVisible()) {
                p.move();
            }
            else {
                PuntiVettore.remove(i);
            }
        }
        PacmanCharacter.instance.move();
        repaint();
    }
    
    

    /*private void rilevaCollisioni() {

        if (!astronave.isEsplosa()) {
            Rectangle ra = astronave.getDimensione();

            for (int i = 0; i < asteroidi.size(); i++) {
                Asteroide a = (Asteroide) asteroidi.get(i);
                Rectangle rs = a.getDimensione();
                if (ra.intersects(rs)) {
                    astronave.setEsplosa(true);
                    esplosione = new Esplosione(astronave.getX(), astronave.getY());

                }
            }

        }

    }*/

    private void creaPunti() {
        generaPunti++;

        if (generaPunti >= 100 - ((livelloPunti + 1) * 10)) {
            int y_p = (int) (Math.random() * getHeight());
            PuntiVettore.add(new Punti(PlayFrame.instance.getWidth(), y_p));
            generaPunti = 0;
        }
    }
    

}
