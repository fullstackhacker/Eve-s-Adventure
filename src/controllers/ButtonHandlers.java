package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Coordinate;
import models.campaign.Campaign;
import models.campaign.KarelCode;
import models.campaign.Level;
import models.campaign.World;
import models.gridobjects.creatures.Creature;
import models.gridobjects.items.Bamboo;
import models.gridobjects.items.Item;
import models.gridobjects.items.Shrub;
import models.gridobjects.items.Tree;
import views.MainApp;
import views.Running;
import views.grid.GridWorld;
import views.karel.KarelTable;
import views.scenes.AdventureModeScene;
import views.scenes.LoadCampaignScene;
import views.scenes.LoadMenuScene;
import views.scenes.LoadMenuSceneA;
import views.scenes.LoadSessionScene;
import views.scenes.MainMenuScene;
import views.scenes.NewCampaignScene;
import views.scenes.SandboxScene;
import views.tabs.GameTabs;
import views.tabs.InstructionsTab;
import views.tips.ProTips;

/**
 * 
 * @author Anthony Wong
 * @author Megan Murray
 * @author Neil Patel
 * @author Carmine Iannaccone
 * @author Mushaheed Kapadia
 *
 */
public final class ButtonHandlers {

	private static boolean sandbox = false;

	public static boolean isSandboxMode() {
		return sandbox;
	}

	public static final void SANDBOX_MODE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("SANDBOX_MODE_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuScene.getInstance());
		sandbox = true;
	}

	public static final void ADVENTURE_MODE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("ADVENTURE_MODE_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuSceneA.getInstance());
		sandbox = false;
	}

	public static final void LOAD_SESSION_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("LOAD_SESSION_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadSessionScene.getInstance());
	}
	
	public static final void LOAD_SANDBOX_SESSION_BUTTON_HANDLER(ActionEvent e){
		System.out.println("LOAD_SANDBOX_SESSION_BUTTON_HANDLER CALLED");
		String levelName = LoadSessionScene.getInstance().getSelectedWorld(); 
		
		System.out.println("WORLD TO LOAD: " + levelName); 
		
		Level level = Load.loadLevel(levelName, null);
	
		SandboxScene.getInstance().setWorld(level.getWorld());
		//SandboxScene.karelTable.setKarelCode(level.getKarelCode());
		MainApp.changeScenes(SandboxScene.getInstance()); 
		try {
			SandboxScene.SandboxPane.getInstance().add(
					GameTabs.getInstance(), 0, 1, 1, 3);
			SandboxScene.SandboxPane.getInstance().add(
					KarelTable.getInstance(), 1, 1, 1, 3);
			SandboxScene.SandboxPane.getInstance().add(
					ProTips.getInstance(), 0, 4, 2, 1);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					GridWorld.gridButtons[i][j].setDisable(false);
				}
			}
			SandboxScene.SandboxPane.getInstance().add(
					SandboxScene.gridWorld, 3, 3, 4, 2);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		GameTabs.getInstance().getTabs()
				.remove(GameTabs.getInstance().CREATURES_TAB);
		GameTabs.getInstance().getTabs()
				.remove(GameTabs.getInstance().ITEMS_TAB);
		GameTabs.getInstance().getTabs()
				.add(GameTabs.getInstance().CREATURES_TAB);
		GameTabs.getInstance().getTabs()
				.add(GameTabs.getInstance().ITEMS_TAB);
	}

	public static final void NEW_SESSION_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("NEW_SESSION_BUTTON_HANDLER CALLED");
		if (sandbox) {
			MainApp.changeScenes(SandboxScene.getInstance());
			try {
				SandboxScene.SandboxPane.getInstance().add(
						GameTabs.getInstance(), 0, 1, 1, 3);
				SandboxScene.SandboxPane.getInstance().add(
						KarelTable.getInstance(), 1, 1, 1, 3);
				SandboxScene.SandboxPane.getInstance().add(
						ProTips.getInstance(), 0, 4, 2, 1);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 10; j++) {
						GridWorld.gridButtons[i][j].setDisable(false);
					}
				}
				SandboxScene.SandboxPane.getInstance().add(
						SandboxScene.gridWorld, 3, 3, 4, 2);

			} catch (Exception e1) {
				System.out.println("Sandbox.add() Exception Caught");
				//e1.printStackTrace();
			}
			GameTabs.getInstance().getTabs()
					.remove(GameTabs.getInstance().CREATURES_TAB);
			GameTabs.getInstance().getTabs()
					.remove(GameTabs.getInstance().ITEMS_TAB);
			GameTabs.getInstance().getTabs()
					.add(GameTabs.getInstance().CREATURES_TAB);
			GameTabs.getInstance().getTabs()
					.add(GameTabs.getInstance().ITEMS_TAB);
		} else {
			MainApp.changeScenes(AdventureModeScene.getInstance());
			try {
				AdventureModeScene.AdventureModePane.getInstance().add(
						GameTabs.getInstance(), 0, 1, 1, 3);
				AdventureModeScene.AdventureModePane.getInstance().add(
						KarelTable.getInstance(), 1, 1, 1, 3);
				/*AdventureModeScene.AdventureModePane.getInstance().add(
						ProTips.getInstance(), 0, 4, 1, 1);*/
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 10; j++) {
						GridWorld.gridButtons[i][j].setDisable(true);
					}
				}
				AdventureModeScene.AdventureModePane.getInstance().add(
						AdventureModeScene.gridWorld, 3, 3, 4, 2);
				AdventureModeScene.AdventureModePane.getInstance().add(
						AdventureModeScene.objective, 0, 4, 1, 1);
			} catch (Exception e2) {
				System.out.println("AdventureMode.add() Exception Caught");
				//e2.printStackTrace();
			}
			GameTabs.getInstance().getTabs()
					.remove(GameTabs.getInstance().CREATURES_TAB);
			GameTabs.getInstance().getTabs()
					.remove(GameTabs.getInstance().ITEMS_TAB);
		}
	}

	public static final void LOAD_CAMPAIGN_ADVENTURE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("LOAD_CAMPAIGN_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadCampaignScene.getInstance());
	}
	
	
	public static final void NEW_CAMPAIGN_ADVENTURE_BUTTON_HANDLER(ActionEvent e){
		System.out.println("NEW_CAMPAIGN_ADVENTURE_BUTTON_HANDLER");
		
		String campaignName = NewCampaignScene.getInstance().sessionsListView.getSelectionModel().getSelectedItem();
		
		System.out.println("campaignName = " + campaignName);
		Campaign campaign = Load.loadCampaign(campaignName); 
		
		if(campaign == null){
			System.out.println("campaign == null");
			return;
		}
		
		if(campaign.getLevels() != null && !campaign.getLevels().isEmpty()){
			System.out.println(campaign.getLevels().size());
			if(campaign.getLevels().get(0) == null){
				System.out.println("ERROR: getWorld == null");
				return;
			}
			AdventureModeScene.setWorld(campaign.getLevels().get(0).getWorld());
			GridWorld.getInstance().setWorld(campaign.getLevels().get(0).getWorld());
		}else{
			System.out.println("ERROR: campaign.getLevel == null");
		}
		
		MainApp.changeScenes(AdventureModeScene.getInstance());
		try {
			AdventureModeScene.AdventureModePane.getInstance().add(
					GameTabs.getInstance(), 0, 1, 1, 3);
			AdventureModeScene.AdventureModePane.getInstance().add(
					KarelTable.getInstance(), 1, 1, 1, 3);
			/*AdventureModeScene.AdventureModePane.getInstance().add(
					ProTips.getInstance(), 0, 4, 1, 1);*/
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					GridWorld.gridButtons[i][j].setDisable(true);
				}
			}
			AdventureModeScene.AdventureModePane.getInstance().add(
					AdventureModeScene.gridWorld, 3, 3, 4, 2);
			AdventureModeScene.AdventureModePane.getInstance().add(
					AdventureModeScene.objective, 0, 4, 1, 1);
			AdventureModeScene.setWorld(campaign.getLevels().get(0).getWorld());
			AdventureModeScene.getInstance().gridWorld = new GridWorld();
			//AdventureModeScene.getInstance().gridWorld.world = 
		} catch (Exception e2) {
			System.out.println("AdventureMode.add() Exception Caught");
			//e2.printStackTrace();
		}
		GameTabs.getInstance().getTabs()
				.remove(GameTabs.getInstance().CREATURES_TAB);
		GameTabs.getInstance().getTabs()
				.remove(GameTabs.getInstance().ITEMS_TAB);
	}
	
	//load campaign 
	public static final void LOAD_ADVENTURE_SESSION_BUTTON_HANDLER(ActionEvent e){
		System.out.println("LOAD_ADVENTURE_SESSION_BUTTON_HANDLER");
		
		String campaignName = LoadCampaignScene.getInstance().getSelectedCampaign();
		
		System.out.println("campaignName = " + campaignName);
		Campaign campaign = Load.loadCampaign(campaignName); 
		
		if(campaign.getLevels() != null && !campaign.getLevels().isEmpty()){
			System.out.println(campaign.getLevels().size());
			if(campaign.getLevels().get(0) == null){
				System.out.println("ERROR: getWorld == null");
				return;
			}
			
			if(campaign.getLevels().get(0).getKarelCode() != null){
				System.out.println(campaign.getLevels().get(0).getKarelCode());
				KarelTable.getInstance().setKarelCode(campaign.getLevels().get(0).getKarelCode());
			}
			AdventureModeScene.setWorld(campaign.getLevels().get(0).getWorld());
			GridWorld.getInstance().setWorld(campaign.getLevels().get(0).getWorld());
		}else{
			System.out.println("ERROR: campaign.getLevel == null");
		}
		
		MainApp.changeScenes(AdventureModeScene.getInstance());
		try {
			AdventureModeScene.AdventureModePane.getInstance().add(
					GameTabs.getInstance(), 0, 1, 1, 3);
			AdventureModeScene.AdventureModePane.getInstance().add(
					KarelTable.getInstance(), 1, 1, 1, 3);
			/*AdventureModeScene.AdventureModePane.getInstance().add(
					ProTips.getInstance(), 0, 4, 1, 1);*/
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					GridWorld.gridButtons[i][j].setDisable(true);
				}
			}
			AdventureModeScene.AdventureModePane.getInstance().add(
					AdventureModeScene.gridWorld, 3, 3, 4, 2);
			AdventureModeScene.AdventureModePane.getInstance().add(
					AdventureModeScene.objective, 0, 4, 1, 1);
		} catch (Exception e2) {
			System.out.println("AdventureMode.add() Exception Caught");
			//e2.printStackTrace();
		}
		GameTabs.getInstance().getTabs()
				.remove(GameTabs.getInstance().CREATURES_TAB);
		GameTabs.getInstance().getTabs()
				.remove(GameTabs.getInstance().ITEMS_TAB);
	}

	public static final void NEW_CAMPAIGN_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("NEW_CAMPAIGN_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(NewCampaignScene.getInstance());
	}

	public static final void CANCEL_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("CANCEL_BUTTON_HANDLER CALLED");
		MainApp.changeScenes(LoadMenuScene.getInstance());
	}
	
	public static final void CANCEL_BUTTON_HANDLERA(ActionEvent e){
		System.out.println("CANCEL_BUTTON_HANDLERA CALLED");
		MainApp.changeScenes(LoadMenuSceneA.getInstance());
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
			ArrayList<String> karelCode = KarelTable.getInstance()
					.getKarelCode();
			for (; line < karelCode.size(); line++) {
				if (karelCode.get(line).equals(KarelCode.ENDWHILE)) {
					KarelTable.getInstance().replaceInstructions(start, line,
							KarelCode.IFSTATEMENT);
					break;
				}
			}
		} else {
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
			ArrayList<String> karelCode = KarelTable.getInstance()
					.getKarelCode();
			for (; line < karelCode.size(); line++) {
				if (karelCode.get(line).equals(KarelCode.ENDIF)) {
					KarelTable.getInstance().replaceInstructions(start, line,
							KarelCode.WHILESTATEMENT);
					break;
				}
			}
		} else {
			KarelTable.getInstance().addInstructionsCode(
					KarelCode.WHILESTATEMENT);
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

		if (ButtonHandlers.isSandboxMode()) {
			SandboxScene.PLAY.setDisable(false);
		} else {
			AdventureModeScene.PLAY.setDisable(false);
		}

		int line = KarelTable.getInstance().getLineSelectedLine();
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		String nextLine = karelCode.get(line + 1);

		switch (nextLine) {
		case KarelCode.FRONTISCLEAR:
		case KarelCode.NEXTTOAFRIEND:
		case KarelCode.FACINGNORTH:
		case KarelCode.FACINGSOUTH:
		case KarelCode.FACINGEAST:
		case KarelCode.FACINGWEST:
			KarelTable.getInstance().replaceCode(line + 1,
					KarelCode.FRONTISCLEAR);
			return;
		default:
			break;
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

		if (ButtonHandlers.isSandboxMode()) {
			SandboxScene.PLAY.setDisable(false);
		} else {
			AdventureModeScene.PLAY.setDisable(false);
		}

		int line = KarelTable.getInstance().getLineSelectedLine();
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		String nextLine = karelCode.get(line + 1);

		switch (nextLine) {
		case KarelCode.FRONTISCLEAR:
		case KarelCode.NEXTTOAFRIEND:
		case KarelCode.FACINGNORTH:
		case KarelCode.FACINGSOUTH:
		case KarelCode.FACINGEAST:
		case KarelCode.FACINGWEST:
			KarelTable.getInstance().replaceCode(line + 1,
					KarelCode.NEXTTOAFRIEND);
			return;
		default:
			break;
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

		if (ButtonHandlers.isSandboxMode()) {
			SandboxScene.PLAY.setDisable(false);
		} else {
			AdventureModeScene.PLAY.setDisable(false);
		}

		int line = KarelTable.getInstance().getLineSelectedLine();
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		String nextLine = karelCode.get(line + 1);

		switch (nextLine) {
		case KarelCode.FRONTISCLEAR:
		case KarelCode.NEXTTOAFRIEND:
		case KarelCode.FACINGNORTH:
		case KarelCode.FACINGSOUTH:
		case KarelCode.FACINGEAST:
		case KarelCode.FACINGWEST:
			KarelTable.getInstance().replaceCode(line + 1,
					KarelCode.FACINGNORTH);
			return;
		default:
			break;
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

		if (ButtonHandlers.isSandboxMode()) {
			SandboxScene.PLAY.setDisable(false);
		} else {
			AdventureModeScene.PLAY.setDisable(false);
		}

		int line = KarelTable.getInstance().getLineSelectedLine();
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		String nextLine = karelCode.get(line + 1);

		switch (nextLine) {
		case KarelCode.FRONTISCLEAR:
		case KarelCode.NEXTTOAFRIEND:
		case KarelCode.FACINGNORTH:
		case KarelCode.FACINGSOUTH:
		case KarelCode.FACINGEAST:
		case KarelCode.FACINGWEST:
			KarelTable.getInstance().replaceCode(line + 1,
					KarelCode.FACINGSOUTH);
			return;
		default:
			break;
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

		if (ButtonHandlers.isSandboxMode()) {
			SandboxScene.PLAY.setDisable(false);
		} else {
			AdventureModeScene.PLAY.setDisable(false);
		}

		int line = KarelTable.getInstance().getLineSelectedLine();
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		String nextLine = karelCode.get(line + 1);

		switch (nextLine) {
		case KarelCode.FRONTISCLEAR:
		case KarelCode.NEXTTOAFRIEND:
		case KarelCode.FACINGNORTH:
		case KarelCode.FACINGSOUTH:
		case KarelCode.FACINGEAST:
		case KarelCode.FACINGWEST:
			KarelTable.getInstance()
					.replaceCode(line + 1, KarelCode.FACINGEAST);
			return;
		default:
			break;
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

		if (ButtonHandlers.isSandboxMode()) {
			SandboxScene.PLAY.setDisable(false);
		} else {
			AdventureModeScene.PLAY.setDisable(false);
		}

		int line = KarelTable.getInstance().getLineSelectedLine();
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		String nextLine = karelCode.get(line + 1);

		switch (nextLine) {
		case KarelCode.FRONTISCLEAR:
		case KarelCode.NEXTTOAFRIEND:
		case KarelCode.FACINGNORTH:
		case KarelCode.FACINGSOUTH:
		case KarelCode.FACINGEAST:
		case KarelCode.FACINGWEST:
			KarelTable.getInstance()
					.replaceCode(line + 1, KarelCode.FACINGWEST);
			return;
		default:
			break;
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

		if (ButtonHandlers.isSandboxMode()) {
			SandboxScene.PLAY.setDisable(false);
		} else {
			AdventureModeScene.PLAY.setDisable(false);
		}

		int line = KarelTable.getInstance().getLineSelectedLine();
		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		String nextLine = karelCode.get(line + 1);

		switch (nextLine) {
		case KarelCode.FRONTISCLEAR:
		case KarelCode.NEXTTOAFRIEND:
		case KarelCode.FACINGNORTH:
		case KarelCode.FACINGSOUTH:
		case KarelCode.FACINGEAST:
		case KarelCode.FACINGWEST:
			KarelTable.getInstance()
					.replaceCode(line + 1, KarelCode.BAGISEMPTY);
			return;
		default:
			break;
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
	 * Popup for if bad collision on placement occurs
	 * Bad collision = Eve & Friend, Bamboo & Tree, Shrub & Tree
	 * Asks to replace object or just cancel
	 * 
	 * @newObject the object the user is trying to put into the square
	 */
	public static void popup(String newObject, String oldObject) {
		
		
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
				//Bad collision = Eve & Friend, Bamboo & Tree, Shrub & Tree
				//remove oldObject
				if ( (oldObject.equals("Tree") || oldObject.equals("Shrub")
						|| oldObject.equals("Bamboo")) )
					RMITEM_BUTTON_HANDLER(null);
				else if (oldObject.equals("Friend"))
					RMCREATURE_BUTTON_HANDLER(null);

				//add newObject
				Coordinate cords = new Coordinate(GridWorld.getXCoordinate(), GridWorld.getYCoordinate());
				switch (newObject) {
				case "Tree":
					Tree tree = new Tree(4);
					tree.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					if (GridWorld.getInstance().getWorld() == null)
						System.out.println("Uninitalized world");
					GridWorld.getInstance().getWorld().addItem(tree);
					
					//front end
					GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					                              					.getYCoordinate()].setGraphic(SandboxScene.getTreeI());
					break;
				case "Shrub":
					Shrub shrub = new Shrub(4, false);
					shrub.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					if (GridWorld.getInstance().getWorld() == null)
						System.out.println("Uninitalized world");
					
					GridWorld.getInstance().getWorld().addItem(shrub);
					GridWorld.getInstance().getWorld().printWorld();
					//front
					GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					                              					.getYCoordinate()].setGraphic(SandboxScene.getShrubI());
					break;
				case "Bamboo":
					Bamboo bamboo = new Bamboo(4);
					bamboo.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					if (GridWorld.getInstance().getWorld() == null)
						System.out.println("Uninitalized world");
					
					GridWorld.getInstance().getWorld().addItem(bamboo);
					GridWorld.getInstance().getWorld().printWorld();
					//front
					GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					                              					.getYCoordinate()].setGraphic(SandboxScene.getBambooI());
				case "Friend":
					Creature Friend = new Creature("Friend", cords);
					Friend.setCoordinates(new Coordinate(GridWorld
							.getXCoordinate(), GridWorld.getYCoordinate()));
					if (GridWorld.getInstance().getWorld() == null)
						System.out.println("Uninitalized world");
					GridWorld.getInstance().getWorld().addCreature(Friend);
					GridWorld.getInstance().getWorld().printWorld();
					GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					                              					.getYCoordinate()].setGraphic(SandboxScene.getFriendI());
				case "Eve":
					Creature eve = GridWorld.getInstance().getWorld().getEve();
					GridWorld.getInstance().getWorld().findEve();
					Coordinate eveLocation = eve.getCoordinates();
					eve.setCoordinates(cords);
					GridWorld.getInstance().getWorld().addCreature(eve);
					
					
					// front end
					GridWorld.gridButtons[cords.getX()][cords
							.getY()].setGraphic(GridWorld.gridButtons[eveLocation
							.getX()][eveLocation.getY()].getGraphic());
					GridWorld.gridButtons[eveLocation.getX()][eveLocation.getY()]
							.setGraphic(null);
					GridWorld.getInstance().getWorld().removeCreature(eveLocation);
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
		Coordinate currentPosition = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		if (!GridWorld.getInstance().getWorld().hasItem(currentPosition))
			return;
		// remove from backend
		GridWorld.getInstance().getWorld().removeItem(currentPosition);
		// remove from frontend
		GridWorld.gridButtons[currentPosition.getX()][currentPosition.getY()]
				.setGraphic(null);
	}

	public static final void RMWALL_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("RMWALL_BUTTON_HANDLER");
		Coordinate cords = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		GridWorld.getInstance().getWorld().removeWall(cords, Coordinate.UP);
		GridWorld.getInstance().getWorld().removeWall(cords, Coordinate.DOWN);
		GridWorld.getInstance().getWorld().removeWall(cords, Coordinate.LEFT);
		GridWorld.getInstance().getWorld().removeWall(cords, Coordinate.RIGHT);
		GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].setId("WorldButton");
	}

	// TODO
	public static final void WALLT_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("WALLT_BUTTON_HANDLER");
		Coordinate cords = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		if (GridWorld.getInstance().getWorld().hasDownWall(cords)
				|| GridWorld.getInstance().getWorld().hasUpWall(cords)
				|| GridWorld.getInstance().getWorld().hasLeftWall(cords)
				|| GridWorld.getInstance().getWorld().hasRightWall(cords)) {
			GridWorld.getInstance().getWorld().removeWall(cords, Coordinate.UP);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.DOWN);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.LEFT);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.RIGHT);
		}
		GridWorld.getInstance().getWorld().addWall(cords, Coordinate.UP);
		GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].setId("upWall");

	}

	public static final void WALLB_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("WALLB_BUTTON_HANDLER");
		Coordinate cords = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		if (GridWorld.getInstance().getWorld().hasDownWall(cords)
				|| GridWorld.getInstance().getWorld().hasUpWall(cords)
				|| GridWorld.getInstance().getWorld().hasLeftWall(cords)
				|| GridWorld.getInstance().getWorld().hasRightWall(cords)) {
			GridWorld.getInstance().getWorld().removeWall(cords, Coordinate.UP);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.DOWN);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.LEFT);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.RIGHT);
		}
		GridWorld.getInstance().getWorld().addWall(cords, Coordinate.DOWN);
		GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].setId("downWall");
	}

	public static final void WALLL_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("WALLL_BUTTON_HANDLER");
		Coordinate cords = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		if (GridWorld.getInstance().getWorld().hasDownWall(cords)
				|| GridWorld.getInstance().getWorld().hasUpWall(cords)
				|| GridWorld.getInstance().getWorld().hasLeftWall(cords)
				|| GridWorld.getInstance().getWorld().hasRightWall(cords)) {
			GridWorld.getInstance().getWorld().removeWall(cords, Coordinate.UP);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.DOWN);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.LEFT);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.RIGHT);
		}
		GridWorld.getInstance().getWorld().addWall(cords, Coordinate.LEFT);
		GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].setId("leftWall");
	}

	public static final void WALLR_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("WALLR_BUTTON_HANDLER");
		Coordinate cords = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		if (GridWorld.getInstance().getWorld().hasDownWall(cords)
				|| GridWorld.getInstance().getWorld().hasUpWall(cords)
				|| GridWorld.getInstance().getWorld().hasLeftWall(cords)
				|| GridWorld.getInstance().getWorld().hasRightWall(cords)) {
			GridWorld.getInstance().getWorld().removeWall(cords, Coordinate.UP);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.DOWN);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.LEFT);
			GridWorld.getInstance().getWorld()
					.removeWall(cords, Coordinate.RIGHT);
		}
		GridWorld.getInstance().getWorld().addWall(cords, Coordinate.RIGHT);
		GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
				.getYCoordinate()].setId("rightWall");
	}

	//shrub on Eve: can co-exist, add shrub
	//shrub on Friend: can co-exist, add shrub
	//shrub on bamboo: add shrub that contains bamboo
	//shrub on tree: cannot co-exist, ask to replace
	public static final void SHRUB_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("SHRUB_BUTTON_HANDLER");
		Coordinate currentPosition = new Coordinate(GridWorld.getXCoordinate(), GridWorld.getYCoordinate());
		//if there is a creature, would change graphic to something where you see both of them
		//if there is item
		if (GridWorld.getInstance().getWorld().hasItem(currentPosition)){
			Item oldObject = GridWorld.getInstance().getWorld().itemAt(currentPosition);
			if (oldObject instanceof Tree)
			popup("Shrub", "Tree");
		} 
		else{
			System.out.println("IN THE ELSE");
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setGraphic(SandboxScene.getShrubI());
		Shrub shrub = new Shrub(4, false);
		shrub.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate()));
		if (GridWorld.getInstance().getWorld() == null)
			System.out.println("Uninitalized world");
		GridWorld.getInstance().getWorld().addItem(shrub);
		GridWorld.getInstance().getWorld().printWorld();
	}
}
	//Tree on Eve - go
	//Tree on Friend - go
	//Tree on Shrub - ask to replace
	//Tree on Bamboo - ask to replace
	public static final void TREE_BUTTON_HANDLER(ActionEvent e) {
		Coordinate currentPosition = new Coordinate(GridWorld.getXCoordinate(), GridWorld.getYCoordinate());
		//if there is a creature, would change graphic to something where you see both of them
		//if there is item
		if (GridWorld.getInstance().getWorld().hasItem(currentPosition)){
			Item oldObject = GridWorld.getInstance().getWorld().itemAt(currentPosition);

			if (oldObject instanceof Shrub)
			popup("Tree", "Shrub");
			else if (oldObject instanceof Bamboo)
				popup("Tree", "Bamboo");
		} 
			else {
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setGraphic(SandboxScene.getTreeI());

			Tree tree = new Tree(4);
			tree.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
					GridWorld.getYCoordinate()));
			if (GridWorld.getInstance().getWorld() == null)
				System.out.println("Uninitalized world");
			GridWorld.getInstance().getWorld().addItem(tree);
			GridWorld.getInstance().getWorld().printWorld();

		}
	}

//Bamboo on Eve - fine
//Bamboo on Friend - fine
//Bamboo on Tree - ask to replace
//Bamboo on Shrub - increment value of bamboo in that shrub, let user know this happened
	public static final void BAMBOO_BUTTON_HANDLER(ActionEvent e) {
		GridWorld.getInstance().getWorld().incrementBambooObjective();
		System.out.println("BAMBOO_BUTTON_HANDLER");
		Coordinate currentPosition = new Coordinate(GridWorld.getXCoordinate(), GridWorld.getYCoordinate());
		//if there is a creature, would change graphic to something where you see both of them
		if(GridWorld.getInstance().getWorld().hasCreature(currentPosition)){
			Bamboo bamboo = new Bamboo(4);
			bamboo.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
					GridWorld.getYCoordinate()));
			GridWorld.getInstance().getWorld().addItem(bamboo);
			return;
		}
		
		//if there is item
		if (GridWorld.getInstance().getWorld().hasItem(currentPosition)){
			Item oldObject = GridWorld.getInstance().getWorld().itemAt(currentPosition);

			if (oldObject instanceof Tree)
				popup("Bamboo", "Tree");
			if (oldObject instanceof Shrub)
				popup("Bamboo", "Shrub");
			
			return;
			
		} 
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setGraphic(SandboxScene.getBambooI());
			Bamboo bamboo = new Bamboo(4);
			bamboo.setCoordinates(new Coordinate(GridWorld.getXCoordinate(),
					GridWorld.getYCoordinate()));
			
			if (GridWorld.getInstance().getWorld() == null)
				System.out.println("Uninitalized world");
			
			GridWorld.getInstance().getWorld().addItem(bamboo);
			GridWorld.getInstance().getWorld().printWorld();
		}
	

	/**
	 * CreaturesTab.java
	 */
	public static final void RMCREATURE_BUTTON_HANDLER(ActionEvent e) {
		Coordinate currentPosition = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		if (!GridWorld.getInstance().getWorld().hasCreature(currentPosition))
			return;
		//backend
		GridWorld.getInstance().getWorld().removeCreature(currentPosition);
		// remove from frontend
		GridWorld.gridButtons[currentPosition.getX()][currentPosition.getY()]
				.setGraphic(null);
	}

	public static final void EVE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("EVE_BUTTON_HANDLER");
		Coordinate currentPosition = new Coordinate(GridWorld.getXCoordinate(),
				GridWorld.getYCoordinate());
		// eve not in world yet
		if (GridWorld.getInstance().getWorld().getEve() == null) {
			Creature eve = new Creature("Eve", currentPosition);
			GridWorld.getInstance().getWorld().addCreature(eve);
			GridWorld.gridButtons[currentPosition.getX()][currentPosition.getY()]
					.setGraphic(SandboxScene.EveDown);
			return;
		}
		// she is in the world somewhere, find her
		Creature eve = GridWorld.getInstance().getWorld().getEve();
		Coordinate eveLocation = eve.getCoordinates();
		// check if there is creature in the new space already
		if (GridWorld.getInstance().getWorld().hasCreature(currentPosition)) {
			Creature creature = GridWorld.getInstance().getWorld()
					.creatureAt(currentPosition);
			if (creature.isEve()) {
				return;
			} else { // friend is there
				popup("Eve","Friend");
			}
		}// check if there is an Item in the new space already
		else if (GridWorld.getInstance().getWorld().hasItem(currentPosition) && !(GridWorld.getInstance().getWorld().itemAt(currentPosition) instanceof Bamboo)) {
			Item item = GridWorld.getInstance().getWorld().itemAt(currentPosition);
			if (item instanceof Tree){
				popup("Eve", "Tree");
			}
			if(item instanceof Shrub){
				popup("Eve", "Shrub");
			}
		}
		//That space was empty, make the change
		else {
			eve.setCoordinates(currentPosition);
			GridWorld.getInstance().getWorld().addCreature(eve);
			GridWorld.getInstance().getWorld().removeCreature(eveLocation);
			GridWorld.getInstance().getWorld().findEve();
			// front end
			GridWorld.gridButtons[currentPosition.getX()][currentPosition
					.getY()].setGraphic(GridWorld.gridButtons[eveLocation
					.getX()][eveLocation.getY()].getGraphic());
			GridWorld.gridButtons[eveLocation.getX()][eveLocation.getY()]
					.setGraphic(null);
		}
		
		if(GridWorld.getInstance().getWorld().hasItem(eveLocation)){
			if(GridWorld.getInstance().getWorld().itemAt(eveLocation) instanceof Bamboo){
				GridWorld.gridButtons[eveLocation.getX()][eveLocation.getY()].setGraphic(SandboxScene.getBambooI());
			}
			if(GridWorld.getInstance().getWorld().itemAt(eveLocation) instanceof Shrub){
				GridWorld.gridButtons[eveLocation.getX()][eveLocation.getY()].setGraphic(SandboxScene.getShrubI());
			}
			if(GridWorld.getInstance().getWorld().itemAt(eveLocation) instanceof Tree){
				GridWorld.gridButtons[eveLocation.getX()][eveLocation.getY()].setGraphic(SandboxScene.getTreeI());
			}
		}
		

	}

	//Friend on Eve - ask
	public static final void FRIENDS_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FRIENDS_BUTTON_HANDLER");
		// String oldObject = GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
		//		.getYCoordinate()].getText();

		//if (oldObject.equals("Tree") || oldObject.equals("Shrub")
			///	|| oldObject.equals("Bamboo") || oldObject.equals("Eve!")) {
			//popup("Friend");
		//} else
			GridWorld.gridButtons[GridWorld.getXCoordinate()][GridWorld
					.getYCoordinate()].setGraphic(SandboxScene.getFriendI());
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

	public static final void PAUSE_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("PAUSE_BUTTON_HANDLER CALLED");
		if ((!sandbox)
				&& (AdventureModeScene.PLAY != null)
				&& (AdventureModeScene.PLAY.getGraphic() == AdventureModeScene.imagePlay)) {
			if (AdventureModeScene.getInterpreter() != null) {
				AdventureModeScene.getInterpreter().pause();
				AdventureModeScene.PLAY.setDisable(false);
				isPause = true;
				return;
			}
		} else if ((sandbox) && (SandboxScene.PLAY != null)
				&& (SandboxScene.PLAY.getGraphic() == SandboxScene.imagePlay)) {
			if (SandboxScene.getInterpreter() != null) {
				SandboxScene.getInterpreter().pause();
				SandboxScene.PLAY.setDisable(false);
				isPause = true;
				return;
			}
		}
	}

	public static final void FORWARD_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("FORWARD_BUTTON_HANDLER CALLED");
		if (!sandbox) {
			try {
				AdventureModeScene.AdventureModePane.getInstance()
						.getChildren().remove(AdventureModeScene.gametabs);
				AdventureModeScene.AdventureModePane.getInstance().add(
						Running.getInstance(), 0, 1, 1, 3);
			} catch (IllegalArgumentException e1) {
				// e1.printStackTrace();
			}
		} else if (sandbox) {
			try {
				SandboxScene.SandboxPane.getInstance().getChildren()
						.remove(SandboxScene.gametabs);
				SandboxScene.SandboxPane.getInstance().add(
						Running.getInstance(), 0, 1, 1, 3);
			} catch (IllegalArgumentException e2) {
				// e2.printStackTrace();
			}
		}
		KarelTable.getInstance().DELETE_BUTTON.setDisable(true);
		KarelTable.getInstance().REPLACE_BUTTON.setDisable(true);
		if ((!sandbox)
				&& (AdventureModeScene.PLAY != null)
				&& (AdventureModeScene.PLAY.getGraphic() == AdventureModeScene.imagePlay)) {
			if (AdventureModeScene.getInterpreter() != null) {
				AdventureModeScene.getInterpreter().executeOne();
			}
		} else if ((sandbox) && (SandboxScene.PLAY != null)
				&& (SandboxScene.PLAY.getGraphic() == SandboxScene.imagePlay)) {
			if (SandboxScene.getInterpreter() != null) {
				SandboxScene.getInterpreter().executeOne();
			}
		}
	}

	private static boolean isPause = false;

	public static final void PLAY_BUTTON_HANDLER(ActionEvent e) {
		if (!sandbox) {
			try {
				AdventureModeScene.AdventureModePane.getInstance()
						.getChildren().remove(AdventureModeScene.gametabs);
				AdventureModeScene.AdventureModePane.getInstance().add(
						Running.getInstance(), 0, 1, 1, 3);
			} catch (Exception e1) {
				// e1.printStackTrace();
			}
		} else if (sandbox) {
			try {
				SandboxScene.SandboxPane.getInstance().getChildren()
						.remove(SandboxScene.gametabs);
				SandboxScene.SandboxPane.getInstance().add(
						Running.getInstance(), 0, 1, 1, 3);
			} catch (Exception e2) {
				// e2.printStackTrace();
			}
		}
		KarelTable.getInstance().DELETE_BUTTON.setDisable(true);
		KarelTable.getInstance().REPLACE_BUTTON.setDisable(true);

		ArrayList<String> karelCode = KarelTable.getInstance().getKarelCode();
		if (karelCode.isEmpty()) {
			System.out.println("karelCode is empty!");
			return;
		}

		System.out.println("PLAY_BUTTON_HANDLER CALLED");
		World world = null;
		if (!sandbox) {
			AdventureModeScene.PLAY.setDisable(true);
			if (isPause) {
				// TODO fix!
				AdventureModeScene.getInterpreter().start();
				isPause = false;
				return;
			}
			world = AdventureModeScene.getInstance().getWorld();
			AdventureModeScene
					.setInterpreter(new Interpreter(karelCode, world));
			System.out.println(KarelTable.getInstance().getKarelCode());
			world.printWorld();
			AdventureModeScene.getInterpreter().start(); // starts the code
			AdventureModeScene.PAUSE.setDisable(false);
			AdventureModeScene.FORWARD.setDisable(false);
		} else {
			SandboxScene.PLAY.setDisable(true);
			if (isPause) {
				SandboxScene.getInterpreter().start();
				isPause = false;
				return;
			}
			world = SandboxScene.getWorld();
			SandboxScene.setInterpreter(new Interpreter(karelCode, world));
			System.out.println(KarelTable.getInstance().getKarelCode());
			world.printWorld();
			SandboxScene.getInterpreter().start(); // starts the code
			SandboxScene.PAUSE.setDisable(false);
			SandboxScene.FORWARD.setDisable(false);

		}

		world.printWorld();
		// do{
		// interpreter.executeOne();
		// world.printWorld();
		// } while(interpreter.next());
	}

	// TODO
	public static final void RESET_BUTTON_HANDLER(ActionEvent e) {
		System.out.println("RESET_BUTTON_HANDLER CALLED");
		isPause = false;
		if (!sandbox) {
			try {
				AdventureModeScene.AdventureModePane.getInstance()
						.getChildren().remove(Running.getInstance());
				AdventureModeScene.AdventureModePane.getInstance().add(
						AdventureModeScene.gametabs, 0, 1, 1, 3);
			} catch (Exception e1) {
				// e1.printStackTrace();
			}
		} else if (sandbox) {
			try {
				SandboxScene.SandboxPane.getInstance().getChildren()
						.remove(Running.getInstance());
				SandboxScene.SandboxPane.getInstance().add(
						SandboxScene.gametabs, 0, 1, 1, 3);
			} catch (Exception e2) {
				// e2.printStackTrace();
			}
		}
		KarelTable.getInstance().DELETE_BUTTON.setDisable(false);
		KarelTable.getInstance().REPLACE_BUTTON.setDisable(false);
		if (!sandbox) {
			if (AdventureModeScene.getInterpreter() != null) {
				AdventureModeScene.getInterpreter().reset();
				AdventureModeScene.PLAY.setDisable(false);
				AdventureModeScene.PAUSE.setDisable(true);
				AdventureModeScene.FORWARD.setDisable(true);
			}
		} else {
			SandboxScene.getInterpreter().reset();
			SandboxScene.PLAY.setDisable(false);
			SandboxScene.PAUSE.setDisable(true);
			SandboxScene.FORWARD.setDisable(true);
		}
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
		// World world = AdventureModeScene.getWorld();
		GridWorld.getInstance().getWorld().setFindObj(false);
		SandboxScene.getInstance().topMenu.collect.setDisable(true);
		SandboxScene.getInstance().topMenu.find.setDisable(false);
	}

	public static final void FIND_MENU_HANDLER(ActionEvent e) {
		System.out.println("Added an objective (Find)!");
		// World world = AdventureModeScene.getWorld();
		GridWorld.getInstance().getWorld().setFindObj(true);
		SandboxScene.getInstance().topMenu.collect.setDisable(false);
		SandboxScene.getInstance().topMenu.find.setDisable(true);
	}

	public static final void SAVES_MENU_HANDLER(ActionEvent e) {
		System.out.println("SaveS Called!");
		String DATADIR = "data" + File.separator;

		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text("Name of level:"));
		Scene dialogScene = new Scene(dialogVbox, 400, 450);

		final TextField NAME_TF = new TextField();
		final Label DESCRIPTION_LABEL = new Label("Write a description");
		final TextField DESCRIPTION_TF = new TextField();
		final Button SAVE = new Button("Save");
		final Button CANCEL = new Button("Cancel");

		NAME_TF.setPrefWidth(200);
		NAME_TF.setMaxWidth(200);
		DESCRIPTION_TF.setPrefWidth(200);
		DESCRIPTION_TF.setMaxWidth(200);
		SAVE.setPrefWidth(100);
		SAVE.setMaxWidth(100);
		CANCEL.setPrefWidth(100);
		CANCEL.setMaxWidth(100);

		dialogVbox.getChildren().addAll(NAME_TF, DESCRIPTION_LABEL,
				DESCRIPTION_TF,
				SAVE, CANCEL);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.setPadding(new Insets(10, 10, 10, 10));
		dialog.setScene(dialogScene);
		dialog.show();

		CANCEL.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				dialog.close();
			}
		});

		SAVE.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Clicked on save");
				if (NAME_TF.getText() == null || NAME_TF.getText().equals("")) {
					System.out.println("REQUIRED to enter name");
					return;
				}
				
				//Iterator<String> levelNames = Load.getLevels();
				
//				while(levelNames.hasNext()){
//					if(levelNames.next().equals(NAME_TF.getText())){
//						// TODO: POPUP message asking if user would like to overwrite
//						return;
//					}
//				}
				
				SandboxScene.getWorld().setName(NAME_TF.getText());
				Load.levels.add(new Level(SandboxScene.getWorld(), DESCRIPTION_TF.getText(), KarelTable.getInstance()
						.getKarelCode()));
				Save.saveLevel(new Level(SandboxScene.getWorld(), DESCRIPTION_TF.getText(), KarelTable.getInstance()
						.getKarelCode()), null);
			}
		});
	}

	public static final void SAVEA_MENU_HANDLER(ActionEvent e) {
		String DATADIR = "data" + File.separator;

		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text("Name of level:"));
		Scene dialogScene = new Scene(dialogVbox, 400, 450);

		ArrayList<String> campaigns = new ArrayList<String>();
		
		Iterator<String> campaignsIterator = Load.getCampaigns();
		if(campaignsIterator != null){
			while(campaignsIterator.hasNext())
				campaigns.add(campaignsIterator.next());
		}
		campaigns.add("NEW");
		ObservableList<String> observableList = FXCollections
				.observableList(campaigns);

		ArrayList<String> objectives = new ArrayList<String>();
		objectives.add("Collect all Bamboo");
		objectives.add("Find Friend");
		ObservableList<String> observableListObjectives = FXCollections
				.observableList(objectives);

		final TextField NAME_TF = new TextField();
		final Label DESCRIPTION_LABEL = new Label("Write a description");
		final TextField DESCRIPTION_TF = new TextField();
		final Label CAMPAIGN_LABEL = new Label("Choose a campaign");
		final ComboBox<String> CAMPAIGN_CB = new ComboBox<String>(
				observableList);
		CAMPAIGN_CB.setEditable(true);
		final Label OBJECTIVE_LABEL = new Label(
				"Objective: "
						+ (GridWorld.getInstance().getWorld().getFindObj() == true ? "Find Friend"
								: "Collect all Bamboo"));
		final Button SAVE = new Button("Save");
		final Button CANCEL = new Button("Cancel");

		NAME_TF.setPrefWidth(200);
		NAME_TF.setMaxWidth(200);
		DESCRIPTION_TF.setPrefWidth(200);
		DESCRIPTION_TF.setMaxWidth(200);
		CAMPAIGN_CB.setPrefWidth(200);
		CAMPAIGN_CB.setMaxWidth(200);
		SAVE.setPrefWidth(100);
		SAVE.setMaxWidth(100);
		CANCEL.setPrefWidth(100);
		CANCEL.setMaxWidth(100);

		dialogVbox.getChildren().addAll(NAME_TF, DESCRIPTION_LABEL,
				DESCRIPTION_TF, CAMPAIGN_LABEL, CAMPAIGN_CB, OBJECTIVE_LABEL,
				SAVE, CANCEL);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.setPadding(new Insets(10, 10, 10, 10));
		dialog.setScene(dialogScene);
		dialog.show();

		CANCEL.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				dialog.close();
			}
		});

		SAVE.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Clicked on save");

				if (NAME_TF.getText() == null || NAME_TF.getText().equals("")) {
					System.out.println("REQUIRED to enter name");
					return;
				}

				if (CAMPAIGN_CB.getValue().equals("NEW")) {
					System.out.println("Selected value is NEW");

					final Stage campaign_dialog = new Stage();
					campaign_dialog.initModality(Modality.APPLICATION_MODAL);
					VBox vbox = new VBox(20);
					vbox.getChildren().add(new Text("Name of new campaign:"));
					Scene dialScene = new Scene(vbox, 400, 300);

					final TextField NAMECAMPAIGN_TF = new TextField();
					final Label DESCRIPTION_LABEL = new Label(
							"Campaign description:");
					final TextField DESCCAMPAIGN_TF = new TextField();
					final Button SUBMIT = new Button("SUBMIT");
					final Button CANCEL = new Button("CANCEL");

					NAMECAMPAIGN_TF.setPrefWidth(200);
					NAMECAMPAIGN_TF.setMaxWidth(200);
					DESCCAMPAIGN_TF.setPrefWidth(200);
					DESCCAMPAIGN_TF.setMaxWidth(200);
					SUBMIT.setPrefWidth(100);
					SUBMIT.setMaxWidth(100);
					CANCEL.setPrefWidth(100);
					CANCEL.setMaxWidth(100);

					vbox.getChildren().addAll(NAMECAMPAIGN_TF,
							DESCRIPTION_LABEL, DESCCAMPAIGN_TF, SUBMIT, CANCEL);
					vbox.setAlignment(Pos.CENTER);
					vbox.setPadding(new Insets(10, 10, 10, 10));
					campaign_dialog.setScene(dialScene);
					campaign_dialog.show();

					CANCEL.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							campaign_dialog.close();
						}
					});

					SUBMIT.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							observableList.add(NAMECAMPAIGN_TF.getText());
							CAMPAIGN_CB.setValue(NAMECAMPAIGN_TF.getText());
							Campaign campaign = new Campaign(NAMECAMPAIGN_TF
									.getText(), "Default Description");
							Save.saveCampaign(campaign);
							Load.campaigns.add(campaign);
							campaign_dialog.close();
						}
					});
				} else {
					// Add campaign
					for (int i = 0; i < Load.campaigns.size(); i++) {
						if (Load.campaigns.get(i).getName()
								.equals(CAMPAIGN_CB.getValue())) {
							System.out.println("NAEMTF: " + NAME_TF.getText());
							
							SandboxScene.getWorld().setName(NAME_TF.getText());
							Level level = new Level(SandboxScene.getWorld(),
									DESCRIPTION_TF.getText());
							level.getWorld().setFindObj(
									GridWorld.getInstance().getWorld()
											.getFindObj());
							Load.campaigns.get(i).addLevel(level);
							Save.saveCampaign(Load.campaigns.get(i));
							break;
						}
					}

					dialog.close();
				}
			}
		});

	}

	public static final void SAVE_MENU_HANDLER(ActionEvent e) {

	}
}
