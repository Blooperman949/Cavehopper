package me.bluper.cavehopper.res.data.block;

public class BlockProperties
{
	float hardness = 1.0f;
	float light = 0.0f;
	boolean solid = true;
	
	public BlockProperties setHardness(float hardness)
	{
		this.hardness = hardness;
		return this;
	}
	
	public BlockProperties setLight(float light)
	{
		this.light = light;
		return this;
	}
	
	public BlockProperties setSolid(boolean solid)
	{
		this.solid = solid;
		return this;
	}
}
