package Pacman;

import java.util.*;

public class Music {
	
	static Timer timer2 = new Timer();
	private final static int CURING_TIME = 1000;
	private static int seconds=0;
	private static int i=0;
	private static int velocita=-5;
	static int vettoreCambio[][]= {{-10,1},{-3,3},{-5,16},{-7,30},{-2,39}}; //[velocità][tempo di cambio]
	
	public static void Timer() {
		
        TimerTask task;

        task = new TimerTask() {
            @Override
            public void run()
            {
            	seconds++;
            	if(vettoreCambio[i][1]==seconds)
            	{
            		velocita=vettoreCambio[i][0];
            		i++;
            	}
            	if(seconds==3)
            	{
            		SoundClip.start();
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
		
		Timer();
	}
	
	public void stop()
	{
		timer2.cancel();
		SoundClip.stop();
	}
}


