package me.bluper.cavehopper.level.block;

public class Block
{
	public String id;
	int x;
	int y;
	boolean[] states;
	
	public Block() {}
	
	public Block(int x, int y, String id)
	{
		this(id);
		this.x = x;
		this.y = y;
		fillStatesEmpty();
	}
	
	public Block(String id)
	{
		this.id = id;
	}
	
	void fillStatesEmpty()
	{
		states = new boolean[]{false, false, false, false, false};
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
}
