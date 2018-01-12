import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;
import javax.swing.*;



public class TetrisGUI extends JPanel implements ActionListener, KeyListener {
	
	public static final int WIDTH = 640, //window's width a
			HEIGHT = 840, //window's height
			MILLI_BETWEEN_CALCS = 300; // Milliseconds between calculations
	private int score = 0;
	private HashMap<Point,Grid> grids = new HashMap<>();
    private boolean gameOver = false, keyPressed = false, done = false; //if key is pressed within calc period
	
	//new tetris
	private Tetris tetris = new Tetris();
	
	public TetrisGUI() {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Tetris");
		// Sets the window bounds
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.add(this);
		//display
		//frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		frame.addKeyListener(this);
		
		//create grid for game
		for(int i=0; i< WIDTH; i+=20) {
			for(int j=0; j<HEIGHT; j+=20) {
				Grid tempGrid = new Grid(i,j);
				tempGrid.setOccupied(false);
				grids.put(new Point(i, j),tempGrid);
			}
		}
        
          
        // Create a new Timer object to be used to schedule repeated updates to the game. 
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask()
        {
            // Override the (abstract) method run() in this anonymous implementation of the TimerTask class.
            @Override
            public void run()
            {
               //if grid below is occupied/inexistent then repaint and make new tetris
            	//TESTS
            	//System.out.println("Low Y:" +tetris.getLowestPoint().y);
            	//System.out.println("Height:" +HEIGHT);
       
            	if(grids.get(tetris.getLowestPoint())==null 
            			||grids.get(tetris.getLowestPoint()).isOccupied()
            			||tetris.getLowestPoint().y >= HEIGHT) {
   
            		done = true;
            	}
            	else {
            		tetris.move("Down");
            	}
                // Resets the keyPressed variable to false
                keyPressed = false;
                repaint();
            }
        }, 750, MILLI_BETWEEN_CALCS);

        frame.addKeyListener(this);
        setBackground(Color.BLACK);
        //frame.pack();
        frame.setVisible(true); 
	}
	
	// This overrides the default paint method for JPanel. As I said earlier in
    // this class' comments, you really shouldn't call this directly.
    @Override
    public void paint(Graphics g){
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D)g;
    	
    	if (gameOver){ //shows text game over when game is over
            g2d.setFont(new Font("Ariel", Font.BOLD, 96));
            g2d.setColor(Color.RED);
            g2d.drawString("Game Over", 50, 200);
        }
    	if(done) {//if tetris can not go lower
    		Grid.paintGrid(tetris.getPoints(), tetris.getColor(),g2d);
    		tetris.resetValues();
    		tetris = new Tetris();
    		done = false;
    	}
    	
    	//draw (new) tetris
    	tetris.render(g2d);

    	// show the score
        g2d.setFont(new Font("Ariel", Font.BOLD, 26));
        g2d.setColor(Color.RED);
        g2d.drawString("Score: " + score, 10, 400);
    }
	

	/**
	 * Schedules a job for the event-dispatching thread creating and showing
	 * this application's GUI.
	 */
	public static void main(String[] args) {
		//final TetrisGUI GUI = 
		new TetrisGUI();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// If the a key has already been pressed this calculation cycle, this
        // exits the method without doing anything.
        if (keyPressed)
            return;
      
        int keyCode = e.getKeyCode();

        // These if statements allow for both WASD and arrow key navigation.
        // Both types are common in games. The character's current movement
        // direction is then set appropriately.
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D)
            tetris.move("Right");
        else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A)
        	tetris.move("Left");

        // Marks keyPressed as true so this method won't do anything for the
        // rest of the calculation cycle.
        keyPressed = true;	
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//TO DO
	//Set up Hashset with all grids in the game and
	//whether or not they are occupied
	//define game over
}

