package views.tabs;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public final class GameTabs extends TabPane{
	
	private static GameTabs instant = null;
	
	private GameTabs() {
		/* Declare All the tabs */
		final Tab INSTRUCTIONS_TAB = new Tab("INSTRUCTIONS");
		final Tab CONDITIONS_TAB = new Tab("CONDITIONS");
		final Tab OPERATIONS_TAB = new Tab("OPERATIONS");

		/* Setting the closableProperty */
		INSTRUCTIONS_TAB.closableProperty().set(false);
		CONDITIONS_TAB.closableProperty().set(false);
		OPERATIONS_TAB.closableProperty().set(false);
		
		INSTRUCTIONS_TAB.setContent(InstructionsTab.getInstance());
		CONDITIONS_TAB.setContent(ConditionalsTab.getInstance());
		OPERATIONS_TAB.setContent(OperationsTab.getInstance());
		
		/* Adding all the tabs to the TabPane */
		this.getTabs().add(INSTRUCTIONS_TAB);
		this.getTabs().add(CONDITIONS_TAB);
		this.getTabs().add(OPERATIONS_TAB);
		

	}

	public static GameTabs getInstance(){
		return (instant == null) ? new GameTabs() : instant;
	}
}
