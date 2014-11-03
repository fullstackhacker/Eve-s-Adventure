package models.campaign;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A static class for all objectives that are valid
 * 
 * Can only have one per level
 */
public class Objectives {
	/**
	 * Pick up the amount of bamboo specified in the level file
	 */
	public static final String PICKXBAMBOO = "pick up x bamboo";
	/**
	 * Must use at least one if statement 
	 */
	public static final String IFSTATEMENT = "use an if statement"; 
	/**
	 * Must use at least one if/else statement
	 */
	public static final String IFELSESTATEMENT = "use an if/else statement";
	/**
	 * Must use at least one while statement
	 */
	public static final String WHILESTATEMENT = "use a while statement";
	/**
	 * Must use at least one loop statement
	 */
	public static final String LOOP = "use a loop statement";
	/**
	 * Gets all the possible objectives that a level can have
	 * 
	 * @return  the list of all possible objectives
	 */
	public static Iterator<String> getPossibleObjectives(){ 
		ArrayList<String> possibleObjectives = new ArrayList<String>(); 
		possibleObjectives.add(Objectives.PICKXBAMBOO); 
		possibleObjectives.add(Objectives.IFSTATEMENT);
		possibleObjectives.add(Objectives.IFELSESTATEMENT);
		possibleObjectives.add(Objectives.WHILESTATEMENT);
		possibleObjectives.add(Objectives.LOOP);
		return possibleObjectives.iterator(); 
	}
	/**
	 * Checks to see if the objective is valid
	 * 
	 * @param objective  the objective to check 
	 * @return  true iff the objective is valid
	 */
	public static boolean validate(String objective){
		Iterator<String> possibleObjectives = getPossibleObjectives();
		while(possibleObjectives.hasNext()){
			if(possibleObjectives.next().equals(objective)){
				return true;
			}
		}
		return false;
	}
}
