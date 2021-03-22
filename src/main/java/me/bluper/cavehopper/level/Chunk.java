package me.bluper.cavehopper.level;

import java.awt.Point;
import java.util.HashMap;

import me.bluper.cavehopper.res.data.block.Block;


public class Chunk
{
	int x, y;
	HashMap<Point, Block> blocks = new HashMap<Point, Block>(1024);
	
	public Chunk(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void fillWithBlock(Block block)
	{
		for (int i = 0; i < 32; i++)
		{
			for (int j = 0; j < 32; j++)
			{
				blocks.put(new Point(i, j), new Block(block, i, j));
			}
		}
	}
	
	public void setBlock(Point pos, Block block)
	{
		blocks.put(pos, block);
	}
	
	public HashMap<Point, Block> getBlocks()
	{
		return blocks;
	}
	
	public WorldPos getCenter()
	{
		return new WorldPos(x*32 + 16, y*32 + 16);
	}
	
	public WorldPos getBlockPos(Point pos)
	{
		return new WorldPos(x*32+pos.x, y*32+pos.y);
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	public Block getBlock(Point pos)
	{
		return blocks.get(pos);
	}
}
