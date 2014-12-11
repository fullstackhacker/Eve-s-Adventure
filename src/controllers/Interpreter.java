package controllers;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.ToggleButton;
import models.Coordinate;
import models.campaign.KarelCode;
import models.campaign.Level;
import models.campaign.World;
import models.gridobjects.creatures.Creature;
import views.grid.GridWorld;
import views.grid.GridWorld;
import views.karel.KarelTable;
import views.scenes.SandboxScene;
import exceptions.IllegalValueException;

/**
 * An object to go through and execute the Karel code
 *
 */
public class Interpreter {
	/**
	 * The karel code to parse through
	 */
	private ArrayList<String> karelCode;
	/**
	 * The current position of the executing code
	 */
	private int activeCodeBlock;
	/**
	 * The world that the code should be executed in
	 */
	private World world;
	/**
	 * The original state of the world
	 */
	private World startWorld;

	private ToggleButton[][] gridButtons;

	private Timer timer;

	private int currentLoopCounter;

	private boolean currentWhileResult;

	/**
	 * Constructor for the parser
	 * 
	 * @param karelCode
	 *            the Karel code to parse and execute
	 * @param world
	 *            the world where to execute the karel code
	 */
	public Interpreter(ArrayList<String> karelCode, World world) {
		this.karelCode = karelCode;
		this.world = world;
		GridWorld.getInstance().setWorld(this.world);
		this.startWorld = world.copyWorld();
		this.currentLoopCounter = 0;
		this.currentWhileResult = false;
		this.gridButtons = new ToggleButton[5][10];
		for(int row = 0; row < this.gridButtons.length; row++){
			for(int col = 0; col < this.gridButtons.length; col++){
				this.gridButtons[row][col] = new ToggleButton();
				this.gridButtons[row][col].setGraphic(GridWorld.gridButtons[row][col].getGraphic()); 
			}
		}
	}

	/**
	 * Moves onto the next code block WITHOUT executing it
	 */
	public boolean next() {
		this.activeCodeBlock++;
		return this.activeCodeBlock < this.karelCode.size();
	}

	public boolean validPosition() {
		return this.activeCodeBlock < this.karelCode.size();
	}

	/**
	 * Moves to the previous code block WITHOUT executing it
	 */
	public int previous() {
		this.activeCodeBlock--;
		return this.activeCodeBlock;
	}

	/**
	 * Executes the current code block and then moves to the next one
	 */
	public void executeOne() {
		this.instruction();
	}

	/**
	 * Set the position of the active code block
	 * 
	 * @param activeCodeBlock
	 *            the new position for the active code block
	 */
	public int setPosition(int activeCodeBlock) {
		this.activeCodeBlock = activeCodeBlock;
		return this.activeCodeBlock;
	}

	/**
	 * Resets the parser back to the first code block
	 */
	public void reset() {
		this.activeCodeBlock = 0;
		this.world.overwrite(startWorld);

		for (int row = 0; row < gridButtons.length; row++) {
			for (int col = 0; col < gridButtons[row].length; col++) {
				// going to be swapped with the images
				if (GridWorld.gridButtons[row][col] != null
						&& gridButtons[row][col] != null)
					GridWorld.gridButtons[row][col]
							.setGraphic(gridButtons[row][col].getGraphic());
			}
		}
		
		if(timer != null){
			timer.cancel();
		}
		
		KarelTable.getInstance().setSelectedIndex(0);
		
	}

	/**
	 * Executes all the code
	 */
	public void executeAll() {

	}

	/**
	 * Changes the karelCode and resets the activeCodeBlock
	 * 
	 * @param karelCode
	 *            the new Karel Code to execute
	 */
	public void changeKarelCode(ArrayList<String> karelCode) {
		this.karelCode = karelCode;
	}

	public World getWorld() {
		return this.world;
	}

	public World getStartWorld() {
		return this.startWorld;
	}

	public void start() {
		this.world.findEve();
		// player.play();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						KarelTable.getInstance().setSelectedIndex(
								activeCodeBlock);
						instructions();
					}
				});
			}
		}, 0, 2000);
	}

	/*
	 * Timeline player = new Timeline(new KeyFrame(Duration.seconds(2), new
	 * EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent event) {
	 * KarelTable.getInstance().setSelectedIndex(activeCodeBlock);
	 * instructions(); } }));
	 */

	// , new KeyFrame(Duration.seconds(2))

	public void instructions() {
		// player.stop();
		System.out.println("Instructions: "
				+ this.karelCode.get(this.activeCodeBlock) + "active: "
				+ this.activeCodeBlock);
		instruction();
		if (!validPosition()) {
			timer.cancel();
			return;
		}
		if (this.karelCode.get(this.activeCodeBlock).equals(
				KarelCode.CLOSESTATEMENT))
			return;
		if (this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDIF))
			return;
		if (this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDELSE))
			return;
		if (this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDWHILE)) {
			// if(this.currentWhileResult) this.repetition();
			return;
		}
		if (this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDLOOP)) {

			return;
		}
		// player.play();
	}

	public void instruction() {
		System.out.println("Instruction: "
				+ this.karelCode.get(this.activeCodeBlock));

		switch (this.karelCode.get(this.activeCodeBlock)) {
		// end statements
		case KarelCode.ENDIF:
		case KarelCode.ENDWHILE:
		case KarelCode.ENDELSE:
		case KarelCode.ENDLOOP:
			this.next();
			return;
			// close statements
		case KarelCode.CLOSESTATEMENT:
			return;
			// repetitions
		case KarelCode.LOOPSTATEMENT:
		case KarelCode.WHILESTATEMENT:
			repetition();
			// this.next();
			break;
		// conditionals
		case KarelCode.IFSTATEMENT:
			conditional();
			this.next();
			break;
		// operations
		case KarelCode.MOVE:
		case KarelCode.WAKEUP:
		case KarelCode.SLEEP:
		case KarelCode.TURNRIGHT:
		case KarelCode.PICKBAMBOO:
		case KarelCode.PUTBAMBOO:
			operation();
			// add an objective check
			if (Level.getObjective().equals("collect")
					&& this.world.getBambooObjective() == GridWorld.getInstance().getWorld()
							.getEve().getNumberOfBamboo()) {
				this.activeCodeBlock = this.karelCode.size();
			} /*else if (Level.getObjective().equals("find")
					&& GridWorld.getInstance().getWorld().getEve().getCoordinates()*/
			this.next();
			break;
		default:
			throw new IllegalValueException("Illegal Karel Code Segment: "
					+ this.karelCode.get(this.activeCodeBlock));
		}
	}

	public void repetition() {
		System.out.println("Repetition: "
				+ this.karelCode.get(this.activeCodeBlock));

		switch (this.karelCode.get(this.activeCodeBlock)) {
		case KarelCode.WHILESTATEMENT:
			int variableCode = this.activeCodeBlock;
			if (!this.next())
				throw new IllegalValueException("Ill formed Karel Code");
			this.currentWhileResult = variable();
			if (this.currentWhileResult) {
				if (!this.next())
					return;
				instructions();
				this.activeCodeBlock = variableCode;
				System.out.println("supposed to reset the result: "
						+ this.karelCode.get(this.activeCodeBlock));
			}
			if (!this.currentWhileResult) {
				do {
					if (!this.next())
						throw new IllegalValueException("Ill formed Karel Code");
				} while (!this.karelCode.get(this.activeCodeBlock).equals(
						KarelCode.ENDWHILE));
			}
			break;
		case KarelCode.LOOPSTATEMENT:
			if (!this.next())
				throw new IllegalValueException("Ill formed Karel Code");
			int times = positiveNumbers();
			System.out.println("int times: " + times);
			int currentInstruction = this.activeCodeBlock;
			if (!this.next())
				throw new IllegalValueException("Ill formed Karel Code");
			if (this.currentLoopCounter < times) {
				instructions();
				this.currentLoopCounter++;
				this.activeCodeBlock = currentInstruction;
			} else {
				do {
					if (!this.next())
						throw new IllegalValueException("Ill formed Karel Code");
				} while (!this.karelCode.get(this.activeCodeBlock).equals(
						KarelCode.ENDLOOP));
			}
			break;
		default:
			throw new IllegalValueException("Ill formed Karel Code: "
					+ this.karelCode.get(this.activeCodeBlock));
		}

	}

	public void conditional() { // if statement
		if (!this.karelCode.get(this.activeCodeBlock).equals(
				KarelCode.IFSTATEMENT))
			return; // error
		if (!this.next())
			throw new IllegalValueException("Ill formed code");
		if (!this.karelCode.get(this.activeCodeBlock).equals(
				KarelCode.CLOSESTATEMENT))
			return;
		if (!this.next())
			throw new IllegalValueException("Ill formed code");
		boolean result = variable();
		if (result) {
			if (!this.next())
				throw new IllegalValueException("Ill formed Karel Code");
			instructions();
		} else {
			do {
				this.next();
			} while (!this.karelCode.get(this.activeCodeBlock).equals(
					KarelCode.ENDIF));
			System.out.println("Conditional (variable false): "
					+ this.karelCode.get(this.activeCodeBlock));
		}
		if (!this.next())
			return;
		if (this.karelCode.get(this.activeCodeBlock).equals(
				KarelCode.ELSESTATEMENT)
				&& !result) {
			if (!this.next())
				throw new IllegalValueException("Ill formed Karel Code");
			instructions();
		}
		if (this.karelCode.get(this.activeCodeBlock).equals(
				KarelCode.ELSESTATEMENT)
				&& result) {
			do {
				this.next();
			} while (!this.karelCode.get(this.activeCodeBlock).equals(
					KarelCode.ENDIF));
		}

	}

	public void operation() {
		if (!this.world.getEve().isAwake()) {
			if (this.karelCode.get(this.activeCodeBlock).equals(
					KarelCode.WAKEUP)) {
				this.world.getEve().setAwake(true);
			} else {
				return;
			}
		}

		switch (this.karelCode.get(this.activeCodeBlock)) {
		case KarelCode.MOVE:
			this.world.moveEve();
			return;
		case KarelCode.TURNRIGHT:
			this.world.getEve().turnLeft();
			if (this.world.getEve().getDirection() == Coordinate.UP) {
				GridWorld.gridButtons[this.world.getEve().getX()][this.world
						.getEve().getY()]
						.setGraphic(SandboxScene.getEveDownI());
			} else if (this.world.getEve().getDirection() == Coordinate.LEFT) {
				GridWorld.gridButtons[this.world.getEve().getX()][this.world
						.getEve().getY()]
						.setGraphic(SandboxScene.getEveLeftI());
			} else if (this.world.getEve().getDirection() == Coordinate.RIGHT) {
				GridWorld.gridButtons[this.world.getEve().getX()][this.world
						.getEve().getY()].setGraphic(SandboxScene
						.getEveRightI());
			} else {
				GridWorld.gridButtons[this.world.getEve().getX()][this.world
						.getEve().getY()].setGraphic(SandboxScene.getEveUpI());
			}
			/*
			 * GridWorld.gridButtons[this.world.getEve().getX()][this.world
			 * .getEve().getY()].getTransforms().add( new Rotate(90, 28, 32.5));
			 */
			return;
		case KarelCode.SLEEP:
			this.world.getEve().setAwake(false);
			return;
		case KarelCode.PUTBAMBOO:
			this.world.evePutBamboo();
			return;
		case KarelCode.PICKBAMBOO:
			this.world.evePickBamboo();
			return;
		default:
			throw new IllegalValueException("Ill forme Karel Code");
		}
	}

	public boolean variable() {

		System.out.println("VARIABLE: CURRENT DIRECTION: "
				+ this.world.getEve().getDirection());
		System.out.println("VARIABLE: CURRENT LOCATION: "
				+ this.world.getEve().getCoordinates());

		switch (this.karelCode.get(this.activeCodeBlock)) {
		case KarelCode.FRONTISCLEAR:
			return this.world.frontIsClear();
		case KarelCode.BAGISEMPTY:
			return !this.world.getEve().hasBamboo();
		case KarelCode.FACINGNORTH:
			return this.world.getEve().getDirection() == Coordinate.UP;
		case KarelCode.FACINGSOUTH:
			return this.world.getEve().getDirection() == Coordinate.DOWN;
		case KarelCode.FACINGEAST:
			return this.world.getEve().getDirection() == Coordinate.RIGHT;
		case KarelCode.FACINGWEST:
			return this.world.getEve().getDirection() == Coordinate.LEFT;
		default:
			throw new IllegalValueException("Ill formed Karel Code "
					+ this.karelCode.get(this.activeCodeBlock));
		}
	}

	public int positiveNumbers() {
		String number = "";
		do {
			number += this.karelCode.get(this.activeCodeBlock);
			System.out.println("number: " + number);
			if (!this.next()) {
				throw new IllegalValueException("Ill formed Karel Code");
			}
		} while (this.karelCode.get(this.activeCodeBlock).matches(".*\\d.*"));
		return Integer.parseInt(number);
	}

	/* Reverser Instruction */
	public void reverseInstruction() {
		switch (this.karelCode.get(this.activeCodeBlock)) {
		case KarelCode.MOVE:
		case KarelCode.TURNRIGHT:
		case KarelCode.SLEEP:
		case KarelCode.WAKEUP:
		case KarelCode.PUTBAMBOO:
		case KarelCode.PICKBAMBOO:
			// reverseOperation();
			return;
		case KarelCode.ENDIF:
		case KarelCode.ENDELSE:
			// reverseConditional();
			return;
		case KarelCode.ENDWHILE:
		case KarelCode.ENDLOOP:
			// reverseReptition();
			return;
		default:
			throw new IllegalValueException("Illegal Set of Karel Code: "
					+ this.karelCode.get(this.activeCodeBlock));
		}
	}

	/*
	 * public static void main(String[] args) { World world = new
	 * World("test_world", 5, 5); Level level = new Level(world,
	 * "testing the parser");
	 * 
	 * if (world.addCreature(new Creature("Eve", new Coordinate(0, 0))))
	 * System.out.println("added eve"); else
	 * System.out.println("unable to add eve");
	 * 
	 * level.addKarelCode(KarelCode.MOVE); Interpreter interpreter = new
	 * Interpreter(level.getKarelCode(), world);
	 * 
	 * if (world.getEve() == null) return;
	 * 
	 * world.printWorld(); interpreter.start();
	 * System.out.println("Active Code Block: " + interpreter.activeCodeBlock +
	 * "total size: " + level.getKarelCode().size()); world.printWorld();
	 * 
	 * }
	 */
}
