package Pacman;

import java.io.*;
import javax.sound.sampled.*;

public class SoundClip {

	/*
	 * public static synchronized void playSound(final String url) { new
	 * Thread(new Runnable() { // The wrapper thread is unnecessary, unless it
	 * blocks on the // Clip finishing; see comments. public void run() { try {
	 * Clip clip = AudioSystem.getClip(); AudioInputStream inputStream =
	 * AudioSystem.getAudioInputStream(
	 * PlayCanvas.class.getResourceAsStream("src/Pacman/musics/" + url));
	 * clip.open(inputStream); clip.start(); } catch (Exception e) {
	 * System.err.println(e.getMessage()); e.printStackTrace(); } } }).start();
	 * }
	 * 
	 * public static void start() { playSound("Tetris.wav"); }
	 */

	public static void start() {
		File musica = new File("src/Pacman/musics/Tetris.wav");
		playSound(musica);
	}

	static void playSound(File sound) {
		new Thread(new Runnable() {
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
		}).start();
	}

}
