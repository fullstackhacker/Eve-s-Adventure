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
	private static final String WORLDDIR = DATADIR + "worlds" + File.separator; 
	private static final String WORLDEXT = ".world"; 

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
		
		return true; 
	}
	
	/**
	 * Serializes the current Level and stores it in a file
	 * 
	 * @param level  the level to save
	 */
	public static void saveLevel(Level level){
		
	}
	
	
	/**
	 * Serializes the current Campaign and stores it in a file
	 * 
	 * @param campaign  the campaign to save
	 */
	public static void saveCampaign(Campaign campaign){
		
	}
}
