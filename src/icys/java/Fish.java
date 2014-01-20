package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
		return (block.getX() == 0 || block.getY() == 0 || 
				block.getX() == blocks.length-1 || block.getY() == blocks.length-1||
				blocks [block.getX()-1][block.getY()].value == WATER || 
				blocks [block.getX()+1][block.getY()].value == WATER ||
				blocks [block.getX()][block.getY()-1].value == WATER || 
				blocks [block.getX()][block.getY()+1].value == WATER);
	}

	@Override
	public void remove() {
		fish.remove(index);
		for (int i = index ; i < fish.size() ; i++) {
			fish.get(i).index--;
		}
	}
	
}
