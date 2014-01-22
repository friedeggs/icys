package icys.java;

import static icys.java.Utilities.*;

import java.awt.Graphics;

public class Game extends Mode {
	
	int score = 0;
	boolean alive = true;
	Penguin player;
	
	public Game () {
		super();
		
		for (int i = 0 ; i < 1 ; i++) {
			fish.add(new Fish (i));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			eggs.add(new Egg (i, 8, 12));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			penguins.add(new Penguin (i, 10, 10));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			bears.add(new PolarBear (i, 5, 12));
		}
		
		
		player = penguins.get(0); 
		player.target = null;
		
		//need to alter penguin class to allow for user-chosen directions
		//cannot use update method, use new gaming method? or just move?
		//maybe use move, but overridden with a parameter giving direction
		//but we still need crosses.. what.
	}
	
	public void draw(Graphics g) {

		super.draw(g);
		
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).update (g);
		}

		System.out.println("==========");
	}
	

}