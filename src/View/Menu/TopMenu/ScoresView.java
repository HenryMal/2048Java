package View.Menu.TopMenu;

import View.Menu.Utilities.TextCreator;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ScoresView extends HBox {
	
	private final static Duration SCORE_DURATION = Duration.millis(750);
	
	private ScoreBoxView userCurrentScore;
	private ScoreBoxView userBestScore;
	private Color animationColor = Color.web("#776e65");
	private TextCreator animatedText;
	private StackPane userCurrentScoreHolder;
	
	// testing score animation
	private int scoreChange = 0;
	private int animationTextSize = 25;
	private TranslateTransition animateText;
	private FadeTransition fadeText;
	private ParallelTransition animationCombine;

	public ScoresView(int currentScore, int bestScore) {
		
		this.userCurrentScore = new ScoreBoxView("SCORE", currentScore);
		this.userBestScore = new ScoreBoxView("BEST", bestScore);
		this.userCurrentScoreHolder = new StackPane(userCurrentScore);
	
		setSpacing(5);
		getChildren().addAll(userCurrentScoreHolder, userBestScore);
		
	}
	
	public void setCurrentScore(int givenValue) {
		userCurrentScore.setScore(givenValue);
	}
	
	public void setBestScore(int givenValue) {
		userBestScore.setScore(givenValue);
	}
	
	public int getBestScore() {
		return userBestScore.getScore();
	}
	
	public int getCurrentScore() {
		return userCurrentScore.getScore();
	}
	
	public void setAnimatedScore(int givenValue) {
		scoreChange = givenValue;
	}
	
	public void animateScore() {
		
		if (scoreChange == 0) {
			return;
		}
		
		animatedText = new TextCreator("", animationTextSize, animationColor);
		fadeText = new FadeTransition(SCORE_DURATION, animatedText);
		animateText = new TranslateTransition(SCORE_DURATION, animatedText);
		
		userCurrentScoreHolder.getChildren().add(animatedText);
		
		animatedText.setText("+" + Integer.toString(scoreChange));
		
		animateText.setByY(100);
		animateText.setInterpolator(Interpolator.EASE_BOTH);
		
		fadeText.setFromValue(1);
		fadeText.setToValue(0);
		
		animationCombine = new ParallelTransition(animateText, fadeText);
		animationCombine.play();
		animationCombine.setOnFinished(finished -> {
			userCurrentScoreHolder.getChildren().remove(animatedText);
		});
		
	}
}
