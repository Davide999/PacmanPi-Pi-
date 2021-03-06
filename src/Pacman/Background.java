/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;


public class Background implements Runnable {
	
	public static void reInit() {
        boolean act = instance.active;
		instance = null;
		instance = new Background();
		instance.active = act;
	}

    public static Background instance = new Background();
    private boolean active = true;

    private final Image somethingCalledOroLoop = new ImageIcon(this.getClass()
            .getResource("sprites/background/oro loop.png"))
            .getImage();
    private final Image clouds = new ImageIcon(this.getClass()
            .getResource("sprites/background/nuvolette.png"))
            .getImage();
    private final Image atmosphere = new ImageIcon(this.getClass()
            .getResource("sprites/background/atmosfera.png"))
            .getImage();
    private final Image ground = new ImageIcon(this.getClass()
            .getResource("sprites/background/terreno.png"))
            .getImage();


    public void paintImage(Graphics2D buffer) {
        if (!active) return;

        //ATMOSFERA
        int imagePos3 = -PlayCanvas.instance.getScrollPosition3();
        while (imagePos3 < -atmosphere.getWidth(null)) imagePos3 += atmosphere.getWidth(null);
        final int numImages3 = (int) Math.ceil(PlayFrame.instance.getWidth() / atmosphere.getWidth(null)) + 2;

        for (int i = 0; i < numImages3; i++) {
            int x = imagePos3 + i * atmosphere.getWidth(null);
            if (x + atmosphere.getWidth(null) > 0) buffer.drawImage(atmosphere, x, 0, null);
        }

        //MONTAGNE
        int imagePos = -PlayCanvas.instance.getScrollPosition();
        while (imagePos < -somethingCalledOroLoop.getWidth(null)) imagePos += somethingCalledOroLoop.getWidth(null);
        final int numImages = (int) Math.ceil(PlayFrame.instance.getWidth() / somethingCalledOroLoop.getWidth(null)) + 2;

        for (int i = 0; i < numImages; i++) {
            int x = imagePos + i * somethingCalledOroLoop.getWidth(null);
            if (x + somethingCalledOroLoop.getWidth(null) > 0) buffer.drawImage(somethingCalledOroLoop, x, 175, null);
        }

        //NUVOLE
        int imagePos2 = -PlayCanvas.instance.getScrollPosition2();
        while (imagePos2 < -clouds.getWidth(null)) imagePos2 += clouds.getWidth(null);
        final int numImages2 = (int) Math.ceil(PlayFrame.instance.getWidth() / clouds.getWidth(null)) + 2;

        for (int i = 0; i < numImages2; i++) {
            int x = imagePos2 + i * clouds.getWidth(null);
            if (x + clouds.getWidth(null) > 0) buffer.drawImage(clouds, x, 0, null);
        }

        //SENTIERO
        int imagePos4 = -PlayCanvas.instance.getScrollPosition4();
        while (imagePos4 < -ground.getWidth(null)) imagePos4 += ground.getWidth(null);
        final int numImages4 = (int) Math.ceil(PlayFrame.instance.getWidth() / ground.getWidth(null)) + 2;

        for (int i = 0; i < numImages4; i++) {
            int x = imagePos4 + i * ground.getWidth(null);
            if (x + ground.getWidth(null) > 0) buffer.drawImage(ground, x, 0, null);
        }
    }

    public void toggleBackground(boolean click) {
    	
    	if(!click)
    	{
    		int readActive=0;
	    	try
	    	{
	    	FileReader fileReader = new FileReader("Background.txt");
	    	BufferedReader reader = new BufferedReader(fileReader);
	    	readActive= Integer.parseInt(reader.readLine());
	    	reader.close();
	    	}
	    	catch (IOException e)
	    	{
	    		
	    	}
	    	
	    	if (readActive==0)
	    	{
	    		active=false;
	    	}
	    	else
	    	{
	    		active=true;
	    	}
    	}
    	else
    	{
    		active = !active;
    	}
    }
    
    

    public boolean getActive()
    {
    	return active;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}


