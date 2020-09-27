package ChooseGamer;

import java.awt.*;

import Client.ClientStart;
import Controller.GameController;
import Controller.GameJFrame;

public class ChooseStart {

	public static int width = GameController.panel_width;
	public static int height = GameController.panel_height;
	private static boolean TF = true;

	public ChooseStart() {
		if (TF) {
			PrepareGame();
			GameJFrame.init();
		}
		// int i = GameController.Owns.size();
		// for (int j = 0; j < i; j++) {
		GameJFrame.restJFrame("请选择您的英雄");
		GameJFrame.GameJFrame.add(new ChooseController(0, true));
		// }
		// this.add(new ChooseController(i, false, this));
		GameJFrame.toView();
		if (TF) {
			Insets insets = GameJFrame.GameJFrame.getInsets();
			GameJFrame.top = insets.top;
			GameJFrame.left = insets.left;
			TF = false;
		}
	}

	private void PrepareGame() {
		ClientStart.OutStreamAll(GameController.own_cilent_id + ":0501");// 加载商店信息
		ClientStart.OutStreamAll(GameController.own_cilent_id + ":0403");// 加载关卡信息

	}
}
