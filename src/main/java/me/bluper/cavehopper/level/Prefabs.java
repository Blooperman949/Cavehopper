package me.bluper.cavehopper.level;

import java.awt.Point;

import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.block.Blocks;

@SuppressWarnings("serial")
public class Prefabs
{
	public static final BlockSet DEBUG_CROSS = new BlockSet()
	{{
		put(new Point(0, -1), Blocks.AIR);
		put(new Point(-1, 0), Blocks.AIR);
		put(new Point(0, 0), Blocks.AIR);
		put(new Point(1, 0), Blocks.AIR);
		put(new Point(0, 1), Blocks.AIR);
		put(new Point(0, 2), Blocks.AIR);
	}};

	public static BlockSet circle(int radius, Block block)
	{
		return ellipse(radius, 0, 0, block);
	}

	public static BlockSet ellipse(int radius, int var1, int var2, Block block)
	{
		BlockSet out = new BlockSet();
		int tRadius = radius + var1 + var2;
		for (int y = -tRadius; y < tRadius; y++)
			for (int x = -tRadius; x < tRadius; x++)
			{
				double val = 0;
				if (var1 != 0 && var2 != 0)
				{
					val = (Math.pow(x, 2)/Math.pow(var1,  2)) + (Math.pow(y, 2)/Math.pow(var2, 2));
					if (val < radius)
						out.putBlock(x, y, block);
				}
				else
				{
					val = Math.pow(x, 2) + Math.pow(y, 2);
					if (val < Math.pow(radius, 2))
						out.putBlock(x, y, block);
				}
			}
		return out;
	}

	public static BlockSet crack(int size, int thickness, int slope, Block block)
	{
		BlockSet out = new BlockSet();
		for (int x = -size; x < size; x++)
			for (int y = -size; y < size; y++)
			{
				if (y == slope*x) out.putBlock(x, y, block);
			}
		return out;
	}
}
