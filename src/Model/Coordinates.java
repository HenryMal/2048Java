package Model;

// stores the tile's position in the 2d grid.
// also returns the tiles location in the GUI
// ^ to be implmented after we get the GUI running
// CELL_SIZE = 128;
// FORMULA for gui cellsize / 2 + (cellsize * coords); (GENERAL)

public class Coordinates {

	private int xCoords;
	private int yCoords;
	
	// want to have customizable board sizes
	public Coordinates(int x, int y) {
		this.xCoords = x;
		this.yCoords = y;
	}
	
	public int getXCoords() {
		return xCoords;
	}
	
	public int getYCoords() {
		return yCoords;
	}
	
	public String toString() {
		return "{ LOCATION -- X: " + xCoords + ", Y: " + yCoords + " }";
	}
	
}
