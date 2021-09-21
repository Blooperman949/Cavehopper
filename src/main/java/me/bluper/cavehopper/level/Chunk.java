package me.bluper.cavehopper.level;

import java.awt.Point;
import java.util.HashMap;
import java.util.function.Consumer;

import me.bluper.cavehopper.biome.Biome;
import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.util.Direction;

public class Chunk
{
	private int x, y;
	private WorldBlockHolder blocks = new WorldBlockHolder();

	public Chunk(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void updateLight()
	{
		for (Point p : blocks.keySet())
		{
			WorldBlock b = blocks.get(p);
			if (b.getLight() != null)
			{
				byte strength = b.getLight().getStrength();
				blocks.putCombinedLight(p, strength);
				propagateLight(p, b.getLight().getLoss());
			}
		}
	}
	
	private void propagateLight(Point pos, byte loss)
	{
		WorldBlock b = blocks.get(pos);
		for (Direction dir : Direction.values())
		{
			WorldBlock bO = blocks.get(offset(pos, dir.get()));
			byte possibleLight = (byte)(b.getCombinedLight() - loss);
			if (bO.getLight() == null || bO.getCombinedLight() < possibleLight)
			{
				bO.setCombinedLight(possibleLight);
				propagateLight(pos, loss);
			}
		}
	}

	public Point offset(Point pos, Point offset)
	{
		return new Point(pos.x + offset.x, pos.y + offset.y);
	}

	public void fillWithBlock(Block block)
	{
		forEachPos(pos -> 
		{
			setBlock(pos, block);
			setWall(pos, block);
		});
	}

	public void forEachPos(Consumer<Point> p)
	{
		for (int y = 0; y < 32; y++)
			for (int x = 0; x < 32; x++)
				p.accept(new Point(x, y));
	}

	public void setBlock(Point pos, Block block)
	{
		blocks.putBlock(pos, block);
		blocks.putLight(pos, block.getProperties().getLight());
	}

	public void setBiome(Point pos, Biome biome)
	{
		blocks.putBiome(pos, biome);
	}

	public void setWall(Point pos, Block block)
	{
		blocks.putWall(pos, block);
	}

	public BlockPos getCenter() { return new BlockPos(x*32 + 16, y*32 + 16); }
	public BlockPos getBlockPosInWorld(Point pos) { return new BlockPos(x*32+pos.x, y*32+pos.y); }
	public Point getPos() { return new Point(x, y); }	
	public int getX() { return x; }
	public int getY() { return y; }

	public HashMap<Point, WorldBlock> get() { return blocks; }
	public Block getBlock(Point pos) { return blocks.get(pos).getBlock(); }
	public Block getBlock(int x, int y) { return getBlock(new Point(x, y)); }	
	public Block getWall(Point pos) { return blocks.get(pos).getWall(); }	
	public Block getWall(int x, int y) { return getWall(new Point(x, y)); }
	public Biome getBiome(Point pos) { return blocks.get(pos).getBiome(); }
	public Biome getBiome(int x, int y) { return getBiome(new Point(x, y)); }
	public byte getCombinedLight(Point pos) { return blocks.get(pos).getCombinedLight(); }
	public byte getCombinedLight(int x, int y) { return getCombinedLight(new Point(x, y)); }
}
