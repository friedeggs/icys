package icys.java;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WaterPollution extends NaturalDisaster {

	char [][] myCrap = new char [15][21];
	
	public WaterPollution() {
		stopFish();
		blackenWater();
	}

	public void stopFish () {
		Utilities.fish.clear (); //Deletes all fish
	}

	public void blackenWater() {
		read("blackWater");
		for (int i = 0; i < myCrap.length; i ++)
			for (int j = 0; j <myCrap[i].length; j++)
				Utilities.blocks[i][j].changeValue (myCrap[i][j]);

	}
	
	public void revert ()
	{
		read("start");
		for (int i = 0; i < myCrap.length; i ++)
			for (int j = 0; j <myCrap[i].length; j++)
				Utilities.blocks[i][j].changeValue (myCrap[i][j]);
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
		for (int i = 0; i < myCrap.length; i++)
			for (int j = 0; j < myCrap[i].length; j++) {
				myCrap[i][j] = splited[indexS].charAt(0);
				indexS++;
			}

	} // End of read method

} // End of Class
