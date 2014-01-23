package icys.java;

//import
import static icys.java.Utilities.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Mode implements Screen {

	//initialization
	Random random = new Random ();
	LabelButton back, help, earthquake, melt, pollution; 
	LabelButton addEgg, addPenguin, addBear;
	MeltingGlacier glacier;
	WaterPollution pollutedWater;
	Earthquake quake;
	
	//Constructor
	public Mode () {
		
		/*-----------LABEL BUTTONS------------*/
		//Always there
		back = new LabelButton ("back", font, Color.WHITE, lightblue, blue, aqua);
		help = new LabelButton ("help", font, Color.WHITE, lightblue, blue, aqua);
		//Disaster
		earthquake = new LabelButton ("earthquake", font_small, Color.WHITE, lightblue, blue, aqua);
		melt = new LabelButton ("melt glacier", font_small, Color.WHITE, lightblue, blue, aqua);
		pollution = new LabelButton ("pollution", font_small, Color.WHITE, lightblue, blue, aqua);
		//Adding entities
		addEgg = new LabelButton ("egg", font_small, Color.WHITE, lightblue, blue, aqua);
		addPenguin = new LabelButton ("penguin", font_small, Color.WHITE, lightblue, blue, aqua);
		addBear = new LabelButton ("bear", font_small, Color.WHITE, lightblue, blue, aqua);

		//ArrayList of objects
		fish = new ArrayList <Fish> ();
		eggs = new ArrayList <Egg> ();
		penguins = new ArrayList <Penguin> ();
		bears = new ArrayList <PolarBear> ();
		
		block_width = width / (blocks.length - 3);
		block_height = (height-offset-border) / blocks[0].length;
		shift = block_width * 3 / 6;
		
		//Read start file to initialize block values
		read ();
		for (int i = 0 ; i < blocks.length ; i++)
			for (int j = 0 ; j < blocks[0].length ; j++) 
			{
				if (value[i][j]=='0')
					blocks [i][j] = new Block (WATER, i, j);
				else if (value[i][j] == '1')
					blocks [i][j] = new Block (LAND, i, j);
				else //if (value[i][j]==2)
					blocks [i][j] = new Block (UNUSED, i, j);
			} 
	
		//ADD EVERYTHING ON THE PANEL TO DISPLAY EVERYTHING.
		main.add (addEgg);
		main.add (addPenguin);
		main.add (addBear);
		
		main.add (back);
		main.add (earthquake);
		main.add (melt);
		main.add (pollution);
		main.add (help);
		main.add(main.background);
		applyGraphics (main.getGraphics());
		
		//Set visible = false
		addEgg.setVisible(false);		
		addPenguin.setVisible(false);		
		addBear.setVisible(false);		
		back.setVisible(false);		
		earthquake.setVisible(false);
		melt.setVisible(false);
		pollution.setVisible(false);
		help.setVisible(false);
		
	}
	
	//read .txt file method
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
		for (int i = 0; i < value[0].length; i++)
			for (int j = 0; j < value.length; j++) {
				value[j][i] = splited[indexS].charAt(0);
				indexS++;
			}
	}

	//the main draw method
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		// Toolbars
		g.setColor (blue);
		g.fillRect (0, offset + border, width, border);
		if (polluted)
			g.setColor (dark);
		else
			g.setColor(water);
		g.fillRect(0, offset+2*border, width, height-offset-2*border);
		
		
		if (TIMER % (sleep / 2) == 0 && isQuake != 0)
			quake.update ();
		
		if (TIMER % sleep == 0) {
		// DON'T ADD MORE FISH THAN CAN BE ADDED
			
		if (melted) {
			glacier.update();
		}
		
		if (stopFish == 0) {
			int rand = (int)(Math.abs(random.nextGaussian()*2/3));
			for (int i = 0 ; i < rand ; i++) {
				fish.add(new Fish (fish.size()));
			}		
		}
		else {
			stopFish--;
			if (stopFish == 0)
				pollutedWater.revert ();
		}
		for (int i = 0 ; i < bears.size () ; i++) {
			bears.get(i).update (g);
		}
		for (int i = 0 ; i < eggs.size () ; i++) {
			eggs.get(i).update (g);
		}
		for (int i = 0 ; i < penguins.size () ; i++) {
			penguins.get(i).update (g);
		}
		} // timer stuff

		for (int i = 0 ; i < blocks.length ; i++) 
			for (int j = 0 ; j < blocks[0].length ; j++)
				blocks[i][j].update(g);
	}

	//the main show
	@Override
	public void show() {
		back.setVisible (true);
		earthquake.setVisible (true);
		melt.setVisible (true);
		pollution.setVisible (true);
		help.setVisible (true);
		addEgg.setVisible (true);
		addPenguin.setVisible (true);
		addBear.setVisible (true);
	}

	//the main hide-your-everything
	@Override
	public void hide() {
		back.setVisible (false);
		earthquake.setVisible(false);
		melt.setVisible(false);
		pollution.setVisible(false);
		help.setVisible (false);
		addEgg.setVisible (false);
		addPenguin.setVisible (false);
		addBear.setVisible (false);
	}

	//all the borders, etc all here
	@Override
	public void applyGraphics(Graphics g) {
		back.applyGraphics(g);
		earthquake.applyGraphics(g);
		melt.applyGraphics(g);
		pollution.applyGraphics(g);
		help.applyGraphics(g);
		
		addEgg.applyGraphics(g);
		addPenguin.applyGraphics(g);
		addBear.applyGraphics(g);
		
		int numOfButtons = 7;
		addEgg.setLocation(width * 1 / numOfButtons, border + back.getHeight() / 2);
		addPenguin.setLocation(width * 2 / numOfButtons -35, border + back.getHeight() / 2);
		addBear.setLocation(width * 3 / numOfButtons -60, border + back.getHeight() / 2);
		
		back.setLocation(border + back.getWidth() / 2, 
				border + back.getHeight() / 2);
		
		earthquake.setLocation(width * 4 / numOfButtons -55, border + back.getHeight() / 2);
		melt.setLocation(width * 5 / numOfButtons -35, border + back.getHeight() / 2);
		pollution.setLocation(width * 6 / numOfButtons -25, border + back.getHeight() / 2);
		
		help.setLocation(width - border - help.getWidth() / 2, 
				border + help.getHeight() / 2);
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
		else if (earthquake.contains(x,y)) {
			earthquake.setState(1);
			quake = new Earthquake();
		}
		else if (melt.contains(x, y)) {
			melt.setState(1);
			glacier = new MeltingGlacier();
		}
		else if (pollution.contains(x, y)) {
			pollution.setState(1);
			pollutedWater = new WaterPollution();
		}
		else if (addEgg.contains(x, y)){
			addEgg.setState(1);
			Egg babyPen = new Egg (eggs.size()); 
			Block home = babyPen.randomBlock();
			if (home != null) {
			babyPen = new Egg (eggs.size(), home.x, home.y);
			eggs.add(babyPen);
			}
		}
		else if (addPenguin.contains(x, y)){
			addPenguin.setState(1);
			Penguin newPen = new Penguin (penguins.size());
			Block place = newPen.randomBlock();
			if (place != null) {
			newPen = new Penguin (penguins.size(), place.x, place.y);
		    penguins.add(newPen);
			}
		}
		else if (addBear.contains(x,y)){
			addBear.setState(1);
			PolarBear bwear = new PolarBear (bears.size());
			Block territory = bwear.randomBlock();
			if (territory != null) {
			bwear = new PolarBear (bears.size(), territory.x, territory.y);
			bears.add(bwear);
			}
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (back.getState () == 1) {
			back.setState(0);
			main.setScreen(SelectionScreen);
		}
		if (help.getState() == 1){
			help.setState(0);
			main.setScreen(HelpScreen);
		}
		if (addEgg.getState() == 1) {
			addEgg.setState(0);
		}
		if (addPenguin.getState() == 1){
			addPenguin.setState(0);
		}
		if (addBear.getState() == 1){
			addBear.setState(0);
		}
		if (earthquake.getState() == 1){
			earthquake.setState(0);
		}
		if (melt.getState() == 1){
			melt.setState(0);
		}
		if (pollution.getState() == 1){
			pollution.setState(0);
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
		if (earthquake.contains(x, y))
			earthquake.setState(1);
		else
			earthquake.setState(0);
		if (melt.contains(x, y))
			melt.setState(1);
		else
			melt.setState(0);
		if (pollution.contains(x, y))
			pollution.setState(1);
		else
			pollution.setState(0);
		if (addEgg.contains (x,y))
			addEgg.setState(1);
		else 
			addEgg.setState(0);
		if (addPenguin.contains (x,y))
			addPenguin.setState(1);
		else 
			addPenguin.setState(0);
		if (addBear.contains (x,y))
			addBear.setState(1);
		else 
			addBear.setState(0);
		
	}

	
}

