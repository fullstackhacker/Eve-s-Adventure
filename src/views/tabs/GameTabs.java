package views.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public final class GameTabs extends TabPane{
	
	private static GameTabs INSTANCE = new GameTabs();
	
	protected static final double GAMETABS_MAX_WIDTH = Double.MAX_VALUE;
	
	public static final int INSTRUCTIONS_TAB_VALUE = 0;
	
	public static final int CONDITIONS_TAB_VALUE = 1;
	
	public static final int OPERATIONS_TAB_VALUE = 2;
	
	public static final int CREATURES_TAB_VALUE = 3;
	
	public static final int ITEMS_TAB_VALUE = 4;
	
	private GameTabs() {
		this.setPadding(new Insets(5, 5, 5, 5));
		
		final Tab INSTRUCTIONS_TAB = new Tab("INSTRUCTIONS");
		final Tab CONDITIONS_TAB = new Tab("CONDITIONS");
		final Tab OPERATIONS_TAB = new Tab("OPERATIONS");
		final Tab CREATURES_TAB = new Tab("CREATURES");
		final Tab ITEMS_TAB = new Tab("ITEMS");

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
		
		INSTRUCTIONS_TAB.setId("tabheader");
		CONDITIONS_TAB.setId("tabheader");
		OPERATIONS_TAB.setId("tabheader");
		CREATURES_TAB.setId("tabheader");
		ITEMS_TAB.setId("tabheader");
		
		CONDITIONS_TAB.setDisable(true);
		
		this.getTabs().addAll(INSTRUCTIONS_TAB, CONDITIONS_TAB, OPERATIONS_TAB, CREATURES_TAB, ITEMS_TAB);
	}
	
	public void switchTab(int tab){
		this.getSelectionModel().select(tab);
	}
	
	public void enableTab(int tab){
		this.getTabs().get(tab).setDisable(false);
	}
	
	public void disableTab(int tab){
		this.getTabs().get(tab).setDisable(true);
	}

	public static GameTabs getInstance(){
		return INSTANCE;
	}
}
