package me.bluper.cavehopper.level.gen;

import java.awt.Point;
import java.util.Random;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.level.Chunk;
import me.bluper.cavehopper.res.data.block.Block;
import me.bluper.cavehopper.res.data.block.Blocks;

public class ChunkGenerator
{
	Random rand = new Random();
	Cavehopper game;
	long seed;
	OpenSimplexNoise noise;
	
	public ChunkGenerator(long seed, Cavehopper game)
	{
		rand.setSeed(seed);
		this.game = game;
		this.seed = seed;
		this.noise = new OpenSimplexNoise(seed);
	}

	public Chunk generateChunk(int x, int y, Blocks blocks)
	{
		Chunk chunk = new Chunk(x, y);

		for (int i = 0; i < 32; i++)
		{
			for (int j = 0; j < 32; j++)
			{
				Block block;
				Point pos = new Point(i, j);
				if (y >= 0 && y < 64)
				{
					if (j < rand.nextInt(32) && y == 0) block = blocks.get("silt");
					else if (j > rand.nextInt(32) && y == 63) block = blocks.get("air");
					else block = game.getBiomes().getBiome(chunk.getBlockPos(new Point(i, j)), noise, game.biomeSize).getFillerBlock();
				}
				else if (y < 0) block = blocks.get("silt");
				else block = blocks.get("air");
//				if (j == 0 && i == 0) block = blocks.get("vulrock");
				chunk.setBlock(pos, new Block(block, i, j));
			}
		}
		return chunk;
	}
}
