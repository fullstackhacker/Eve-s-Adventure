package models.gridobjects;

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
	private static final long serialVersionUID = 8L;

	/**
	 * Checks to see if the Shrub has at least one Bamboo object stored inside
	 * of it.
	 * 
	 * @return true - If the Shrub has a Bamboo object inside it.
	 */
	public boolean hasBamboo() {

		return false;
	}
}
