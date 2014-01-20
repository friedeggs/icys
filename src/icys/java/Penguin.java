package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Penguin extends LifeForm {

	boolean alive;
	Fish target;
	Block aim; // Block version of target
	int x, y;
	int direction;
	int sinceEaten;
	int fishesEaten; 
	
	public Penguin () {
		
	}
	
	public Penguin (int x, int y) 
	{
		this.x = x;
		this.y = y;
		alive = true; 
		fishesEaten = 0;
		sinceEaten = 0;
		//choose target
		chooseTarget ();
		try {
			image = ImageIO.read(new File ("penguin.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(Graphics g)
	{
		this.eatFish();
		this.checkAlive();
		this.reproduces ();
		this.updateTarget();
		this.move();
		this.show(g);
	}

	public void eatFish ()
	{
		if (target != null && x == target.x && y == target.y)
		{
			target.remove();
			fishesEaten ++;
			sinceEaten = 0;
		}
		else
			sinceEaten ++ ;
	}
	
	public void checkAlive() 
	{
		if (alive)
		{
			if (sinceEaten > 20)
				alive = false;
		}
	}
	
	public void reproduces()
	{
		if (fishesEaten == 5)
		{
			Egg baby = new Egg(x, y);
			fishesEaten = 0;
			eggs.add(baby);
		}	
	}
	
	public void updateTarget() //updateTarget: This method checks if target is alive, and calls method to choose new target if current target is dead
	{
		boolean targetAlive = false; 
		for (int i = 0; i < fish.size(); i ++) 
		{
			if (this.target == fish.get(i)) //Checks if target fish is still in fish ArrayList
				targetAlive = true; 
		}
		if (targetAlive == false) //Chooses new target if target is dead
			this.chooseTarget();
	}
	
	public void chooseTarget() //Chooses closest fish for new target
	{		
		if (fish.size() == 0) {
			// choose a random block of land
			aim = randomBlock ();
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
	
	public int distanceTo (Fish fish) //distanceTo: This method finds the distance between the penguin and fish parameter
	{
		int distanceTo = (Math.abs(this.x - fish.x)) + (Math.abs(this.y - fish.y));
		return distanceTo;
	}
	
	public void chooseDirection() //chooseDirection: This method chooses a direction for the penguin to go, towards the fish
	{
		int[] possibilities = new int [2];
		int direction; //8- up, 6-right, 2- down, 4- left
		
		if (this.x == target.x) //Compares x-coordinate of penguin vs. target
			possibilities[0]= 0; 
		else if(this.x - target.x > 0)
			possibilities[0] = 4;
		else
			possibilities[0] = 6;
		
		if (this.y == target.y) //Compares y-coordinate of penguin vs. target
			possibilities[0] = 0;
		else if (this.y - target.y > 0)
			possibilities[1] = 8;
		else
			possibilities[1] = 2;
		
		int random = (int)(Math.random() * 2); //Chooses random direction within two possible directions
		direction = possibilities [random];
		
		if (direction == 0) //If direction chosen is not valid, choose other direction
		{
			if (random == 1)
				random = 2;
			else
				random = 1;
			direction = possibilities[random - 1];
		}
		this.direction = direction;
	}

	/** YO. THIS CHANGES THE POSITION BEFORE CHECKING IF THE NEW BLOCK
	   IS OCCUPIED OR NOT LAND ? */
	public boolean valid(Block[][] thegreatbigworld)
	{
		if (this.direction == 8 && this.y > 0)		
			this.y = this.y - 1 ;
		else if (this.direction == 2 && this.y < thegreatbigworld[0].length-1)
			this.y = this.y + 1; 
		else if (this.direction == 6 && this.x > 0)
			this.x = this.x - 1 ;
		else if (this.direction == 4 && this.x < thegreatbigworld.length-1)
			this.x = this.x + 1; 
		
		if (thegreatbigworld[x][y].isOccupied == false && thegreatbigworld[x][y].value == 1)
			return true;
		else
			return false;
	}
	
	public void move (Block [][] thegreatbigworld)
	{
		/** shouldn't you cross off invalid directions
		 and also what if there is no valid direction? */
		do 
		{
			this.chooseDirection();
		}
		while (!this.valid(thegreatbigworld));
	}
}
