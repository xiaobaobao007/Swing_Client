package Enity;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Skills {

	public static List<Special> specials = new ArrayList<Special>();

	public static void add(Special special) {
		specials.add(special);
	}

	public static void Draw(Graphics g, int x, int y, ImageObserver o) {
		if (specials != null) {
			for (int i = 0; i < specials.size(); i++) {
				specials.get(i).Draw(g, x, y, o, i);
			}
		}
	}
}
