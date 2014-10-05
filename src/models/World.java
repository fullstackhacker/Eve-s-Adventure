package models;

import java.io.Serializable;

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
		private EObject object; 
		/**
		 * Constructor
		 */
		public Square(){
			object = null; 
		}
		/**
		 * Adding an object to the square if there is no object on the square
		 * 
		 * @param object - the object you want to add to the sqaure
		 * @return true - iff the object was added to the board
		 * @return false - the object was not added to the board
		 */
		public boolean addObject(EObject object){
			if(this.isEmpty()){
				this.object = object; 
				return true; 
			}
			return false; 	
		}
		/**
		 * Check if the square is empty
		 * 
		 * @return true - iff there is no object in the square
		 */
		public boolean isEmpty(){
			if(this.object == null) return true; 
			return false; 
		}
		
		
		
	}
	Square square = new Square(); //added to shut eclipse up
	
}
