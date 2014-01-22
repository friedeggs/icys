package icys.java;

import static icys.java.Utilities.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Game extends Mode {
	
	int score = 0;
	boolean alive = true;
	Penguin player;
	Tile hovered;
	
	public Game () {
		super();
		
		for (int i = 0 ; i < tile.length ; i++) {
			for (int j = 0 ; j < tile [0].length ; j++) {
				tile [i][j] = new Tile (i, j);
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
		
		
		player = new Player (12, 7); 
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
		return (x - nudgeX + convY(y) * shift) / block_width - 3;
		//return (x + 3) * block_width - y * shift + nudgeX; // TEMPORARY
	}	
	
	/**
	 * Converts a coordinate into a block index
	 */
	private int convY (int y) {
		return (y - nudgeY - offset - 2 * border) / block_height;
		//return y * block_height + offset+2*border + nudgeY; // TEMPORARY
	}
	
	private boolean valid (int x, int y) {
		return x >= 0 && x < blocks.length && y >= 0 && y < blocks [0].length
				&& blocks [x][y].value == LAND;
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {
		super.mouseReleased (e);
		int x = convX (e.getX(), e.getY()),
				y = convY (e.getY());
		if (valid (x, y)) {
			if (player.target != null)
				tile [player.target.x][player.target.y].setState (0);
			tile [x][y].setState (2);
			player.target = blocks [x][y];
		}
	}
	
	@Override
	public void mouseMoved (MouseEvent e) {
		System.out.println ("mouse moved");
		int x = convX (e.getX(), e.getY()),
				y = convY (e.getY());
		if (valid (x, y)) {
			if (hovered != null)
				hovered.setState (0);
			hovered = tile [x][y];
			tile [x][y].setState (1);
		}
	}
	

}