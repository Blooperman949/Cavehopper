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
		generateInRange(game.rd*2, game.spawn.getChunk());
		lr = new LevelRenderer(game);
	}
	
	public void generateInRange(int range, Point origin)
	{
		game.getLogger().log("Generating Chunks...");
		String text = "";
		for (int i = origin.x-range; i < origin.x+range; i++)
		{
			for (int j = origin.y-range; j < origin.y+range; j++)
			{
				Point p = new Point(i, j);
				if (!chunks.containsKey(p))
				{
					putChunk(p, cg.generateChunk(i, j, game.getBlocks()));
					text += "(" + i + ", " + j + ") ";
				}
			}
		}
		game.getLogger().log("Generated chunks around chunk (" + origin.x + ", " + origin.y + ") in range " + range + ": [ " + text + "]");
	}
	
	void putChunk(Point pos, Chunk chunk)
	{
		chunks.put(pos, chunk);
	}
	
	public LevelRenderer getLevelRenderer()
	{
		return lr;
	}
	
	public HashMap<Point, Chunk> getChunks()
	{
		return chunks;
	}
	
	public Chunk getChunk(WorldPos pos)
	{
		return chunks.get(pos.getChunk());
	}
}
