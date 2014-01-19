import java.util.ArrayList;

public abstract class NaturalDisaster extends Mode
{
}

public class MeltingGlacier extends NaturalDisaster
{
    public MeltingGlacier (ArrayList < PolarBear > list, int [][] block)
    {
        lostBear (list);
        meltArea ();
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

public class WaterPollution extends NaturalDisaster
{
    public WaterPollution (ArrayList < Fish > list)
    {
        stopFish (list);
        blackenWater (  /*Other Crap*/);
    }


    public void stopFish (ArrayList < Fish > list)
    {
        for (int i = 0 ; i < list.size () ; i++)
            list.get < i > .canAppear = false;
    }


    public void blackenWater (Block[] [])
    {
        //Cover all water block with darker shade
        //Black or dark blue?
        //(11,23,59)
        
        boolean [][] cover = new boolean [block.length ()][block[0].length ()];
        for (int i = 0; i < cover.length; i ++)
            for (int j = 0; j < cover[i].length; j ++)
                    cover[i][j] = false;
        for (int i = 0; i < block.length; i ++)
            for (int j = 0; j < block[i].length; j ++)
                if (block[i][j]== -1 || block [i][j] == 0)
                    cover[i][j] = true;
                    
        Color dark = new Color (11,23,59);
        
        
    }
} //End of Class

public class Earthquake extends NaturalDisaster
{
    public Earthquake (ArrayList < PolarBear > list, ArrayList < Penguin > list2)
    {
        loseBears (list);
        losePenguins (list2);
    }


    public void loseBears (ArrayList < PolarBear > list)
    {
        //25%
        boolean dead = false;
        for (int i = 0 ; i < list.size () ; i++)
        {
            dead = Math.random () < 0.25;
            if (dead)
            {
                list.remove (i);
                i--;
            }
        }
    } //End of lose Bears


    public void losePenguins (ArrayList < Penguin > list)
    {
        //30%
        boolean dead = false;
        for (int i = 0 ; i < list.size () ; i++)
        {
            dead = Math.random () < 0.3;
            if (dead)
            {
                list.remove (i);
                i--;
            }
        }

    } //End of lose penguins


    public void nudge (Block[] [])
    {
        //lol WUT.
    }
} //End of Class

/* ------- Ideas -------
    ArrayList --> Objects
    loseBear, loseFish, etc. goes into the program
    and deletes object from Array[List]
    meltArea; blackenWater etc. --> GUI?
    
    Blocks are the box is the number of box with the certain
    [ROW NO.] [COL NO.]
    -1 = Unused
    0 = Water
    1 = Land
   ------- Ideas ------- */
