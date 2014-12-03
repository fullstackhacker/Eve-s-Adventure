package models.campaign;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A static class for the karel code segments that is valid
 */
public class KarelCode {
	/**
	 * Karel ifstatement 
	 */
	public static final String IFSTATEMENT = "if("; 
	/**
	 * Karel end of if statement  
	 */
	public static final String ENDIF = "ENDIF\n";
	public static final String ENDELSE = "ENDELSE\n"; 
	public static final String ENDWHILE = "ENDWHILE\n"; 
	public static final String ENDLOOP = "ENDLOOP\n";
	/**
	 * Karel else statement 
	 */
	public static final String ELSESTATEMENT = "else{";
	/**
	 * Karel while statement
	 */
	public static final String WHILESTATEMENT = "while(";
	/**
	 * Karel loop statement
	 */
	public static final String LOOPSTATEMENT = "loop(";
	/**
	 * close statement
	 */
	public static final String CLOSESTATEMENT = "){\n";
	/**
	 * Karel move function
	 */
	public static final String MOVE = "move();\n"; 
	/**
	 * Karel sleep function
	 */
	public static final String SLEEP = "sleep();\n"; 
	/**
	 * Karel wake up function
	 */
	public static final String WAKEUP = "wakeUp();\n"; 
	/**
	 * Karel turn left function
	 */
	public static final String TURNRIGHT = "turnright();\n";
	/**
	 * Karel pick up bamboo function
	 */
	public static final String PICKBAMBOO = "pickbamboo();\n";
	/**
	 * Karel put down bamboo
	 */
	public static final String PUTBAMBOO = "putbamboo();\n"; 
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
	public static final String ONE = "1"; 
	public static final String TWO = "2"; 
	public static final String THREE = "3"; 
	public static final String FOUR = "4"; 
	public static final String FIVE = "5"; 
	public static final String SIX  = "6"; 
	public static final String SEVEN = "7"; 
	public static final String EIGHT = "8"; 
	public static final String NINE = "9"; 
	public static final String ZERO = "0"; 
	
	/**
	 * Gets all the possible Karel Code blocks that can exist
	 * 
	 * @return  all possible karel code blocks
	 */
	public static Iterator<String> getPossibleKarelCode(){ 
		ArrayList<String> possibleKarelCode = new ArrayList<String>(); 
		possibleKarelCode.add(KarelCode.IFSTATEMENT); 
		possibleKarelCode.add(KarelCode.ELSESTATEMENT);
		possibleKarelCode.add(KarelCode.WHILESTATEMENT);
		possibleKarelCode.add(KarelCode.LOOPSTATEMENT);
		possibleKarelCode.add(KarelCode.MOVE);
		possibleKarelCode.add(KarelCode.SLEEP);
		possibleKarelCode.add(KarelCode.WAKEUP);
		possibleKarelCode.add(KarelCode.TURNRIGHT);
		possibleKarelCode.add(KarelCode.PICKBAMBOO);
		possibleKarelCode.add(KarelCode.PUTBAMBOO);
		possibleKarelCode.add(KarelCode.FRONTISCLEAR);
		possibleKarelCode.add(KarelCode.NEXTTOAFRIEND);
		possibleKarelCode.add(KarelCode.FACINGNORTH);
		possibleKarelCode.add(KarelCode.FACINGSOUTH);
		possibleKarelCode.add(KarelCode.FACINGEAST);
		possibleKarelCode.add(KarelCode.FACINGWEST);
		possibleKarelCode.add(KarelCode.BAGISEMPTY);
		return possibleKarelCode.iterator();
	}
	/**
	 * Gets all the conditionals 
	 * 
	 * @return  a list of valid conditionals 
	 */
	public static Iterator<String> getPossibleConditionals(){ 
		ArrayList<String> possibleKarelCode = new ArrayList<String>();
		possibleKarelCode.add(KarelCode.IFSTATEMENT); 
		possibleKarelCode.add(KarelCode.ELSESTATEMENT);

		return possibleKarelCode.iterator(); 
	}
	/**
	 * Gets all the operations
	 * 
	 * @return  a list of valid operations
	 */
	public static Iterator<String> getPossibleOperations(){ 
		ArrayList<String> possibleKarelCode = new ArrayList<String>(); 
		possibleKarelCode.add(KarelCode.MOVE);
		possibleKarelCode.add(KarelCode.SLEEP);
		possibleKarelCode.add(KarelCode.WAKEUP);
		possibleKarelCode.add(KarelCode.TURNRIGHT);
		possibleKarelCode.add(KarelCode.PICKBAMBOO);
		possibleKarelCode.add(KarelCode.PUTBAMBOO);
		return possibleKarelCode.iterator();
	}
	/**
	 * Gets all the possible repetitions
	 *  
	 * @return  a list of valid repetitions
	 */
	public static Iterator<String> getPossibleRepetitions(){ 
		ArrayList<String> possibleKarelCode = new ArrayList<String>();
		possibleKarelCode.add(KarelCode.WHILESTATEMENT);
		possibleKarelCode.add(KarelCode.LOOPSTATEMENT);
		return possibleKarelCode.iterator();
	}
	/**
	 * Gets all the possible variables
	 * 
	 * @return  a list of valid variables
	 */
	public static Iterator<String> getPossibleVariables(){
		ArrayList<String> possibleKarelCode = new ArrayList<String>(); 
		possibleKarelCode.add(KarelCode.FRONTISCLEAR);
		possibleKarelCode.add(KarelCode.NEXTTOAFRIEND);
		possibleKarelCode.add(KarelCode.FACINGNORTH);
		possibleKarelCode.add(KarelCode.FACINGSOUTH);
		possibleKarelCode.add(KarelCode.FACINGEAST);
		possibleKarelCode.add(KarelCode.FACINGWEST);
		possibleKarelCode.add(KarelCode.BAGISEMPTY);
		return possibleKarelCode.iterator(); 
	}
	/** Gets all the numbers in karel
	 * 
	 * @retun an interator that goes through all the numbers in Karel
	 */
	public static Iterator<String> getPossibleDigits(){
		ArrayList<String> possibleKarelCode = new ArrayList<String>();
		possibleKarelCode.add(KarelCode.ONE); 
		possibleKarelCode.add(KarelCode.TWO); 
		possibleKarelCode.add(KarelCode.THREE);
		possibleKarelCode.add(KarelCode.FOUR); 
		possibleKarelCode.add(KarelCode.FIVE);
		possibleKarelCode.add(KarelCode.SIX); 
		possibleKarelCode.add(KarelCode.SEVEN); 
		possibleKarelCode.add(KarelCode.EIGHT); 
		possibleKarelCode.add(KarelCode.NINE); 
		possibleKarelCode.add(KarelCode.ZERO); 
		return possibleKarelCode.iterator(); 
	}
	/**
	 * Validates the code block to see if it is karel code
	 * 
	 * @param karelCode  the karel code to check
	 * @return  true iff the karel code is valid
	 */
	public static boolean validate(String karelCode){ 
		Iterator<String> karelCodes = KarelCode.getPossibleKarelCode();
		while(karelCodes.hasNext()){
			if(!karelCode.equals(karelCodes.next())) return false; 
		}
		return true; 
	}
}
