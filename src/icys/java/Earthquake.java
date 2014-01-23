package icys.java;

import java.util.ArrayList;
import static icys.java.Utilities.*;

public class Earthquake extends NaturalDisaster {
	
	int shakiness = 6;
	
	public Earthquake() {
		isQuake = 10 * 2;
		dieDieDIE ();
	}

	public void dieDieDIE() {
		bears.clear();
		fish.clear();
		penguins.clear();
	} // I WILL NEVER GIVE UP!!!  dieDieDIE
	
	public void update (){
		dieDieDIE (); // WHY WON'T YOU DIE FISH?
		isQuake--;
		if (nudgeX == 0)
			nudgeX = (int)(Math.random ()*shakiness);
		else
			nudgeX = 0;
		if (nudgeY == 0)
			nudgeY = (int)(Math.random ()*shakiness);
		else
			nudgeY = 0;
	}	
}