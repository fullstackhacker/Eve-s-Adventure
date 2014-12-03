package controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import models.campaign.KarelCode;
import models.campaign.World;
import views.MainApp;
import views.karel.KarelTable;
import views.scenes.LoadMenuScene;
import views.scenes.LoadSessionScene;
import views.scenes.MainMenuScene;
import views.scenes.SandboxScene;
import views.tabs.GameTabs;
/**
 * 
 * @author Anthony Wong
 *
 */
public final class ButtonHandlers {

	public static final void SANDBOX_MODE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("SANDBOX_MODE_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuScene.getInstance());
	}
	
	public static final void ADVENTURE_MODE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("ADVENTURE_MODE_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuScene.getInstance());
	}
	
	public static final void LOAD_SESSION_BUTTON_HANDLER(ActionEvent e){
		System.out.println("LOAD_SESSION_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadSessionScene.getInstance());
	}
	
	public static final void NEW_SESSION_BUTTON_HANDLER(ActionEvent e){
		System.out.println("NEW_SESSION_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(SandboxScene.getInstance());
	}
	
	public static final void CANCEL_BUTTON_HANDLER(ActionEvent e){
		System.out.println("CANCEL_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuScene.getInstance());
	}
	
	public static final void BACK_HOMESCREEN_BUTTON_HANDLER(ActionEvent e){
		System.out.println("BACK_HOMESCREEN_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(MainMenuScene.getInstance());
	}
	
	/**
	 * InstuctionsTab.java
	 */
	public static final void IF_BUTTON_HANDLER(ActionEvent e){
		KarelTable.getInstance().addInstructionsCode(KarelCode.IFSTATEMENT);
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.IFSTATEMENT);
			return;
		}
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}
	
	public static final void END_IF_BUTTON_HANDLER(ActionEvent e){
	}
	
	public static final void ELSE_BUTTON_HANDLER(ActionEvent e){
		KarelTable.getInstance().addInstructionsCode(KarelCode.ELSESTATEMENT);
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			//TODO
			KarelTable.getInstance().replaceCode(KarelCode.ELSESTATEMENT);
			return;
		}
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void END_ELSE_BUTTON_HANDLER(ActionEvent e){
	}
	
	public static final void WHILE_BUTTON_HANDLER(ActionEvent e){
		KarelTable.getInstance().addInstructionsCode(KarelCode.WHILESTATEMENT);
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.WHILESTATEMENT);
			return;
		}
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}
	
	public static final void END_WHILE_BUTTON_HANDLER(ActionEvent e){
	}
	
	public static final void TASK_BUTTON_HANDLER(ActionEvent e){
	}

	public static final void LOOP_BUTTON_HANDLER(ActionEvent e){
		KarelTable.getInstance().addInstructionsCode(KarelCode.LOOPSTATEMENT);
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.NUMBERS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.NUMBERS_TAB_VALUE);
	}
	
	public static final void END_LOOP_BUTTON_HANDLER(ActionEvent e){
	}
	
	/**
	 * ConditionalsTab.java
	 */
	public static final void FRONT_IS_CLEAR_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FRONT_IS_CLEAR_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.FRONTISCLEAR);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FRONTISCLEAR);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void NEXT_TO_A_FRIEND_BUTTON_HANDLER(ActionEvent e){
		System.out.println("NEXT_TO_A_FRIEND_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.NEXTTOAFRIEND);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.NEXTTOAFRIEND);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void FACING_NORTH_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FACING_NORTH_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.FACINGNORTH);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FACINGNORTH);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void FACING_SOUTH_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FACING_SOUTH_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.FACINGSOUTH);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FACINGSOUTH);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void FACING_EAST_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FACING_EAST_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.FACINGEAST);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FACINGEAST);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void FACING_WEST_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FACING_WEST_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.FACINGWEST);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.FACINGWEST);

		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void BAG_IS_EMPTY_BUTTON_HANDLER(ActionEvent e){
		System.out.println("BAG_IS_EMPTY_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
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
	public static final void MOVE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("MOVE_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.MOVE);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.MOVE);
	}
	
	public static final void SLEEP_BUTTON_HANDLER(ActionEvent e){
		System.out.println("SLEEP_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.SLEEP);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.SLEEP);
	}
	
	public static final void WAKE_UP_BUTTON_HANDLER(ActionEvent e){
		System.out.println("WAKE_UP_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.WAKEUP);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.WAKEUP);
	}
	
	public static final void TURN_LEFT_BUTTON_HANDLER(ActionEvent e){
		System.out.println("TURN_LEFT_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.TURNLEFT);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.TURNLEFT);
	}
	
	public static final void PICK_UP_BAMBOO_BUTTON_HANDLER(ActionEvent e){
		System.out.println("PICK_UP_BAMBOO_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.PICKBAMBOO);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.PICKBAMBOO);
	}
	
	public static final void PUT_BAMBOO_BUTTON_HANDLER(ActionEvent e){
		System.out.println("PUT_BAMBOO_BUTTON_HANDLER");
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(KarelCode.PUTBAMBOO);
			return;
		}
		KarelTable.getInstance().addCode(KarelCode.PUTBAMBOO);
	}
	
	public static final void NUMBERS_BUTTON_HANDLER(ActionEvent e){
		System.out.println("NUMBERS_BUTTON_HANDLER");
		String value = ((Button)e.getSource()).getText();
		if(KarelTable.getInstance().isREPLACE_BUTTON_ON()){
			KarelTable.getInstance().replaceCode(value);
			return;
		}
		KarelTable.getInstance().addCode(value);
		
		GameTabs.getInstance().disableTab(GameTabs.NUMBERS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void GridWorld_BUTTON_HANDLER(ActionEvent e){
	}
	
	public static final void BACK_BUTTON_HANDLER(ActionEvent e){
		System.out.println("BACK_BUTTON_HANDLER CALLED");
	}
	
	public static final void FORWARD_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FORWARD_BUTTON_HANDLER CALLED");
	}
	
	public static final void PLAY_BUTTON_HANDLER(ActionEvent e){
		System.out.println("PLAY_BUTTON_HANDLER CALLED");
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode(); 
		World world = SandboxScene.getWorld(); 
		System.out.println(KarelTable.getInstance().getKarelCode());
		Interpreter interpreter = new Interpreter(karelCode, world);
		world.printWorld();
		interpreter.start(); //starts the code
		world.printWorld();
	}

	public static final void RESET_BUTTON_HANDLER(ActionEvent e){
		System.out.println("RESET_BUTTON_HANDLER CALLED");
	}
	
	public static final void REPLACE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("REPLACE_BUTTON_HANDLER CALLED");
	}
	
	public static final void QUIT_MENU_HANDLER(ActionEvent e){
		System.out.println("Quit sandbox mode. Returned to home screen.");
		MainApp.changeScenes(MainMenuScene.getInstance());
	}
	
	public static final void SAVE_MENU_HANDLER(ActionEvent e){
		System.out.println("Saved!");
	}
}
