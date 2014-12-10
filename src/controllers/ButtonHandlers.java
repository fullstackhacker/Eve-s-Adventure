package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Coordinate;
import models.campaign.KarelCode;
import models.campaign.World;
import models.gridobjects.creatures.Creature;
import models.gridobjects.items.Bamboo;
import models.gridobjects.items.Item;
import models.gridobjects.items.Shrub;
import models.gridobjects.items.Tree;
import views.MainApp;
import views.TopMenu;
import views.grid.GridWorld;
import views.karel.KarelTable;
import views.scenes.LoadMenuScene;
import views.scenes.LoadSessionScene;
import views.scenes.MainMenuScene;
import views.scenes.AdventureModeScene;
import views.scenes.SandboxScene;
import views.tabs.GameTabs;
import views.tabs.InstructionsTab;

/**
 * 
 * @author Anthony Wong
 * @author Megan Murray
 *
 */
public final class ButtonHandlers {

	private static boolean sandbox = false;

	public static final void SANDBOX_MODE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("SANDBOX_MODE_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuScene.getInstance());
		sandbox = true;
	}

	public static final void ADVENTURE_MODE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("ADVENTURE_MODE_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuScene.getInstance());
		sandbox = false;
	}

	public static final void LOAD_SESSION_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("LOAD_SESSION_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadSessionScene.getInstance());
	}

	public static final void NEW_SESSION_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("NEW_SESSION_BUTTON_HANDLER CALLED");
		if (sandbox) {
			MainApp.changeScenes(SandboxScene.getInstance());
		} else {
			MainApp.changeScenes(AdventureModeScene.getInstance());
		}
	}

	public static final void CANCEL_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("CANCEL_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuScene.getInstance());
	}

	public static final void BACK_HOMESCREEN_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("BACK_HOMESCREEN_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(MainMenuScene.getInstance());
	}

	/**
	 * InstuctionsTab.java
	 */
	public static final void IF_BUTTON_HANDLER(ActionEvent e) {
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			int line = KarelTable.getInstance().getLineSelectedLine();
			int start = line;
			ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
			for( ; line < karelCode.size() ;line++){
				if(karelCode.get(line).equals(KarelCode.ENDWHILE)){
					KarelTable.getInstance().replaceInstructions(start, line, KarelCode.IFSTATEMENT);
					break;
				}
			}
		}else{
			KarelTable.getInstance().addInstructionsCode(KarelCode.IFSTATEMENT);
		}
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}

	public static final void END_IF_BUTTON_HANDLER(ActionEvent e) {
	}

	public static final void ELSE_BUTTON_HANDLER(ActionEvent e) {
		KarelTable.getInstance().addInstructionsCode(KarelCode.ELSESTATEMENT);
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			InstructionsTab.LOOP_BUTTON.setDisable(false);
			// TODO
			return;
		}
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void END_ELSE_BUTTON_HANDLER(ActionEvent e) {
	}

	public static final void WHILE_BUTTON_HANDLER(ActionEvent e) {
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			int line = KarelTable.getInstance().getLineSelectedLine();
			int start = line;
			ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
			for( ; line < karelCode.size() ;line++){
				if(karelCode.get(line).equals(KarelCode.ENDIF)){
					KarelTable.getInstance().replaceInstructions(start, line, KarelCode.WHILESTATEMENT);
					break;
				}
			}
		}else{
			KarelTable.getInstance().addInstructionsCode(KarelCode.WHILESTATEMENT);
		}
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}

	public static final void END_WHILE_BUTTON_HANDLER(ActionEvent e) {
	}

	public static final void TASK_BUTTON_HANDLER(ActionEvent e) {
	}

	public static final void LOOP_BUTTON_HANDLER(ActionEvent e) {
		KarelTable.getInstance().addInstructionsCode(KarelCode.LOOPSTATEMENT);
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.NUMBERS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.NUMBERS_TAB_VALUE);
	}

	public static final void END_LOOP_BUTTON_HANDLER(ActionEvent e) {
	}

	/**
	 * ConditionalsTab.java
	 */
	public static final void FRONT_IS_CLEAR_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FRONT_IS_CLEAR_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.FRONTISCLEAR);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FRONTISCLEAR);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void NEXT_TO_A_FRIEND_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("NEXT_TO_A_FRIEND_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.NEXTTOAFRIEND);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.NEXTTOAFRIEND);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void FACING_NORTH_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FACING_NORTH_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.FACINGNORTH);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FACINGNORTH);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void FACING_SOUTH_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FACING_SOUTH_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.FACINGSOUTH);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FACINGSOUTH);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void FACING_EAST_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FACING_EAST_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.FACINGEAST);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FACINGEAST);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void FACING_WEST_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FACING_WEST_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.FACINGWEST);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FACINGWEST);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void BAG_IS_EMPTY_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("BAG_IS_EMPTY_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.BAGISEMPTY);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.BAGISEMPTY);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	/**
	 * OperationsTab.java
	 */
	public static final void MOVE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("MOVE_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.MOVE);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.MOVE);
	}

	public static final void SLEEP_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("SLEEP_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.SLEEP);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.SLEEP);
	}

	public static final void WAKE_UP_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("WAKE_UP_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.WAKEUP);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.WAKEUP);
	}

	public static final void TURN_RIGHT_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("TURN_RIGHT_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.TURNRIGHT);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.TURNRIGHT);
	}

	public static final void PICK_UP_BAMBOO_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("PICK_UP_BAMBOO_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.PICKBAMBOO);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.PICKBAMBOO);
	}

	public static final void PUT_BAMBOO_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("PUT_BAMBOO_BUTTON_HANDLER");
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(KarelCode.PUTBAMBOO);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.PUTBAMBOO);
	}

	public static final void NUMBERS_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("NUMBERS_BUTTON_HANDLER");
		String value = ((Button) e.getSource()).getText();
		if (KarelTable.getInstance().isREPLACE_BUTTON_ON()) {
			KarelTable.getInstance().replaceCode(value);
			return;
		}
		KarelTable.getInstance().addCode(value);

		GameTabs.getInstance().disableTab(GameTabs.NUMBERS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	/**
	 * Popup for if a grid space is not empty Asks to replace object or just
	 * cancel
	 * 
	 * @newObject the object the user is trying to put into the square
	 */
	public static void popup(String newObject) {
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(
				new Text("There is already an object in this space. \nReplace "
						+ oldObject + " with " + newObject + "?"));
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		final Button REPLACE = new Button("Replace");
		REPLACE.setMaxWidth(100);
		final Button CANCEL = new Button("Cancel");
		REPLACE.setMaxWidth(100);
		dialogVbox.getChildren().addAll(REPLACE, CANCEL);
		dialog.setScene(dialogScene);
		dialog.show();

		CANCEL.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				dialog.close();
			}
		});
		REPLACE.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				dialog.close();
				if (oldObject.equals("Tree") || oldObject.equals("Shrub")
						|| oldObject.equals("Bamboo"))
					RMITEM_BUTTON_HANDLER(null);

				if (oldObject.equals("Friend"))
					RMCREATURE_BUTTON_HANDLER(null);

				GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
						.getYCoordinate()].setText(newObject);
				switch (newObject) {
				case "Tree":
					Tree tree = new Tree(4);
					tree.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					if (GridWorld.getInstance().getWorld() == null)
						System.out.println("Uninitalized world");
					GridWorld.getInstance().getWorld().addItem(tree);
					GridWorld.getInstance().getWorld().printWorld();
					break;
				case "Shrub":
					Shrub shrub = new Shrub(4, false);
					shrub.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					if (GridWorld.getInstance().getWorld() == null)
						System.out.println("Uninitalized world");
					GridWorld.getInstance().getWorld().addItem(shrub);
					GridWorld.getInstance().getWorld().printWorld();
					break;
				case "Bamboo":
					Bamboo bamboo = new Bamboo(4);
					bamboo.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					if (GridWorld.getInstance().getWorld() == null)
						System.out.println("Uninitalized world");
					GridWorld.getInstance().getWorld().addItem(bamboo);
					GridWorld.getInstance().getWorld().printWorld();
				case "Friend":
					Coordinate cords = new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate());
					Creature Friend = new Creature("Friend", cords);
					Friend.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					if (GridWorld.getInstance().getWorld() == null)
						System.out.println("Uninitalized world");
					GridWorld.getInstance().getWorld().addCreature(Friend);
					GridWorld.getInstance().getWorld().printWorld();
				default:
					break;
				}
			}
		});

	}

	/**
	 * Popup if they try to put something where Eve is
	 * 
	 * @newObject the object the user is trying to put into the square
	 */
	public static void EvePop() {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox
				.getChildren()
				.add(new Text(
						"This space is already full. \nYou can't put an Object here."));
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		final Button OKAY = new Button("Okay");
		dialogVbox.getChildren().add(OKAY);
		dialog.setScene(dialogScene);
		dialog.show();
		OKAY.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				dialog.close();
			}
		});
	}

	/**
	 * Popup if they try delete the wrong thing
	 * 
	 * @newObject the object the user is trying to put into the square
	 */
	public static void DeletePop() {
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(
				new Text("This space is a(n) " + oldObject
						+ ". \nUse the Remove" + oldObject + " button."));
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		final Button OKAY = new Button("Okay");
		dialogVbox.getChildren().add(OKAY);
		dialog.setScene(dialogScene);
		dialog.show();
		OKAY.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				dialog.close();
			}
		});
	}

	/**
	 * ItemsTab.java
	 */
	public static final void RMITEM_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("RMITEM_BUTTON_HANDLER");
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();
		switch (oldObject) {
		case "Tree":
			GridWorld
					.getInstance()
					.getWorld()
					.removeItem(
							new Coordinate(GridWorld.getXCoordinate(),
									GridWorld.getYCoordinate()));
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setText("");
			break;
		case "Shrub":
			GridWorld
					.getInstance()
					.getWorld()
					.removeItem(
							new Coordinate(GridWorld.getXCoordinate(),
									GridWorld.getYCoordinate()));
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setText("");
			break;
		case "Bamboo":
			GridWorld
					.getInstance()
					.getWorld()
					.removeItem(
							new Coordinate(GridWorld.getXCoordinate(),
									GridWorld.getYCoordinate()));
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setText("");
		default:
			break;
		}
	}

	public static final void SHRUB_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("SHRUB_BUTTON_HANDLER");
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();
		if (oldObject.equals("Eve!"))
			EvePop();

		else if (oldObject.equals("Tree") || oldObject.equals("Shrub")
				|| oldObject.equals("Bamboo") || oldObject.equals("Friend")) {
			popup("Shrub");
		} else
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setText("Shrub");
		Shrub shrub = new Shrub(4, false);
		shrub.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate()));
		if (GridWorld.getInstance().getWorld() == null)
			System.out.println("Uninitalized world");
		GridWorld.getInstance().getWorld().addItem(shrub);
		GridWorld.getInstance().getWorld().printWorld();
	}

	public static final void TREE_BUTTON_HANDLER(ActionEvent e) {
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();
		if (oldObject.equals("Eve!"))
			EvePop();

		else if (oldObject.equals("Tree") || oldObject.equals("Shrub")
				|| oldObject.equals("Bamboo") || oldObject.equals("Friend")) {
			popup("Tree");

		} else {
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setText("Tree");
			Tree tree = new Tree(4);
			tree.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
					GridWorld.getYCoordinate()));
			if (GridWorld.getInstance().getWorld() == null)
				System.out.println("Uninitalized world");
			GridWorld.getInstance().getWorld().addItem(tree);
			GridWorld.getInstance().getWorld().printWorld();
		}
	}

	public static final void BAMBOO_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("BAMBOO_BUTTON_HANDLER");
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();
		if (oldObject.equals("Eve!"))
			EvePop();

		else if (oldObject.equals("Tree") || oldObject.equals("Shrub")
				|| oldObject.equals("Bamboo") || oldObject.equals("Friend")) {
			popup("Bamboo");
		} else {
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setText("Bamboo");
			Bamboo bamboo = new Bamboo(4);
			bamboo.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
					GridWorld.getYCoordinate()));
			if (GridWorld.getInstance().getWorld() == null)
				System.out.println("Uninitalized world");
			GridWorld.getInstance().getWorld().addItem(bamboo);
			GridWorld.getInstance().getWorld().printWorld();
		}
	}

	/**
	 * CreaturesTab.java
	 */
	public static final void RMCREATURE_BUTTON_HANDLER(ActionEvent e) {
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();
		if (oldObject.equals("Tree") || oldObject.equals("Shrub")
				|| oldObject.equals("Bamboo") || oldObject.equals("Friend")) {

		}
		System.out.println("RMCREATURE_BUTTON_HANDLER");
		GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].setText("");
		GridWorld
				.getInstance()
				.getWorld()
				.removeCreature(
						new Coordinate(GridWorld.getXCoordinate(), GridWorld
								.getYCoordinate()));
	}

	public static final void EVE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("EVE_BUTTON_HANDLER");
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();

		for (int y = 0; y < GridWorld.gridButtons.length; y++) {
			for (int x = 0; x < GridWorld.gridButtons[y].length; x++) {
				if (GridWorld.gridButtons[y][x].getText().equals("Eve!")) {
					// frontend move
					GridWorld.gridButtons[y][x].setText("   ");

					// backend move
					System.out.println("X: " + x + "Y: " + y);
					Creature eve = GridWorld.getInstance().getWorld()
							.removeCreature(new Coordinate(y, x));
					eve.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					eve.setDirection(Coordinate.DOWN);
					GridWorld.getInstance().getWorld().addCreature(eve);
				}
			}
		}

		if (oldObject.equals("Tree") || oldObject.equals("Shrub")
				|| oldObject.equals("Bamboo") || oldObject.equals("Friend")) {
			EvePop();
		}

		else {
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setText("Eve!");
			Coordinate cords = new Coordinate(GridWorld.getXCoordinate(),
					GridWorld.getYCoordinate());
			Creature Eve = new Creature("Eve!", cords);
			Eve.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
					GridWorld.getYCoordinate()));
			if (GridWorld.getInstance().getWorld() == null)
				System.out.println("Uninitalized world");
			GridWorld.getInstance().getWorld().addCreature(Eve);
			GridWorld.getInstance().getWorld().printWorld();
		}
	}

	public static final void FRIENDS_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FRIENDS_BUTTON_HANDLER");
		String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].getText();

		if (oldObject.equals("Tree") || oldObject.equals("Shrub")
				|| oldObject.equals("Bamboo") || oldObject.equals("Eve!")) {
			popup("Friend");
		} else
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setText("Friend");
		Coordinate cords = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		Creature Friend = new Creature("Friend", cords);
		Friend.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate()));
		if (GridWorld.getInstance().getWorld() == null)
			System.out.println("Uninitalized world");
		GridWorld.getInstance().getWorld().addCreature(Friend);
		GridWorld.getInstance().getWorld().printWorld();
	}

	public static final void GridWorld_BUTTON_HANDLER(ActionEvent e) {
	}

	public static final void BACK_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("BACK_BUTTON_HANDLER CALLED");
	}

	public static final void FORWARD_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FORWARD_BUTTON_HANDLER CALLED");
	}

	public static final void PLAY_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("PLAY_BUTTON_HANDLER CALLED");
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		if (karelCode.isEmpty()) {
			System.out.println("karelCode is empty!");
			return;
		}
		World world = AdventureModeScene.getWorld();
		System.out.println(KarelTable.getInstance().getKarelCode());
		Interpreter interpreter = new Interpreter(karelCode, world);
		world.printWorld();
		interpreter.start(); // starts the code
		world.printWorld();
	}

	public static final void RESET_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("RESET_BUTTON_HANDLER CALLED");
	}

	public static final void REPLACE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("REPLACE_BUTTON_HANDLER CALLED");
	}

	public static final void QUIT_MENU_HANDLER(ActionEvent e) {
		System.out.println("Quit sandbox mode. Returned to home screen.");
		MainApp.changeScenes(MainMenuScene.getInstance());
	}

	public static final void COLLECT_MENU_HANDLER(ActionEvent e) {
		System.out.println("Added an objective (Collect)!");
		//World world = AdventureModeScene.getWorld();
		//Level.setObjective(false);
		SandboxScene.getInstance().topMenu.collect.setDisable(true);
		SandboxScene.getInstance().topMenu.find.setDisable(false);
	}
	
	public static final void FIND_MENU_HANDLER(ActionEvent e) {
		System.out.println("Added an objective (Find)!");
		//World world = AdventureModeScene.getWorld();
		//Level.setObjective(true);
		SandboxScene.getInstance().topMenu.collect.setDisable(false);
		SandboxScene.getInstance().topMenu.find.setDisable(true);
	}

	public static final void SAVE_MENU_HANDLER(ActionEvent e) {
		System.out.println("Saved!");
	}
}
