package views;

import controllers.ButtonHandlers;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public final class ATopMenu extends MenuBar {

	private static ATopMenu instant = null;

	private ATopMenu() {
		final Menu menu1 = new Menu("File");
		// final Menu menu2 = new Menu("Options");
		// final Menu menu3 = new Menu("Help");

		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(ButtonHandlers::QUIT_MENU_HANDLER);
		MenuItem save = new MenuItem("Save");
		save.setOnAction(ButtonHandlers::SAVE_MENU_HANDLER);

		menu1.getItems().addAll(save, quit);

		this.getMenus().addAll(menu1);
	}

	public static ATopMenu getInstance() {
		return (instant == null) ? new ATopMenu() : instant;
	}
}

