package icys.java;

import static icys.java.Utilities.*;

import java.awt.Graphics;
public class Simulation extends Mode{

	public Simulation ()
	{
		super();
		
		for (int i = 0 ; i < 3 ; i++) {
			fish.add(new Fish (i));
		}
		
		for (int i = 0 ; i < 3 ; i++) {
			eggs.add(new Egg (i, 8, 12));
		}
		
		for (int i = 0 ; i < 3 ; i++) {
			penguins.add(new Penguin (i, 10, 10));
		}
		
		for (int i = 0 ; i < 3 ; i++) {
			bears.add(new PolarBear (i, 5, 12));
		}
		
	}
	
	public void draw(Graphics g) {

		super.draw(g);
		
//		for (int i = 0 ; i < penguins.size () ; i++) {
//			penguins.get(i).update ();
//		}

		System.out.println("==========");
	}
	
}
