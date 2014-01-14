package icys.java;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

import static icys.java.Utilities.*;


public class StartScreen implements Screen  {
	LabelButton title;
	LabelButton play, instructions;
	
	public StartScreen () {
		title = new LabelButton ("ICY-S", font_large, Color.WHITE, lightblue);
		
		play = new LabelButton ("play", font, Color.WHITE, lightblue, blue, aqua);
		
		instructions = new LabelButton ("about", font,
				Color.WHITE, lightblue, blue, aqua);
		
		main.add (play);
		main.add (instructions);
		main.add (title);
		
		show ();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub	
		title.setVisible (true);
		play.setVisible (true);
		instructions.setVisible (true);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub	
		title.setVisible (false);
		play.setVisible (false);
		instructions.setVisible (false);
	}

	@Override
	public void applyGraphics (Graphics g) {
		// TODO Auto-generated method stub
		title.applyGraphics (g);
		play.applyGraphics (g);
		instructions.applyGraphics (g);		
		
		title.setLocation (width / 2, height / 2 - play.getHeight() / 2);
		play.setLocation(width / 4, height / 2 + title.getHeight() / 2
				- play.getHeight() / 2);
		instructions.setLocation(width * 3 / 4, height / 2 + title.getHeight() / 2
				- play.getHeight() / 2);
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
		if (play.contains (x, y)) {
			play.setState (1);
			main.repaint ();
		}
		else if (instructions.contains (x, y)) {
			instructions.setState (1);
			main.repaint ();
		}
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {			
		if (play.getState () == 1) {
			play.setState(0);
			main.setScreen (SelectionScreen);
		}
		else if (instructions.getState () == 1)
		{
			instructions.setState(0);
			main.setScreen (AboutScreen);
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
		if (play.contains (x, y))
			play.setState (1);
		else 
			play.setState (0);
		if (instructions.contains (x, y))
			instructions.setState (1);
		else
			instructions.setState (0);
		main.repaint();
	}
}