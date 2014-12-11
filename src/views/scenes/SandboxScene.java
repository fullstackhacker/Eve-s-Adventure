package views.scenes;

import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import models.Coordinate;
import models.campaign.World;
import models.gridobjects.creatures.Creature;
import views.MainApp;
import views.TopMenu;
import views.grid.Cols;
import views.grid.GridWorld;
import views.grid.GridWorld;
import views.grid.Rows;
import views.karel.KarelTable;
import views.tabs.GameTabs;
import views.tips.ProTips;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import controllers.ButtonHandlers;
import controllers.Interpreter;

public final class SandboxScene extends Scene {

	private static World world;

	public static GridWorld gridWorld;

	public static GameTabs gametabs = null;
	public static ProTips protips = null;
	public static KarelTable karelTable = null;

	//public static ToggleButton Eve = new ToggleButton("Eve!");

	private static Interpreter interpreter;
	
	//public static ToggleButton Eve = new ToggleButton("Eve!");
	
	public static TopMenu topMenu = null;
	
	public static ImageView EveUp = new ImageView(new Image("./Images/eve_up.png"));
	public static ImageView EveRight = new ImageView(new Image("./Images/eve_right.png"));
	public static ImageView EveDown = new ImageView(new Image("./Images/eve_down2.png"));
	public static ImageView EveLeft = new ImageView(new Image("./Images/eve_left.png"));
	public static ImageView Bush = new ImageView(new Image("./Images/bush.png"));
	public static ImageView Bamboo = new ImageView(new Image("./Images/bamboo.png"));

	public static final class SandboxPane extends GridPane {
		

		private static SandboxPane instanceOfMainMenuPane = new SandboxPane();

		private ImageView imageBack = new ImageView(new Image(
				"./Images/ArrowLeft.png"));
		private ImageView imageRight = new ImageView(new Image(
				"./Images/ArrowRight.png"));
		private ImageView imagePlay = new ImageView(new Image(
				"./Images/PlayButton.png"));
		private ImageView imageReset = new ImageView(new Image(
				"./Images/ResetButton.png"));
		
		//private ImageView imageEve = new ImageView(new Image("./Images/Eve.png"));

		private Button BACK, FORWARD, PLAY, RESET;

		private SandboxPane() {
			this.getStylesheets().add("./sandbox_style.css");

			buttonSetup();

			GridPane.setFillWidth(BACK, true);
			GridPane.setHalignment(BACK, HPos.CENTER);
			GridPane.setFillWidth(FORWARD, true);
			GridPane.setHalignment(FORWARD, HPos.CENTER);
			GridPane.setFillWidth(PLAY, true);
			GridPane.setHalignment(PLAY, HPos.CENTER);
			GridPane.setFillWidth(RESET, true);
			GridPane.setHalignment(RESET, HPos.CENTER);

			topMenu = TopMenu.getInstance();
			gametabs = GameTabs.getInstance();
			gametabs.setId("gametabs");
			protips = ProTips.getInstance();
			karelTable = KarelTable.getInstance();
			protips.setId("protips");
			gridWorld = GridWorld.getInstance();

			Rows rows = Rows.getInstance();
			Cols cols = Cols.getInstance();

			this.add(topMenu, 0, 0, 7, 1);
			this.add(BACK, 2, 1, 2, 1);
			this.add(FORWARD, 4, 1);
			this.add(PLAY, 5, 1);
			this.add(RESET, 6, 1);
			//this.add(gametabs, 0, 1, 1, 3);
			//this.add(karelTable, 1, 1, 1, 3);
			this.add(cols, 3, 2, 4, 1);
			this.add(rows, 2, 3, 1, 2);
			this.add(gridWorld, 3, 3, 4, 2);
			//this.add(protips, 0, 4, 2, 1);
			GridPane.setHalignment(rows, HPos.RIGHT);

			ColumnConstraints column1 = new ColumnConstraints();
			column1.setPercentWidth(32);
			ColumnConstraints column2 = new ColumnConstraints();
			column2.setPercentWidth(36);
			ColumnConstraints column3 = new ColumnConstraints();
			column3.setPercentWidth(4);
			ColumnConstraints column4 = new ColumnConstraints();
			column4.setPercentWidth(4);
			ColumnConstraints column5 = new ColumnConstraints();
			column5.setPercentWidth(8);
			ColumnConstraints column6 = new ColumnConstraints();
			column6.setPercentWidth(8);
			ColumnConstraints column7 = new ColumnConstraints();
			column7.setPercentWidth(8);
			this.getColumnConstraints().addAll(column1, column2, column3,
					column4, column5, column6, column7);

			RowConstraints row1 = new RowConstraints();
			row1.setPercentHeight(3);
			RowConstraints row2 = new RowConstraints();
			row2.setPercentHeight(5);
			RowConstraints row3 = new RowConstraints();
			row3.setPercentHeight(4);
			RowConstraints row4 = new RowConstraints();
			row4.setPercentHeight(78);
			RowConstraints row5 = new RowConstraints();
			row5.setPercentHeight(10);
			/*
			 * RowConstraints row6 = new RowConstraints();
			 * row6.setPercentHeight(20.5); RowConstraints row7 = new
			 * RowConstraints(); row7.setPercentHeight(20.5); RowConstraints
			 * row8 = new RowConstraints(); row8.setPercentHeight(10);
			 */
			this.getRowConstraints().addAll(row1, row2, row3, row4, row5);

			// BACKEND-FRONTEND Integration
			world = new World("SandboxWorld", 10, 5);
			GridWorld.getInstance().setWorld(world);
			world.addCreature(new Creature("Eve", new Coordinate(2, 2)));
			// world.printWorld();
			// Coordinate coordEve = new Coordinate(1,1);
			// Creature CreatureEve = new Creature("Eve", coordEve);
			// world.addCreature(CreatureEve);
			// world.printWorld();
			//
			// Label Eve = new Label("Eve!");
			// GridPane.setHalignment(Eve, HPos.CENTER);
			System.out.println("Sandbox Scene things");
<<<<<<< HEAD
			//GridWorld.gridButtons[2][2].setText("Eve!");
			Eve.setVisible(true);
=======
			GridWorld.gridButtons[2][2].setText("Eve!");
			//Eve.setVisible(true);
>>>>>>> interpreter stuff

			//GridWorld.gridButtons[2][2].setText("Eve!");
			GridWorld.gridButtons[2][2].setText("");
			GridWorld.gridButtons[2][2].setGraphic(EveDown);
			//imageEve.setId("Eve!");
			//GridWorld.gridButtons[2][2].setGraphic(imageEve);
			//Eve.setVisible(true);
			
			
			//
			// //world.moveEveEast();
			// world.moveEve();
			// gridWorld.getChildren().remove(Eve);
			// gridWorld.add(Eve, CreatureEve.getX(), CreatureEve.getY());
			// world.printWorld();
			//
			// //world.moveEveSouth();
			// world.moveEve();
			// gridWorld.getChildren().remove(Eve);
			// gridWorld.add(Eve, CreatureEve.getX(), CreatureEve.getY());
			// world.printWorld();

			// this.setPadding(new Insets(0, 5, 5, 5));

			// this.setGridLinesVisible(true);

		}

		private void buttonSetup() {
			BACK = new Button();
			FORWARD = new Button();
			PLAY = new Button();
			RESET = new Button();
			BACK.setGraphic(imageBack);
			FORWARD.setGraphic(imageRight);
			PLAY.setGraphic(imagePlay);
			RESET.setGraphic(imageReset);

			BACK.setOnAction(ButtonHandlers::BACK_BUTTON_HANDLER);
			FORWARD.setOnAction(ButtonHandlers::FORWARD_BUTTON_HANDLER);
			PLAY.setOnAction(ButtonHandlers::PLAY_BUTTON_HANDLER);
			RESET.setOnAction(ButtonHandlers::RESET_BUTTON_HANDLER);
		}

		public static SandboxPane getInstance() {
			return instanceOfMainMenuPane;
		}
		
		/*@SuppressWarnings("unused")
		public static void addToScene(Node child, int i, int j, int k, int l){
			
		}*/
	}

	private static SandboxScene instanceOfSandboxScene = new SandboxScene(SandboxPane.getInstance(), MainApp.WINDOW_WIDTH,MainApp.WINDOW_HEIGHT);

	/*public static SandboxPane getSandbox(){
		return 
	}*/
	
	private SandboxScene(Parent arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}

	public void setWorld(World world1) {
		world = world1;
	}

	public static void setGridWorld(GridWorld gridworld1) {
		gridWorld = gridworld1;
	}

	public static World getWorld() {
		return world;
	}

	public static GridWorld getGridWorld() {
		return gridWorld;
	}

	public static SandboxScene getInstance() {
		return instanceOfSandboxScene;
	}

	public static Interpreter getInterpreter() {
		return interpreter;
	}

	public static void setInterpreter(Interpreter interpreter) {
		SandboxScene.interpreter = interpreter;
	}
}
