package icys.java;

import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Mode implements Screen {
	
	LabelButton back, help, addEgg, addPenguin, addBear;
	Block blocks [][] = new Block [30][30];
	ArrayList <Fish> fish = new ArrayList <Fish> (1);
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
		
//		back = new LabelButton ("back", font, Color.WHITE, blue, blue, aqua);
//		help = new LabelButton ("help", font, Color.WHITE, blue, blue, aqua);
//
//		// make icons later
//		addEgg = new LabelButton ("egg", font, Color.WHITE, blue, blue, aqua);
//		addPenguin = new LabelButton ("pen", font, Color.WHITE, blue, blue, aqua);
//		addBear = new LabelButton ("bear", font, Color.WHITE, blue, blue, aqua);
		
		
		
		main.add (addEgg);
		main.add (addPenguin);
		main.add (addBear);
		
		main.add (back);
		main.add (help);
		main.add (background);
		applyGraphics (main.getGraphics ());
		
	}
	
	public void animate () {
		main.repaint ();
		Thread.sleep (500);
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).update (penguins, fish, eggs, blocks);
		}
		for (int i = 0 ; i < bears.size () ; i++) {
			bears.get(i).update (bears, penguins, blocks);
		}
		for (int i = 0 ; i < eggs.size () ; i++) {
			eggs.get(i).update ();
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).show (blocks, g);
		}
		for (int i = 0 ; i < bears.size () ; i++) {
			bears.get(i).show (blocks, g);
		}
		for (int i = 0 ; i < eggs.size () ; i++) {
			eggs.get(i).show (blocks, g);
		}
		g.setColor (blue);
		g.fillRect (0, offset + border, width, border);
		g.setColor(aqua);
		g.fillRect(0, offset+2*border, width, height-offset-2*border);
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