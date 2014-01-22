package icys.java;

import java.awt.Color;
import java.awt.Graphics;

public interface Button {
	
	public void setLocation (int x, int y);
	
	public void setSize (int width, int height);

	public void setState (int state);
	
	public boolean contains (int x, int y);
	
	public void draw (Graphics g);

	public int getState ();
	
	public int getX ();
	
	public int getY ();
	
	public int getWidth ();
	
	public int getHeight ();
}