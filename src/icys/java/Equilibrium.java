package icys.java;

import static icys.java.Utilities.LAND;
import static icys.java.Utilities.WATER;
import static icys.java.Utilities.bears;
import static icys.java.Utilities.blocks;
import static icys.java.Utilities.eggs;
import static icys.java.Utilities.fish;
import static icys.java.Utilities.penguins;

import java.awt.Graphics;

public class Equilibrium extends Mode {
	
	public Equilibrium () {
		super();
		
		for (int i = 0 ; i < 64 ; i++) {
			fish.add(new Fish (i));
		}
		
		for (int i = 0 ; i < 3 ; i++) {
			Egg babyPen = new Egg (eggs.size()); 
			Block home = babyPen.randomBlock();
			if (home != null) {
			babyPen = new Egg (eggs.size(), home.x, home.y);
			eggs.add(babyPen);
			System.out.println("An egg was spontaneously generated!");
			}
		}
		
		for (int i = 0 ; i < 2 ; i++) {
			Penguin newPen = new Penguin (penguins.size());
			Block place = newPen.randomBlock();
			if (place != null) {
			newPen = new Penguin (penguins.size(), place.x, place.y);
		    penguins.add(newPen);
		    System.out.println("A penguin was spontaneously generated!");
			}
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			PolarBear bwear = new PolarBear (bears.size());
			Block territory = bwear.randomBlock();
			if (territory != null) {
			bwear = new PolarBear (bears.size(), territory.x, territory.y);
			bears.add(bwear);
			System.out.println("A bear was spontaneously generated!");
			}
		}
		
	}	
	
	public void draw(Graphics g) {
		if (fish.size() < 30)
		for (int i = 0 ; i < 10 ; i++) {
			fish.add(new Fish (i));
		}
		super.draw(g);
	}
	
}