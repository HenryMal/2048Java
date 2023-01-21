package View.Game;


import Model.Tile;
import View.Menu.Utilities.TextCreator;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.effect.BlurType;

public class TileView extends StackPane {
    
    private Tile tile;
    private RectangleView rectangleView;
    private TextCreator displayedValue;
    private Color textColor;
    private Color tileColor;
    private Color glowColor;
    private Color borderColor;
    private DropShadow tileGlow;
    private double fontSize;
    private static double fontMultiplier = 1;
    
	public TileView (Tile givenTile) {
		
		this.tile = givenTile;
		this.rectangleView = new RectangleView();
		
		changeTileAppearance();
		
	}

	public void changeTileAppearance() {
		
		getChildren().clear();
		rectangleView.setEffect(null);
		rectangleView.setStrokeWidth(0);
		fontSize = 55 * fontMultiplier;
		
		int tileValue = tile.getValue();
		
		switch (tileValue) {
		case 2:
			textColor = Color.web("#776e65"); 
			tileColor = Color.web("#eee4da");
			break;
		case 4:
			textColor = Color.web("#776e65"); 
			tileColor = Color.web("#ede0c8");
			break;
		case 8:
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#f2b179");
			break;
		case 16: 
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#f59563");
			break;
		case 32:
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#f67c5f");
			break;
		case 64:
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#f65e3b");
			break;
		case 128: 
			fontSize = 45 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#edcf72");
			borderColor = Color.web("#FFFFFF", 0.14286);
			rectangleView.setStroke(borderColor);
			rectangleView.setStrokeWidth(1);
			glowColor = Color.web("#F3D774", 0.2381);
			tileGlow = new DropShadow(BlurType.THREE_PASS_BOX, glowColor, 30, 0.5, 0, 0);
			rectangleView.setEffect(tileGlow);
			break;
		case 256: 
			fontSize = 45 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#edcc61");
			borderColor = Color.web("#FFFFFF", 0.19048);
			rectangleView.setStroke(borderColor);
			rectangleView.setStrokeWidth(1);
			glowColor = Color.web("#F3D774", 0.31746);
			tileGlow = new DropShadow(BlurType.THREE_PASS_BOX, glowColor, 30, 0.5, 0, 0);
			rectangleView.setEffect(tileGlow);
			break;	
		case 512:
			fontSize = 45 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#edc850");
			borderColor = Color.web("#FFFFFF", 0.2381);
			rectangleView.setStroke(borderColor);
			rectangleView.setStrokeWidth(1);
			glowColor = Color.web("#F3D774", 0.39683);
			tileGlow = new DropShadow(BlurType.THREE_PASS_BOX, glowColor, 30, 0.5, 0, 0);
			rectangleView.setEffect(tileGlow);
			break;
		case 1024:
			fontSize = 35 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#edc53f");
			borderColor = Color.web("#FFFFFF", 0.28571);
			rectangleView.setStroke(borderColor);
			rectangleView.setStrokeWidth(1);
			glowColor = Color.web("#F3D774", 0.47619);
			tileGlow = new DropShadow(BlurType.THREE_PASS_BOX, glowColor, 30, 0.5, 0, 0);
			rectangleView.setEffect(tileGlow);
			break;
		case 2048:
			fontSize = 35 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#edc53f");
			borderColor = Color.web("#FFFFFF", 0.33333);
			rectangleView.setStroke(borderColor);
			rectangleView.setStrokeWidth(1);
			glowColor = Color.web("#F3D774", 0.55556);
			tileGlow = new DropShadow(BlurType.THREE_PASS_BOX, glowColor, 30, 0.5, 0, 0);
			rectangleView.setEffect(tileGlow);
			break;
		case 4096:
			fontSize = 30 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#b885ac");
			break;
		case 8192:
			fontSize = 30 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#af6da9");
			break;
		case 16384:
			fontSize = 30 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#ab61a7");
			break;
		case 32768:
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#a755a6");
			break;
		case 65536:
		case 131072:
		case 262144:
			fontSize = 25 * fontMultiplier;
			textColor = Color.web("#f9f6f2");
			tileColor = Color.web("#3c3a32");
			break;
		}
		
		displayedValue = new TextCreator(Integer.toString(tileValue), fontSize, textColor);
		setColor(tileColor);
		getChildren().addAll(rectangleView, displayedValue);
		
	}
	
	
	
	public void setColor(Color givenColor) {
		rectangleView.setColor(givenColor);
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile givenTile) {
		tile = givenTile;
	}

	public int moveXUnits() {
		return (tile.getFinalPosition().getXCoords() - tile.getCurrentPosition().getXCoords());
	}
	
	public int moveYUnits() {
		return (tile.getFinalPosition().getYCoords() - tile.getCurrentPosition().getYCoords());
	}
	
	public void resetTileCoordinates() {
		tile.setCurrentPosition(tile.getFinalPosition());
	}
	

	
}
