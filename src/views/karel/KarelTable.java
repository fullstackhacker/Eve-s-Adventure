package views.karel;

import models.campaign.KarelCode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import views.tabs.GameTabs;

public final class KarelTable extends GridPane {

	private static final KarelTable instant = new KarelTable();

	private ObservableList<String> karelCode;
	
	private ListView<String> listView;
	
	private KarelTable() {
		this.karelCode = FXCollections.observableArrayList("ADD CODE HERE");
		this.listView = new ListView<String>(karelCode);
		
		Button REPLACE_BUTTON = new Button("REPLACE");
		Button DELETE_BUTTON = new Button("DELETE");
		
		this.add(listView, 0, 0, 2, 1);
		this.add(REPLACE_BUTTON, 0, 1);
		this.add(DELETE_BUTTON, 1, 1);
		
		//REPLACE_BUTTON.setOnAction(ButtonHandlers::REPLACE_BUTTON_HANDLER);
		REPLACE_BUTTON.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String code = listView.getSelectionModel().getSelectedItem();
				
				if(code == null){
					return;
				}
				//TODO
				switch(code){
					case KarelCode.IFSTATEMENT:
					case KarelCode.WHILESTATEMENT:
					case KarelCode.LOOPSTATEMENT:
						break;
					case KarelCode.MOVE:
					case KarelCode.SLEEP:
					case KarelCode.WAKEUP:
					case KarelCode.TURNLEFT:
					case KarelCode.PICKBAMBOO:
					case KarelCode.PUTBAMBOO:
						break;
					case KarelCode.FRONTISCLEAR:
					case KarelCode.NEXTTOAFRIEND:
					case KarelCode.FACINGNORTH:
					case KarelCode.FACINGSOUTH:
					case KarelCode.FACINGEAST:
					case KarelCode.FACINGWEST:
						GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
						GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
						GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
						break;
					default:
						/* Do Nothing */
				}
			}
			
		});
		
		DELETE_BUTTON.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String item = listView.getSelectionModel().getSelectedItem();
				if(item != null){
					karelCode.remove(item);
				}
			}
			
		});
		
		this.listView.getSelectionModel().selectedItemProperty().addListener(new 
				ChangeListener<String>(){

			/**
			 * The list would change the tab based on the selected item
			 */
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						System.out.println("Old Value:" + oldValue);
						System.out.println("New Value:" + newValue);
						
						if(newValue == null){
							return;
						}
						
						switch(newValue){
							case "ADD CODE HERE":
								REPLACE_BUTTON.setDisable(true);
								DELETE_BUTTON.setDisable(true);
								return;
							case KarelCode.IFSTATEMENT:
							case KarelCode.WHILESTATEMENT:
							case KarelCode.LOOPSTATEMENT:
								GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
								GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
								GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
							case KarelCode.FRONTISCLEAR:
							case KarelCode.NEXTTOAFRIEND:
							case KarelCode.FACINGNORTH:
							case KarelCode.FACINGSOUTH:
							case KarelCode.FACINGEAST:
							case KarelCode.FACINGWEST:
								GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
								GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
								break;
							default:
								GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
						}
						REPLACE_BUTTON.setDisable(false);
						DELETE_BUTTON.setDisable(false);
					}
		});

		GridPane.setFillWidth(REPLACE_BUTTON, true);
		GridPane.setHalignment(REPLACE_BUTTON, HPos.CENTER);
		GridPane.setFillWidth(DELETE_BUTTON, true);
		GridPane.setHalignment(DELETE_BUTTON, HPos.CENTER);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(50);
		this.getColumnConstraints().addAll(column1, column2);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(95);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(5);
		this.getRowConstraints().addAll(row1, row2);
		
		this.setPadding(new Insets(5, 5, 5, 5));
	}

	/**
	 * Adding a piece a code to the Karel Table
	 * @param code Karel Code
	 */
	public void addCode(String code){
		this.karelCode.add(code);
		this.listView.getSelectionModel().clearSelection(karelCode.size()-1);
		this.listView.getSelectionModel().clearAndSelect(karelCode.size()-1);
		System.out.println(karelCode.size()-1);
	}

	public static KarelTable getInstance() {
		return instant;
	}
}
