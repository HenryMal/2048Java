package View.Menu.BottomMenu;

import View.Menu.Utilities.TextCreator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MovesView extends HBox {

	private Color textColor = Color.web("#bbada0");
	private TextCreator userMoves;
	
	public MovesView(int numberOfMoves) {
		
		this.userMoves = new TextCreator(Integer.toString(numberOfMoves) + " moves", 15, textColor);
		
		getChildren().add(userMoves);
		
		setAlignment(Pos.CENTER_LEFT);
		setMargin(userMoves, new Insets(5, 0, 5, 25));
		
	}
	
	public void setNumberOfMoves(int givenValue) {
		userMoves.setText(Integer.toString(givenValue) + " moves");
	}
}
