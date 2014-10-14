package models.gridobjects.items;

import java.io.Serializable;

/**
 * The Bamboo object extends Item and is an object that can be stored within Eve's inventory or other Item objects.
 * @author Carmine Iannaccone
 *
 */
public class Bamboo extends Item implements Serializable{

	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 7L;
	
	public Bamboo(int iD){ 
		super(iD); 
	}
	
	public String toString() {
		return null;

	}
}
