package Pacman;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class PlayCanvas extends java.awt.Canvas
        implements ActionListener {

    public static PlayCanvas instance = new PlayCanvas();

    private int scrollPosition = 0;
    private int scrollPosition2 = 0;
    private int scrollPosition3 = 0;
    private int scrollPosition4 = 0;
    private int livelloPunti;
    private int livelloOstacoli;
    private int livelloFantasmi;
    private int generaPunti;
    private int generaOstacoli;
    private int generaFantasmi;
    private int randomOstacoli;
    final private int YDistanzaOstacoli=80; //la distanza verticale tra un ostacolo e l'altro
    final private int YDistanzaBase=30;	//la distanza verticale dalla cima dello schermo al primo ostacolo
    private int rateoOstacoli=1; //il rateo con cui appaiono gli ostacoli: pi� basso=pi� distanti

    private Vector<Food> foodVector;
    private Vector<Obstacle> obstacleVector;
    private Vector<Ghost> ghostVector;
    private int schemaOstacoli[][]={{0,2,1,0,0,1},{1,0,2,1,1,1},{2,0,1,1,0,0},{1,1,0,2,1,1},{0,0,1,1,2,0},{0,0,3,0,0,0},{0,0,0,0,3,0}};
    public final int REFRESH_TIME = 8;
    private Timer timer; // timeout

    private PlayCanvas() {
        foodVector = new Vector<>();
        obstacleVector = new Vector<>();
        ghostVector = new Vector<>();
        livelloPunti = 1;
        generaPunti = 0;
        livelloOstacoli = 1;
        generaOstacoli = 0;
        // start timer timeout
        timer = new Timer(REFRESH_TIME, this);
        timer.start();
    }

    public void stopGame(){
        timer.stop();
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

    public Vector<Food> getFoodVector() {
        return foodVector;
    }

    public Vector<Obstacle> getObstacleVector() {
        return obstacleVector;
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
        PacmanCharacter.instance.moveAndHandleCollisions();
        PacmanCharacter.instance.paintImage(buffer);

        //Gesione punti
        for (Food p : foodVector) {
            buffer.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }
        
        //Gesione ostacoli
        for (Obstacle p : obstacleVector) {
            buffer.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }

        //Gesione fantasmi
        for (Ghost p : ghostVector) {
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
        PacmanCharacter.instance.moveAndHandleCollisions();
        randomOstacoli=(int) Math.floor(Math.random()*schemaOstacoli.length);
        creaPunti();
        creaOstacoli();
        creaFantasmi();
        for (int i = 0; i < foodVector.size(); i++) {
            Food p = foodVector.get(i);
            if (p.isVisible()) {
                p.move();
            } else {
                foodVector.remove(i);
            }
        }
        for (int j = 0; j < obstacleVector.size(); j++) {
            Obstacle o = obstacleVector.get(j);
            if (o.isVisible()) {
                o.move();
            } else {
                obstacleVector.remove(j);
            }
        }
        for (int j = 0; j < ghostVector.size(); j++) {
            Ghost o = ghostVector.get(j);
            if (o.isVisible()) {
                o.move();
            } else {
                ghostVector.remove(j);
            }
        }
        repaint();
    }

    private void creaPunti() {
        generaPunti++;
        if (generaPunti >= 100 - ((livelloPunti + 1) * rateoOstacoli)) {
        	
        	for(int j=0;j<schemaOstacoli.length-1;j++)
        	{    		
        		if(schemaOstacoli[randomOstacoli][j]==2)
        		{
		            int y_p = (int) (j*YDistanzaOstacoli+YDistanzaBase);
		            Food f = new Food(PlayFrame.instance.getWidth(), y_p);
		            foodVector.add(f);
		            generaPunti = 0;
        		}
        	}
        }
    }
    
    private void creaOstacoli() {
        generaOstacoli++;
        if (generaOstacoli >= 100 - ((livelloOstacoli + 1) * rateoOstacoli)) {
        	
        	for(int j=0;j<schemaOstacoli.length-1;j++)
        	{    		
        		if(schemaOstacoli[randomOstacoli][j]==1)
        		{
        			int y_o = j*YDistanzaOstacoli+YDistanzaBase;
            		Obstacle o = new Obstacle(PlayFrame.instance.getWidth(), y_o);
                    obstacleVector.add(o);
                    generaOstacoli = 0;
        		}
        	}
        }
    }
    
    private void creaFantasmi() {
        generaFantasmi++;
        if (generaFantasmi >= 100 - ((livelloFantasmi + 1) * rateoOstacoli)) {
        	
        	for(int j=0;j<schemaOstacoli.length-1;j++)
        	{    		
        		if(schemaOstacoli[randomOstacoli][j]==3)
        		{
        			int y_f = j*YDistanzaOstacoli+YDistanzaBase;
            		Ghost f = new Ghost(PlayFrame.instance.getWidth(), y_f);
                    ghostVector.add(f);
                    generaFantasmi = 0;
        		}
        	}
            //int y_o = (int) (Math.random() * PlayFrame.instance.getHeight());   
        }
    }
    
    
}
