package controllers;

import java.util.ArrayList;

import models.Coordinate;
import models.campaign.KarelCode;
import models.campaign.World;
import models.gridobjects.creatures.Creature;

/**
 * An object to go through and execute the Karel code
 *
 */
public class Parser {
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
	 * Constructor for the parser
	 * 
	 * @param karelCode  the Karel code to parse and execute
	 * @param world the world where to execute the karel code
	 */
	public Parser(ArrayList<String> karelCode, World world){ 
		 this.karelCode = karelCode; 
		 this.world = world; 
	}
	/**
	 * Moves onto the next code block WITHOUT executing it
	 */
	public int next(){
		this.activeCodeBlock++;
		return this.activeCodeBlock; 
	}
	/**
	 * Moves to the previous code block WITHOUT executing it
	 */
	public int previous(){
		this.activeCodeBlock--;
		return this.activeCodeBlock; 
	}
	/**
	 * Executes the current code block and then moves to the next one
	 */
	public void execute(){
		switch(this.karelCode.get(this.activeCodeBlock)){
		case KarelCode.MOVE: 
			world.getEve().moveUp(); //suppress warning
			break; 
		case KarelCode.BAGISEMPTY:
			break;
		default: 
			break; 
		}
	}
	/**
	 * Set the position of the active code block
	 * 
	 * @param activeCodeBlock  the new position for the active code block
	 */
	public int setPosition(int activeCodeBlock){
		this.activeCodeBlock = activeCodeBlock; 
		return this.activeCodeBlock; 
	}
	/**
	 * Resets the parser back to the first code block
	 */
	public void reset(){
		this.activeCodeBlock = 0; 
	}
	/**
	 * Executes all the code 
	 */
	public void executeAll(){
		
	}
	/**
	 * Changes the karelCode and resets the activeCodeBlock
	 * 
	 * @param karelCode  the new Karel Code to execute
	 */
	public void changeKarelCode(ArrayList<String> karelCode){
		this.karelCode = karelCode;
	}
	
	/**
	 * Starts the program interpretation
	 */
	public void start(){ 
		instructions();
	}
	
	/**
	 * Interprets an instruction and then move instructions  
	 */
	public void instructions(){
		instruction();
		if(this.activeCodeBlock != -1)
			return; 
		instructions();
	}
	
	/**
	 * Directs the interpreter into the various instructions
	 */
	public void instruction(){
		switch(this.karelCode.get(this.activeCodeBlock)){
		case KarelCode.IFSTATEMENT: 
			conditionals(); 
			break; 
		case KarelCode.LOOPSTATEMENT:
		case KarelCode.WHILESTATEMENT: 
			repetitions(); 
			break;
		case KarelCode.MOVE: 
		case KarelCode.WAKEUP: 
		case KarelCode.SLEEP: 
		case KarelCode.TURNLEFT: 
		case KarelCode.PICKBAMBOO: 
		case KarelCode.PUTBAMBOO: 
			operations(); 
			break; 
		}
	}
	
	/**
	 * Conditional statements in Karel
	 */
	public void conditionals(){ 
		this.next();
		boolean variable = this.variable();
		if(variable){
			instructions();
		}
		
		if(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDIFSTATEMENT)){
			//error - something went wrong
		}
		
		this.next(); 
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ELSESTATEMENT)){
			instructions();
		}
		
		return;
	}
	
	public void repetitions(){
		
	}
	
	public void operations(){
		
	}
	
	public boolean variable(){
		
		switch(this.karelCode.get(this.activeCodeBlock)){
		case KarelCode.FRONTISCLEAR:
			this.next();
			switch(this.world.getEve().getDirection()){
			case Creature.UP:
				return this.world.getEve().getY()+1 >= this.world.getHeight() ? false : !this.world.hasCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()+1));
			case Creature.DOWN: 
				return this.world.getEve().getY()-1 < 0 ? false : !this.world.hasCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()-1));
			case Creature.LEFT: 
				return this.world.getEve().getX()+1 >= this.world.getWidth() ? false : !this.world.hasCreature(new Coordinate(this.world.getEve().getX()-1, this.world.getEve().getY()));
			case Creature.RIGHT: 
				return this.world.getEve().getX()-1 < 0 ? false : !this.world.hasCreature(new Coordinate(this.world.getEve().getX()+1, this.world.getEve().getY()-1));
			default:
				return false; //error
			} 
		case KarelCode.FACINGNORTH:
			this.next(); 
			return this.world.getEve().getDirection() == Creature.UP; 
		case KarelCode.FACINGSOUTH: 
			this.next(); 
			return this.world.getEve().getDirection() == Creature.DOWN; 
		case KarelCode.FACINGEAST: 
			return this.world.getEve().getDirection() == Creature.RIGHT; 
		case KarelCode.FACINGWEST: 
			return this.world.getEve().getDirection() == Creature.LEFT; 
		default: 
			this.next(); 
			return false; 
		}
		
	}
	
}
