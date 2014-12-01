package views.karel;

import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.RE;

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
import views.tabs.InstructionsTab;

public final class KarelTable extends GridPane {

	private static final KarelTable instant = new KarelTable();

	private ObservableList<String> karelCode;
	
	private ListView<String> listView;
	
	private boolean REPLACE_BUTTON_ON = false;
	
	private KarelTable() {
		this.karelCode = FXCollections.observableArrayList("ADD CODE HERE");
		this.listView = new ListView<String>(karelCode);
		
		this.listView.getSelectionModel().clearAndSelect(0);
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
				
				REPLACE_BUTTON_ON = true;
				
				if(code.matches("-?\\d+(\\.\\d+)?")){
					System.out.println("Works");
					GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
					GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
					GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
					GameTabs.getInstance().enableTab(GameTabs.NUMBERS_TAB_VALUE);
					GameTabs.getInstance().switchTab(GameTabs.NUMBERS_TAB_VALUE);
					return;
				}
				
				//TODO
				switch(code){
					/* Instructions */
					case KarelCode.IFSTATEMENT:
					case KarelCode.WHILESTATEMENT:
						GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
						GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
						GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
						GameTabs.getInstance().switchTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
						break;
					/* Conditionals */
					case KarelCode.FRONTISCLEAR:
					case KarelCode.NEXTTOAFRIEND:
					case KarelCode.FACINGNORTH:
					case KarelCode.FACINGSOUTH:
					case KarelCode.FACINGEAST:
					case KarelCode.FACINGWEST:
					case KarelCode.BAGISEMPTY:
						GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
						GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
						GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
						GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
						break;
					/* Operations */
					default:
						GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
						GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
						GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
						GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
				}
			}
			
		});
		
		DELETE_BUTTON.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String item = listView.getSelectionModel().getSelectedItem();
				if(item != null){
					//TODO Conditonals
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
						
						if(newValue == null){
							return;
						}
						
						if(REPLACE_BUTTON_ON == true){
							REPLACE_BUTTON_ON = false;
						}
						
						InstructionsTab.ELSE_BUTTON.setVisible(false);
						
						switch(newValue){
							case "ADD CODE HERE":
								REPLACE_BUTTON.setDisable(true);
								DELETE_BUTTON.setDisable(true);
								return;
							case KarelCode.ENDIF:
								InstructionsTab.ELSE_BUTTON.setVisible(true);
								break;
							case KarelCode.IFSTATEMENT:
							case KarelCode.WHILESTATEMENT:
								GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
								GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.CONDITIONS_TAB_VALUE);
								GameTabs.getInstance().switchTab(GameTabs.CONDITIONS_TAB_VALUE);
								break;
							case KarelCode.LOOPSTATEMENT:
								GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
								GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
								GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.NUMBERS_TAB_VALUE);
								GameTabs.getInstance().switchTab(GameTabs.NUMBERS_TAB_VALUE);
								break;
							default:
								GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
								GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
								break;

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
	
	public boolean isREPLACE_BUTTON_ON(){
		return this.REPLACE_BUTTON_ON;
	}
	
	public void replaceCode(String code){
		this.REPLACE_BUTTON_ON = false;
		
		this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 1, code);
		this.listView.getSelectionModel().clearAndSelect(this.listView.getSelectionModel().getSelectedIndex() + 1);
		this.karelCode.remove(this.listView.getSelectionModel().getSelectedIndex() - 1);
	}

	/**
	 * Adding a piece a code to the Karel Table
	 * @param code Karel Codel
	 */
	public void addCode(String code){
		this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 1, code);
		this.listView.getSelectionModel().clearAndSelect(this.listView.getSelectionModel().getSelectedIndex() + 1);
	}
	
	public void addInstructionsCode(String code){
		switch(code){
			case KarelCode.IFSTATEMENT:
				this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 1 , code);
				this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 2 , KarelCode.ENDIF);
				this.listView.getSelectionModel().clearAndSelect(this.listView.getSelectionModel().getSelectedIndex() + 1);
				break;
			case KarelCode.ELSESTATEMENT:
				this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 1 , code);
				this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 2 , KarelCode.ENDELSE);
				this.listView.getSelectionModel().clearAndSelect(this.listView.getSelectionModel().getSelectedIndex() + 1);
				break;
			case KarelCode.WHILESTATEMENT:
				this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 1 , code);
				this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 2 , KarelCode.ENDWHILE);
				this.listView.getSelectionModel().clearAndSelect(this.listView.getSelectionModel().getSelectedIndex() + 1);
				break;
			case KarelCode.LOOPSTATEMENT:
				this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 1 , code);
				this.karelCode.add(this.listView.getSelectionModel().getSelectedIndex() + 2 , KarelCode.ENDLOOP);
				this.listView.getSelectionModel().clearAndSelect(this.listView.getSelectionModel().getSelectedIndex() + 1);
				break;
			default:
				/* Do Nothing */
		}
	}

	public ArrayList<String> getKarelCode(){
		ArrayList<String> karelCodeArrayList = new ArrayList<String>();
		
		for(String code : this.karelCode){
			karelCodeArrayList.add(code);
		}
		
		/* Remove Add Code Here */
		karelCodeArrayList.remove(0);
		
		return karelCodeArrayList;
	}
	
	public static KarelTable getInstance() {
		return instant;
	}
}
