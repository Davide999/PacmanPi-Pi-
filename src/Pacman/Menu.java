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


public class Menu extends Canvas implements ActionListener {
    private ImageIcon icon;
    private JLabel label;
    private JPanel p;



    public Menu() {




        //prepara la finestra
        JFrame f = new JFrame("Main Menu: Pacman");
        JButton start=new JButton("Start Game");
        int width=1000;
        int height=500;
        int larghezza=(int)width*2/5;
        int altezza=(int)height*75/500;

        //immagine start.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/3-(PlayFrame.instance.getHeight()/3)),larghezza, altezza);
        //buttons
        start.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/2-(PlayFrame.instance.getHeight()/5)),larghezza, altezza);
        f.add(start);
        JButton esci=new JButton("Exit");
        esci.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/2-(PlayFrame.instance.getHeight()/5))+2*altezza,larghezza, altezza);
        esci.setBackground(Color.BLACK);
        start.setBackground(Color.BLACK);
        start.setForeground(Color.WHITE);
        esci.setForeground(Color.WHITE);
        //operation buttons
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                MainPacman.init();
            }
        });
        esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//close
            }
        });
        f.add(esci);
        f.setSize(width, height);
        f.setResizable(false);
        f.setLocation(100, 100);
        f.add(this);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
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
        buffer.setColor(new Color(0, 0, 0));
        buffer.fillRect(0, 0, 600, 400);

        Image img = getToolkit().getImage("sprites/Title/Title.png");
        buffer.drawImage(img, 0, 0, this);

        //si visualizza l'immagine del buffer esterno
        //avendo disegnato su uno spazio esterno si disegna l0immagine già pronta, eliminando di fatto lo sfarfallio


        //per liberare spazio in memoria si elimina l'immagine precedentemente memorizzata
        buffer.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
