import java.util.*;

interface Judge {
	public int isGameOver(Bing fang,Obstacle obs);
}

class SimpleJudge implements Judge {

	final LinkedList<Pillar> tmp;
	Pillar now;
	private int bottom;
	
	SimpleJudge(Bing fang,Obstacle obs)
	{
		tmp = obs.getObstacles();
		this.bottom = 600 - fang.getHeight();
		now  = tmp.peek();
	}
	
	public int isGameOver(Bing fang,Obstacle obs) // 0表示死，1表示活，2表示得分
	{
		if (fang.getY() + fang.getHeight() >= bottom)
			return 0;
		if (now.getWidLmt() + now.getX() <= fang.getX())
		{
			for (Pillar p : tmp)
			{
				if (p.getX() > fang.getX())
				{
					now = p;
					break;
				}
			}
			return 2;
		}
		if (fang.getX() < now.getX())
			return 1;
		if (fang.getY() > now.getHeight() && fang.getY() + fang.getHeight() < now.getHeight() + now.getHeiGap())
			return 1;
		else
			return 0;
	}
}