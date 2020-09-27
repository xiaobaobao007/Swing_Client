package Enity;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.*;

import Controller.HurtController;

public class Special {

	int state = 1;
	int scope = 5;
	String img;
	HurtController hurtController = new HurtController();
	private int id;
	private int x;
	private int y;
	private int type;
	private final int direct;
	private final int hurt;
	private int live_time;

	public Special(int id, int x, int y, int type, int direct, int hurt, int live_time) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.setType(type);
		this.direct = direct;
		this.hurt = hurt;
		this.live_time = live_time;
		this.img = "resouce/image/specials/special" + type + "/direct" + direct + "/special";
	}

	public Image getImg() {
		return new ImageIcon(img + state + ".png").getImage();
	}

	public void Draw(Graphics g, int x, int y, ImageObserver o, int i) {
		if (live_time > 0) {
			// System.out.println(getImg().);
			g.drawImage(getImg(), this.x * 20 + x, this.y * 20 + y, 100, 100, o);
			if (hurt != 0) hurtController.hurtEnemys(i, this.x, this.y, scope, this.hurt, 2);
			// g.drawRect(this.x * 1 , this.y * 1 , 300,300);
			switch (direct) {
				case 0:
					this.y -= 2;
					break;
				case 1:
					this.x += 2;
					break;
				case 2:
					this.y += 2;
					break;
				case 3:
					this.x -= 2;
					break;
				default:
					break;
			}
			this.state++;
			this.live_time--;
		} else {

		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
