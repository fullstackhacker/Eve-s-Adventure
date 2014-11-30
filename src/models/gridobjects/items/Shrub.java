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
	 * 
	 */
	private Bamboo bamboo;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;

	public Shrub(int iD, Bamboo bamboo){ 
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
		return this.bamboo != null;
	}
	
	/**
	 * Adds Bamboo to the shrub.
	 * @param bamboo the bamboo to add to the shrub
	 * @return true iff able to add bamboo successfully
	 */
	public boolean addBamboo(Bamboo bamboo){
		if (bamboo == null){
			this.bamboo = bamboo;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Removes bamboo from the tree.
	 * @return The bamboo that was removed
	 */
	public Bamboo removeBamboo(){
		Bamboo temp = bamboo;
		bamboo = null;
		return temp;
	}
	
	/**
	 * Determines what type the object is and returns the first letter of that types name.
	 * @return type  Returns the first letter of the type name. (Example: "S" = Shrub)
	 */
	public String toString() {
		return "S";

	}
	

}
