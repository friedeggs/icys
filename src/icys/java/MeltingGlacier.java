package icys.java;

//import
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static icys.java.Utilities.*;


public class MeltingGlacier extends NaturalDisaster {
	
	//initialization
	char [][] values = new char [21][15];
	int time = 0;
	
	//constructor
	public MeltingGlacier() {
		melted = true;
		//lostBear();
		meltArea();
		sinkLifeForms ();
	}	
	
	//update everything
	public void update () {
		//time increase
		time++;
		if (time == interval) {
			revert ();
			melted = false;
		}
	}
	
	//make life forms drop into the water
	public void sinkLifeForms () {
		for (int i = 0 ; i < blocks.length ; i++)
			for (int j = 0 ; j < blocks[0].length ; j++)
				if (blocks [i][j].lifeform != null && blocks [i][j].value != LAND)
					blocks [i][j].lifeform.meltY += 2 * block_height / interval;
	}
	
	//melt required area
	public void meltArea() {
		// 175, 217, 255
		if (polluted)
			read ("blackWaterAndMelt");
		else
			read("melt");
		for (int i = 0; i < values.length; i ++)
			for (int j = 0; j <values[i].length; j++)
				Utilities.blocks[i][j].changeValue (values[i][j]);
	}
	
	//make glaciers back to normal
	public void revert ()
	{
		read("start");
		for (int i = 0; i < values.length; i ++)
			for (int j = 0; j <values[i].length; j++)
				Utilities.blocks[i][j].changeValue (values[i][j]);
	}

	//read stored text file
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
