package models.campaign;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import exceptions.IllegalValueException;
import models.Coordinate;
import models.gridobjects.GridObject;
import models.gridobjects.creatures.Creature;
import models.gridobjects.items.Bamboo;
import models.gridobjects.items.Item;
import models.gridobjects.items.Shrub;
import models.gridobjects.items.Tree;
import models.gridobjects.items.Wall;

/**
 * World represents the playing environment with Eve and the different things that Eve can interact with 
 *  
 */
public class World implements Serializable {

	/**
	 * Square represents a location in the world. A square can be occupied by Eve,
	 * one her friends, a stick of bamboo, a wall, or a shrub
	 *
	 */
	private class Square implements Serializable { 	
		/**
		 * Serial ID
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Item that is on square
		 */
		private Item item; 
		/**
		 * Creature that on square
		 */
		private Creature creature; 
		/**
		 * Constructor
		 */
		private Square(){
			this.item = null; 
			this.creature = null; 
		}
		
		private Item currentItem(){
			return this.item; 
		}
		
		private Creature currentCreature(){
			return this.creature; 
		}
		/**
		 * Adding an item to the square if there is no item on the square
		 * 
		 * @see replaceItem
		 * @param item  the object to add to the square
		 * @return true  iff the object was added to the board
		 * @return false  the object was not added to the board
		 */
		private boolean addItem(Item item){
			if(this.hasItem()) return false;
			this.item = item; 
			return true; 	
		}
		/**
		 * Replaces the current item on the square.
		 * If there is no current item, this returns false
		 * 
		 * @see addItem
		 * 
		 * @param item  the object to add to the square
		 * @return true  iff the object replaced the old object on the square
		 * @return false  iff the object did not replace the old object on the square
		 */
		private boolean replaceItem(Item item){
			if(this.hasItem()) return false; 
			this.item = item; 
			return true; 
		}
		/**
		 * Removes the object from the square
		 *  
		 */
		private void removeItem(){
			this.item = null; 
		}
		/**
		 * Add a creature to the square iff there is no creature
		 * 
		 * @see replaceCreature()
		 * 
		 * @param creature  the creature to add the square
		 * @return  true iff able to add a creature to the square
		 */
		private boolean addCreature(Creature creature){ 
			if(this.hasCreature()) return false;
			this.creature = creature;
			return true; 
		}
		/**
		 * Replaces the creature on the square 
		 * 
		 * @see addCreature()
		 * 
		 * @param creature  the new creature for the square
		 * @return  true iff replaces a creature
		 * @throws Exception 
		 */
		private boolean replaceCreature(Creature creature){
			this.creature = creature;
			return true;
		}
		/**
		 * Remove the creature on the square
		 */
		private Creature removeCreature(){ 
			Creature temp = this.creature; 
			this.creature = null; 
			return temp; 
		}
		/**
		 * Check if the square has an item
		 * 
		 * @return  true iff there is an object in the square
		 */
		private boolean hasItem(){
			return this.item != null;
		}
		/**
		 * Check if the square has a creature
		 * 
		 * @return  true iff there is a creature in the square
		 */
		private boolean hasCreature(){
			return this.creature != null; 
		}
		@Override
		/**
		 * Converts the Square to a string
		 * 
		 * @return  a String representation the square based on what object is on the square
		 */
		public String toString(){ 
			String s = this.hasCreature()? this.creature.toString() : "_";
			s += this.hasItem()? this.item.toString() : "_" ;
			s += "|";
			return s;
		}
	}
	/**
	 * Name of the world
	 */
	private String name; 
	/**
	 * Serializable ID for saving
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Grid of Squares
	 */
	private Square[][] world; 
	/**
	 * Constructor for the world
	 * 
	 * @param name The name of the world
	 * @param height  The height of the world 
	 * @param width  The width of the world
	 */
	public World(String name, int height, int width){ 
		this.name = name; 
		this.world = new Square[height][width];
		
		for(int y=0; y < height; y++){
			for(int x=0; x<width; x++){ 
				this.world[y][x] = new Square(); 
			}
		}
	}
	/**
	 * Gets the name of the world
	 * 
	 * @return the name of the world 
	 */
	public String getName(){
		return this.name; 
	}
	/**
	 * Sets the name of the world
	 * 
	 * @param name The new name of the world
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Retrieves the item at the given coordinate
	 * 
	 * @param coordinate
	 * @return
	 */
	public Item itemAt(Coordinate coordinate){
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()].currentItem();
	}
	
	/**
	 * Retrieves the creature at the given coordinate
	 * 
	 * @param coordinate
	 * @return
	 */
	public Creature creatureAt(Coordinate coordinate){
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()].currentCreature();
	}
	/**
	 * Add an item to the world at a specified location
	 * 
	 * @see #replaceItem(Item)
	 * 
	 * @param item  the Item to add to the world
	 * @return  true iff the Item was placed into the world
	 */
	public boolean addItem(Item item){ 
		if(item == null) throw new IllegalValueException();
		return this.world[item.getY()][item.getX()].addItem(item);
	}
	/**
	 * Replaces the current object on the world at the specified location 
	 * 
	 * @see #addItem(Item)
	 * 
	 * @param item  the Item to add to the world
	 * @return true iff the Item replaced another item in the world
	 */
	public boolean replaceItem(Item item){ 
		if(item == null) throw new IllegalValueException();
		return this.world[item.getY()][item.getX()].replaceItem(item); 
	}
	/**
	 * Removes an object from the world at the specified location
	 * 
	 * @param coordinate The coordinate representation of the object in the world
	 */
	public void removeItem(Coordinate coordinate){ 
		verifyCoordinate(coordinate);
		this.world[coordinate.getY()][coordinate.getX()].removeItem();
	}
	/**
	 * Adds a creature if there isn't anything on that square
	 * 
	 * @see #replaceCreature(Creature)
	 * 
	 * @param creature  the creature 
	 * @return  true iff added the creature to the square
	 */
	public boolean addCreature(Creature creature){
		if(creature == null) throw new IllegalValueException();
		return this.world[creature.getY()][creature.getX()].addCreature(creature); 
	}
	/**
	 * Replaces the creature on the square
	 * 
	 * @param creature  the new creature for the square
	 * @return  true iff the old creature was replaced by the new creature
	 */
	public boolean replaceCreature(Creature creature){
		if(creature == null) throw new IllegalValueException();
		return this.world[creature.getY()][creature.getX()].replaceCreature(creature); 
	}
	/**
	 * Remove the creature on the specified location
	 * 
	 * @param coordinate A coordinate object representing the location of the creature to remove
	 */
	public Creature removeCreature(Coordinate coordinate){
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()].removeCreature();
	}
	/**
	 * Checks to see if there is an item on the location
	 * 
	 * @param x  the x coordinate of the location
	 * @param y  the y coordinate of the location
	 * @return  true iff the specified location has an item
	 */
	public boolean hasItem(Coordinate coordinate){ 
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()].hasItem();
	}
	/**
	 * Checks to see if there is a creature on the location
	 * 
	 * @param x  the x coordinate of the location 
	 * @param y  the y coordinate of the location
	 * @return  true iff the specified location has a creature
	 */
	public boolean hasCreature(Coordinate coordinate){
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()].hasCreature();
	}
	/**
	 * Gets the area of the world
	 * 
	 * @return  the area of the world
	 */
	public int getArea(){ 
		return this.getHeight() * this.getWidth(); 
	}
	/**
	 * Gets the height of the world
	 * 
	 * @return  the height of the world
	 */
	public int getHeight(){
		return this.world.length;
	}
	/**
	 * Gets the width of the world
	 * 
	 * @return  the width of the world 
	 */
	public int getWidth(){
		return this.world[0].length; 
	} 
	/**
	 * Verifies that the provided coordinate is within the bounds of the world and is not null.
	 * 
	 * @param coordinate
	 */
	private boolean verifyCoordinate(Coordinate coordinate){
		if(coordinate == null 
				|| coordinate.getY() > this.getHeight() 
				|| coordinate.getX() > this.getWidth()
				|| coordinate.getY() < 0
				|| coordinate.getX() < 0){ 
			return false;
		}
		return true;
	}
	
	/**
	 * Moves Eve east on the board. Updates coordinate for the Creature Eve and updates world (2D array).
	 * 
	 * Does not do anything if the move is invalid
	 * 
	 */
	public void moveEveEast(){
		Coordinate newEveLocation = new Coordinate(this.getEve().getCoordinates().getX()+1, this.getEve().getCoordinates().getY()); 
		if(!verifyCoordinate(newEveLocation) && this.world[newEveLocation.getY()][newEveLocation.getX()].hasCreature()) return; 
		this.getEve().move();
		this.world[newEveLocation.getY()][newEveLocation.getY()].addCreature(this.getEve());
		this.world[this.getEve().getCoordinates().getY()][this.getEve().getCoordinates().getX()].removeCreature();
	}
	
	/**
	 * Moves Eve north on the board. Updates coordinate for the Creature Eve and updates world (2D array).
	 * 
	 */
	public void moveEveNorth(){
		Coordinate newEveLocation = new Coordinate(this.getEve().getCoordinates().getX(), this.getEve().getCoordinates().getY()+1); 
		if(!verifyCoordinate(newEveLocation) && this.world[newEveLocation.getY()][newEveLocation.getX()].hasCreature()) return; 
		this.getEve().move();
		this.world[newEveLocation.getY()][newEveLocation.getY()].addCreature(this.getEve());
		this.world[this.getEve().getCoordinates().getY()][this.getEve().getCoordinates().getX()].removeCreature();
	}
	
	/**
	 * Moves Eve west on the board. Updates coordinate for the Creature Eve and updates world (2D array).
	 * 
	 */
	public void moveEveWest(){
		Coordinate newEveLocation = new Coordinate(this.getEve().getCoordinates().getX()-1, this.getEve().getCoordinates().getY()); 
		if(!verifyCoordinate(newEveLocation) && this.world[newEveLocation.getY()][newEveLocation.getX()].hasCreature()) return; 
		this.getEve().move();
		this.world[newEveLocation.getY()][newEveLocation.getY()].addCreature(this.getEve());
		this.world[this.getEve().getCoordinates().getY()][this.getEve().getCoordinates().getX()].removeCreature();
	}
	
	/**
	 * Moves Eve south on the board. Updates coordinate for the Creature Eve and updates world (2D array).
	 * 
	 */
	public void moveEveSouth(){
		Coordinate newEveLocation = new Coordinate(this.getEve().getCoordinates().getX(), this.getEve().getCoordinates().getY()-1); 
		if(!verifyCoordinate(newEveLocation) && this.world[newEveLocation.getY()][newEveLocation.getX()].hasCreature()) return; 
		this.getEve().move();
		this.world[newEveLocation.getY()][newEveLocation.getY()].addCreature(this.getEve());
		this.world[this.getEve().getCoordinates().getY()][this.getEve().getCoordinates().getX()].removeCreature();
	}
	/**
	 * Take bamboo from nearby shrub
	 */
	public void takeBambooFromShrub(){
		Coordinate shrubCoordinate = null; 
		switch(this.getEve().getDirection()){
		case Creature.UP: 
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates().getX(), this.getEve().getCoordinates().getY()+1);
			break;
		case Creature.DOWN: 
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates().getX(), this.getEve().getCoordinates().getY()-1);
			verifyCoordinate(shrubCoordinate);
			break;
		case Creature.LEFT: 
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates().getX()-1, this.getEve().getCoordinates().getY());
			break;
		case Creature.RIGHT:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates().getX()+1, this.getEve().getCoordinates().getY());
			break;
		default: 
			throw new IllegalValueException("Eve is facing an illegal direction");
		}
		
		if(!verifyCoordinate(shrubCoordinate)){ //eve is not facing a near by bush
			return; //dont do anything
		}
		
		if(this.world[shrubCoordinate.getY()][shrubCoordinate.getX()].item instanceof Shrub){
			if(((Shrub)this.world[shrubCoordinate.getY()][shrubCoordinate.getX()].item).hasBamboo()){
				((Shrub)this.world[shrubCoordinate.getY()][shrubCoordinate.getX()].item).removeBamboo();
				this.getEve().incrementBamboo();
			}
		}
	}
	
	/**
	 * Put bamboo in a nearby shrub
	 */
	public void putBambooInShrub(){
		Coordinate shrubCoordinate = null; 
		switch(this.getEve().getDirection()){
		case Creature.UP: 
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates().getX(), this.getEve().getCoordinates().getY()+1);
			break;
		case Creature.DOWN: 
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates().getX(), this.getEve().getCoordinates().getY()-1);
			verifyCoordinate(shrubCoordinate);
			break;
		case Creature.LEFT: 
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates().getX()-1, this.getEve().getCoordinates().getY());
			break;
		case Creature.RIGHT:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates().getX()+1, this.getEve().getCoordinates().getY());
			break;
		default: 
			throw new IllegalValueException("Eve is facing an illegal direction");
		}
		
		if(!verifyCoordinate(shrubCoordinate)){ //eve is not facing a near by bush
			return; //dont do anything
		}
		
		if(this.world[shrubCoordinate.getY()][shrubCoordinate.getX()].item instanceof Shrub){
			if(((Shrub)this.world[shrubCoordinate.getY()][shrubCoordinate.getX()].item).hasBamboo()){
				((Shrub)this.world[shrubCoordinate.getY()][shrubCoordinate.getX()].item).addBamboo();
				this.getEve().decrementBamboo();
			}
		}
	}
	/**
	 * Gets all the objects in the world.
	 * This includes all creatures and items  so everything
	 * 
	 * @return  all the objects that are currently in the world
	 */
	public Iterator<GridObject> getObjects(){ 
		ArrayList<GridObject> objects = new ArrayList<GridObject>();
		for(int y=0; y<this.world.length; y++){ 
			for(int x=0; x<this.world[y].length; x++){ 
				if(world[y][x].hasItem()){
					objects.add(world[y][x].item);
				}else if(world[y][x].hasCreature()){
					objects.add(world[y][x].creature);
				}
			}
		}
		
		return objects.iterator();
	}
	/**
	 * Gets only the items in the world. Creatures not included.
	 * 
	 * @return   the items in the world
	 */
	public Iterator<Item> getItems(){ 
		ArrayList<Item> items = new ArrayList<Item>();
		for(int y=0; y<this.world.length; y++){ 
			for(int x=0; x<this.world[y].length; x++){ 
				if(world[y][x].hasItem()){
					items.add(world[y][x].item);
				}
			}
		}
		
		return items.iterator();
	}
	/**
	 * Gets only bamboo in the world 
	 * 
	 * @return  all the bamboo in the world
	 */
	public Iterator<Bamboo> getBamboo(){
		ArrayList<Bamboo> bamboos = new ArrayList<Bamboo>();
		for(int y=0; y<this.world.length; y++){ 
			for(int x=0; x<this.world[y].length; x++){ 
				if(world[y][x].hasItem()){
					if(world[y][x].item.toString().equals("B"))
						bamboos.add((Bamboo) world[y][x].item);
				}
			}
		}
		
		return bamboos.iterator();
	}
	/**
	 * Gets all the creatures EXCLUDING Eve
	 * 
	 * @see #getEve()
	 * 
	 * @return  the creatures in the world EXCLUDING eve
	 */
	public Iterator<Creature> getCreatures(){ 
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		for(int y=0; y<this.world.length; y++){ 
			for(int x=0; x<this.world[y].length; x++){ 
				if(world[y][x].hasCreature()){
					if(!world[y][x].creature.isEve()){
						creatures.add(world[y][x].creature);
					}
				}
			}
		}
		
		return creatures.iterator();
	}
	
	/**
	 * Gets only the shrubs in the world
	 * 
	 * @return  all the shrubs in the world
	 */
	public Iterator<Shrub> getShrubs(){
		ArrayList<Shrub> shrubs = new ArrayList<Shrub>();
		for(int y=0; y<this.world.length; y++){ 
			for(int x=0; x<this.world[y].length; x++){ 
				if(world[y][x].hasItem()){
					if(world[y][x].item.toString().equals("S")){
						shrubs.add((Shrub)world[y][x].item);
					}
				}
			}
		}
		
		return shrubs.iterator();
	}
	/**
	 * Gets only the trees in the world
	 * 
	 * @return  all the trees in the world
	 */
	public Iterator<Tree> getTrees(){
		ArrayList<Tree> trees = new ArrayList<Tree>();
		for(int y=0; y<this.world.length; y++){ 
			for(int x=0; x<this.world[y].length; x++){ 
				if(world[y][x].hasItem()){
					if(world[y][x].item.toString().equals("T")){
						trees.add((Tree)world[y][x].item);
					}
				}
			}
		}
		
		return trees.iterator();
	}
	/**
	 * Gets only the trees in the world
	 * 
	 * @return  all the trees in the world
	 */
	public Iterator<Wall> getWalls(){
		ArrayList<Wall> walls = new ArrayList<Wall>();
		for(int y=0; y<this.world.length; y++){ 
			for(int x=0; x<this.world[y].length; x++){ 
				if(world[y][x].hasItem()){
					if(world[y][x].item.toString().equals("W")){
						walls.add((Wall)world[y][x].item);
					}
				}
			}
		}
		
		return walls.iterator();
	}
	/**
	 * Gets Eve 
	 * @return  the Eve Creature Object
	 */
	public Creature getEve(){ 
		for(int y=0; y<this.getHeight(); y++){
			for(int x=0; x<this.getWidth(); x++){
				if(this.world[y][x].hasCreature() && this.world[y][x].currentCreature().getName().equals("Eve"))
					return this.world[y][x].currentCreature(); 
			}
		}
		return null;
	}
	/**
	 * Prints the world out to the console
	 */
	public void printWorld(){ 
		System.out.print(this); 
	}
	@Override
	/**
	 * Converts the world into a string based on what is on the squares
	 * Prints out in a gridlike format
	 * 
	 * @return  a string that resembles an ASCII grid
	 */
	public String toString(){ 
		String s = "\n"; 
		for(int y=0; y<this.world.length; y++){ 
			s += "|";
			for(int x=0; x<this.world[y].length; x++){ 
				s += this.world[y][x].toString(); 
			}
			s += "\n";
		}
		return s; 
	}
}
