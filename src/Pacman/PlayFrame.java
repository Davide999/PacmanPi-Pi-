package Pacman;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayFrame extends JFrame implements KeyListener {

    public static void removeCanvas() {
        instance.remove(PlayCanvas.instance);
    }

    public static void addCanvas() {
        instance.add(PlayCanvas.instance);
    }

    private static final long serialVersionUID = 1L;
    public static PlayFrame instance = new PlayFrame();

    private PlayFrame() {
        super("Pacman++");
        this.setSize(1100, 500);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.add(PlayCanvas.instance);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Updates pacman movement and override
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        PacmanCharacter.instance.handleKeyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            MainPacman.reInit();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PacmanCharacter.instance.handleKeyReleased(e);
    }
}
