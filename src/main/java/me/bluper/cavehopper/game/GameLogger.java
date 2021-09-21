package me.bluper.cavehopper.game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class GameLogger
{
	File logLoc;
	FileWriter writer;
	FileReader reader;
	
	public GameLogger(String logLoc)
	{
		this.logLoc = new File(logLoc + "logs/log-" + LocalDate.now() + "-" + (System.currentTimeMillis() + ".txt").substring(6));
		try
		{
			new File(logLoc + "logs").mkdirs();
			writer = new FileWriter(this.logLoc);
			reader = new FileReader(this.logLoc);
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void log(Object in)
	{
		String text = in.toString();
		text = "[" + LocalDate.now() + "-" + LocalTime.now() + "] " + text;
		System.out.println(text);
		try
		{
			writer.write(text + "\n");
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void close()
	{
		try
		{
			writer.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void logException(Exception e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		log("ERROR: " + sw.toString());
	}
}
