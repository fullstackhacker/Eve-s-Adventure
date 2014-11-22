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
		
		if(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDELSESTATEMENT)){
			//error
		}
		
		return;
	}
	
	/**
	 * handles repetitions in Karel
	 */
	public void repetitions(){
		switch(this.karelCode.get(this.activeCodeBlock)){
		case KarelCode.WHILESTATEMENT: 
			while(variable()){
				instructions();
			}
		case KarelCode.LOOPSTATEMENT: 
			for(int x=0; x< Integer.parseInt(numbers()); x++){
				instructions(); 
			}
			break; 
		}
		
	}
	
	public void operations(){
		switch(this.karelCode.get(this.activeCodeBlock)){ 
		case KarelCode.MOVE:
			this.next(); 
			if(!this.world.getEve().isAwake()) return; 
			switch(this.world.getEve().getDirection()){
			case Creature.UP: 
				if(this.world.hasCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()+1))) return;
				Creature eve = this.world.removeCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()));
				eve.moveUp(); 
				this.world.addCreature(eve); 
				break; 
			case Creature.DOWN: 
				if(this.world.hasCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()-1))) return;
				eve = this.world.removeCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()));
				eve.moveDown(); 
				this.world.addCreature(eve);
				break;
			case Creature.LEFT: 
				if(this.world.hasCreature(new Coordinate(this.world.getEve().getX()-1, this.world.getEve().getY()))) return;
				eve = this.world.removeCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()));
				eve.moveLeft(); 
				this.world.addCreature(eve);
				break;
			case Creature.RIGHT: 
				if(this.world.hasCreature(new Coordinate(this.world.getEve().getX()+1, this.world.getEve().getY()))) return;
				eve = this.world.removeCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()));
				eve.moveRight(); 
				this.world.addCreature(eve);
				break;
			default: 
				//error
				return; 
			}
		case KarelCode.SLEEP: 
		case KarelCode.WAKEUP: 
		case KarelCode.TURNLEFT: 
		case KarelCode.PICKBAMBOO:
		case KarelCode.PUTBAMBOO: 
		default:
		}
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
				return this.world.getEve().getX()-1 < 0 ? false : !this.world.hasCreature(new Coordinate(this.world.getEve().getX()+1, this.world.getEve().getY()));
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
			this.next(); 
			return this.world.getEve().getDirection() == Creature.RIGHT; 
		case KarelCode.FACINGWEST: 
			this.next(); 
			return this.world.getEve().getDirection() == Creature.LEFT; 
		case KarelCode.BAGISEMPTY:
			this.next(); 
			return !this.world.getEve().hasBamboo(); 
		case KarelCode.NEXTTOAFRIEND: 
			this.next(); 
			//because java lets me do multiple error checks, create objects, solve boolean expressions and basic math in the same line
			return this.world.getEve().getY()+1 >= this.world.getHeight() ? false : this.world.hasCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()+1)) || this.world.getEve().getY()-1 < 0 ? false : this.world.hasCreature(new Coordinate(this.world.getEve().getX(), this.world.getEve().getY()-1)) || this.world.getEve().getX()+1 >= this.world.getWidth() ? false : this.world.hasCreature(new Coordinate(this.world.getEve().getX()-1, this.world.getEve().getY())) || this.world.getEve().getX()-1 < 0 ? false : this.world.hasCreature(new Coordinate(this.world.getEve().getX()+1, this.world.getEve().getY()));  
		default: 
			return false; 
		}	
	}	
	
	public String numbers(){
		String number = number();
		if(isDigit(this.karelCode.get(this.activeCodeBlock))){
			number += numbers(); 
		}
		return number; 
	}
	
	public String number(){ 
		String digit = this.karelCode.get(this.activeCodeBlock);
		this.next();
		return digit;
	}
	
	public static boolean isDigit(String token){ 
		try{
			Integer.parseInt(token);
			return true; 
		}
		catch(Exception e){
			return false; 
		}
	}
}
