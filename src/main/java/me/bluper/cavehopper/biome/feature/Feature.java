package me.bluper.cavehopper.biome.feature;

import java.util.Random;

import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.level.BlockPos;
import me.bluper.cavehopper.level.BlockSet;

public class Feature
{
	Block fillerBlock;
	int size;
	int sizeV;
	short rarity;
	float noiseThresh;
	boolean isNoise;
	
	public Feature(short rarity, int size, int sizeV, Block fillerBlock, float noiseThresh)
	{
		this.rarity = rarity;
		this.size = size;
		this.sizeV = sizeV;
		this.fillerBlock = fillerBlock;
		this.noiseThresh = noiseThresh;
		isNoise = true;
	}
	
	public Feature(short rarity, int size, int sizeV, Block fillerBlock)
	{
		this(rarity, size, sizeV, fillerBlock, 0.0f);
		isNoise = false;
	}
	
	public BlockSet generate(BlockPos pos, Random rand) { return null; }
	
	public short getRarity() { return rarity; }
	public Block getFillerBlock() { return fillerBlock; }
	public float getNoiseThresh() { return noiseThresh; }
	public boolean isNoise() { return isNoise; }
}
