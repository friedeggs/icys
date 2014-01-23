package icys.java;

//Import
import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Game extends Mode {

	// Initialization
	boolean alive = true;
	Player player;
	Tile hovered, targeted;
	LabelButton scoreLabel, endLabel;

	// Constructor
	public Game() {
		super();
		
		//Remove buttons that should be disabled
		main.remove (earthquake);
		main.remove (pollution);
		main.remove (melt);
		main.remove (addEgg);
		main.remove (addPenguin);
		main.remove (addBear);

		//Comments 
		score = 0;
		scoreLabel = new LabelButton("Fish eaten: " + score, font, Color.WHITE, blue);
		endLabel = new LabelButton("THE END", 
				new Font ("Calibri Light", Font.PLAIN, 48), 
				Color.WHITE, blue);
		main.add(scoreLabel);
		main.add(endLabel);
		endLabel.setVisible (false);
		main.add(main.background);

		// Add title
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[0].length; j++) {
				tile[i][j] = new Tile(i, j);
			}
		}

		// Add Fish
		for (int i = 0; i < 1; i++) {
			fish.add(new Fish(i));
		}

		// Add eggs
		for (int i = 0; i < 1; i++) {
			eggs.add(new Egg(i, 8, 12));
		}

		// Add penguins
		for (int i = 0; i < 1; i++) {
			penguins.add(new Penguin(i, 10, 10));
		}

		// Add polar bears
		for (int i = 0; i < 1; i++) {
			bears.add(new PolarBear(i, 5, 12));
		}

		// Add player
		player = new Player(12, 7);
		player.target = null;
		penguins.add(player);

	}

	// determine if the game should go on by returning if player is alive
	public boolean ongoing() {
		return alive;
	}

	// End game = player -> dead
	public void endGame() {
		if (targeted != null)
			targeted.setState(0);
		alive = false;
		player.remove();
		player = null;
		if (hovered != null)
			hovered.setState(0);
		endLabel.setVisible (true);
	}

	// return the object player
	public Penguin getPlayer() {
		return player;
	}

	// draw EVERYTHING
	public void draw(Graphics g) {

		super.draw(g);
		for (int i = 0; i < blocks.length; i++)
			for (int j = 0; j < blocks[0].length; j++)
				blocks[i][j].show(g);

		scoreLabel.setText("Fish eaten: " + score);
		scoreLabel.applyGraphics(g);
		scoreLabel.setLocation(block_width / 2 + scoreLabel.getWidth() / 2,
				block_height * 7);
		
		endLabel.applyGraphics(g);
		endLabel.setLocation(width / 2, height / 2);
		System.out.println(score);
	}

	public void hide() {
		super.hide();
		scoreLabel.setVisible(false);
		endLabel.setVisible (false);
	}

	public void show() {
		super.show();
		scoreLabel.setVisible(true);
	}

	/*--------ALL THE MOUSE MOVEMENTS------------*/
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		if (alive) {
			int x = e.getX(), y = e.getY();
			for (int i = 0; i < tile.length; i++) {
				for (int j = 0; j < tile[0].length; j++) {
					if (tile[i][j].contains(x, y)) {
						tile[i][j].setState(2);
						player.target = blocks[i][j];
						targeted = tile[i][j];
					} else
						tile[i][j].setState(0);
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		if (alive) {
			int x = e.getX(), y = e.getY();
			for (int i = 0; i < tile.length; i++) {
				for (int j = 0; j < tile[0].length; j++) {
					if (tile[i][j].getState() != 2 && tile[i][j].contains(x, y)) {
						tile[i][j].setState(1);
						hovered = tile[i][j];
					} else if (tile[i][j].getState() != 2)
						tile[i][j].setState(0);
				}
			}
		}
	}

}