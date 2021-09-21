package me.bluper.cavehopper.level.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.biome.Biome;
import me.bluper.cavehopper.biome.Biomes;
import me.bluper.cavehopper.biome.feature.Feature;
import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.block.Blocks;
import me.bluper.cavehopper.level.BlockPos;
import me.bluper.cavehopper.level.BlockSet;
import me.bluper.cavehopper.level.Chunk;

public class ChunkGenerator
{
	private Random rand;
	private Cavehopper game = Cavehopper.getInstance();
	private OpenSimplexNoise noise;

	public ChunkGenerator(long seed)
	{
		rand = new Random(seed);
		this.noise = new OpenSimplexNoise(seed);
	}

	public Chunk generateChunk(int x, int y)
	{
		Chunk chunk = new Chunk(x, y);

		chunk.forEachPos(pos ->
		{
			Block block;
			Biome biome = Biomes.getBiome(chunk.getBlockPosInWorld(pos), noise, game.biomeSize);
			chunk.setBiome(pos, biome);
			if (y >= 0 && y < 64)
			{
				if (pos.y < rand.nextInt(32) && y == 0) block = Blocks.SILT;
				else if (pos.y > rand.nextInt(32) && y == 63) block = Blocks.AIR;
				else block = biome.getProperties().getFillerBlock();
			}
			else if (y < 0) block = Blocks.SILT;
			else block = Blocks.AIR;

			chunk.setBlock(pos, block);
			chunk.setWall(pos, block);
		});

		return chunk;
	}

	public void generateFeaturesAt(Chunk chunk)
	{
		Random rand = new Random(game.level.getSeed() + chunk.getX()/2 + chunk.getY()/2);
		if (chunk.getY() > 0)
			chunk.forEachPos(pos ->
			{
				BlockPos wPos = chunk.getBlockPosInWorld(pos);
				List<Feature> features = new ArrayList<Feature>();
				for (Feature f : chunk.getBiome(pos).getFeatures())
				{
					if (f.isNoise())
					{
						double val = noise.eval((float)(wPos.x)/20, (float)(wPos.y)/20);
						if (val > f.getNoiseThresh()) features.add(f);
					}
					else
					{
						short r = (short)rand.nextInt(1000);
						if (f.getRarity() + 800 < r) features.add(f);
					}
				}
				features.forEach(f ->
				{
					if (game.level.getBlock(wPos) != f.getFillerBlock())
					{
						BlockSet gend = f.generate(wPos, rand);
						gend.placeInLevel(game.level, wPos);
					}
				});
			});
	}
}
