package icys.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static icys.java.Utilities.*;


public class MeltingGlacier extends NaturalDisaster {
	
	char [][] values = new char [21][15];
	int time = 0;
	
	public MeltingGlacier() {
		melted = true;
		//lostBear();
		meltArea();
		sinkLifeForms ();
	}	
	
	public void update () {
		System.out.println("GLACIER UPDATEEEEEEEE");
		time++;
		if (time == interval) {
			revert ();
			melted = false;
		}
	}
	
	public void sinkLifeForms () {
		for (int i = 0 ; i < blocks.length ; i++)
			for (int j = 0 ; j < blocks[0].length ; j++)
				if (blocks [i][j].lifeform != null && blocks [i][j].value != LAND)
					blocks [i][j].lifeform.meltY += 2 * block_height / interval;
	}
	
	
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
	
	public void revert ()
	{
//		for (int i = 0 ; i < blocks.length ; i++)
//			for (int j = 0 ; j < blocks[0].length ; j++)
//				if (blocks [i][j].lifeform != null && 
//				blocks [i][j].value != LAND) {
//					System.out.println("sunk");
//					blocks [i][j].lifeform.remove();
//					System.out.println(blocks [i][j].lifeform);
//				}
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
