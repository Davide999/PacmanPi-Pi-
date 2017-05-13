package Pacman;

import java.io.*;
import javax.sound.sampled.*;

public class SoundClip {

	public static void start() {
		File musica = new File("src/Pacman/musics/Tetris.wav");
		playSound(musica);
	}
	
	private static Clip thaClip;
	private static Thread soundThread;
	
	public static void stop() {
		thaClip.stop();
	}

	private static void playSound(File sound) {
		soundThread = new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					thaClip = clip;
					clip.open(AudioSystem.getAudioInputStream(sound));
					clip.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		soundThread.start();
	}

}
