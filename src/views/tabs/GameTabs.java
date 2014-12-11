package views.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public final class GameTabs extends TabPane {

	private static GameTabs INSTANCE = new GameTabs();

	protected static final double GAMETABS_MAX_WIDTH = Double.MAX_VALUE;

	public static final int INSTRUCTIONS_TAB_VALUE = 0;

	public static final int CONDITIONS_TAB_VALUE = 1;

	public static final int OPERATIONS_TAB_VALUE = 2;

	public static final int CREATURES_TAB_VALUE = 3;

	public static final int ITEMS_TAB_VALUE = 4;

	public static final int NUMBERS_TAB_VALUE = 5;

	public final Tab CREATURES_TAB = new Tab("CREATURES");
	public final Tab ITEMS_TAB = new Tab("ITEMS");

	private GameTabs() {
		this.setPadding(new Insets(5, 5, 5, 5));

		final Tab INSTRUCTIONS_TAB = new Tab("INSTRUCTIONS");
		final Tab CONDITIONS_TAB = new Tab("CONDITIONS");
		final Tab OPERATIONS_TAB = new Tab("OPERATIONS");
		final Tab NUMBERS_TAB = new Tab("NUMBERS");

		INSTRUCTIONS_TAB.closableProperty().set(false);
		CONDITIONS_TAB.closableProperty().set(false);
		OPERATIONS_TAB.closableProperty().set(false);
		CREATURES_TAB.closableProperty().set(false);
		ITEMS_TAB.closableProperty().set(false);
		NUMBERS_TAB.closableProperty().set(false);

		INSTRUCTIONS_TAB.setContent(InstructionsTab.getInstance());
		CONDITIONS_TAB.setContent(ConditionalsTab.getInstance());
		OPERATIONS_TAB.setContent(OperationsTab.getInstance());
		CREATURES_TAB.setContent(CreaturesTab.getInstance());
		ITEMS_TAB.setContent(ItemsTab.getInstance());
		NUMBERS_TAB.setContent(NumbersTab.getInstance());

		CONDITIONS_TAB.setDisable(true);
		CREATURES_TAB.setDisable(true);
		ITEMS_TAB.setDisable(true);
		NUMBERS_TAB.setDisable(true);

		INSTRUCTIONS_TAB.setId("tabheader");
		CONDITIONS_TAB.setId("tabheader");
		OPERATIONS_TAB.setId("tabheader");
		CREATURES_TAB.setId("tabheader");
		ITEMS_TAB.setId("tabheader");
		NUMBERS_TAB.setId("tabheader");

		this.getTabs().addAll(INSTRUCTIONS_TAB, CONDITIONS_TAB, OPERATIONS_TAB,
				CREATURES_TAB, ITEMS_TAB, NUMBERS_TAB);
	}

	public void switchTab(int tab) {
		this.getSelectionModel().select(tab);
	}

	public void enableTab(int tab) {
		this.getTabs().get(tab).setDisable(false);
	}

	public void disableTab(int tab) {
		this.getTabs().get(tab).setDisable(true);
	}

	public static GameTabs getInstance() {
		return INSTANCE;
	}
}
