package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Penguin extends LifeForm {
	
	Entity target; // Block or fish
	int x, y;
	int direction;
	int sinceEaten;
	int fishesEaten; 
	
	public Penguin (int index) {
		super (index);
	}
	
	public Penguin (int index, int x, int y) 
	{
		super (index);
		this.x = x;
		this.y = y;
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
	
	public void checkAlive() 
	{
		if (sinceEaten > 20)
			remove();
	}
	
	public void reproduces()
	{
		if (fishesEaten == 5)
		{
			fishesEaten = 0;
			Egg baby = new Egg(eggs.size(), x, y);
			eggs.add(baby);
		}	
	}
	
	public void updateTarget() //updateTarget: This method checks if target is alive, and calls method to choose new target if current target is dead
	{
//		boolean targetAlive = false; 
//		for (int i = 0; i < fish.size(); i ++) 
//		{
//			if (this.target == fish.get(i)) //Checks if target fish is still in fish ArrayList
//				targetAlive = true; 
//		}
//		if (targetAlive == false) //Chooses new target if target is dead
		if (target == null && (fish.size() > 0 || aim == null))
			chooseTarget();
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
	
	public void chooseDirection() //chooseDirection: This method chooses a direction for the penguin to go, towards the fish
	{
		int[] possibilities = new int [2];
		int direction; //8- up, 6-right, 2- down, 4- left
		
		if (this.x == target.x) //Compares x-coordinate of penguin vs. target
			possibilities[0] = 0; 
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
	public boolean valid()
	{
		if (this.direction == 8 && this.y > 0)		
			this.y = this.y - 1 ;
		else if (this.direction == 2 && this.y < blocks[0].length-1)
			this.y = this.y + 1; 
		else if (this.direction == 6 && this.x > 0)
			this.x = this.x - 1 ;
		else if (this.direction == 4 && this.x < blocks.length-1)
			this.x = this.x + 1; 
		
		if (blocks[x][y].lifeform == null && blocks[x][y].value == 1)
			return true;
		else
			return false;
	}
	
	public void move ()
	{
		boolean direction [][] = new boolean [3][3]; 
		int temp, counter = 0;
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++)
				if (Math.abs (i+j) == 1) {
					if (target != null)
						temp = distanceTo (target);
					else
						temp = distanceTo (aim);
					x += i;
					y += j;
					if ((target != null && distanceTo (target) < temp)
							|| (aim != null && distanceTo (aim) < temp)) {
						direction [i+1][j+1] = true;
						counter++;
					}
					x -= i;
					y -= j;
				}
		/** shouldn't you cross off invalid directions
		 and also what if there is no valid direction? */
		do 
		{
			this.chooseDirection();
		}
		while (!this.valid());
	}

	@Override
	public void remove() {
		penguins.remove(index);
		for (int i = index ; i < penguins.size() ; i++)
			penguins.get(i).index--;
	}
}
