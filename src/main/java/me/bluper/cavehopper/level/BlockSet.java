package me.bluper.cavehopper.level;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

import me.bluper.cavehopper.block.Block;

public class BlockSet extends HashMap<Point, Block>
{
	Point minP = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
	Point maxP = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

	public void placeInLevel(Level level, BlockPos pos)
	{
		for (Point i : keySet())
		{
			BlockPos wPos = new BlockPos(pos.x + i.x, pos.y + i.y);
			level.setBlock(wPos, get(i));
		}
	}

	public void putBlock(Point pos, Block block)
	{
		put(pos, block);
		if (pos.x < minP.x) minP.x = pos.x;
		if (pos.x > maxP.x) maxP.x = pos.x;
		if (pos.y < minP.y) minP.y = pos.y;
		if (pos.y > maxP.y) maxP.y = pos.y;
	}
	
	public void putBlock(int x, int y, Block block)
	{
		putBlock(new Point(x, y), block);
	}

	public Dimension getBoundBox()
	{
		return new Dimension(maxP.x - minP.x, maxP.y - minP.y);
	}
	
	private static final long serialVersionUID = -4353022482292465597L;
}
