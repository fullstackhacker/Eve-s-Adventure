package models.eobjects;

import java.io.Serializable;

/**
 * The Wall object extends Item and acts as a pure obstruction in the grid
 * world. No EObject can interact with a Wall beyond being blocked by it.
 * 
 * @author Carmine Iannaccone
 * 
 */
public class Wall extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/**
	 * Locates the coordinate of the eastern most section of a wall within a
	 * sequence of Wall objects.
	 * 
	 * @return coordinate - The position of the farthest east wall section.
	 */
	public int end_x_east() {

		return 0;
	}

	/**
	 * Locates the coordinate of the northern most section of a wall within a
	 * sequence of Wall objects.
	 * 
	 * @return coordinate - The position of the farthest north wall section.
	 */
	public int end_y_north() {

		return 0;
	}

	/**
	 * Locates the coordinate of the western most section of a wall within a
	 * sequence of Wall objects.
	 * 
	 * @return coordinate - The position of the farthest west wall section.
	 */
	public int end_x_west() {

		return 0;
	}

	/**
	 * Locates the coordinate of the southern most section of a wall within a
	 * sequence of Wall objects.
	 * 
	 * @return coordinate - The position of the farthest south wall section.
	 */
	public int end_y_south() {

		return 0;
	}

	/**
	 * Checks to see if the Wall is near a Tree object.
	 * 
	 * @return true - If the Wall is near a Tree.
	 */
	public boolean nearTree() {

		return false;
	}
}
