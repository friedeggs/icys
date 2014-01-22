package icys.java;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static icys.java.Utilities.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements MouseListener, MouseMotionListener {
	//Mode selection;
	BufferedImage fish;
	Font font = new Font ("Calibri", Font.BOLD, 32);
	FontMetrics metrics;
	Background background;
	boolean running = false;
	int i = 0;
	
	public Main () {
		super ("ICY-S"); 
	}
	
	public void create () {
		try {
			fish = ImageIO.read (new File ("fish.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		background = new Background ();
		background.addMouseListener (this);
		background.addMouseMotionListener (this);
		//add (selectLabel [0]);

		
		StartScreen = new StartScreen ();
		SelectionScreen = new SelectionScreen ();
		AboutScreen = new AboutScreen ();
		HelpScreen = new HelpScreen ();
		//mode = new Mode ();
		currentScreen = StartScreen;

		//currentScreen.add();
		add (background);
		pack(); // graphics after pack
		
		metrics = getGraphics ().getFontMetrics(font);
		//StartScreen.applyFont (font, metrics);
		StartScreen.applyGraphics (getGraphics ());
		SelectionScreen.applyGraphics (getGraphics ());
		AboutScreen.applyGraphics (getGraphics ());
		HelpScreen.applyGraphics (getGraphics ());
		//mode.applyGraphics (getGraphics ());
		
		setBackground (lightblue);
		setLocationRelativeTo (null);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
		
		animate ();
	}
	
	public class Background extends JPanel {
		public Background () {
			super ();
			setPreferredSize (screenSize);
		}
		
		public void paintComponent (Graphics g) {
//			int density = 180, j;
//			for (int i = 5 ; i < width ; i += density) {
//				if (i / density % 2 == 0)
//					j = 0;
//				else
//					j = density / 2;
//				for ( ; j < height ; j += density) {
//					g.drawImage(fish, i, j, null);
//				}
//			}
			g.setColor (lightblue);
			g.fillRect(0, 0, width, height);
			currentScreen.draw (g);
			
//				g.drawLine(0, border+offset, getWidth(), border+offset);
//				g.drawLine (0, getHeight() - border, getWidth(), getHeight() - border);
//				g.drawLine(border, 0, border, getHeight());
//				g.drawLine(getWidth() - border, 0, getWidth () - border, getHeight());
//				
//				g.drawLine(0, (getHeight()-offset+border)/4+offset, 
//						getWidth(), (getHeight()-offset+border)/4+offset);
//				g.drawLine(0, (3*getHeight()-3*offset-border)/4+offset, 
//						getWidth(), (3*getHeight()-3*offset-border)/4+offset);
//				g.drawLine((getWidth()+border)/4, 0, (getWidth()+border)/4, getHeight());
//				g.drawLine((3*getWidth()-border)/4, 0, 
//						(3*getWidth()-border)/4, getHeight());
				
			
			
//				g.setColor (aqua);
//				g.fillRect (border, border+offset, // (A-3B)/2
//				(getWidth()-3*border)/2, (getHeight()-offset-3*border)/2);
//				g.fillRect ((getWidth()+border)/2, border+offset,
//						(getWidth()-3*border)/2, (getHeight()-offset-3*border)/2);
//				g.fillRect (border, (getHeight()+offset+border)/2,
//						(getWidth()-3*border)/2, (getHeight()-offset-3*border)/2);
//				g.fillRect ((getWidth()+border)/2, (getHeight()+offset+border)/2,
//						(getWidth()-3*border)/2, (getHeight()-offset-3*border)/2);
		}
	}
	
	public void animate () {
		while (true) {
			if (running) {
				i++;
				if (i == 40)
					i = 0;
				repaint ();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main (String args []) {
		main = new Main ();
		main.create ();
	}
	
	public void setScreen (Screen screen) {
		currentScreen.hide ();
		currentScreen = screen;
		currentScreen.show ();
		if (currentScreen == mode) {
			i = 0;
			running = true;
		}
		else {
			i = 0;
			running = false;
		}
		repaint ();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		currentScreen.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		currentScreen.mouseReleased (e);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		currentScreen.mouseMoved(e);
	}
}