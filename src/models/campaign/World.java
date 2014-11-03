package models.campaign;

import java.io.Serializable;
import java.util.ArrayList;

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
		/**
		 * Adding an item to the square if there is no item on the square
		 * 
		 * @see replaceItem
		 * @param item  the object to add to the square
		 * @return true  iff the object was added to the board
		 * @return false  the object was not added to the board
		 */
		private boolean addItem(Item item){
			if(this.hasItem()){
				this.item = item; 
				return true; 
			}
			return false; 	
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
			return false; 
		}
		/**
		 * Replaces the creature on the square 
		 * 
		 * @see addCreature()
		 * 
		 * @param creature  the new creature for the square
		 * @return  true iff replaces a creature
		 */
		private boolean replaceCreature(Creature creature){ 
			return false; 
		}
		/**
		 * Remove the creature on the square
		 */
		private void removeCreature(){ 
			this.creature = null; 
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
			String s = this.hasCreature()? this.creature.toString() : " ";
			s += this.hasItem()? this.item.toString() : " " ; 
			s += " ";
			return s;
		}
	}
	/**
	 * Name of the world
	 */
	private String name; 
	/**
	 * Serailizable ID for saving
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Grid of Squares
	 */
	private Square[][] world; 
	
	/**
	 * Constructor for the world
	 * 
	 * @param height  The height of the world 
	 * @param width  The width of the world
	 */
	public World(int height, int width){ 
		this.world[0][0] = new Square(); 
	}
	/**
	 * Gets the name of the world
	 * 
	 * @return the name of the world 
	 */
	public String getName(){
		return null; 
	}
	/**
	 * Change the name of the world 
	 * 
	 * @param name  the new name for the world
	 */
	public void replaceName(String name){
		
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
		return false; //added to make compiler happy #sesh
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
		return false; //added to make compiler happy #sesh
	}
	/**
	 * Removes an object from the world at the specified location
	 * 
	 * @param coordinate The coordinate representation of the object in the world
	 */
	public void removeItem(Coordinate coordinate){ 
		
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
		return false; 
	}
	/**
	 * Replaces the creature on the square
	 * 
	 * @param creature  the new creature for the square
	 * @return  true iff the old creature was replaced by the new creature
	 */
	public boolean replaceCreature(Creature creature){
		return false; 
	}
	/**
	 * Remove the creature on the specified location
	 * 
	 * @param coordinate A coordinate object representing the location of the creature to remove
	 */
	public void removeCreature(Coordinate coordiante){
		
	}
	/**
	 * Checks to see if there is an item on the location
	 * 
	 * @param x  the x coordinate of the location
	 * @param y  the y coordinate of the location
	 * @return  true iff the specified location has an item
	 */
	public boolean hasItem(int x, int y){ 
		return false; //added to shut up compiler
	}
	/**
	 * Checks to see if there is a creature on the location
	 * 
	 * @param x  the x coordinate of the location 
	 * @param y  the y coordinate of the location
	 * @return  true iff the specified location has a creature
	 */
	public boolean hasCreature(int x, int y){
		return false; 
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
	 * Gets all the objects in the world.
	 * This includes all creatures and items  so everything
	 * 
	 * @return  all the objects that are currently in the world
	 */
	public ArrayList<GridObject> getObjects(){ 
		return null; //added to make compiler unhappy 
	}
	/**
	 * Gets only the items in the world. Creatures not included.
	 * 
	 * @return   the items in the world
	 */
	public ArrayList<Item> getItems(){ 
		return null; //shhhhhhut up 
	}
	/** * @author Mushaheed Kapadia
	 * Gets only bamboo in the world 
	 * 
	 * @return  all the bamboo in the world
	 */
	public ArrayList<Bamboo> getBamboo(){
		return null; //compiler be mad yo
	}
	/**
	 * Gets all the creatures EXCLUDING Eve
	 * 
	 * @see #getEve()
	 * 
	 * @return  the creatures in the world EXCLUDING eve
	 */
	public ArrayList<Creature> getCreatures(){ 
		return null; //dat compiler camp
	}
	/**
	 * Gets Eve 
	 * @return  the Eve Creature Object
	 */
	public Creature getEve(){ 
		return null; //we will return Eve if you meet our demands 
	}
	/**
	 * Gets only the shrubs in the world
	 * 
	 * @return  all the shrubs in the world
	 */
	public ArrayList<Shrub> getShrubs(){
		return null; //woot woot compilers
	}
	/**
	 * Gets only the trees in the world
	 * 
	 * @return  all the trees in the world
	 */
	public ArrayList<Tree> getTrees(){
		return null; //eclipse's red lines are scary
	}
	/**
	 * Gets only the trees in the world
	 * 
	 * @return  all the trees in the world
	 */
	public ArrayList<Wall> getWalls(){
		return null; //no one's gonna read these anyways
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
		String s = ""; 
		for(int y=0; y<this.world.length; y++){ 
			for(int x=0; x<this.world[y].length; x++){ 
				s += this.world[y][x].toString(); 
			}
			s += "\n";
		}
		return s; 
	}
}
