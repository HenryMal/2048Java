package View.Menu;

import View.Menu.TopMenu.LogoView;
import View.Menu.TopMenu.ResetAndScoreView;
import View.Menu.TopMenu.ResetBoardView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TopMenuView extends HBox {
	
	private LogoView logo;
	private ResetAndScoreView resetAndScore;
	
	public TopMenuView() {
		
		this.logo = new LogoView();
		this.resetAndScore = new ResetAndScoreView();

        HBox fillBox = new HBox();
        setHgrow(fillBox, Priority.ALWAYS);
        fillBox.setAlignment(Pos.CENTER);
        
        
        setAlignment(Pos.CENTER);
		getChildren().addAll(logo, fillBox, resetAndScore);
		setMargin(resetAndScore, new Insets(20, 25, 15, 25));

	}
	
	public void setCurrentScore(int givenValue) {
		resetAndScore.setCurrentScore(givenValue);
	}
	
	public void setBestScore(int givenValue) {
		resetAndScore.setBestScore(givenValue);
	}
	
	public int getBestScore() {
		return resetAndScore.getBestScore();
	}
	
	public int getCurrentScore() {
		return resetAndScore.getCurrentScore();
	}
	
	public ResetBoardView getResetButton() {
		return resetAndScore.getResetButton();
	}
	
	public void setAnimatedScore(int givenValue) {
		resetAndScore.setAnimatedScore(givenValue);
	}
	
	public void animateScore() {
		resetAndScore.animateScore();
	}

}
