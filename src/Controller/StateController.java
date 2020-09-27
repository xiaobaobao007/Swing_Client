package Controller;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.*;

public class StateController implements Runnable {

	int attack = 1;
	int magic = 1;
	int move = 1;
	int vertigo = 0;
	int frozen = 0;
	int attack_time = 0;
	int magic_time = 0;
	int move_time = 0;
	int vertigo_time = 0;
	int frozen_time = 0;
	Image frozen_img = new ImageIcon("resouce/image/state/frozen.png").getImage();
	private boolean TF = true;

	public StateController() {
		new Thread(this).start();
	}

	public void Draw(Graphics g, int x, int y, ImageObserver o) {
		if (frozen == 1)
			g.drawImage(frozen_img, x - 30, y - 30, 160, 210, o);
	}

	public synchronized void setTF() {
		TF = false;
	}

	public synchronized void Stop_Attack(int time) {
		attack = 0;
		attack = time;
	}

	public synchronized void Stop_Magic(int time) {
		magic = 0;
		magic_time = time;
	}

	public synchronized void Stop_Move(int time) {
		move = 0;
		move_time = time;
	}

	public void Start_Vertigo(int time) {
		vertigo = 1;
		vertigo_time = time;
	}

	public void Start_Frozen(int time) {
		frozen = 1;
		frozen_time = time;
	}

	public synchronized boolean Attack() {
		return attack == 1 && vertigo == 0 && frozen == 0;
	}

	public synchronized boolean Magic() {
		return magic == 1;
	}

	public synchronized boolean Move() {
		return move == 1 && vertigo == 0 && frozen == 0;
	}

	public boolean Vertigo() {
		return vertigo == 1;
	}

	public boolean Frozen() {
		return frozen == 1;
	}

	public synchronized void TF_Attack() {
		if (attack_time > 0) {
			attack_time -= 100;
			if (attack_time <= 0)
				attack = 1;
		}
	}

	public synchronized void TF_Magic() {
		if (magic_time > 0) {
			magic_time -= 100;
			if (magic_time <= 0)
				magic = 1;
		}
	}

	public synchronized void TF_Move() {
		if (move_time > 0) {
			move_time -= 100;
			if (move_time <= 0)
				move = 1;
		}
	}

	public synchronized void TF_Vertigo() {
		if (vertigo_time > 0) {
			vertigo_time -= 100;
			if (vertigo_time <= 0)
				vertigo = 0;
		}
	}

	public synchronized void TF_Frozen() {
		if (frozen_time > 0) {
			frozen_time -= 100;
			if (frozen_time <= 0)
				frozen = 0;
		}
	}

	@Override
	public void run() {
		while (TF) {
			try {
				Thread.sleep(100);
				TF_Attack();
				TF_Magic();
				TF_Move();
				TF_Vertigo();
				TF_Frozen();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
}
