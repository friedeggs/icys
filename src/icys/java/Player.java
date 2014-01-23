package icys.java;

//import
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Player extends Penguin {

	//constructor
	public Player() {
		super(0);
		try {
			image = ImageIO.read(new File ("penguinina.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		// TODO Auto-generated constructor stub
	}	
	
	//constructor with X and Y values as coordinates
	public Player(int x, int y) {
		super(0, x, y);
		try {
			image = ImageIO.read(new File ("penguinina.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		// TODO Auto-generated constructor stub
	}
	
	// update
	public void update (Graphics g) {
		if (target == null)
			return;
		move ();
		blocks [x][y].lifeform = this;
	}
	
	//moves player around and around and around
	protected void move () {
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
	
}