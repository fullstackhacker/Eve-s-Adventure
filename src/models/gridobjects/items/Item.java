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
	private static int iD; 

	public Item(int iD){ 
		this.iD =  iD; 
	}
	
	public String returnType() {
		return null;

	}

}
