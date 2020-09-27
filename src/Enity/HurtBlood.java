package Enity;

import java.awt.*;
import java.awt.image.ImageObserver;

public class HurtBlood {

	private int blood;
	private int time;
	private int type;
	private String info;

	public HurtBlood() {
		super();
	}

	public HurtBlood(int blood, int time, int type, String info) {
		super();
		this.blood = blood;
		this.time = time;
		this.type = type;
		this.info = info;
	}

	public void Draw(Graphics g, int x, int y, ImageObserver o) {
		if (type == 1) {//物理伤害提示
			g.setColor(Color.red);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("-" + blood, x + 35, y + time * 5 - 50);
		} else if (type == 2) {//法术伤害提示
			g.setColor(Color.blue);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("-" + blood, x + 35, y + time * 5 - 50);
		} else if (type == 3) {//真实伤害提示
			g.setColor(Color.white);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("-" + blood, x + 35, y + time * 5 - 50);
		} else if (type == 4) {//未命中提示
			g.setColor(Color.red);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("未命中", x + 5, y + time * 5 - 50);
		} else if (type == 5) {//暴击提示
			g.setColor(Color.red);
			g.setFont(new Font("宋体", Font.BOLD + Font.ITALIC, 35));
			g.drawString("-" + blood, x + 35, y + time * 5 - 50);
		} else if (type == 6) {//加蓝提示
			g.setColor(Color.blue);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("+" + blood, x + 35, y + time * 5 - 50);
		} else if (type == 7) {//加血提示
			g.setColor(Color.green);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("+" + blood, x + 35, y + time * 5 - 50);
		} else if (type == 8) {//冰冻提示
			g.setColor(Color.blue);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("冰冻", x + 35, y + time * 5 - 50);
		} else {
			g.setColor(Color.yellow);
			g.setFont(new Font("宋体", Font.BOLD, 20));
			g.drawString("-" + blood, x + 35, y + time * 5 - 50);
		}
		time--;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


}
