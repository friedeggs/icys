package icys.java;

//imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import static icys.java.Utilities.*;

public class SelectionScreen implements Screen {
	
	// Initialization
	LabelButton selectLabel [], back, help, title;
	BoxButton box [];
	
	// Constructor
	public SelectionScreen () {
		
		//set title, back, help
		title = new LabelButton ("SELECT A MODE", new Font ("Calibri Light", Font.PLAIN, 48), 
				Color.WHITE, lightblue);
		back = new LabelButton ("back", font, Color.WHITE, lightblue, blue, aqua);
		help = new LabelButton ("help", font, Color.WHITE, lightblue, blue, aqua);
		
		//put different labels in the selectLabel array
		selectLabel = new LabelButton [4];
		selectLabel [0] = new LabelButton ("game", font,
				Color.WHITE, lightblue, blue, aqua);
		selectLabel [1] = new LabelButton ("simulation",  font,
				Color.WHITE, lightblue, blue, aqua);
		selectLabel [2] = new LabelButton ("extinction",  font,
				Color.WHITE, lightblue, blue, aqua);
		selectLabel [3] = new LabelButton ("equilibrium",  font,
				Color.WHITE, lightblue, blue, aqua);
		
		//create box for putting things in
		box = new BoxButton [4];
		for (int i = 0 ; i < 4 ; i++) {
			box [i] = new BoxButton ((width - 3 * border) / 2,
					(height - 3 * border - offset) / 2, aqua, Color.WHITE);
		}
		
		//add everything
		main.add (back);
		main.add (help);
		main.add (title);
		for (int i = 0 ; i < 4 ; i++){
			main.add(selectLabel [i]);
		}
		
		hide ();
	}

	//Draws GUI
	@Override
	public void draw(Graphics g) {
		g.setColor (aqua);
		g.fillRect(0, 100, main.i*15, 300);
		for (int i = 0 ; i < 4 ; i++)
			box [i].draw(g);
	}

	//displays the class
	@Override
	public void show() {
		title.setVisible(true);
		back.setVisible (true);
		help.setVisible (true);
		for (int i = 0 ; i < 4; i ++)
			selectLabel [i].setVisible(true);
	}

	//hide when unnecessary
	@Override
	public void hide() {
		title.setVisible(false);
		back.setVisible (false);
		help.setVisible (false);
		for (int i = 0 ; i < 4; i ++)
			selectLabel [i].setVisible (false);
	}

	//apply border and etc
	@Override
	public void applyGraphics (Graphics g) {
		// TODO Auto-generated method stub
		title.applyGraphics(g);
		back.applyGraphics(g);
		help.applyGraphics(g);

		title.setLocation (width / 2, (offset + border) / 2);
		back.setLocation(border + back.getWidth() / 2, 
				border + back.getHeight() / 2);
		help.setLocation(width - border - help.getWidth() / 2, 
				border + help.getHeight() / 2);
		
		for (int i = 0 ; i < 4 ; i++)
			selectLabel [i].applyGraphics(g);
		
		selectLabel [0].setLocation (
				(width + border) / 4, 
				(height - offset + border) / 4 + offset);
		selectLabel [1].setLocation ( // (3A - 9B)/4+8B/4
				(3 * width - border) / 4, 
				(height - offset + border) / 4 + offset);
		selectLabel [2].setLocation (
				(width + border) / 4,
				(3 * height - 3 * offset - border) / 4 + offset);
		selectLabel [3].setLocation ( // (3A-3C-B)/4
				(3 * width - border) / 4,
				(3 * height - 3 * offset - border) / 4 + offset);
		
		box [0].setLocation (
				(width + border) / 4, 
				(height - offset + border) / 4 + offset);
		box [1].setLocation ( // (3A - 9B)/4+8B/4
				(3 * width - border) / 4, 
				(height - offset + border) / 4 + offset);
		box [2].setLocation (
				(width + border) / 4,
				(3 * height - 3 * offset - border) / 4 + offset);
		box [3].setLocation ( // (3A-3C-B)/4
				(3 * width - border) / 4,
				(3 * height - 3 * offset - border) / 4 + offset);
	}

	
	/*--------ALL THE MOUSE MOVEMENTS------------*/
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
		else {
			for (int i = 0 ; i < 4; i ++) {
				if (box [i].contains (x, y)) {
					box [i].setState (1);
					selectLabel [i].setState (1);
					main.repaint ();
					i = 4;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (back.getState () == 1) {
			back.setState(0);
			main.setScreen(StartScreen);
		}
		else if (help.getState() == 1) {
			help.setState (0);
			main.setScreen (HelpScreen);
		}
		
		if (box[0].getState() == 1) {
			box[0].setState(0);
			selectLabel[0].setState(0);
			mode = new Game();
			System.out.println("Game chosen");
			main.setScreen(mode);
		}
		else if (box[1].getState() == 1) {
			box[1].setState(0);
			selectLabel[1].setState(0);
			mode = new Simulation ();
			main.setScreen(mode);
			System.out.println("SIM chosen");
		}
		else if (box[2].getState() == 1) {
			box[2].setState(0);
			selectLabel[2].setState(0);
			mode = new Extinction ();
			System.out.println("Extinction chosen");
			main.setScreen(mode);
		}
		else if (box[3].getState() == 1) {
			box[3].setState(0);
			selectLabel[3].setState(0);
			mode = new Equilibrium ();
			System.out.println("Equilibrium chosen");
			main.setScreen(mode);
		}	
		/*for (int i = 0 ; i < 4 ; i++) {
			if (box [i].getState () == 1) {
				box [i].setState (0);
				selectLabel [i].setState (0);
				//mode = new Mode ();
				main.setScreen(mode);
			}
		}*/
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
		for (int i = 0 ; i < 4; i ++) {
			if (box [i].contains (x, y)) {
				box [i].setState (1);
				selectLabel [i].setState (1);
			}
			else {
				box [i].setState (0);
				selectLabel [i].setState (0);
			}
		}
		main.repaint ();
	}
}