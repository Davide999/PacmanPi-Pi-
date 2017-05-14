package Pacman;

public class MainPacman {

    public static void main(String argv[]) {
        Menu.instance.init();
    }

    public static void init() {
            PlayFrame.instance.setVisible(true);
            PlayCanvas.instance.init();
    }
    
    public static void reInit() {
    	Background.reInit();
    	Music.reInit();
    	PacmanCharacter.reInit();
    	PlayFrame.removeCanvas();
    	PlayCanvas.reInit();
    	PlayFrame.addCanvas();
    	init();
    }
}
