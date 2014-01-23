package icys.java;

import static icys.java.Utilities.*;



import java.awt.Graphics;

public class Extinction extends Mode{

	public Extinction()
	{
		super();		
		/*
		earthquake.setEnabled(false);
		pollution.setEnabled(false);
		melt.setEnabled(false);
		addEgg.setEnabled(false);
		addPenguin.setEnabled(false);
		addBear.setEnabled(false); */
		main.remove (earthquake);
		main.remove (pollution);
		main.remove (melt);
		main.remove (addEgg);
		main.remove (addPenguin);
		main.remove (addBear);
		
		for (int i = 0 ; i < 1 ; i++) {
			fish.add(new Fish (i));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			eggs.add(new Egg (i, 8, 12));
			eggs.add(new Egg (i, 14, 10));
			eggs.add(new Egg (i, 9, 5));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			penguins.add(new Penguin (i, 10, 10));
			penguins.add(new Penguin (i, 13, 7));
			penguins.add(new Penguin (i, 8, 10));
			penguins.add(new Penguin (i, 17, 9));
			penguins.add(new Penguin (i, 10, 7));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			bears.add(new PolarBear (i, 5, 12));
			bears.add(new PolarBear (i, 10, 11));
		}
		
	}
	
	public void draw(Graphics g) {

		super.draw(g);
		
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).update (g);
		}
		//System.out.println("==========");
	}
	
	
}
