package icys.java;
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Polygon;

public class Block
{

public boolean isOccupied;
public int value; 
public int row, column,increaseX, increaseY, x /*=768, 567*/, y;

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
  }
}
