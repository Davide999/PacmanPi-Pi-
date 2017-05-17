package Pacman;

import java.io.*;
import javax.sound.sampled.*;

public class SoundClip {

    public static void start() {
        File musica = new File(SoundClip.class.getResource("musics/tetris2.wav").getFile());
        playSound(musica);
    }

    private static Clip thaClip;
    private static Thread soundThread;

    public static void stop() {
        if (thaClip != null) thaClip.stop();
    }
    
    public static void stopTread() {
        soundThread.interrupt();
    }

    private static void playSound(File sound) {
        soundThread = new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                thaClip = clip;
                clip.open(AudioSystem.getAudioInputStream(sound));
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        soundThread.start();
    }

}
