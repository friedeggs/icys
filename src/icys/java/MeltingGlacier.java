package icys.java;

import java.util.ArrayList;

public class MeltingGlacier extends NaturalDisaster
{
    public MeltingGlacier (ArrayList < PolarBear > list, int [][] block)
    {
        lostBear (list);
        meltArea (block);
    }


    public void lostBear (ArrayList < PolarBear > list)
    {
        //50%
        //grid [row] [col] = Math.random () < density; <Boolean?
        boolean dead = false;
        for (int i = 0 ; i < list.size () ; i++)
        {
            dead = Math.random () < 0.5;
            if (dead)
            {
                list.remove (i);
                i--;
            }
        }
    } //End of remove polar bear list


    public void meltArea ( int [][] block)
    {
        //Cover certain blocks around the edge with "water" blocks?
        //Make sure that it covers bears grids?
        //How many percent?
        // -1 not used
        // 0 water
        // 1 land
        // what are the coordinates to multiply/add for GUI
        
        //175, 217, 255
    }
    
} //End of Class
