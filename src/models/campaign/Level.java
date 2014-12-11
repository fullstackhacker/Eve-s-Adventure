package models.campaign;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

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
	 * Description about the level 
	 */
	private String description; 
	/**
	 * 
	 */
	private boolean findObj;
	/**
	 * Constructor
	 * 
	 * @param world  the world for the level to exist in 
	 * @param description  the description for the level
	 */
	public Level(World world, String description){ 
		this.name = world.getName(); 
		this.world = world;
		this.description = description;
		this.karelCode = new ArrayList<String>();
	}
	/**
	 * Constructor - create a level by giving all the parts
	 * 
	 * @param world
	 * @param description
	 * @param objectives
	 * @param karelCode
	 */
	public Level(World world, String description, ArrayList<String> karelCode){
		this.world = world;
		this.name = world.getName();
		this.description = description;
		this.karelCode = karelCode;
	}
	/**
	 * Gets the name of the level
	 * 
	 * @return the name of the level
	 */
	public String getName(){
		return this.name; 
	}
	/**
	 * Replace the name of the level
	 * 
	 * @param name  the new name of the level
	 */
	public void replaceName(String name){
		this.name = name;
		this.world.setName(name);
	}
	/**
	 * Gets the world for the level 
	 * 
	 * @return  The current world for the level
	 */
	public World getWorld(){ 
		return this.world;
	}
	/**
	 * Gets the Karel Code in the level
	 * 
	 * @return  the karel code in the world
	 */
	public ArrayList<String> getKarelCode(){
		return this.karelCode;
	}
	/**
	 * Gets the description for the level
	 * 
	 * @return The string description for the level
	 */
	public String getDescription(){
		return this.description; 
	}
	/**
	 * Change the world in the level
	 * 
	 * @param world   the new world for the level
	 */
	public void changeWorld(World world){ 
		this.world = world;
	}
	/**
	 * Adds the Karel Code to the end of the list
	 * 
	 * @param karelCode  the new code string to add
	 */
	public void addKarelCode(String karelCode){ 
		this.karelCode.add(karelCode);
	}
	/**
	 * Inserts Karel Code into a specific location in the list
	 *  
	 * @param karelCode  the Karel code to add the list 
	 * @param position  the position in the list to add it too
	 * @return boolean  true iff able to add the karel code to that position
	 */
	public boolean insertKarelCode(String karelCode, int position){
		if(this.karelCode.size() > position){
			this.karelCode.add(position, karelCode);
			return true;
		}
		return false;
	}
	/**
	 * Change the position of a Karel code segment
	 * 
	 * @param oldposition  the old position of the code segment
	 * @param newposition  the new position of the code segment
	 * @return  true iff the position change was a success
	 */
	public boolean changeKarelCodePosition(int oldposition, int newposition){ 
		if(oldposition > this.karelCode.size() || oldposition < this.karelCode.size() || newposition < this.karelCode.size() || newposition > this.karelCode.size()){
			return false;
		}
		this.karelCode.add(newposition, this.karelCode.remove(oldposition));
		return true;
	}
	/**
	 * Remove Karel code from the list 
	 * 
	 * @param position  the position of the code in the list
	 */
	public void removeKarelCode(int position){
		if(this.karelCode.size() > position && position >= 0){
			this.karelCode.remove(position);
		}
	}
	/**
	 * Overwrites the description. 
	 * 
	 * @param description  the new description for the level 
	 */
	public void overwriteDescription(String description){ 
		this.description = description;
	}
	
	/**
	 * @return Iterator of the karelCode.
	 */
	public Iterator<String> listKarelCode(){
		return this.karelCode.iterator();
	}

}