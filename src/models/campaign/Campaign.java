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
	public Campaign(String description){
		
	}
	/**
	 * Constructor to create a campaign with an initial level
	 * 
	 * @param level  the initial level for the campaign
	 * @param description  the description for the campaign
	 */
	public Campaign(Level level, String description){ 
		
	}
	/**
	 * Gets the name of the campaign
	 * 
	 * @return  the name of the campaign
	 */
	public String getName(){
		return null; 
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
