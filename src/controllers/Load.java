package controllers;

import java.util.ArrayList;

import models.campaign.Campaign;
import models.campaign.Level;
import models.campaign.World;


/**
 * Loads data from serialized file
 */
public class Load {

	
	/**
	 * Loads the serialized file, deserializes it, and creates the World
	 * 
	 * @param world  the world to load
	 */
	public static World loadWorld(String worldName){
		return null; //compiler
	}
	
	/**
	 * Loads the serialized file, deserializes it, and creates the Level
	 * 
	 * @param level the level to load
	 */
	public static Level loadLevel(String levelName){
		return null; 
	}
	
	/**
	 * Loads the serialized file, deserializes it, and creates the Campaign
	 * 
	 * @param campaign the campaign to load
	 */
	public static Campaign loadCampaign(String campaignName){
		return null; 
	}
	
	/**
	 * Gets the worlds that aren't associated with a level
	 * 
	 * @return A list of world names
	 */
	public static ArrayList<String> getWorlds(){	
		 return null;
	}
	
	/**
	 * Gets the levels that aren't associated with a campaign
	 * 
	 * @return A list of level names
	 */
	public static ArrayList<String> getLevels(){ 
		return null; 
	}
	
	/**
	 * Gets a list of the different campaigns
	 * 
	 * @return A list of campaign names
	 */
	public static ArrayList<String> getCampaigns(){ 
		return null; 
	}
	
	
}
