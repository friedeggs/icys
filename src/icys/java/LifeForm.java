package icys.java;

//Import
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static icys.java.Utilities.*;

public abstract class LifeForm extends Entity {
	// Many things are really only applicable to penguin and polar bear
	
	//Initialization
	Entity target; // Block or fish
	int index, meltY = 0, chosenDir [] = new int [2];
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
	
	//Constructor
	public LifeForm (int index) {
		this.index = index;
	}	
	
	//Mutated constructor
	public LifeForm (int index, int x, int y) {
		this.index = index;
		this.x = x;
		this.y = y;
	}
	
	//update
	public abstract void update (Graphics g);

	//nope, not here anymore
	protected void crossOff (int i, int j) {
		blocks [x][y].targeter = null;
		direction [i+1][j+1] = -1;
	}

	//Nope. Nothing here
	protected void crossOffLifeForms () {
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++)
				if (valid (i, j) && blocks [x+i][y+j].lifeform instanceof Egg)
						direction [i+1][j+1] = -1;
	}

	//When moving, the life form cannot move diagonally
	protected void clearCrosses () {
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++)
				if (direction [i+1][j+1] == -1)
					direction [i+1][j+1] = 0;
	}
	
	//GUI
	public void show(Graphics g)
	{ // DRAW IN MIDDLE OF BLOCK
		g.drawImage (image, coordX(x), coordY(y) - block_height / 2 + meltY, null);
		clearCrosses();
	}
	
	//make a random block as target
	public Block randomBlock () {
		int counter = 0;
		for (int i = 0 ; i < blocks.length ; i++)
			for (int j = 0 ; j < blocks[0].length ; j++)
				if (blocks[i][j].lifeform == null && blocks[i][j].value== LAND)
					counter++;
		
		//Choose a block, any block
		int x = (int)(Math.random () * counter);
		for (int i = 0 ; i < blocks.length ; i++)
			for (int j = 0 ; j < blocks[0].length ; j++)
				if (blocks[i][j].lifeform == null && blocks[i][j].value== LAND)
				{
					counter--;
					if (x == counter)
						return blocks [i][j];
				}

		return null;
	}
	
	//remove
	public abstract void remove ();
	
	/**
	 * Returns whether this position is a valid location for a life form
	 * to move to
	 * @param i
	 * @param j
	 * @return
	 */
	protected boolean valid (int i, int j)
	{
		return (i*j == 0) && x+i >= 0 && x+i < blocks.length &&
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
	
	//If this = that
	protected boolean equals (LifeForm l) {
		return l == null || (l.x == x && l.y == y);
	}
	
	/**
	 * Choose a location to target but don't actually change the location
	 */
	protected void move ()
	{
		crossOffLifeForms ();
		
		// Don't allow life forms to switch positions
		if (blocks[x][y].targeter != null && !equals(blocks [x][y].targeter))
			direction [blocks[x][y].targeter.x - x +1]
					[blocks[x][y].targeter.y - y +1] = -1;
		if (equals (blocks [x][y].lifeform))
			blocks [x][y].lifeform = null;
		
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

		for (int k = 2 ; k >= 0 ; k--) {
			int random = (int)(Math.random() * counter [k]);
			for (int i = -1 ; i <= 1 ; i++)
				for (int j = -1 ; j <= 1 ; j++)
					if (direction [i+1][j+1] == k+1) {
						counter[k]--;
						if (random == counter[k]) {
							chosenDir [0] = i; // MARK AS CHOSEN
							chosenDir [1] = j; 
							blocks [x+i][y+j].setTargeter(this);
							return;
						}
					}
		}
		
		chosenDir [0] = 0; // MARK AS CHOSEN
		chosenDir [1] = 0; 
		
		// Couldn't make a move! Inform whatever is targeting this block
		if (blocks [x][y].targeter != null) {
			blocks [x][y].targeter.crossOff (x - blocks [x][y].targeter.x,
					y - blocks [x][y].targeter.y);
			if (blocks [x][y].targeter != null)
				blocks [x][y].targeter.move();
		}
	}
	
	public void sink () {
		if (TIMER % sleep == 0 && meltY > 0) {
			meltY += 2 * block_height / interval;
		}
	}
	
}




