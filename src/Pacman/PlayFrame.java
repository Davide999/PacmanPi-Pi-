package Pacman;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayFrame extends JFrame implements KeyListener{

	public static PlayFrame instance = new PlayFrame();

	private PlayFrame(){
		super("Pacman");
		this.setSize(1100, 500);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.add(PlayCanvas.instance);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Updates pacman movement and override
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        PacmanCharacter.instance.handleKeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PacmanCharacter.instance.handleKeyReleased(e);
    }
}
