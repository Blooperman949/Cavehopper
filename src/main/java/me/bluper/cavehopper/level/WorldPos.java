package me.bluper.cavehopper.level;

import java.awt.Point;

public class WorldPos extends Point
{
	private static final long serialVersionUID = 8367754226742284404L;
	
	public WorldPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public WorldPos(Point pos)
	{
		this.x = pos.x;
		this.y = pos.y;
	}
	
	public Point getPosInChunk()
	{
		int x, y;
		x = this.x < 0 ? this.x%-32+32 : this.x%32;
		y = this.y < 0 ? this.y%-32+32 : this.y%32;
		if (x == 32) x = 0;
		if (y == 32) y = 0;
		return new Point(x, y);
	}
	
	public Point getChunk()
	{
		int x, y;
		x = this.x < 0 ? this.x/32-1 : this.x/32;
		y = this.y < 0 ? this.y/32-1 : this.y/32;
		return new Point(x, y);
	}
	
	public Point getPosOnScreen(int w, int h, float orX, float orY, int res)
	{
		return new WorldPos(getXOnScreen(this.x, w, orX, res), getYOnScreen(this.y, h, orY, res));
	}
	
	public static int getXOnScreen(int x, int w, float orX, int res)
	{
		return (int) ((x - orX) * res + w/2);
	}
	
	public static int getYOnScreen(int y, int h, float orY, int res)
	{
		return (int) ((y - orY) * res + h/2);
	}
	
	@Override public String toString() { return "(" + x + ", " + y + ")"; }
}
