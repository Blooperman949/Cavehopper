package me.bluper.cavehopper.block;

import java.awt.Color;
import java.util.ArrayList;

import me.bluper.cavehopper.light.Light;

public class Blocks
{
	public static short bIds = 0;
	public static ArrayList<Block> allBlocks = new ArrayList<Block>();
	
	public static final Block AIR = new Block("air", bIds, new Block.Properties()
			.setSolid(false)
			.setLight(new Light((byte)2, (byte)1, Color.WHITE))
			.makeDone());
	public static final Block SILT = new Block("silt", bIds, new Block.Properties()
			.setHardness(0.0f)
			.makeDone());
	public static final Block CRUMBROCK = new Block("crumbrock", bIds, new Block.Properties()
			.setHardness(2.0f)
			.makeDone());
	public static final Block VULROCK = new Block("vulrock", bIds, new Block.Properties()
			.setHardness(3.5f)
			.makeDone());
	public static final Block BRONZESTONE = new Block("bronzestone", bIds, new Block.Properties()
			.setHardness(4.0f)
			.makeDone());
	public static final Block SANDSTONE = new Block("sandstone", bIds, new Block.Properties()
			.setHardness(0.5f)
			.makeDone());
}
