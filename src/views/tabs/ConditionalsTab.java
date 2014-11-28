package views.tabs;

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
		final Button IF_BUTTON = new Button("FRONT IS CLEAR");
		final Button ELSE_BUTTON = new Button("NEXT TO A BAMBOO");
		final Button WHILE_BUTTON = new Button("NEXT TO A WALL");
		final Button TASK_BUTTON = new Button("FACING NORTH");
		final Button LOOP_BUTTON = new Button("FACING SOUTH");
		
		//TODO Add setOnAction
		
		IF_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		ELSE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		WHILE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TASK_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		LOOP_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		//TODO Add setOnAction for each buttons
		
		this.getChildren().addAll(IF_BUTTON, ELSE_BUTTON, WHILE_BUTTON, TASK_BUTTON, LOOP_BUTTON);
	}
	
	protected static ConditionalsTab getInstance(){
		return (instant == null) ? new ConditionalsTab() : instant;
	}
}
