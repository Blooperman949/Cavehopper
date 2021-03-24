package me.bluper.cavehopper.level;

import java.awt.Point;
import java.util.HashMap;

import me.bluper.cavehopper.res.data.block.Block;
import me.bluper.cavehopper.res.data.block.Blockstate;
import static me.bluper.cavehopper.Cavehopper.game;


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
	
	public void update()
	{
		game.getLogger().log("Updating Chunk: (" + x + ", " + y + ") ");
		for (Block b : blocks.values())
		{
			if (b.getX() > 0 && b.getY() > 0 && b.getX() < 31 && b.getY() < 31)
			{
				if (this.getBlock(new Point(b.getX(), b.getY()-1)).getId().equals(b.getId())) b.setState(Blockstate.CON_UP, true);
				else b.setState(Blockstate.CON_UP, false);
				if (this.getBlock(new Point(b.getX(), b.getY()+1)).getId().equals(b.getId())) b.setState(Blockstate.CON_DOWN, true);
				else b.setState(Blockstate.CON_DOWN, false);
				if (this.getBlock(new Point(b.getX()-1, b.getY())).getId().equals(b.getId())) b.setState(Blockstate.CON_LEFT, true);
				else b.setState(Blockstate.CON_LEFT, false);
				if (this.getBlock(new Point(b.getX()+1, b.getY())).getId().equals(b.getId())) b.setState(Blockstate.CON_RIGHT, true);
				else b.setState(Blockstate.CON_RIGHT, false);
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
