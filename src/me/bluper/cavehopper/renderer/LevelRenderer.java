package me.bluper.cavehopper.renderer;

import static me.bluper.cavehopper.Cavehopper.game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import me.bluper.cavehopper.level.Chunk;
import me.bluper.cavehopper.level.Level;
import me.bluper.cavehopper.level.WorldPos;
import me.bluper.cavehopper.level.block.Block;

public class LevelRenderer
{
	int w = game.getWindow().getWidth();
	int h = game.getWindow().getHeight();
	Camera c = new Camera(w/game.blockTextures.getRes(), h/game.blockTextures.getRes(), game.spawn, game);
	Level level;
	HashMap<Point, BufferedImage> chunks = new HashMap<Point, BufferedImage>();
	
	public LevelRenderer(Level level)
	{
		this.level = level;
	}
	
	public BufferedImage render(float x, float y)
	{
		int res = game.blockTextures.getRes();
		BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = out.createGraphics();
		for (Chunk i : level.getChunks().values())
		{
			if (i.getCenter().distance(c.getCenterBlock()) > game.rd*32) chunks.remove(new Point(i.getX(), i.getY()));
			else if(!chunks.containsKey(new Point(i.getX(), i.getY()))) chunks.put(new Point(i.getX(), i.getY()), renderChunk(i));
			if (chunks.containsKey(new Point(i.getX(), i.getY())) && i.getCenter().distance(c.getCenterBlock()) <= game.rd*32)
			{
				g.drawImage(chunks.get(new Point(i.getX(), i.getY())), WorldPos.getXOnScreen(i.getX()*32, w, x, res), WorldPos.getYOnScreen(i.getY()*32, h, y, res), null);
			}
		}
		return out;
	}
	
	public BufferedImage renderChunk(Chunk chunk)
	{
		int res = game.blockTextures.getRes();
		BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = out.createGraphics();
		for (Block b : chunk.getBlocks().values())
		{
			g.drawImage(game.blockTextures.getTextureByName(b.id), (b.getX()*res), (b.getY()*res), null);
		}
		return out;
	}
	
	public Camera getCamera() { return c; }
}
