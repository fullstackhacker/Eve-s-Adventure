package views.tabs;

import controllers.ButtonHandlers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

final class CreaturesTab extends VBox{
	
	private static final CreaturesTab INSTANCE = new CreaturesTab();
	
	private CreaturesTab(){
		this.setSpacing(10);
		this.setPadding(new Insets(0, 20, 10, 20));
		this.setAlignment(Pos.CENTER);
		
		/* Making all the buttons */
		final Button EVE_BUTTON = new Button("Eve");
		final Button FRIENDS_BUTTON = new Button("Friends");
		final Button RMCREATURE_BUTTON = new Button("Remove Creature");

		/* Width */
		EVE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		FRIENDS_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		RMCREATURE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		/* Listeners */
		EVE_BUTTON.setOnAction(ButtonHandlers::EVE_BUTTON_HANDLER);
		FRIENDS_BUTTON.setOnAction(ButtonHandlers::FRIENDS_BUTTON_HANDLER);
		RMCREATURE_BUTTON.setOnAction(ButtonHandlers::RMCREATURE_BUTTON_HANDLER);
		
		/* Add to Pane */
		this.getChildren().addAll(EVE_BUTTON, FRIENDS_BUTTON, RMCREATURE_BUTTON);
		
	}
	
	protected static CreaturesTab getInstance(){
		return INSTANCE;
	}
}
