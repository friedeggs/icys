package icys.java;

//imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static icys.java.Utilities.*;

public class WaterPollution extends NaturalDisaster {

	//Initialization
	char [][] values = new char [21][15];
	
	//Constructor
	public WaterPollution() {
		polluted = true;
		stopFish();
		blackenWater();
	}

	//wipes out fish for this certain time
	public void stopFish () {
		Utilities.fish.clear (); //Deletes all fish
		stopFish = 30;
	}

	//set water area to black
	public void blackenWater() {
		if (melted)
			read ("blackWaterAndMelt");
		else
			read("blackWater");
		for (int i = 0; i < values.length; i ++)
			for (int j = 0; j <values[i].length; j++)
				Utilities.blocks[i][j].changeValue (values[i][j]);
	}
	
	
	//revert and set all water area to normal color
	public void revert ()
	{
		polluted = false;
		read("start");
		for (int i = 0; i < values.length; i ++)
			for (int j = 0; j <values[i].length; j++)
				Utilities.blocks[i][j].changeValue (values[i][j]);
	}

	//read .txt files method
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
