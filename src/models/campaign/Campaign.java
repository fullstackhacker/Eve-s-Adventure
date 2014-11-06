package models.campaign;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A campaign is a set of level that has a story
 * 
 * A campaign must always have a description
 */
public class Campaign implements Serializable{
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 12L;
	/**
	 * Name of the campaign
	 */
	private String name; 
	/**
	 * List of the levels in the campaign
	 */
	private ArrayList<Level> levels; 
	/**
	 * Description of the campaign
	 */
	private String description; 
	/**
	 * Current level that the player is on
	 */
	private int currentLevel; 	
	/**
	 * Constructor to create a blank campaign
	 * 
	 * @param description  the description for the campaign
	 */
	public Campaign(String name, String description){
		this.name = name; 
		this.description = description; 
		this.levels = new ArrayList<Level>();
		this.currentLevel = 0;
	}
	/**
	 * Constructor to create a campaign with an initial level
	 * 
	 * @param level  the initial level for the campaign
	 * @param description  the description for the campaign
	 */
	public Campaign(String name, String description, Level level){ 
		this.name = name; 
		this.description = description; 
		this.levels = new ArrayList<Level>();
		this.levels.add(level);
		this.currentLevel = 0;
	}
	/**
	 * Constructor to create a campaign with all of its parts. To be used in Loading Campaigns
	 * 
	 * @param name The current name of the campaign
	 * @param levels The current levels in the campaign
	 * @param description The current description of the campaign
	 * @param currentLevel The current level that the user is on
	 */
	public Campaign(String name, ArrayList<Level> levels, String description, int currentLevel){ 
		
	}
	/**
	 * Gets the name of the campaign
	 * 
	 * @return  the name of the campaign
	 */
	public String getName(){
		return this.name; 
	}
	/**
	 * Gets the levels in the campaign
	 * 
	 * @return the list of levels in this campaign
	 */
	public ArrayList<Level> getLevels(){ 
		return this.levels; 
	}
	/**
	 * Gets the description of the campaign
	 * 
	 * @return the string representation of the campaign description
	 */
	public String getDescription(){ 
		return this.description; 
	}
	/**
	 * Gets the current level 
	 * 
	 * @return The user's current progress through the campaign
	 */
	public int getCurrentLevel(){ 
		return this.currentLevel; 
	}
	/**
	 * Changes the name of the campaign
	 * 
	 * @param name   the new name of the campaign
	 */
	public void replaceName(String name){
		
	}
	/**
	 * Adds a level to the end of the campaign
	 * 
	 * @param level  the level to add
	 */
	public void addLevel(Level level){
		this.levels.add(level);
	}
	/**
	 * Move a level around in the campaign
	 * 
	 * @param oldPosition  the old position of the level 
	 * @param newPosition  the new position of the level
	 */
	public void moveLevel(int oldPosition, int newPosition){
		
	}
	/**
	 * Inserts a new level at a specified location
	 * 
	 * @param level  the level to add
	 * @param position  the position of the level to add
	 */
	public void insertLevel(Level level, int position){
		
	}
	/**
	 * Remove a level from the campaign
	 * 
	 * @param position  the position of the level to remove
	 */
	public void removeLevel(int position){
		
	}
	/**
	 * Change the description for the campaign
	 * 
	 * @param description  the new description of the campaign
	 */
	public void changeDescription(String description){
		
	}
	/**
	 * Gets the next level in the campaign
	 * 
	 * @return  the next level in the campaign
	 */
	public Level nextLevel(){ 
		return null; 
	}
	/**
	 * Changes the current level in the campaign and returns it
	 * 
	 * @param newCurrentLevel  the position of the wanted level in the campaign
	 * @return  the new current levelS
	 */
	public Level changeCurrentLevel(int newCurrentLevel){ 
		return null;
	}
}
