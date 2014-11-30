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
	public void execute(){
		
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
		instruction();
		if(!this.next()) return; 
		instructions();
	}
	
	public void instruction(){
		switch(this.karelCode.get(this.activeCodeBlock)){
		//end statements
		case KarelCode.ENDBLOCK: 
			return; 
		//repetitions
		case KarelCode.LOOPSTATEMENT:
		case KarelCode.WHILESTATEMENT: 
			repetition(); 
			break; 
		//conditionals
		case KarelCode.IFSTATEMENT: 
			conditional(); 
			break; 
		//operations
		case KarelCode.MOVE:
		case KarelCode.WAKEUP: 
		case KarelCode.SLEEP: 
		case KarelCode.TURNLEFT: 
		case KarelCode.PICKBAMBOO: 
		case KarelCode.PUTBAMBOO:
			operation(); 
			break;
		default: 
			throw new IllegalValueException("Illegal Karel Code Segment: " + this.karelCode.get(this.activeCodeBlock));
		}
	}
	
	public void repetition(){
		switch(this.karelCode.get(this.activeCodeBlock)){
		case KarelCode.WHILESTATEMENT:
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code");
			boolean result = variable(); 
			if(result){
				if(!this.next()) throw new IllegalValueException("Ill formed Karel Code"); 
				instructions(); 
			}
			else{
				do{
					if(!this.next()) throw new IllegalValueException("Ill formed Karel Code");
				}while(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDBLOCK)); 
			}
			break; 
		case KarelCode.LOOPSTATEMENT:
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code"); 
			int times = positiveNumbers(); 
			int currentInstruction = this.activeCodeBlock;
			for(int counter = 0; counter < positiveNumbers(); counter++){
				this.activeCodeBlock = currentInstruction; 
				instructions();
			}
			break; 
		default: 
			throw new IllegalValueException("Ill formed Karel Code" + this.karelCode.get(this.activeCodeBlock)); 
		}
		
	}
	
	public void conditional(){ //if statement
		if(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.IFSTATEMENT)) return; //error
		if(!this.next()) throw new IllegalValueException("Ill formed code"); 
		boolean result = variable(); 
		if(result){
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code");
			instructions(); 
		}
		else{ 
			do{
				this.next(); 
			}while(!this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ENDBLOCK));
		}
		if(!this.next()) return; 
		if(this.karelCode.get(this.activeCodeBlock).equals(KarelCode.ELSESTATEMENT) && !result){
			if(!this.next()) throw new IllegalValueException("Ill formed Karel Code");
			instructions(); 
		}
	}
	
	public void operation(){
		
	}
	
	public boolean variable(){
		return false; 
	}
	
	public int positiveNumbers(){
		String number = "";
		
		return Integer.parseInt(number); 
	}
	
	public void positiveNumber(){
		
	}
	
	public static void main(String[] args){
		World world = new World("test_world",5, 5);
		Level level = new Level(world, "testing the parser");
		
		if(world.addCreature(new Creature("Eve", new Coordinate(0,0)))) System.out.println("added eve");
		else System.out.println("unable to add eve");
		
		level.addKarelCode(KarelCode.MOVE);
		level.addKarelCode(KarelCode.IFSTATEMENT);
		level.addKarelCode(KarelCode.FRONTISCLEAR);
		level.addKarelCode(KarelCode.MOVE);
		level.addKarelCode(KarelCode.ENDBLOCK);
		Parser parser = new Parser(level.getKarelCode(), world);
		
		if(world.getEve() == null) return;
		
		System.out.println("-----------");
		world.printWorld();
		System.out.println("-----------");
		parser.start(); 
		System.out.println("-----------");
		world.printWorld();
		System.out.println("-----------");
	}
}
