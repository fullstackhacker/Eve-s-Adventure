package models.gridobjects.items;

import java.io.Serializable;

/**
 * The Shrub object extends Item and acts as a more limited Tree. It blocks
 * spaces and allows for the storage and removal of Bamboo.
 * 
 * @author Carmine Iannaccone
 * 
 */
public class Shrub extends Item implements Serializable{
	
	/**
	 * A shrub can only contain one piece bamboo.
	 */
	private boolean bamboo;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;

	public Shrub(int iD, boolean bamboo){ 
		super(iD); 
		this.bamboo = bamboo;
	}
	
	/**
	 * Checks to see if the Shrub has at least one Bamboo object stored inside
	 * of it.
	 * 
	 * @return true  If the Shrub has a Bamboo object inside it.
	 */
	public boolean hasBamboo() {
		return this.bamboo;
	}
	
	/**
	 * Adds Bamboo to the shrub.
	 * @param bamboo the bamboo to add to the shrub
	 * @return true iff able to add bamboo successfully
	 */
	public boolean addBamboo(){
		if(this.bamboo) return false; 
		this.bamboo = true; 
		return true; 
	}
	
	/**
	 * Removes bamboo from the tree.
	 */
	public void removeBamboo(){
		this.bamboo = false;
	}
	
	/**
	 * Determines what type the object is and returns the first letter of that types name.
	 * @return type  Returns the first letter of the type name. (Example: "S" = Shrub)
	 */
	public String toString() {
		return "S";

	}
	

}
