package me.bluper.cavehopper.game;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import me.bluper.cavehopper.Cavehopper;
import me.bluper.cavehopper.registries.Texts;

public class GameWindow extends JFrame implements KeyListener
{
	private static final long serialVersionUID = -1215501927192857725L;
	int w, h;
	Insets ins;
	Cavehopper game;
	boolean[] keys = new boolean[256];
	
	public GameWindow(Cavehopper game)
	{
		this.game = game;
		init();
	}
	
	public void init()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Texts.GAME_TITLE.get());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(50, 50, screenSize.width-100, screenSize.height-100);
		w = getWidth();
		h = getHeight();
		
		setVisible(game.getRunning());
	}
	
	@Override
	public int getWidth()
	{
		ins = getInsets();
		return super.getWidth() - ins.left - ins.right;
	}
	@Override
	public int getHeight()
	{
		ins = getInsets();
		return super.getHeight() - ins.top - ins.bottom;
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		keys[e.getKeyCode()] = true;
//		System.out.println(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		keys[e.getKeyCode()] = false;
	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	public boolean getKeyDown(int key)
	{
		return keys[key];
	}
}
