package models.gridobjects;

import java.io.Serializable;

import models.Coordinate;

/**
 * EObjects are interactable objects in Eve's Adventure
 * 
 * Various Types:  person  Eve  Her friends  bamboos  shrubs  trees 
 * walls
 * 
 */
public abstract class GridObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;

	/**
	 * Name of the object
	 */
	private String name;

	/**
	 * Location of the GridObject in the world
	 */
	private Coordinate coordinate; 

	/**
	 * Gets the location of the object
	 * 
	 * @return A representation of the GridObject's location in the world
	 */
	public Coordinate getCoordinates(){ 
		return this.coordinate; 
	}
	/**
	 * Gets the name of the object
	 * 
	 * @return  The name of the object
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the X coordinate of the object
	 * 
	 * @return The X coordinate of the object
	 */
	public int getX() {
		return this.coordinate.getX();
	}

	/**
	 * Gets the Y coordinate of the object
	 * 
	 * @return The Y coordinate of the object
	 */
	public int getY() {
		return this.coordinate.getY();
	}

	
	public void moveNorth(){ 
		this.coordinate.moveNorth();
	}
	/**
	 * Sets the name of the object
	 * 
	 * @param name
	 *             The (new) name of the object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the coordinates of the object. Assumes the coordinate are valid for the world
	 * 
	 * @param coordinate The new coordinate of this object
	 *             
	 * 
	 */
	public void setCoordinates(Coordinate coordinate) {
		this.coordinate = coordinate;
	}


	/**
	 * Determines what type the object is and returns the first letter of that types name.
	 * @return type  Returns the first letter of the type name. (Example: "S" = Shrub)
	 */
	public String toString() {
		return null;

	}
}
