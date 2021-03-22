package me.bluper.cavehopper.res.data.block;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.res.ResourceURLFilter;
import me.bluper.cavehopper.res.Resources;

public class Blocks extends HashMap<String, Block>
{
	private static final long serialVersionUID = -338043478038066657L;
	
	public Blocks(Cavehopper game)
	{
		try {
			for (@SuppressWarnings("unused") URL u : Resources.getResourceURLs(Resources.class, new ResourceURLFilter()
			{
				@Override public boolean accept(URL u)
				{
					return u.getPath().contains("data/cavehopper/blocks/") && u.getPath().contains(".properties");
				}
			}))
			{
				String s = u.getFile();
				s = s.substring(0, s.length()-11);
				String[] ss = s.split("/");
				s = ss[(ss.length-1)];
				Properties p = new Properties();
				p.load(u.openStream());
				put(s, new Block(s,
						new BlockProperties()
							.setHardness((!p.containsKey("properties.hardness") ? 0.0f : Float.parseFloat(p.getProperty("properties.hardness"))))
							.setLight(!p.containsKey("properties.light") ? 0.0f : Float.parseFloat(p.getProperty("properties.light")))
							.setSolid(!p.containsKey("properties.solid") ? true :  Boolean.parseBoolean(p.getProperty("properties.solid"))),
						new Blockstate()
							.set(Blockstate.CON_UP, !p.containsKey("states.up") ? false : Boolean.parseBoolean((String) p.get("states.up")))
							.set(Blockstate.CON_RIGHT, !p.containsKey("states.right") ? false : Boolean.parseBoolean((String) p.get("states.right")))
							.set(Blockstate.CON_DOWN, !p.containsKey("states.down") ? false : Boolean.parseBoolean((String) p.get("states.down")))
							.set(Blockstate.CON_LEFT, !p.containsKey("states.left") ? false : Boolean.parseBoolean((String) p.get("states.left")))
							.set(Blockstate.ON, !p.containsKey("states.on") ? false : Boolean.parseBoolean((String) p.get("states.on")))
				));
			}
		}
		catch (IOException | URISyntaxException e) { game.getLogger().logException(e); }
	}
}
