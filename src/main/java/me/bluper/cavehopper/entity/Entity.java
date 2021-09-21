package me.bluper.cavehopper.entity;

//import me.bluper.cavehopper.Cavehopper;
//import me.bluper.cavehopper.level.BlockPos;
//import me.bluper.cavehopper.level.Level;
//import me.bluper.cavehopper.util.Direction;
//
public class Entity
{
//	EntityPos pos;
//	EntityPos prevPos = new EntityPos();
//	EntityProperties prop;
//	float xVel = 0, yVel = 0;
//	Level level;
//	public boolean onGround;
//	public boolean moving = false;
//
//	public Entity(EntityProperties prop, EntityPos pos)
//	{
//		this.prop = prop;
//		this.pos = pos;
//		level = Cavehopper.getInstance().level;
//	}
//
//	public Entity(EntityProperties prop)
//	{
//		this(prop, new EntityPos());
//	}
//
//	public boolean canSpawnAt(Level level, BlockPos pos)
//	{
//		for (int y = 0; y < (int)Math.ceil(prop.getHeight()); y++)
//			for (int x = 0; x < (int)Math.ceil(prop.getWidth()); x++)
//			{
//				if (level.getBlock(pos.offset(x, y)).getProperties().getSolid()) return false;
//			}
//		return true;
//	}
//
//	public void tick()
//	{
////		if (willCollide(Direction.RIGHT, xVel))
////		{
////			xVel = 0;
////			pos.x = (float)Math.ceil(pos.x + xVel);
////		}
////		if (xVel < 0 && willCollide(Direction.LEFT, xVel))
////		{
////			xVel = 0;
////			pos.x = (float)Math.floor(pos.x + xVel);
////		}
////		if (yVel > 0 && willCollide(Direction.DOWN, yVel))
////		{
////			yVel = 0;
////			pos.y = (float)Math.ceil(pos.y + yVel);
////		}
////		
////		onGround = willCollide(Direction.DOWN, 0.1f);
////		if (!onGround) yVel += prop.getGravity();
//		if (xVel != 0 && !moving)
//			if (xVel < 0 && xVel + prop.getFriction() < 0) xVel += prop.getFriction();
//			else if (xVel > 0 && xVel - prop.getFriction() > 0) xVel -= prop.getFriction();
//			else xVel = 0;
//
//		yVel += prop.getGravity();
//		pos.y += yVel;
//		pos.x += xVel;
//	}
//	
//	private boolean willCollide(Direction dir, float offset)
//	{
//		offset = Math.abs(offset);
//		switch(dir)
//		{
//		case DOWN:
//			for (int i = 1; i < prop.getWidth(); i++)
//			{
//				if (level.getBlock(pos.offset(i, prop.getHeight() + offset).getBlockPos()).getProperties().getSolid())
//					return true;
//			}
//		case LEFT:
//			for (int i = 1; i < prop.getHeight(); i++)
//			{
//				if (level.getBlock(pos.offset(-offset, i).getBlockPos()).getProperties().getSolid())
//					return true;
//			}
//		case RIGHT:
//			for (int i = 1; i < prop.getHeight(); i++)
//			{
//				if (level.getBlock(pos.offset(prop.getWidth() + offset, i).getBlockPos()).getProperties().getSolid())
//					return true;
//			}
//		case UP:
//			for (int i = 1; i < prop.getWidth(); i++)
//			{
//				if (level.getBlock(pos.offset(i, -offset).getBlockPos()).getProperties().getSolid())
//					return true;
//			}
//		}
//		return false;
//	}
//
//	public void addVelocity(float x, float y)
//	{
//		if ((xVel < prop.getSpeed() && x > 0) | (xVel > -prop.getSpeed() && x < 0)) xVel += x;
//		if ((yVel < prop.getSpeed() && y > 0) | (yVel > -prop.getSpeed() && y < 0)) yVel += y;
//	}
//
//	public void setYVelocity(float yVel)
//	{
//		this.yVel = yVel;
//	}
//	
//	public void setXVelocity(float xVel)
//	{
//		this.xVel = xVel;
//	}
//
//	public void setPos(BlockPos pos)
//	{
//		this.pos = pos.toEntityPos();
//	}
//
//	public EntityPos getFeet() { return pos.offset(prop.getWidth()/2, prop.getHeight()); }
//	public EntityPos getEyes() { return pos.offset(prop.getWidth()/2, prop.getEyes()); }
//
//	public EntityPos getPos() { return pos; }
//	public EntityProperties getProperties() { return prop; }
//	
//	public float getXVelocity() { return xVel; }
//	public float getYVelocity() { return yVel; }
}
