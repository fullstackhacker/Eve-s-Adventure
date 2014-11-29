package views.tabs;

import controllers.ButtonHandlers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

final class OperationsTab extends VBox{

	private static OperationsTab instant = null;
	
	private OperationsTab(){
		this.setSpacing(10);
		this.setPadding(new Insets(0, 20, 10, 20));
		this.setAlignment(Pos.CENTER);
		
		/* Making all the buttons */
		final Button MOVE_BUTTON = new Button("MOVE");
		final Button SLEEP_BUTTON = new Button("SLEEP");
		final Button AWAKE_BUTTON = new Button("AWAKE");
		final Button TURN_LEFT_BUTTON = new Button("TURN LEFT");
		final Button TURN_RIGHT_BUTTON = new Button("TURN RIGHT");
		final Button TAKE_BAMBOO_BUTTON = new Button("Take Bamboo");
		final Button GIVE_BAMBOO_BUTTON = new Button("Give Bamboo");
		
		MOVE_BUTTON.setOnAction(ButtonHandlers::MOVE_BUTTON_HANDLER);
		
		
		MOVE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		SLEEP_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		AWAKE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TURN_LEFT_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TURN_RIGHT_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TAKE_BAMBOO_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		GIVE_BAMBOO_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		this.getChildren().addAll(MOVE_BUTTON, SLEEP_BUTTON, AWAKE_BUTTON, TURN_LEFT_BUTTON, TURN_RIGHT_BUTTON, TAKE_BAMBOO_BUTTON, GIVE_BAMBOO_BUTTON);
	}
	
	protected static OperationsTab getInstance(){
		return (instant == null) ? new OperationsTab(): instant;
	}
}
