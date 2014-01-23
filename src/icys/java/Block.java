package icys.java;

//Imports
import static icys.java.Utilities.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Block extends Entity {

	//Initialization
	public int value;
	public LifeForm lifeform, targeter;
	public Polygon poly;

	//Constructor
	public Block(int value1, int i, int j) {
		value = value1;
		x = i;
		y = j;
	}

	//Set life form on block
	public void set(LifeForm l) {
		lifeform = l;
	}	
	
	//Sets the target of the life form on it
	public void setTargeter(LifeForm l) {
		targeter = l;
	}
	
	//Update targets and timing
	public void update (Graphics g) {
		
		//timed, refreshes every interval
		if (TIMER % sleep == 0 && value == LAND &&
				!(targeter == null && (lifeform instanceof Egg 
				|| lifeform instanceof Fish || lifeform instanceof Player))) {
			lifeform = targeter;
			if (lifeform != null) {
				lifeform.x += lifeform.chosenDir [0];
				lifeform.y += lifeform.chosenDir [1];
			}
			targeter = null;
		}
		show (g);
	}
	
	//Use the character read in files to decide whether the block is land, water, unused, or polluted	
	public void changeValue (char x){
		if (x == '0')
			value = Utilities.WATER;
		else if (x =='1')
			value = Utilities.LAND;
		else if (x =='2')
			value = Utilities.UNUSED;
		else if (x =='3')
			value = Utilities.POLLUTED;
	}

	//GUI, repaint, etc.
	public void show(Graphics g)
	{
		//Set land color
		Color land = new Color(255, 255, 255), use;

		if (value != 1) {
			if (value == 3) {
				use = dark; // water pollution
			}
			else
				use = water; //regular water
		} else
			use = land;
		if (use == land) {
			use = new Color (255-x*3,255, 255-y*3); //Gives gradient to land
		}

		// the 2x 2D Arrays sets the points for X and Y to draw polygon
		int [] xPoint = {coordX(x), coordX(x)+block_width, 
				coordX(x)+block_width-shift, coordX(x)-shift};
		int [] yPoint = {coordY(y), coordY(y), 
				coordY(y)+block_height, coordY(y)+block_height};
		g.setColor(use); //sets use color
		poly = new Polygon (xPoint, yPoint, xPoint.length);
		g.fillPolygon (poly); //draw Polygon
		if (value == LAND) { //special land polygons
			g.setColor (blue);
			g.drawPolygon (poly);
		}//paints the ledge for blocks near the water
		else if (y > 0 && blocks [x][y-1].value == 1) {
			int ledge = 5;
			int [] threedX = {coordX(x), coordX(x)+block_width, 
					coordX(x)+block_width, coordX(x)};
			int [] threedY = {coordY(y), coordY(y), 
					coordY(y)+ledge, coordY(y)+ledge};
			g.setColor(Color.GRAY);
			Polygon threed = new Polygon (threedX, threedY, threedX.length);
			g.fillPolygon (threed);
		}
		
		//also, otherside ledges for 3D effects
		if (value != 1 && x > 0 && blocks [x-1][y].value == 1) {
			int ledge = 5;
			int [] threedX = {coordX(x), coordX(x), 
					coordX(x)-shift, coordX(x)-shift};
			int [] threedY = {coordY(y), coordY(y)+ledge, 
					coordY(y)+block_height+ledge, coordY(y)+block_height};
			g.setColor(Color.GRAY);
			Polygon threed = new Polygon (threedX, threedY, threedX.length);
			g.fillPolygon (threed);
		}
		
		if (currentScreen instanceof Game && value == LAND) {
			tile [x][y].draw(g);
		}
		
		//if life form is not empty, draw lifeform on the block
		if (lifeform != null) {
			lifeform.sink(); // does nothing if glacier is not melting
			lifeform.show(g);
		}
	}
}
