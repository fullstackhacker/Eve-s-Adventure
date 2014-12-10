package views;

import controllers.ButtonHandlers;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public final class TopMenu extends MenuBar {

	private static TopMenu instant = null;

	private TopMenu() {
		final Menu menu1 = new Menu("File");
		final Menu menu2 = new Menu("Options");
		// final Menu menu2 = new Menu("Options");
		// final Menu menu3 = new Menu("Help");

		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(ButtonHandlers::QUIT_MENU_HANDLER);
		MenuItem save = new MenuItem("Save");
		save.setOnAction(ButtonHandlers::SAVE_MENU_HANDLER);
		MenuItem addObj = new MenuItem("Add Objective");
		addObj.setOnAction(ButtonHandlers::ADDOB_MENU_HANDLER);

		menu1.getItems().addAll(save, quit);
		menu2.getItems().addAll(addObj);

		this.getMenus().addAll(menu1, menu2);
	}

	public static TopMenu getInstance() {
		return (instant == null) ? new TopMenu() : instant;
	}
}
