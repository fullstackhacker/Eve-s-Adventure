package views.tabs;

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

		/* Width */
		EVE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		/* Add to Pane */
		this.getChildren().addAll(EVE_BUTTON);
		
	}
	
	protected static CreaturesTab getInstance(){
		return INSTANCE;
	}
}
