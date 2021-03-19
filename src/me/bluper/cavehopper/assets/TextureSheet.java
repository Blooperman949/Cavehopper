package me.bluper.cavehopper.assets;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import me.bluper.cavehopper.Cavehopper;

public class TextureSheet
{
	BufferedImage sheet;
	HashMap<String, Point> map;
	private int res;
	
	/**
	 * Instantiates a new texture sheet image and stitches all the images
	 * in the specified directory together, assigning a Point and String (id)
	 * to each texture as it goes, so that each texture on the sheet may be
	 * referenced by name. 
	 * @param assetsDir The directory to grab images from.
	 * @param size The width/height of the texture sheet in pixels.
	 */
	public TextureSheet(String path, int size, int res, Cavehopper game)
	{
		this.res = res;
		path = getFile(path);
		System.out.println("Path: " + path);
		sheet = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		map = new HashMap<String, Point>((int) (Math.pow(size, 2)/res));
		Point currentPos = new Point();
		Graphics g = sheet.getGraphics();
		for(File f : new File(path).listFiles())
		{
			try
			{
				g.drawImage(ImageIO.read(f).getScaledInstance(res, res, Image.SCALE_FAST), currentPos.x, currentPos.y, null);
			}
			catch (IOException e) { e.printStackTrace(); }
			String texName = f.getName().substring(0, f.getName().length()-4);
			map.put(texName, new Point(currentPos.x, currentPos.y));
			if (currentPos.x < size - res) currentPos.x += res;
			else if (currentPos.y < size - res)
			{
				currentPos.x = 0;
				currentPos.y += res;
			}
			else break;
			System.out.println("Stitching Texture at (" + map.get(texName).x + ", " + map.get(texName).y + ") from file " + f.getPath());
		}
	}
	
	/**
	 * @param texture The name of the texture.
	 * @return The texture in the sheet with the specified name.
	 */
	public Image getTextureByName(String texture)
	{
		return sheet.getSubimage(map.get(texture).x, map.get(texture).y, res, res);
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
	
	public String getFile(String path)
	{
		return Thread.currentThread().getContextClassLoader().getResource(path).getPath();
	}
	
}
