package views.tabs;

import javafx.scene.layout.VBox;

final class CreaturesTab extends VBox{
	
	private static final CreaturesTab INSTANCE = new CreaturesTab();
	
	private CreaturesTab(){
		
	}
	
	protected static CreaturesTab getInstance(){
		return INSTANCE;
	}
}
