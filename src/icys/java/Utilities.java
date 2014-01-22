package icys.java;
import static icys.java.Utilities.border;
import static icys.java.Utilities.offset;
import icys.java.Main.Background;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;


public class Utilities {
	
	public static Main main;
	
	public static Screen StartScreen;
	
	public static Screen SelectionScreen;
	
	public static Screen AboutScreen;
	
	public static Screen HelpScreen;
	
	public static Screen currentScreen;
	
	public static Screen mode;
	
	//public static Background background;
	
	public static Dimension screenSize = new Dimension (768, 576);
	
	public static int width = screenSize.width, height = screenSize.height;
	
	public static int border = 10, offset = 50, shift = 10, nudgeX = 0, nudgeY = 0;
	
	public static int block_width, block_height;
	
	public static Color lightblue = new Color (175, 217, 255),
			blue = new Color (124, 175, 222),
			aqua = new Color (221, 240, 255),
			water = aqua;//new Color (103, 213, 235);
	
	public static Font font = new Font ("Calibri", Font.BOLD, 32),
			font_large = new Font ("Calibri", Font.BOLD, 144),
			font_small = new Font ("Calibri", Font.BOLD, 24);

	
	public static Block blocks [][] = new Block [21][15];
	
	public static char [][] value = new char [21][15];
	
	public static final int WATER = 0, LAND = 1, UNUSED = 2;
	
	public static ArrayList <Fish> fish;
	
	public static ArrayList <Egg> eggs;
	
	public static ArrayList <Penguin> penguins;
	
	public static ArrayList <PolarBear> bears;
	
	public static boolean stopFish = false;
}