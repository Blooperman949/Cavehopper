package me.bluper.cavehopper;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import me.bluper.cavehopper.game.GameLogger;
import me.bluper.cavehopper.game.GameWindow;
import me.bluper.cavehopper.level.Level;
import me.bluper.cavehopper.level.WorldPos;
import me.bluper.cavehopper.res.assets.Texts;
import me.bluper.cavehopper.res.assets.TextureSheet;
import me.bluper.cavehopper.res.data.block.Blocks;

public class Cavehopper
{
	public static Cavehopper game;
	public static final String NAMESPACE = "cavehopper";
	public static final String VERSION = "WorldGen Test 0.1.2";
	boolean running;
	int tr = 20;
	
	public Level level;
	public final int rd = 2;
	public WorldPos spawn = new WorldPos(16, 16);
	GameLogger logger = new GameLogger("");
	Texts texts;
	Blocks blocks;
	GameWindow gw;
	
	public TextureSheet blockTextures;
	int blockRes = 8;
	public TextureSheet skyTextures;
	int skyRes = 64;
	
	public void run()
	{
		init();
		while(running)
		{
			long time = System.currentTimeMillis();
			
			tick();
			render();
			
			time = (1000/tr) - (System.currentTimeMillis() - time);
			
			if(time > 0)
			{
				try { Thread.sleep(time); }
				catch(Exception e) { logger.log(e.getStackTrace().toString()); };
			}
		}
	}
	
	void init()
	{
		logger.log("Initializing...");
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			@Override public void run() { logger.close(); }
		} ));
		loadAssets();
		loadData();

		running = true;
		gw = new GameWindow(game);
		gw.addKeyListener(gw);
		level = new Level();
	}
	
	void tick()
	{
		if (gw.getKeyDown(87)) level.getLevelRenderer().getCamera().removeY(2.8f);
		if (gw.getKeyDown(83)) level.getLevelRenderer().getCamera().addY(2.8f);
		if (gw.getKeyDown(65)) level.getLevelRenderer().getCamera().removeX(2.8f);
		if (gw.getKeyDown(68)) level.getLevelRenderer().getCamera().addX(2.8f);
		if (gw.getKeyDown(69)) level.generateInRange(rd, level.getLevelRenderer().getCamera().getCenterBlock().getChunk());
	}
	
	
	void render()
	{
		Image buffer = new BufferedImage(gw.getWidth(), gw.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();
		g.drawImage(level.getLevelRenderer().getCamera().getView(level, gw.getWidth(), gw.getHeight()), 0, 0, null);
		
		gw.getGraphics().drawImage(buffer, gw.getInsets().left, gw.getInsets().top, null);
	}
	
	void loadAssets()
	{
		try
		{
			blockTextures = new TextureSheet("assets/cavehopper/textures/block/", blockRes*8, blockRes, this);
			logger.log("Finished loading textures: Blocks");
			skyTextures = new TextureSheet("assets/cavehopper/textures/sky/", skyRes*4, skyRes, this);
			logger.log("Finished loading textures: Skies");
		}
		catch (IOException | URISyntaxException e) { logger.logException(e); }
		texts = new Texts("en_us", game);		
		logger.log("Finished loading all assets!");
	}
	
	void loadData()
	{
		blocks = new Blocks(game);
	}
	
	public static void main(String args[])
	{
		game = new Cavehopper();
		game.run();
		System.exit(0);
	}
	
	public boolean getRunning() { return running; }
	
	public GameWindow getWindow() { return gw; }
	public GameLogger getLogger() { return logger; }
	public Texts getTexts() { return texts; }
	public Blocks getBlocks() { return blocks; }
}
