package icys.java;

public abstract class Entity {
	public int x, y;
	
	/**
	 * This method finds the distance between the penguin and fish parameter
	 * @param lifeform
	 * @return
	 */
	public int distanceTo (Entity entity)
	{
		return (Math.abs(x - entity.x)) + (Math.abs(y - entity.y));
	}
}