package Pacman;

import java.util.*;

public class Music {
	
	static Timer timer = new Timer();
	private final static int CURING_TIME = 1000;
	private static int seconds=0;
	private static int i=0;
	private static int velocita=-5;
	static int vettoreCambio[][]= {{-5,1},{-2,3},{-4,5},{-6,7}}; //[velocità][tempo di cambio]
	
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
         timer.schedule(task, 0, CURING_TIME);
         
    }
	
	
	
	public void start()
	{
		Timer();
	}
	
	public void stop()
	{
		timer.cancel();
	}
}
