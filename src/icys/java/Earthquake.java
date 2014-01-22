package icys.java;

import java.util.ArrayList;
import static icys.java.Utilities.*;

public class Earthquake extends NaturalDisaster {
	public Earthquake() {
		quake = true;
		dieDieDIE();
		int times = 3;
		do{
			for (int x = 0; x < Utilities.blocks.length; x ++)
				for (int y = 0; y < Utilities.blocks[x].length; y++)
					blocks[x][y].nudgeChange (-5,0);
		//====================
		for (int x = 0; x < Utilities.blocks.length; x ++)
			for (int y = 0; y < Utilities.blocks[x].length; y++)
				blocks[x][y].nudgeChange (3,-2);
		//===================
		for (int x = 0; x < Utilities.blocks.length; x ++)
			for (int y = 0; y < Utilities.blocks[x].length; y++)
				blocks[x][y].nudgeChange (-5,-6);
		//====================
		for (int x = 0; x < Utilities.blocks.length; x ++)
			for (int y = 0; y < Utilities.blocks[x].length; y++)
				blocks[x][y].nudgeChange (5,0);
		//=====================
		for (int x = 0; x < Utilities.blocks.length; x ++)
			for (int y = 0; y < Utilities.blocks[x].length; y++)
				blocks[x][y].nudgeChange (5,1);
		//====================
		for (int x = 0; x < Utilities.blocks.length; x ++)
			for (int y = 0; y < Utilities.blocks[x].length; y++)
				blocks[x][y].nudgeChange (0,0);
		times--;
		} while (times >0);
	}

	public void dieDieDIE() {
		bears.clear();
		fish.clear();
		penguins.clear();
	} // End of lose Bears
}