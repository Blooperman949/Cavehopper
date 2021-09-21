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
import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.game.GameLogger;
import me.bluper.cavehopper.level.BlockPos;
import me.bluper.cavehopper.level.Level;
import me.bluper.cavehopper.res.ResourceURLFilter;
import me.bluper.cavehopper.res.Resources;
import me.bluper.cavehopper.util.Direction;

public class TextureSheet
{
	BufferedImage sheet;
	HashMap<String, Point> map;
	private int res;
	private int scalex;
	private int scaley;
	private Cavehopper game = Cavehopper.getInstance();
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
	public TextureSheet(String path, int size, int res, int sheetScale, int scalex, int scaley) throws IOException, URISyntaxException
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
		this.scalex = scalex;
		this.scaley = scaley;
		map = new HashMap<String, Point>((int) (Math.pow(size, 2)));
		sheet = new BufferedImage(size*res*sheetScale, size*res*sheetScale, BufferedImage.TYPE_INT_ARGB);
		Point currentPos = new Point();
		Graphics g = sheet.getGraphics();

		for(URL f : files)
		{
			g.drawImage(ImageIO.read(f).getScaledInstance(res*scalex, res*scaley, Image.SCALE_FAST), currentPos.x, currentPos.y, null);
			File texPath = new File(f.getFile());
			String texName = texPath.getName().substring(0, texPath.getName().length()-4);
			map.put(texName, new Point(currentPos.x, currentPos.y));
			if (currentPos.x <= sheetScale*res*scalex - res) currentPos.x += res*scalex;
			else if (currentPos.y < sheetScale*res*scaley - res)
			{
				currentPos.x = 0;
				currentPos.y += res*scaley;
			}
			else break;
			logger.log("Stitching Texture \"" + texName + "\" at (" + map.get(texName).x + ", " + map.get(texName).y + ") from " + f);
		}
//		ImageIO.write((RenderedImage) this.getSheet(), "PNG", new File("sheet.png"));
//		ImageIO.write((RenderedImage) this.getTextureByName("crumbrock"), "PNG", new File("crumbrock.png"));
	}

	/**
	 * @param texture The name of the texture.
	 * @return The texture in the sheet with the specified name.
	 */
	private Image getTextureByName(String texture)
	{
		Image out;
		if (map.containsKey(texture)) 
			out = sheet.getSubimage(map.get(texture).x, map.get(texture).y, res*scalex, res*scaley);
		else
		{
			out = new BufferedImage(res*scalex, res*scaley, BufferedImage.TYPE_INT_RGB);
			Graphics g = out.getGraphics();
			g.setColor(Color.MAGENTA);
			g.fillRect(0, 0, res*scalex, res*scaley);
		}
		return out;
	}

	public Image getBlockTexture(String texture, boolean connected)
	{
		BufferedImage out = (BufferedImage) getTextureByName(texture);
		return connected ?
				out.getSubimage(0, 0, res, res) : out.getSubimage(res, 0, res, res);
	}

	public Image getBlockTexture(String texId, BlockPos pos, Level level, boolean wall)
	{
		BufferedImage out = (BufferedImage) getTextureByName(texId);
		if(scalex == 4 && scaley == 4)
		{
			Block block;
			boolean exists = level.getChunks().containsKey(pos.getChunk());
//			boolean sameUp;
			boolean sameRight;
			boolean sameDown;
//			boolean sameLeft;
			boolean solidUp;
//			boolean solidRight;
//			boolean solidDown;
			boolean solidLeft;
			if (wall)
			{
				block = level.getWall(pos);
//				sameUp = level.getWall(pos.offset(Direction.UP.get())) == block && exists;
				sameRight = level.getWall(pos.offset(Direction.RIGHT.get())) == block && exists;
				sameDown = level.getWall(pos.offset(Direction.DOWN.get())) == block && exists;
//				sameLeft = level.getWall(pos.offset(Direction.LEFT.get())) == block && exists;
				solidUp = level.getWall(pos.offset(Direction.UP.get())).getProperties().getSolid() && exists;
//				solidRight = level.getWall(pos.offset(Direction.RIGHT.get())).getProperties().getSolid() && exists;
//				solidDown = level.getWall(pos.offset(Direction.DOWN.get())).getProperties().getSolid() && exists;
				solidLeft = level.getWall(pos.offset(Direction.LEFT.get())).getProperties().getSolid() && exists;
			}
			else
			{
				block = level.getBlock(pos);
//				sameUp = level.getBlock(pos.offset(0, -1)) == block && exists;
				sameRight = level.getBlock(pos.offset(1, 0)) == block && exists;
				sameDown = level.getBlock(pos.offset(0, 1)) == block && exists;
//				sameLeft = level.getBlock(pos.offset(-1, 0)) == block && exists;
				solidUp = level.getBlock(pos.offset(0, -1)).getProperties().getSolid() && exists;
//				solidRight = level.getBlock(pos.offset(1, 0)).getProperties().getSolid() && exists;
//				solidDown = level.getBlock(pos.offset(0, 1)).getProperties().getSolid() && exists;
				solidLeft = level.getBlock(pos.offset(-1, 0)).getProperties().getSolid() && exists;
			}

			if (!solidUp && !sameRight && !sameDown && !solidLeft)
				out = out.getSubimage(res, 0, res, res);
			else if (!solidUp && sameRight && !sameDown && solidLeft)
				out = out.getSubimage(0, res, res, res);
			else if (solidUp && !sameRight && sameDown && !solidLeft)
				out = out.getSubimage(res, res, res, res);

			else if (!solidUp && !sameRight && sameDown && !solidLeft)
				out = out.getSubimage(0, res*2, res, res);
			else if (!solidUp && !sameRight && !sameDown && solidLeft)
				out = out.getSubimage(res, res*2, res, res);
			else if (solidUp && !sameRight && !sameDown && !solidLeft)
				out = out.getSubimage(0, res*3, res, res);
			else if (!solidUp && sameRight && !sameDown && !solidLeft)
				out = out.getSubimage(res, res*3, res, res);

			else if (solidUp && !sameRight && !sameDown && solidLeft)
				out = out.getSubimage(res*2, res*2, res, res);
			else if (solidUp && sameRight && !sameDown && !solidLeft)
				out = out.getSubimage(res*3, res*2, res, res);
			else if (!solidUp && sameRight && sameDown && !solidLeft)
				out = out.getSubimage(res*2, res*3, res, res);
			else if (!solidUp && !sameRight && sameDown && solidLeft)
				out = out.getSubimage(res*3, res*3, res, res);

			else if (!solidUp && sameRight && sameDown && solidLeft)
				out = out.getSubimage(res*2, 0, res, res);
			else if (solidUp && !sameRight && sameDown && solidLeft)
				out = out.getSubimage(res*3, 0, res, res);
			else if (solidUp && sameRight && !sameDown && solidLeft)
				out = out.getSubimage(res*2, res, res, res);
			else if (solidUp && sameRight && sameDown && !solidLeft)
				out = out.getSubimage(res*3, res, res, res);

			else out = out.getSubimage(0, 0, res, res);
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
