package controllers;

import java.util.ArrayList;

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
	 * Constructor for the parser
	 * 
	 * @param karelCode  the Karel code to parse and execute
	 */
	public Parser(ArrayList<String> karelCode){ 
		 
	}
	/**
	 * Moves onto the next code block WITHOUT executing it
	 */
	public void next(){
		
	}
	/**
	 * Moves to the previous code block WITHOUT executing it
	 */
	public void previous(){
		
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
	public void setPosition(int activeCodeBlock){
		
	}
	/**
	 * Resets the parser back to the first code block
	 */
	public void reset(){
		
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
		
	}
}
