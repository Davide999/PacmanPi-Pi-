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
    private final Image image3 = new ImageIcon(this.getClass()
            .getResource("sprites/background/atmosfera.png"))
            .getImage();
    private final Image image4 = new ImageIcon(this.getClass()
            .getResource("sprites/background/terreno.png"))
            .getImage();

    public void paintImage(Graphics2D buffer) {   
      //ATMOSFERA
        int imagePos3 = -PlayCanvas.instance.getScrollPosition3();
        while(imagePos3 < -image3.getWidth(null)) imagePos3 += image3.getWidth(null);
        final int numImages3 = (int) Math.ceil(PlayFrame.instance.getWidth() / image3.getWidth(null)) + 2;
        
        for (int i = 0; i < numImages3; i++) {
            int x = imagePos3 + i * image3.getWidth(null);
            if (x + image3.getWidth(null) > 0) buffer.drawImage(image3, x, 0, null);
        }
        
      //MONTAGNE
        int imagePos = -PlayCanvas.instance.getScrollPosition();
        while(imagePos < -image.getWidth(null)) imagePos += image.getWidth(null);
        final int numImages = (int) Math.ceil(PlayFrame.instance.getWidth() / image.getWidth(null)) + 2;
        
        for (int i = 0; i < numImages; i++) {
            int x = imagePos + i * image.getWidth(null);
            if (x + image.getWidth(null) > 0) buffer.drawImage(image, x, 175, null);
        }
        
        //NUVOLE
        int imagePos2 = -PlayCanvas.instance.getScrollPosition2();
        while(imagePos2 < -image2.getWidth(null)) imagePos2 += image2.getWidth(null);
        final int numImages2 = (int) Math.ceil(PlayFrame.instance.getWidth() / image2.getWidth(null)) + 2;
        
        for (int i = 0; i < numImages2; i++) {
            int x = imagePos2 + i * image2.getWidth(null);
            if (x + image2.getWidth(null) > 0) buffer.drawImage(image2, x, 0, null);
        }
        
      //SENTIERO
        int imagePos4 = -PlayCanvas.instance.getScrollPosition4();
        while(imagePos4 < -image4.getWidth(null)) imagePos4 += image4.getWidth(null);
        final int numImages4 = (int) Math.ceil(PlayFrame.instance.getWidth() / image4.getWidth(null)) + 2;
        
        for (int i = 0; i < numImages4; i++) {
            int x = imagePos4 + i * image4.getWidth(null);
            if (x + image4.getWidth(null) > 0) buffer.drawImage(image4, x, 0, null);
        }
         
        
    }
}

