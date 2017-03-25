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

    private final int w=1000;
    private final int h=500;

    public Menu() {
    	
        //prepara la finestra
        JFrame f = new JFrame("Main Menu: Pacman");
        JButton start=new JButton("Start Game");
        int larghezza=(int)w*50/100;
        int altezza=(int)h*75/500;
        
        
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
                PlayFrame.init();
            }
        });
        esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//close
            }
        });
        f.add(esci);
        f.setSize(w, h);
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
        // The background. It works, ok?
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, w, h);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
