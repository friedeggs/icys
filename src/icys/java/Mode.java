package icys.java;

import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//arbitary comment?
public class Mode implements Screen {
	
	LabelButton back, help, addEgg, addPenguin, addBear;
	static ArrayList <Fish> fish = new ArrayList <Fish> (1);
	ArrayList <Egg> eggs = new ArrayList <Egg> (1);
	ArrayList <Penguin> penguins = new ArrayList <Penguin> (1);
	ArrayList <PolarBear> bears = new ArrayList <PolarBear> (1);
	
	public Mode () {
		
		back = new LabelButton ("back", font, Color.WHITE, lightblue, blue, aqua);
		help = new LabelButton ("help", font, Color.WHITE, lightblue, blue, aqua);

		// make icons later
		addEgg = new LabelButton ("egg", font, Color.WHITE, lightblue, blue, aqua);
		addPenguin = new LabelButton ("pen", font, Color.WHITE, lightblue, blue, aqua);
		addBear = new LabelButton ("bear", font, Color.WHITE, lightblue, blue, aqua);
		
		blocks = new Block [30][30];
		for (int i = 0 ; i < blocks.length ; i++) {
			for (int j = 0 ; j < blocks.length ; j++) {
				blocks [i][j] = new Block (1);
				blocks [i][j].set (i * 16, j * 16);
				blocks [i][j].setIndices (i, j);
			}
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			fish.add(new Fish ());
		}
		
		for (int i = 0 ; i < 10 ; i++) {
			eggs.add(new Egg (8, 12));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			penguins.add(new Penguin (10, 10));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			bears.add(new PolarBear (5, 15, penguins));
		}
		
		main.add (addEgg);
		main.add (addPenguin);
		main.add (addBear);
		
		main.add (back);
		main.add (help);
		
		addEgg.setVisible(false);		
		addPenguin.setVisible(false);		
		addBear.setVisible(false);		
		back.setVisible(false);		
		help.setVisible(false);
		//main.add (main.background);
		//applyGraphics (main.getGraphics ());
	}
	
	public void animate1 () {
		int s = 0;
		while (s < 30) {
		main.repaint ();
		main.invalidate();
		back.setForeground (back.getForeground());
		try {
			Thread.sleep (500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// fish
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).update (penguins, fish, eggs, 
					blocks, main.getGraphics ());
		}
		for (int i = 0 ; i < bears.size () ; i++) {
			bears.get(i).update (bears, penguins, blocks, main.getGraphics ());
		}
		for (int i = 0 ; i < eggs.size () ; i++) {
			eggs.get(i).update (penguins, blocks, main.getGraphics ());
		}
		s++;
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor (blue);
		g.fillRect (0, offset + border, width, border);
		g.setColor(aqua);
		g.fillRect(0, offset+2*border, width, height-offset-2*border);
		
//		for (int i = 0 ; i < fish.size () ; i++) {
//			fish.get(i).show (10, blocks, g);
//		}
//		for (int i = 0 ; i < penguins.size () ; i++) {
//			penguins.get(i).show (blocks, g);
//		}
//		for (int i = 0 ; i < bears.size () ; i++) {
//			bears.get(i).show (blocks, g);
//		}
//		for (int i = 0 ; i < eggs.size () ; i++) {
//			eggs.get(i).show (blocks, g);
//		}
		for (int i = 0 ; i < fish.size () ; i++) {
			fish.get(i).show (g);
		}
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).update (penguins, fish, eggs, 
					blocks, g);
		}
		for (int i = 0 ; i < bears.size () ; i++) {
			bears.get(i).update (bears, penguins, blocks, g);
		}
		for (int i = 0 ; i < eggs.size () ; i++) {
			eggs.get(i).update (penguins, blocks, g);
		}
	}

	@Override
	public void show() {
		back.setVisible (true);
		help.setVisible (true);
		addEgg.setVisible (true);
		addPenguin.setVisible (true);
		addBear.setVisible (true);
	}

	@Override
	public void hide() {
		back.setVisible (false);
		help.setVisible (false);
		addEgg.setVisible (false);
		addPenguin.setVisible (false);
		addBear.setVisible (false);
	}

	@Override
	public void applyGraphics(Graphics g) {
		back.applyGraphics(g);
		help.applyGraphics(g);
		
		addEgg.applyGraphics(g);
		addPenguin.applyGraphics(g);
		addBear.applyGraphics(g);
		
		int numOfButtons = 4;
		addEgg.setLocation(width * 1 / numOfButtons, border + back.getHeight() / 2);
		addPenguin.setLocation(width * 2 / numOfButtons, border + back.getHeight() / 2);
		addBear.setLocation(width * 3 / numOfButtons, border + back.getHeight() / 2);
		
		back.setLocation(border + back.getWidth() / 2, 
				border + back.getHeight() / 2);
		help.setLocation(width - border - help.getWidth() / 2, 
				border + help.getHeight() / 2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX ();
		int y = e.getY ();
		if (back.contains (x, y)) {
			back.setState (1);
			main.repaint ();
		}
		else if (help.contains (x, y)) {
			help.setState (1);
			main.repaint ();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (back.getState () == 1) {
			back.setState(0);
			main.setScreen(SelectionScreen);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX ();
		int y = e.getY ();
		if (back.contains (x, y))
			back.setState (1);
		else 
			back.setState (0);
		if (help.contains (x, y))
			help.setState (1);
		else
			help.setState (0);
	}
	
}