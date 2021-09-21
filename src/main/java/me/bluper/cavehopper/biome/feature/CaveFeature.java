package me.bluper.cavehopper.biome.feature;

import me.bluper.cavehopper.block.Blocks;

public class CaveFeature extends Feature
{
	public CaveFeature(int size, int sizeV, float noiseThresh)
	{
		super((short)0, size, sizeV, Blocks.AIR, noiseThresh);
	}
}
