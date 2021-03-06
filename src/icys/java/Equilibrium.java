package icys.java;

//Imports
import static icys.java.Utilities.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Equilibrium extends Mode {
	
	//Initialization
	public Equilibrium () {
		super();
		//Remove buttons that should be disabled
		main.remove (earthquake);
		main.remove (pollution);
		main.remove (melt);
		main.remove (addEgg);
		main.remove (addPenguin);
		main.remove (addBear);
		
		//Add fish
		for (int i = 0 ; i < 64 ; i++) {
			fish.add(new Fish (i));
		}
		
		//Add eggs
		for (int i = 0 ; i < 3 ; i++) {
			Egg babyPen = new Egg (eggs.size()); 
			Block home = babyPen.randomBlock();
			if (home != null) {
			babyPen = new Egg (eggs.size(), home.x, home.y);
			eggs.add(babyPen);
			}
		}
		
		//Add penguins
		for (int i = 0 ; i < 2 ; i++) {
			Penguin newPen = new Penguin (penguins.size());
			Block place = newPen.randomBlock();
			if (place != null) {
			newPen = new Penguin (penguins.size(), place.x, place.y);
		    penguins.add(newPen);
			}
		}
		
		//Add polar bears
		for (int i = 0 ; i < 1 ; i++) {
			PolarBear bwear = new PolarBear (bears.size());
			Block territory = bwear.randomBlock();
			if (territory != null) {
			bwear = new PolarBear (bears.size(), territory.x, territory.y);
			bears.add(bwear);
			}
		}
		
	}	
	
	//Draw, GUI, update fish, etc.
	public void draw(Graphics g) {
		if (fish.size() < 30)
		for (int i = 0 ; i < 10 ; i++) {
			fish.add(new Fish (i));
		}
		super.draw(g);
	}
	
	/*--------ALL THE MOUSE MOVEMENTS------------*/
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX ();
		int y = e.getY ();
		if (back.contains (x, y)) {
			back.setState (1);
			main.repaint ();
		}
		else if (help.contains (x, y)) {
			help.setState (1);
			main.repaint ();
		}
				
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (back.getState () == 1) {
			back.setState(0);
			main.setScreen(SelectionScreen);
		}
		if (help.getState() == 1){
			help.setState(0);
			main.setScreen(HelpScreen);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX ();
		int y = e.getY ();
		if (back.contains (x, y))
			back.setState (1);
		else 
			back.setState (0);
		if (help.contains (x, y))
			help.setState (1);
		else
			help.setState (0);
		}
	
}