package me.bluper.cavehopper.res.assets;

import java.io.IOException;
import java.util.Properties;

import me.bluper.cavehopper.Cavehopper;

public class Texts
{
	Properties prop = new Properties();
	Cavehopper game;
	
	public Texts(String lang)
	{
		game = Cavehopper.getInstance();
		try
		{
			prop.load(getClass().getClassLoader().getResourceAsStream("assets/cavehopper/lang/" + lang + ".properties"));
		}
		catch (IOException e) { game.getLogger().logException(e); }
	}
	
	public String get(String key1, String key2, String key3)
	{
		return prop.getProperty(key1 + "." + key2 + "." + key3);
	}
	public String get(String key1, String key2)
	{
		return prop.getProperty(key1 + "." + key2);
	}
	public String get(String key1)
	{
		return prop.getProperty(key1);
	}
}
