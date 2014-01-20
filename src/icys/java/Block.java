package icys.java;

import java.awt.Graphics;

public class Block
{

public boolean isOccupied;
public int value; 
public int x, y, indexX, indexY; // X, Y ARE COORDINATES OR INDICES?
public LifeForm lifeform;

  public Block (int value)
  {
    boolean isOccupied = false; 
    this.value = value; 
  }
  
  public void set (int x, int y) {
	  this.x = x;
	  this.y = y;
  }
  
  public void set (LifeForm l) {
	  lifeform = l;
  }
  
  public int getX () {
	  return indexX;
  }
  
  public int getY () {
	  return indexY;
  }
  
  public void setIndices (int x, int y) {
	  indexX = x;
	  indexY = y;
  }
  
  public void draw(Graphics g)
  {
    if (lifeform != null) {
    	lifeform.show(g);
    }
  }
}
