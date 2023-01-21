package View.Menu.TopMenu;

import View.Menu.Utilities.BackgroundCreator;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ScoreBoxView extends HBox {
	
	private ScoresLabelView scoreContainer;
	private Color backgroundColor = Color.web("#bbada0");
	private BackgroundCreator backgroundCreator;
	
	public ScoreBoxView (String givenTitle, int userScore) {
		
		this.scoreContainer = new ScoresLabelView(givenTitle, userScore);
		this.backgroundCreator = new BackgroundCreator(backgroundColor);
		setBackground(backgroundCreator.getBackground());
		setMaxHeight(60);

		setAlignment(Pos.CENTER);
		
		getChildren().add(scoreContainer);
		
	}
	
	public void setScore(int givenValue) {
		scoreContainer.setScore(givenValue);
	}
	
	public int getScore() {
		return scoreContainer.getScore();
	}
	
}
