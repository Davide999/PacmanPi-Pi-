package Pacman;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Game main menu.
 *
 * TODO: Make a huge refactor inside this class. Now it's awful. Bob Ross forgive me.
 */

public class Menu {
    public static Menu instance = new Menu();

    private boolean menuOptionsShown = false;
    private final int WIDTH = 1000;
    private final int HEIGHT = 500;
    private final int BUTTONWIDTH = WIDTH * 2 / 5;
    private final int BUTTONHEIGHT = HEIGHT * 75 / 500;
    private JFrame mainFrame;

    private static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    private Menu() {
        // construct frames and remove default layout
        mainFrame = new JFrame("Main Menu: Pacman++");
        mainFrame.setLayout(null);

        JFrame o = new JFrame("Options: Pacman++");
        o.setLayout(null);

        // buttons inside menu frame
        JButton start = new JButton("Start Game");
        JButton exit = new JButton("Exit");
        JButton options = new JButton("Options");

        // buttons inside options frame
        JButton back = new JButton("Back");
        JButton ldm = new JButton("Low Detail Mode");

        // buttons, styles and listeners follow

        start.setBounds((PlayFrame.instance.getWidth() / 2 - BUTTONWIDTH / 2), (PlayFrame.instance.getHeight() / 2 -
                (PlayFrame.instance.getHeight() / 5)), BUTTONWIDTH, BUTTONHEIGHT);
        start.setBackground(Color.BLACK);
        start.setForeground(Color.WHITE);
        start.addActionListener(e -> {
            mainFrame.setVisible(false);
            MainPacman.init();
        });
        mainFrame.add(start);

        options.setBounds((PlayFrame.instance.getWidth() / 2 - BUTTONWIDTH / 2), (PlayFrame.instance.getHeight() / 2
                - (PlayFrame.instance.getHeight() / 5)) + BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT);
        options.setBackground(Color.BLACK);
        options.setForeground(Color.WHITE);
        options.addActionListener(e -> {
            menuOptionsShown = !menuOptionsShown;
            mainFrame.setVisible(false);
            o.setVisible(true);
        });
        mainFrame.add(options);

        ldm.setBounds((PlayFrame.instance.getWidth() / 2 - BUTTONWIDTH / 2), (PlayFrame.instance.getHeight() / 2 -
                (PlayFrame.instance.getHeight() / 5)), BUTTONWIDTH, BUTTONHEIGHT);
        ldm.setBackground(Color.BLACK);
        ldm.setForeground(Color.WHITE);
        ldm.addActionListener(e -> Background.instance.toggleBackground());
        o.add(ldm);

        back.setBounds((PlayFrame.instance.getWidth() / 2 - BUTTONWIDTH / 2), (PlayFrame.instance.getHeight() / 2 -
                (PlayFrame.instance.getHeight() / 5)) + 2 * BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(e -> {
            menuOptionsShown = !menuOptionsShown;
            o.setVisible(false);
            mainFrame.setVisible(true);
        });
        o.add(back);

        exit.setBounds((PlayFrame.instance.getWidth() / 2 - BUTTONWIDTH / 2), (PlayFrame.instance.getHeight() / 2 -
                (PlayFrame.instance.getHeight() / 5)) + 2 * BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.addActionListener(e -> System.exit(0));
        mainFrame.add(exit);

        // Game logo image
        Image titleImage = new ImageIcon(this.getClass().getResource("sprites/title/title.png")).getImage();

        final int TITLEWIDTH = (int) ((double) WIDTH * 0.8);
        final int TITLEHORIZ = (WIDTH - TITLEWIDTH) / 2;
        final int TITLEVERT = 22;
        final int TITLEHEIGHT = (int) (((double) TITLEWIDTH / (double) titleImage.getWidth(null))
                * (double) titleImage.getHeight(null));

        titleImage = getScaledImage(titleImage, TITLEWIDTH, TITLEHEIGHT);
        JLabel title = new JLabel(new ImageIcon(titleImage));
        title.setBounds(TITLEHORIZ, TITLEVERT, TITLEWIDTH, TITLEHEIGHT);
        title.setVisible(true);
        mainFrame.add(title);

        // final options
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setLocation(100, 100);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        o.setSize(WIDTH, HEIGHT);
        o.setResizable(false);
        o.setLocation(100, 100);
        o.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void init() {
        mainFrame.setVisible(true);
    }
}
