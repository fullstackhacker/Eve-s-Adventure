package views.tabs;

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
		final Button IF_BUTTON = new Button("MOVE");
		final Button ELSE_BUTTON = new Button("SLEEP");
		final Button WHILE_BUTTON = new Button("AWAKE");
		final Button TASK_BUTTON = new Button("TURN LEFT");
		final Button LOOP_BUTTON = new Button("TURN RIGHT");
		
		//TODO Add setOnAction
		
		IF_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		ELSE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		WHILE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TASK_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		LOOP_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		//TODO Add setOnAction for each buttons
		
		this.getChildren().addAll(IF_BUTTON, ELSE_BUTTON, WHILE_BUTTON, TASK_BUTTON, LOOP_BUTTON);
	}
	
	protected static OperationsTab getInstance(){
		return (instant == null) ? new OperationsTab(): instant;
	}
}
