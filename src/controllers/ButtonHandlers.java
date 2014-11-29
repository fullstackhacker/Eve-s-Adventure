package controllers;

import models.campaign.KarelCode;
import javafx.event.ActionEvent;
import views.MainApp;
import views.karel.KarelTable;
import views.scenes.LoadMenuScene;
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
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}
	
	public static final void ELSE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("ELSE_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.ELSESTATEMENT);
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}
	
	public static final void WHILE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("WHILE_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.WHILESTATEMENT);
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}
	
	public static final void TASK_BUTTON_HANDLER(ActionEvent e){
		System.out.println("TASK_BUTTON_HANDLER CALLED");

		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}

	public static final void LOOP_BUTTON_HANDLER(ActionEvent e){
		System.out.println("LOOP_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.LOOPSTATEMENT);
		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
	}
	
	/**
	 * ConditionalsTab.java
	 */
	
	/**
	 * OperationsTab.java
	 */
	public static final void MOVE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("MOVE_BUTTON_HANDLER CALLED");
		KarelTable.getInstance().addCode(KarelCode.MOVE);
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
	
	public static final void EDIT_BUTTON_HANDLER(ActionEvent e){
		System.out.println("EDIT_BUTTON_HANDLER CALLED");
	}

	public static final void DELETE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("DELETE_BUTTON_HANDLER CALLED");
	}
	
	public static final void QUIT_MENU_HANDLER(ActionEvent e){
		System.out.println("Quit sandbox mode. Returned to home screen.");
		MainApp.changeScenes(MainMenuScene.getInstance());
	}
}
