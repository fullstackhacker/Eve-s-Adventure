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
		final Button WALLT_BUTTON = new Button("Wall - Top");
		final Button WALLB_BUTTON = new Button("Wall - Bottom");
		final Button WALLL_BUTTON = new Button("Wall - Left");
		final Button WALLR_BUTTON = new Button("Wall - Right");
		final Button RMITEM_BUTTON = new Button("Remove Item");
		final Button RMWALL_BUTTON = new Button("Remove Wall");
		
		
		/* Width */
		WALLT_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		WALLB_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		WALLL_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		WALLR_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		SHRUB_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		TREE_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		BAMBOO_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		RMITEM_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		RMWALL_BUTTON.setMaxWidth(GameTabs.GAMETABS_MAX_WIDTH);
		
		/* Listeners */
		SHRUB_BUTTON.setOnAction(ButtonHandlers::SHRUB_BUTTON_HANDLER);
		TREE_BUTTON.setOnAction(ButtonHandlers::TREE_BUTTON_HANDLER);
		BAMBOO_BUTTON.setOnAction(ButtonHandlers::BAMBOO_BUTTON_HANDLER);
		RMITEM_BUTTON.setOnAction(ButtonHandlers::RMITEM_BUTTON_HANDLER);
		RMWALL_BUTTON.setOnAction(ButtonHandlers::RMWALL_BUTTON_HANDLER);
		WALLT_BUTTON.setOnAction(ButtonHandlers::WALLT_BUTTON_HANDLER);
		WALLB_BUTTON.setOnAction(ButtonHandlers::WALLB_BUTTON_HANDLER);
		WALLL_BUTTON.setOnAction(ButtonHandlers::WALLL_BUTTON_HANDLER);
		WALLR_BUTTON.setOnAction(ButtonHandlers::WALLR_BUTTON_HANDLER);
		
		/* Add to Tab */
		this.getChildren().addAll(SHRUB_BUTTON, TREE_BUTTON, BAMBOO_BUTTON, RMITEM_BUTTON);
		VBox space = new VBox(10);
		VBox space2 = new VBox(10);
		VBox space3 = new VBox(10);
		this.getChildren().addAll(space, space2, space3, WALLT_BUTTON, WALLB_BUTTON, WALLL_BUTTON, WALLR_BUTTON, RMWALL_BUTTON);
	}
	
	protected static ItemsTab getInstance(){
		return INSTANCE;
	}
}
