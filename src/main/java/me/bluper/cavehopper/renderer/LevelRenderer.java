package me.bluper.cavehopper.renderer;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.level.Chunk;
import me.bluper.cavehopper.level.Level;
import me.bluper.cavehopper.level.WorldPos;
import me.bluper.cavehopper.res.data.block.Block;

public class LevelRenderer
{
	int w, h;
	Level level;
	HashMap<Point, BufferedImage> chunks = new HashMap<Point, BufferedImage>();
	Cavehopper game;
	Camera c;
	
	public LevelRenderer(Cavehopper game)
	{
		this.game = game;
		c = new Camera(game, game.spawn);
	}
	
	public BufferedImage render(float x, float y, Level level)
	{
		w = game.getWindow().getWidth();
		h = game.getWindow().getHeight();
		this.level = level;
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
		WorldPos camPos = new WorldPos((int) x, (int) y);
		Chunk cc = null;
		if(level.getChunks().containsKey(camPos.getChunk())) cc = level.getChunk(camPos);
		String text = camPos.toString();
		if (cc != null) text += " in " + new WorldPos(cc.getX(), cc.getY()).toString();
		if (cc != null && cc.getBlocks().containsKey(camPos.getPosInChunk())) text += " Block: " + cc.getBlock(camPos.getPosInChunk()).getId();
		g.drawString(text, w/3, h/3);
		return out;
	}
	
	public BufferedImage renderChunk(Chunk chunk)
	{
		int res = game.blockTextures.getRes();
		BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = out.createGraphics();
		for (Block b : chunk.getBlocks().values())
		{
			g.drawImage(game.blockTextures.getTextureByName(b.getId()), (b.getX()*res), (b.getY()*res), null);
		}
		return out;
	}
	
	public Camera getCamera() { return c == null ? new Camera(game, game.spawn) : c; }
}
