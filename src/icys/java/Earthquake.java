package icys.java;

//Imports
import static icys.java.Utilities.*;

public class Earthquake extends NaturalDisaster {
	
	//Initialization
	int shakiness = 6;
	
	//Constructor
	public Earthquake() {
		isQuake = 10 * 2;
		dieDieDIE ();
	}

	//Removes all life forms
	public void dieDieDIE() {
		bears.clear();
		fish.clear();
		penguins.clear();
	}
	
	//Update GUI and runs Earthquake
	public void update (){
		dieDieDIE ();
		isQuake--; //Time running out
		if (nudgeX == 0)
			nudgeX = (int)(Math.random ()*shakiness); //value to nudge X by
		else
			nudgeX = 0;
		if (nudgeY == 0)
			nudgeY = (int)(Math.random ()*shakiness);//value to nudge X by
		else
			nudgeY = 0;
	}	
}