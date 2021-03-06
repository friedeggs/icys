package icys.java;

//Imports
import static icys.java.Utilities.*;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class PolarBear extends LifeForm {

	//Initialization
	int sinceEaten = 0;
	int penguinsEaten = 0; 
	int timeout = 15, timer = 0;

	//Constructor
	public PolarBear(int index)
	{
		super (index);
	}
	
	//Modified constructor
	public PolarBear (int index, int x, int y)
	{
		super (index);
		blocks [x][y].set(this);
		sinceEaten = 0;
		penguinsEaten = 0;
		this.x = x;
		this.y = y;
		chooseTarget();
		try {
			image = ImageIO.read(new File ("polar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//update the state of the bear
	public void update(Graphics g)
	{
		if (blocks [x][y].value == LAND) {
			checkAlive();
			reproduces ();
			updateTarget();
			move();
		}
	}
	
	//removes the target penguin that was eaten
	public void eatPenguin ()
	{
		System.out.println(this + " " + penguinsEaten);
		if (target != null && x == target.x && y == target.y)
		{
			if (target instanceof Penguin) {
				if (target instanceof Player)
					((Game)currentScreen).endGame ();
				else
					((Penguin) target).remove();
				penguinsEaten ++;
				sinceEaten = 0;
			}
			target = null;
			blocks [x][y].set(this);
		}
		else {
			sinceEaten ++ ;
		}
	}
	
	// is this still alive?
	private void checkAlive() 
	{
		if (!(currentScreen instanceof Game) && sinceEaten > 60) {
			remove();
		}
	}
	
	//HOW DOES IT EVEN--
	private void reproduces()
	{
		if (penguinsEaten == 5)
		{
			System.out.println ("new polar bear");
			PolarBear baby = new PolarBear(bears.size(), x, y);
			penguinsEaten = 0;
			bears.add(baby);
		}
	}
	
	/**
	 * updateTarget: This method checks if target is alive, and calls 
	 * method to choose new target if current target is dead
	 */
	private void updateTarget() 
	{
		timer++;
		if (timer == timeout ||
				target == null || (penguins.size() > 0 && target instanceof Block))
			chooseTarget();
	}
	
	private void chooseTarget() //Chooses closest fish for new target
	{		
		timer = 0;
		if (penguins.size() == 0 && !(currentScreen instanceof Game 
				&& ((Game)currentScreen).ongoing())) {
			target = randomBlock ();
			return;
		}
		if (penguins.size() == 0 && (currentScreen instanceof Game) 
				&& ((Game)currentScreen).ongoing())
		{
			target = ((Game)currentScreen).getPlayer();
			return;
		}
		
		int[] distances = new int [penguins.size()]; 
		int min; 
		
		for (int i = 0; i < penguins.size(); i ++) 
		{
			distances [i] = this.distanceTo(penguins.get(i)); //Creates an array for the distance of each fish from penguin
		}
		
		min = distances[0]; 
		target = penguins.get (0);
		for (int j = 0; j< distances.length; j++)
		{
			if (distances[j] < min ) //If current element is smaller than minimum... 
			{
				min = distances[j];	//...set as minimum.
				this.target = penguins.get(j); //And set the respective fish as new target
			}
		}
		if (currentScreen instanceof Game && ((Game)currentScreen).ongoing()) {
			if (distanceTo (((Game)currentScreen).getPlayer()) < min)
				target = ((Game)currentScreen).getPlayer();
		}
	}
	
	protected void crossOffLifeForms () {
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++)
				if (valid (i, j) && (blocks [x+i][y+j].lifeform instanceof Egg
						|| blocks [x+i][y+j].lifeform instanceof Fish))
						direction [i+1][j+1] = -1;
	}
	
	public boolean equals (PolarBear p) {
		return (p.x == x && p.y == y);
	}
	
	@Override
	public void remove() {
		blocks [x][y].set(null);
		ArrayList <PolarBear> newlist = new ArrayList <PolarBear> ();
		for (int i = 0 ; i < bears.size() ; i ++) {
			if (equals(bears.get(i)))
				bears.remove(i);
		}
		
		for (int i = 0 ; i < bears.size () - 1 ; i++) {
			newlist.add(bears.get(i));
			newlist.get(i).index = i;
		}
	}
	
	protected void move ()
	{
		crossOffLifeForms ();
		
		// Don't allow lifeforms to switch positions
		if (blocks[x][y].targeter != null)
			direction [blocks[x][y].targeter.x - x +1]
					[blocks[x][y].targeter.y - y +1] = -1;
		
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

		if (blocks [x][y].targeter == this)
			blocks [x][y].setTargeter(null);
		for (int k = 2 ; k >= 0 ; k--) {
			int random = (int)(Math.random() * counter [k]);
			for (int i = -1 ; i <= 1 ; i++)
				for (int j = -1 ; j <= 1 ; j++)
					if (direction [i+1][j+1] == k+1) {
						counter[k]--;
						if (random == counter[k]) {
							//blocks [x][y].targeter = null;
							chosenDir [0] = i; // MARK AS CHOSEN
							chosenDir [1] = j; 
							x += i;
							y += j;
							//if (x == target.x && y == target.y
							//		/*&& target instanceof Penguin*/) {
							eatPenguin ();
							//if (i+j != 0)
							//}
							blocks [x][y].setTargeter(this);
							x -= i;
							y -= j;
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

}

