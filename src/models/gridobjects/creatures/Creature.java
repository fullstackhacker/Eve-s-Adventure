package models.gridobjects.creatures;

import exceptions.IllegalValueException;
import models.Coordinate;
import models.campaign.World;
import models.gridobjects.GridObject;
import models.gridobjects.items.Shrub;

public class Creature extends GridObject {

	/**
	 * The directional value of Up.
	 */
	public static final int UP = 0;
	
	/**
	 * The directional value of Left.
	 */
	public static final int LEFT = 1;
	
	/**
	 * The directional value of Down.
	 */
	public static final int DOWN = 2;
	
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
	
	/**
	 * Is the creature awake.
	 */
	private boolean isAwake;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	
	/**
	 * Creature's Constructor 
	 * @param name The name of the creature.
	 * @param coordinate The location of the creature in the world
	 */
	public Creature(String name, Coordinate coordinate){
		super.setName(name);
		super.setCoordinates(coordinate);
		this.isAwake =  true; 
		this.direction = Creature.UP;
		this.numberOfBamboo = 0;		
	}
	
	public boolean facingNorth(){ 
		return this.direction == Creature.UP; 
	}
	
	public boolean facingSouth(){ 
		return this.direction == Creature.DOWN; 
	}
	
	public boolean facingEast(){ 
		return this.direction == Creature.RIGHT; 
	}
	
	public boolean facingWest(){
		return this.direction == Creature.LEFT;
	}
	
	public void turnLeft(){ 
		this.direction++; 
		this.direction = this.direction%4; 
	}
	
	/**
	 *  Move the creature in the direction he or she is facing 
	 */
	public void move(){ 
		switch(this.direction){
		case Creature.UP: 
			this.getCoordinates().moveNorth();
			break; 
		case Creature.DOWN: 
			this.getCoordinates().moveSouth(); 
			break; 
		case Creature.RIGHT: 
			this.getCoordinates().moveEast(); 
			break; 
		case Creature.LEFT: 
			this.getCoordinates().moveWest(); 
			break; 
		default: 
			throw new IllegalValueException("Invalid Direction");
		}
	}
	
	/**
	 * Give a bamboo to a creature.
	 * 
	 * @param creature  the creature to give bamboo to 
	 */
	public void giveBamboo(Creature creature){
		if(this.numberOfBamboo > 0){
			this.numberOfBamboo--;
			creature.incrementBamboo();
		}
	}
	
	/**
	 * Take a bamboo from a creature.
	 * @param creature Take a bamboo from that creature. 
	 */
	public void takeBamboo(Creature creature){
		if(creature.numberOfBamboo > 0){
			this.numberOfBamboo--;
			creature.incrementBamboo();
		}
	}
	
	/** 
	 * gets the number of bamboo
	 */
	public int getNumberOfBamboo(){
		return this.numberOfBamboo; 
	}
	
	/** 
	 * tells if the creature has bamboo or not
	 */
	public boolean hasBamboo(){
		return !(this.numberOfBamboo == 0);
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
	public void decrementBamboo(){
		if(this.numberOfBamboo > 0) this.numberOfBamboo--;
	}
	
	/**
	 * Set the direction of the creature.
	 * @param direction  the (new) direction of the creature
	 */
	public void setDirection(int direction){
		if(direction < 0 || direction > 3) throw new IllegalValueException();
		this.direction = direction;
	}
	
	/**
	 * Gets the creature direction.
	 * 
	 * @return The one of the integer values of UP, DOWN, LEFT and RIGHT.
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
	 * @return True if the creature is awake. Otherwise false.
	 */
	public boolean isAwake(){
		return this.isAwake;
	}
	
	/**
	 * Checks to see if the current object is Eve.
	 * @return true iff the creature is eve
	 */
	public boolean isEve(){
		return super.getName().equals("Eve");
	}
	
	/**
	 * Returns the identifier. 
	 */
	public String toString(){
		return String.valueOf(Character.toUpperCase(this.getName().charAt(0)));
	}
}
