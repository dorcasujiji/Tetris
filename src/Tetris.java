import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Tetris {
	private static ArrayList<String> tetrisColors = new ArrayList<String>();
	private static ArrayList<String> shape = new ArrayList<>();
	private static ArrayList<Tetrispiece> tetrispieces = new ArrayList<>();
	private static ArrayList<Point> tetrisPoints = new ArrayList<>();
	private static boolean firstTime = true;
	
	private static int TET_X = 320, TET_Y = 20;
	private final static int GRID_SIZE = 20;
	private static Point lowestPoint ;
	private static Color COLOR = new Color(0, 0, 255);
	private static Random random = new Random();
	
	public Tetris() {
		if(firstTime) {
			//colors: red, blue, yellow and green
			tetrisColors.add("#800000"); tetrisColors.add("#0000FF");
			tetrisColors.add("#FFFF00"); tetrisColors.add("#008000"); 
	
			//shapes of tetris
			shape.add("Hor4"); shape.add("Vert4"); shape.add("Hor1Vert3");
			shape.add("Hor2Vert2"); shape.add("Hor3Vert1");
			firstTime = false;
		} else {
			resetValues();
		}
	//set color and shape of tetris
	setColor();
	setShape();
	}
	
	
	public void resetValues() {
		TET_X = 320; TET_Y = 20;
	}
	
	//choose tetris color
	private static void setColor() {
		int index = random.nextInt(tetrisColors.size());
		COLOR = Color.decode(tetrisColors.get(index));
	}
	
	//choose shape of tetris
	private static void setShape() {
		int index2 = random.nextInt(shape.size());
		String string = shape.get(index2);
		if(string.equals("Hor4")) {
			for(int i=0; i<4; i++) {
				tetrispieces.add(new Tetrispiece(TET_X +=GRID_SIZE, TET_Y));
			}
		}
		if(string.equals("Vert4")) {
			for(int i=0; i<4; i++) {
				tetrispieces.add(new Tetrispiece(TET_X , TET_Y+=GRID_SIZE));
			}
		}
		if(string.equals("Hor1Vert3")) {
			tetrispieces.add(new Tetrispiece(TET_X , TET_Y));
			tetrispieces.add(new Tetrispiece(TET_X+=GRID_SIZE , TET_Y));
			tetrispieces.add(new Tetrispiece(TET_X , TET_Y+=GRID_SIZE));
			tetrispieces.add(new Tetrispiece(TET_X , TET_Y+=GRID_SIZE));
			//System.out.println(TET_X + TET_Y);
		}
		if(string.equals("Hor2Vert2")) {
			tetrispieces.add(new Tetrispiece(TET_X , TET_Y));
			tetrispieces.add(new Tetrispiece(TET_X+=GRID_SIZE , TET_Y));
			tetrispieces.add(new Tetrispiece(TET_X+=GRID_SIZE , TET_Y));
			tetrispieces.add(new Tetrispiece(TET_X , TET_Y+=GRID_SIZE));
		}
		else {
			tetrispieces.add(new Tetrispiece(TET_X , TET_Y));
			tetrispieces.add(new Tetrispiece(TET_X+=GRID_SIZE , TET_Y));
			tetrispieces.add(new Tetrispiece(TET_X+=GRID_SIZE , TET_Y));
			tetrispieces.add(new Tetrispiece(TET_X-=GRID_SIZE , TET_Y+=GRID_SIZE));
		}
		lowestPoint = new Point(TET_X, TET_Y+GRID_SIZE);
	}
	
	//switch orientation of tetris with up arrow button
	//i.e vertical above horizontal and vice versa
	public void flip(Tetris tet) {
		
	}
	
	public Point getLowestPoint() {
		return lowestPoint;
	}
	
	public Color getColor() {
		return COLOR;
	}
	
	//returns top left point of all tetrispieces within tetris
	public ArrayList<Point> getPoints() {
		for(Tetrispiece p: tetrispieces){
			tetrisPoints.add(p.getPoint());
		}
		return tetrisPoints;
	}
	
	public void move(String direction) {
		if(direction.equals("Right")) {
			for(Tetrispiece tet: tetrispieces) { 
				tet.setX(tet.getX()+GRID_SIZE);
				lowestPoint = new Point(lowestPoint.x+GRID_SIZE, lowestPoint.y);
			}
		}
		if(direction.equals("Left")) {
			for(Tetrispiece tet: tetrispieces) { 
				tet.setX(tet.getX()-GRID_SIZE);
				lowestPoint = new Point(lowestPoint.x-GRID_SIZE, lowestPoint.y);
			}
		}
		if(direction.equals("Down")) {
			for(Tetrispiece tet: tetrispieces) { 
				tet.setY(tet.getY()+GRID_SIZE);
				lowestPoint = new Point(lowestPoint.x, lowestPoint.y+GRID_SIZE);
			}
		}
			
	}
	
	public static void render(Graphics2D graphics2d) {
		for(Tetrispiece tet: tetrispieces) {
			tet.render(graphics2d);
		}
		
	}
	
	/** This class creates the individual pieces of the tetris*/
	private static class Tetrispiece{
		int x, y ;

		public Tetrispiece(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public Point getPoint() {
			Point point = new Point(x, y);
			return point;
		}
		
		public void setX(int newX) {
			x =newX; 
		}
		
		public void setY(int newY) {
			y = newY;
		}
		
		//renders individual pieces of the tetris
		public void render(Graphics2D g2d) {
			g2d.setColor(COLOR);
			g2d.fillRect(x, y, GRID_SIZE, GRID_SIZE);
		}
	}
}
/**So far...
 * 1. Can only move to left or right, down will be automatic
 * 2.
 * */
