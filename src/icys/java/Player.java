package icys.java;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Player extends Penguin {
	
	boolean ate;

	public Player() {
		super(0);
		try {
			image = ImageIO.read(new File ("penguinina.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		// TODO Auto-generated constructor stub
	}	
	
	public Player(int x, int y) {
		super(0, x, y);
		try {
			image = ImageIO.read(new File ("penguinina.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		// TODO Auto-generated constructor stub
	}
	
	public void update (Graphics g) {
		if (target == null)
			return;
		move ();
		blocks [x][y].lifeform = this;
	}
	
	protected void move () {
		System.out.println ("used------------------------");
		if (target == null) {
			if (blocks [x][y].targeter != null) {
				blocks [x][y].targeter.crossOff (x - blocks [x][y].targeter.x,
						y - blocks [x][y].targeter.y);
				if (blocks [x][y].targeter != null)
					blocks [x][y].targeter.move();
			}
			blocks [x][y].targeter = this;
			return;
		}
		super.move();
	}
	
	public boolean ateFish () {
		return ate;
	}
	
	public void remove () {
		if (equals (blocks [x][y].targeter))
				blocks [x][y].targeter = null;
		super.remove ();
	}
	
}