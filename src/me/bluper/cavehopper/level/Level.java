package me.bluper.cavehopper.level;

import static me.bluper.cavehopper.Cavehopper.game;

import java.awt.Point;
import java.util.HashMap;

import me.bluper.cavehopper.level.gen.ChunkGenerator;
import me.bluper.cavehopper.renderer.LevelRenderer;

public class Level
{
	HashMap<Point, Chunk> chunks = new HashMap<Point, Chunk>();
	ChunkGenerator cg = new ChunkGenerator(0);
	LevelRenderer lr;
	
	public Level()
	{
		generateInRange(game.rd, game.spawn.getChunk());
		lr = new LevelRenderer(this);
	}
	
	public void generateInRange(int range, Point origin)
	{
		System.out.println("Generating chunks in range " + range + " around chunk (" + origin.x + ", " + origin.y + ")");
		for (int i = origin.x-range; i < origin.x+range; i++)
		{
			for (int j = origin.y-range; j < origin.y+range; j++)
			{
				if (!chunks.containsKey(new Point(i, j))) putChunk(cg.generateChunk(i, j));
			}
		}
	}
	
	void putChunk(Chunk chunk)
	{
		chunks.put(new Point(chunk.getX(), chunk.getY()), chunk);
	}
	
	public LevelRenderer getLevelRenderer()
	{
		return lr;
	}
	
	public HashMap<Point, Chunk> getChunks()
	{
		return chunks;
	}
}
