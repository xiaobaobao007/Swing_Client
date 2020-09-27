package Controller;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import ChooseChapter.ChooseLeave;
import ChooseGamer.ChooseStart;
import Enity.*;
import Panel.MesPanel;

public class GameController extends JPanel implements Runnable {

	public static final int panel_width = 1264;
	public static final int panel_height = 895;
	/**
	 * synchronized
	 */
	private static final long serialVersionUID = 1L;
	public static int own_cilent_id = -1;
	public static int info_x = 30;
	public static int info_y = 30;
	public static int info_width = 300;
	public static int info_height = 150;
	public static int exit_x = 1150;
	public static int exit_y = 250;
	public static int exit_width = 50;
	public static int exit_height = 50;
	public static int leave;
	public static HurtController hurtController;
	public static Vector<EnemyPeople> Enemys = new Vector<>();
	public static Vector<Goods> map_goods = new Vector<>();
	public static Vector<OwnPeople> Owns = new Vector<>();
	public static OwnPeople own = new OwnPeople();
	public static int[] Lastnums = new int[10];
	static int input_x = 39;
	static int input_y = 550;
	static int input_width = 255;
	static int input_height = 30;
	public static int minleave;
	private static final Vector<TeamPeople> Teammates = new Vector<>();
	private static MesPanel mesPanel;
	private static int online_people = 0;
	private static final int[][] goods_id = new int[30][30];
	private static final int[] online = {0, 0, 0, 0, 0, 0, 0, 0};
	private static int nums = -1;
	// public HashMap<EnemyPeople, Thread> Thread_one;
//    public GameMap map = new GameMap();
	private int pack_x = 1060;
	private int pack_y = 740;
	private int pack_width = 70;
	private int pack_height = 70;
	private int shop_x = 900;
	private int shop_y = 740;
	private int shop_width = 80;
	private int shop_height = 80;
	private int poor_x;
	private int poor_y;
	private Image shop;// 商店图标
	private Image backpack;// 背包图标
	private Image map_background;// 小地图背景
	private Image information;// 个人信息按钮
	private Image exit;// 退出按钮
	private Image InputBox;// 聊天框

	public GameController() {
		GameJFrame.GameJFrame.setFocusable(true);
		this.setFocusable(true);
		// super.addMouseListener(this);
		hurtController = new HurtController();
		mesPanel = new MesPanel();
		try {
			shop = new ImageIcon("resouce/image/others/shopping_icon.png").getImage();
			backpack = new ImageIcon("resouce/image/others/backpack_icon.png").getImage();
			InputBox = new ImageIcon("resouce/image/others/mesinputbox1.png").getImage();
			map_background = new ImageIcon("resouce/image/map/map_ground.png").getImage();
			information = new ImageIcon("resouce/image/others/information.png").getImage();
			exit = new ImageIcon("resouce/image/others/exit.png").getImage();
		} catch (Exception e) {
			System.out.println("GameController.GameController");
		}
	}

	public static void start_own(int[] q, String name) {
		ChooseLeave.leaves = Integer.valueOf(name.split(",")[1]);
		name = name.split(",")[0];
		ChooseLeave.info = name + "已准备就绪";
		OwnPeople o = new OwnPeople(q[0], q[1], q[2], q[3], name, q[5], new Attribute(q[6], q[7], q[8], q[9], q[10],
				q[11], q[12], q[13], q[14], q[15], q[16], q[17], q[18], q[19], q[20], q[21], q[22]));
		ChooseLeave.restPeople(o.getPeople(), name);
		Owns.addElement(o);
		Thread own_thread = new Thread(o);
		own_thread.start();
		new ChooseStart();
	}

	public static void choseOwn(int index) {
		own = Owns.get(index);
	}

	public static void own_goods(String info) {
		int size = info.length() / 6;
		for (int i = 0; i < size; i++) {
			int type = Integer.parseInt(info.substring(i * 6, i * 6 + 2));
			int id = Integer.parseInt(info.substring(i * 6 + 2, i * 6 + 4));
			int nums = Integer.parseInt(info.substring(i * 6 + 4, i * 6 + 6));
			Goods g = Shop.goods.get(goods_id[type][id]);
			if (nums == 0) {
				OwnPeople.Add_Equip(g);
			} else {
				g.setNums(nums);
				OwnPeople.own_goods.add(g);
			}
		}
		int blood = own.getAttribute().getHP();
		own.setBlood(blood);
		own.setFull_blood(blood);
		int magic = own.getAttribute().getMagic();
		own.setMagic(magic);
		own.setFull_magic(magic);
	}

	public static void start_enemy(int e_nums, int[][] Ene, String[] name) {
		GameController.minleave += 1;
		int leftleave = Lastnums[leave - 1] - minleave;
		if (leftleave == 0) {
			MesPanel.messages.add(new Message(-1, "这是最后一关，请退出进行下一关哦"));
			GameController.minleave = 0;
			if (ChooseLeave.leaves == leave) {
				ChooseLeave.leaves++;
			}
		} else
			MesPanel.messages.add(new Message(-1, "开始第" + minleave + "小关，剩余" + leftleave + "小关"));
		Enemys = new Vector<>();
		for (int i = 0; i < e_nums; i++) {
			EnemyPeople Enemy = new EnemyPeople(Ene[i][0], Ene[i][1], Ene[i][2], Ene[i][3], name[i], Ene[i][5],
					new Attribute(Ene[i][6], Ene[i][7], Ene[i][8], Ene[i][9], Ene[i][10], Ene[i][11], Ene[i][12],
							Ene[i][13], Ene[i][14], Ene[i][15], Ene[i][16], Ene[i][17], Ene[i][18], Ene[i][19],
							Ene[i][20], Ene[i][21], Ene[i][22]));
			Enemys.add(Enemy);
			Enemy.setController(hurtController);
			Thread Enemy_thread = new Thread(Enemy);
			Enemy.setThread(Enemy_thread);
			Enemy_thread.start();
		}
	}

	public static void start_Teams(int nums, int[][] Ten, String[] name) {
		for (int i = 0; i < nums; i++) {
			String n = name[i].split(",")[0];
			TeamPeople Teams = new TeamPeople(Ten[i][0], Ten[i][1], Ten[i][2], Ten[i][3], Ten[i][4], Ten[i][5], n);
			ChooseLeave.restPeople(Teams.getPeople(), n);
			Thread Teams_thread = new Thread(Teams);
			Teams.setThread(Teams_thread);
			Teams_thread.start();
			Teammates.add(Teams);
			online[Ten[i][4]] = online_people++;
		}
		ChooseLeave.reFreshPanel();
	}

	public synchronized static void Change_Teams(int id) {
		Teammates.remove(online[id]);
		online[id] = 0;
		online_people--;
		for (int i = 0; i < 8; i++) {
			if (online[i] > id)
				online[i]--;
		}
		ChooseLeave.removePeople(id);
	}

	public synchronized static void Change_Teams(int id, int operate, int info) {
//        System.out.println("id = [" + id + "], operate = [" + operate + "], info = [" + info + "]");
		TeamPeople team = Teammates.get(online[id]);
		switch (operate) {
			case 0:
				team.setX(info);
				break;
			case 1:
				team.setY(info);
				break;
			case 2:
				team.people_attack();
				break;
			case 3:
				team.Magic_attack();
				break;
			case 4:
				team.pullGoods();
				break;
			default:
				break;
		}
		if (operate == 0) {
			team.setX(info);
		} else if (operate == 1) {
			team.setY(info);
		}
	}

	public static void add_map_goods(int x, int y, String a) {
		int type = Integer.parseInt(a.substring(0, 2));
		int id = Integer.parseInt(a.substring(2, 4));
		Goods good = Shop.goods.get(goods_id[type][id]);
		good.setX(x);
		good.setY(y);
		map_goods.add(good);
	}

	static void restNumbers() {
		input_x = 30;
		input_y = 550;
		input_width = 260;
		input_height = 35;
		info_x = 30;
		info_y = 30;
		info_width = 300;
		info_height = 150;
		exit_x = 1150;
		exit_y = 250;
		exit_width = 50;
		exit_height = 50;
	}

	public static void add_shop_goods(Goods goods) {
		int type = goods.getType();
		int id = goods.getId();
		goods_id[type][id] = ++nums;
		Shop.goods.add(goods);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			int poor_own_x = own.getPaint_x();
			poor_x = poor_own_x;
			if (poor_x <= 600) {
				poor_x = 0;
			} else if (poor_x <= 1800) {
				poor_own_x = 600;
				poor_x = 600 - poor_x;
			} else {
				poor_own_x = poor_own_x - 1200;
				poor_x = -1200;
			}

			int poor_own_y = own.getPaint_y();
			poor_y = poor_own_y;
			if (poor_y <= 400) {
				poor_y = 0;
			} else if (poor_y <= 1200) {
				poor_own_y = 400;
				poor_y = 400 - poor_y;
			} else {
				poor_own_y = poor_own_y - 800;
				poor_y = -800;
			}
//
//			poor_own_x = own.getX();
//			poor_x = poor_own_x;
//			if (poor_x <= 30) {
//				poor_own_x *= 20;
//				poor_x = 0;
//			} else if (poor_x <= 90) {
//				poor_own_x = 600;
//				poor_x = 600 - poor_x * 20;
//			} else {
//				poor_own_x = (poor_own_x - 60) * 20;
//				poor_x = -1200;
//			}
//
//			poor_own_y = own.getY();
//			poor_y = poor_own_y;
//			if (poor_y <= 20) {
//				poor_own_y *= 20;
//				poor_y = 0;
//			} else if (poor_y <= 60) {
//				poor_own_y = 400;
//				poor_y = 400 - poor_y * 20;
//			} else {
//				poor_own_y = (poor_own_y - 40) * 20;
//				poor_y = -800;
//			}

			DrawBackGround(g);// 背景图

			DrawGoods(g, map_goods, this);// 物品掉落

			DrawEnemys(g, Enemys, this);// 怪物

			if (Teammates != null && Teammates.size() > 0)
				DrawTeams(g, Teammates, this);// 队友

			if (own.getAlive())
				own.Draw(g, poor_own_x, poor_own_y, this);// 自己

			Skills.Draw(g, poor_x, poor_y, this);

			mesPanel.Draw(g, this);// 聊天信息

			g.drawImage(InputBox, input_x, input_y, input_width, input_height, this);// 输入框

			g.drawImage(shop, shop_x, shop_y, shop_width, shop_height, this);// 商店

			g.drawImage(backpack, pack_x, pack_y, pack_width, pack_height, this);// 背包

			int smap_height = 180;
			int smap_width = 260;
			int smap_y = 20;
			int smap_x = 937;
			g.drawImage(map_background, smap_x, smap_y, smap_width, smap_height, this);// 小地图

			g.drawImage(information, info_x, info_y, info_width, info_height, this);// 个人信息

			g.drawImage(exit, exit_x, exit_y, exit_width, exit_height, this);// 退出
			// System.out.println(System.currentTimeMillis()-a);
			// g.setColor(Color.RED);
			// g.setFont(new Font("宋体", Font.BOLD, 30));
			// a=1000/(System.currentTimeMillis()-a);
			// g.drawString(""+a,10,50);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("paint error");
		}
	}

	private void DrawBackGround(Graphics g) {

		Image background = new ImageIcon("resouce/image/map/map" + leave + ".jpg").getImage();
		g.drawImage(background, poor_x, poor_y, 1200 * 2 + 80, 800 * 2 + 85, this);

	}

	private void DrawGoods(Graphics g, List<Goods> goods, ImageObserver o) {

		for (Goods a : goods) {
			g.drawImage(a.getImage(), a.getX() * 20 + poor_x, a.getY() * 20 + poor_y, 50, 50, o);
		}
	}

	private void DrawEnemys(Graphics g, Vector<EnemyPeople> p, ImageObserver o) {
		try {
			for (EnemyPeople one : p) {
				one.Draw(g, poor_x, poor_y, o);
			}
		} catch (Exception e) {
			System.out.println("GameController.DrawEnemys");
		}
	}

	private void DrawTeams(Graphics g, Vector<TeamPeople> p, ImageObserver o) {
		try {
			for (TeamPeople one : p) {
				one.Draw(g, poor_x, poor_y, o);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("GameController.DrawTeams");
		}
	}

	@Override
	public void run() {
//		int i = 0;
//		int nums = 0;
		while (true) {
			try {
//				a = System.currentTimeMillis();
				Thread.sleep(50);
//				if(Error_time==0) {
//					i++;
//				}
//				if(i==4) {
//					i=0;
//					Error_time=-1;
//				}
//				i++;
//				boolean qq = true;
//				if (i > 60) {
//					i = 0;// 每3秒
//					nums++;
////					System.out.println(AliveEnemy);
//					qq = true;
//					for (EnemyPeople e : Enemys) {
//						if (e.getAlive() == true) {
//							qq = false;
//						}
//					}
//				}

//				if (leave >= ChooseLeave.leaves)
//					ClientStart.OutStreamAll(own_cilent_id + ":0604:" + (leave + 1));
//				ChooseLeave.leaves++;
//				MesPanel.messages.add(new Message(1, "系统", "已是最后一小关"));

			} catch (Exception e) {
				System.out.println("游戏主流程关闭");
			}
			this.repaint();
		}
	}

	void setPack_x(int pack_x, int q) {
		if (q == 1) {
			this.pack_x += pack_x;
		} else {
			this.pack_x -= pack_x;
		}
	}

	void setPack_y(int pack_y, int q) {
		if (q == 1) {
			this.pack_y += pack_y;
		} else {
			this.pack_y -= pack_y;
		}
	}

	void setPack_width(int pack_width, int q) {
		if (q == 1) {
			this.pack_width += pack_width;
		} else {
			this.pack_width -= pack_width;
		}
	}

	void setPack_height(int pack_height, int q) {
		if (q == 1) {
			this.pack_height += pack_height;
		} else {
			this.pack_height -= pack_height;
		}
	}

	void setShop_x(int shop_x, int q) {
		if (q == 1) {
			this.shop_x += shop_x;
		} else {
			this.shop_x -= shop_x;
		}
	}

	void setShop_y(int shop_y, int q) {
		if (q == 1) {
			this.shop_y += shop_y;
		} else {
			this.shop_y -= shop_y;
		}
	}

	void setShop_width(int shop_width, int q) {
		if (q == 1) {
			this.shop_width += shop_width;
		} else {
			this.shop_width -= shop_width;
		}
	}

	void setShop_height(int shop_height, int q) {
		if (q == 1) {
			this.shop_height += shop_height;
		} else {
			this.shop_height -= shop_height;
		}
	}

	void AddExit(int size) {
		exit_x -= size;
		exit_y -= size;
		exit_height += size * 2;
		exit_width += size * 2;
	}

	void SubExit(int size) {
		exit_x += size;
		exit_y += size;
		exit_height -= size * 2;
		exit_width -= size * 2;
	}

	void setInputBox(Image inputBox) {
		InputBox = inputBox;
	}
}
