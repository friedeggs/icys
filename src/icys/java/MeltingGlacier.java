package icys.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MeltingGlacier extends NaturalDisaster {
	
	char [][] myCrap = new char [15][21];
	
	public MeltingGlacier(ArrayList<PolarBear> list, int[][] block) {
		lostBear(list);
		meltArea(block);
	}

	public void lostBear(ArrayList<PolarBear> list) {
		// 50%
		// grid [row] [col] = Math.random () < density; <Boolean?
		boolean dead = false;
		for (int i = 0; i < list.size(); i++) {
			dead = Math.random() < 0.5;
			if (dead) {
				list.remove(i);
				i--;
			}
		}
	} // End of remove polar bear list

	public void meltArea(int[][] block) {
		// Cover certain blocks around the edge with "water" blocks?
		// Make sure that it covers bears grids?
		// How many percent?
		// -1 not used
		// 0 water
		// 1 land
		// what are the coordinates to multiply/add for GUI

		// 175, 217, 255

		read();

	}

	public void read() {

		String[] splited = new String [315];
		String result = "a";

		BufferedReader br = null;
		String sCurrentLine = null;
		try {
			br = new BufferedReader(new FileReader("melt.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				result = sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		splited = result.split("	");
		int indexS = 0;
		for (int i = 0; i < myCrap.length; i++)
			for (int j = 0; j < myCrap[i].length; j++) {
				myCrap[i][j] = splited[indexS].charAt(0);
				indexS++;
			}

	} // End of read method

} // End of Class
