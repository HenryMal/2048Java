package View.Menu.TopMenu;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ResetAndScoreView extends VBox {
	
	private ResetBoardView resetButton;
	private Color backgroundColor = Color.web("#8f7a66");
	private ScoresView scores;
	
	public ResetAndScoreView() {
		
		this.resetButton = new ResetBoardView("New Game", backgroundColor);
		this.scores = new ScoresView(0, 0);
		setSpacing(55);
		setAlignment(Pos.CENTER_RIGHT);
		
		getChildren().addAll(scores, resetButton);
		
	}

	public void setCurrentScore(int givenValue) {
		scores.setCurrentScore(givenValue);
	}
	
	public void setBestScore(int givenValue) {
		scores.setBestScore(givenValue);
	}
	
	public int getBestScore() {
		return scores.getBestScore();
	}
	
	public int getCurrentScore() {
		return scores.getCurrentScore();
	}
	
	public void setAnimatedScore(int givenValue) {
		scores.setAnimatedScore(givenValue);
	}
	
	public void animateScore() {
		scores.animateScore();
	}
	
	public ResetBoardView getResetButton() {
		return resetButton;
	}
	
}
