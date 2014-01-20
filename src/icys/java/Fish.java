package icys.java;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Fish extends LifeForm {
	
	public Fish (int index)
	{
		super (index);
		do
			pos = randomBlock ();
		while (!byWater (pos));
		x = pos.x;
		y = pos.y;
		pos.set(this);
		try {
            image = ImageIO.read(new File ("fish.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public boolean byWater (Block block) {
		return (block.x == 0 || block.y == 0 || 
				block.x == blocks.length-1 || block.y == blocks.length-1||
				blocks [block.x-1][block.y].value == WATER || 
				blocks [block.x+1][block.y].value == WATER ||
				blocks [block.x][block.y-1].value == WATER || 
				blocks [block.x][block.y+1].value == WATER);
	}

	@Override
	public void remove() {
		fish.remove(index);
		for (int i = index ; i < fish.size() ; i++)
			fish.get(i).index--;
	}
	
}
