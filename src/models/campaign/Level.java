package models.campaign;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A level represents a combination of karel code, a world, and a set of objectives
 * 
 * A level must always have a description
 * 
 */
public class Level implements Serializable {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 11L;
	/**
	 * Name of the level
	 */
	private String name; 
	/**
	 * The world for the level
	 */
	private World world; 
	/**
	 * The set of Karel Code in the level 
	 */
	private ArrayList<String> karelCode;
	/**
	 * The set of Objects in the level 
	 */
	private ArrayList<String> objectives; 
	/**
	 * Designates how many bamboo must be picked up 
	 * Only has a value if the bamboo objective is enabled
	 * 
	 * 1 designates the bamboo objective is not enabled	 *  
	 */
	private int bambooObjective; 
	/**
	 * Description about the level 
	 */
	private String description; 
	/**
	 * Constructor
	 * 
	 * @param world  the world for the level to exist in 
	 * @param description  the description for the level
	 */
	public Level(World world, String description){ 
		bambooObjective = 1; 
	}
	/**
	 * Gets the name of the level
	 * 
	 * @return the name of the level
	 */
	public String getName(){
		return null; 
	}
	/**
	 * Replace the name of the level
	 * 
	 * @param name  the new name of the level
	 */
	public void replaceName(String name){
		
	}
	/**
	 * Gets the world for the level 
	 * 
	 * @return  The current world for the level
	 */
	public World getWorld(){ 
		return null; //shh compiler go to sleep
	}
	/**
	 * Gets the objectives in the level 
	 * 
	 * @return  the objectives in the world
	 */
	public ArrayList<String> getObjectives(){ 
		return null; //shh
	}
	/**
	 * Gets the Karel Code in the level
	 * 
	 * @return  the karel code in the world
	 */
	public ArrayList<String> getKarelCode(){
		return null; //shh
	}
	/**
	 * Change the world in the level
	 * 
	 * @param world   the new world for the level
	 */
	public void changeWorld(World world){ 
		
	}
	/**
	 * Adds the Karel Code to the end of the list
	 * 
	 * @param karelCode  the new code string to add
	 */
	public void addKarelCode(String karelCode){ 
		
	}
	/**
	 * Inserts Karel Code into a specific location in the list
	 *  
	 * @param karelCode  the Karel code to add the list 
	 * @param position  the position in the list to add it too
	 * @return boolean  true iff able to add the karel code to that position
	 */
	public boolean insertKarelCode(String karelCode, int position){
		return false; //replace
	}
	/**
	 * Change the position of a Karel code segment
	 * 
	 * @param oldposition  the old position of the code segment
	 * @param newposition  the new position of the code segment
	 * @return  true iff the position change was a success
	 */
	public boolean changeKarelCodePosition(int oldposition, int newposition){ 
		return false; //this is complicated but cool
	}
	/**
	 * Remove Karel code from the list 
	 * 
	 * @param position  the position of the code in the list
	 */
	public void removeKarelCode(int position){
		
	}
	/**
	 * Adds the objective to the ArrayList
	 * 
	 * @param objective  the new objective to add
	 */
	public void addObjective(String objective){
		
	}
	/**
	 * Removes an objective from the objective list
	 * 
	 * @param position  the position of the objective in the list
	 */
	public void removeObjective(int position){
		
	}
	/**
	 * Overwrites the description. 
	 * 
	 * @param description  the new description for the level 
	 * @return true  iff the old description was overwritten by the new description
	 */
	public boolean overwriteDescription(String description){ 
		return false; //don't you cry
	}
}
