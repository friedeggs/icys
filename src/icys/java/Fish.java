package icys.java;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	
	public boolean byWater (Block block) {
		return (block.x == 0 || block.y == 0 || 
				block.x == blocks.length-1 || block.y == blocks.length-1 ||
				blocks [block.x-1][block.y].value == WATER || 
				blocks [block.x+1][block.y].value == WATER ||
				blocks [block.x][block.y-1].value == WATER || 
				blocks [block.x][block.y+1].value == WATER);
	}	
	
	public boolean equals (Fish f) {
		return (f.x == x && f.y == y);
	}

	@Override
	public void remove() {
		blocks [x][y].set(null);
		ArrayList <Fish> newlist = new ArrayList <Fish> ();
		for (int i = 0 ; i < fish.size() ; i ++) {
			if (equals(fish.get(i)))
				fish.remove(i);
		}
		
		for (int i = 0 ; i < fish.size () - 1 ; i++) {
				newlist.add(fish.get(i));
				newlist.get(i).index = i;
				System.out.println (i);
		}
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
