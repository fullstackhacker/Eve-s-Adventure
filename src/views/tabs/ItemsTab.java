package views.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

final class ItemsTab extends VBox{

	private static final ItemsTab INSTANCE = new ItemsTab();
	
	private ItemsTab(){
		this.setSpacing(10);
		this.setPadding(new Insets(0, 20, 10, 20));
		this.setAlignment(Pos.CENTER);
		
		/* Making all the buttons */
		final Button SHRUB = new Button("Shrub");
		final Button TREE = new Button("Tree");
		
		/* Width */
		SHRUB.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TREE.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		/* Add to Pane */
		this.getChildren().addAll(SHRUB, TREE);
	}
	
	protected static ItemsTab getInstance(){
		return INSTANCE;
	}
}
