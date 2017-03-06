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
    	//1=montagne
    	//2=nuvolette
        final int cose = PlayCanvas.instance.getScrollPosition() % PlayFrame.instance.getWidth();
        final int cose2 = PlayCanvas.instance.getScrollPosition2() % PlayFrame.instance.getWidth();
        
        for(int i=0; i<5; i++){
            int x = -cose+i*image.getWidth(null);
            if(x+image.getWidth(null)>0) buffer.drawImage(image, x, 175, null);
            int x2 = -cose2+i*image2.getWidth(null);
            if(x2+image2.getWidth(null)>0) buffer.drawImage(image2, x, 0, null);
        }
    }
}

