package icys.java;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Player extends Penguin {

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
		if (target == null)
			return;
		super.move();
	}
	
}