package models.campaign;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import models.Coordinate;
import models.gridobjects.GridObject;
import models.gridobjects.creatures.Creature;
import models.gridobjects.items.Bamboo;
import models.gridobjects.items.Item;
import models.gridobjects.items.Shrub;
import models.gridobjects.items.Tree;
import models.gridobjects.items.Wall;
import views.grid.GridWorld;
import views.scenes.SandboxScene;
import exceptions.IllegalValueException;

/**
 * World represents the playing environment with Eve and the different things
 * that Eve can interact with
 * 
 */
public class World implements Serializable {

	/**
	 * Square represents a location in the world. A square can be occupied by
	 * Eve, one her friends, a stick of bamboo, a wall, or a shrub
	 *
	 */
	
	public static int bambooCount = 0;
	
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
		 * Wall on upside of the square
		 */
		private Wall upWall;
		/**
		 * Wall on downside of the square
		 */
		private Wall downWall;
		/**
		 * Wall on left of the square
		 */
		private Wall leftWall;
		/**
		 * Wall on right of the square
		 */
		private Wall rightWall;
		/**
		 * Creature that on square
		 */
		private Creature creature;

		/**
		 * Constructor
		 */
		private Square() {
			this.item = null;
			this.creature = null;
			this.upWall = null;
			this.downWall = null;
			this.leftWall = null;
			this.rightWall = null;
		}

		private Item currentItem() {
			return this.item;
		}

		private Creature currentCreature() {
			return this.creature;
		}

		/**
		 * Adding an item to the square if there is no item on the square
		 * 
		 * @see replaceItem
		 * @param item
		 *            the object to add to the square
		 * @return true iff the object was added to the board
		 * @return false the object was not added to the board
		 */
		private boolean addItem(Item item) {
			if (this.hasItem())
				return false;
			this.item = item;
			return true;
		}

		/**
		 * Replaces the current item on the square. If there is no current item,
		 * this returns false
		 * 
		 * @see addItem
		 * 
		 * @param item
		 *            the object to add to the square
		 * @return true iff the object replaced the old object on the square
		 * @return false iff the object did not replace the old object on the
		 *         square
		 */
		private boolean replaceItem(Item item) {
			if (this.hasItem())
				return false;
			this.item = item;
			return true;
		}

		/**
		 * Removes the object from the square
		 * 
		 */
		private void removeItem() {
			this.item = null;
		}

		/**
		 * Add a creature to the square iff there is no creature
		 * 
		 * @see replaceCreature()
		 * 
		 * @param creature
		 *            the creature to add the square
		 * @return true iff able to add a creature to the square
		 */
		private boolean addCreature(Creature creature) {
			if (this.hasCreature())
				return false;
			this.creature = creature;
			return true;
		}

		/**
		 * Replaces the creature on the square
		 * 
		 * @see addCreature()
		 * 
		 * @param creature
		 *            the new creature for the square
		 * @return true iff replaces a creature
		 * @throws Exception
		 */
		private boolean replaceCreature(Creature creature) {
			this.creature = creature;
			return true;
		}

		/**
		 * Remove the creature on the square
		 */
		private Creature removeCreature() {
			Creature temp = this.creature;
			this.creature = null;
			return temp;
		}

		private boolean addUpWall(Wall wall) {
			if (this.hasUpWall())
				return false;
			this.upWall = wall;
			return true;
		}

		private boolean addDownWall(Wall wall) {
			if (this.hasDownWall())
				return false;
			this.downWall = wall;
			return true;
		}

		private boolean addLeftWall(Wall wall) {
			if (this.hasLeftWall())
				return false;
			this.leftWall = wall;
			return true;
		}

		private boolean addRightWall(Wall wall) {
			if (this.hasRightWall())
				return false;
			this.rightWall = wall;
			return true;
		}

		private void removeUpWall() {
			this.upWall = null;
		}

		private void removeDownWall() {
			this.downWall = null;
		}

		private void removeLeftWall() {
			this.leftWall = null;
		}

		private void removeRightWall() {
			this.rightWall = null;
		}
		
		private Wall currentUpWall(){
			return this.upWall; 
		}
		
		private Wall currentDownWall(){
			return this.downWall;
		}
		
		private Wall currentLeftWall(){
			return this.leftWall; 
		}
		
		private Wall currentRightWall(){
			return this.rightWall; 
		}
		/**
		 * 
		 * Check if the square has an item
		 * 
		 * @return true iff there is an object in the square
		 */
		private boolean hasItem() {
			return this.item != null;
		}

		/**
		 * Check if the square has a creature
		 * 
		 * @return true iff there is a creature in the square
		 */
		private boolean hasCreature() {
			return this.creature != null;
		}

		/**
		 * Check if there is an up wall
		 * 
		 * @return true iff there is an up wall
		 */
		private boolean hasUpWall() {
			return this.upWall != null;
		}

		private boolean hasDownWall() {
			return this.downWall != null;
		}

		private boolean hasLeftWall() {
			return this.leftWall != null;
		}

		private boolean hasRightWall() {
			return this.rightWall != null;
		}

		@Override
		/**
		 * Converts the Square to a string
		 * 
		 * @return  a String representation the square based on what object is on the square
		 */
		public String toString() {
			String s = this.hasLeftWall() ? "x" : "|";
			s += this.hasCreature() ? this.creature.toString() : " ";
			s += this.hasItem() ? this.item.toString() : " ";
			s += this.hasRightWall() ? " x" : " |";
			return s;
		}
	}

	/**
	 * Name of the world
	 */
	private String name;

	/**
	 * Eve
	 */
	private Creature eve;

	/**
	 * Serializable ID for saving
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Grid of Squares
	 */
	private Square[][] world;

	private Stack<Coordinate> evesMoves;
	/**
	 * Constructor for the world
	 * 
	 * @param name
	 *            The name of the world
	 * @param height
	 *            The height of the world
	 * @param width
	 *            The width of the world
	 */
	public World(String name, int height, int width) {
		this.name = name;
		this.world = new Square[height][width];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				this.world[y][x] = new Square();
			}
		}
		findEve();
	}

	/**
	 * Gets the name of the world
	 * 
	 * @return the name of the world
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the world
	 * 
	 * @param name
	 *            The new name of the world
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the item at the given coordinate
	 * 
	 * @param coordinate
	 * @return
	 */
	public Item itemAt(Coordinate coordinate) {
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()].currentItem();
	}

	/**
	 * Retrieves the creature at the given coordinate
	 * 
	 * @param coordinate
	 * @return
	 */
	public Creature creatureAt(Coordinate coordinate) {
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()]
				.currentCreature();
	}

	/**
	 * Add an item to the world at a specified location
	 * 
	 * @see #replaceItem(Item)
	 * 
	 * @param item
	 *            the Item to add to the world
	 * @return true iff the Item was placed into the world
	 */
	public boolean addItem(Item item) {
		if (item == null)
			throw new IllegalValueException();
		return this.world[item.getY()][item.getX()].addItem(item);
	}

	/**
	 * Replaces the current object on the world at the specified location
	 * 
	 * @see #addItem(Item)
	 * 
	 * @param item
	 *            the Item to add to the world
	 * @return true iff the Item replaced another item in the world
	 */
	public boolean replaceItem(Item item) {
		if (item == null)
			throw new IllegalValueException();
		return this.world[item.getY()][item.getX()].replaceItem(item);
	}

	/**
	 * Removes an object from the world at the specified location
	 * 
	 * @param coordinate
	 *            The coordinate representation of the object in the world
	 */
	public void removeItem(Coordinate coordinate) {
		verifyCoordinate(coordinate);
		this.world[coordinate.getY()][coordinate.getX()].removeItem();
	}

	/**
	 * Adds a creature if there isn't anything on that square
	 * 
	 * @see #replaceCreature(Creature)
	 * 
	 * @param creature
	 *            the creature
	 * @return true iff added the creature to the square
	 */
	public boolean addCreature(Creature creature) {
		if (eve == null && creature.getName().equals("Eve"))
			this.eve = creature;
		if (creature == null)
			throw new IllegalValueException();
		return this.world[creature.getY()][creature.getX()]
				.addCreature(creature);
	}

	/**
	 * Replaces the creature on the square
	 * 
	 * @param creature
	 *            the new creature for the square
	 * @return true iff the old creature was replaced by the new creature
	 */
	public boolean replaceCreature(Creature creature) {
		if (creature == null)
			throw new IllegalValueException();
		return this.world[creature.getY()][creature.getX()]
				.replaceCreature(creature);
	}

	/**
	 * Remove the creature on the specified location
	 * 
	 * @param coordinate
	 *            A coordinate object representing the location of the creature
	 *            to remove
	 */
	public Creature removeCreature(Coordinate coordinate) {
		verifyCoordinate(coordinate);
		if(this.getSquareAt(coordinate).currentCreature().getName() == "Eve")
			this.eve = null;
		return this.world[coordinate.getY()][coordinate.getX()]
				.removeCreature();
	}

	/**
	 * Add a wall to a square The direction should be from Coordinate (UP, DOWN,
	 * LEFT, RIGHT)
	 */
	public boolean addWall(Coordinate coordinate, int direction) {
		switch (direction) {
		case Coordinate.UP:
			return this.getSquareAt(coordinate).addUpWall(
					new Wall(new Random().nextInt(5) * this.hashCode(),
							direction));
		case Coordinate.DOWN:
			return this.getSquareAt(coordinate).addDownWall(
					new Wall(new Random().nextInt(5) * this.hashCode(),
							direction));
		case Coordinate.LEFT:
			return this.getSquareAt(coordinate).addLeftWall(
					new Wall(new Random().nextInt(5) * this.hashCode(),
							direction));
		case Coordinate.RIGHT:
			return this.getSquareAt(coordinate).addRightWall(
					new Wall(new Random().nextInt(5) * this.hashCode(),
							direction));
		default:
			return false;
		}
	}

	public void removeWall(Coordinate coordinate, int direction) {
		if (!verifyCoordinate(coordinate))
			throw new IllegalValueException("Bad coordinates");
		switch (direction) {
		case Coordinate.UP:
			this.getSquareAt(coordinate).removeUpWall();
			break;
		case Coordinate.DOWN:
			this.getSquareAt(coordinate).removeDownWall();
			break;
		case Coordinate.LEFT:
			this.getSquareAt(coordinate).removeLeftWall();
			break;
		case Coordinate.RIGHT:
			this.getSquareAt(coordinate).removeRightWall();
			break;
		default:
			throw new IllegalValueException("Illegal direction");
		}
	}

	/**
	 * Checks to see if there is an item on the location
	 * 
	 * @param x
	 *            the x coordinate of the location
	 * @param y
	 *            the y coordinate of the location
	 * @return true iff the specified location has an item
	 */
	public boolean hasItem(Coordinate coordinate) {
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()].hasItem();
	}

	/**
	 * Checks to see if there is a creature on the location
	 * 
	 * @param x
	 *            the x coordinate of the location
	 * @param y
	 *            the y coordinate of the location
	 * @return true iff the specified location has a creature
	 */
	public boolean hasCreature(Coordinate coordinate) {
		verifyCoordinate(coordinate);
		return this.world[coordinate.getY()][coordinate.getX()].hasCreature();
	}

	/**
	 * Gets the area of the world
	 * 
	 * @return the area of the world
	 */
	public int getArea() {
		return this.getHeight() * this.getWidth();
	}

	/**
	 * Gets the height of the world
	 * 
	 * @return the height of the world
	 */
	public int getHeight() {
		return this.world.length;
	}

	/**
	 * Gets the width of the world
	 * 
	 * @return the width of the world
	 */
	public int getWidth() {
		return this.world[0].length;
	}

	/**
	 * Verifies that the provided coordinate is within the bounds of the world
	 * and is not null.
	 * 
	 * @param coordinate
	 */
	private boolean verifyCoordinate(Coordinate coordinate) {
		System.out.println("VERIFY: " + coordinate);
		System.out.println("VERIFY WIDTH: " + this.getWidth());
		System.out.println("VERIFY HEIGHT: " + this.getHeight());

		if (coordinate.getX() > -1 && coordinate.getX() < this.getWidth()
				&& coordinate.getY() > -1
				&& coordinate.getY() < this.getHeight())
			return true;
		return false;
	}

	/**
	 * Moves Eve in the direction that she is facing
	 */
	public void moveEve() {
		System.out.println("MOVE: CURRENT DIRECTION: "
				+ this.getEve().getDirection());

		Coordinate currentEveLocation = this.getEve().getCoordinates();
		Coordinate newEveLocation = null;

		switch (this.getEve().getDirection()) {
		case Coordinate.UP:
			if (this.getSquareAt(currentEveLocation).hasUpWall())
				return;

			newEveLocation = new Coordinate(currentEveLocation.getX(),
					currentEveLocation.getY() + 1);

			if (!verifyCoordinate(newEveLocation))
				return;
			if (this.getSquareAt(newEveLocation).hasCreature())
				return;
			GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
					.getY() + 1]
					.setGraphic(GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
							.getY()].getGraphic());
			if (!this.getSquareAt(currentEveLocation).hasItem())
				GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
						.getY()].setGraphic(null);
			if ((this.getSquareAt(currentEveLocation).currentItem() instanceof Bamboo))
				GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
						.getY()].setGraphic(SandboxScene.getBambooI());
			GridWorld.getInstance().setVisible(true);
			break;
		case Coordinate.DOWN:
			if (this.getSquareAt(currentEveLocation).hasDownWall())
				return;

			newEveLocation = new Coordinate(currentEveLocation.getX(),
					currentEveLocation.getY() - 1);
			if (!verifyCoordinate(newEveLocation))
				return;
			if (this.getSquareAt(newEveLocation).hasCreature())
				return;
			GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
					.getY() - 1]
					.setGraphic(GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
							.getY()].getGraphic());
			if (!this.getSquareAt(currentEveLocation).hasItem())
				GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
						.getY()].setGraphic(null);
			if ((this.getSquareAt(currentEveLocation).currentItem() instanceof Bamboo))
				GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
						.getY()].setGraphic(SandboxScene.getBambooI());
			GridWorld.getInstance().setVisible(true);
			break;
		case Coordinate.LEFT:
			if (this.getSquareAt(currentEveLocation).hasLeftWall())
				return;
			newEveLocation = new Coordinate(currentEveLocation.getX() - 1,
					currentEveLocation.getY());
			if (!verifyCoordinate(newEveLocation))
				return;
			if (this.getSquareAt(newEveLocation).hasCreature())
				return;
			GridWorld.gridButtons[currentEveLocation.getX() - 1][currentEveLocation
					.getY()].setGraphic(GridWorld.gridButtons[currentEveLocation
					.getX()][currentEveLocation.getY()].getGraphic());
			if (!this.getSquareAt(currentEveLocation).hasItem())
				GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
						.getY()].setGraphic(null);
			if ((this.getSquareAt(currentEveLocation).currentItem() instanceof Bamboo))
				GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
						.getY()].setGraphic(SandboxScene.getBambooI());
			GridWorld.getInstance().setVisible(true);
			break;
		case Coordinate.RIGHT:
			if (this.getSquareAt(currentEveLocation).hasRightWall())
				return;
			newEveLocation = new Coordinate(currentEveLocation.getX() + 1,
					currentEveLocation.getY());
			if (!verifyCoordinate(newEveLocation))
				return;
			if (this.getSquareAt(newEveLocation).hasCreature())
				return;
			GridWorld.gridButtons[currentEveLocation.getX() + 1][currentEveLocation
					.getY()].setGraphic(GridWorld.gridButtons[currentEveLocation
					.getX()][currentEveLocation.getY()].getGraphic());
			if (!this.getSquareAt(currentEveLocation).hasItem())
				GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
						.getY()].setGraphic(null);
			if ((this.getSquareAt(currentEveLocation).currentItem() instanceof Bamboo))
				GridWorld.gridButtons[currentEveLocation.getX()][currentEveLocation
						.getY()].setGraphic(SandboxScene.getBambooI());
			GridWorld.getInstance().setVisible(true);
			break;
		default:
			throw new IllegalValueException("Eve facing illegal direction");
		}

		System.out.println("eve current: " + currentEveLocation);
		System.out.println("new local: " + newEveLocation);
		this.getSquareAt(newEveLocation).addCreature(
				this.getSquareAt(currentEveLocation).removeCreature());
		this.getEve().move();

		System.out.println("MOVE: CURRENT LOCATION: "
				+ this.getEve().getCoordinates());
		System.out.println("MOVE: END DIRECTION: "
				+ this.getEve().getDirection());

	}

	/**
	 * Put bamboo on current square
	 */
	public void evePutBamboo() {
		Coordinate currentEveLocation = this.getEve().getCoordinates();
		if (this.getSquareAt(currentEveLocation).hasItem())
			return;
		this.getSquareAt(currentEveLocation).addItem(
				new Bamboo(new Random().nextInt(5)));
		this.getEve().decrementBamboo();

	}

	/**
	 * Pick up bamboo on current square
	 */
	public void evePickBamboo() {
		Coordinate currentEveLocation = this.getEve().getCoordinates();
		if (!this.getSquareAt(currentEveLocation).hasItem())
			return;
		if (!(this.getSquareAt(currentEveLocation).currentItem() instanceof Bamboo))
			return;
		this.getSquareAt(currentEveLocation).removeItem();
		this.getEve().incrementBamboo();
	}

	/**
	 * Take bamboo from nearby shrub
	 */
	public void takeBambooFromShrub() {
		Coordinate shrubCoordinate = null;
		switch (this.getEve().getDirection()) {
		case Coordinate.UP:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates()
					.getX(), this.getEve().getCoordinates().getY() + 1);
			break;
		case Coordinate.DOWN:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates()
					.getX(), this.getEve().getCoordinates().getY() - 1);
			verifyCoordinate(shrubCoordinate);
			break;
		case Coordinate.LEFT:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates()
					.getX() - 1, this.getEve().getCoordinates().getY());
			break;
		case Coordinate.RIGHT:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates()
					.getX() + 1, this.getEve().getCoordinates().getY());
			break;
		default:
			throw new IllegalValueException(
					"Eve is facing an illegal direction");
		}

		if (!verifyCoordinate(shrubCoordinate)) { // eve is not facing a near by
													// bush
			return; // dont do anything
		}

		if (this.world[shrubCoordinate.getY()][shrubCoordinate.getX()].item instanceof Shrub) {
			if (((Shrub) this.world[shrubCoordinate.getY()][shrubCoordinate
					.getX()].item).hasBamboo()) {
				((Shrub) this.world[shrubCoordinate.getY()][shrubCoordinate
						.getX()].item).removeBamboo();
				this.getEve().incrementBamboo();
			}
		}
	}

	/**
	 * Put bamboo in a nearby shrub
	 */
	public void putBambooInShrub() {
		Coordinate shrubCoordinate = null;
		switch (this.getEve().getDirection()) {
		case Coordinate.UP:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates()
					.getX(), this.getEve().getCoordinates().getY() + 1);
			break;
		case Coordinate.DOWN:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates()
					.getX(), this.getEve().getCoordinates().getY() - 1);
			verifyCoordinate(shrubCoordinate);
			break;
		case Coordinate.LEFT:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates()
					.getX() - 1, this.getEve().getCoordinates().getY());
			break;
		case Coordinate.RIGHT:
			shrubCoordinate = new Coordinate(this.getEve().getCoordinates()
					.getX() + 1, this.getEve().getCoordinates().getY());
			break;
		default:
			throw new IllegalValueException(
					"Eve is facing an illegal direction");
		}

		if (!verifyCoordinate(shrubCoordinate)) { // eve is not facing a near by
													// bush
			return; // dont do anything
		}

		if (this.world[shrubCoordinate.getY()][shrubCoordinate.getX()].item instanceof Shrub) {
			if (((Shrub) this.world[shrubCoordinate.getY()][shrubCoordinate
					.getX()].item).hasBamboo()) {
				((Shrub) this.world[shrubCoordinate.getY()][shrubCoordinate
						.getX()].item).addBamboo();
				this.getEve().decrementBamboo();
			}
		}
	}

	/**
	 * Check's to see if the square in front of eve is open or not
	 */
	public boolean frontIsClear() {
		System.out.println("WORLD: FRONT IS CLEAR CHECKING BEGIN");
		System.out.println("FRONTISCLEAR: CURRENT DIRECTION: "
				+ this.getEve().getDirection());

		Coordinate eveLocation = this.getEve().getCoordinates();
		Coordinate front = null;
		switch (this.getEve().getDirection()) {
		case Coordinate.UP:
			if (this.getSquareAt(eveLocation).hasUpWall())
				return false;
			front = new Coordinate(eveLocation.getX(), eveLocation.getY() + 1);
			break;
		case Coordinate.DOWN:
			if (this.getSquareAt(eveLocation).hasDownWall())
				return false;
			front = new Coordinate(eveLocation.getX(), eveLocation.getY() - 1);
			break;
		case Coordinate.LEFT:
			if (this.getSquareAt(eveLocation).hasLeftWall())
				return false;
			front = new Coordinate(eveLocation.getX() - 1, eveLocation.getY());
			break;
		case Coordinate.RIGHT:
			if (this.getSquareAt(eveLocation).hasRightWall())
				return false;
			front = new Coordinate(eveLocation.getX() + 1, eveLocation.getY());
			break;
		default:
			throw new IllegalValueException("Eve facing illegal direction");
		}

		System.out.println(this.getEve().getDirection());
		System.out.println("FRONT IS CLEAR: EVE'S LOCATION: " + eveLocation);
		System.out.println("FRONT IS CLEAR: FRONT LOCATION: " + front);

		if (!verifyCoordinate(front))
			return false;

		System.out.println("HERE");
		return !this.getSquareAt(front).hasCreature();
	}

	/**
	 * Gets all the objects in the world. This includes all creatures and items
	 * so everything
	 * 
	 * @return all the objects that are currently in the world
	 */
	public Iterator<GridObject> getObjects() {
		ArrayList<GridObject> objects = new ArrayList<GridObject>();
		for (int y = 0; y < this.world.length; y++) {
			for (int x = 0; x < this.world[y].length; x++) {
				if (world[y][x].hasItem()) {
					objects.add(world[y][x].item);
				} else if (world[y][x].hasCreature()) {
					objects.add(world[y][x].creature);
				}
			}
		}

		return objects.iterator();
	}

	/**
	 * Gets only the items in the world. Creatures not included.
	 * 
	 * @return the items in the world
	 */
	public Iterator<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		for (int y = 0; y < this.world.length; y++) {
			for (int x = 0; x < this.world[y].length; x++) {
				if (world[y][x].hasItem()) {
					items.add(world[y][x].item);
				}
			}
		}

		return items.iterator();
	}

	/**
	 * Gets only bamboo in the world
	 * 
	 * @return all the bamboo in the world
	 */
	public Iterator<Bamboo> getBamboo() {
		ArrayList<Bamboo> bamboos = new ArrayList<Bamboo>();
		for (int y = 0; y < this.world.length; y++) {
			for (int x = 0; x < this.world[y].length; x++) {
				if (world[y][x].hasItem()) {
					if (world[y][x].item.toString().equals("B"))
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
	 * @return the creatures in the world EXCLUDING eve
	 */
	public Iterator<Creature> getCreatures() {
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		for (int y = 0; y < this.world.length; y++) {
			for (int x = 0; x < this.world[y].length; x++) {
				if (world[y][x].hasCreature()) {
					if (!world[y][x].creature.isEve()) {
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
	 * @return all the shrubs in the world
	 */
	public Iterator<Shrub> getShrubs() {
		ArrayList<Shrub> shrubs = new ArrayList<Shrub>();
		for (int y = 0; y < this.world.length; y++) {
			for (int x = 0; x < this.world[y].length; x++) {
				if (world[y][x].hasItem()) {
					if (world[y][x].item.toString().equals("S")) {
						shrubs.add((Shrub) world[y][x].item);
					}
				}
			}
		}

		return shrubs.iterator();
	}

	/**
	 * Gets only the trees in the world
	 * 
	 * @return all the trees in the world
	 */
	public Iterator<Tree> getTrees() {
		ArrayList<Tree> trees = new ArrayList<Tree>();
		for (int y = 0; y < this.world.length; y++) {
			for (int x = 0; x < this.world[y].length; x++) {
				if (world[y][x].hasItem()) {
					if (world[y][x].item.toString().equals("T")) {
						trees.add((Tree) world[y][x].item);
					}
				}
			}
		}

		return trees.iterator();
	}

	/**
	 * Gets only the trees in the world
	 * 
	 * @return all the trees in the world
	 */
	public Iterator<Wall> getWalls() {
		ArrayList<Wall> walls = new ArrayList<Wall>();
		for (int y = 0; y < this.world.length; y++) {
			for (int x = 0; x < this.world[y].length; x++) {
				if (world[y][x].hasItem()) {
					if (world[y][x].item.toString().equals("W")) {
						walls.add((Wall) world[y][x].item);
					}
				}
			}
		}

		return walls.iterator();
	}

	/**
	 * Gets Eve
	 * 
	 * @return the Eve Creature Object
	 */
	public Creature getEve() {
		return this.eve;
	}

	public void findEve() {
		for (int y = 0; y < this.world.length; y++) {
			for (int x = 9; x < this.world[y].length; x++) {
				if (this.world[y][x].hasCreature()
						&& this.world[y][x].currentCreature().getName()
								.equals("Eve"))
					this.eve = this.world[y][x].currentCreature();
			}
		}
	}

	private Square getSquareAt(Coordinate coordinate) {
		return this.world[coordinate.getY()][coordinate.getX()];
	}

	/**
	 * Prints the world out to the console
	 */
	public void printWorld() {
		System.out.print(this);
	}
	
	@Override
	/**
	 * 
	 */
	protected Object clone(){
		World world = new World(this.name, this.getHeight(), this.getWidth()); 
		//add things to the world
		for(int row = 0; row < this.getHeight(); row++){
			for(int col = 0; col < this.getWidth(); col++){
				Coordinate currentPosition = new Coordinate(col, row); 
				if(this.getSquareAt(currentPosition).hasCreature()) world.getSquareAt(currentPosition).addCreature(this.getSquareAt(currentPosition).currentCreature().copy());
				if(this.getSquareAt(currentPosition).hasItem()) world.getSquareAt(currentPosition).addItem(this.getSquareAt(currentPosition).currentItem().copy());
				if(this.getSquareAt(currentPosition).hasUpWall()) world.getSquareAt(currentPosition).addUpWall(this.getSquareAt(currentPosition).currentUpWall().copy());
				if(this.getSquareAt(currentPosition).hasDownWall()) world.getSquareAt(currentPosition).addDownWall(this.getSquareAt(currentPosition).currentDownWall().copy());
				if(this.getSquareAt(currentPosition).hasRightWall()) world.getSquareAt(currentPosition).addRightWall(this.getSquareAt(currentPosition).currentRightWall().copy());
				if(this.getSquareAt(currentPosition).hasLeftWall()) world.getSquareAt(currentPosition).addLeftWall(this.getSquareAt(currentPosition).currentLeftWall().copy());
			}
		}
		return world;
	}
	
	public World copyWorld(){
		return (World)this.clone();
	}

	public void overwrite(World world){
		for(int row = 0; row < this.getHeight(); row++){
			for(int col = 0; col < this.getWidth(); col++){
				Coordinate currentPosition = new Coordinate(col, row);
				
				//overwrite the creatures
				if(world.getSquareAt(currentPosition).hasCreature() && !this.getSquareAt(currentPosition).hasCreature()) 
					this.getSquareAt(currentPosition).addCreature(world.getSquareAt(currentPosition).currentCreature()); 
				else if(world.getSquareAt(currentPosition).hasCreature() && this.getSquareAt(currentPosition).hasCreature())
					this.getSquareAt(currentPosition).replaceCreature(world.getSquareAt(currentPosition).currentCreature());
				else
					this.getSquareAt(currentPosition).removeCreature(); 
				
				//overwrite the items
				if(world.getSquareAt(currentPosition).hasItem() && !this.getSquareAt(currentPosition).hasItem())
					this.getSquareAt(currentPosition).addItem(world.getSquareAt(currentPosition).currentItem()); 
				else if(world.getSquareAt(currentPosition).hasItem() && this.getSquareAt(currentPosition).hasItem())
					this.getSquareAt(currentPosition).replaceItem(world.getSquareAt(currentPosition).currentItem());
				else
					this.getSquareAt(currentPosition).removeItem();
					
				//overwrite the walls
				this.getSquareAt(currentPosition).removeUpWall(); 
				this.getSquareAt(currentPosition).removeDownWall(); 
				this.getSquareAt(currentPosition).removeRightWall(); 
				this.getSquareAt(currentPosition).removeLeftWall(); 
				if(world.getSquareAt(currentPosition).hasUpWall())
					this.getSquareAt(currentPosition).addUpWall(world.getSquareAt(currentPosition).upWall.copy());
				if(world.getSquareAt(currentPosition).hasDownWall())
					this.getSquareAt(currentPosition).addDownWall(world.getSquareAt(currentPosition).downWall.copy());
				if(world.getSquareAt(currentPosition).hasLeftWall())
					this.getSquareAt(currentPosition).addLeftWall(world.getSquareAt(currentPosition).leftWall.copy()); 
				if(world.getSquareAt(currentPosition).hasRightWall())
					this.getSquareAt(currentPosition).addRightWall(world.getSquareAt(currentPosition).rightWall.copy()); 
			}
		}
	}

	@Override
	/**
	 * Converts the world into a string based on what is on the squares
	 * Prints out in a gridlike format
	 * 
	 * @return  a string that resembles an ASCII grid
	 */
	public String toString() {
		String s = "\n";
		for (int y = this.world.length - 1; y > -1; y--) {
			for (int x = 0; x < this.world[y].length; x++) {
				s += this.world[y][x].hasUpWall() ? " -x- " : " ___ ";
			}
			s += "\n";
			for (int x = 0; x < this.world[y].length; x++) {
				s += this.world[y][x].toString();
			}
			s += "\n";
			for (int x = 0; x < this.world[y].length; x++) {
				s += this.world[y][x].hasDownWall() ? " -x- " : "|___|";
			}
			s += "\n";
		}
		return s;
	}
}
