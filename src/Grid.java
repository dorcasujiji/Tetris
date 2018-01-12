import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/** This class handles the grids within the game */
public class Grid {
	int x, y;
	private boolean occupied = false;
	private final static int GRID_SIZE = 20;
	
	
	/** Grid constructor */
	public Grid(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setOccupied(Boolean newOccupStatus) {
		occupied = newOccupStatus;
	}
	
	public static void paintGrid(ArrayList<Point> list, Color color, Graphics2D g2D) {
		for(Point p: list) {
			g2D.fillRect(p.x, p.y, GRID_SIZE, GRID_SIZE);
			g2D.setColor(color);
		}
	}
}
