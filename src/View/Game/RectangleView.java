package View.Game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// the bases of the tiles and the grid



public class RectangleView extends Rectangle {
	

	private static double cellSize = 107; // original 107d 4x4, 87 for 5x5 and 6x6
	private static double arcSize = cellSize / 12d;
	
	protected Color rectangleColor;
	
	public RectangleView() {
		
		setWidth(cellSize);
		setHeight(cellSize);
		setArcWidth(arcSize);
		setArcHeight(arcSize);
		
	}
	
	public void setColor(Color newColor) {
		
		this.rectangleColor = newColor;
		this.setFill(newColor);
		
	}
	
	public double getCellSize() {
		return cellSize;
	}
	
	
}
