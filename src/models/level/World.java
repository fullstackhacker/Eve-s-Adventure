package models.level;

import java.io.Serializable;

import models.gridobjects.GridObject;

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
		public Square(){
			gObject = null; 
		}
		/**
		 * Adding an object to the square if there is no object on the square
		 * 
		 * @see replaceObject
		 * @param object - the object to add to the square
		 * @return true - iff the object was added to the board
		 * @return false - the object was not added to the board
		 */
		public boolean addObject(GridObject object){
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
		public boolean replaceObject(GridObject gObject){
			if(this.isEmpty()) return false; 
			this.gObject = gObject; 
			return true; 
		}
		/**
		 * Removes the object from the square
		 *  
		 */
		public void removeObject(){
			this.gObject = null; 
		}
		/**
		 * Check if the square is empty
		 * 
		 * @return true - iff there is no object in the square
		 */
		public boolean isEmpty(){
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
		
	}
	/**
	 * Add an object to the world at a specified location
	 * 
	 * @param gObject - the GridObject to add to the world
	 * @param x - the x-coordinate of the location 
	 * @param y - the y-coordinate of the location
	 * @return - true iff the GridObject was placed into the world
	 */
	public boolean addObject(GridObject gObject, int x, int y){ 
		return false; //added to make compiler happy #sesh
	}
	/**
	 * Replaces the current object on the world at the specified location 
	 * 
	 * @see addObject()
	 * @param gObject - the GridObject to add to the world
	 * @param x - the x-coordinate of the location
	 * @param y - the y-coordinate of the location
	 * @return true iff the GridObject replaced another object in the world
	 */
	public boolean replaceObject(GridObject gObject, int x , int y){ 
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
