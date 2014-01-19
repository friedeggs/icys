package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Penguin extends LifeForm {

	boolean alive;
	Fish target;
	int x, y;
	int direction;
	BufferedImage cup;
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
		target = Mode.fish.get(0); // TEMPORARY
		try {
			cup = ImageIO.read(new File ("penguin.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(ArrayList<Penguin> pencils, ArrayList <Fish> blub, 
			ArrayList<Egg> frieda, Block[][] iii, Graphics g)
	{
		this.eatFish();
		this.checkAlive();
		this.reproduces (frieda);
		this.updateTarget(blub);
		this.move(iii);
		this.show(iii, g);
	}

	public void eatFish ()
	{
		if ((this.x == target.x) && (this.y == target.y) && this.alive)
		{
			target.alive = false;
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
	
	public ArrayList<Egg> reproduces(ArrayList<Egg> eggs)
	{
		if (fishesEaten == 5)
		{
			Egg baby = new Egg(x, y);
			fishesEaten = 0;
			eggs.add(baby);
		}
		return eggs; 	
	}
	
	public void updateTarget(ArrayList<Fish> fishes) //updateTarget: This method checks if target is alive, and calls method to choose new target if current target is dead
	{
		boolean targetAlive = false; 
		for (int i = 0; i < fishes.size(); i ++) 
		{
			if (this.target == fishes.get(i)) //Checks if target fish is still in fish ArrayList
				targetAlive = true; 
		}
		if (targetAlive == false) //Chooses new target if target is dead
			this.chooseTarget(fishes);
	}
	
	public void chooseTarget(ArrayList<Fish> fishes) //Chooses closest fish for new target
	{		
		int[] distances = new int [fishes.size()]; 
		int min; 
		
		for (int i = 0; i < fishes.size(); i ++) 
		{
			distances [i] = this.distanceTo(fishes.get(i)); //Creates an array for the distance of each fish from penguin
		}
		
		min = distances[0]; 
		for (int j = 0; j< distances.length; j++)
		{
			
			if (distances[j] < min ) //If current element is smaller than minimum... 
			{
				min = distances[j];	//...set as minimum.
				this.target = fishes.get(j); //And set the respective fish as new target
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
		else if (this.direction == 2 && this.y < thegreatbigworld[0].length)
			this.y = this.y + 1; 
		else if (this.direction == 6 && this.x > 0)
			this.x = this.x - 1 ;
		else if (this.direction == 4 && this.x < thegreatbigworld.length)
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

	public void show(Block[][] enviro, Graphics g)
	{
		g.drawImage (cup, enviro[x][y].x, enviro[x][y].y, null); 
	}
}
