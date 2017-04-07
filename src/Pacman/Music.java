package Pacman;

import java.util.*;

public class Music {
	
	static Timer timer = new Timer();
	private final static int CURING_TIME = 10000;
	private int velocita=-5;
	
	public void Music()
	{
		
		Ghost.changeSpeed(velocita);
		
	}
	
	public static void Timer() {

        TimerTask task;

        task = new TimerTask() {
            @Override
            public void run()
            {
            	
            }
        };
         timer.schedule(task, CURING_TIME, CURING_TIME);

    }
}
