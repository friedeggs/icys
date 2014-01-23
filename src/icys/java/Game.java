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
	
	public boolean ongoing () {
		return alive;
	}
	
	public void endGame () {
		alive = false;
		player = null;
	}
	
	public Penguin getPlayer () {
		return player;
	}
	
	public void draw(Graphics g) {

		super.draw(g);

		if (TIMER % sleep == 0 && alive) 
			player.update(g);
		
		for (int i = 0 ; i < blocks.length ; i++) 
			for (int j = 0 ; j < blocks[0].length ; j++)
				blocks[i][j].show(g);

		//System.out.println("==========");
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {
		super.mouseReleased (e);
		if (alive) {
		int x = e.getX(), y = e.getY();
		for (int i = 0 ; i < tile.length ; i++) {
			for (int j = 0 ; j < tile[0].length ; j++) {
				if (tile [i][j].contains(x, y)) {
					tile [i][j].setState (2);
					player.target = blocks [i][j];
				}
				else
					tile [i][j].setState (0);
			}
		}
		}
	}
	
	@Override
	public void mouseMoved (MouseEvent e) {
		super.mouseMoved(e);
		System.out.println ("mouse moved");
		if (alive) {
		int x = e.getX(), y = e.getY();
		for (int i = 0 ; i < tile.length ; i++) {
			for (int j = 0 ; j < tile[0].length ; j++) {
				if (tile [i][j].getState() != 2 && tile [i][j].contains(x, y))
					tile [i][j].setState (1);
				else if (tile [i][j].getState() != 2)
					tile [i][j].setState (0);
			}
		}
		}
	}
	

}