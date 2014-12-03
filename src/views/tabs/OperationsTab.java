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
		final Button WAKE_UP_BUTTON = new Button("WAKE UP");
		final Button TURN_LEFT_BUTTON = new Button("TURN LEFT");
		final Button PICK_UP_BAMBOO_BUTTON = new Button("PICK UP BAMBOO");
		final Button PUT_BAMBOO_BUTTON = new Button("PUT BAMBOO");
		
		MOVE_BUTTON.setOnAction(ButtonHandlers::MOVE_BUTTON_HANDLER);
		SLEEP_BUTTON.setOnAction(ButtonHandlers::SLEEP_BUTTON_HANDLER);
		WAKE_UP_BUTTON.setOnAction(ButtonHandlers::WAKE_UP_BUTTON_HANDLER);
		TURN_LEFT_BUTTON.setOnAction(ButtonHandlers::TURN_LEFT_BUTTON_HANDLER);
		PICK_UP_BAMBOO_BUTTON.setOnAction(ButtonHandlers::PICK_UP_BAMBOO_BUTTON_HANDLER);
		PUT_BAMBOO_BUTTON.setOnAction(ButtonHandlers::PUT_BAMBOO_BUTTON_HANDLER);
		
		
		MOVE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		SLEEP_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		WAKE_UP_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TURN_LEFT_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		PICK_UP_BAMBOO_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		PUT_BAMBOO_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		this.getChildren().addAll(MOVE_BUTTON, SLEEP_BUTTON, WAKE_UP_BUTTON, TURN_LEFT_BUTTON, PICK_UP_BAMBOO_BUTTON, PUT_BAMBOO_BUTTON);
	}
	
	protected static OperationsTab getInstance(){
		return (instant == null) ? instant = new OperationsTab(): instant;
	}
}
