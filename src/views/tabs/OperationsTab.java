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
		
		IF_BUTTON.setMaxWidth(Double.MAX_VALUE);
		ELSE_BUTTON.setMaxWidth(Double.MAX_VALUE);
		WHILE_BUTTON.setMaxWidth(Double.MAX_VALUE);
		TASK_BUTTON.setMaxWidth(Double.MAX_VALUE);
		LOOP_BUTTON.setMaxWidth(Double.MAX_VALUE);
		
		//TODO Add setOnAction for each buttons
		
		this.getChildren().addAll(IF_BUTTON, ELSE_BUTTON, WHILE_BUTTON, TASK_BUTTON, LOOP_BUTTON);
	}
	
	protected static OperationsTab getInstance(){
		return (instant == null) ? new OperationsTab(): instant;
	}
}
