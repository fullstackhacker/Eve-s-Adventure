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
		 * @see replaceObject
		 * @param object - the object to add to the square
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
		 * Replaces the current object on the square.
		 * If there is no current object, this returns false
		 * 
		 * @see addObject
		 * 
		 * @param object - the object to add to the square
		 * @return true - iff the object replaced the old object on the square
		 * @return false - iff the object did not replace the old object on the square
		 */
		public boolean replaceObject(EObject object){
			if(this.isEmpty()) return false; 
			this.object = object; 
			return true; 
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
