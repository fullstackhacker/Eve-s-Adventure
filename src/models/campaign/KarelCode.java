package models.campaign;

import java.util.ArrayList;

/**
 * A static class for the karel code segments that is valid
 */
public class KarelCode {
	/**
	 * Karel ifstatement 
	 */
	public static final String IFSTATEMENT = "if"; 
	/**
	 * Karel end of if statement  
	 */
	public static final String ENDIFSTATEMENT = "}\n";
	/**
	 * Karel else statement 
	 */
	public static final String ELSESTATEMENT = "else";
	/**
	 * Karel end of else statement 
	 */
	public static final String ENDELSESTATEMENT = "}\n";
	/**
	 * Karel while statement
	 */
	public static final String WHILESTATEMENT = "while";
	/**
	 * Karel end of while statement
	 */
	public static final String ENDWHILESTATEMENT = "}\n";
	/**
	 * Karel loop statement
	 */
	public static final String LOOPSTATEMENT = "loop"; 
	/**
	 * Karel end of loop statement
	 */
	public static final String ENDLOOPSTATEMENT = "}\n";
	/**
	 * Karel move function
	 */
	public static final String MOVE = "move()\n"; 
	/**
	 * Karel sleep function
	 */
	public static final String SLEEP = "sleep()\n"; 
	/**
	 * Karel wake up function
	 */
	public static final String WAKEUP = "wakeUp()\n"; 
	/**
	 * Karel turn left function
	 */
	public static final String TURNLEFT = "turnleft()\n";
	/**
	 * Karel pick up bamboo function
	 */
	public static final String PICKBAMBOO = "pickbamboo()\n";
	/**
	 * Karel put down bamboo
	 */
	public static final String PUTBAMBOO = "putbamboo()\n"; 
	/**
	 * Karel front is clear check
	 */
	public static final String FRONTISCLEAR = "(frontIsClear()){\n"; 
	/**
	 * Karel next to a friend check
	 */
	public static final String NEXTTOAFRIEND = "(nextToAFriend()){\n"; 
	/**
	 * Karel facing north check
	 */
	public static final String FACINGNORTH = "(facingNorth()){\n";
	/**
	 * Karel facing south check
	 */
	public static final String FACINGSOUTH = "(facingSouth()){\n";
	/**
	 * Karel facing east check
	 */
	public static final String FACINGEAST = "(facingEast()){\n";
	/**
	 * Karel facing west check
	 */
	public static final String FACINGWEST = "(facingWest()){\n";
	/** 
	 * Karel bag is empty check
	 */
	public static final String BAGISEMPTY = "(bagIsEmpty){\n";
	
	public static void main(String[] args){
		System.out.print(KarelCode.IFSTATEMENT + KarelCode.FACINGNORTH + KarelCode.MOVE);
	}
	
	/**
	 * Gets all the possible Karel Code blocks that can exist
	 * 
	 * @return  all possible karel code blocks
	 */
	public static ArrayList<String> getPossibleKarelCode(){ 
		return null; 
	}
	/**
	 * Gets all the conditionals 
	 * 
	 * @return  a list of valid conditionals 
	 */
	public static ArrayList<String> getPossibleConditionals(){ 
		return null; 
	}
	/**
	 * Gets all the operations
	 * 
	 * @return  a list of valid operations
	 */
	public static ArrayList<String> getPossibleOperations(){ 
		return null; 
	}
	/**
	 * Gets all the possible repetitions
	 *  
	 * @return  a list of valid repetitions
	 */
	public static ArrayList<String> getPossibleRepetitions(){ 
		return null; 
	}
	/**
	 * Gets all the possible variables
	 * 
	 * @return  a list of valid variables
	 */
	public static ArrayList<String> getPossibleVariables(){
		return null; 
	}
	/**
	 * Validates the code block to see if it is karel code
	 * 
	 * @param karelCode  the karel code to check
	 * @return  true iff the karel code is valid
	 */
	public static boolean validate(String karelCode){ 
		return false;
	}
}
