package controllers;

import java.util.ArrayList;

import exceptions.IllegalValueException;
import models.Coordinate;
import models.campaign.KarelCode;
import models.campaign.Level;
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
	public boolean next(){
		this.activeCodeBlock++;
		return this.activeCodeBlock < this.karelCode.size(); 
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
	public void executeOne(){
		this.instruction();
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
	
	public void start(){ 
		instructions();
	}
	
	public void instructions(){ 
		System.out.println("Instructions: " + this.karelCode.get(this.activeCodeBlock)); 
		instruction();
		//if(!this.next()) return;
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.CLOSESTATEMENT)) return; 
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDIF)) return;
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDELSE)) return;
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDWHILE)) return;
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDLOOP)) return;
		instructions();
	}
	
	public void instruction(){
		System.out.println("Instruction: " + this.karelCode.get(this.activeCodeBlock)); 

		switch(this.karelCode.get(this.activeCodeBlock)){
		//end statements
		case KarelCode.ENDIF:
		case KarelCode.ENDWHILE:
		case KarelCode.ENDELSE: 
		case KarelCode.ENDLOOP:
			this.next();
			return; 
		//close statements
		case KarelCode.CLOSESTATEMENT:
			return;
		//repetitions
		case KarelCode.LOOPSTATEMENT:
		case KarelCode.WHILESTATEMENT: 
			repetition(); 
			this.next();
			break; 
		//conditionals
		case KarelCode.IFSTATEMENT: 
			conditional(); 
			this.next();
			break; 
		//operations
		case KarelCode.MOVE:
		case KarelCode.WAKEUP: 
		case KarelCode.SLEEP: 
		case KarelCode.TURNLEFT: 
		case KarelCode.PICKBAMBOO: 
		case KarelCode.PUTBAMBOO:
			operation(); 
			this.next();
			break;
		default: 
			throw new IllegalValueException("Illegal Karel Code Segment: " + this.karelCode.get(this.activeCodeBlock));
		}
	}
	
	public void repetition(){
		System.out.println("Repetition: " + this.karelCode.get(this.activeCodeBlock)); 

		switch(this.karelCode.get(this.activeCodeBlock)){
		case KarelCode.WHILESTATEMENT:
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code");
			int variableCode = this.activeCodeBlock;
			boolean result = variable(); 
			while(result){
				if(!this.next()) return; 
				instructions(); 
				this.activeCodeBlock = variableCode; 
				result = variable();
			}
			if(!result){
				do{
					if(!this.next()) throw new IllegalValueException("Ill formed Karel Code");
				}while(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDWHILE)); 
			}
			break; 
		case KarelCode.LOOPSTATEMENT:
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code"); 
			int times = positiveNumbers(); 
			int currentInstruction = this.activeCodeBlock;
			for(int counter = 0; counter < times; counter++){
				this.activeCodeBlock = currentInstruction; 
				instructions();
			}
			break; 
		default: 
			throw new IllegalValueException("Ill formed Karel Code" + this.karelCode.get(this.activeCodeBlock)); 
		}
		
	}
	
	public void conditional(){ //if statement
		System.out.println("Conditional: " + this.karelCode.get(this.activeCodeBlock)); 
		System.out.println("Conditional: CURRENT LOCATION: " + this.world.getEve().getCoordinates());
		if(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.IFSTATEMENT)) return; //error
		if(!this.next()) throw new IllegalValueException("Ill formed code"); 
		if(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.CLOSESTATEMENT)) return;
		if(!this.next()) throw new IllegalValueException("Ill formed code"); 
		System.out.println("Conditional (variable): " + this.karelCode.get(this.activeCodeBlock)); 
		
		boolean result = variable(); 
		if(result){
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code");
			instructions(); 
		}
		else{ 
			do{
				this.next(); 
			}while(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDIF));
			System.out.println("Conditional (variable false): " + this.karelCode.get(this.activeCodeBlock)); 
		}
		if(!this.next()) return; 
		System.out.println("Conditional (else check): " + this.karelCode.get(this.activeCodeBlock)); 
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ELSESTATEMENT) && !result){
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code");
			instructions(); 
		}
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ELSESTATEMENT)&& result){
			do{
				this.next(); 
			}while(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDIF));
		}
		System.out.println("Conditional (end): " + this.karelCode.get(this.activeCodeBlock)); 

	}
	
	public void operation(){
		if(!this.world.getEve().isAwake()){
			if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.WAKEUP)){
				this.world.getEve().setAwake(true);
			}
			else{
				return;
			}
		}
		
		System.out.println("OPERATION: CURRENT LOCATION: " + this.world.getEve().getCoordinates());
		
		switch(this.karelCode.get(this.activeCodeBlock)){
		case KarelCode.MOVE:
			this.world.moveEve();
			return;
		case KarelCode.TURNLEFT:
			this.world.getEve().turnLeft();
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
	
	public boolean variable(){		
		
		System.out.println("VARIABLE: CURRENT DIRECTION: " + this.world.getEve().getDirection()); 
		System.out.println("VARIABLE: CURRENT LOCATION: " + this.world.getEve().getCoordinates());
		
		
		switch(this.karelCode.get(this.activeCodeBlock)){
		case KarelCode.FRONTISCLEAR:
			return this.world.frontIsClear();  
		case KarelCode.BAGISEMPTY: 
			return !this.world.getEve().hasBamboo();
		case KarelCode.FACINGNORTH: 
			return this.world.getEve().getDirection() == Creature.UP;
		case KarelCode.FACINGSOUTH:
			return this.world.getEve().getDirection() == Creature.DOWN; 
		case KarelCode.FACINGEAST: 
			return this.world.getEve().getDirection() == Creature.RIGHT;
		case KarelCode.FACINGWEST:
			return this.world.getEve().getDirection() == Creature.LEFT;
		default: 
			throw new IllegalValueException("Ill formed Karel Code " + this.karelCode.get(this.activeCodeBlock));
		}
	}
	
	public int positiveNumbers(){
		String number = "";
		do{ 
			number += this.karelCode.get(this.activeCodeBlock);
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code"); 
		}while(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.CLOSESTATEMENT)); 
		return Integer.parseInt(number); 
	}
	
	public static void main(String[] args){
		World world = new World("test_world",5, 5);
		Level level = new Level(world, "testing the parser");
		
		if(world.addCreature(new Creature("Eve", new Coordinate(0,0)))) System.out.println("added eve");
		else System.out.println("unable to add eve");
		
		level.addKarelCode(KarelCode.MOVE);
		level.addKarelCode(KarelCode.WHILESTATEMENT);
		level.addKarelCode(KarelCode.FRONTISCLEAR);
		//level.addKarelCode(KarelCode.CLOSESTATEMENT);
		level.addKarelCode(KarelCode.MOVE);
		level.addKarelCode(KarelCode.ENDWHILE);
		level.addKarelCode(KarelCode.TURNLEFT); 
		level.addKarelCode(KarelCode.TURNLEFT); 
		level.addKarelCode(KarelCode.TURNLEFT); 
		level.addKarelCode(KarelCode.MOVE);
		level.addKarelCode(KarelCode.PUTBAMBOO);
		level.addKarelCode(KarelCode.IFSTATEMENT);
		level.addKarelCode(KarelCode.FRONTISCLEAR);
		//level.addKarelCode(KarelCode.CLOSESTATEMENT);
		level.addKarelCode(KarelCode.MOVE);
		level.addKarelCode(KarelCode.ENDIF);
		Parser parser = new Parser(level.getKarelCode(), world);
		
		if(world.getEve() == null) return;
		
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		world.printWorld(); 
		parser.executeOne();
		System.out.println("Active Code Block: " + parser.activeCodeBlock +  "total size: " + level.getKarelCode().size());
		
		
	}
}
