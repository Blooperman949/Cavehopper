package me.bluper.cavehopper.res.data.biome;

import me.bluper.cavehopper.res.data.block.Block;

public class Biome
{
	Block fillerBlock;
	int maxY = 2048, minY = 0;
	String id;
	byte weight;
	
	public Biome(String id)
	{
		this.id = id;
	}
	
	public Biome setFillerBlock(Block fillerBlock)
	{
		this.fillerBlock = fillerBlock;
		return this;
	}
	
	public Biome setMaxY(int maxY)
	{
		this.maxY = maxY;
		return this;
	}
	
	public Biome setMinY(int minY)
	{
		this.minY = minY;
		return this;
	}
	
	public Biome setWeight(byte weight)
	{
		this.weight = weight;
		return this;
	}
	
	public Block getFillerBlock() { return fillerBlock; }
	public int getMinY() { return minY; }
	public int getMaxY() { return maxY; }
	public byte getWeight() { return weight; }
}
