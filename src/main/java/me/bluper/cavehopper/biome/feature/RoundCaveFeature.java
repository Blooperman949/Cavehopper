package me.bluper.cavehopper.biome.feature;

import java.util.Random;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.level.BlockPos;
import me.bluper.cavehopper.level.BlockSet;
import me.bluper.cavehopper.level.Level;
import me.bluper.cavehopper.level.Prefabs;

public class RoundCaveFeature extends CaveFeature
{
	Level level = Cavehopper.getInstance().level;

	public RoundCaveFeature(int size, int sizeV)
	{
		super(size, sizeV, 0.3f);
	}

	@Override
	public BlockSet generate(BlockPos pos, Random rand)
	{
		int rSize = size;
		int var1 = 1, var2 = 1;
		if (sizeV != 0)
		{
			rSize += rand.nextInt(sizeV);
			var1 = rand.nextInt(sizeV);
			var2 = rand.nextInt(sizeV);
		}
		return Prefabs.ellipse(rSize, var1, var2, fillerBlock);
		//		return Prefabs.DEBUG_CROSS;
	}
}
