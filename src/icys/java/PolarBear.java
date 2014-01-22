package icys.java;

import static icys.java.Utilities.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class PolarBear extends LifeForm {

	int sinceEaten;
	int penguinsEaten; 

	public PolarBear(int index)
	{
		super (index);
	}
	
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
	
	public void update(Graphics g)
	{
		//eatPenguin();
		checkAlive();
		reproduces ();
		updateTarget();
		move();
	}
	
	private void eatPenguin ()
	{
		if (x == target.x && y == target.y)
		{
			if (target instanceof Penguin) {
				((Penguin) target).remove();
				penguinsEaten ++;
				sinceEaten = 0;
				System.out.println("nom. RAWR");
				System.out.println("Penguin: *eaten by Polar Bear*");
			}
			target = null;
		}
		else
			sinceEaten ++ ;
	}
	
	private void checkAlive() 
	{
		if (sinceEaten > 60) {
			System.out.println ("Polar bear: *dies*");
			remove();
		}
	}
	
	
	private ArrayList<PolarBear> reproduces()
	{
		
		if (penguinsEaten == 5)
		{
			PolarBear baby = new PolarBear(bears.size(), x, y);
			penguinsEaten = 0;
			bears.add(baby);
		}
		return bears; 	
	}
	
	/**
	 * updateTarget: This method checks if target is alive, and calls 
	 * method to choose new target if current target is dead
	 */
	private void updateTarget() 
	{
		if (target == null || (penguins.size() > 0 && target instanceof Block))
			chooseTarget();
	}
	
	private void chooseTarget() //Chooses closest fish for new target
	{		
		if (penguins.size() == 0) {
			target = randomBlock ();
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
							eatPenguin ();
							blocks [x][y].setTargeter(this);
							blocks [x][y].set(this);
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

