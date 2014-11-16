package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import models.campaign.Campaign;
import models.campaign.Level;
import models.campaign.World;

/**
 * Serializes data from and saves to a file
 */
public class Save {
	private static final String DATADIR = "data" + File.separator; 
	private static final String WORLDDIR = Save.DATADIR + "worlds" + File.separator; 
	private static final String LEVELDIR = Save.DATADIR + "levels" + File.separator; 
	private static final String CAMPAIGNDIR = Save.DATADIR + "campaigns" + File.separator; 
	private static final String WORLDEXT = ".world"; 
	private static final String NAMEFILENAME = "name.dat"; 
	private static final String DESCRIPTIONFILENAME = "description.dat";
	private static final String OBJECTIVEFILENAME = "objectives.dat";
	private static final String CURRENTLEVELFILENAME = "currentLevel.dat";
	private static final String KARELCODEFILENAME = "karelcode.dat";
	private static final String BAMBOOBJECTIVEFILENAME = "bambooObjective.dat";

	/**
	 * Serializes the current World and stores it in a file
	 * 
	 * @param world  the world to save
	 * @return true iff the world was saved
	 */
	public static boolean saveWorld(World world){
		/* check if the worlds directory exists */
		File worldsDir = new File(Save.WORLDDIR); 
		if(!worldsDir.exists()) worldsDir.mkdirs();
		
		/* create the save path and the output streams */
		String worldPath = WORLDDIR + world.getName() + WORLDEXT; 
		FileOutputStream fos = null;
		ObjectOutputStream oos = null; 
		
		/* save the world */
		try{ 
			fos = new FileOutputStream(new File(worldPath));
			oos = new ObjectOutputStream(fos); 
			oos.writeObject(world); 
			oos.close();
			fos.close(); 
		}
		catch(Exception e){ 
			System.out.println("Unable to save the file"); 
			return false; 
		}
		return true; //saved successfully 
	}
	
	/**
	 * Serializes the current Level and stores it in a file
	 * 
	 * @param level  the level to save
	 * @return true iff able to save the level 
	 */
	public static boolean saveLevel(Level level, String campaignPath){
		/* check if the level exists */
		File levelsDir = new File(Save.LEVELDIR);
		if(!levelsDir.exists()) levelsDir.mkdirs(); 
		
		/* create the path and the output stream objects */
		String levelPath = campaignPath == null? Save.LEVELDIR + level.getName() + File.separator : campaignPath + level.getName() + File.separator; 
		FileOutputStream fos = null; 
		ObjectOutputStream oos = null; 
		
		/* create the directory for the level */
		File levelDir = new File(levelPath); 
		levelDir.mkdirs(); 
		
		/* save the world */
		String worldPath = levelPath + File.separator + level.getWorld().getName() + Save.WORLDEXT; 
		try{ 
			fos = new FileOutputStream(new File(worldPath)); 
			oos = new ObjectOutputStream(fos); 
			oos.writeObject(level.getWorld()); 
			oos.close(); 
			fos.close(); 
		}
		catch(Exception e){ 
			//unable to save the world
			return false; 
		}
		
		/* save the karel code */
		String karelCodePath = levelPath + File.separator + Save.KARELCODEFILENAME; 
		try{
			fos = new FileOutputStream(new File(karelCodePath)); 
			oos = new ObjectOutputStream(fos); 
			oos.writeObject(level.getKarelCode()); 
			oos.close(); 
			fos.close();
		}
		catch(Exception e){
			//unable to save karel code
			return false; 
		}
		
		/* save the description */ 
		String descriptionPath = levelPath + File.separator + Save.DESCRIPTIONFILENAME; 
		try{ 
			fos = new FileOutputStream(new File(descriptionPath)); 
			oos = new ObjectOutputStream(fos);
			oos.writeObject(level.getDescription());
			oos.close(); 
			fos.close();
		}
		catch(Exception e){ 
			//unable to save the description
			return false; 
		}
		
		/* save the objectives */
		String objectivePath = levelPath + File.separator + Save.OBJECTIVEFILENAME; 
		try{ 
			fos = new FileOutputStream(new File(objectivePath)); 
			oos = new ObjectOutputStream(fos);
			oos.writeObject(level.getObjectives());
			oos.close();
			fos.close(); 
		}
		catch(Exception e){ 
			//unable to save the objectives
			return false; 
		}
		
		/* save the bamboo objectives */
		String bambooObjectivesPath = levelPath + File.separator + Save.BAMBOOBJECTIVEFILENAME; 
		try{ 
			fos = new FileOutputStream(new File(bambooObjectivesPath));
			oos = new ObjectOutputStream(fos); 
			oos.writeObject(new Integer(level.getBambooObjective()));
			oos.close(); 
			fos.close(); 
		}
		catch(Exception e){ 
			//unable to save the bamboo objectives
			return false; 
		}
		
		return true; //saved the level 
	}
	
	
	/**
	 * Serializes the current Campaign and stores it in a file
	 * 
	 * @param campaign  the campaign to save
	 */
	public static boolean saveCampaign(Campaign campaign){
		/* check to see if the campaigns folder exists */
		File campaignsDir = new File(Save.CAMPAIGNDIR); 
		if(!campaignsDir.exists()) campaignsDir.mkdirs(); 
		
		/* set up the campaign path and output stream objects */
		String campaignPath = Save.CAMPAIGNDIR + campaign.getName() + File.separator; 
		FileOutputStream fos = null;
		ObjectOutputStream oos = null; 
		
		/* create the directory for the campaign */
		File campaignDir = new File(campaignPath); 
		campaignDir.mkdir(); 
		
		/* save the levels */
		for(Level level : campaign.getLevels()){
			if(!saveLevel(level, campaignPath + "levels" + File.separator)) return false; //error saving one of the levels
		}
		
		/* save the name */
		String namePath = campaignPath + Save.NAMEFILENAME; 
		try{ 
			fos = new FileOutputStream(new File(namePath)); 
			oos = new ObjectOutputStream(fos); 
			oos.writeObject(campaign.getName()); 
			oos.close(); 
			fos.close();
		}
		catch(Exception e){
			//unable to save the name
			return false; 
		}
		
		/* save the description */
		String descriptionPath = campaignPath + Save.DESCRIPTIONFILENAME; 
		try{ 
			fos = new FileOutputStream(new File(descriptionPath)); 
			oos = new ObjectOutputStream(fos); 
			oos.writeObject(campaign.getDescription()); 
			oos.close(); 
			fos.close(); 
		}
		catch(Exception e){
			//unable to save the description
			return false; 
		}
		
		/* save the current level */
		String currentLevelPath = campaignPath + Save.CURRENTLEVELFILENAME; 
		try{ 
			fos = new FileOutputStream(new File(currentLevelPath)); 
			oos = new ObjectOutputStream(fos);
			oos.writeObject(new Integer(campaign.getCurrentLevel())); 
			oos.close();
			fos.close(); 
		}
		catch(Exception e){ 
			//unable to save the current level 
			return false; 
		}
		
		return true;  //save the campaign successfully
	}
	
	public static void main(String args[]){
		World world = new World("My_World", 1, 1); 
		
		Level level = new Level(world, "My_World description");
		
		Campaign campaign = new Campaign("My_Campaign", "mycampaign description"); 
		campaign.addLevel(level);
		
		Campaign campaign2 = new Campaign("c2", "asdfa");
		campaign2.addLevel(new Level(new World("asdfasdf", 1, 1), "description"));
		
		Save.saveCampaign(campaign2); 
	}
}
