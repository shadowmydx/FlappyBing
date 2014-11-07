import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.awt.event.*;

public interface DrawBoard {
	public void draw();
}

class SimpleDraw extends JPanel implements DrawBoard {
	Bing bing;
	Obstacle obs;
	final LinkedList<Pillar> pillar;

	SimpleDraw(Bing bing,Obstacle obs)
	{
		this.bing = bing;
		this.obs  = obs;
		this.pillar = obs.getObstacles();
	}
	
	public void draw()
	{
		this.repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		paintBing(g);
		paintObs(g);
	}
	
	private void paintObs(Graphics g)
	{
		g.setColor(Color.black);
		for (Pillar p : pillar)
		{
			g.fillRect(p.getX(),0,p.getWidLmt(),p.getHeight());
			g.fillRect(p.getX(),p.getHeight() + p.getHeiGap(),p.getWidLmt(),this.getHeight() - p.getHeight() - p.getHeiGap());
		}
	}
	
	private void paintBing(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillOval(bing.getX(),bing.getY(),bing.getWidth(),bing.getHeight());
	}
}