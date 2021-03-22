package me.bluper.cavehopper.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.level.Level;
import me.bluper.cavehopper.level.WorldPos;

public class Camera
{
	private int w;
	private int h;
	private float posX;
	private float posY;
	private Cavehopper game;
	
	public Camera(Cavehopper game, WorldPos pos)
	{
		this.w = game.getWindow().getWidth();
		this.h = game.getWindow().getHeight();
		this.posX = pos.x;
		this.posY = pos.y;
		this.game = game;
	}
	
	public WorldPos getCenterBlock()
	{
		return new WorldPos(Math.round(posX), Math.round(posY));
	}
	
	public void setOrigin(float x, float y)
	{
		this.posX = x;
		this.posY = y;
	}
	
	public Image getView(Level level, int w, int h)
	{
		BufferedImage out = new BufferedImage(game.getWindow().getWidth(), game.getWindow().getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = out.createGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.WHITE);
		g.drawImage(level.getLevelRenderer().render(posX, posY, level), 0, 0, null);
		BufferedImage cameraView = out.getSubimage(out.getWidth()/3, out.getHeight()/3, out.getWidth()/3, out.getHeight()/3)/*.getScaledInstance(out.getWidth(), out.getHeight(), Image.SCALE_FAST)*/;
		g.drawRect(out.getWidth()/3, out.getHeight()/3, out.getWidth()/3-1, out.getHeight()/3-1);
		g.drawRect(out.getWidth()/2-1, out.getHeight()/2-1, 3, 3);
		if (game.getWindow().getKeyDown(67)) g.drawImage(cameraView.getScaledInstance(cameraView.getWidth()/2, cameraView.getHeight()/2, Image.SCALE_FAST), 0, 0, null);
		else g.drawImage(cameraView.getScaledInstance(w, h, Image.SCALE_FAST), 0, 0, null);
		return out;
	}
	
	public int getWidth() { return w; }
	public int getHeight() { return h; }
	public float getX() { return posX; }
	public float getY() { return posY; }
	public void addX(float x) { this.posX += x; }
	public void addY(float y) { this.posY += y; }
	public void removeX(float x) { this.posX -= x; }
	public void removeY(float y) { this.posY -= y; }
}
