package icys.java;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Fish extends LifeForm {
	
	Block pos;
	
	public Fish (int index)
	{
		super (index);
		// WHAT IF THERE ISN'T ANY AVAILABLE SPACE?
		do
			pos = randomBlock ();
		while (!byWater (pos) || pos.lifeform != null);
		x = pos.x;
		y = pos.y;
		pos.set(this);
		try {
            image = ImageIO.read(new File ("fishh.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public void update () {
		blocks[x][y].setTargeter(this);
	}
	
	public boolean byWater (Block block) {
		return (block.x == 0 || block.y == 0 || 
				block.x == blocks.length-1 || block.y == blocks.length-1 ||
				blocks [block.x-1][block.y].value == WATER || 
				blocks [block.x+1][block.y].value == WATER ||
				blocks [block.x][block.y-1].value == WATER || 
				blocks [block.x][block.y+1].value == WATER);
	}

	@Override
	public void remove() {
		blocks [x][y].set(null);
		fish.remove(index); // SOMETHING'S WRONG I THINK FISH
		// KEEP GETTING REMOVED WHEN THEY DON'T EXIST
		// java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
		for (int i = index ; i < fish.size() ; i++)
			fish.get(i).index--;
	}
	
}
