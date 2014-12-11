package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import views.scenes.SandboxScene;
import models.campaign.Campaign;
import models.campaign.Level;
import models.campaign.World;

/**
 * Loads data from serialized file
 */
public class Load {
	private static final String DATADIR = "data" + File.separator;
	private static final String WORLDDIR = Load.DATADIR + "worlds"
			+ File.separator;
	private static final String LEVELDIR = Load.DATADIR + "levels"
			+ File.separator;
	private static final String CAMPAIGNDIR = Load.DATADIR + "campaigns"
			+ File.separator;
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
	 * @param worldName
	 *            The name of the world to load
	 */
	public static World loadWorld(String worldName) {
		/* check if the world directory exists */
		File worldsDir = new File(Load.WORLDDIR);
		if (!worldsDir.exists())
			return null; // file does not exist

		/* create the path and check if file exists */
		String worldPath = Load.WORLDDIR + worldName + Load.WORLDEXT;
		File worldFile = new File(worldPath);
		if (!worldFile.exists())
			return null; // file does not exist

		/* create streams */
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		/* load the world */
		World world;
		try {
			fis = new FileInputStream(worldFile);
			ois = new ObjectInputStream(fis);
			world = (World) ois.readObject();
			SandboxScene.getInstance().setWorld(world);
			ois.close();
			fis.close();
		} catch (Exception e) {
			// failed to load the world
			return null;
		}

		/* return the world */
		return world;
	}

	/**
	 * Loads the serialized file, deserializes it, and creates the Level
	 * 
	 * @param levelName
	 *            The name of the level to load
	 * @param campaignPath
	 *            The path to campaign's directory if the level is part of a
	 *            campaign, otherwise null.
	 */
	@SuppressWarnings("unchecked")
	public static Level loadLevel(String levelName, String campaignPath) {
		/* check if the proper directory exists */
		String levelsPath = ""; // path to the levels directory
		if (campaignPath == null) { // loading from data/levels
			levelsPath = Load.LEVELDIR;
			File levelsDir = new File(levelsPath);
			if (!levelsDir.exists())
				return null; // levels folder doesn't exist
		} else { // loading from data/campaigns/<campaign_name>/levels
			levelsPath = campaignPath;
			File levelsDir = new File(campaignPath);
			if (!levelsDir.exists())
				return null; // levels folder doesnt exist in the campaigns
								// folder
		}

		/* create the load path and the input streams */
		String levelPath = levelsPath + levelName + File.separator;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		/* check if the level directory exists */
		File levelDir = new File(levelsPath);
		if (!levelDir.exists())
			return null;

		/* load the world */
		String worldPath = levelPath + levelName + Load.WORLDEXT;
		World world = null;

		File worldFile = new File(worldPath);
		if (!worldFile.exists())
			return null; // world file doesn't exist

		try {
			fis = new FileInputStream(worldFile);
			ois = new ObjectInputStream(fis);
			Object temp = ois.readObject();
			world = temp instanceof World ? (World) temp : null;
			ois.close();
			fis.close();
		} catch (Exception e) {
			return null;
		}

		/* load the objectives */
		String objectivesPath = levelPath + Load.OBJECTIVEFILENAME;
		ArrayList<String> objectives = null;

		File objectiveFile = new File(objectivesPath);
		if (!objectiveFile.exists())
			return null; // objective file doesn't exist

		try {
			fis = new FileInputStream(objectiveFile);
			ois = new ObjectInputStream(fis);
			objectives = (ArrayList<String>) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			return null;
		}

		/* load the karel code */
		String karelCodePath = levelPath + Load.KARELCODEFILENAME;
		ArrayList<String> karelCode = null;

		File karelCodeFile = new File(karelCodePath);
		if (!karelCodeFile.exists())
			return null; // karel code file doesn't exist

		try {
			fis = new FileInputStream(karelCodeFile);
			ois = new ObjectInputStream(fis);
			karelCode = (ArrayList<String>) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			return null;
		}

		/* load the description */
		String descriptionPath = levelPath + Load.DESCRIPTIONFILENAME;
		String description = null;

		File descriptionFile = new File(descriptionPath);
		if (!descriptionFile.exists())
			return null;

		try {
			fis = new FileInputStream(new File(descriptionPath));
			ois = new ObjectInputStream(fis);
			description = (String) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			return null;
		}

		/* load the bambooo objective */
		String bambooObjectivePath = levelPath + Load.BAMBOOBJECTIVEFILENAME;
		Integer bambooObjective = null;

		File bambooObjectiveFile = new File(bambooObjectivePath);
		if (!bambooObjectiveFile.exists())
			return null;

		try {
			fis = new FileInputStream(new File(bambooObjectivePath));
			ois = new ObjectInputStream(fis);
			bambooObjective = (Integer) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			return null;
		}

		/* create the level */
		Level level = new Level(world, description, objectives, karelCode,
				bambooObjective.intValue());

		/* return the level */
		return level;
	}

	/**
	 * Loads the serialized file, deserializes it, and creates the Campaign
	 * 
	 * @param campaignName
	 *            The name of the campaign to load
	 */
	public static Campaign loadCampaign(String campaignName) {
		/* check if campaigns directory exists */
		File campaignsDir = new File(Load.CAMPAIGNDIR);
		if (!campaignsDir.exists())
			return null; // campaign doesn't exist

		/* create the path to the campaign and the input streams */
		String campaignPath = Load.CAMPAIGNDIR + campaignName + File.separator;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		/* check if the campaign exists */
		File campaignDir = new File(campaignPath);
		if (!campaignDir.exists())
			return null;

		/* get level names and check if they exist */
		File levelsDir = new File(campaignPath + "levels" + File.separator);
		if (!levelsDir.exists())
			return null;
		String levelNames[] = levelsDir.list();

		/* load the levels */
		ArrayList<Level> levels = new ArrayList<Level>();
		for (String levelName : levelNames)
			levels.add(Load.loadLevel(levelName, campaignPath + "levels"
					+ File.separator));

		/* load the name */
		String namePath = campaignPath + Load.NAMEFILENAME;
		String name = null;

		File nameFile = new File(namePath);
		if (!nameFile.exists())
			return null; // name file doesn't exist

		try {
			fis = new FileInputStream(nameFile);
			ois = new ObjectInputStream(fis);
			name = (String) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			return null;
		}

		/* load the description */
		String descriptionPath = campaignPath + Load.DESCRIPTIONFILENAME;
		String description = null;

		File descriptionFile = new File(descriptionPath);
		if (!descriptionFile.exists())
			return null; // description file doesn't exist

		try {
			fis = new FileInputStream(descriptionFile);
			ois = new ObjectInputStream(fis);
			description = (String) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			return null;
		}

		/* load the current level */
		String currentLevelPath = campaignPath + Load.CURRENTLEVELFILENAME;
		Integer currentLevel = null;

		File currentLevelFile = new File(currentLevelPath);
		if (!currentLevelFile.exists())
			return null; // current level file doesn't exist

		try {
			fis = new FileInputStream(currentLevelPath);
			ois = new ObjectInputStream(fis);
			currentLevel = (Integer) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			return null;
		}

		/* create the campaign */
		Campaign campaign = new Campaign(name, levels, description,
				currentLevel);

		/* return the campaign */
		return campaign;
	}

	/**
	 * Gets the worlds that aren't associated with a level
	 * 
	 * @return A list of world names
	 */
	public static ArrayList<String> getWorlds() {
		return null;
	}

	/**
	 * Gets the levels that aren't associated with a campaign
	 * 
	 * @return A list of level names
	 */
	public static ArrayList<String> getLevels() {
		return null;
	}

	/**
	 * Gets a list of the different campaigns
	 * 
	 * @return A list of campaign names
	 */
	public static ArrayList<String> getCampaigns() {
		return null;
	}

}
