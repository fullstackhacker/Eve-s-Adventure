package views.tabs;

import controllers.ButtonHandlers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

final class ConditionalsTab extends VBox {

	private static ConditionalsTab instant = null;
	
	private ConditionalsTab(){
		this.setSpacing(10);
		this.setPadding(new Insets(0, 20, 10, 20));
		this.setAlignment(Pos.CENTER);
		
		/* Making all the buttons */
		final Button FRONT_IS_CLEAR_BUTTON = new Button("FRONT IS CLEAR");
		final Button NEXT_TO_A_FRIEND_BUTTON = new Button("NEXT TO A FRIEND");
		final Button FACING_NORTH_BUTTON = new Button("FACING NORTH");
		final Button FACING_SOUTH_BUTTON = new Button("FACING SOUTH");
		final Button FACING_EAST_BUTTON = new Button("FACING EAST");
		final Button FACING_WEST_BUTTON = new Button("FACING WEST");
		final Button BAG_IS_EMPTY_BUTTON = new Button("BAG IS EMPTY");
		
		FRONT_IS_CLEAR_BUTTON.setOnAction(ButtonHandlers::FRONT_IS_CLEAR_BUTTON_HANDLER);
		NEXT_TO_A_FRIEND_BUTTON.setOnAction(ButtonHandlers::NEXT_TO_A_FRIEND_BUTTON_HANDLER);
		FACING_NORTH_BUTTON.setOnAction(ButtonHandlers::FACING_NORTH_BUTTON_HANDLER);
		FACING_SOUTH_BUTTON.setOnAction(ButtonHandlers::FACING_SOUTH_BUTTON_HANDLER);
		FACING_EAST_BUTTON.setOnAction(ButtonHandlers::FACING_EAST_BUTTON_HANDLER);
		FACING_WEST_BUTTON.setOnAction(ButtonHandlers::FACING_WEST_BUTTON_HANDLER);
		BAG_IS_EMPTY_BUTTON.setOnAction(ButtonHandlers::BAG_IS_EMPTY_BUTTON_HANDLER);
		
		
		FRONT_IS_CLEAR_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		NEXT_TO_A_FRIEND_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		FACING_NORTH_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		FACING_SOUTH_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		FACING_EAST_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		FACING_WEST_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		BAG_IS_EMPTY_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		this.getChildren().addAll(FRONT_IS_CLEAR_BUTTON, NEXT_TO_A_FRIEND_BUTTON, FACING_NORTH_BUTTON, FACING_SOUTH_BUTTON, FACING_EAST_BUTTON);
	}
	
	protected static ConditionalsTab getInstance(){
		return (instant == null) ? instant = new ConditionalsTab() : instant;
	}
}
