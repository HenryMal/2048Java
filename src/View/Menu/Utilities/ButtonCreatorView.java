package View.Menu.Utilities;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ButtonCreatorView extends HBox {
	
	private TextCreator text;
	private BackgroundCreator backgroundCreator;
	private Color textColor = Color.web("#f9f6f2");

	public ButtonCreatorView(String givenText, int fontSize, Color backgroundColor) {
		this.text = new TextCreator(givenText, fontSize, textColor);
		this.backgroundCreator = new BackgroundCreator(backgroundColor);
		setBackground(backgroundCreator.getBackground());
		
		setAlignment(Pos.CENTER);
		setMaxWidth(30);
		setMargin(text, new Insets(7.5, 15, 7.5, 15));
		
		getChildren().add(text);
		
	}

}
