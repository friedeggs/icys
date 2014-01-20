package icys.java;
import java.awt.Color; 
import java.awt.Graphics;

public class Block
{

public boolean isOccupied;
public int value; 
public int row, column,increaseX, increaseY, x /*=768, 567*/, y;

  public Block (int value, int i, int j)
  {
    boolean isOccupied = false; 
    this.value = value; 
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
  	
  	if (a.equals'x')
  		use = dark;
  	else
  		use = water;
  	
  	int [] xPoint = {row*x; row*x+increaseX};
  	int [] yPoint = {column*y, colume*y+increaseY};
  }
