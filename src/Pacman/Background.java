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

    public void paintImage(Graphics2D buffer) {
        final int cose = PlayCanvas.instance.getScrollPosition() % PlayFrame.instance.getWidth();

        for(int i=0; i<5; i++){
            int x = -cose+i*image.getWidth(null);
            if(x+image.getWidth(null)>0) buffer.drawImage(image, x, 175, null);
        }
    }
}

