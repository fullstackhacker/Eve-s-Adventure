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
	 * List of the levels in the campaign
	 */
	private ArrayList<Level> levels; 
	/**
	 * Description of the campaign
	 */
	private String description; 
	/**
	 * Constructor to create a blank campaign
	 * 
	 * @param description - the description for the campaign
	 */
	public Campaign(String description){
		
	}
	/**
	 * Constructor to create a campaign with an initial level
	 * 
	 * @param level - the initial level for the campaign
	 * @param description - the description for the campaign
	 */
	public Campaign(Level level, String description){ 
		
	}
	public void addLevel(Level level){
		
	}
	public void removeLevel(int position){
		
	}
}
