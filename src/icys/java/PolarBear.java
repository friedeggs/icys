package icys.java;

import static icys.java.Utilities.blocks;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class PolarBear extends LifeForm {

	Penguin target;
	//Block subtarget;
	int x, y;
	int direction;
	BufferedImage image;
	int sinceEaten;
	int penguinsEaten; 

	public PolarBear(int index)
	{
		super (index);
		//x int
		//y int
		sinceEaten = 0;
		penguinsEaten = 0;
		chooseTarget();
		try {
			image = ImageIO.read(new File ("polarbear.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PolarBear (int index, int x, int y)
	{
		super (index);
		sinceEaten = 0;
		penguinsEaten = 0;
		this.x = x;
		this.y = y;
		chooseTarget();
		try {
			image = ImageIO.read(new File ("polarbear.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(ArrayList <PolarBear> rawr, ArrayList<Penguin> pencils,
			Block[][] iii, Graphics g)
	{
		eatPenguin();
		checkAlive();
		reproduces (rawr, pencils);
		updateTarget(pencils);
		move(iii);
		show(iii, g);
	}
	
	public void eatPenguin ()
	{
		if ((this.x == target.x) && (this.y == target.y)&& this.alive)
		{
			target.alive = false;
			penguinsEaten ++;
			sinceEaten = 0;
		} else
			sinceEaten ++;
	}
	
	public void checkAlive() 
	{
		if (alive)
		{
			if (sinceEaten > 20)
				alive = false;
		}
	}
	
	
	public ArrayList<PolarBear> reproduces(ArrayList<PolarBear> bears, ArrayList<Penguin> pens)
	{
		
		if (penguinsEaten == 5)
		{
			PolarBear baby = new PolarBear(x, y);
			penguinsEaten = 0;
			bears.add(baby);
		}
		return bears; 	
	}
	
	public void updateTarget(ArrayList<Penguin> penGUIno) 
	{
		boolean targetAlive = false; 
		for (int i = 0; i < penGUIno.size(); i ++) 
		{
			if (this.target == penGUIno.get(i)) //Checks if target penguin is still in penguin ArrayList
				targetAlive = true; 
		}
		if (targetAlive == false) //Chooses new target if target is dead
			this.chooseTarget(penGUIno);
	}
	
	public void chooseTarget(ArrayList<Penguin> pens) //Chooses closest fish for new target
	{		
		if (pens.size() == 0) {
			target = null;
			//int[] coordinates = this.chooseRandomLand();
			//subtarget = blocks[coordinates[0]][coordinates[1]];
		}
		else {
		
		int[] distances = new int [pens.size()]; 
		int min; 
		
		for (int i = 0; i < pens.size(); i ++) 
		{
			distances [i] = this.distanceTo(pens.get(i)); //Creates an array for the distance of each fish from penguin
		}
		
		min = distances[0]; 
		this.target = pens.get (0);
		for (int j = 0; j< distances.length; j++)
		{
			
			if (distances[j] < min ) //If current element is smaller than minimum... 
			{
				min = distances[j];	//...set as minimum.
				this.target = pens.get(j); //And set the respective fish as new target
			}
		}
		
		}
	}
	
	public int distanceTo (Penguin pen) //distanceTo: This method finds the distance between the penguin and fish parameter
	{
		int distanceTo = (Math.abs(this.x - pen.x)) + (Math.abs(this.y - pen.y));
		return distanceTo;
	}
	
	public void chooseDirection() //chooseDirection: This method chooses a direction for the penguin to go, towards the fish
	{
		int[] possibilities = new int [2];
		int direction; //8- up, 6-right, 2- down, 4- left
		
		if (target == null)
		{
			direction = (int)((Math.random()* 4)+1); //Chooses random direction if there is no target
		}	
		else
		{
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
				direction = possibilities[random-1];
			}
			this.direction = direction;
		}
	}

	public boolean valid(Block[][] thegreatbigworld)
	{
		int newX = x, newY = y;
		
		if (this.direction == 8 && this.y > 0)
			newY = this.y - 1 ;
		else if (this.direction == 2 && this.y < thegreatbigworld[0].length)
			newY = this.y + 1; 
		else if (this.direction == 6 && this.x > 0)
			newX = this.x -1 ;
		else if (this.direction == 4 && this.x < thegreatbigworld.length)
			newX = this.x + 1;
		
		if (thegreatbigworld[newX][newY].isOccupied == false && thegreatbigworld[newX][newY].value == 1)
		{
			x = newX;
			y = newY;
			return true;
		}
		else
			return false;
	}
	
	public void move(Block[][] thegreatbigworld)
	{
		do 
		{
			this.chooseDirection();
		}
		while (this.valid(thegreatbigworld) == false); 
	}
	
	public void show(Graphics g)
	{
		g.drawImage (image, blocks[x][y].x, blocks[x][y].y, null); 
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}

