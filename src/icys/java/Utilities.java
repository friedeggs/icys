package icys.java;
import icys.java.Main.Background;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public class Utilities {
	
	public static Main main;
	
	public static Screen StartScreen;
	
	public static Screen SelectionScreen;
	
	public static Screen AboutScreen;
	
	public static Screen HelpScreen;
	
	public static Screen currentScreen;
	
	public static Background background;
	
	public static Dimension screenSize = new Dimension (768, 576);
	
	public static int width = screenSize.width, height = screenSize.height;
	
	public static int border = 10, offset = 50;
	
	public static Color lightblue = new Color (175, 217, 255),
			blue = new Color (124, 175, 222),
			aqua = new Color (221, 240, 255);
	
	public static Font font = new Font ("Calibri", Font.BOLD, 32),
	font_large = new Font ("Calibri", Font.BOLD, 144);
}