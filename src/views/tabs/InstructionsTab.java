package views.tabs;

import controllers.ButtonHandlers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public final class InstructionsTab extends VBox{

	private static InstructionsTab instant = null;
	
	public static final Button ELSE_BUTTON = new Button("ELSE");
	
	public static final Button END_IF_BUTTON = new Button("END IF");
	
	public static final Button END_ELSE_BUTTON = new Button("END ELSE");
	
	public static final Button END_WHILE_BUTTON = new Button("END WHILE");
	
	public static final Button END_LOOP_BUTTON = new Button("END LOOP");
	
	private InstructionsTab(){
		this.setSpacing(10);
		this.setPadding(new Insets(0, 20, 10, 20));
		this.setAlignment(Pos.CENTER);
		
		/* Making all the buttons */
		final Button IF_BUTTON = new Button("IF");
		final Button WHILE_BUTTON = new Button("WHILE");
		final Button TASK_BUTTON = new Button("TASK");
		final Button LOOP_BUTTON = new Button("LOOP");
		
		IF_BUTTON.setOnAction(ButtonHandlers::IF_BUTTON_HANDLER);
		ELSE_BUTTON.setOnAction(ButtonHandlers::ELSE_BUTTON_HANDLER);
		WHILE_BUTTON.setOnAction(ButtonHandlers::WHILE_BUTTON_HANDLER);
		TASK_BUTTON.setOnAction(ButtonHandlers::TASK_BUTTON_HANDLER);
		LOOP_BUTTON.setOnAction(ButtonHandlers::LOOP_BUTTON_HANDLER);
		
		END_IF_BUTTON.setOnAction(ButtonHandlers::END_IF_BUTTON_HANDLER);
		END_ELSE_BUTTON.setOnAction(ButtonHandlers::END_ELSE_BUTTON_HANDLER);
		END_WHILE_BUTTON.setOnAction(ButtonHandlers::END_WHILE_BUTTON_HANDLER);
		END_LOOP_BUTTON.setOnAction(ButtonHandlers::END_LOOP_BUTTON_HANDLER);
		
		IF_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		ELSE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		WHILE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TASK_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		LOOP_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		END_IF_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		END_ELSE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		END_WHILE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		END_LOOP_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		TASK_BUTTON.setVisible(false);
		ELSE_BUTTON.setVisible(false);
		END_IF_BUTTON.setVisible(false);
		END_ELSE_BUTTON.setVisible(false);
		END_WHILE_BUTTON.setVisible(false);
		END_LOOP_BUTTON.setVisible(false);
		
		this.getChildren().addAll(IF_BUTTON, ELSE_BUTTON, WHILE_BUTTON, TASK_BUTTON, LOOP_BUTTON, END_IF_BUTTON, END_ELSE_BUTTON, END_WHILE_BUTTON, END_LOOP_BUTTON);
	}
	
	public static InstructionsTab getInstance(){
		return (instant == null) ? instant = new InstructionsTab() : instant;
	}
}
