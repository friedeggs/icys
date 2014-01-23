package icys.java;

//Imports
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Penguin extends LifeForm {
	
	//initialization
	int sinceEaten;
	int fishesEaten; 
	
	//Constructor
	public Penguin (int index) {
		super (index);
	}
	
	//Sur-normal constructor
	public Penguin (int index, int x, int y) 
	{
		super (index);
		this.x = x; //position X
		this.y = y; //position Y
		blocks [x][y].set(this); //Block on life form = this
		fishesEaten = 0;
		sinceEaten = 0;
		chooseTarget ();
		try {
			image = ImageIO.read(new File ("penguino.png")); //img
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	//removes the target fish that was eaten
	public void eatFish ()
	{
		if (x == target.x && y == target.y)
		{
			if (target instanceof Fish) {
				((Fish) target).remove();
				fishesEaten ++;
				sinceEaten = 0;
			}
			target = null;
		}
		else
			sinceEaten ++ ;
	}
	
	// is this still alive?
	public void checkAlive() 
	{
		if (sinceEaten > 20) 
			remove();
	}
	
	//=.= asexual reproduction. Just pretend that it ate too much and pooped out an egg.
	public void reproduces()
	{
		if (fishesEaten == 5)
		{
			fishesEaten = 0;
			Egg baby = new Egg(eggs.size(), x, y);
			blocks [x][y].targeter = baby;
			blocks [x][y].set(this);
			eggs.add(baby);
			direction [1][1] = -1;
		}	
	}
	
	 //checks if target is alive, and calls method to choose new target if current target is dead
	public void updateTarget()
	{
		if (target == null || (fish.size() > 0 && target instanceof Block))
			chooseTarget();
	}
	
	//Chooses closest fish for new target
	public void chooseTarget() 
	{
		if (fish.size() == 0) {
			// choose a random block of land
			target = randomBlock ();
			return;
		}
		// else: 
		int[] distances = new int [fish.size()]; 
		int min; 
		
		for (int i = 0; i < fish.size(); i ++) 
		{
			distances [i] = this.distanceTo(fish.get(i)); 
			//Creates an array for the distance of each fish from penguin
		}
		
		min = distances[0]; 
		target = fish.get(0);
		for (int j = 0; j< distances.length; j++)
		{
			
			if (distances[j] < min ) //If current element is smaller than minimum 
			{
				min = distances[j];	//...set as minimum.
				this.target = fish.get(j); 
				//And set the respective fish as new target
			}
		}
	}
	
	/**
	 * fish appear first
	 * eggs updated next
	 * penguins target a new valid location in order of ranking
	 * - closer to target
	 * - same distance as before
	 * - farther from target
	 * valid: if it's land
	 * the new location can be currently occupied by something else
	 * if that something has to stay in the same place the targeter
	 * must choose a new target
	 * @param i
	 * @param j
	 * @return
	 */
	
	//this...= that?
	public boolean equals (Penguin p) {
		return (p.x == x && p.y == y);
	}
	
	//more remove method that kills the entity
	@Override
	public void remove() {
		blocks [x][y].set(null);
		ArrayList <Penguin> newlist = new ArrayList <Penguin> ();
		for (int i = 0 ; i < penguins.size() ; i ++) {
			if (equals(penguins.get(i)))
				penguins.remove(i);
		}
		
		for (int i = 0 ; i < penguins.size () - 1 ; i++) {
			newlist.add(penguins.get(i));
			newlist.get(i).index = i;
		}
	}

	//... and even more updates
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if (blocks [x][y].value == LAND) {
			this.eatFish();
			this.checkAlive();
			this.reproduces ();
			this.updateTarget();
			this.move();
		}
	}
}
