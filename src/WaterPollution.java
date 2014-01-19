import java.util.ArrayList;

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