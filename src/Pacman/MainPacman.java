package Pacman;

public class MainPacman {

    public static void main(String argv[]) {
        showMenu();
    }

    public static void showMenu() {
        Menu.instance.init();
    }

    public static void init() {
            PlayFrame.instance.setVisible(true);
            PlayCanvas.instance.init();
    }

    public static void reInit() {
        Background.reInit();
        PlayFrame.removeCanvas();
        PlayCanvas.reInit();
        PlayFrame.addCanvas();
        Music.reInit();
        PacmanCharacter.reInit();
    }

    public static void stopGame() throws InterruptedException {
        SoundClip.stop();
        replay();
    }
    
    public static void replay() {
    	reInit();
    	init();
    }
}
