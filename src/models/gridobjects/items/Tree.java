package models.gridobjects.items;

import java.io.Serializable;

/**
 * 
 * The Tree object extends Item and acts as an obstruction inside the
 *         grid. The Tree allows for Eve to climb into it, move to a neighboring
 *         tree, store bamboo and take bamboo from the tree.
 *         
 * @author Carmine Iannaccone
 * 
 *         
 */
public class Tree extends Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;

	public Tree(int iD){
		super(iD); 
	}
	/**
	 * Checks to see if the Tree has at least one Bamboo object stored inside of
	 * it.
	 * 
	 * @return true - If the Tree has a Bamboo object inside it.
	 */
	public boolean hasBamboo() {

		return false;
	}

	/**
	 * Checks to see if the Tree is near a Tree object.
	 * 
	 * @return true - If the Tree is near a Tree.
	 */
	public boolean nearTree() {

		return false;
	}

	/**
	 * Checks to see if the Tree is near a Wall object.
	 * 
	 * @return true - If the Tree is near a Wall.
	 */
	public boolean nearWall() {

		return false;
	}

	/**
	 * Given a name and a direction, this method will create a new tree and
	 * place it in an adjacent space specified by the parameters. If the space
	 * specified is invalid either because an EObject already exists there or
	 * because the space is out of bounds, this method will do nothing.
	 * 
	 * @param name
	 *            - The name to be given to the Tree object being created.
	 * @param direction
	 *            - Takes an int from 1-4 to specify which neighbor to remove. 1
	 *            (North), 2 (South), 3 (East), 4 (West).
	 */
	public void addNearbyTree(String name, int direction) {

	}

	/**
	 * Removes a specified neighboring Tree object. If the Tree passed in by the
	 * parameter does not exist then this method does nothing.
	 * 
	 * @param tree
	 *            - The name of the Tree object to remove.
	 */
	public void removeNearbyTree(Tree tree) {

	}

}
