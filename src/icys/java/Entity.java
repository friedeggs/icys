package icys.java;

import java.awt.Graphics;

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
}