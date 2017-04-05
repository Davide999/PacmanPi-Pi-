package Pacman;

public class MainPacman {

    public static void main(String argv[]) {
        Menu.instance.init();
    }

    public static void init() {
        PlayFrame.instance.setVisible(true);
        PlayCanvas.instance.init();
    }
}
