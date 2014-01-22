package icys.java;


import java.util.ArrayList;
import static icys.java.Utilities.*;

public class Earthquake extends NaturalDisaster {
	public Earthquake() {
		quake = true;
		loseBears();
		losePenguins();
	}

	public void loseBears() {
		// 25%
		boolean dead = false;
		for (int i = 0; i < bears.size(); i++) {
			dead = Math.random() < 0.25;
			if (dead) {
				bears.remove(i);
				i--;
			}
		}
	} // End of lose Bears

	public void losePenguins() {
		// 30%
		boolean dead = false;
		for (int i = 0; i < penguins.size(); i++) {
			dead = Math.random() < 0.3;
			if (dead) {
				penguins.remove(i);
				i--;
			}
		}
	} // End of lose penguins

	public void nudge() {
		// lol WUT.
	}

}