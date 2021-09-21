package me.bluper.cavehopper.light;

import java.awt.Color;

public class Light
{
	byte strength;
	byte loss;
	Color color;
	
	public Light(byte strength, byte loss, Color color)
	{
		this.strength = strength;
		this.loss = loss;
		this.color = color;
	}
	
	public byte getStrength() { return strength; }
	public byte getLoss() { return loss; }
	public Color getColor() { return color; }
}
