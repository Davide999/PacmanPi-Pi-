/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Menu extends Canvas implements ActionListener {
    private ImageIcon icon;
    private JLabel label;
    private JPanel p;
    private boolean menu_options=false;


    public Menu() {
        //prepara la finestra
        JFrame f = new JFrame("Main Menu: Pacman");
        JFrame o = new JFrame("Optionis: Pacman");
        JButton start=new JButton("Start Game");
        JButton esci=new JButton("Exit");
        JButton options=new JButton("Options");
        JButton back=new JButton("Back");
        JButton ldm=new JButton("Low Detail Mode");
        
        int width=1000;
        int height=500;
        int larghezza=(int)width*2/5;
        int altezza=(int)height*75/500;
        int larghezza2=(int)width*2/10;
        int altezza2=(int)height*75/1000;
        

        //immagine start.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/3-(PlayFrame.instance.getHeight()/3)),larghezza, altezza);
        //buttons
        start.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/2-(PlayFrame.instance.getHeight()/5)),larghezza, altezza);
        f.add(start);
        options.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/2-(PlayFrame.instance.getHeight()/5))+1*altezza,larghezza, altezza);
        f.add(options);
        ldm.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/2-(PlayFrame.instance.getHeight()/5)),larghezza, altezza);
        o.add(ldm);
        back.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/2-(PlayFrame.instance.getHeight()/5))+2*altezza,larghezza, altezza);
        o.add(back);
        esci.setBounds((PlayFrame.instance.getWidth()/2-larghezza/2),(PlayFrame.instance.getHeight()/2-(PlayFrame.instance.getHeight()/5))+2*altezza,larghezza, altezza);
        esci.setBackground(Color.BLACK);
        start.setBackground(Color.BLACK);
        ldm.setBackground(Color.BLACK);
        options.setBackground(Color.BLACK);
        back.setBackground(Color.BLACK);
        start.setForeground(Color.WHITE);
        esci.setForeground(Color.WHITE);
        ldm.setForeground(Color.WHITE);
        options.setForeground(Color.WHITE);
        back.setForeground(Color.WHITE);
        //operation buttons
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                MainPacman.init();
            }
        });
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	menu_options= !menu_options;
            	f.setVisible(false);
            	o.setVisible(true);
            }
   
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	menu_options= !menu_options;
            	o.setVisible(false);
            	f.setVisible(true);
            }
   
        });
        ldm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Background.instance.toggleBackground();
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
        
        o.setSize(width, height);
        o.setResizable(false);
        o.setLocation(100, 100);
        o.add(this);
        o.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
