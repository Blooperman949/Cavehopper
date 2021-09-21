package me.bluper.cavehopper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

//import me.bluper.cavehopper.entity.PlayerEntity;
import me.bluper.cavehopper.game.GameLogger;
import me.bluper.cavehopper.game.GameWindow;
import me.bluper.cavehopper.level.Level;
import me.bluper.cavehopper.res.assets.Texts;
import me.bluper.cavehopper.res.assets.TextureSheet;
import me.bluper.cavehopper.util.LevelFileManager;

public class Cavehopper
{
	public static Cavehopper game;
	public static final String VERSION = "WorldGen Test v0.4.2";
	private boolean running = false;
	private boolean inGame = false;
	private int tr = 20;
	public final int rd = 2;
	public final int biomeSize = 500;

	private GameLogger logger;
	public Level level;
	private GameWindow gw;
	private LevelFileManager lw;
//	public PlayerEntity player;

	private TextureSheet blockTextures;
	private final int blockRes = 8;
	BufferedImage bgImg;
	Texts texts;

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
				catch(Exception e) { logger.logException(e); };
			}
		}
	}

	void init()
	{
		logger = new GameLogger("");
		logger.log("Initializing Cavehopper" + VERSION + "...");
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			@Override public void run()
			{
				logger.log("Shutting Down...");
				logger.close();
			}
		}));
		loadAssets();

		running = true;
		gw = new GameWindow();
		gw.addKeyListener(gw);
		initLevel();
	}

	void initLevel()
	{
		lw = new LevelFileManager("world");

		level = new Level();
		level.createLevelRenderer();
		level.onChunkEntered(level.spawn.getChunk());
		level.spawnPlayer();
//		player = level.getPlayer();
		inGame = true;
	}

	void tick()
	{
		if (inGame)
		{
//			if (player != null)
//			{
//				float speed = player.getProperties().getAcceleration();
//				if (gw.getKeyDown(87)/* && player.onGround*/)
//				{
//					player.setYVelocity(-1);
//				}
//				if (gw.getKeyDown(83)) player.setYVelocity(-player.getProperties().getGravity());
//				if (gw.getKeyDown(65))
//				{
//					player.addVelocity(-speed, 0);
//					player.moving = true;
//				}
//				else if (gw.getKeyDown(68))
//				{
//					player.addVelocity(speed, 0);
//					player.moving = true;
//				}
//				else player.moving = false;
//			}
			int speed = 3;
			if (gw.getKeyDown(87)/* && player.onGround*/)
			{
				level.getLevelRenderer().getCamera().addY(-speed);
			}
			if (gw.getKeyDown(83))
			{
				level.getLevelRenderer().getCamera().addY(speed);
			}
			if (gw.getKeyDown(65))
			{
				level.getLevelRenderer().getCamera().addX(-speed);
			}
			else if (gw.getKeyDown(68))
			{
				level.getLevelRenderer().getCamera().addX(speed);
			}
			if (gw.getKeyDown(69));
			level.onChunkEntered(level.getLevelRenderer().getCamera().getCenterBlock().getChunk());
			level.tick();
		}
	}

	void render()
	{
		Image buffer = new BufferedImage(gw.getWidth(), gw.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();
//		if (player != null && level.getLevelRenderer().getCamera() != null)
//		{
//			level.getLevelRenderer().getCamera().setOrigin(player.getEyes());
			if (inGame)
			{
				g.drawImage(level.getLevelRenderer().getCamera().getView(level.getLevelRenderer(), gw.getWidth(), gw.getHeight()), 0, 0, null);
				gw.getGraphics().drawImage(buffer, gw.getInsets().left, gw.getInsets().top, null);
			}
//		}
	}

	void loadAssets()
	{
		try
		{
			blockTextures = new TextureSheet("assets/cavehopper/textures/block/", 8, blockRes, 4, 4, 4);
			logger.log("Finished loading textures: Blocks");
			//			for (URL u : Resources.getResourceURLs(Resources.class, new ResourceURLFilter()
			//			{
			//				@Override public boolean accept(URL u) { return u.getPath().contains("assets/cavehopper/textures/menu/menu.png"); }				
			//			})) { bgImg = ImageIO.read(u); }
			//			logger.log("Finished loading textures: Misc");
		}
		catch (IOException | URISyntaxException e) { logger.logException(e); }
		texts = new Texts("en_us");		
		logger.log("Finished loading Texts");
		logger.log("Finished loading all assets!");
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
	public TextureSheet getBlockTextures() { return blockTextures; }
	public Texts getTexts() { return texts; }
	public static Cavehopper getInstance() { return game; }
	public LevelFileManager getLevelWriter() { return lw; }
}
