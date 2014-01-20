package icys.java;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface Screen {
	
	public void draw (Graphics g);
	
	public void show ();
	
	public void hide ();
	
	public void applyGraphics (Graphics g);
	
	public void mouseClicked(MouseEvent e);

	public void mouseEntered(MouseEvent e);

	public void mouseExited(MouseEvent e);

	public void mousePressed(MouseEvent e);

	public void mouseReleased(MouseEvent e);

	public void mouseDragged(MouseEvent e);

	public void mouseMoved(MouseEvent e);
}