package View.Game;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GridView extends GridPane{
	
	private static final double PADDING = 15;
	private static final int DEFAULT_ROW_SIZE = 4;
	private static final int DEFAULT_COL_SIZE = 4;
	
	RectangleView[][] cells;
	
	public GridView() {
		initCells();
	}
	
	public void initCells() {
		
		getChildren().clear();
		
		setHgap(PADDING);
		setVgap(PADDING);
		
		this.cells = new RectangleView[DEFAULT_ROW_SIZE][DEFAULT_COL_SIZE];
		Color cellColor = Color.web("#eee4da", 0.35);

		for (int rows = 0; rows < DEFAULT_ROW_SIZE; rows++) {
			for (int cols = 0; cols < DEFAULT_COL_SIZE; cols++) {
				
				this.cells[rows][cols] = new RectangleView();
				this.cells[rows][cols].setColor(cellColor);
			}
			
			addRow(rows, cells[rows]);
		}

		
	}
	
	public double getCellSize() {
		return cells[0][0].getCellSize();
	}
	
	public double getPaddingSize() {
		return PADDING;
	}
	
	public int getRowSize() {
		return DEFAULT_ROW_SIZE;
	}
	
	public int getColSize() {
		return DEFAULT_COL_SIZE;
	}
	
	
}
