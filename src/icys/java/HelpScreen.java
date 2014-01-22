package icys.java;

import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


public class HelpScreen implements Screen {
	
	LabelButton back, title;
	JTextArea area;
	
	public HelpScreen () {
		
		title = new LabelButton ("Help", new Font ("Calibri Light", Font.PLAIN, 48), 
				Color.WHITE, lightblue);
		back = new LabelButton ("back", font, Color.WHITE, lightblue, blue, aqua);
		area = new JTextArea ("GAME: simulation based game" +
				"\nSIMULATION: primary feature with interaction" +
				"\nEXTINCTION: interactive simulation in which the life forms " +
				"will eventually die out. " +
				"fish will continue to appear on the sides" +
				"\nEQUILIBRIUM: a simulation system that repeats itself");
		area.setFont(font);
		area.setEditable(false);
		area.setOpaque(true);
		area.setBackground(lightblue);
		area.setForeground(Color.WHITE);
		
		main.add(title);
		main.add(area);
		main.add (back);
		
		hide ();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		back.setVisible (true);
		area.setVisible (true);
		title.setVisible (true);
	}

	@Override
	public void hide() {
		back.setVisible (false);
		area.setVisible (false);
		title.setVisible (false);
	}

	@Override
	public void applyGraphics(Graphics g) {
		back.applyGraphics(g);
		title.applyGraphics(g);
		
		back.setLocation(border + back.getWidth() / 2, 
				height - border - back.getHeight() / 2);
		
		int h = 250;
		area.setBorder (new EmptyBorder (0, 6, 0, 6));
		area.setLocation(back.getX(), height / 2 - h / 2);
		area.setSize(width - back.getX(), h);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		

		title.setLocation (width / 2, height / 2 - h / 2 - title.getHeight () / 2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
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
	}
	
}