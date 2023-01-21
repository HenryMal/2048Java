package Presenter;

import Model.Board;
import View.Game.ApplicationView;
import javafx.animation.ParallelTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

public class GamePresenter {
	
	private Board model;
	private ApplicationView applicationView;
	private int numberOfMoves;
	private boolean didGameOverPlay = false;
	
	public GamePresenter() {
		
		this.model = new Board();
		this.applicationView = new ApplicationView(model.getTilesInBoard());
		
		initializeResetBoard();
		
	}
	
	private void setScores(int givenValue) {
		
		applicationView.setCurrentScore(givenValue);
		if (applicationView.getBestScore() < givenValue) {
			applicationView.setBestScore(givenValue);
		}
		
	}
	
	private void animateScores(int givenValue) {
		applicationView.setAnimatedScore(givenValue);
		applicationView.animateScore();
	}
	
	private void updateNumberOfMoves(boolean boardChanged) {
		if (boardChanged) numberOfMoves++;
		applicationView.setNumberOfMoves(numberOfMoves);
	}
	
	private void initializeResetBoard() {
		applicationView.getResetButton().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
		        	resetGame();
		        }
			}
			
		});
	}
	
	private void resetGame() {
    	
		didGameOverPlay = false;
    	numberOfMoves = 0;
		model = new Board();
		synchronize();
		applicationView.initializeGameView();
		setScores(model.getUserScore());
		updateNumberOfMoves(model.getHasBoardChanged());
		
		if (getWindow() != null) {
			getWindow().sizeToScene();
		}

	}
	
	private void synchronize() {
		applicationView.setModelList(model.getTilesInBoard());
		applicationView.setNumberOfTilesToBeRemoved(model.getNumberOfTilesRemoved());		
		applicationView.updateBoard();
	}
	
	public void moveTiles(int direction) {
		
		model.move(direction);
		synchronize();
		setScores(model.getUserScore());
		animateScores(model.getChangedScore());
		updateNumberOfMoves(model.getHasBoardChanged());
		
		
		if (!model.canMakeMove() && !didGameOverPlay) {
			applicationView.gameOverAnimation();
			didGameOverPlay = true;
			applicationView.getTryAgain().setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {
			        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
			        	resetGame();
			        }
				}
				
			});
		}
		
	}
	
	public Pane getPane() {
		return applicationView;
	}
	
	public ParallelTransition getSlidingAnimation() {
		return applicationView.getSlidingAnimation();
	}
	
	public ParallelTransition getMergeAndSpawnAnimation() {
		return applicationView.getMergeAndSpawnAnimation();
	}
	
	private Window getWindow() {
		try {
			return applicationView.getScene().getWindow();
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
