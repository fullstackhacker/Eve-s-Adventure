package views.tabs;

import controllers.ButtonHandlers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

final class InstructionsTab extends VBox{

	private static InstructionsTab instant = null;
	
	private InstructionsTab(){
		this.setSpacing(10);
		this.setPadding(new Insets(0, 20, 10, 20));
		this.setAlignment(Pos.CENTER);
		
		/* Making all the buttons */
		final Button IF_BUTTON = new Button("IF");
		final Button ELSE_BUTTON = new Button("ELSE");
		final Button WHILE_BUTTON = new Button("WHILE");
		final Button TASK_BUTTON = new Button("TASK");
		final Button LOOP_BUTTON = new Button("LOOP");
		
		//TODO Add setOnAction
		IF_BUTTON.setOnAction(ButtonHandlers::IF_BUTTON_HANDLER);
		ELSE_BUTTON.setOnAction(ButtonHandlers::ELSE_BUTTON_HANDLER);
		
		IF_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		ELSE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		WHILE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TASK_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		LOOP_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		//TODO Add setOnAction for each buttons
		
		this.getChildren().addAll(IF_BUTTON, ELSE_BUTTON, WHILE_BUTTON, TASK_BUTTON, LOOP_BUTTON);
	}
	
	public static InstructionsTab getInstance(){
		return (instant == null) ? new InstructionsTab() : instant;
	}
}
