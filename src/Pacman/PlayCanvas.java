package Pacman;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.lang.Thread;

public class PlayCanvas extends java.awt.Canvas implements ActionListener {

    public static void reInit() {
        SoundClip.stop();
        instance.continueGenerateCharacters = false;
        try {
            instance.generateCharacters.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        instance.timer.stop();

        instance = null;
        instance = new PlayCanvas();
    }

    private static final long serialVersionUID = 1L;

    public static PlayCanvas instance = new PlayCanvas();

    private int scrollPosition = 0;
    private int scrollPosition2 = 0;
    private int scrollPosition3 = 0;
    private int scrollPosition4 = 0;
    private int foodLevel;
    private int obstacleLevel;
    private int ghostLevel;
    private int generaPunti;
    private int generaOstacoli;
    private int generaFantasmi;
    private int randomOstacoli;
    final private int ObstacleDistanceY = 80; // la distanza verticale tra un ostacolo e l'altro
    final private int BaseDistanceY = 30; // la distanza verticale dalla cima dello schermo al primo ostacolo
    private int rateoOstacoli = 1; // il rateo con cui appaiono gli ostacoli: più basso=più distanti


    private final Vector<Thing> characters;
    private int schemaOstacoli[][] = {
            {0, 2, 1, 0, 0, 1},
            {1, 0, 2, 0, 1, 0},
            {2, 0, 1, 1, 0, 0},
            {1, 0, 0, 2, 0, 1},
            {1, 0, 0, 0, 2, 1},
            {0, 0, 3, 0, 0, 0},
            {0, 0, 0, 0, 3, 0}
    };

    public final int REFRESH_TIME = 10;
    private boolean doNotPaint = true;
    private Timer timer;
    private boolean continueGenerateCharacters = true;
    private Thread generateCharacters = new ThreadGenerateCharacters();

    private PlayCanvas() {
        characters = new Vector<>();
        foodLevel = 1;
        generaPunti = 0;
        obstacleLevel = 1;
        generaOstacoli = 0;

        // start timer timeout
        timer = new Timer(REFRESH_TIME, this);
    }

    public void init() {
        timer.start();
        generateCharacters.start();
        doNotPaint = false;
        Music.instance.start();
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

    public Vector<Thing> getCharacters() {
        return characters;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (doNotPaint) return;

        Image workspace = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) workspace.getGraphics();

        Image background = new ImageIcon(this.getClass().getResource("sprites/background/bup.png")).getImage();

        buffer.drawImage(background, 0, 0, this);

        scrollPosition += 3;
        scrollPosition2 += 2;
        scrollPosition3 += 1;
        scrollPosition4 += 3;
        // draws background according to pacman
        Background.instance.paintImage(buffer);

        // moves pacman and draws it
        PacmanCharacter.instance.paintImage(buffer);

        buffer.drawString("Score: " + PacmanCharacter.getPoints() + "  Seconds: " + Music.instance.getTime() + "", 25, 25);

        synchronized (characters) {
            for (Drawable d : characters)
                d.paintImage(buffer);
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
        randomOstacoli = (int) Math.floor(Math.random() * schemaOstacoli.length);

        for (int i = 0; i < characters.size(); i++) {
            Movable m = characters.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                characters.remove(i);
            }
        }

        repaint();
    }

    class ThreadGenerateCharacters extends Thread {
        public void run() {
            while (continueGenerateCharacters) {
                if (Music.instance.getVelocita() != 0) {
                    generaPunti++;
                    if (generaPunti >= 100 - ((foodLevel + 1) * rateoOstacoli))
                        for (int j = 0; j < schemaOstacoli.length - 1; j++)
                            if (schemaOstacoli[randomOstacoli][j] == 2) {
                                int y_p = j * ObstacleDistanceY + BaseDistanceY;
                                Food f = new Food(PlayFrame.instance.getWidth(), y_p);
                                synchronized (characters) {
                                    characters.add(f);
                                }
                                generaPunti = 0;
                            }
                    generaOstacoli++;
                    if (generaOstacoli >= 100 - ((obstacleLevel + 1) * rateoOstacoli))
                        for (int j = 0; j < schemaOstacoli.length - 1; j++)
                            if (schemaOstacoli[randomOstacoli][j] == 1) {
                                int y_o = j * ObstacleDistanceY + BaseDistanceY;
                                Obstacle o = new Obstacle(PlayFrame.instance.getWidth(), y_o);
                                synchronized (characters) {
                                    characters.add(o);
                                }
                                generaOstacoli = 0;
                            }
                }
                generaFantasmi++;
                if (generaFantasmi >= 100 - ((ghostLevel + 1) * rateoOstacoli)) {
                    for (int j = 0; j < schemaOstacoli.length - 1; j++) {
                        if (schemaOstacoli[randomOstacoli][j] == 3) {
                            int y_f = j * ObstacleDistanceY + BaseDistanceY;
                            Ghost g = new Ghost(PlayFrame.instance.getWidth(), y_f);
                            synchronized (characters) {
                                characters.add(g);
                            }
                            generaFantasmi = 0;
                        }
                    }
                }
                try {
                    Thread.sleep(PlayCanvas.instance.REFRESH_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



