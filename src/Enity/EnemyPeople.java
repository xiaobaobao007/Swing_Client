package Enity;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import Controller.HurtController;
import Controller.StateController;

public class EnemyPeople implements Runnable {

	public static final int width = 100;
	public static final int height = 100;
	// public static final int minmovewidth = 9;
	// public static final int minmoveheight = 9;
	// public static final int maxmovewidth = 100;
	// public static final int maxmoveheight = 61;
	public static final int minmovewidth = 1;
	public static final int minmoveheight = 1;
	public static final int maxmovewidth = 116;
	public static final int maxmoveheight = 74;
	private static final int scope = 20;
	public static boolean draw_bo = false;
	int smap_x = 937;
	int smap_y = 20;
	List<HurtBlood> hurtBloods;// 伤害提示
	String enemy_img_string = "resouce/image/specials/special1/special";
	String enemy_weapon_string = "resouce/image/goods/weapons/weapon2/weapon";
	String weapon_special_string = "resouce/image/specials/special5/special";// 人物武器图片
	private HurtController hurtController;
	private int x;
	private int y;
	private final int imgId;
	private int last_x;
	private int last_y;
	private String name;
	private int speed;
	private int hurt;
	private int behurt;
	private int behurt_time;
	private int state = -1;
	private int full_blood;
	private int blood;
	private boolean alive = true;
	private int direct;
	private int people;
	private int money;
	private int enemy_img_state = 1;
	private int weapon_special_state = 0;// 武器特效状态
	private int enemy_weapon_state = 1;
	private boolean weapon_state = false;
	private Thread thread;
	private Attribute attribute;
	private StateController stateController;

	public EnemyPeople(int x, int y, int people, int imgId, String name, int money, Attribute attribute) {
		super();
		hurtBloods = new ArrayList<>();
		setStateController(new StateController());
		this.x = x;
		this.y = y;
		this.imgId = imgId;
		this.last_x = x;
		this.last_y = y;
		this.name = name;
		this.money = money;
		this.people = people;
		setAttribute(attribute);
	}

	public void setController(HurtController hurtController) {
		this.hurtController = hurtController;
	}

	public void Draw(Graphics g, int x, int y, ImageObserver o) {
		if (state != -2) {
			if (draw_bo) {
				draw_bo = false;
			}
			x += this.x * 20;
			y += this.y * 20;
			g.drawImage(getEnemy_img(), x, y, width, height, o);
			if (state == 1) {
				stateController.Draw(g, x, y, o);
				if (weapon_special_state != 0) {
					int q = 7 - weapon_special_state;
					int a = q / 10;
					int b = q % 10;
					g.setColor(Color.WHITE);
					g.setFont(new Font("宋体", Font.BOLD, 20));
					g.drawString(a + "." + b, 961, 715);
					g.drawImage(getWeapon_special(), x - 100, y - 100, 300, 300, o);
				}
				int rest = (blood * 100 / full_blood);
				if (rest != 100) {
					g.setColor(Color.GRAY);
					g.fill3DRect(rest + x, -20 + y, 100 - rest, 15, true);
				}
				g.setColor(Color.RED);
				g.fill3DRect(x, -20 + y, rest, 15, true);

				g.setColor(Color.WHITE);
				g.setFont(new Font("宋体", Font.BOLD, 15));
				g.drawString(blood + "/" + full_blood, x + 20, y - 8);
				g.drawImage(getEnemy_weapon(), 40 + x, 20 + y, width, height, o);
				g.setColor(Color.RED);
				g.drawString(name, x + 20, y - 28);
				g.setFont(new Font("宋体", Font.BOLD, 30));
				for (int i = 0; i < this.hurtBloods.size(); i++) {
					this.hurtBloods.get(i).Draw(g, x, y, o);
					if (this.hurtBloods.get(i).getTime() == 0) {
						hurtBloods.remove(i);
						i--;
					}
				}
				g.setColor(Color.RED);
				g.fillOval(smap_x + this.x * 2, smap_y + this.y * 2, 20, 20);
			}
		}
	}

	public synchronized int getX() {
		return x;
	}

	public synchronized void setX(int x) {
		this.last_x = this.x;
		this.x = x;
	}

	public synchronized int getY() {
		return y;
	}

	public synchronized void setY(int y) {
		this.last_x = this.x;
		this.last_y = this.y;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPAttack(int attack) {
		return attack * 300 / (300 + attribute.getPhy_defense());
	}

	public int getMAttack(int attack) {
		return attack * 300 / (300 + attribute.getMag_defense());
	}

	public synchronized int getLast_x() {
		return last_x;
	}

	public synchronized void setLast_x(int last_x) {
		this.last_x = last_x;
	}

	public synchronized int getLast_y() {
		return last_y;
	}

	public synchronized void setLast_y(int last_y) {
		this.last_y = last_y;
	}

	public synchronized int getSpeed() {
		return speed;
	}

	public synchronized void setSpeed(int speed) {
		this.speed = speed;
	}

	public void addHurtBloods(int blood, int time, int type, String info) {
		this.hurtBloods.add(new HurtBlood(blood, time, type, info));
	}

	public int getEnemy_img_state() {
		return enemy_img_state;
	}

	public synchronized int getBehurt() {
		return behurt;
	}

	public synchronized void setBehurt(int behurt) {
		this.behurt = behurt;
	}

	public synchronized int getBehurt_time() {
		return behurt_time;
	}

	public synchronized void setBehurt_time(int behurt_time) {
		this.behurt_time = behurt_time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public synchronized int getBlood() {
		return blood;
	}

	public synchronized void setBlood(int blood) {
		this.blood = blood;
	}

	public synchronized int getFull_blood() {
		return full_blood;
	}

	public synchronized void setFull_blood(int full_blood) {
		this.full_blood = full_blood;
	}

	public synchronized int getPeople() {
		return people;
	}

	public synchronized void setPeople(int people) {
		this.people = people;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public synchronized int getDirect() {
		return direct;
	}

	public synchronized void setDirect(int direct) {
		this.direct = direct;
	}

	public synchronized boolean getAlive() {
		return alive;
	}

	public synchronized void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Image getEnemy_img() {
		return new ImageIcon(enemy_img_string + enemy_img_state + ".png").getImage();
	}

	public Image getEnemy_weapon() {
		return new ImageIcon(enemy_weapon_string + enemy_weapon_state + ".png").getImage();
	}

	public Image getWeapon_special() {
		return new ImageIcon(weapon_special_string + weapon_special_state + ".png").getImage();
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
		this.blood = attribute.getHP();
		this.full_blood = this.blood;
		this.hurt = attribute.getPhy_attack();
		this.speed = attribute.getSpeed_action();
	}

	public StateController getStateController() {
		return stateController;
	}

	public void setStateController(StateController stateController) {
		this.stateController = stateController;
	}

	public synchronized Thread getThread() {
		return thread;
	}

	public synchronized void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isWeapon_state() {
		return weapon_state;
	}

	public void setWeapon_state(boolean weapon_state) {
		this.weapon_state = weapon_state;
		weapon_special_state = 1;
	}

	// synchronized
	public void people_attack() {

//		this.weapon_state = true;
		hurtController.hurtOwn(x, y, scope, hurt, this, 1);
	}

	@Override
	public void run() {

		int thread_num = 1;
		boolean TF = true;
		int sleepTime = 100;
		try {
			Thread.sleep(new Random().nextInt(sleepTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (TF) {
			thread_num++;
			if (thread_num == 9)
				thread_num = 1;
			if (alive) {
				try {
					Thread.sleep(sleepTime);
				} catch (Exception e) {
				}

				if (thread_num % 1 == 0) {

					if (this.weapon_state) {
						this.enemy_weapon_state++;
						if (this.enemy_weapon_state == 5) {
							this.enemy_weapon_state = 1;
							this.weapon_state = false;
						}
					}
					if (weapon_special_state != 0) {
						this.weapon_special_state++;
						if (this.weapon_special_state == 6) {
							this.weapon_special_state = 0;
						}
					}
					if (state == -1) {
						if (enemy_img_state++ > 6) {
							enemy_img_state = -1;
							enemy_img_string = "resouce/image/monsters/monsters" + imgId + "/monster1/monster";
							enemy_img_state = 1;
							state = 1;
						}
					}
					if (behurt_time >= 1)
						behurt_time--;
					else
						behurt = 0;
				}

				if (thread_num % 4 == 0) {
					if (state == 1 && !stateController.Frozen()) {
						this.enemy_img_state++;
						if (this.enemy_img_state == 5)
							this.enemy_img_state = 1;
					}
				}

			} else {
				try {
					Thread.sleep(50);
					if (state == 1) {
						enemy_img_string = "resouce/image/specials/booms/boom1/boom";
						enemy_img_state = 1;
						state = -1;
					}
					if (enemy_img_state++ > 7) {
						state = -2;
						this.getThread().interrupt();
					}
				} catch (Exception e) {
					TF = false;
					break;
				}
			}
		}
	}
}
