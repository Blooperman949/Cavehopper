package me.bluper.cavehopper.level;

import me.bluper.cavehopper.biome.Biome;
import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.light.Light;

public class WorldBlock
{
	private Block block;
	private Biome biome;
	private Block wall;
	private Light light;
	private byte combinedLight;
	
	public void setBlock(Block block) { this.block = block; }
	public void setBiome(Biome biome) { this.biome = biome; }
	public void setWall(Block wall) { this.wall = wall; }
	public void setLight(Light light) { this.light = light; }
	public void setCombinedLight(byte combinedLight) { this.combinedLight = combinedLight; }
	
	public Block getBlock() { return block; }
	public Biome getBiome() { return biome; }
	public Block getWall() { return wall; }
	public Light getLight() { return light; }
	public byte getCombinedLight() { return combinedLight; }
}
