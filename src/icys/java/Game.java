package icys.java;

import static icys.java.Utilities.*;
import java.awt.Graphics;

public class Game extends Mode {
	
	int score = 0;
	boolean alive = true;
	Penguin player;
	
	public Game () {
		super();
		player = penguins.get(0); 
		player.target = null;
		
		//need to alter penguin class to allow for user-chosen directions
		//cannot use update method, use new gaming method? or just move?
		//maybe use move, but overridden with a parameter giving direction
		//but we still need crosses.. what.
	}

}