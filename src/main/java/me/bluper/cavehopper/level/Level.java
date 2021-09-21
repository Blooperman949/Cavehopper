package me.bluper.cavehopper.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.block.Block;
import me.bluper.cavehopper.block.Blocks;
//import me.bluper.cavehopper.entity.Entity;
//import me.bluper.cavehopper.entity.PlayerEntity;
import me.bluper.cavehopper.level.gen.ChunkGenerator;
import me.bluper.cavehopper.renderer.LevelRenderer;
import me.bluper.cavehopper.util.LevelFileManager;

public class Level
{
	public BlockPos spawn = new BlockPos(-1010, 1900);
	private HashMap<Point, Chunk> chunks = new HashMap<Point, Chunk>();
	private HashMap<Point, BlockSet> preChunks = new HashMap<Point, BlockSet>();
	private long seed = 420;
	private ChunkGenerator cg = new ChunkGenerator(seed);
	private LevelRenderer lr;
	private Cavehopper game;
//	private GameLogger logger;
	private LevelFileManager lfm;
//	private ArrayList<Entity> entities = new ArrayList<Entity>();
//	private PlayerEntity player;

	public Level()
	{
		game = Cavehopper.getInstance();
//		logger = game.getLogger();
		lfm = game.getLevelWriter();
	}

	private void unloadChunk(Point pos)
	{
		Chunk c = chunks.get(pos);
		lfm.writeChunk(c);
		chunks.remove(pos);
	}

	public void onChunkEntered(int range, Point origin)
	{
//		String text = "";
//		int c = 0;
		for (int x = origin.x-range; x < origin.x+range; x++)
		{
			for (int y = origin.y-range; y < origin.y+range; y++)
			{
				Point p = new Point(x, y);
				if (!chunks.containsKey(p))
				{
					if(lfm.chunkExists(p))
					{
						chunks.put(p, lfm.readChunk(p));
					}
					else
					{
						putNewChunk(p);
//						text += "(" + x + ", " + y + ") ";
					}
//					c++;
				}
				else if (p.distance(origin) > range)
					unloadChunk(p);
			}
		}
//		logger.log("Generated " + c + " chunks around chunk (" + origin.x + ", " + origin.y + ") in range " + range + ": [ " + text + "]");
	}
	public void onChunkEntered(Point pos)
	{
		onChunkEntered(game.rd+2, pos);
	}
	
	private void putNewChunk(Point p)
	{
		chunks.put(p, cg.generateChunk(p.x, p.y));
		cg.generateFeaturesAt(getChunk(p.x, p.y));
		if (preChunks.containsKey(p))
		{
			preChunks.get(p).placeInLevel(this, BlockPos.chunkToBlockPos(p));
			preChunks.remove(p);
		}
//		chunks.get(p).updateLight();
	}

	public boolean setBlock(BlockPos pos, Block block)
	{
		Chunk chunk = getChunk(pos);
		Point p = pos.getPosInChunk();
		Point cPos = pos.getChunk();
		if (chunks.containsKey(pos.getChunk()))
		{
			Block cBlock = chunk.getBlock(p);
			chunk.setBlock(p, block);
			return block != cBlock;
		}
		else
		{
			if (!preChunks.containsKey(cPos)) preChunks.put(cPos, new BlockSet());
			preChunks.get(cPos).put(p, block);
			return true;
		}
	}

//	public boolean spawnEntity(Entity entity, BlockPos pos)
//	{
//		boolean flag = entity.canSpawnAt(this, pos);
//		if (flag) entities.add(entity);
//		return flag;
//	}

	public void spawnPlayer()
	{
//		if (player == null)
//		{
//			player = new PlayerEntity(spawn.toEntityPos());
//			Chunk chunk = getChunk(spawn.getChunk());
//			for (Point pos : chunk.get().keySet())
//			{
//				BlockPos wPos = chunk.getBlockPosInWorld(pos);
//				if (player.canSpawnAt(this, wPos))
//				{
//					entities.add(player);
//					player.setPos(wPos);
//					spawn = wPos;
//					break;
//				}
//			}
//		}
	}

	public void tick()
	{
//		for (Entity e : entities)
//		{
//			e.tick();
//		}
	}

	public LevelRenderer getLevelRenderer() { return lr; }
	public HashMap<Point, Chunk> getChunks() { return chunks; }
	public Chunk getChunk(BlockPos pos) { return chunks.get(pos.getChunk()); }
	public Chunk getChunk(int x, int y) { return chunks.get(new Point(x, y)); }
	public Chunk getChunk(Point pos) { return chunks.get(pos); }
	public Block getBlock(BlockPos pos)
	{
		return chunks.containsKey(pos.getChunk()) ? getChunk(pos).getBlock(pos.getPosInChunk()) : Blocks.AIR;
	}
	public Block getWall(BlockPos pos)
	{
		return chunks.containsKey(pos.getChunk()) ? getChunk(pos).getWall(pos.getPosInChunk()) : Blocks.AIR;
	}
	public LevelFileManager getFileManager() { return lfm; }
//	public ArrayList<Entity> getEntities() { return entities; }
//	public PlayerEntity getPlayer() { return player; }
	public long getSeed() { return seed; }
	public void createLevelRenderer() { lr = new LevelRenderer(); }
}
