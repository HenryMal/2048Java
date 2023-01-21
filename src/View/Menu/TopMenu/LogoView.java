package View.Menu.TopMenu;

import Model.Tile;
import View.Game.TileView;
import View.Menu.Utilities.TextCreator;
import javafx.geometry.Insets;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LogoView extends BorderPane {
	
	private TextCreator createdWithDescription;
	private TextCreator authorDescription;
	private TextCreator inspiredByText;
	private TileView tileLogo;
	private Color descriptionColor = Color.web("#FFA500");
	private Color textGlowColor = Color.web("#f2b179");
	private Color authorColor = Color.web("#776e65");
	private VBox authorHolder;
	private DropShadow textGlow;

	public LogoView() {
		createLogo();
		createDescription();
		createAuthorDescription();
	}
	
	private void createLogo() {
		tileLogo = new TileView(new Tile(2048));
		setLeft(tileLogo);
		BorderPane.setMargin(tileLogo, new Insets(12.5, 15, 0, 22.5));
	}
	
	private void createDescription() {
		
		createdWithDescription = new TextCreator("JavaFX ED.", 32.5, descriptionColor);
		
		textGlow = new DropShadow(BlurType.THREE_PASS_BOX, textGlowColor, 5, 0.25, 0, 0);
		createdWithDescription.setEffect(textGlow);
		
		setRight(createdWithDescription);
		BorderPane.setMargin(createdWithDescription, new Insets(10, 0, 0, 0));
		
	}
	
	private void createAuthorDescription() {
		
		authorHolder = new VBox();
		
		authorDescription = new TextCreator("Created by H. MAL", 17.5, authorColor);
		inspiredByText = new TextCreator("Clone of 2048", 17.5, authorColor);
		
		authorHolder.getChildren().addAll(inspiredByText, authorDescription);
		setBottom(authorHolder);
		BorderPane.setMargin(authorHolder, new Insets(0, 0, 10, 25));
	}
	
}
