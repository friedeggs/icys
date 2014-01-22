package icys.java;

import java.util.ArrayList;
import static icys.java.Utilities.*;

public class Earthquake extends NaturalDisaster {
	public Earthquake() {
		quake = true;
		dieDieDIE();
	}

	public void dieDieDIE() {
		bears.clear();
		fish.clear();
		penguins.clear();
	} // I WILL NEVER GIVE UP!!!  dieDieDIE
	
	public void update (){
		if (nudgeX == 0)
			nudgeX = (int)(Math.random ()*6);
		else
			nudgeX = 0;
		if (nudgeY == 0)
			nudgeY = (int)(Math.random ()*6);
		else
			nudgeY = 0;
	}	
}