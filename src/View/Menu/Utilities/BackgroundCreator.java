package View.Menu.Utilities;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class BackgroundCreator {

	private Color backgroundColor;
	private BackgroundFill backgroundFill;
	private Background background;
	
	public BackgroundCreator(Color givenColor) {
		this.backgroundColor = givenColor;
		this.backgroundFill = new BackgroundFill(backgroundColor, new CornerRadii(3), Insets.EMPTY);
		this.background = new Background(backgroundFill);
	}
	
	public Background getBackground() {
		return background;
	}
}
