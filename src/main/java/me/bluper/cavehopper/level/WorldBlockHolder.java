package me.bluper.cavehopper.level;

import java.awt.Point;
import java.util.HashMap;

import me.bluper.cavehopper.biome.Biome;
import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.light.Light;

public class WorldBlockHolder extends HashMap<Point, WorldBlock>
{	
	public void putBlock(Point pos, Block block)
	{
		if (containsKey(pos))
			get(pos).setBlock(block);
		else
		{
			WorldBlock wb = new WorldBlock();
			wb.setBlock(block);
			put(pos, wb);
		}
	}
	public void putBiome(Point pos, Biome biome)
	{
		if (containsKey(pos))
			get(pos).setBiome(biome);
		else
		{
			WorldBlock wb = new WorldBlock();
			wb.setBiome(biome);
			put(pos, wb);
		}
	}
	public void putWall(Point pos, Block wall)
	{
		if (containsKey(pos))
			get(pos).setWall(wall);
		else
		{
			WorldBlock wb = new WorldBlock();
			wb.setWall(wall);
			put(pos, wb);
		}
	}
	public void putLight(Point pos, Light light)
	{
		if (containsKey(pos))
			get(pos).setLight(light);
		else
		{
			WorldBlock wb = new WorldBlock();
			wb.setLight(light);
			put(pos, wb);
		}
	}
	public void putCombinedLight(Point pos, byte combinedLight)
	{
		if (containsKey(pos))
			get(pos).setCombinedLight(combinedLight);
		else
		{
			WorldBlock wb = new WorldBlock();
			wb.setCombinedLight(combinedLight);
			put(pos, wb);
		}
	}

	private static final long serialVersionUID = 982374281714945748L;
}
