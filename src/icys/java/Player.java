package icys.java;

public class Player extends Penguin {

	public Player(int index) {
		super(index);
		// TODO Auto-generated constructor stub
	}	
	
	public Player(int index, int x, int y) {
		super(index, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void update () {
		move ();
	}
	
}