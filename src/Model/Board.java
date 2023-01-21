package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {
	
	private static final int[][] DIRECTION_ARRAY = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; // left, right, up, down
	private static final int DEFAULT_ROW_SIZE = 4;
	private static final int DEFAULT_COL_SIZE = 4;
	private static final int TILES_AT_START = 2;

	private Tile[][] board;
	private boolean hasBoardChanged;
	private List<Coordinates> emptySpaces;
	private List<Tile> tilesInBoard;
	private int numberOfTilesRemoved;
	private int userScore;
	private int scoreChange;
	
	public Board() {
		
		userScore = 0;
		board = new Tile[DEFAULT_ROW_SIZE][DEFAULT_COL_SIZE];
		emptySpaces = new LinkedList<Coordinates>();
		tilesInBoard = new ArrayList<Tile>();
		
		initializeEmptySpacesList();
		spawnStartingTiles();
		
	}
	
	private void initializeEmptySpacesList() {	
		
		emptySpaces.clear();
		
		for (int rows = 0; rows < DEFAULT_ROW_SIZE; rows++) {
			for (int cols = 0; cols < DEFAULT_COL_SIZE; cols++) {
				
				if (board[rows][cols] == null) {
					emptySpaces.add(new Coordinates(cols, rows));
				}
				
				else {

					board[rows][cols].setFinalPosition(new Coordinates(cols, rows));
					tilesInBoard.add(new Tile(board[rows][cols]));

				}
				
			}
		}

	}
	
	private void spawnTile() {
		
		int randomIndex = (int) (Math.random() * emptySpaces.size());
		Coordinates tilesCoords = emptySpaces.get(randomIndex);
		
		board[tilesCoords.getYCoords()][tilesCoords.getXCoords()] = new Tile(tilesCoords);
		tilesInBoard.add(board[tilesCoords.getYCoords()][tilesCoords.getXCoords()]);

		emptySpaces.remove(tilesCoords);
		
	}
	
	public void move(int direction) {
		
		tilesInBoard.clear();
		hasBoardChanged = false;
		numberOfTilesRemoved = 0;
		scoreChange = 0;
		
		int[] directionArray = DIRECTION_ARRAY[direction]; 
		int iterator = (isDirectionOdd(direction)) ? -1 : 1; // 1

		for (int rows = setRowsInteger(isDirectionOdd(direction)); rowCondition(direction, rows); rows += iterator) { // 2
			
			for (int cols = setColsInteger(isDirectionOdd(direction)); colCondition(direction, cols); cols += iterator) { // 2
				
				if (board[rows][cols] != null) {

					if (board[rows][cols].getFinalPosition() != null) {
						board[rows][cols].setCurrentPosition(board[rows][cols].getFinalPosition());
					}
					
					board[rows][cols].clearRecentlySpanwed();
					
					while ( (!atBorder(direction, cols, rows)) 
							&& board[rows + directionArray[1]][cols + directionArray[0]] == null) {
						
						board[rows + directionArray[1]][cols + directionArray[0]] = board[rows][cols];
						board[rows][cols] = null;
						
						cols += directionArray[0];
						rows += directionArray[1];
						
						hasBoardChanged = true;
					}
					
					if (!atBorder(direction, cols, rows) 
							&& board[rows][cols].getValue() == board[rows + directionArray[1]][cols + directionArray[0]].getValue() 
							&& !board[rows + directionArray[1]][cols + directionArray[0]].getMerged()
							&& !board[rows][cols].getMerged()) {
						
						board[rows + directionArray[1]][cols + directionArray[0]].combineValues();
						board[rows + directionArray[1]][cols + directionArray[0]].setMerged();
						
						userScore += board[rows + directionArray[1]][cols + directionArray[0]].getValue();
						scoreChange += board[rows + directionArray[1]][cols + directionArray[0]].getValue();
						
						board[rows][cols].setFinalPosition(new Coordinates(cols + directionArray[0], rows + directionArray[1]));
						tilesInBoard.add(board[rows][cols]);
						board[rows][cols] = null;
						numberOfTilesRemoved++;
						
						hasBoardChanged = true;
						
					}
					
					
				}

					
			}
			
			
		}

		initializeEmptySpacesList();
		clearMergeStates();
		if (hasBoardChanged) spawnTile();
	}
	
	// --------------------- helper methods for move ---------------------
	private void clearMergeStates() {
		for (int rows = 0; rows < DEFAULT_ROW_SIZE; rows++) {
			for (int cols = 0; cols < DEFAULT_COL_SIZE; cols++) {
				if (board[rows][cols] != null) board[rows][cols].clearMerge();
			}
		}
	}
	private boolean isDirectionOdd(int direction) {
		return direction % 2 == 1;
	}
	private boolean rowCondition(int direction, int rows) {
		boolean holder = (isDirectionOdd(direction)) ? (rows >= 0) : (rows < DEFAULT_ROW_SIZE);
		return holder;
	}
	private boolean colCondition(int direction, int cols) {
		boolean holder = (isDirectionOdd(direction)) ? (cols >= 0) : (cols < DEFAULT_COL_SIZE);
		return holder;
	}
	private int setRowsInteger(boolean isOdd) {
		int rows = (isOdd) ? DEFAULT_ROW_SIZE - 1 : 0;
		return rows;
	}
	private int setColsInteger(boolean isOdd) {
		int cols = (isOdd) ? DEFAULT_COL_SIZE - 1 : 0;
		return cols;
	}
	private boolean atBorder(int direction, int cols, int rows) {
		
		boolean borderChecker = false;
		
		switch (direction) {
		case 0:
			borderChecker = (cols == 0);
			break;
		case 1:
			borderChecker = (cols == DEFAULT_COL_SIZE - 1);
			break;
		case 2:
			borderChecker = (rows == 0);
			break;
		case 3:
			borderChecker = (rows == DEFAULT_ROW_SIZE - 1);
			break;
		}
		
		return borderChecker;
		
	}
	// --------------------- ENDING OF HELPER METHODS ---------------------
	
	
	
	private void spawnStartingTiles() {
		for(int i = 0; i < TILES_AT_START; i++) {
			spawnTile();
		}
	}
	public List<Tile> getTilesInBoard() {
		return tilesInBoard;
	}
	public int getNumberOfTilesRemoved() {
		return numberOfTilesRemoved;
	}
	public int getUserScore() {
		return userScore;
	}
	public int getChangedScore() {
		return scoreChange;
	}
	public boolean getHasBoardChanged() {
		return hasBoardChanged;
	}
	public boolean canMakeMove() {
		for (int rows = 0; rows < DEFAULT_ROW_SIZE; rows++) {
			
			for(int cols = 0; cols < DEFAULT_COL_SIZE - 1; cols++) {
				
				if (board[cols][rows] == null 
						|| board[cols + 1][rows] == null
						|| board[rows][cols + 1] == null
						|| board[cols][rows].getValue() == board[cols + 1][rows].getValue() 
						|| board[rows][cols + 1].getValue() == board[rows][cols].getValue()) {
					return true;
				}
				
			}
			
		}
		
		return false;
	}
	
	public String toString() {

		String boardRepresentation = "";
		String adder = "";
		
		for (int rows = 0; rows < DEFAULT_ROW_SIZE; rows++) {
			
			boardRepresentation += "[ ";
			
			for (int cols = 0; cols < DEFAULT_COL_SIZE; cols++) {
				
				adder = (board[rows][cols] == null) ? "-" : Integer.toString(board[rows][cols].getValue());
				
				if (cols == DEFAULT_COL_SIZE - 1) {
					boardRepresentation += adder + " ]" + System.lineSeparator();
				}
				else {
					boardRepresentation += adder + " | ";
				}
				
			}
			
		}
		
		return boardRepresentation;
	}
	
}
