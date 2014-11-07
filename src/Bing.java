import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public interface Bing {
	public void beginUp();
	public void action();
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public int getWidth();
	public int getHeight();
}

interface State {
	public void move();
	public void changeState();
	public void setInit(int speed);
}

class DownState implements State {
	SimpleBing bing;
	private static final int DOWN_SPEED = 3;
	private int downSpeed = 3;
	
	DownState(SimpleBing bing)
	{
		this.bing = bing;
	}
	
	public void move()
	{
		bing.setY(bing.getY() + downSpeed);
		//downSpeed ++;
		//if (downSpeed > DOWN_SPEED)
			//downSpeed = DOWN_SPEED;
	}
	
	public void changeState()
	{
		bing.setState(bing.getUpState());
	}
	
	public void setInit(int speed)
	{
		this.downSpeed = speed;
	}
}

class UpState implements State {
	SimpleBing bing;
	private int upSpeed = 30;
	private static final int SPEED = 3;
	private static final int DES = 1;
	
	UpState(SimpleBing bing)
	{
		this.bing = bing;
	}
	
	public void move()
	{			
		bing.setY(bing.getY() - SPEED);
		upSpeed -= DES;
		if (upSpeed <= 0)
			this.changeState();
	}
	
	public void changeState()
	{
		bing.setState(bing.getDownState());
		//bing.getDownState().setInit(0);
	}
	
	public void setInit(int speed)
	{
		this.upSpeed = speed;
	}
}

class SimpleBing extends JPanel implements Bing {
	
	
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	private static final int UP_SPEED = 30;
	private int x;
	private int y;
	State nowState;
	DownState down;
	UpState up;
	SimpleBing(int x,int y)
	{
		this.x = x;
		this.y = y;
		down = new DownState(this);
		up   = new UpState(this);
		nowState = down;
		//nowState.setInit(0);
	}
	
	public void setState(State state)
	{
		nowState = state;
	}
	
	public void action()
	{
		nowState.move();
		if (this.getY() < 0)
			this.setY(0);
	}
	
	public void beginUp()
	{
		up.setInit(UP_SPEED);
		nowState = up;
	}
	
	public State getDownState()
	{
		return down;
	}
	
	public State getUpState()
	{
		return up;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	public int getHeight()
	{
		return HEIGHT;
	}
}