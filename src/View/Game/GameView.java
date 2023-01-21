package View.Game;

import java.util.ArrayList;
import java.util.List;

import Model.Tile;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameView extends Pane {
	
    private final static Duration SLIDING_ANIMATION = Duration.millis(65); //100
    private final static Duration SPAWNING_ANIMATION = Duration.millis(125); // 125
    private final static Duration MERGING_ANIMATION = Duration.millis(80); //80
    private final static Duration FADE_ANIMATION = Duration.millis(2000);
	
	private BoardView boardView;
	private GameOverView gameOver;
	private List<Tile> modelList;
	private List<TileView> tilesToBeRemoved;
	private List<TileView> childrenList;
	private ParallelTransition animateMergeAndSpawn;
	private ParallelTransition animateSliding;
	private FadeTransition fadeGameOverOverlay;
	private Group tileGroup;
	private int numberOfTilesToBeRemoved;
	private double translateLength;

	public GameView(List<Tile> givenModelList) {

		this.modelList = givenModelList;
		initializeGameView();
		
	}
	
	public void updateBoard() {
		
		for (Tile tile : modelList) {
			
			if (tile.getRecentlySpawned()) {
				addInitialTiles(tile);
			}

			else {
				
				for (int i = 0; i < childrenList.size(); i++) {
					
					if (tile.getCurrentPosition().getXCoords() == childrenList.get(i).getTile().getCurrentPosition().getXCoords()
							&& tile.getCurrentPosition().getYCoords() == childrenList.get(i).getTile().getCurrentPosition().getYCoords()) {
						
						childrenList.get(i).setTile(tile);
						
						if (numberOfTilesToBeRemoved != 0) {
							tilesToBeRemoved.add(childrenList.get(i));
							numberOfTilesToBeRemoved -= 1;
						}

					}
					
				}
				
			}

		}
		
		for (TileView tileView : childrenList) {
			
			if (tileView.getTile().getMerged()) {
				animateMergeAndSpawn.getChildren().add(combineAnimation(tileView));
			}
			
			if (tileView.getTile().getRecentlySpawned() == false) {
				animateSliding.getChildren().add(moveTilesAnimation(tileView));
				tileView.getTile().setCurrentPosition(tileView.getTile().getFinalPosition());
			}
			
		}


		animateSliding.play();
		animateSliding.setOnFinished(finished -> {
			

			animateMergeAndSpawn.play();
			childrenList.removeAll(tilesToBeRemoved);
			tileGroup.getChildren().removeAll(tilesToBeRemoved);

			childrenList.forEach(tileView -> tileView.changeTileAppearance());

			animateSliding.getChildren().clear();
			animateMergeAndSpawn.getChildren().clear();
			tilesToBeRemoved.clear();

		});

	}

	
	public void initializeGameView() {
		
		getChildren().clear();
		
		this.boardView = new BoardView();
		this.gameOver = new GameOverView(boardView.getBoardWidth(), boardView.getBoardHeight());
		this.tileGroup = new Group();
		this.animateMergeAndSpawn = new ParallelTransition();
		this.animateSliding = new ParallelTransition();
		this.fadeGameOverOverlay = new FadeTransition(FADE_ANIMATION, gameOver);
		this.tilesToBeRemoved = new ArrayList<TileView>();
		this.childrenList = new ArrayList<TileView>();
		this.translateLength = boardView.getCellSize() + boardView.getPaddingSize();
		
		tileGroup.setLayoutX(boardView.getPaddingSize());
		tileGroup.setLayoutY(boardView.getPaddingSize());
		
		for (Tile tile : modelList) {
			
			if (tile.getRecentlySpawned()) {
				addInitialTiles(tile);
			}

		}
		
		gameOver.setOpacity(0);
		
		getChildren().addAll(boardView, tileGroup, gameOver);
		animateMergeAndSpawn.play();
		animateMergeAndSpawn.getChildren().clear();
		
	}

	private TileView createTileView(Tile givenTile) {
		
		TileView gameTile = new TileView(givenTile);
		gameTile.setLayoutX(translateLength * givenTile.getCurrentPosition().getXCoords());
		gameTile.setLayoutY(translateLength * givenTile.getCurrentPosition().getYCoords());
		gameTile.setScaleX(0);
		gameTile.setScaleY(0);
		
		return gameTile;
		
	}
	
	private ScaleTransition spawningAnimation(TileView givenTileView) {
		
		ScaleTransition spawnTileAnimation = new ScaleTransition(SPAWNING_ANIMATION, givenTileView);
		spawnTileAnimation.setToX(1.0);
        spawnTileAnimation.setToY(1.0);
        spawnTileAnimation.setInterpolator(Interpolator.EASE_OUT);
        
        return spawnTileAnimation;
		
	}
	
	private SequentialTransition combineAnimation(TileView givenTileView) {
		
		ScaleTransition scaleUpAnimation = new ScaleTransition(MERGING_ANIMATION, givenTileView);
		scaleUpAnimation.setToX(1.2);
		scaleUpAnimation.setToY(1.2);
		scaleUpAnimation.setInterpolator(Interpolator.EASE_IN);
        
		ScaleTransition scaleDownAnimation = new ScaleTransition(MERGING_ANIMATION, givenTileView);
		scaleDownAnimation.setToX(1.0);
		scaleDownAnimation.setToY(1.0);
		scaleDownAnimation.setInterpolator(Interpolator.EASE_OUT);
		
        return new SequentialTransition(scaleUpAnimation, scaleDownAnimation);
		
	}
	
	private TranslateTransition moveTilesAnimation(TileView givenTileView) {
		
		TranslateTransition slideTile = new TranslateTransition(SLIDING_ANIMATION, givenTileView);

		slideTile.setByX(translateLength * givenTileView.moveXUnits());
		slideTile.setByY(translateLength * givenTileView.moveYUnits());
		slideTile.setInterpolator(Interpolator.EASE_BOTH);
		
		return slideTile;
		
	}
	
	private void addInitialTiles(Tile givenTile) {
		
		TileView newTile = createTileView(givenTile);
		animateMergeAndSpawn.getChildren().add(spawningAnimation(newTile));
		tileGroup.getChildren().add(newTile);
		childrenList.add(newTile);
		
	}
	
	public void gameOverAnimation() {
		
		fadeGameOverOverlay.setFromValue(0);
		fadeGameOverOverlay.setToValue(1);
		fadeGameOverOverlay.setInterpolator(Interpolator.EASE_BOTH);
		fadeGameOverOverlay.play();
		
	}
	
	public TryAgainView getTryAgain() {
		return gameOver.getTryAgain();
	}
	
	public void setModelList(List<Tile> modelList) {
		this.modelList = modelList;
	}
	
	public void setNumberOfTilesToBeRemoved(int number) {
		numberOfTilesToBeRemoved = number;
	}
	
	public ParallelTransition getSlidingAnimation() {
		return animateSliding;
	}
	
	public ParallelTransition getMergeAndSpawnAnimation() {
		return animateMergeAndSpawn;
	}

}
