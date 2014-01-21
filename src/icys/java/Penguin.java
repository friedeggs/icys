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
		blocks [x][y].set(this);
		fishesEaten = 0;
		sinceEaten = 0;
		chooseTarget ();
		try {
			image = ImageIO.read(new File ("penguino.png"));
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
				System.out.println("omNOMNOM");
			}
			target = null;
		}
		else
			sinceEaten ++ ;
	}
	
	public void checkAlive() 
	{
		if (sinceEaten > 20) {
			System.out.println ("Penguin: *dies of hunger*");
			remove();
		}
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
		if (target == null || (fish.size() > 0 && target instanceof Block))
			chooseTarget();
	}
	
	public void chooseTarget() //Chooses closest fish for new target
	{
		if (fish.size() == 0) {
			// choose a random block of land
			target = randomBlock ();
			//System.out.println("random target");
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

	public boolean valid (int i, int j)
	{
		return (Math.abs(i+j) == 1) && x+i >= 0 && x+i < blocks.length &&
				y+j >= 0 && y+j < blocks[0].length && blocks [x+i][y+j].value 
				== LAND && free (blocks[x+i][y+j]) &&
				blocks [x+i][y+j].distanceTo(target) < distanceTo (target);
	}
	
	public boolean free (Block block) {
		return block.lifeform == null || block.targeter == null ||
				block.lifeform instanceof Fish;
	}
	
	public void move ()
	{
		boolean direction [][] = new boolean [3][3]; // CHANGE IN X AND Y
		int counter = 0;
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++) {
				if (valid (i, j)) {
					direction [i+1][j+1] = true;
					counter++;
				}
			}
		
		if (counter == 0) // There is nowhere to move - don't move
			return;

		blocks [x][y].targeter = null;
		int random = (int)(Math.random() * counter);
		
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++)
				if (direction [i+1][j+1]) {
					counter--;
					if (random == counter) {
						x += i;
						y += j;
					}
				}
		blocks [x][y].setTargeter(this);
	}

	public void move (int dir)
	{
		
	}
	
	@Override
	public void remove() {
		penguins.remove(index);
		for (int i = index ; i < penguins.size() ; i++)
			penguins.get(i).index--;
	}
}
