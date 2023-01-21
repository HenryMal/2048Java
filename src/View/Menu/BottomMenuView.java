package View.Menu;

import View.Menu.BottomMenu.MovesView;
import javafx.scene.layout.VBox;

public class BottomMenuView extends VBox {
	
	private MovesView movesView;
	
	public BottomMenuView() {
		
		this.movesView = new MovesView(0);
		
		getChildren().addAll(movesView);

	}
	
	public void setNumberOfMoves(int givenValue) {
		movesView.setNumberOfMoves(givenValue);
	}
	
}
