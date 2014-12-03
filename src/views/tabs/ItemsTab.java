package views.tabs;

import controllers.ButtonHandlers;
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
		final Button SHRUB_BUTTON = new Button("Shrub");
		final Button TREE_BUTTON = new Button("Tree");
		final Button BAMBOO_BUTTON = new Button("Bamboo");
		final Button RMITEM_BUTTON = new Button("Remove Item");
		
		/* Width */
		SHRUB_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TREE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		BAMBOO_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		RMITEM_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		/* Listeners */
		SHRUB_BUTTON.setOnAction(ButtonHandlers::SHRUB_BUTTON_HANDLER);
		TREE_BUTTON.setOnAction(ButtonHandlers::TREE_BUTTON_HANDLER);
		BAMBOO_BUTTON.setOnAction(ButtonHandlers::BAMBOO_BUTTON_HANDLER);
		RMITEM_BUTTON.setOnAction(ButtonHandlers::RMITEM_BUTTON_HANDLER);
		
		
		/* Add to Tab */
		this.getChildren().addAll(SHRUB_BUTTON, TREE_BUTTON, BAMBOO_BUTTON, RMITEM_BUTTON);
	}
	
	protected static ItemsTab getInstance(){
		return INSTANCE;
	}
}
