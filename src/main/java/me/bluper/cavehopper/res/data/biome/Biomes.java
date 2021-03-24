package me.bluper.cavehopper.res.data.biome;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.level.WorldPos;
import me.bluper.cavehopper.level.gen.OpenSimplexNoise;
import me.bluper.cavehopper.res.ResourceURLFilter;
import me.bluper.cavehopper.res.Resources;

public class Biomes extends HashMap<String, Biome>
{
	private static final long serialVersionUID = 5968455644391683190L;
	
	public Biomes(Cavehopper game)
	{
		try {
			for (URL u : Resources.getResourceURLs(Resources.class, new ResourceURLFilter()
			{
				@Override public boolean accept(URL u)
				{
					return u.getPath().contains("data/cavehopper/biomes/") && u.getPath().contains(".properties");
				}
			}))
			{
				String s = u.getFile();
				s = s.substring(0, s.length()-11);
				String[] ss = s.split("/");
				s = ss[(ss.length-1)];
				Properties p = new Properties();
				p.load(u.openStream());
				put(s, new Biome(s)
						.setFillerBlock(!p.containsKey("block.filler") ? game.getBlocks().get("air") : game.getBlocks().get(p.get("block.filler")))
						.setMaxY(!p.containsKey("y.max") ? 2048 : Integer.parseInt(p.getProperty("y.max")))
						.setMinY(!p.containsKey("y.min") ? 0 : Integer.parseInt(p.getProperty("y.min")))
						.setWeight(!p.containsKey("weight") ? 1 : Byte.parseByte(p.getProperty("weight")))
				);
			}
		}
		catch (IOException | URISyntaxException e) { game.getLogger().logException(e); }
	}
	
	public Biome get(String key)
	{
		final Biome b = !containsKey(key) ? get("crevices") : super.get(key);
		return b;
	}
	
	ArrayList<Biome> getFilteredBiomes(int depth)
	{
		ArrayList<Biome> out = new ArrayList<Biome>();
		for (Biome i : values())
		{
			if (depth >= i.getMinY() && depth <= i.getMaxY()) out.add(i);
		}
		return out;
	}
	
	public Biome getBiome(WorldPos pos, OpenSimplexNoise n, int biomeSize)
	{
		Random rand = new Random();
		rand.setSeed(n.getSeed());
		ArrayList<String> biomes = new ArrayList<String>();
		ArrayList<Biome> filtered = getFilteredBiomes(pos.y);
		for (Biome b : filtered)
		{
			for (int i = 0; i < b.getWeight(); i++) biomes.add(b.id);
		}
		double val = n.eval(pos.getX()/biomeSize, (pos.getY()/biomeSize - rand.nextInt(biomeSize))) + 1;
		int index = (int) Math.floor(val * (biomes.size() / 2));
		
		return (biomes.size() == 0) ? get("crevices") : get(biomes.get(index));
	}
}
