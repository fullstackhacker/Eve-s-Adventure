package views.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public final class GameTabs extends TabPane{
	
	private static GameTabs instant = null;
	
	protected static final double GAMETABS_MAX_WIDTH = Double.MAX_VALUE;
	
	private GameTabs() {
		/* Declare All the tabs */
		final Tab INSTRUCTIONS_TAB = new Tab("INSTRUCTIONS");
		final Tab CONDITIONS_TAB = new Tab("CONDITIONS");
		final Tab OPERATIONS_TAB = new Tab("OPERATIONS");
		final Tab CREATURES_TAB = new Tab("CREATURES");
		final Tab ITEMS_TAB = new Tab("ITEMS");
		
		/* Setting the closableProperty */
		INSTRUCTIONS_TAB.closableProperty().set(false);
		CONDITIONS_TAB.closableProperty().set(false);
		OPERATIONS_TAB.closableProperty().set(false);
		CREATURES_TAB.closableProperty().set(false);
		ITEMS_TAB.closableProperty().set(false);
		
		INSTRUCTIONS_TAB.setContent(InstructionsTab.getInstance());
		CONDITIONS_TAB.setContent(ConditionalsTab.getInstance());
		OPERATIONS_TAB.setContent(OperationsTab.getInstance());
		CREATURES_TAB.setContent(CreaturesTab.getInstance());
		ITEMS_TAB.setContent(ItemsTab.getInstance());
		
		
		/* Adding all the tabs to the TabPane */
		this.getTabs().add(INSTRUCTIONS_TAB);
		this.getTabs().add(CONDITIONS_TAB);
		this.getTabs().add(OPERATIONS_TAB);
		this.getTabs().add(CREATURES_TAB);
		this.getTabs().add(ITEMS_TAB);
		
		this.setPadding(new Insets(5, 5, 5, 5));
		

	}

	public static GameTabs getInstance(){
		return (instant == null) ? new GameTabs() : instant;
	}
}
