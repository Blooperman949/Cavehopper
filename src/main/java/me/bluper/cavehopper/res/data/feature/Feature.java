package me.bluper.cavehopper.res.data.feature;

import java.util.ArrayList;

import me.bluper.cavehopper.res.data.block.Block;

public class Feature
{
	private int size;
	private Block block;
	private ArrayList<String> biomes;
	
	public Feature()
	{
		this.biomes = new ArrayList<String>();
	}
	
	public Feature setBlock(Block block)
	{
		this.block = block;
		return this;
	}
	
	public Feature setSize(int size)
	{
		this.size = size;
		return this;
	}
		
	public int getSize() { return size; }
	public Block getBlock() { return block; }
	public boolean canBeIn(String biome) { return biomes.contains(biome); }
	public ArrayList<String> getBiomes() { return biomes; }
}
