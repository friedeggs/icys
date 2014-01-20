import java.util.ArrayList;

public class Earthquake extends NaturalDisaster {
	public Earthquake(ArrayList<PolarBear> list, ArrayList<Penguin> list2) {
		loseBears(list);
		losePenguins(list2);
	}

	public void loseBears(ArrayList<PolarBear> list) {
		// 25%
		boolean dead = false;
		for (int i = 0; i < list.size(); i++) {
			dead = Math.random() < 0.25;
			if (dead) {
				list.remove(i);
				i--;
			}
		}
	} // End of lose Bears

	public void losePenguins(ArrayList<Penguin> list) {
		// 30%
		boolean dead = false;
		for (int i = 0; i < list.size(); i++) {
			dead = Math.random() < 0.3;
			if (dead) {
				list.remove(i);
				i--;
			}
		}
	} // End of lose penguins

	public void nudge() {
		// lol WUT.
	}

}