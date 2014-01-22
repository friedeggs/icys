package icys.java;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	public void update () {
		if (target == null)
			return;
		move ();
	}
	
	protected void move () {
		if (target == null)
			return;
		super.move();
	}
	
}