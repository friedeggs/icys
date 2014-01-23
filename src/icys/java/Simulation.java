package icys.java;

//imports
import static icys.java.Utilities.*;

import java.awt.Graphics;

public class Simulation extends Mode{

	//constructor
	public Simulation ()
	{
		super();
		
		//Add fish
		for (int i = 0 ; i < 3 ; i++) {
			fish.add(new Fish (i));
		}
		
		//Add eggs
		for (int i = 0 ; i < 3 ; i++) {
			eggs.add(new Egg (i, 8, 12));
		}
		
		//Add penguins
		for (int i = 0 ; i < 3 ; i++) {
			penguins.add(new Penguin (i, 10, 10));
		}
		
		//Add bears
		for (int i = 0 ; i < 3 ; i++) {
			bears.add(new PolarBear (i, 5, 12));
		}
		
	}
	
	//GUI
	public void draw(Graphics g) {
		super.draw(g);
		
//		for (int i = 0 ; i < penguins.size () ; i++) {
//			penguins.get(i).update ();
//		}
	}
	
}
