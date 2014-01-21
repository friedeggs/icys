package icys.java;

import static icys.java.Utilities.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class PolarBear extends LifeForm {

	Entity target;
	int direction;
	int sinceEaten;
	int penguinsEaten; 

	public PolarBear(int index)
	{
		super (index);
		//x int
		//y int
		blocks [x][y].set(this);
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
		eatPenguin();
		checkAlive();
		reproduces ();
		updateTarget();
		move();
	}
	
	public void eatPenguin ()
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
	
	public void checkAlive() 
	{
		if (sinceEaten > 20) {
			System.out.println ("Polar bear: *dies*");
			remove();
		}
	}
	
	
	public ArrayList<PolarBear> reproduces()
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
	public void updateTarget() 
	{
		if (target == null || (penguins.size() > 0 && target instanceof Block))
			chooseTarget();
	}
	
	public void chooseTarget() //Chooses closest fish for new target
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

	public boolean valid (int i, int j)
	{
		return (Math.abs(i+j) == 1) && x+i >= 0 && x+i < blocks.length &&
				y+j >= 0 && y+j < blocks[0].length && blocks [x+i][y+j].value 
				== LAND && free (blocks[x+i][y+j]);
	}
	
	public boolean free (Block block) {
		return block.lifeform == null || block.targeter == null ||
				block.lifeform instanceof Penguin;
	}
	
	public int closer (int i, int j) {
		if (blocks [x+i][y+j].distanceTo(target) < distanceTo (target))
			return 3;
		if (blocks [x+i][y+j].distanceTo(target) == distanceTo (target))
			return 2;
		return 1;
	}
	
	public void move ()
	{
		int direction [][] = new int [3][3]; // CHANGE IN X AND Y
		int counter [] = new int [3];
		for (int i = -1 ; i <= 1 ; i++)
			for (int j = -1 ; j <= 1 ; j++) {
				if (valid (i, j)) {
					direction [i+1][j+1] = closer (i, j);
					counter [direction [i+1][j+1]-1 ]++;
				}
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
							blocks [x][y].setTargeter(this);
							return;
						}
					}
		}
	}

	public void move (int dir)
	{
		 
	}
	
	@Override
	public void remove() {
		blocks [x][y].set(null);
		bears.remove(index);
		for (int i = index ; i < bears.size() ; i++)
			bears.get(i).index--;
	}

}

