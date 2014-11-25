package controllers;

import javafx.event.ActionEvent;
import views.MainApp;
import views.scenes.LoadMenuScene;
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
	
	public static final void IF_BUTTON_HANDLER(ActionEvent e){
		System.out.println("IF_BUTTON_HANDLER CALLED");
	}
	
	public static final void ELSE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("ELSE_BUTTON_HANDLER CALLED");
	}
	
	public static final void WHILE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("WHILE_BUTTON_HANDLER CALLED");
	}
	
	public static final void TASK_BUTTON_HANDLER(ActionEvent e){
		System.out.println("TASK_BUTTON_HANDLER CALLED");
	}

	public static final void LOOP_BUTTON_HANDLER(ActionEvent e){
		System.out.println("LOOP_BUTTON_HANDLER CALLED");
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
}
