package me.bluper.cavehopper.level.gen;

import java.awt.Point;
import java.util.Random;

import me.bluper.cavehopper.level.Chunk;
import me.bluper.cavehopper.level.block.Block;
import me.bluper.cavehopper.registries.Blocks;

public class ChunkGenerator
{
	Random rand = new Random();
	public ChunkGenerator(long seed)
	{
		rand.setSeed(seed);
	}
	
	public Chunk generateChunk(int x, int y)
	{
		Chunk chunk = new Chunk(x, y);
		if (y == 0 && x == 0) chunk.fillWithBlock(new Block(Blocks.CRUMBROCK.id));
		else if (y >= 0)
		{
			for (int i = 0; i < 32; i++)
			{
				for (int j = 0; j < 32; j++)
				{
					//System.out.print("Generating block at: (" + i + ", " + j + ") in chunk (" + chunk.getX() + ", " + chunk.getY() + "), Block: ");
					Point pos = new Point(i, j);
					String block = Blocks.AIR.id;
					if (j < rand.nextInt(32) & y == 0) block = Blocks.SILT.id;
					else block = Blocks.VULROCK.id;
					chunk.setBlock(pos, new Block(i, j, block));
					//System.out.println(block);
				}
			}
		}
		else chunk.fillWithBlock(new Block(Blocks.SILT.id));
		return chunk;
	}
	
	public Chunk getChunkWithOre(Block block, Chunk chunk)
	{
		return chunk;
	}
}
