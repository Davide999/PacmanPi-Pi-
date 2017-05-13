package Pacman;

import java.util.*;

public class Music {
	
	public static Music instance = new Music();
	
	private Timer timer2 = new Timer();
	private final int CURING_TIME = 1000;
	private int seconds=0;
	private int secondsOfBoss=0;
	private int secondsOfGame=0;
	private int i=0;
	private int velocita=-5;
	private int ripeti=0;
	private int BOSS=6;
	private int vettoreCambio[][]= {{-10,1,0},{-3,3,0},{-5,16,0},{-7,29,0},{-3,39,0},{-2,41,1},{0,0,0}}; //[velocità][tempo di cambio]
	private TimerTask task;
	
	private Music() {}
	
	public void Timer() {
		
        task = new TimerTask() {
            @Override
            public void run()
            {	
            	if(i==BOSS)	//conta durata boss
            	{
            		secondsOfBoss++;
            	}
            	else
            	{
            		seconds++;
            	}
            	secondsOfGame++;
            	
            	System.out.println("s= "+seconds+"  s boss= "+secondsOfBoss);
            	if(vettoreCambio[i][1]==seconds)	//cambia la velocità
            	{
            		velocita=vettoreCambio[i][0];
            		if(vettoreCambio[i][2]==1)
            		{
            			i=1;
            			seconds=0;
            		}
            		i++;	
            	}
            	
            	if(ripeti==1 && i==4)	//fa bloccare lo schermo per il boss  if(ripeti==2)
        		{
        			i=BOSS;
        			velocita=vettoreCambio[i][0];
        			ripeti=1;
        		}
            	
            	if(secondsOfBoss==9)	//fine boss
            	{
            		i=1;
            		seconds=0;
            		secondsOfBoss=0;
            		System.out.println("boss finito");
            	}
            	
            	if(seconds==3)	//fa partire la musica
            	{
            		if(ripeti==0)
            			{SoundClip.start();
            			}
            		ripeti++;
            		System.out.println("i= "+i);
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
		timer2.purge();
	}
	
	public int getVelocita()
	{
		return velocita;
	}
	
	public int getTime()
	{
		return secondsOfGame;
	}
}


