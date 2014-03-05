



import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;//used for placement of apples

import javax.swing.*;

public class SnakeTest extends JFrame 
{
	private boolean GAMEOVER=false; //Detect if user has a gameover
	
	private int score=-1;//Keeps the score ie// number of apples eaten
	
	MoveDown s=new MoveDown();
	MoveRight q=new MoveRight();
	MoveLeft r=new MoveLeft();
	MoveUp t=new MoveUp();
	
	private boolean LEFT = false;
    private boolean RIGHT = false;
    private boolean UP = false;
    private boolean DOWN = false;

	Timer downtimer = new Timer(10, s);
	Timer righttimer=new Timer(10,  q);
	Timer lefttimer=new Timer(10,   r);
	Timer uptimer=new Timer(10,     t);
	
	Font gameoverfont= new Font("Helvetica", Font.BOLD, 70);
	Font scorefont=new Font("Helvetica", Font.BOLD,30);
	
	//defines the snake
    int snakex=325;
	int snakey=340;
	Rectangle SNAKE=new Rectangle(snakex, snakey,15,15);
	
	//Defines the tail
	Rectangle[] tails=new Rectangle[999];
    int[] tailx=new int[999];
    int[] taily=new int[999];
    
	//Defines the apple
	int applex=400;
	int appley=400;
	Rectangle apple=new Rectangle(applex,appley,15,15);
	Random ran=new Random();//Used for apple placement
	
	
	public SnakeTest()
	{
		super("SNAKE!     By Cody Antcliffe");		
		addKeyListener(new TAdapter());
	}
	
	public void paint(Graphics G)
	{	
		if(!GAMEOVER)
		{
			StopAtTail();
			StopAtWalls();
			EatApple();
			G.setColor(Color.BLACK);
			G.fillRect(0, 0, 800, 700);
			G.setColor(Color.WHITE);
			G.fillRect(50, 75, 700, 575);
			Graphics2D g2= (Graphics2D)G;
			g2.setColor(Color.GREEN);
			g2.fill(SNAKE);	
			g2.setColor(Color.RED);
			g2.fill(apple);			
			g2.setColor(Color.DARK_GRAY);
			for(int i=0; i<=score;i++)
				g2.fill(tails[i]);				
		}
		
		else
		{
			G.setColor(Color.WHITE);
			G.fillRect(0, 0, 800, 700);
			G.setColor(Color.BLACK);
			G.fillRect(0, 0, 800, 200);
			G.fillRect(0, 500, 800, 200);
			G.setColor(Color.RED);
			G.setFont(gameoverfont);
			G.drawString("Game Over!", 200, 370);
			G.setFont(scorefont);
			G.setColor(Color.BLACK);
			Integer.toString(score);
			G.drawString("Score: "+score/2, 290, 450);			
		}
				
	}//end paint()
	
		public static void main(String[] args)
		{
			JFrame newframe=new SnakeTest();
			newframe.setIconImage((new ImageIcon("snake.png")).getImage());
			newframe.setSize(800,700);
			newframe.setVisible(true);
		}
		
		//handles movement down
		public class MoveDown implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//Algorithm for tail movement
				tails[0]=new Rectangle(snakex, snakey,15,15);
				tailx[0]=snakex;
				taily[0]=snakey;
				for(int i=score;i>0;i--)
				{
					tailx[i]=tailx[i-1];
					taily[i]=taily[i-1];
					tails[i]=tails[i-1];
				}
				//
				snakey+=5;
				SNAKE=new Rectangle(snakex, snakey,15,15);
				
				repaint();
			}
		}//end MoveDown
		
		//handles movement up
		public class MoveUp implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//Algorithm for tail movement
					tails[0]=new Rectangle(snakex, snakey,15,15);
					tailx[0]=snakex;
					taily[0]=snakey;
					for(int i=score;i>0;i--)
					{
						tailx[i]=tailx[i-1];
						taily[i]=taily[i-1];
						tails[i]=tails[i-1];
					}
				//	
					snakey-=5;
					SNAKE=new Rectangle(snakex, snakey,15,15);		
					repaint();
			}
		}//end MoveUp
		
		//handles movement right
		public class MoveRight implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//Algorithm for tail movement
					tails[0]=new Rectangle(snakex, snakey,15,15);
					tailx[0]=snakex;
					taily[0]=snakey;
					for(int i=score;i>0;i--)
					{
						tailx[i]=tailx[i-1];
						taily[i]=taily[i-1];
						tails[i]=tails[i-1];
					}
				//	
					snakex+=5;
					SNAKE=new Rectangle(snakex, snakey,15,15);	
					repaint();
			}
		}//MoveRight
		
		//handles movement left
		public class MoveLeft implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{		
				//Algorithm for tail movement
					tails[0]=new Rectangle(snakex, snakey,15,15);
					tailx[0]=snakex;
					taily[0]=snakey;
					for(int i=score;i>0;i--)
					{
						tailx[i]=tailx[i-1];
						taily[i]=taily[i-1];
						tails[i]=tails[i-1];
					}
				//	
					snakex-=5;
					SNAKE=new Rectangle(snakex, snakey,15,15);			
					repaint();
			}
		}//end MoveLeft
		
			
		//Class for handling the arrow key presses
		private class TAdapter extends KeyAdapter 
		{
	        public void keyPressed(KeyEvent e) 
	        {
	            int direction = e.getKeyCode();
	            
	            if ((direction == KeyEvent.VK_LEFT) && (!RIGHT)&&(!GAMEOVER)) 
	            {
	                LEFT = true;
	                UP = false;
	                DOWN = false;
	                downtimer.stop();
	                uptimer.stop();
	                lefttimer.start();
	            }
	            if ((direction == KeyEvent.VK_RIGHT) && (!LEFT)&&(!GAMEOVER))
	            {
	                RIGHT = true;
	                UP = false;
	                DOWN = false;
	                downtimer.stop();
	                uptimer.stop();
	    			righttimer.start();
	            }
	            if ((direction == KeyEvent.VK_UP) && (!DOWN)&&(!GAMEOVER)) 
	            {
	                UP = true;
	                RIGHT = false;
	                LEFT = false;
	                righttimer.stop();
	                lefttimer.stop();
	    			uptimer.start();
	            }
	            if ((direction == KeyEvent.VK_DOWN) && (!UP)&&(!GAMEOVER))
	            {
	                DOWN = true;
	                RIGHT = false;
	                LEFT = false;
	                righttimer.stop();
	                lefttimer.stop();
	    			downtimer.start();
	            }
	        }
	    }//end arrow ActionHandler
		

	//Collision Detection Methods
		//Detects when you hit a wall
		public void StopAtWalls()
		{
			if(snakex<=45)
			{
				lefttimer.stop();
				GAMEOVER=true;
				repaint();
			}
			else if(snakex>=735)
			{
				righttimer.stop();
				GAMEOVER=true;
				repaint();
			}
			else if(snakey>=635)
			{
				GAMEOVER=true;
				repaint();
				downtimer.stop();
			}
			else if(snakey<=70)
			{
				GAMEOVER=true;
				repaint();
				uptimer.stop();
			}
		}	//End StopAtWalls()
		
		public void StopAtTail()
		{
			for(int i=5; i<=score; i++)
				if(snakex-5<=tailx[i]&&snakex+5>=tailx[i]&&snakey-5<=taily[i]&&snakey+5>=taily[i])
					GAMEOVER=true;
		} //End StopAtTail()
		
	//End Collision Detection Methods
		
		//Detects when an apple is eaten
		public void EatApple()
		{
			if(snakex-10<=applex&&snakex+15>=applex&&snakey-10<=appley&&snakey+15>=appley)
			{
				applex=gen_randomx();
				appley=gen_randomy();
				apple=new Rectangle(applex, appley,15,15);			
				score+=2; //Increase by two so that the tail grows quicker
						  //Will be corrected by dividing the final score by 2		
				repaint();			
			}
		}//End EatApple)
			
		/* Get new apple location*/
		//generates an x value to move apple around
		public int gen_randomx()
		{
			int Low = 50;
			int High = 730;
			int x = ran.nextInt(High-Low) + Low;
			return x;
		}
		//generates a y value to move apple around
		public int gen_randomy()
		{
			int Low = 80;
			int High = 630;
			int y = ran.nextInt(High-Low) + Low;
			return y;
		}
}//end main class SnakeTest
		
	





