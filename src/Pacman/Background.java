/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import java.awt.*;
import javax.swing.ImageIcon;

public class Background {
    private int horiz = 200;
    private int vert = 250;
    public static final Background instance = new Background();
    private final Image image = new ImageIcon(this.getClass()
            .getResource("sprites/background/oro loop.png"))
            .getImage();
    private final Image image2 = new ImageIcon(this.getClass()
            .getResource("sprites/background/nuvolette.png"))
            .getImage();

    public void paintImage(Graphics2D buffer) {
        int imagePos = -PlayCanvas.instance.getScrollPosition();
        while(imagePos < -image.getWidth(null)) imagePos += image.getWidth(null);
        final int numImages = (int) Math.ceil(PlayFrame.instance.getWidth() / image.getWidth(null)) + 2;

        for (int i = 0; i < numImages; i++) {
            int x = imagePos + i * image.getWidth(null);
            if (x + image.getWidth(null) > 0) buffer.drawImage(image, x, 175, null);
        }
    }
}

