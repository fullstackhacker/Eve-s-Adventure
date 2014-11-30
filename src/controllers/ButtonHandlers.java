package controllers;

import models.campaign.KarelCode;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import views.MainApp;
import views.karel.KarelTable;
import views.scenes.LoadMenuScene;
import views.scenes.MainMenuScene;
import views.scenes.SandboxScene;
import views.tabs.GameTabs;
import views.tabs.InstructionsTab;
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
	}
	
	public static final void NEW_SESSION_BUTTON_HANDLER(ActionEvent e){
		System.out.println("NEW_SESSION_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(SandboxScene.getInstance());
	}
	
	/**
	 * InstuctionsTab.java
	 */
	public static final void IF_BUTTON_HANDLER(ActionEvent e){
		System.out.println("IF_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.IFSTATEMENT);
		GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
		
		InstructionsTab.END_IF_BUTTON.setVisible(true);
	}
	
	public static final void END_IF_BUTTON_HANDLER(ActionEvent e){
		System.out.println("END_IF_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.ENDIFSTATEMENT);
		InstructionsTab.END_IF_BUTTON.setVisible(false);
	}
	
	public static final void ELSE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("ELSE_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.ELSESTATEMENT);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
		
		InstructionsTab.END_ELSE_BUTTON.setVisible(true);
	}
	
	public static final void END_ELSE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("END_ELSE_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.ENDELSESTATEMENT);
		InstructionsTab.END_ELSE_BUTTON.setVisible(false);
	}
	
	public static final void WHILE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("WHILE_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.WHILESTATEMENT);
		GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	
		InstructionsTab.END_WHILE_BUTTON.setVisible(true);
	}
	
	public static final void END_WHILE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("END_WHILE_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.ENDWHILESTATEMENT);
		InstructionsTab.END_WHILE_BUTTON.setVisible(false);
	}
	
	public static final void TASK_BUTTON_HANDLER(ActionEvent e){
		System.out.println("TASK_BUTTON_HANDLER CALLED");

		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}

	public static final void LOOP_BUTTON_HANDLER(ActionEvent e){
		System.out.println("LOOP_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.LOOPSTATEMENT);
		GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
		
		InstructionsTab.END_LOOP_BUTTON.setVisible(true);
	}
	
	public static final void END_LOOP_BUTTON_HANDLER(ActionEvent e){
		System.out.println("END_LOOP_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.ENDLOOPSTATEMENT);
		InstructionsTab.END_LOOP_BUTTON.setVisible(false);
	}
	
	/**
	 * ConditionalsTab.java
	 */
	
	public static final void FRONT_IS_CLEAR_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FRONT_IS_CLEAR_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.FRONTISCLEAR);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void NEXT_TO_A_FRIEND_BUTTON_HANDLER(ActionEvent e){
		System.out.println("NEXT_TO_A_FRIEND_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.NEXTTOAFRIEND);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void FACING_NORTH_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FACING_NORTH_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.FACINGNORTH);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void FACING_SOUTH_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FACING_SOUTH_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.FACINGSOUTH);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}

	public static final void FACING_EAST_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FACING_EAST_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.FACINGEAST);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void FACING_WEST_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FACING_WEST_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.FACINGWEST);
		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void BAG_IS_EMPTY_BUTTON_HANDLER(ActionEvent e){
		System.out.println("BAG_IS_EMPTY_BUTTON_HANDLER CALLED");
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
		System.out.println("MOVE_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.MOVE);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void SLEEP_BUTTON_HANDLER(ActionEvent e){
		System.out.println("SLEEP_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.SLEEP);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void WAKE_UP_BUTTON_HANDLER(ActionEvent e){
		System.out.println("WAKE_UP_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.WAKEUP);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void TURN_LEFT_BUTTON_HANDLER(ActionEvent e){
		System.out.println("TURN_LEFT_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.TURNLEFT);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void PICK_UP_BAMBOO_BUTTON_HANDLER(ActionEvent e){
		System.out.println("PICK_UP_BAMBOO_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.PICKBAMBOO);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void PUT_BAMBOO_BUTTON_HANDLER(ActionEvent e){
		System.out.println("PUT_BAMBOO_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.PUTBAMBOO);
		GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
	}
	
	public static final void BACK_BUTTON_HANDLER(ActionEvent e){
		System.out.println("BACK_BUTTON_HANDLER CALLED");
	}
	
	public static final void FORWARD_BUTTON_HANDLER(ActionEvent e){
		System.out.println("FORWARD_BUTTON_HANDLER CALLED");
	}
	
	public static final void PLAY_BUTTON_HANDLER(ActionEvent e){
		System.out.println("PLAY_BUTTON_HANDLER CALLED");
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
}
