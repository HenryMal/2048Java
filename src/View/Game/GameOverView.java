package View.Game;

import View.Menu.Utilities.BackgroundCreator;
import View.Menu.Utilities.TextCreator;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameOverView extends VBox {

	private TryAgainView tryAgain;
	private TextCreator gameOverText;
	private Color ovelayColor = Color.web("#eee4da", 0.73);
	private Color textOverlayColor = Color.web("#776e65");
	private Color backgroundButtonColor = Color.web("#8f7a66");
	private BackgroundCreator backgroundCreator;
	private int fontSize;
	
	public GameOverView(double widthSize, double heightSize) {
		
		setMaxWidth(widthSize);
		setMaxHeight(heightSize);
		setMinWidth(widthSize);
		setMinHeight(heightSize);
		
		backgroundCreator = new BackgroundCreator(ovelayColor);
		setBackground(backgroundCreator.getBackground());
		
		fontSize = (widthSize < 300) ? 40 : 60;
		
		tryAgain = new TryAgainView("Try Again", 18, backgroundButtonColor);
		gameOverText = new TextCreator("Game over!", fontSize, textOverlayColor);
		
		getChildren().addAll(gameOverText, tryAgain);
		setSpacing(25);
		setAlignment(Pos.CENTER);
		
	}
	
	public TryAgainView getTryAgain() {
		return tryAgain;
	}
	
}
