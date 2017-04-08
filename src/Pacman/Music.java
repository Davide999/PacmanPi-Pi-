package Pacman;


import java.io.IOException;
import javax.sound.sampled.*;
import  sun.audio.*;
import  java.io.*;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.*;

public class Music {
	
	static Timer timer2 = new Timer();
	private final static int CURING_TIME = 1000;
	private static int seconds=0;
	private static int i=0;
	private static int velocita=-5;
	static int vettoreCambio[][]= {{-10,1},{-3,3},{-5,14},{-7,27},{-2,39}}; //[velocità][tempo di cambio]
	
	public Music()
	{	
		
	}
	
	public static void Timer() {
		
        TimerTask task;

        task = new TimerTask() {
            @Override
            public void run()
            {
            	seconds++;
            	System.out.println("second: "+seconds);
            	if(vettoreCambio[i][1]==seconds)
            	{
            		velocita=vettoreCambio[i][0];
            		i++;
            		System.out.println("i: "+i);
            	}
            	Ghost.changeSpeed(velocita);
            	Food.changeSpeed(velocita);
            	Obstacle.changeSpeed(velocita);
            }
        };
         timer2.schedule(task, 0, CURING_TIME);
         
    }
	

	public void start()
	{
		SoundClip.start();
		Timer();
	}
	
	public void stop()
	{
		timer2.cancel();
	}
}


