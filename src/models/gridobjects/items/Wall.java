package models.gridobjects.items;

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
	 * If a neighboring wall segment exists in a particular direction then it is
	 * stored within these variables.
	 */
	@SuppressWarnings("unused")
	private Wall north, south, east, west;
	/**
	 * Stores the side of the grid box that the wall exists on ("north", "south", "east", "west").
	 */
	@SuppressWarnings("unused")
	private String side;

	public Wall(int iD, String side) {
		super(iD);
		north = null;
		south = null;
		east = null;
		west = null;
		this.side = side;

	}

	/**
	 * Locates the coordinate of the eastern most section of a wall within a
	 * sequence of Wall objects.
	 * 
	 * @return coordinate - The farthest east wall section.
	 */
	public Wall end_east() {

		return null;
	}

	/**
	 * Locates the coordinate of the northern most section of a wall within a
	 * sequence of Wall objects.
	 * 
	 * @return coordinate - The farthest north wall section.
	 */
	public Wall end_north() {

		return null;
	}

	/**
	 * Locates the coordinate of the western most section of a wall within a
	 * sequence of Wall objects.
	 * 
	 * @return coordinate - The farthest west wall section.
	 */
	public Wall end_west() {

		return null;
	}

	/**
	 * Locates the coordinate of the southern most section of a wall within a
	 * sequence of Wall objects.
	 * 
	 * @return coordinate - The farthest south wall section.
	 */
	public Wall end_south() {

		return null;
	}

	/**
	 * Returns the next Wall segment in a specified direction. If none exists
	 * then it returns null;
	 * 
	 * @param direction
	 *            - The direction that the method should search in ("north",
	 *            "south", "east", "west").
	 * @return nextWall - The next Wall object in the specified direction.
	 */
	public Wall getNextWall(String direction) {
		return null;

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
