package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import models.campaign.Campaign;
import models.campaign.Level;
import models.campaign.World;


/**
 * Loads data from serialized file
 */
public class Load {
	private static final String DATADIR = "data" + File.separator; 
	private static final String WORLDDIR = Load.DATADIR + "worlds" + File.separator; 
	private static final String LEVELDIR = Load.DATADIR + "levels" + File.separator; 
	private static final String CAMPAIGNDIR = Load.DATADIR + "campaigns" + File.separator; 
	private static final String WORLDEXT = ".world"; 
	private static final String NAMEFILENAME = "name.dat"; 
	private static final String DESCRIPTIONFILENAME = "description.dat";
	private static final String OBJECTIVEFILENAME = "objectives.dat";
	private static final String CURRENTLEVELFILENAME = "currentLevel.dat";
	private static final String KARELCODEFILENAME = "karelcode.dat";
	private static final String BAMBOOBJECTIVEFILENAME = "bambooObjective.dat";
	
	/**
	 * Loads the serialized file, deserializes it, and creates the World
	 * 
	 * @param worldName The name of the world to load
	 */
	public static World loadWorld(String worldName){
		/* check if the world directory exists */
		File worldsDir = new File(Load.WORLDDIR); 
		if(!worldsDir.exists()) return null; //file does not exist
		
		/* create the path and check if file exists */
		String worldPath = Load.WORLDDIR + worldName + Load.WORLDEXT; 
		File worldFile = new File(worldPath); 
		if(!worldFile.exists()) return null; //file does not exist
		
		/* create streams */
		FileInputStream fis = null; 
		ObjectInputStream ois = null; 
		
		/* load the world */
		World world; 
		try{ 
			fis = new FileInputStream(worldFile); 
			ois = new ObjectInputStream(fis); 
			world = (World) ois.readObject(); 
			ois.close(); 
			fis.close(); 
		}
		catch(Exception e){
			//failed to load the world
			return null; 
		}
		
		/* return the world */
		return world; 
	}
	
	/**
	 * Loads the serialized file, deserializes it, and creates the Level
	 * 
	 * @param levelName The name of the level to load
	 * @param campaignPath The path to campaign's directory if the level is part of a campaign, otherwise null.
	 */
	public static Level loadLevel(String levelName, String campaignPath){
		/* check if the proper directory exists  */
		if(campaignPath == null){ //loading from data/levels
			
		}
		else { //loading from data/campaigns/<campaign_name>/levels
			
		}
		
		
		return null; 
	}
	
	/**
	 * Loads the serialized file, deserializes it, and creates the Campaign
	 * 
	 * @param campaignName The name of the campaign to load
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
