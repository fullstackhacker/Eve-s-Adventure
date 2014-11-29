package views.tabs;

import javafx.scene.layout.VBox;

final class ItemsTab extends VBox{

	private static final ItemsTab INSTANCE = new ItemsTab();
	
	private ItemsTab(){
		
	}
	
	protected static ItemsTab getInstance(){
		return INSTANCE;
	}
}
