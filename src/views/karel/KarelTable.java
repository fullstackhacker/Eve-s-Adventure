package views.karel;

import java.util.ArrayList;


import java.util.Collections;
import java.util.List;

import com.sun.javafx.sg.prism.NGShape.Mode;

import controllers.ButtonHandlers;
import models.campaign.KarelCode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;
import views.scenes.AdventureModeScene;
import views.scenes.SandboxScene;
import views.tabs.GameTabs;
import views.tabs.InstructionsTab;

public final class KarelTable extends GridPane {

	public static Button REPLACE_BUTTON = new Button("REPLACE");
	public static Button DELETE_BUTTON = new Button("DELETE");
	
	private static final KarelTable instant = new KarelTable();

	private ObservableList<String> karelCode;
	
	private ListView<String> listView;
	
	private boolean REPLACE_BUTTON_ON = false;
	
	
	private int line;
	
	private KarelTable() {
		this.karelCode = FXCollections.observableArrayList();
		this.listView = new ListView<String>(karelCode);
		
		//REPLACE_BUTTON = new Button("REPLACE");
		//DELETE_BUTTON = new Button("DELETE");
		
		this.add(listView, 0, 0, 2, 1);
		this.add(REPLACE_BUTTON, 0, 1);
		this.add(DELETE_BUTTON, 1, 1);
		
		/* Drag and Drop */
		final IntegerProperty dragFromIndex = new SimpleIntegerProperty(-1);
		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>(){

            @Override
            public ListCell<String> call(ListView<String> lv) {
                final ListCell<String> cell = new ListCell<String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item,  empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item);
                        }
                    }
                };
                
                cell.setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                    	listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                        if (! cell.isEmpty()) {
                        	if(cell.getText().matches(".*\\d.*")){
                        		return;
                        	}
                        	switch(cell.getText()){
                        		case "ADD CODE HERE":
		                		case KarelCode.ELSESTATEMENT:
                        		case KarelCode.FRONTISCLEAR:
                        		case KarelCode.NEXTTOAFRIEND:
                        		case KarelCode.FACINGNORTH:
                        		case KarelCode.FACINGSOUTH:
                        		case KarelCode.FACINGEAST:
                        		case KarelCode.FACINGWEST:
                        		case KarelCode.ENDIF:
		                		case KarelCode.ENDELSE:
		                		case KarelCode.ENDLOOP:
		                		case KarelCode.ENDWHILE:
                        			return;
		                		case KarelCode.IFSTATEMENT:
		                		case KarelCode.WHILESTATEMENT:
		                		case KarelCode.LOOPSTATEMENT:
		                			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		                			int i = cell.getIndex();
		                			for(; i < karelCode.size(); i++){
		                				listView.getSelectionModel().select(i);
		                				if(karelCode.get(i).equals(KarelCode.ENDIF) || 
		                						karelCode.get(i).equals(KarelCode.ENDWHILE) ||
		                						karelCode.get(i).equals(KarelCode.ENDLOOP)){
		                					System.out.println(listView.getSelectionModel().getSelectedIndices());
		                					if(i + 1 < karelCode.size() && 
		                							karelCode.get(i + 1).equals(KarelCode.ELSESTATEMENT)){
		                						for(int z = i + 1; z < karelCode.size(); z++){
		                							listView.getSelectionModel().select(z);
		                							if(karelCode.get(z).equals(KarelCode.ENDELSE)){
		                								break;
		                							}
		                						}
		                					}
		                					break;
		                				}
		                				
		                			}
		                			break;
                        	}
                        	
                            dragFromIndex.set(cell.getIndex());
                            Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);
                            ClipboardContent cc = new ClipboardContent();
                            cc.putString(cell.getItem());
                            db.setContent(cc);
                            // Java 8 only:
//                          db.setDragView(cell.snapshot(null, null));
                        }
                    }
                });

                cell.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                    	if(!cell.isEmpty()){
                    		if(listView.getSelectionModel().getSelectedIndices().contains(cell.getIndex())){
                    			return;
                    		}
	                    	switch(cell.getText()){
		                    	case "ADD CODE HERE":
		                		case KarelCode.FRONTISCLEAR:
		                		case KarelCode.NEXTTOAFRIEND:
		                		case KarelCode.FACINGNORTH:
		                		case KarelCode.FACINGSOUTH:
		                		case KarelCode.FACINGEAST:
		                		case KarelCode.FACINGWEST:
		                		case KarelCode.ELSESTATEMENT:
		                		case KarelCode.ZERO:
		                		case KarelCode.ONE:
		                		case KarelCode.TWO:
		                		case KarelCode.THREE:
		                		case KarelCode.FOUR:
		                		case KarelCode.FIVE:
		                		case KarelCode.SIX:
		                		case KarelCode.SEVEN:
		                		case KarelCode.EIGHT:
		                		case KarelCode.NINE:
		                			return;
	                    	}
	                        if (dragFromIndex.get() >= 0 && dragFromIndex.get() != cell.getIndex()) {
	                            event.acceptTransferModes(TransferMode.MOVE);
	                        }
                    	}
                    }
                });

                // highlight drop target by changing background color:
                cell.setOnDragEntered(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                    	if(!cell.isEmpty()){
                    		if(listView.getSelectionModel().getSelectedIndices().contains(cell.getIndex())){
                    			return;
                    		}
                    		switch(cell.getText()){
                    		//TODO FIX this shit
		                    	case "ADD CODE HERE":
		                		case KarelCode.FRONTISCLEAR:
		                		case KarelCode.NEXTTOAFRIEND:
		                		case KarelCode.FACINGNORTH:
		                		case KarelCode.FACINGSOUTH:
		                		case KarelCode.FACINGEAST:
		                		case KarelCode.FACINGWEST:
		                		case KarelCode.ZERO:
		                		case KarelCode.ONE:
		                		case KarelCode.TWO:
		                		case KarelCode.THREE:
		                		case KarelCode.FOUR:
		                		case KarelCode.FIVE:
		                		case KarelCode.SIX:
		                		case KarelCode.SEVEN:
		                		case KarelCode.EIGHT:
		                		case KarelCode.NINE:
		                			return;
	                    	}
	                        if (dragFromIndex.get() >= 0 && dragFromIndex.get() != cell.getIndex()) {
	                            // should really set a style class and use an external style sheet,
	                            // but this works for demo purposes:
	                            cell.setStyle("-fx-background-color: gold;");
	                        }
                    	}
                    }
                });

                // remove highlight:
                cell.setOnDragExited(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        cell.setStyle("");
                    }
                });

                cell.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {

                        int dragItemsStartIndex ;
                        int dragItemsEndIndex ;
                        int direction ;
                        if (cell.isEmpty()) {
                        	System.out.println("true");
                            dragItemsStartIndex = dragFromIndex.get();
                            dragItemsEndIndex = listView.getItems().size();
                            direction = -1;
                        } else {
                            if (cell.getIndex() < dragFromIndex.get()) {
                            	System.out.println("IF");
                                dragItemsStartIndex = cell.getIndex();
                                dragItemsEndIndex = dragFromIndex.get() + 1 ;
                                direction = 1 ;
                                line = dragItemsStartIndex;
                            } else {
                            	System.out.println("ELSE");
                                dragItemsStartIndex = dragFromIndex.get();
                                direction = -1 ;
                            	if(karelCode.get(cell.getIndex()).equals(KarelCode.IFSTATEMENT) || 
                            			karelCode.get(cell.getIndex()).equals(KarelCode.WHILESTATEMENT) ||
                            			karelCode.get(cell.getIndex()).equals(KarelCode.LOOPSTATEMENT)){
                                    dragItemsEndIndex = cell.getIndex() + 2 ;
                            	}else{
                            		dragItemsEndIndex = cell.getIndex() + 1 ;
                            	}
                            	line = dragItemsEndIndex - 1;
                            }
                        }
                        
                        System.out.println("dragItemsStartIndex: " + dragItemsStartIndex);
                        System.out.println("dragItemsEndIndex: " + dragItemsEndIndex);
                        if(listView.getSelectionModel().getSelectionMode() == SelectionMode.MULTIPLE){
                        	System.out.println(listView.getSelectionModel().getSelectedIndices());
                        	List<String> copy = new ArrayList<>(listView.getSelectionModel().getSelectedItems());
                        	
                        	int beginngingLine = listView.getSelectionModel().getSelectedIndices().get(0);
                        	System.out.println("beginngingLine: " + beginngingLine);
                        	
                        	int enddingLine = listView.getSelectionModel().getSelectedIndices().get(copy.size()-1);
                    		System.out.println("endingLine: " + enddingLine);
                        	
                    		System.out.println("times:" + (enddingLine - beginngingLine));
                    		
                    		int times = enddingLine - beginngingLine + 1;
                    		
                        	if(beginngingLine > line){
                        		/* Moving the code upwards */
                        		for(int i = 0; i < times; i++){
                        			karelCode.remove(beginngingLine);
                        		}
                        	
                        		for(int i = copy.size()-1; i >= 0 ;i--){
                        			karelCode.add(line, copy.get(i));
                        		}
                        		
                        	}else{
                        		/* Moving the code downwards */
                        		int newLine = line - times + 1;
                        		
                        		for(int i = 0; i < times; i++){
                        			karelCode.remove(beginngingLine);
                        		}
                        		
                        		for(int i = copy.size()-1; i >= 0 ;i--){
                        			karelCode.add(newLine, copy.get(i));
                        		}
                        		
                        		line = newLine;
                        		System.out.println("newLine: " + newLine);
                        	}
                        	return;
                        }
                        
                        List<String> rotatingItems = listView.getItems().subList(dragItemsStartIndex, dragItemsEndIndex);
                        List<String> rotatingItemsCopy = new ArrayList<>(rotatingItems);
                        Collections.rotate(rotatingItemsCopy, direction);
                        rotatingItems.clear();
                        rotatingItems.addAll(rotatingItemsCopy);
                        dragFromIndex.set(-1);
                    }
                });

                cell.setOnDragDone(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                    	dragFromIndex.set(-1);
                    	System.out.println("line: " + line);
                    	System.out.println(listView.getSelectionModel().getSelectedIndex());
                        listView.getSelectionModel().clearAndSelect(line);
                    }
                });
                return cell ;
            }
			
		});
		
		//REPLACE_BUTTON.setOnAction(ButtonHandlers::REPLACE_BUTTON_HANDLER);
		REPLACE_BUTTON.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				System.out.println("REPLACE_BUTTON CALLED");
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
						InstructionsTab.LOOP_BUTTON.setDisable(true);
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
				System.out.println("DELETE_BUTTON CALLED");
				String item = listView.getSelectionModel().getSelectedItem();
				if(item != null){
					System.out.println("item: " + item);
					switch(item){
						case KarelCode.ENDIF:
						case KarelCode.ENDELSE:
						case KarelCode.ENDWHILE:
						case KarelCode.ENDLOOP:
							/* Fine the beginning of the block */
							System.out.println(true);
							for(int i = listView.getSelectionModel().getSelectedIndex(); i >= 0; i--){
								String code = karelCode.get(i);
								System.out.println("For Loop: " + code);
								if(code.equals(KarelCode.IFSTATEMENT) || 
									code.equals(KarelCode.ELSESTATEMENT) || 
									code.equals(KarelCode.WHILESTATEMENT) || 
									code.equals(KarelCode.LOOPSTATEMENT)){
									if(ButtonHandlers.isSandboxMode()){
										SandboxScene.PLAY.setDisable(false);
									}else{
										AdventureModeScene.PLAY.setDisable(false);
									}
									listView.getSelectionModel().clearAndSelect(i);
									System.out.println("i == " + i);
									break;
								}
							}
						case KarelCode.IFSTATEMENT:
						case KarelCode.ELSESTATEMENT:
						case KarelCode.WHILESTATEMENT:
						case KarelCode.LOOPSTATEMENT:
							/* Delete the whole code block */
							System.out.println("Called");
							int line = listView.getSelectionModel().getSelectedIndex();
							System.out.println("line: " + line);
							for(; line < karelCode.size();){
								String code = listView.getSelectionModel().getSelectedItem();
								if(code.equals(KarelCode.ENDIF) || code.equals(KarelCode.ENDELSE) || code.equals(KarelCode.ENDWHILE) || code.equals(KarelCode.ENDLOOP)){
									break;
								}
								karelCode.remove(line);
								listView.getSelectionModel().clearAndSelect(line);
							}
							if(ButtonHandlers.isSandboxMode()){
								SandboxScene.PLAY.setDisable(false);
							}else{
								AdventureModeScene.PLAY.setDisable(false);
							}
							/* Remove last line of code */
							karelCode.remove(line);
							return;
						case KarelCode.FRONTISCLEAR:
						case KarelCode.FACINGNORTH:
						case KarelCode.FACINGSOUTH:
						case KarelCode.FACINGEAST:
						case KarelCode.FACINGWEST:
							if(ButtonHandlers.isSandboxMode()){
								SandboxScene.PLAY.setDisable(true);
							}else{
								AdventureModeScene.PLAY.setDisable(true);
							}
					default:
							karelCode.remove(listView.getSelectionModel().getSelectedIndex());
							break;
					}
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
						
						GameTabs.getInstance().disableTab(GameTabs.CREATURES_TAB_VALUE);
						GameTabs.getInstance().disableTab(GameTabs.ITEMS_TAB_VALUE);
						GameTabs.getInstance().disableTab(GameTabs.NUMBERS_TAB_VALUE);
						
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
								GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
								GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
								return;
							case KarelCode.ENDIF:
								GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
								GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
								GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
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
	
	public void replaceCode(int line, String code){
		this.REPLACE_BUTTON_ON = false;
	
		this.karelCode.add(line, code);
		this.listView.getSelectionModel().clearAndSelect(line);
		this.karelCode.remove(line + 1);
	
	}
	
	/**
	 * 
	 * @param start 
	 * @param end
	 * @param instruc Current Instructions
	 */
	public void replaceInstructions(int start, int end, String instruc){
		System.out.println("replaceInstructions CALLED");
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		this.REPLACE_BUTTON_ON = false;
		InstructionsTab.ELSE_BUTTON.setDisable(false);
		this.karelCode.remove(end);
		this.karelCode.remove(start);
		
		String beginning = "";
		String ending = "";
		
		switch(instruc){
			case KarelCode.IFSTATEMENT:
				/* While Loop */
				/* IF Statement */
				beginning = KarelCode.IFSTATEMENT;
				ending = KarelCode.ENDIF;
				break;
			case KarelCode.WHILESTATEMENT:
				beginning = KarelCode.WHILESTATEMENT;
				ending = KarelCode.ENDWHILE;
				break;
		}
		
		this.karelCode.add(start, beginning);
		this.karelCode.add(end, ending);
		System.out.println(this.karelCode);
		this.listView.getSelectionModel().clearAndSelect(this.listView.getSelectionModel().getSelectedIndex() + 1);
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
				break;
				/* Do Nothing */
		}
	}
	
	public void setSelectedIndex(int index){
		if(!karelCode.isEmpty() && (index >= 0 && index < karelCode.size())){
			this.listView.getSelectionModel().clearAndSelect(index);
		}
	}

	public ArrayList<String> getKarelCode(){
		ArrayList<String> karelCodeArrayList = new ArrayList<String>();
		
		for(String code : this.karelCode){
			karelCodeArrayList.add(code);
		}
		
		return karelCodeArrayList;
	}
	
	public void setKarelCode(ArrayList<String> karelCode){
		for(String code : karelCode){
			this.karelCode.add(code);
		}
	}
	
	public int getLineSelectedLine(){
		return this.listView.getSelectionModel().getSelectedIndex();
	}
	
	public static KarelTable getInstance() {
		return instant;
	}
}
