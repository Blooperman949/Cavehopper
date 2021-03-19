package me.bluper.cavehopper.level.block;

public class OreBlock extends Block
{
	int minGenY;
	int maxGenY;
	
	public OreBlock(String id)
	{
		super(id);
	}
	
	public OreBlock(int x, int y, String id)
	{
		super(x, y, id);
	}
	
	public OreBlock(String id, int minGenY, int maxGenY)
	{
		super(id);
		this.minGenY = minGenY;
		this.maxGenY = maxGenY;
	}
	
	public boolean isInGenRange(int y) { return minGenY < y && y < maxGenY; }
}
