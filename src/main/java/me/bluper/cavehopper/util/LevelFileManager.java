package me.bluper.cavehopper.util;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.block.Blocks;
import me.bluper.cavehopper.game.GameLogger;
import me.bluper.cavehopper.level.Chunk;

public class LevelFileManager
{
	private FileWriter writer;
	private String path;
	Cavehopper game ;
	GameLogger logger;
	
	public LevelFileManager(String path)
	{
		this.path = path;
		game = Cavehopper.getInstance();
		logger = game.getLogger();
	}
	
	public void writeWorldProperties()
	{
		try
		{
			writer = new FileWriter(path + "/world.properties");
			writer.write("seed=" + game.level.getSeed());
			writer.close();
		} 
		catch(IOException e) { logger.logException(e); }
	}
	
	private String getFileName(Point pos)
	{
		return pos.x + "_" + pos.y + ".csv";
	}
	
	public void writeChunk(Chunk c)
	{
//		new File(path + "/chunks/").mkdirs();
//		try
//		{
//			writer = new FileWriter(path + "/chunks/" + getFileName(c.getPos()));
//			for (int i = 0; i < 32; i++)
//				for (int j = 0; j < 32; j++)
//				{
//					writer.write(c.getBlock(new Point(j, i)).getSaveId() + ",");
//					if (j == 31) writer.write("\n");
//				}
//			logger.log("Writing chunk (" + c.getX() + ", " + c.getY() + ") to file");
//			writer.close();
//		}
//		catch (IOException e) { logger.logException(e); }
	}
	
	public Chunk readChunk(Point pos)
	{
		Chunk out = new Chunk(pos.x, pos.y);
		String str = "";
		
		try
		{
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(path + "/chunks/" + getFileName(pos)));
			for(int i = 0; i < 32; i++)
				str += reader.readLine();
		}
		catch (IOException e) { logger.logException(e); }
		
		str = str.replace("\n", "");
		String[] strArr = str.split(",", 1024);
		int i = 0;
		for(int y = 0; y < 32; y++)
		{
			for(int x = 0; x < 32; x++, i++)
			{
				short id = Short.parseShort(strArr[i].replace(",", ""));
				out.setBlock(new Point(x, y), Blocks.allBlocks.get(id));
			}
		}
		return out;
	}
	
	public boolean chunkExists(Point pos)
	{
		File chunks = new File(path + "/chunks/");
		chunks.mkdirs();
		for (File f : chunks.listFiles())
			if(f.getName().equals(pos.x + "_" + pos.y + ".csv")) return true;
		return false;
	}
}
