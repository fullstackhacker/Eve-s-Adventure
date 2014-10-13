package models.campaign;

import java.io.Serializable;
import java.util.ArrayList;

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
 * @author Mushaheed Kapadia
 * 
 */
public class World implements Serializable {

	/**
	 * Square represents a location in the world. A square can be occupied by Eve,
	 * one her friends, a stick of bamboo, a wall, or a shrub
	 * @author Mushaheed Kapadia
	 *
	 */
	private class Square implements Serializable { 	
		/**
		 * Serial ID
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Object that is on square
		 */
		private GridObject gObject; 
		/**
		 * Constructor
		 */
		private Square(){
			this.gObject = null; 
		}
		/**
		 * Adding an object to the square if there is no object on the square
		 * 
		 * @see replaceObject
		 * @param object - the object to add to the square
		 * @return true - iff the object was added to the board
		 * @return false - the object was not added to the board
		 */
		private boolean addObject(GridObject object){
			if(this.isEmpty()){
				this.gObject = object; 
				return true; 
			}
			return false; 	
		}
		/**
		 * Replaces the current object on the square.
		 * If there is no current object, this returns false
		 * 
		 * @see addObject
		 * 
		 * @param object - the object to add to the square
		 * @return true - iff the object replaced the old object on the square
		 * @return false - iff the object did not replace the old object on the square
		 */
		private boolean replaceObject(GridObject gObject){
			if(this.isEmpty()) return false; 
			this.gObject = gObject; 
			return true; 
		}
		/**
		 * Removes the object from the square
		 *  
		 */
		private void removeObject(){
			this.gObject = null; 
		}
		/**
		 * Check if the square is empty
		 * 
		 * @return true - iff there is no object in the square
		 */
		private boolean isEmpty(){
			return this.gObject == null;
		}
		@Override
		/**
		 * Converts the Square to a string
		 * 
		 * @return - a String representation the square based on what object is on the square
		 */
		public String toString(){ 
			if(!isEmpty()) return this.gObject.toString(); 
			return " "; 
		}
	}
	
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
	 * @param height - The height of the world 
	 * @param width - The width of the world
	 */
	public World(int height, int width){ 
		this.world[0][0] = new Square(); 
	}
	/**
	 * Add an object to the world at a specified location
	 * 
	 * @param gObject - the GridObject to add to the world
	 * @return - true iff the GridObject was placed into the world
	 */
	public boolean addObject(GridObject gObject){ 
		return false; //added to make compiler happy #sesh
	}
	/**
	 * Replaces the current object on the world at the specified location 
	 * 
	 * @see addObject()
	 * @param gObject - the GridObject to add to the world
	 * @return true iff the GridObject replaced another object in the world
	 */
	public boolean replaceObject(GridObject gObject){ 
		return false; //added to make compiler happy #sesh
	}
	/**
	 * Removes an object from the world at the specified location
	 * 
	 * @param x - the x-coordinate of the location
	 * @param y - the y-coordinate of the location
	 */
	public void removeObject(int x, int y){ 
		
	}
	/**
	 * Checks if the specified location is empty
	 * 
	 * @param x - the x coordinate of the location
	 * @param y - the y coordinate of the location
	 * @return - true iff the specified location is empty
	 */
	public boolean isEmpty(int x, int y){ 
		return false; //added to shut up compiler
	}
	/**
	 * Gets the area of the world
	 * 
	 * @return - the area of the world
	 */
	public int getArea(){ 
		return this.getHeight() * this.getWidth(); 
	}
	/**
	 * Gets the height of the world
	 * 
	 * @return - the height of the world
	 */
	public int getHeight(){
		return this.world.length;
	}
	/**
	 * Gets the width of the world
	 * 
	 * @return - the width of the world 
	 */
	public int getWidth(){
		return this.world[0].length; 
	} 
	/**
	 * Gets all the objects in the world.
	 * This includes all creatures and items -- so everything
	 * 
	 * @return - all the objects that are currently in the world
	 */
	public ArrayList<GridObject> getObjects(){ 
		return null; //added to make compiler unhappy 
	}
	/**
	 * Gets only the items in the world. Creatures not included.
	 * 
	 * @return -  the items in the world
	 */
	public ArrayList<Item> getItems(){ 
		return null; //shhhhhhut up 
	}
	/**
	 * Gets only bamboo in the world 
	 * 
	 * @return - all the bamboo in the world
	 */
	public ArrayList<Bamboo> getBamboo(){
		return null; //compiler be mad yo
	}
	/**
	 * Gets all the creatures EXCLUDING Eve
	 * 
	 * @see getEve()
	 * 
	 * @return - the creatures in the world EXCLUDING eve
	 */
	public ArrayList<Creature> getCreatures(){ 
		return null; //dat compiler camp
	}
	/**
	 * Gets Eve 
	 * @return - the Eve Creature Object
	 */
	public Creature getEve(){ 
		return null; //we will return Eve if you meet our demands 
	}
	/**
	 * Gets only the shrubs in the world
	 * 
	 * @return - all the shrubs in the world
	 */
	public ArrayList<Shrub> getShrubs(){
		return null; //woot woot compilers
	}
	/**
	 * Gets only the trees in the world
	 * 
	 * @return - all the trees in the world
	 */
	public ArrayList<Tree> getTrees(){
		return null; //eclipse's red lines are scary
	}
	/**
	 * Gets only the trees in the world
	 * 
	 * @return - all the trees in the world
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
	 * Prints out in a grid-like format
	 * 
	 * @return - a string that resembles an ASCII grid
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
