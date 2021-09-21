package me.bluper.cavehopper.biome;

import java.util.ArrayList;
import java.util.Random;

import me.bluper.cavehopper.biome.feature.Feature;
import me.bluper.cavehopper.biome.feature.Features;
import me.bluper.cavehopper.block.Blocks;
import me.bluper.cavehopper.level.BlockPos;
import me.bluper.cavehopper.level.gen.OpenSimplexNoise;

public class Biomes
{
	public static short bIds = 0;
	public static ArrayList<Biome> allBiomes = new ArrayList<Biome>();

	public static final Biome SILT = new Biome("silt", bIds, new Biome.Properties()
			.makeDone(), false);
	public static final Biome OCEAN_FLOOR = new Biome("ocean_floor", bIds, new Biome.Properties()
			.setFillerBlock(Blocks.SANDSTONE)
			.makeDone());
	public static final Biome CREVICES = new Biome("crevices", bIds, new Biome.Properties()
			.setFillerBlock(Blocks.CRUMBROCK)
			.makeDone(),
			new Feature[]
					{
							Features.CRACK_CAVE
					});
	public static final Biome STONEDEEP = new Biome("stonedeep", bIds, new Biome.Properties()
			.setFillerBlock(Blocks.BRONZESTONE)
			.makeDone(),
			new Feature[]
					{
							Features.ROUND_CAVE
					});
	public static final Biome LAVA_TUBES = new Biome("lava_tubes", bIds, new Biome.Properties()
			.setFillerBlock(Blocks.VULROCK)
			.makeDone());

	public static Biome getBiome(BlockPos pos, OpenSimplexNoise n, int biomeSize)
	{
		Random rand = new Random();
		rand.setSeed(n.getSeed());
		double val = n.eval(pos.getX()/biomeSize, (pos.getY()/biomeSize - rand.nextInt(biomeSize))) + 1;
		int index = (int) Math.floor(val * (allBiomes.size()/2));

		return allBiomes.get(index);
	}
}
