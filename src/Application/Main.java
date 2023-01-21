package Application;

import Presenter.GamePresenter;
import javafx.animation.Animation.Status;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryState) throws Exception {
		
		GamePresenter gamePresenter = new GamePresenter();
		
		Scene scene = new Scene(gamePresenter.getPane());

		scene.setOnKeyPressed(event -> {

			if (gamePresenter.getSlidingAnimation().getStatus() == Status.STOPPED 
					&& gamePresenter.getMergeAndSpawnAnimation().getStatus() == Status.STOPPED) {
				
				gamePresenter.getSlidingAnimation().setRate(1);
				gamePresenter.getMergeAndSpawnAnimation().setRate(1);
				
				switch(event.getCode()) {
				case LEFT:
					gamePresenter.moveTiles(0);
					break;
				case RIGHT:
					gamePresenter.moveTiles(1);
					break;
				case UP:
					gamePresenter.moveTiles(2);
					break;
				case DOWN:
					gamePresenter.moveTiles(3);
					break;
				default:
					break;
				}
			}
			
			else if (gamePresenter.getSlidingAnimation().getStatus() == Status.RUNNING 
					|| gamePresenter.getMergeAndSpawnAnimation().getStatus() == Status.RUNNING){
	
				gamePresenter.getSlidingAnimation().setRate(3.5);
				gamePresenter.getMergeAndSpawnAnimation().setRate(3.5);
				
			}

		});
		

		primaryState.setScene(scene);
		primaryState.setTitle("2048");
		primaryState.setResizable(false);
		primaryState.show();

		
	}
	


}
