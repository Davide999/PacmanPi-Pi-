/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Caricamento di immagini
 *
 * @author depietro
 */
public class Menu extends Canvas implements ActionListener {

    private Image robot[];
    private int x, y;
    private Timer timer;
    private int numImmagine;

    public Menu() {
        //prepara la finestra
        JFrame f = new JFrame("Main Menu: Pacman");
        f.setSize(600, 400);
        f.setResizable(false);
        f.setLocation(100, 100);
        f.add(this);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

        //timer
        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        //si gestisce lo spazio da memorizzare nel buffer, ricavandolo da tutta l'ara visualizzabile
        //in poche parole si crea uno spazio virtuale per l'immagine
        Image workspace = createImage(getWidth(), getHeight());
        Graphics buffer = workspace.getGraphics();

        //si disegnano gli elementi nel buffer esterno
        buffer.setColor(new Color(255, 0, 0));
        buffer.fillRect(0, 0, 100, 100);
        //Image img=getToolkit().getImage("image/montagne.png");
        //buffer.drawImage(img, 0, 0, this);

        //si visualizza l'immagine del buffer esterno
        //avendo disegnato su uno spazio esterno si disegna l0immagine già pronta, eliminando di fatto lo sfarfallio
        //Graphics2D g2=(Graphics2D)g;
        g.drawImage(workspace, 0, 0, this);

        //per liberare spazio in memoria si elimina l'immagine precedentemente memorizzata
        buffer.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
