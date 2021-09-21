package me.bluper.cavehopper.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.block.Blocks;
//import me.bluper.cavehopper.entity.Entity;
//import me.bluper.cavehopper.entity.EntityPos;
import me.bluper.cavehopper.level.BlockPos;
import me.bluper.cavehopper.level.Chunk;
import me.bluper.cavehopper.level.Level;

public class LevelRenderer
{
	int w, h;
	private HashMap<Point, BufferedImage> chunks = new HashMap<Point, BufferedImage>();
	private Cavehopper game;
	private Level level;
	private Camera camera;

	public LevelRenderer()
	{
		game = Cavehopper.getInstance();
		level = game.level;
		camera = new Camera(level.spawn);
	}

	public BufferedImage render(float x, float y)
	{
		w = game.getWindow().getWidth();
		h = game.getWindow().getHeight();
		int res = game.getBlockTextures().getRes();
		BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = out.createGraphics();
		for (Chunk i : level.getChunks().values())
		{
			if (i.getCenter().distance(camera.getCenterBlock()) > game.rd*32) chunks.remove(new Point(i.getX(), i.getY()));
			else if(!chunks.containsKey(new Point(i.getX(), i.getY()))) chunks.put(new Point(i.getX(), i.getY()), renderChunk(i));
			Point k = new Point(i.getX(), i.getY());
			if (chunks.containsKey(k) && i.getCenter().distance(camera.getCenterBlock()) <= game.rd*32)
			{
				g.drawImage(chunks.get(new Point(i.getX(), i.getY())), BlockPos.getXOnScreen(i.getX()*32, w, x, res), BlockPos.getYOnScreen(i.getY()*32, h, y, res), null);
			}
		}
		g.setColor(Color.GREEN);
//		for (Entity i : level.getEntities())
//		{
//			EntityPos ePos = i.getPos();
//			if (ePos.distance(camera.getCenter()) <= game.rd*32)
//			{
//				g.fillRect(
//						(int)EntityPos.getXYOnScreen(ePos.x, w, x, res), 
//						(int)EntityPos.getXYOnScreen(ePos.y, h, y, res), 
//						(i.getProperties().getWidth()*res),
//						(i.getProperties().getHeight()*res));
//			}
//		}
		g.setColor(Color.WHITE);

		//info text
		BlockPos camPos = new BlockPos((int) x, (int) y);
		Chunk cc = null;
		if(level.getChunks().containsKey(camPos.getChunk())) cc = level.getChunk(camPos);
		String text1 = "World: " + camPos.toString();
		String text2 = "";
		String text3 = "";
		String text4 = "Velocity: ";
		if (cc != null)
		{
			Point cCamPos = camPos.getPosInChunk();
			text1 += " Chunk: (" + cc.getX() + ", " + cc.getY() + "), (" + cCamPos.x + ", " + cCamPos.y + ")";
			if (cc.get().containsKey(cCamPos) && cc.getBlock(cCamPos) != null)
			{
				text2 += "Block: " + cc.getBlock(cCamPos).getId();
				text3 += "Light: " + cc.getCombinedLight(cCamPos);
			}
			if (cc.get().containsKey(cCamPos) && cc.getWall(cCamPos) != null)
				text2 += ", Wall: " + cc.getWall(cCamPos).getId();
		}
//		if (level.getPlayer() != null)
//		{
//			text4 += "x " + level.getPlayer().getXVelocity() + " y " + level.getPlayer().getYVelocity();
//		}
		g.drawString(text1, w/3+5, h/3+15);
		g.drawString(text2, w/3+5, h/3+30);
		g.drawString(text3, w/3+5, h/3+45);
		g.drawString(text4, w/3+5, h/3+60);

		return out;
	}

	public BufferedImage renderChunk(Chunk chunk)
	{
		int res = game.getBlockTextures().getRes();
		BufferedImage out = new BufferedImage(res*32, res*32, BufferedImage.TYPE_INT_ARGB);
		Graphics g = out.createGraphics();
		for (Point i : chunk.get().keySet())
		{
			if (chunk.getWall(i) != Blocks.AIR)
			{
				BlockPos blockPos = chunk.getBlockPosInWorld(i);
				String wallId = chunk.getWall(i).getId();
				Image wallTex = game.getBlockTextures().getBlockTexture(wallId, blockPos, level, true);
				g.drawImage(wallTex, i.x*res, i.y*res, null);
			}
		}
		for (int y = 0; y < out.getHeight(); y++)
			for (int x = 0; x < out.getWidth(); x++)
			{
				int clr = out.getRGB(x, y);
				int red = (clr & 0x00ff0000) >> 16,
					green = (clr & 0x0000ff00) >> 8,
					blue = (clr & 0x000000ff);;
					if (red > 0 || green > 0 || blue > 0)
					{
						red /= 2;
						green /= 2;
						blue /= 2;
						if (red < 0) red = 0;
						if (green < 0) green = 0;
						if (blue < 0) blue = 0;
						clr = (clr & 0xff000000) + (red << 16) + (green << 8) + blue;
						out.setRGB(x, y, clr);
					}
			}
		for (Point i : chunk.get().keySet())
		{
			if (chunk.getBlock(i) != Blocks.AIR)
			{
				BlockPos blockPos = chunk.getBlockPosInWorld(i);
				String blockId = chunk.getBlock(i).getId();
				Image blockTex = game.getBlockTextures().getBlockTexture(blockId, blockPos, level, false);
				g.drawImage(blockTex, i.x*res, i.y*res, null);
			}
		}
		g.dispose();
		return out;
	}

	public void unloadChunk(Point pos)
	{
		chunks.remove(pos);
	}

	public Camera getCamera() { return camera; }		
}
