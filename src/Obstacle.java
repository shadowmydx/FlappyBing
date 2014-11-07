import javax.swing.*;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;

public interface Obstacle {
	public void move();
	public LinkedList<Pillar> getObstacles();
}

class SimpleObstacle implements Obstacle {
	private static final int SPEED = 2;
	public LinkedList<Pillar> pillar = new LinkedList<Pillar> ();
	int border;
	
	SimpleObstacle(int border)
	{
		this.border = border;
		init();
	}
	
	private static int getRandomHeight()
	{
		int res = (int)(Math.random() * (Pillar.getHeiLmt() - 100) + 100);
		return res;
	}
	
	private void init()
	{
		pillar.add(new Pillar(this.border,getRandomHeight()));
	}
	
	public LinkedList<Pillar> getObstacles()
	{
		return pillar;
	}
	
	public void move()
	{
		boolean del = false;
		for (Pillar p : pillar)
		{
			p.setX(p.getX() - SPEED);
			if (p.getX() + Pillar.getWidLmt() <= 0)
				del = true;
		}
		if (del)
			pillar.remove();
		Pillar tmp = pillar.getLast();
		if (tmp.getX() + Pillar.getWidLmt() + Pillar.getWidGap() <= border)
			pillar.add(new Pillar(this.border,getRandomHeight()));
	}
}

class Pillar {
	int height;
	int x;
	private static final int WIDGAP = 200;
	private static final int HEIGAP = 150;
	private static final int WIDLMT = 100;
	private static final int HEILMT = 300;
	Pillar(int x,int height)
	{
		this.x = x;
		this.height = height;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public static int getWidGap()
	{
		return WIDGAP;
	}
	
	public static int getHeiGap()
	{
		return HEIGAP;
	}
	
	public static int getWidLmt()
	{
		return WIDLMT;
	}
	
	public static int getHeiLmt()
	{
		return HEILMT;
	}
}



