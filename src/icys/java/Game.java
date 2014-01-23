package icys.java;

//Import
import static icys.java.Utilities.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Game extends Mode {
	
	//Initialization
	int score = 0;
	boolean alive = true;
	Penguin player;
	Tile hovered, targeted;
	
	//Constructor
	public Game () {
		super();

		// Add title
		for (int i = 0 ; i < tile.length ; i++) {
			for (int j = 0 ; j < tile [0].length ; j++) {
				tile [i][j] = new Tile (i, j);
			}
		}
		
		// Add Fish
		for (int i = 0 ; i < 1 ; i++) {
			fish.add(new Fish (i));
		}
		
		// Add eggs
		for (int i = 0 ; i < 1 ; i++) {
			eggs.add(new Egg (i, 8, 12));
		}
		
		//Add penguins
		for (int i = 0 ; i < 1 ; i++) {
			penguins.add(new Penguin (i, 10, 10));
		}
		
		// Add polar bears
		for (int i = 0 ; i < 1 ; i++) {
			bears.add(new PolarBear (i, 5, 12));
		}
		
		//Add player
		player = new Player (12, 7); 
		player.target = null;
		penguins.add(player);
		
	}
	
	// determine if the game should go on by returning if player is alive
	public boolean ongoing () {
		return alive;
	}
	
	//End game = player -> dead
	public void endGame () {
		if (targeted != null)
			targeted.setState(0);
		alive = false;
		player.remove();
		player = null;
		if (hovered != null)
			hovered.setState(0);
	}
	
	//return the object player
	public Penguin getPlayer () {
		return player;
	}
	
	//draw EVERYTHING
	public void draw(Graphics g) {

		super.draw(g);
		for (int i = 0 ; i < blocks.length ; i++) 
			for (int j = 0 ; j < blocks[0].length ; j++)
				blocks[i][j].show(g);
	}
	
	/*--------ALL THE MOUSE MOVEMENTS------------*/
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
					targeted = tile [i][j];
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
		if (alive) {
		int x = e.getX(), y = e.getY();
		for (int i = 0 ; i < tile.length ; i++) {
			for (int j = 0 ; j < tile[0].length ; j++) {
				if (tile [i][j].getState() != 2 && tile [i][j].contains(x, y)) {
					tile [i][j].setState (1);
					hovered = tile [i][j];
				}
				else if (tile [i][j].getState() != 2)
					tile [i][j].setState (0);
			}
		}
		}
	}
	

}