package me.bluper.cavehopper.util;

import java.awt.Point;

public enum Direction
{
	UP(0, -1),
	RIGHT(1, 0),
	DOWN(0, 1),
	LEFT(-1, 0);
	
	int x, y;
	
	Direction(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point get()
	{
		return new Point(x, y);
	}
}
