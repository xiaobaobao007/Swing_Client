package ChooseChapter;

import java.awt.*;

import Controller.GameController;
import Controller.GameJFrame;

public class ChooseChapter {

	public static int width = GameController.panel_width;
	public static int height = GameController.panel_height;
	private static GameLeave gameLeave;
	//private static GameFriend gameFriend;
	private static ChooseLeave chooseLeave;
	private static int x1 = 100;
	private static int y1 = 100;
	private static int x2 = 400;
	private static int y2 = 100;

	public ChooseChapter() {
		gameLeave = new GameLeave(x1, y1);
		// gameFriend = new GameFriend(x2, y2);
		chooseLeave = new ChooseLeave(100, 100);
		GameJFrame.restJFrame("选择功能");
		GameJFrame.GameJFrame.add(gameLeave);
//		GameJFrame.GameJFrame.add(gameFriend);
		GameJFrame.setBack(new Color(148, 198, 221));
		GameJFrame.GameJFrame.setVisible(true);
	}

	public static void restNumbers() {
		x1 = 100;
		y1 = 100;
		x2 = 400;
		y2 = 100;
	}

	public static void main(String[] args) {
		new ChooseChapter();
	}

	static void ToLeave() {
		chooseLeave = new ChooseLeave(100, 100);
		GameJFrame.restJFrame("选择关卡", chooseLeave);
		GameJFrame.repaintJFrame();
	}

	static void ToMain() {
		GameJFrame.restJFrame("游戏", gameLeave);
		GameJFrame.repaintJFrame();
	}
}
