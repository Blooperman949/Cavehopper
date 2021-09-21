package me.bluper.cavehopper.biome.feature;

import java.util.Random;

import me.bluper.cavehopper.block.Blocks;
import me.bluper.cavehopper.level.BlockPos;
import me.bluper.cavehopper.level.BlockSet;
import me.bluper.cavehopper.level.Prefabs;

public class CrackCaveFeature extends Feature {

	int slopeV;
	
	public CrackCaveFeature(int size, int sizeV, int slopeV)
	{
		super((short)198, size, sizeV, Blocks.AIR);
		this.slopeV = slopeV;
	}
	
	@Override
	public BlockSet generate(BlockPos pos, Random rand)
	{
		int rSize = size;
		int slope = 1, thickness = 1;
		if (sizeV != 0)
		{
			rSize += rand.nextInt(sizeV);
			slope = rand.nextInt(slopeV)+1;
			thickness = rand.nextInt(sizeV)+1;
		}
//		System.out.println(rSize + " " + slope + " " + thickness + "  " + size + " " + sizeV);
		return Prefabs.crack(rSize, slope, thickness, fillerBlock);
	}
}
