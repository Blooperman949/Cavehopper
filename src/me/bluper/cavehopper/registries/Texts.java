package me.bluper.cavehopper.registries;

import me.bluper.cavehopper.Cavehopper;

public enum Texts
{
	BLOCK_AIR("Empty Air"),
	BLOCK_CRUMBROCK("Crumbrock"),
	BLOCK_VULROCK("Vulrock"),
	BLOCK_SANDSTONE("Sandstone"),
	BLOCK_SILT("Silt"),
	BLOCK_IRONIDE_ORE("Ironide Ore"),
	
	GAME_TITLE("Cavehopper " + Cavehopper.VERSION);
	
	String text;
	Texts(String text)
	{
		this.text = text;
	}
	
	public String get()
	{
		return text;
	}
}
