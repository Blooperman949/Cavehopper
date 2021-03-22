package me.bluper.cavehopper.res.assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.game.GameLogger;
import me.bluper.cavehopper.res.ResourceURLFilter;
import me.bluper.cavehopper.res.Resources;

public class TextureSheet
{
	BufferedImage sheet;
	HashMap<String, Point> map;
	private int res;
	GameLogger logger;

	/**
	 * Instantiates a new texture sheet image and stitches all the images
	 * in the specified directory together, assigning a Point and String (id)
	 * to each texture as it goes, so that each texture on the sheet may be
	 * referenced by name. 
	 * @param assetsDir The directory to grab images from.
	 * @param size The width/height of the texture sheet in pixels.
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public TextureSheet(String path, int size, int res, Cavehopper game) throws IOException, URISyntaxException
	{
		logger = game.getLogger();
		ArrayList<URL> files = new ArrayList<URL>();
		for (URL u : Resources.getResourceURLs(Resources.class, new ResourceURLFilter()
		{
			@Override public boolean accept(URL u)
			{
				String s = u.getFile();
				return s.endsWith(".png") && s.contains(path);
			}
		}))
		{
			files.add(u);
		}

		this.res = res;
		sheet = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		map = new HashMap<String, Point>((int) (Math.pow(size, 2)/res));
		Point currentPos = new Point();
		Graphics g = sheet.getGraphics();

		for(URL f : files)
		{
			g.drawImage(ImageIO.read(f).getScaledInstance(res, res, Image.SCALE_FAST), currentPos.x, currentPos.y, null);
			File texPath = new File(f.getFile());
			String texName = texPath.getName().substring(0, texPath.getName().length()-4);
			map.put(texName, new Point(currentPos.x, currentPos.y));
			if (currentPos.x < size - res) currentPos.x += res;
			else if (currentPos.y < size - res)
			{
				currentPos.x = 0;
				currentPos.y += res;
			}
			else break;
			logger.log("Stitching Texture \"" + texName + "\" at (" + map.get(texName).x + ", " + map.get(texName).y + ") from " + f);
		}
	}

	/**
	 * @param texture The name of the texture.
	 * @return The texture in the sheet with the specified name.
	 */
	public Image getTextureByName(String texture)
	{
		Image out;
		if (map.containsKey(texture)) 
			out = sheet.getSubimage(map.get(texture).x, map.get(texture).y, res, res);
		else
		{
			out = new BufferedImage(res, res, BufferedImage.TYPE_INT_RGB);
			Graphics g = out.getGraphics();
			g.setColor(Color.MAGENTA);
			g.fillRect(0, 0, res, res);
		}
		return out;
	}

	/**
	 * @return The entire stitched texture sheet. Mainly for debugging.
	 */
	public Image getSheet()
	{
		return sheet;
	}

	/**
	 * @return The resolution of the textures in this sheet (8x, 64x, etc.)
	 */
	public int getRes()
	{
		return res;
	}
}
