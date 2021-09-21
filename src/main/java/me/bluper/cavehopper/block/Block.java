package me.bluper.cavehopper.block;

import me.bluper.cavehopper.light.Light;

public class Block
{
	Properties prop;
	String id;
	short saveId;

	public Block(String id, short saveId, Properties prop)
	{
		this.id = id;
		this.saveId = saveId;
		this.prop = prop;
		Blocks.bIds++;
		Blocks.allBlocks.add(this);
	}

	public String getId() { return id; }
	public short getSaveId() { return saveId; }
	public Properties getProperties() { return prop; }

	public static class Properties
	{
		float hardness = 1.0f;
		Light light;
		boolean solid = true;
		boolean done = false;

		public Properties setHardness(float hardness)
		{
			if(!done) this.hardness = hardness;
			return this;
		}

		public Properties setLight(Light light)
		{
			if(!done) this.light = light;
			return this;
		}

		public Properties setSolid(boolean solid)
		{
			if(!done) this.solid = solid;
			return this;
		}
		
		public Properties clone()
		{
			return new Properties()
					.setHardness(hardness)
					.setLight(light)
					.setSolid(solid)
					.makeDone();
		}
		
		public Properties makeDone()
		{
			done = true;
			return this;
		}
		
		public float getHardness() { return hardness; }
		public Light getLight()
		{
			return light;
		}
		public boolean getSolid() { return solid; }
	}
}
