package icys.java;

//import java.awt.Color;
import java.util.ArrayList;

public class WaterPollution extends NaturalDisaster {

	public WaterPollution() {
		stopFish();
		blackenWater();
	}

	public void stopFish () {
		for (int i = 0; i < Utilities.fish.size(); i++)
			Utilities.fish.get(i).canAppear = false;
	}

	public void blackenWater() {
		// Cover all water block with darker shade
		// Black or dark blue?
		// (11,23,59)

		boolean[][] cover = new boolean[Utilities.blocks.length][Utilities.blocks[0].length];
		for (int i = 0; i < cover.length; i++)
			for (int j = 0; j < cover[i].length; j++)
				cover[i][j] = false;
		for (int i = 0; i < Utilities.blocks.length; i++)
			for (int j = 0; j < Utilities.blocks[i].length; j++)
				if (Utilities.blocks[i][j].value == 2 || Utilities.blocks[i][j].value == 0)
					cover[i][j] = true;

	}

} // End of Class
