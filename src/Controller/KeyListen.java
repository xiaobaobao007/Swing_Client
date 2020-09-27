package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Client.ClientStart;
import Enity.OwnPeople;
import GameStart.GameStart;

public class KeyListen implements KeyListener {
	private boolean up = false;
	private boolean left = false;
	private boolean right = false;
	private boolean down = false;
	private boolean attack = false;
	private boolean Magic_attack = false;
	private boolean space = false;

	public KeyListen() {
		super();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_J) {
			attack = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			Magic_attack = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			OwnPeople.stateController.Start_Frozen(1000);
			GameController.own.addHurtBloods(0, 10, 8, "");
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameStart.TF = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			space = true;
		}
		if (up) {
			if (OwnPeople.stateController.Move()) {
				GameController.own.setDirect(0);
				GameController.own.move(0);
			}
		}
		if (down) {
			if (OwnPeople.stateController.Move()) {

				GameController.own.setDirect(2);
				GameController.own.move(2);
			}
		}
		if (left) {
			if (OwnPeople.stateController.Move()) {

				GameController.own.setDirect(3);
				GameController.own.move(1);
			}
		}
		if (right) {
			if (OwnPeople.stateController.Move()) {
				GameController.own.setDirect(1);
				GameController.own.move(3);
			}
		}
		if (attack) {
			if (OwnPeople.stateController.Attack()) {
				ClientStart.OutStreamAll(GameController.own_cilent_id + ":0101:2:1");
				GameController.own.people_attack();
			}
		}
		if (Magic_attack) {
			if (OwnPeople.stateController.Attack()) {
				GameController.own.Magic_attack();
			}
		}
		if (space) {
			ClientStart.OutStreamAll(GameController.own_cilent_id + ":0101:3:1");
			GameController.own.pullGoods();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_J) {
			attack = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			Magic_attack = false;
		}
//		if (e.getKeyCode() == KeyEvent.VK_T) {//测试
//			System.out.println(GameController.own.getX()+"|"+GameController.own.getY());
//		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			space = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
