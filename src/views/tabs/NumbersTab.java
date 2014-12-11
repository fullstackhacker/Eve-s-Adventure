package views.tabs;

import controllers.ButtonHandlers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

final class NumbersTab extends VBox{
	private static final NumbersTab INSTANCE = new NumbersTab();
	
	private NumbersTab(){
		this.setSpacing(10);
		this.setPadding(new Insets(0, 20, 10, 20));
		this.setAlignment(Pos.CENTER);
		
		for(int i = 0; i < 10; i++){
			final Button BUTTON = new Button(Integer.toString(i));
			BUTTON.setOnAction(ButtonHandlers::NUMBERS_BUTTON_HANDLER);
			this.getChildren().add(BUTTON);
		}
	}
	
	protected static NumbersTab getInstance(){
		return INSTANCE;
	}
}
