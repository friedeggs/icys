package icys.java;
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Polygon;

import java.awt.Graphics;

public class Block
{

public boolean isOccupied;
public int value; 
<<<<<<< HEAD
public int x, y, indexX, indexY; // X, Y ARE COORDINATES OR INDICES?
public LifeForm lifeform;
=======
public int row, column,increaseX, increaseY, x /*=768, 567*/, y;
>>>>>>> 9c4d4764f8c2441a16d74326a0a9cdbcbc7956b9

  public Block (int value1, int i, int j)
  {
    isOccupied = false; 
    value = value1; 
    row = i;
    column = j;
  }
  
  /* public void set (int iC, int jC) {
	  row = iC;
	  column = jC;
  }*/
  
<<<<<<< HEAD
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
=======
  public void draw(Graphics g, char a)
  {
  	Color water = new Color (175, 217, 255),
  	land = new Color (0,0,0),
  	dark = new Color (11,23,59),
  	use;
  	
  	if (value != 1){
  		if (a =='x')
  			use = dark;
  		else
  			use = water;
  	}
  	else
  		use = land;
  	
  	int [] xPoint = {row*x, row*x+increaseX, row*x - increaseX, row + increaseX};
  	int [] yPoint = {column*y, column*y+increaseY, column*y-increaseY, column - increaseY};
  	g.setColor (use);
  	Polygon poly = new Polygon (xPoint, yPoint, xPoint.length);
  	g.drawPolygon (poly);
>>>>>>> 9c4d4764f8c2441a16d74326a0a9cdbcbc7956b9
  }
}
