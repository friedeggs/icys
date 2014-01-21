package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static icys.java.Utilities.*;

public abstract class LifeForm extends Entity {
	// Many things are really only applicable to penguin and polarbear
	
	Entity target; // Block or fish
	int index;
	BufferedImage image;
	/**
	 * Indices: 1 more than change in x and y	\
	 * valid: if it's land and nothing else is targeting it	  \
	 * 3 - closer to target   \
	 * 2 - same distance as before   \
	 * 1 - farther from target   \
	 * 0 - !valid
	 * -1 - don't change until the blocks are drawn
	 */
	int direction [][] = new int [3][3]; // CHANGE IN X AND Y
	
	public LifeForm (int index) {
		this.index = index;
	}	
	
	public LifeForm (int index, int x, int y) {
		this.index = index;
		this.x = x;
		this.y = y;
	}

	protected void crossOff (int i, int j) {
		direction [i+1][j+1] = -1;
	}

	protected void clearCrosses () {
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++)
				if (direction [i+1][j+1] == -1)
					direction [i+1][j+1] = 0;
	}
	
	public void show(Graphics g)
	{ // DRAW IN MIDDLE OF BLOCK
		g.drawImage (image, coordX(x), coordY(y) - block_height / 2, null);
		clearCrosses();
	}
	
	public Block randomBlock () {
		int x, y;
		do {
			x = (int)(Math.random () * blocks.length);
			y = (int)(Math.random () * blocks[0].length);
		} while (blocks[x][y].lifeform != null || blocks[x][y].value != LAND);
		return blocks [x][y];
	}
	
	public abstract void remove ();
	
	/**
	 * Returns whether this position is a valid location for a lifeform
	 * to move to
	 * @param i
	 * @param j
	 * @return
	 */
	protected boolean valid (int i, int j)
	{
		return (Math.abs(i+j) == 1) && x+i >= 0 && x+i < blocks.length &&
				y+j >= 0 && y+j < blocks[0].length && blocks [x+i][y+j].value 
				== LAND && blocks [x+i][y+j].targeter == null;
	}
	
	/**
	 * Returns a block's rating of closeness to the target
	 * @param i
	 * @param j
	 * @return
	 */
	protected int closer (int i, int j) {
		if (blocks [x+i][y+j].distanceTo(target) < distanceTo (target))
			return 3;
		if (blocks [x+i][y+j].distanceTo(target) == distanceTo (target))
			return 2;
		return 1;
	}
	
	/**
	 * Choose a location to target but don't actually change the location
	 */
	protected void move ()
	{
		int counter [] = new int [3];
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++)
				if (direction [i+1][j+1] != -1)
				{
					if (valid (i, j)) {
						direction [i+1][j+1] = closer (i, j);
						counter [direction [i+1][j+1]-1 ]++;
					}
				else
					direction [i+1][j+1] = 0;
				}

		blocks [x][y].setTargeter(this);
		for (int k = 2 ; k >= 0 ; k--) {
			int random = (int)(Math.random() * counter [k]);
			for (int i = -1 ; i <= 1 ; i++)
				for (int j = -1 ; j <= 1 ; j++)
					if (direction [i+1][j+1] == k+1) {
						counter[k]--;
						if (random == counter[k]) {
							blocks [x][y].targeter = null;
							x += i;
							y += j;
							blocks [x][y].setTargeter(this);
							return;
						}
					}
		}
		// Couldn't make a move! Inform whatever is targeting this block
		if (blocks [x][y].targeter != null) {
			blocks [x][y].targeter.crossOff (x - blocks [x][y].targeter.x,
					y - blocks [x][y].targeter.y);
			blocks [x][y].targeter.move();
		}
	}
	
}




