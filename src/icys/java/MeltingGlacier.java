import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//HELLO WORLD!!! :D
public class MeltingGlacier extends NaturalDisaster {
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

	public void read(String name) {

		String[][] rows = new String[16][21];
		int index = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(
					"C://Users/Lili/Desktop/nope.txt")));
			String str = null;

			while ((str = br.readLine()) != null) {
				rows[index] = str.split("HEYHEYHEYHEYHEY");
				index++;
			}

			for (String[] strings : rows) {
				for (String string : strings) {
					System.out.print(string);
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // End of read method

} // End of Class