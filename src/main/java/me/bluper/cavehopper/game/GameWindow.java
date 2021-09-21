package me.bluper.cavehopper.game;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import me.bluper.cavehopper.Cavehopper;

public class GameWindow extends JFrame implements KeyListener, MouseListener
{
	private static final long serialVersionUID = -1215501927192857725L;
	int w, h;
	Insets ins;
	Cavehopper game = Cavehopper.getInstance();
	boolean[] keys = new boolean[256];
	boolean mouseDown;
	
	public GameWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(game.getTexts().get("game", "title") + ' ' + Cavehopper.VERSION);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(50, 50, screenSize.width-100, screenSize.height-100);
		w = getWidth();
		h = getHeight();
		addMouseListener(this);
		
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
		if (e.getKeyCode() < keys.length-1) keys[e.getKeyCode()] = true;
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() < keys.length-1) keys[e.getKeyCode()] = false;
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	public boolean getKeyDown(int key) { return keys[key]; }
	public boolean getMouseDown() { return mouseDown; }
	public Point getMousePos() { return MouseInfo.getPointerInfo().getLocation(); }
	
	@Override public void mousePressed(MouseEvent e) { mouseDown = true; }
	@Override public void mouseReleased(MouseEvent e) { mouseDown = false; }

	@Override public void mouseClicked(MouseEvent e) { }
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}
