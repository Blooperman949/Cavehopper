package me.bluper.cavehopper;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import me.bluper.cavehopper.assets.TextureSheet;
import me.bluper.cavehopper.game.GameWindow;
import me.bluper.cavehopper.level.Level;
import me.bluper.cavehopper.level.WorldPos;

public class Cavehopper
{
	public static Cavehopper game;
	public Level level;
	public static final String NAMESPACE = "cavehopper";
	public static final String VERSION = "WorldGen Test 0.0.2";
	public final int rd = 2;
	public WorldPos spawn = new WorldPos(0, 0);
	boolean running;
	int tr = 20;
	
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
				catch(Exception e) {};
			}
		}
	}
	
	void init()
	{
		System.out.println("Initializing...");
		loadAssets();
		
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
		if (gw.getKeyDown(32)) level.generateInRange(rd, level.getLevelRenderer().getCamera().getCenterBlock().getChunk());
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
		blockTextures = new TextureSheet("assets/cavehopper/textures/block/", blockRes*8, blockRes, this);
		System.out.println("Finished loading textures: Blocks");
		skyTextures = new TextureSheet("assets/cavehopper/textures/sky/", skyRes*4, skyRes, this);
		System.out.println("Finished loading textures: Skies");
		System.out.println("Finished loading all assets!");
	}
	
	public static void main(String args[])
	{
		game = new Cavehopper();
		game.run();
		System.exit(0);
	}
	
	public boolean getRunning() { return running; }
	
	public GameWindow getWindow() { return gw; }
}
