import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements MouseListener {
	Judge judge;
	SimpleDraw draw;
	Bing fang;
	Obstacle obs;

	private static final int TIME = 10;
	private int score;
	
	public Game()
	{
		score = 0;
	}
	
	public static void main(String [] args)
	{
		Game game = new Game();
		game.setup();
		game.start();
		game.end();
	}
	
	public void setup()
	{
		JFrame frame = new JFrame();
		
		
		fang = new SimpleBing(200,200);
		obs  = new SimpleObstacle(600);
		judge = new SimpleJudge(fang,obs);
		draw = new SimpleDraw(fang,obs);
		
		frame.getContentPane().add(draw);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		draw.addMouseListener(this);
		frame.setSize(600,600);
		frame.setVisible(true);
	}
	
	public void start()
	{
		int flag = 1;
		while (flag > 0)
		{
			fang.action();
			obs.move();
			flag = judge.isGameOver(fang,obs);
			if (flag == 2)
				score ++;
			draw.draw();
			try 
			{
				Thread.sleep(TIME);
			} catch (Exception e)
			{}
		}
	}
	
	public void end()
	{
		System.out.println("Your score is " + score);
	}
	
	public void mouseClicked(MouseEvent e)
	{
	
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) 
	{
		fang.beginUp();
	}
	public void mouseReleased(MouseEvent e) {}
}