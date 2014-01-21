package icys.java;

import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//arbitary comment?
public class Mode implements Screen {

	Random random = new Random ();
	LabelButton back, help, addEgg, addPenguin, addBear;
	
	public Mode () {
		
		back = new LabelButton ("back", font, Color.WHITE, lightblue, blue, aqua);
		help = new LabelButton ("help", font, Color.WHITE, lightblue, blue, aqua);

		// make icons later
		addEgg = new LabelButton ("egg", font, Color.WHITE, lightblue, blue, aqua);
		addPenguin = new LabelButton ("pen", font, Color.WHITE, lightblue, blue, aqua);
		addBear = new LabelButton ("bear", font, Color.WHITE, lightblue, blue, aqua);

		fish = new ArrayList <Fish> ();
		eggs = new ArrayList <Egg> ();
		penguins = new ArrayList <Penguin> ();
		bears = new ArrayList <PolarBear> ();
		
		read ();
		blocks = new Block [15][21];
		for (int i = 0 ; i < blocks.length ; i++)
			for (int j = 0 ; j < blocks[0].length ; j++) {
				if (value[i][j]=='0')
					blocks [i][j] = new Block (WATER, i, j);
				else if (value[i][j] == '1')
					blocks [i][j] = new Block (LAND, i, j);
				else //if (value[i][j]==2)
					blocks [i][j] = new Block (UNUSED, i, j);
			}
		
		for (int i = 0 ; i < 1 ; i++) {
			fish.add(new Fish (i));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			eggs.add(new Egg (i, 8, 12));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			penguins.add(new Penguin (i, 10, 10));
		}
		
		for (int i = 0 ; i < 1 ; i++) {
			bears.add(new PolarBear (i, 5, 12));
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
	}
	
	public void read ()
	{
		String[] splited = new String [315];
		String result = "a";

		BufferedReader br = null;
		String sCurrentLine = null;
		try {
			br = new BufferedReader(new FileReader("start.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				result = sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		splited = result.split("	");
		int indexS = 0;
		for (int i = 0; i < value.length; i++)
			for (int j = 0; j < value[i].length; j++) {
				value[i][j] = splited[indexS].charAt(0);
				indexS++;
			}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		// Toolbars
		g.setColor (blue);
		g.fillRect (0, offset + border, width, border);
		g.setColor(aqua);
		g.fillRect(0, offset+2*border, width, height-offset-2*border);
		g.setColor(blue);
		g.fillRect (0, 180, main.i*15, 90);;
		for (int i = 0 ; i < blocks.length ; i++) 
			for (int j = 0 ; j < blocks[0].length ; j++)
				blocks[i][j].show(g);
		
		int rand = (int)(Math.abs(random.nextGaussian()*2/3));
		for (int i = 0 ; i < rand ; i++) {
			fish.add(new Fish (fish.size()));
		}		
//		rand = (int)(Math.abs(random.nextGaussian()/2));
//		for (int i = 0 ; i < rand ; i++) {
//			eggs.add(new Egg (eggs.size(),
//					(int)(Math.random()*blocks.length),
//					(int)(Math.random()*blocks[0].length)));
//		}		
//		rand = (int)(Math.abs(random.nextGaussian()/2));
//		for (int i = 0 ; i < rand ; i++) {
//			penguins.add(new Penguin (penguins.size(),
//					(int)(Math.random()*blocks.length),
//					(int)(Math.random()*blocks[0].length)));
//		}	
//		rand = (int)(Math.abs(random.nextGaussian()/2));
//		for (int i = 0 ; i < rand ; i++) {
//			bears.add(new PolarBear (bears.size(),
//					(int)(Math.random()*blocks.length),
//					(int)(Math.random()*blocks[0].length)));
//		}
		/**
		 * HERE GWACIE
		 */
		for (int i = 0 ; i < eggs.size () ; i++) {
			eggs.get(i).update (g);
		}
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).update (g);
		}
		for (int i = 0 ; i < bears.size () ; i++) {
			bears.get(i).update (g);
		}
		for (int i = 0 ; i < fish.size () ; i++) {
			fish.get(i).show (g);
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
