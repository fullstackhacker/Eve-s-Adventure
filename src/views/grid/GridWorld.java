package views.grid;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import models.Coordinate;
import models.campaign.World;
import models.gridobjects.items.Bamboo;
import models.gridobjects.items.Shrub;
import models.gridobjects.items.Tree;
import views.scenes.AdventureModeScene;
import views.scenes.SandboxScene;
import views.tabs.GameTabs;

public final class GridWorld extends GridPane {

	private static GridWorld instant = null;

	public static ToggleButton[][] gridButtons = null;

	private World world;

	public GridWorld() {
		this.getStylesheets().add(GridWorld.class.getResource("/sandbox_style.css").toExternalForm());

		this.setGridLinesVisible(true);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(20);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(20);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(20);
		this.getColumnConstraints().addAll(column1, column2, column3, column4,
				column5);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(10);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(10);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(10);
		RowConstraints row5 = new RowConstraints();
		row5.setPercentHeight(10);
		RowConstraints row6 = new RowConstraints();
		row6.setPercentHeight(10);
		RowConstraints row7 = new RowConstraints();
		row7.setPercentHeight(10);
		RowConstraints row8 = new RowConstraints();
		row8.setPercentHeight(10);
		RowConstraints row9 = new RowConstraints();
		row9.setPercentHeight(10);
		RowConstraints row10 = new RowConstraints();
		row10.setPercentHeight(10);
		this.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6,
				row7, row8, row9, row10);

		final ToggleGroup group = new ToggleGroup();
		group.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle toggle, Toggle new_toggle) {
						if (new_toggle == null) {
							GameTabs.getInstance().enableTab(
									GameTabs.OPERATIONS_TAB_VALUE);
							GameTabs.getInstance().enableTab(
									GameTabs.INSTRUCTIONS_TAB_VALUE);
							GameTabs.getInstance().disableTab(
									GameTabs.CREATURES_TAB_VALUE);
							GameTabs.getInstance().disableTab(
									GameTabs.ITEMS_TAB_VALUE);
							GameTabs.getInstance().switchTab(
									GameTabs.OPERATIONS_TAB_VALUE);
						} else {
							GameTabs.getInstance().disableTab(
									GameTabs.OPERATIONS_TAB_VALUE);
							GameTabs.getInstance().disableTab(
									GameTabs.INSTRUCTIONS_TAB_VALUE);
							GameTabs.getInstance().disableTab(
									GameTabs.CONDITIONS_TAB_VALUE);
							GameTabs.getInstance().disableTab(
									GameTabs.NUMBERS_TAB_VALUE);
							GameTabs.getInstance().enableTab(
									GameTabs.CREATURES_TAB_VALUE);
							GameTabs.getInstance().enableTab(
									GameTabs.ITEMS_TAB_VALUE);
							GameTabs.getInstance().switchTab(
									GameTabs.CREATURES_TAB_VALUE);
							getXCoordinate();
							getYCoordinate();
						}
					}
				});

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {
		            if(new_toggle == null){
		            	GameTabs.getInstance().enableTab(GameTabs.OPERATIONS_TAB_VALUE);
						GameTabs.getInstance().enableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		        		GameTabs.getInstance().disableTab(GameTabs.CREATURES_TAB_VALUE);
		        		GameTabs.getInstance().disableTab(GameTabs.ITEMS_TAB_VALUE);
						GameTabs.getInstance().switchTab(GameTabs.OPERATIONS_TAB_VALUE);
		            }else{
		            	GameTabs.getInstance().disableTab(GameTabs.OPERATIONS_TAB_VALUE);
		        		GameTabs.getInstance().disableTab(GameTabs.INSTRUCTIONS_TAB_VALUE);
		        		GameTabs.getInstance().disableTab(GameTabs.CONDITIONS_TAB_VALUE);
		        		GameTabs.getInstance().disableTab(GameTabs.NUMBERS_TAB_VALUE);
		        		GameTabs.getInstance().enableTab(GameTabs.CREATURES_TAB_VALUE);
		        		GameTabs.getInstance().enableTab(GameTabs.ITEMS_TAB_VALUE);
		        		GameTabs.getInstance().switchTab(GameTabs.CREATURES_TAB_VALUE);
		        		getXCoordinate();
		        		getYCoordinate();
		            }
		         }
		});
		
		gridButtons = new ToggleButton[5][10];

		System.out.println("OVERWERITINEG BUTTONGS");

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				gridButtons[i][j] = new ToggleButton();
				GridPane.setHalignment(gridButtons[i][j], HPos.CENTER);
				GridPane.setHgrow(gridButtons[i][j], Priority.ALWAYS);
				GridPane.setVgrow(gridButtons[i][j], Priority.ALWAYS);
				gridButtons[i][j]
						.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				gridButtons[i][j].setId("WorldButton");
				gridButtons[i][j].setToggleGroup(group);

				this.add(gridButtons[i][j], i, j);

			}
		}

		//gridButtons[2][2].setText("Eve!");

		// gridButtons[2][2].setText("Eve!");

		// this.getChildren().addAll(GridWorld.gridButtons);

		this.setPadding(new Insets(5, 5, 5, 5));
	}

	public void setWorld(World world) {
		this.world = world;
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 10; j++){
				if(this.world.itemAt(new Coordinate(i, j)) != null){
					if(this.world.itemAt(new Coordinate(i, j)) instanceof Bamboo){
						gridButtons[i][j].setGraphic(new ImageView(new Image(SandboxScene.class.getResourceAsStream("/Images/bamboo.png"))));
					}
					if(this.world.itemAt(new Coordinate(i, j)) instanceof Shrub){
						gridButtons[i][j].setGraphic(new ImageView(new Image(SandboxScene.class.getResourceAsStream("/Images/bush.png"))));
					}					
					if(this.world.itemAt(new Coordinate(i, j)) instanceof Tree){
						gridButtons[i][j].setGraphic(new ImageView(new Image(SandboxScene.class.getResourceAsStream("/Images/tree.png"))));
					}						
				}else if(getWorld().creatureAt(new Coordinate(i, j)) != null){
					if(this.world.creatureAt(new Coordinate(i, j)).isEve()){
						switch(this.world.creatureAt(new Coordinate(i, j)).getDirection()){
						case Coordinate.UP:
							gridButtons[i][j].setGraphic(SandboxScene.getEveDownI());
							break;
						case Coordinate.DOWN:
							gridButtons[i][j].setGraphic(SandboxScene.getEveUpI());
							break;
						case Coordinate.RIGHT:
							gridButtons[i][j].setGraphic(SandboxScene.getEveRightI());
							break;
						case Coordinate.LEFT:
							gridButtons[i][j].setGraphic(SandboxScene.getEveLeftI());
							break;
						}
					}else{
						gridButtons[i][j].setGraphic(new ImageView(new Image(SandboxScene.class.getResourceAsStream("/Images/friend.png"))));
					}
				}
			}
		}
	}

	public World getWorld() {
		return world;
	}

	public static int getXCoordinate(){
		for(int y = 0; y < gridButtons.length; y++){
			for(int x = 0; x < gridButtons[y].length; x++){
				if (GridWorld.gridButtons[y][x].isSelected()){
					System.out.println("X: " + y);
					return y;
				}
			}
		}
		System.out.println("no X coordinate");
		return 0;
	}
	
	public static int getYCoordinate(){
		for(int y = 0; y < gridButtons.length; y++){
			for(int x = 0; x < gridButtons[y].length; x++){
				if (GridWorld.gridButtons[y][x].isSelected()){
					System.out.println("Y: " +  x);
					return x;
				}
			}
    	}

		System.out.println("no Y coordinate");
		return 0;
	}

	public static GridWorld getInstance() {
		return (instant == null) ? instant = new GridWorld() : instant;
	}
}
