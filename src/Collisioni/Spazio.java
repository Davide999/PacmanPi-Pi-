package Collisioni;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import sun.security.x509.AttributeNameEnumeration;

public class Spazio extends Canvas implements ActionListener, KeyListener{
    
    private Timer timer;
    private Astronave astronave;
    private Vector asteroidi;
    private int livello;
    private int genera;
    private Esplosione esplosione;
    
    public Spazio(){
        
        astronave=new Astronave();
        //prepara la finestra        
        JFrame f=new JFrame("Gioco spaziale");
        f.setSize(400, 550);
        f.setResizable(false);
        f.setLocation(100, 100);
        f.add(this);
        f.addKeyListener(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        asteroidi=new Vector();
        livello=1;
        genera=0;
        
        //timer
        timer=new Timer(7, this);
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
        Image workspace=createImage(getWidth(),getHeight());
        Graphics2D buffer=(Graphics2D) workspace.getGraphics();
        
        
        //si disegnano gli elementi nel buffer esterno       
        Image img=new ImageIcon(this.getClass().getResource("../images/sfondo.jpg")).getImage();
        buffer.drawImage(img, 0, 0, this);   
        
        if (astronave.isEsplosa())
            buffer.drawImage(esplosione.getImage(), esplosione.getX(),esplosione.getY(),this);
        else
            buffer.drawImage(astronave.getImage(),astronave.getX(),astronave.getY(),this);
        
        
        
        //**************************** Gestione asteroidi ********************************************
       for (int i=0;i<asteroidi.size();i++){
           Asteroide a=(Asteroide)asteroidi.get(i);
           buffer.drawImage(a.getImage(), a.getX(), a.getY(), this);
       }
        
        
        //si visualizza l'immagine del buffer esterno         
        Graphics2D g2=(Graphics2D)g;
        g2.drawImage(workspace, 0, 0, this);        
        //per liberare spazio in memoria si elimina l'immagine precedentemente memorizzata
        buffer.dispose();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        creaAsteroidi();
        
        for (int i=0;i<asteroidi.size();i++){
            Asteroide a=(Asteroide)asteroidi.get(i);
            if (a.isVisible()) {
                a.move();
            }
            else {
                asteroidi.remove(i);
            }
        }
       if (!astronave.isEsplosa()) { 
            astronave.move();
        }
       else {
            esplosione.move();
        }
       
       if(astronave.isEsplosa() && esplosione.isTerminaGioco()) {
             timer.stop();
        }
       rilevaCollisioni();
       repaint();
    }
    
      private void rilevaCollisioni() {
          
          if (!astronave.isEsplosa()){
               Rectangle ra=astronave.getDimensione();
        
                for (int i=0;i<asteroidi.size();i++){
                   Asteroide a=(Asteroide)asteroidi.get(i);
                   Rectangle rs=a.getDimensione();
                  if (ra.intersects(rs)){
                      astronave.setEsplosa(true);
                      esplosione=new Esplosione(astronave.getX(), astronave.getY());

                  }
          }
       
        }
        
    }

    

    @Override
    public void keyPressed(KeyEvent e) {
        astronave.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        astronave.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    private void creaAsteroidi() {
        genera++;
        
        if (genera>=100-((livello+1)*10)){
            int x_a=(int) (Math.random()*getWidth());
            asteroidi.add(new Asteroide(x_a,0));
            genera=0;
        }
        
    }
    
}
