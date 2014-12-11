package models.gridobjects.items;

import java.io.Serializable;

import models.gridobjects.GridObject;

/**
 * The Item class extends EObject and acts as a super class for the Bamboo,
 * Shrub, Tree and Wall objects. Methods defined here are methods that all sub
 * Item object share.
 * 
 * @author Carmine Iannaccone
 * 
 */
public abstract class Item extends GridObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	/**
	 * ID of the item to help distinguish it from other items
	 */
	private int iD; 

	public Item(int iD){ 
		this.iD =  iD; 
	}
	
	public int getId(){
		 return this.iD;
	}
	
	/**
	 * Determines what type the object is and returns the first letter of that types name.
	 * @return type  Returns the first letter of the type name. (Example: "S" = Shrub)
	 */
	public String toString() {
		return "I";

	}
	
	public abstract Item copy(); 

}
