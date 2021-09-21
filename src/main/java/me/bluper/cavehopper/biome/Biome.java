package me.bluper.cavehopper.biome;

import me.bluper.cavehopper.biome.feature.Feature;
import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.block.Blocks;

public class Biome
{
	String id;
	short saveId;
	Properties prop;
	Feature[] features;
	
	public Biome(String id, short saveId, Properties prop, Feature[] features, boolean normal)
	{
		this.id = id;
		this.saveId = saveId;
		this.prop = prop;
		this.features = features;
		Biomes.bIds++;
		if(normal) Biomes.allBiomes.add(this);
	}
	
	public Biome(String id, short saveId, Properties prop, Feature[] features)
	{
		this(id, saveId, prop, features, true);
	}
	
	public Biome(String id, short saveId, Properties prop)
	{
		this(id, saveId, prop, new Feature[]{}, true);
	}
	
	public Biome(String id, short saveId, Properties prop, boolean normal)
	{
		this(id, saveId, prop, new Feature[]{}, normal);
	}
	
	public static class Properties
	{
		private Block fillerBlock = Blocks.CRUMBROCK;
		private int maxY = 2048, minY = 0;
		
		private boolean done = false;
		
		public Properties setFillerBlock(Block fillerBlock)
		{
			if(!done) this.fillerBlock = fillerBlock;
			return this;
		}
		
		public Properties setMinMaxY(int minY, int maxY)
		{
			if(!done)
			{
				this.minY = minY;
				this.maxY = maxY;
			}
			return this;
		}
		
		public Properties makeDone()
		{
			done = true;
			return this;
		}
		
		public Block getFillerBlock() { return fillerBlock; }
		public int getMinY() { return minY; }
		public int getMaxY() { return maxY; }
	}
	
	public String getId() { return id; }
	public short getSaveId() { return saveId; }
	public Properties getProperties() { return prop; }
	public Feature[] getFeatures() { return features; }
}
