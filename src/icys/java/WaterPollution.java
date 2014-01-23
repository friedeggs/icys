package icys.java;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static icys.java.Utilities.*;

public class WaterPollution extends NaturalDisaster {

	char [][] values = new char [21][15];
	
	public WaterPollution() {
		polluted = true;
		stopFish();
		blackenWater();
	}

	public void stopFish () {
		Utilities.fish.clear (); //Deletes all fish
		stopFish = 30;
	}

	public void blackenWater() {
		if (melted)
			read ("blackWaterAndMelt");
		else
			read("blackWater");
		for (int i = 0; i < values.length; i ++)
			for (int j = 0; j <values[i].length; j++)
				Utilities.blocks[i][j].changeValue (values[i][j]);

	}
	
	public void revert ()
	{
		polluted = false;
		read("start");
		for (int i = 0; i < values.length; i ++)
			for (int j = 0; j <values[i].length; j++)
				Utilities.blocks[i][j].changeValue (values[i][j]);
	}

	public void read(String name) {

		String[] splited = new String [315];
		String result = "a";

		BufferedReader br = null;
		String sCurrentLine = null;
		try {
			br = new BufferedReader(new FileReader(name+".txt"));

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
		for (int i = 0; i < values[0].length; i++)
			for (int j = 0; j < values.length; j++) {
				values[j][i] = splited[indexS].charAt(0);
				indexS++;
			}

	} // End of read method

} // End of Class
