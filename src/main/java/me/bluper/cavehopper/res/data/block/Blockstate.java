package me.bluper.cavehopper.res.data.block;

public class Blockstate
{
	private boolean[] states = new boolean[5];
	
	public static final byte CON_UP = 0;
	public static final byte CON_RIGHT = 1;
	public static final byte CON_DOWN = 2;
	public static final byte CON_LEFT = 3;
	public static final byte ON = 4;
	
	public Blockstate()
	{
		
	}
	
	public boolean get(byte stateid)
	{
		return states[stateid];
	}
	
	public Blockstate set(byte stateid, boolean state)
	{
		states[stateid] = state;
		return this;
	}
	
	@Override
	public String toString()
	{
		String out = "Blockstate: [ ";
		for (boolean i : states) out += i + " ";
		return out + "]";
	}
}
