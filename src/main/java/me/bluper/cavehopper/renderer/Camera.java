package me.bluper.cavehopper.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import me.bluper.cavehopper.Cavehopper;
//import me.bluper.cavehopper.entity.EntityPos;
import me.bluper.cavehopper.level.BlockPos;

public class Camera
{
	private int w;
	private int h;
	private float posX;
	private float posY;
	private Cavehopper game = Cavehopper.getInstance();
	
	public Camera(BlockPos pos)
	{
		this.w = game.getWindow().getWidth();
		this.h = game.getWindow().getHeight();
		this.posX = pos.x;
		this.posY = pos.y;
	}
	
	public BlockPos getCenterBlock()
	{
		return new BlockPos(Math.round(posX), Math.round(posY));
	}
	
//	public EntityPos getCenter()
//	{
//		return new EntityPos(posX, posY);
//	}
	
	public void setOrigin(float x, float y)
	{
		this.posX = x;
		this.posY = y;
	}
	
//	public void setOrigin(EntityPos pos)
//	{
//		this.posX = pos.x;
//		this.posY = pos.y;
//	}
	
	public Image getView(LevelRenderer lr, int w, int h)
	{
		BufferedImage out = new BufferedImage(game.getWindow().getWidth(), game.getWindow().getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = out.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.WHITE);
		g.drawImage(lr.render(posX, posY), 0, 0, null);
		//out = out.getSubimage(out.getWidth()/3, out.getHeight()/3, out.getWidth()/3, out.getHeight()/3)/*.getScaledInstance(out.getWidth(), out.getHeight(), Image.SCALE_FAST)*/;
		return out.getScaledInstance(w, h, Image.SCALE_FAST);
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
