package models.gridobjects;

import java.io.Serializable;

/**
 * EObjects are interactable objects in Eve's Adventure
 * 
 * Various Types: 
 * - person
 * 		- Eve
 * 		- Her friends
 * - bamboos
 * - shrubs
 * - trees
 * - walls
 *
 */
public abstract class GridObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	/**
	 * Name of the object 
	 */
	private String name;
	
	/**
	 * X Coordinate in the World
	 */
	private int xcoordinate;
	
	/**
	 * Y Coordinate in the World
	 */
	private int ycoordinate; 
	
	/**
	 * Gets the name of the object
	 * 
	 * @return - The name of the object
	 */
	public String getName(){
		return this.name; 
	}
	
	/**
	 * Gets the X coordinate of the object
	 * @return The X coordinate of the object
	 */
	public int getX(){
		return this.xcoordinate; 
	}
	
	/**
	 * Gets the Y coordinate of the object
	 * @return The Y coordinate of the object
	 */
	public int getY(){
		return this.ycoordinate; 
	}
	
	/**
	 * Sets the name of the object
	 * @param name - The (new) name of the object
	 */
	public void setName(String name){
		this.name = name; 
	}
	
	/**
	 * Sets the coordinates of the object
	 * @param xcoordinate - the (new) x-coordinate of the object
	 * @param ycoorindate - the (new) y-coordinate of the object
	 */
	public void setCoordinates(int xcoordinate, int ycoordinate){
		this.xcoordinate = xcoordinate; 
		this.ycoordinate = ycoordinate; 
	}
	
	/**
	 * Sets the X-coordinate of the object
	 * @param xcoordinate - the (new) x-coordinate of the object
	 */
	public void setXCoordinate(int xcoordinate){
		this.xcoordinate = xcoordinate; 
	}
	
	/**
	 * Sets the Y-coordinate of the object
	 * @param ycoorindate - the (new) y-coordinate of the object
	 */
	public void setYCoordinate(int ycoordinate){ 
		this.ycoordinate = ycoordinate; 
	}
}
