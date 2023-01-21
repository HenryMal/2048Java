package View.Menu.Utilities;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextCreator extends Text {
	
	private Font font;
	
	public TextCreator(String givenText, double fontSize, Color givenColor) {
		
		this.font = Font.loadFont(getClass().getResource("/ClearSans-Bold.ttf").toExternalForm(), fontSize);
		setFont(font);
		setText(givenText);
		setSmooth(true);
		setFill(givenColor);		
		
	}
	
}
