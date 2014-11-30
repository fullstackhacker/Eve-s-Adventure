package models.gridobjects.creatures;

import models.Coordinate;
import models.campaign.IllegalValueException;
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
	 * Width of the current world.
	 */
	private int worldWidth;
	
	/**
	 * Height of the current world.
	 */
	private int worldHeight;
	
	private World world;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	
	/**
	 * Creature's Constructor 
	 * @param name The name of the creature.
	 * @param coordinate The location of the creature in the world
	 */
	public Creature(String name, Coordinate coordinate, World World){
		super.setName(name);
		super.setCoordinates(coordinate);
		this.isAwake =  true; 
		this.direction = Creature.UP;
		this.numberOfBamboo = 0;
		this.world = World;
		this.setWorldWidth(World.getWidth());
		this.setWorldHeight(World.getHeight());
		
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
	 * Moves the object up one in the World
	 */
	public void moveUp(){
		if ((super.getCoordinates().getY() + 1) < this.worldHeight) super.getCoordinates().moveNorth();
	}
	
	/**
	 * Moves the object down one in the World
	 */
	public void moveDown(){
		if((super.getCoordinates().getY() - 1) >= 0) super.getCoordinates().moveSouth();
	}
	
	/**
	 * Moves the object left one in the World 
	 */
	public void moveLeft(){
		if ((super.getCoordinates().getX() - 1) >= 0) super.getCoordinates().moveWest();
	}
	/**
	 * Moves the object right one in the World 
	 */
	public void moveRight(){
		if ((super.getCoordinates().getX() + 1) < this.world.getWidth()){
			super.getCoordinates().moveEast();
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
	 * Removes bamboo from shrub.
	 * 
	 * @param shrub
	 */
	public void takeBambooFromShrub(Shrub shrub){
		verifyShrubLocation(shrub);
		if(shrub.hasBamboo()){
			this.numberOfBamboo++;
			shrub.removeBamboo();
		}
	}
	
	/**
	 * Verifies that shrub is within one space away from the creature.
	 * 
	 * @param shrub
	 */
	private void verifyShrubLocation(Shrub shrub){
		int creatureX = this.getCoordinates().getX();
		int creatureY = this.getCoordinates().getY();
		
		if(creatureX == shrub.getX()){
			if((creatureX + 1) == shrub.getX() || (creatureX - 1) == shrub.getX()) return;
		}else if(creatureY == shrub.getY()){
			if((creatureY + 1) == shrub.getY() || (creatureY - 1) == shrub.getY()) return;
		}
			
		throw new IllegalValueException("Shrub is not within reach of Eve.");
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
	 * Must be set before any program is executed. Setter for worldWidth which is used 
	 * within other methods to determine whether the current creature is in the bounds 
	 * of the world.
	 * 
	 * @param width
	 */
	public void setWorldWidth(int width){
		this.worldWidth = width;
	}
	
	/**
	 * Must be set before any program is executed. Setter for worldHeight which is used 
	 * within other methods to determine whether the current creature is in the bounds 
	 * of the world.
	 * 
	 * @param width
	 */
	public void setWorldHeight(int height){
		this.worldHeight = height;
	}
	
	/**
	 * Returns the identifier. 
	 */
	public String toString(){
		return String.valueOf(Character.toUpperCase(this.getName().charAt(0)));
	}
}
