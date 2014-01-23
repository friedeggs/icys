package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Penguin extends LifeForm {
	
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

	public void eatFish ()
	{
		if (x == target.x && y == target.y)
		{
			if (target instanceof Fish) {
				((Fish) target).remove();
				if (currentScreen == mode && player != null && equals (player)) {
					System.out.println ("PENGUIN OVER HERE");
					score++;
				}
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
			blocks [x][y].targeter = baby;
			blocks [x][y].set(this);
			eggs.add(baby);
			direction [1][1] = -1;
			//System.out.println("EEEEEEGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
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
	
	public boolean equals (Penguin p) {
		return (p.x == x && p.y == y);
	}
	
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
