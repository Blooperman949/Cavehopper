package me.bluper.cavehopper.level.gen;

import java.awt.Point;
import java.util.Random;

import me.bluper.cavehopper.level.Chunk;
import me.bluper.cavehopper.res.data.block.Block;
import me.bluper.cavehopper.res.data.block.Blocks;

public class ChunkGenerator
{
	Random rand = new Random();
	public ChunkGenerator(long seed)
	{
		rand.setSeed(seed);
	}

	public Chunk generateChunk(int x, int y, Blocks blocks)
	{
		Chunk chunk = new Chunk(x, y);

		for (int i = 0; i < 32; i++)
		{
			for (int j = 0; j < 32; j++)
			{
				Block block = blocks.get("air");
				Point pos = new Point(i, j);
				if (j == 0 && i == 0) block = blocks.get("air");
				else if (y >= 0)
				{
					if (j < rand.nextInt(32) & y == 0) block = blocks.get("silt");
					else block = blocks.get("crumbrock");
				}
				else block = blocks.get("silt");
				chunk.setBlock(pos, new Block(block, i, j));
			}
		}
		return chunk;
	}

	public Chunk getChunkWithOre(Block block, Chunk chunk)
	{
		return chunk;
	}
}
