package launcher;

import java.awt.Color;

public class ColorCreator {
	public static Color createColorWithId(String colorId){
		switch(colorId){
			case "BLUE":
				return Color.blue;
			case "BLACK":
				return Color.black;
			case "DARKGRAY":
				return Color.darkGray;
			case "GRAY":
				return Color.gray;
			case "LIGHTGRAY":
				return Color.lightGray;
			case "RED":
				return Color.red;
			case "GREEN":
				return Color.green;
			case "YELLOW":
				return Color.yellow;
			case "PINK":
				return Color.pink;
			case "CYAN":
				return Color.cyan;
			case "MAGENTA":
				return Color.magenta;
			case "ORANGE":
				return Color.orange;
			case "WHITE":
				return Color.white;
			default:
				return Color.white;
		}
	}
}
