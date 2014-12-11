package models.gridobjects.items;

import java.io.Serializable;

/**
 * 
 * The Tree object extends Item and acts as an obstruction inside the grid. The
 * Tree allows for Eve to climb into it, move to a neighboring tree, store
 * bamboo and take bamboo from the tree.
 * 
 * @author Carmine Iannaccone
 * 
 * 
 */
public class Tree extends Item implements Serializable {

	/**
	 * 
	 */
	private Bamboo bamboo;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;

	public Tree(int iD){
		super(iD);
	}
	
	public Tree(int iD, Bamboo bamboo) {
		super(iD);
		this.bamboo = bamboo;
	}

	/**
	 * Checks to see if the Tree has at least one Bamboo object stored inside of
	 * it.
	 * 
	 * @return true If the Tree has a Bamboo object inside it.
	 */
	public boolean hasBamboo() {
		return this.bamboo !=  null;
	}
	
	/**
	 * Adds Bamboo to the tree.
	 * @param bamboo The bamboo to add to the this tree
	 * @return true iff able to add bamboo to this tree
	 */
	public boolean addBamboo(Bamboo bamboo) {
		if (bamboo == null) {
			this.bamboo = bamboo;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes bamboo from the tree.
	 * @return The bamboo object that was removed from this object
	 */
	public Bamboo removeBamboo() {
		Bamboo temp = bamboo;
		bamboo = null;
		return temp;
	}

	/**
	 * Checks to see if the Tree is near a Tree object.
	 * 
	 * @return true If the Tree is near a Tree.
	 */
	/*
	 * public boolean nearTree() {
	 * 
	 * return false; }
	 */

	/**
	 * Checks to see if the Tree is near a Wall object.
	 * 
	 * @return true If the Tree is near a Wall.
	 */
	/*
	 * public boolean nearWall() {
	 * 
	 * return false; }
	 */

	/**
	 * Given a name and a direction, this method will create a new tree and
	 * place it in an adjacent space specified by the parameters. If the space
	 * specified is invalid either because an EObject already exists there or
	 * because the space is out of bounds, this method will do nothing.
	 * 
	 * @param name
	 *            The name to be given to the Tree object being created.
	 * @param direction
	 *            Takes an int from 14 to specify which neighbor to remove. 1
	 *            (North), 2 (South), 3 (East), 4 (West).
	 */
	/*
	 * public void addNearbyTree(String name, int direction) {
	 * 
	 * }
	 */

	/**
	 * Removes a specified neighboring Tree object. If the Tree passed in by the
	 * parameter does not exist then this method does nothing.
	 * 
	 * @param tree
	 *            The name of the Tree object to remove.
	 */
	/*
	 * public void removeNearbyTree(Tree tree) {
	 * 
	 * }
	 */

	/**
	 * Determines what type the object is and returns the first letter of that
	 * types name.
	 * 
	 * @return type Returns the first letter of the type name. (Example: "S" =
	 *         Shrub)
	 */
	public String toString() {
		return "T";

	}
	
	public Item copy(){
		return new Tree(this.getId(), this.bamboo); 
	}

}
