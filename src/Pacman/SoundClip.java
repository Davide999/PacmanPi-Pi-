package Pacman;

import java.io.*;
import javax.sound.sampled.*;

public class SoundClip {
	
	private static Thread t;
	
	public static void start() {
		File musica = new File("src/Pacman/musics/Tetris.wav");
		playSound(musica);
	}

	static void playSound(File sound) {

		t = new Thread(new Runnable() {
			
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(sound));
					clip.start();
					Thread.sleep(clip.getMicrosecondLength() / 1000);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				
			}
		});
		t.start();
		t.setName("Pacman++ Musica");
		
	}
	
	public static void stop()
	{
		t.interrupt();
	}

}
