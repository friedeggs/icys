package icys.java;

import static icys.java.Utilities.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Game extends Mode {
	
	int score = 0;
	boolean alive = true;
	Penguin player;
	BoxButton tile [][] = new BoxButton [blocks.length][blocks[0].length];
	
	public Game () {
		super();
		
		for (int i = 0 ; i < tile.length ; i++) {
			for (int j = 0 ; j < tile [0].length ; j++) {
				tile [i][j] = new BoxButton (i, j);
			}
		}
		
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
			penguins.get(i).update ();
		}

		System.out.println("==========");
	}
	
	/**
	 * Converts a coordinate into a block index
	 */
	private int convX (int x, int y) {
		//return index * block_width - y * shift; // TEMPORARY
		return (x + convY(y) * shift) / block_width;
		//return (x + 3) * block_width - y * shift + nudgeX; // TEMPORARY
	}	
	
	/**
	 * Converts a coordinate into a block index
	 */
	private int convY (int y) {
		return (y - offset - 2 * border) / block_width;
		//return y * block_height + offset+2*border + nudgeY; // TEMPORARY
	}
	
	public void mouseReleased (MouseEvent e) {
		super.mouseReleased (e);
		if (!quake) // selected
			tile [convX (e.getX(), e.getY())][convY (e.getY())].setState (2);
	}
	
	public void mouseMoved (MouseEvent e) {
		
	}
	

}