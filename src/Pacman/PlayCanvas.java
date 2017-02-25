package Pacman;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class PlayCanvas extends java.awt.Canvas
        implements ActionListener {

    public static PlayCanvas instance = new PlayCanvas();

    private Timer timer; // timeout

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
        Image img = new ImageIcon(this.getClass().getResource("../images/sfondo.jpg")).getImage();
        buffer.drawImage(img, 0, 0, this);

        buffer.drawImage(PacmanCharacter.instance.getCurrentImage(),
                PacmanCharacter.instance.getHoriz(),
                PacmanCharacter.instance.getVert(),
                this);

        //si visualizza l'immagine del buffer esterno
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(workspace, 0, 0, this);
        //per liberare spazio in memoria si elimina l'immagine precedentemente memorizzata
        buffer.dispose();
    }

    //Timer
    @Override
    public void actionPerformed(ActionEvent e) {
        PacmanCharacter.instance.move();
        repaint();
    }

    private void rilevaCollisioni() {

        /*if (!astronave.isEsplosa()) {
            Rectangle ra = astronave.getDimensione();

            for (int i = 0; i < asteroidi.size(); i++) {
                Asteroide a = (Asteroide) asteroidi.get(i);
                Rectangle rs = a.getDimensione();
                if (ra.intersects(rs)) {
                    astronave.setEsplosa(true);
                    esplosione = new Esplosione(astronave.getX(), astronave.getY());

                }
            }

        }*/

    }

    /*private void creaAsteroidi() {
        genera++;

        if (genera >= 100 - ((livello + 1) * 10)) {
            int x_a = (int) (Math.random() * getWidth());
            asteroidi.add(new Asteroide(x_a, 0));
            genera = 0;
        }

    }*/

}
