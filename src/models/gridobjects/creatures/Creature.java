package models.gridobjects.creatures;

import models.Coordinate;
import models.gridobjects.GridObject;

public class Creature extends GridObject {

	/**
	 * The directional value of Up.
	 */
	public static final int UP = 0;
	
	/**
	 * The directional value of Down.
	 */
	public static final int DOWN = 1;
	
	/**
	 * The directional value of Left.
	 */
	public static final int LEFT = 2;
	
	/**
	 * The directional value of Right.
	 */
	public static final int RIGHT = 3;
	
	/**
	 * The number of bamboos the creature is currently holding.
	 */
	private int numberOfBamboo;
	
	/**
	 * The direction of the creature that is currently pointing towards.
	 */
	private int direction;
	
	/*
	 * Is the creature awake.
	 */
	private boolean isAwake;
	
	/**
	 * 
	 */
	private Coordinate coordinate;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	
	/**
	 * Creature's Constructor 
	 * @param name The name of the creature.
	 * @param xcoordinate The xcoordinate of the creature.
	 * @param ycoordinate The ycoordinate of the creature.
	 */
	public Creature(String name, Coordinate coordinate){
		this.setName(name);
		this.setCoordinates(coordinate);
	}
	
	/**
	 * Moves the object up one in the World
	 */
	public void moveUp(){
		this.coordinate.moveNorth();
	}
	
	/**
	 * Moves the object down one in the World
	 */
	public void moveDown(){
		this.coordinate.moveSouth();
	}
	
	/**
	 * Moves the object left one in the World 
	 */
	public void moveLeft(){
		this.coordinate.moveWest();
	}
	/**
	 * Moves the object right one in the World 
	 */
	public void moveRight(){ 
		this.coordinate.moveEast();
	}
	
	/**
	 * Give a bamboo to a creature.
	 * 
	 * @param creature  the creature to give bamboo to 
	 */
	public void giveBamboo(Creature creature){
		this.numberOfBamboo--;
		creature.incrementBamboo();
		
	}
	
	/**
	 * Take a bamboo from a creature.
	 * @param creature Take a bamboo from that creature. 
	 */
	public void takeBamboo(Creature creature){
		this.numberOfBamboo++;
		creature.decrementBamboo();
	}
	
	/**
	 * Add 1 bamboo to this creature. 
	 */
	public void incrementBamboo(){
		this.numberOfBamboo++;
	}
	
	/**
	 * Minus 1 bamboo to this creature.
	 */
	private void decrementBamboo(){
		if(this.numberOfBamboo > 0){ this.numberOfBamboo--; }
	}
	
	/**
	 * Set the direction of the creature.
	 * @param direction  the (new) direction of the creature
	 */
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	/**
	 * Gets the direction of the creature
	 * 
	 * @return The creature direction.
	 */
	public int getDirection(){
		return this.direction;
	}
	
	/**
	 * Set the status of the creature awake.
	 * @param isAwake True = awake. Otherwise false.
	 */
	public void setAwake(boolean isAwake){
		this.isAwake = isAwake;
	}
	
	/**
	 * Checks to see if the creature is awake or asleep 
	 * 
	 * @return true iff the creature is awake.
	 */
	public boolean isAwake(){
		return this.isAwake;
	}
	
	/**
	 * Returns the identifier. 
	 */
	public String toString(){
		return String.valueOf(Character.toUpperCase(this.getName().charAt(0)));
	}
}
