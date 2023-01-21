package Model;

public class Tile {
	
	
	// currentPos will be its current location in the grid
	// AND gui
	// finalPos is where we send that shit to
	
	private boolean hasMerged;
	private boolean recentlySpawned;
	private int value;
	private Coordinates currentPos;
	private Coordinates finalPos; // we could have done something like if this was null, it recently spawned and other shit.
	
	// creates a new tile of 2 or 4
	public Tile(Coordinates startingCoordinates) {
		
		this.currentPos = startingCoordinates;
		this.value = Math.random() < 0.9 ? 2 : 4;
		this.hasMerged = false;
		this.recentlySpawned = true;
		
	}
	
	// for the view
	public Tile(int value) {
		this.value = value;
	}
	
	// copy constructor
	public Tile(Tile tile) {
		
		this.hasMerged = tile.getMerged();
		this.recentlySpawned = tile.getRecentlySpawned();
		this.value = tile.getValue();
		this.currentPos = tile.getCurrentPosition();
		this.finalPos = tile.getFinalPosition();
		
	}
	
	public int getValue() {
		return value;
	}
	
	public void combineValues() {
		value = value * 2;
	}
	
	public boolean getMerged() {
		return hasMerged;
	}
	
	public void setMerged() {
		hasMerged = true;
	}
	
	public void clearMerge() {
		hasMerged = false;
	}
	
	public boolean getRecentlySpawned() {
		return recentlySpawned;
	}
	
	public void clearRecentlySpanwed() {
		recentlySpawned = false;
	}
	
	public Coordinates getCurrentPosition() {
		return currentPos;
	}
	
	public Coordinates getFinalPosition() {
		return finalPos;
	}
	
	public void setCurrentPosition(Coordinates givenCoordinates) {
		currentPos = givenCoordinates;
	}
	
	public void setFinalPosition(Coordinates givenCoordinates) {
		finalPos = givenCoordinates;
	}
	
	public String toString() {
		
		String finalCoords = (finalPos == null) ? "" : " | MOVE TO X: " + finalPos.getXCoords() + ", Y: " + finalPos.getYCoords();
		
		return "{ TILE CURRENT X: " + currentPos.getXCoords() + ", Y: " + currentPos.getYCoords()
				+ finalCoords
				+ " | VALUE: " + value + ", HAS MERGED: " + hasMerged + " | RECENTLY SPAWNED: " + recentlySpawned + " }";
	}
	
	
}
