package me.bluper.cavehopper.res.data.block;

public class Block
{
	BlockProperties prop;
	Blockstate state;
	String id;
	int x, y;
	
	public Block(String id, BlockProperties prop, Blockstate state)
	{
		this.id = id;
		this.prop = prop;
		this.state = state;
	}
	
	public Block (Block b, int x, int y)
	{
		this.id = b.getId();
		this.prop = b.getProperties();
		this.state = b.getState();
		this.x = x;
		this.y = y;
	}
	
	public String getId() { return id; }	
	public Blockstate getState() { return state; }
	public BlockProperties getProperties() { return prop; }
	public int getX() { return x; }
	public int getY() { return y; }
}
