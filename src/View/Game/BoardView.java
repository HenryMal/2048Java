package View.Game;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardView extends StackPane {
	
	private double boardWidth;
	private double boardHeight;
	private final double BOARD_ARC_SIZE = 128 / 12d;
	
	private Color boardColor = Color.web("#bbada0");
	private Rectangle boardView;
	private GridView gridView;
	
	public BoardView() {
		
		this.gridView = new GridView();
		Group gridGroup = new Group(gridView);
		
		this.boardWidth = (gridView.getCellSize() + gridView.getPaddingSize()) * gridView.getColSize() + gridView.getPaddingSize();
		this.boardHeight = (gridView.getCellSize() + gridView.getPaddingSize()) * gridView.getRowSize() + gridView.getPaddingSize();
		initiateBoard();
		
		getChildren().addAll(boardView, gridGroup);
		
	}
	
	private void initiateBoard() {
		
		this.boardView = new Rectangle();
		this.boardView.setWidth(boardWidth);
		this.boardView.setHeight(boardHeight);
		this.boardView.setArcWidth(BOARD_ARC_SIZE);
		this.boardView.setArcHeight(BOARD_ARC_SIZE);
		this.boardView.setFill(boardColor);
		
	}
	
	public double getCellSize() {
		return gridView.getCellSize();
	}
	
	public double getPaddingSize() {
		return gridView.getPaddingSize();
	}
	
	public double getBoardWidth() {
		return boardWidth;
	}
	
	public double getBoardHeight() {
		return boardHeight;
	}
	
	
}
