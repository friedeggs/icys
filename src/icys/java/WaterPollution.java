package icys.java;
import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class WaterPollution extends NaturalDisaster
{
    boolean [][] cover;
    public WaterPollution (ArrayList < Fish > list, int [][] block, Graphics g)
    {
        cover = new boolean [block.length][block[0].length];
        for (int i = 0; i < cover.length; i ++)
            for (int j = 0; j < cover[i].length; j ++)
                    cover[i][j] = false;
        for (int i = 0; i < block.length; i ++)
            for (int j = 0; j < block[i].length; j ++)
                if (block[i][j]== -1 || block [i][j] == 0)
                    cover[i][j] = true;

        stopFish (list);
        blackenWater (g, blocks  /*Other Crap*/);
    }


    public void stopFish (ArrayList < Fish > list)
    {
        for (int i = 0 ; i < list.size () ; i++)
            list.get ( i ) .canAppear = false;
    }

    public void blackenWater (Graphics g, Block[][] block) //(this is going to be the draw method)
    {
        //Cover all water block with darker shade
        //(11,23,59)
        
		for (int i = 0; i < block.length; i ++)
            for (int j = 0; j < block[i].length; j ++)
                if (cover[i][j]);
                    
                            
        Color dark = new Color (11,23,59);

        
        
    }
} //End of Class

