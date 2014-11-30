package models.campaign;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A static class for all objectives that are valid
 * 
 * Can only have one per level
 */
public class Tips {
	public static final String CREATURESTAB = "You can select the \"Creture\" tab after selecting a cell within the grid world to add a creature.";
	public static final String ITEMSTAB = "You can select the \"Items\" tab after selecting a cell within the grid world to add an item.";
	public static final String SAVING = "If you go to File->Save, at the top of the screen you can make you're scenario playable in Adventure Mode!";
	public static Iterator<String> getPossibleTips() {
		ArrayList<String> possibleTips = new ArrayList<String>();
		possibleTips.add(Tips.CREATURESTAB);
		possibleTips.add(Tips.ITEMSTAB);
		possibleTips.add(Tips.SAVING);
		return possibleTips.iterator();
	}

	
	public static boolean validate(String tip) {
		Iterator<String> possibleTips = getPossibleTips();
		while (possibleTips.hasNext()) {
			if (possibleTips.next().equals(tip)) {
				return true;
			}
		}
		return false;
	}
}