package views;

import controllers.ButtonHandlers;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public final class TopMenu extends MenuBar {

	private static TopMenu instant = null;
	public MenuItem collect = null;
	public MenuItem find = null;
	public MenuItem saveA = null;
	public MenuItem saveS = null;
	
	private TopMenu() {
		final Menu menu1 = new Menu("File");
		final Menu menu2 = new Menu("Options");
		// final Menu menu2 = new Menu("Options");
		// final Menu menu3 = new Menu("Help");

		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(ButtonHandlers::QUIT_MENU_HANDLER);

		Menu save = new Menu("Save");
		saveA = new MenuItem("Save As Level");
		saveA.setOnAction(ButtonHandlers::SAVEA_MENU_HANDLER);
		saveS = new MenuItem("Save For Editing");
		saveS.setOnAction(ButtonHandlers::SAVES_MENU_HANDLER);
		Menu addObj = new Menu("Add Objective");
		collect = new MenuItem("Collect all Bamboo");
		collect.setOnAction(ButtonHandlers::COLLECT_MENU_HANDLER);
		find = new MenuItem("Find Friend");
		find.setOnAction(ButtonHandlers::FIND_MENU_HANDLER);
		addObj.getItems().addAll(collect, find);
		save.getItems().addAll(saveS, saveA);

		menu1.getItems().addAll(save, quit);
		menu2.getItems().addAll(addObj);
//		MenuItem saveworld = new MenuItem("Save world");
//		saveworld.setOnAction(ButtonHandlers::SAVE_MENU_HANDLER);
//		
//		menu1.getItems().addAll(saveworld, quit);

		this.getMenus().addAll(menu1, menu2);
	}

	public static TopMenu getInstance() {
		return (instant == null) ? instant = new TopMenu() : instant;
	}
}
