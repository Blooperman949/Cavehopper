package me.bluper.cavehopper.level;

import java.awt.Point;

import me.bluper.cavehopper.level.block.Block;

public class WorldPos extends Point
{
	private static final long serialVersionUID = 8367754226742284404L;
	
	public WorldPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point getPosInChunk()
	{
		return new Point(x%32, y%32);
	}
	
	public Point getChunk()
	{
		return new Point(x/32, y/32);
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
	
	public static WorldPos getBlockPosInWorld(Block b, Chunk c)
	{
		return new WorldPos(c.getX()*32 + b.getX(), c.getY()*32 + b.getY());
	}
	
	@Override public String toString() { return "(" + x + ", " + y + ")"; }
}
