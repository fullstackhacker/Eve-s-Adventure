package controllers;

import java.util.ArrayList;
import java.util.Random;

import models.Coordinate;
import models.campaign.KarelCode;
import models.campaign.Level;
import models.campaign.World;
import models.gridobjects.creatures.Creature;
import models.gridobjects.items.Bamboo;

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
	
	public void start(){ 
	
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
		level.addKarelCode(KarelCode.ENDIFSTATEMENT);
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
