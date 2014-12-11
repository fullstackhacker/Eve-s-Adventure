package controllers;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import models.Coordinate;
import models.campaign.KarelCode;
import models.campaign.Level;
import models.campaign.World;
import models.gridobjects.items.Bamboo;
import models.gridobjects.items.Shrub;
import models.gridobjects.items.Tree;
import views.Running;
import views.grid.GridWorld;
import views.karel.KarelTable;
import views.scenes.AdventureModeScene;
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

	//private ToggleButton[][] gridButtons;

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
		KarelTable.getInstance().setSelectedIndex(activeCodeBlock);
		return this.activeCodeBlock;
	}

	/**
	 * Executes the current code block and then moves to the next one
	 */
	public void executeOne() {
		if ((activeCodeBlock >= 0 && activeCodeBlock < this.karelCode.size())) {
			KarelTable.getInstance().setSelectedIndex(activeCodeBlock);
			this.instruction();
		}
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
		System.out.println("----- RESET WORLD -----");
		this.world.printWorld();
		this.world.overwrite(startWorld);

		System.out.println("----- RESET WORLD -----");
		this.world.printWorld();
		
		//based on world, reset the grid
		for(int row = 0; row < this.world.getHeight(); row++){
			for(int col = 0; col < this.world.getWidth(); col++){
				//current position 
				Coordinate currentPosition = new Coordinate(col, row); 
			
				//clear the current graphic
				GridWorld.gridButtons[col][row].setGraphic(null);
				
				//set graphic to creature
				if(this.world.hasCreature(currentPosition) && this.world.creatureAt(currentPosition).isEve()){
					System.out.println("------- Changing eve graphic! ------" + this.world.getEve().getDirection());
					switch(this.world.getEve().getDirection()){
					case Coordinate.UP: 
						GridWorld.gridButtons[col][row].setGraphic(SandboxScene.getEveDownI());
						break;
					case Coordinate.DOWN: 
						GridWorld.gridButtons[col][row].setGraphic(SandboxScene.getEveUpI());
						break;
					case Coordinate.LEFT:
						GridWorld.gridButtons[col][row].setGraphic(SandboxScene.getEveLeftI());
						break;
					case Coordinate.RIGHT: 
						GridWorld.gridButtons[col][row].setGraphic(SandboxScene.getEveRightI()); 
						break;
					default: 
						throw new IllegalValueException("Eve is facing an illegal direction");
					}
				}
				else if(this.world.hasCreature(currentPosition)){
					GridWorld.gridButtons[col][row].setGraphic(SandboxScene.getFriendI());
				}
				else if(this.world.hasItem(currentPosition)){
					if(this.world.itemAt(currentPosition) instanceof Bamboo){
						GridWorld.gridButtons[col][row].setGraphic(SandboxScene.getBambooI());
					}
					else if(this.world.itemAt(currentPosition) instanceof Shrub){
						GridWorld.gridButtons[col][row].setGraphic(SandboxScene.getShrubI()); 
					}
					else if(this.world.itemAt(currentPosition) instanceof Tree){
						GridWorld.gridButtons[col][row].setGraphic(SandboxScene.getTreeI());
					}
				}
				//
				
			}
		}
		
		if (timer != null) {
			timer.cancel();
		}

		KarelTable.getInstance().setSelectedIndex(0);

	}

	public void pause() {
		if (timer != null) {
			timer.cancel();
		}
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
						System.out.println("activeCodeBlock: "
								+ activeCodeBlock);
						KarelTable.getInstance().setSelectedIndex(
								activeCodeBlock);
						instructions();
					}
				});
			}
		}, 0, 1000);
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
		KarelTable.getInstance().setSelectedIndex(activeCodeBlock);
		if (!validPosition()) {
			timer.cancel();
			if (ButtonHandlers.isSandboxMode()) {
				SandboxScene.PLAY.setDisable(false);
				;
			} else {
				AdventureModeScene.PLAY.setDisable(false);
			}
			return;
		}
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ELSESTATEMENT))
			return;
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
		instructions();
		// player.play();
	}

	public void instruction() {
		System.out.println("Instruction: "
				+ this.karelCode.get(this.activeCodeBlock));

		switch (this.karelCode.get(this.activeCodeBlock)) {
		// end statements
		case KarelCode.ELSESTATEMENT: 
			return;
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
			if (!this.world.getFindObj()
					&& this.world.getBambooObjective() == GridWorld
							.getInstance().getWorld().getEve()
							.getNumberOfBamboo()) {
				this.activeCodeBlock = this.karelCode.size();
				Running.getInstance().setText("YOU WIN!!!!");
			} else if (this.world.getFindObj()
					&& GridWorld.getInstance().getWorld().eveNearFriend()) {
				this.activeCodeBlock = this.karelCode.size();
				Running.getInstance().setText("YOU WIN!!!!");
			}
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
			System.out.println("activeCode Block: " + this.karelCode.get(this.activeCodeBlock));
			int currentInstruction = this.activeCodeBlock-2;
			if (this.currentLoopCounter < times) {
				instructions();
				this.currentLoopCounter++;
				this.activeCodeBlock = currentInstruction;
				System.out.println("LOOP FINISHED INSTRUCTIONS: AND RESET: " + this.karelCode.get(this.activeCodeBlock));
			} else {
				do {
					if (!this.next())
						return;
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
		System.out.println("condtional CALLED");
		System.out.println("this.activeCodeBlock = " + this.karelCode.get(this.activeCodeBlock));
		if (!this.karelCode.get(this.activeCodeBlock).equals(
				KarelCode.IFSTATEMENT)){
			System.out.println("1. if");
			return; // error
		}
		if (!this.next()){
			throw new IllegalValueException("Ill formed code");
		}
		KarelTable.getInstance().setSelectedIndex(activeCodeBlock);
		boolean result = variable();
		System.out.println("result = " + result);
		if(!this.next()) throw new IllegalValueException("ill formed karel code");
		if (result) {
			instructions();
		} else {
			System.out.println("FALSE");
			do {
				if(!this.next()) throw new IllegalValueException("ill formed karel code");
			} while (!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDIF));
		}
		if(!validPosition()) return;
		System.out.println("1this.activeCodeBlock = " + this.karelCode.get(this.activeCodeBlock));
		System.out.println("2this.activeCodeBlock = " + this.karelCode.get(this.activeCodeBlock));
		if(!this.next()) throw new IllegalValueException("illegal stuff");
		if (this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ELSESTATEMENT) && !result) {
			System.out.println("ELSEING");
			if (!this.next()){	
				throw new IllegalValueException("Ill formed Karel Code");
			}
			System.out.println("instruction() CALLED-----------------");
			instructions();
		}
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ELSESTATEMENT) && result){
			do{
				if(!this.next()) throw new IllegalValueException("mal formed karel code");
			}while(this.karelCode.get(activeCodeBlock).equals(KarelCode.ENDELSE));
		}
	}

	public void operation() {
		KarelTable.getInstance().setSelectedIndex(activeCodeBlock);
		if (!this.world.getEve().isAwake()) {
			if (this.karelCode.get(this.activeCodeBlock).equals(
					KarelCode.WAKEUP)) {
				this.world.getEve().setAwake(true);
			}
			return;
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
		case KarelCode.WAKEUP:
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
		System.out.println("variable CALLED");
		System.out.println("VARIABLE: CURRENT DIRECTION: "
				+ this.world.getEve().getDirection());
		System.out.println("VARIABLE: CURRENT LOCATION: "
				+ this.world.getEve().getCoordinates());

		System.out.println("this.activeCodeBlock = " + this.karelCode.get(this.activeCodeBlock));
		switch (this.karelCode.get(this.activeCodeBlock)) {
		case KarelCode.FRONTISCLEAR:
			System.out.println("frontIsClear" +this.world.frontIsClear());
			return this.world.frontIsClear();
		case KarelCode.BAGISEMPTY:
			return !this.world.getEve().hasBamboo();
		case KarelCode.FACINGNORTH:
			return this.world.getEve().getDirection() == Coordinate.DOWN;
		case KarelCode.FACINGSOUTH:
			return this.world.getEve().getDirection() == Coordinate.UP;
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
		switch (this.karelCode.get(this.activeCodeBlock--)) {
		case KarelCode.MOVE:
		case KarelCode.TURNRIGHT:
		case KarelCode.SLEEP:
		case KarelCode.WAKEUP:
		case KarelCode.PUTBAMBOO:
		case KarelCode.PICKBAMBOO:
			reverseOperation();
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

	private void reverseOperation() {
		switch (this.karelCode.get(this.activeCodeBlock)) {
		case KarelCode.MOVE:
			this.world.moveEve();
			break;
		default:
			break;
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
