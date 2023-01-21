package View.Menu.TopMenu;

import View.Menu.Utilities.TextCreator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ScoresLabelView extends VBox {
	
	private TextCreator scoreLabel;
	private TextCreator currentScore;
	private Color labelColor = Color.web("#eee4da");
	private Color scoreColor = Color.WHITE;
	
	public ScoresLabelView(String labelTitle, int userScore) {
		
		this.scoreLabel = new TextCreator(labelTitle, 13, labelColor);
		this.currentScore = new TextCreator(Integer.toString(userScore), 25, scoreColor);
		setAlignment(Pos.CENTER);
		setMargin(scoreLabel, new Insets(5, 15, 0, 15));
		setMargin(currentScore, new Insets(0, 20, 0, 20));
		setSpacing(-5);
		
		getChildren().addAll(scoreLabel, currentScore);
		
	}
	
	public void setScore(int givenValue) {
		currentScore.setText(Integer.toString(givenValue));
	}
	
	public int getScore() {
		return Integer.parseInt(currentScore.getText());
	}
	
}
