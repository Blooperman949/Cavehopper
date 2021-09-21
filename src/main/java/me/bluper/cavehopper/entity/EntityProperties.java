package me.bluper.cavehopper.entity;

public class EntityProperties
{
	private float gravity;
	private float speed;
	private float accel;
	private float friction;
	private int width;
	private int height;
	private int eyes;
	
	public EntityProperties setGravity(float gravity) { this.gravity = gravity; return this; }
	public EntityProperties setSpeed(float speed) { this.speed = speed; return this; }
	public EntityProperties setAcceleration(float accel) { this.accel = accel; return this; }
	public EntityProperties setFriction(float friction) { this.friction = friction; return this; }
	public EntityProperties setWidth(int width) { this.width = width; return this; }
	public EntityProperties setHeight(int height) { this.height = height; return this; }
	public EntityProperties setEyes(int eyes) { this.eyes = eyes; return this; }
	
	public float getGravity() { return gravity; }
	public float getSpeed() { return speed; }
	public float getAcceleration() { return accel; }
	public float getFriction() { return friction; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getEyes() { return eyes; }
}
