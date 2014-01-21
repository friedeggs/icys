package icys.java;

import java.awt.Graphics;
import static icys.java.Utilities.*;

public abstract class Entity {
	public int x, y; // X AND Y ARE INDEXES OF THE 2D ARRAY CALLED BLOCKS
	
	/**
	 * This method finds the distance between the penguin and fish parameter
	 * @param lifeform
	 * @return
	 */
	public int distanceTo (Entity entity)
	{
		return (Math.abs(x - entity.x)) + (Math.abs(y - entity.y));
	}
	
	public abstract void show (Graphics g);
	
	/**
	 * Converts a block x index into coordinates
	 */
	public int coordX (int index) {
		return index * 30; // TEMPORARY
	}	
	
	/**
	 * Converts a block y index into coordinates
	 */
	public int coordY (int index) {
		return index * 30 + offset+2*border; // TEMPORARY
	}
}