package icys.java;

import static icys.java.Utilities.bears;
import static icys.java.Utilities.blocks;
import static icys.java.Utilities.eggs;
import static icys.java.Utilities.fish;
import static icys.java.Utilities.penguins;

public class Simulation extends Mode{

	public Simulation ()
	{
		super();
		for (int i = 0 ; i < 1 ; i++) {
			fish.add(new Fish (i));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			eggs.add(new Egg (i, 8, 12));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			penguins.add(new Penguin (i, 10, 10));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			bears.add(new PolarBear (i, 5, 12));
		}
		
		for (int i = 0 ; i < bears.size () ; i++) {
			bears.get(i).update (g);
		}
		for (int i = 0 ; i < eggs.size () ; i++) {
			eggs.get(i).update (g);
		}
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).update (g);
		}
		for (int i = 0 ; i < blocks.length ; i++) 
			for (int j = 0 ; j < blocks[0].length ; j++)
				blocks[i][j].update(g);
	}
	
}
