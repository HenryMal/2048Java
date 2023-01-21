package View.Game;

import java.util.List;

import Model.Tile;
import View.Menu.BottomMenuView;
import View.Menu.TopMenuView;
import View.Menu.TopMenu.ResetBoardView;
import View.Menu.Utilities.BackgroundCreator;
import javafx.animation.ParallelTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

// will hold everything, from score, reset button, 

public class ApplicationView extends BorderPane {

	private TopMenuView topView;
	private BottomMenuView bottomView;
	private GameView gameView;
	private HBox gameViewHolder;
	private Color backgroundColor = Color.web("#faf8ef");
	private BackgroundCreator background;
	
	public ApplicationView(List<Tile> modelList) {
		
		this.topView = new TopMenuView();
		this.bottomView = new BottomMenuView();
		this.gameView = new GameView(modelList);
		this.gameViewHolder = new HBox(gameView);
		this.background = new BackgroundCreator(backgroundColor);
		
		setBackground(background.getBackground());
		
		HBox.setMargin(gameView, new Insets(0, 50, 0, 50));
		gameViewHolder.setAlignment(Pos.CENTER);
		
		setTop(topView);
		setCenter(gameViewHolder);
		setBottom(bottomView);
		
	}
	
	//---------------------GAME VIEW METHODS---------------------
	
	public void gameOverAnimation() {
		gameView.gameOverAnimation();
	}
	
	public void setModelList(List<Tile> modelList) {
		gameView.setModelList(modelList);
	}
	
	public void setNumberOfTilesToBeRemoved(int number) {
		gameView.setNumberOfTilesToBeRemoved(number);
	}
	
	public ParallelTransition getSlidingAnimation() {
		return gameView.getSlidingAnimation();
	}
	
	public ParallelTransition getMergeAndSpawnAnimation() {
		return gameView.getMergeAndSpawnAnimation();
	}
	
	public void updateBoard() {
		gameView.updateBoard();
	}
	
	public void initializeGameView() {
		gameView.initializeGameView();
	}
	
	public TryAgainView getTryAgain() {
		return gameView.getTryAgain();
	}
	
	//---------------------GAME VIEW METHODS---------------------
	
	//---------------------TOP MENU VIEW METHODS---------------------
	public void setCurrentScore(int givenValue) {
		topView.setCurrentScore(givenValue);
	}
	
	public void setBestScore(int givenValue) {
		topView.setBestScore(givenValue);
	}
	
	public int getBestScore() {
		return topView.getBestScore();
	}
	
	public int getCurrentScore() {
		return topView.getCurrentScore();
	}
	
	public void setAnimatedScore(int givenValue) {
		topView.setAnimatedScore(givenValue);
	}
	
	public void animateScore() {
		topView.animateScore();
	}
	
	public ResetBoardView getResetButton() {
		return topView.getResetButton();
	}
	//---------------------TOP MENU VIEW METHODS---------------------
	
	//---------------------BUTTOM MENU VIEW METHODS---------------------
	public void setNumberOfMoves(int givenValue) {
		bottomView.setNumberOfMoves(givenValue);
	}
	//---------------------BUTTOM MENU VIEW METHODS---------------------
	
}
