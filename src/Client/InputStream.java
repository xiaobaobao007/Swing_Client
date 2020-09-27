package Client;

import java.net.SocketException;

import ChooseChapter.ChooseLeave;
import ChooseGamer.ChooseStart;
import Controller.GameController;
import Controller.Shop;
import Enity.EnemyPeople;
import Enity.Goods;
import Enity.Message;
import GameStart.GameStart;
import Panel.MesPanel;

public class InputStream implements Runnable {

	public static String info;
	public static boolean TF = true;
	boolean start = false;// JFrame

	public InputStream() {
		super();
	}

	@Override
	public void run() {

		String[] a;
		String[] b;
		try {
			while (true) {
				if ((info = ClientStart.dis.readUTF().trim()) != null) {
					b = info.split(";");
					a = b[0].split(":");
					// 怪物操作:00
					if (a[1].compareTo("0100") < 0) {
						if (start && a[1].equals("0000")) {// 怪物攻击
							if (GameController.Enemys.size() > 0) {
								GameController.Enemys.get(Integer.valueOf(a[0])).people_attack();
							}
						} else if (start && a[1].equals("0001")) {// 怪物移动
							if (GameController.Enemys.size() > 0) {
								if (a[2].equals("0")) {
									GameController.Enemys.get(Integer.valueOf(a[0])).setX(Integer.valueOf(a[3]));
								} else {
									GameController.Enemys.get(Integer.valueOf(a[0])).setY(Integer.valueOf(a[3]));
								}
							}
						} else if (a[1].equals("0002")) {
						}
					}
					// 键盘操作:01
					else if (a[1].compareTo("0200") < 0) {
						if (a[1].equals("0101")) {// 其他用户的操作
							GameController.Change_Teams(Integer.valueOf(a[0]), Integer.valueOf(a[2]),
									Integer.valueOf(a[3]));
						} else if (a[1].equals("0102")) {// 其他用户退出
							GameController.Change_Teams(Integer.valueOf(a[0]));
						}
					}
					// 个人数据:02
					else if (a[1].compareTo("0300") < 0) {
						if (a[1].equals("0201")) {// 接受服务器的个人数据
							System.out.print("开始加载个人信息：");
							String[] c = b[1].split(":");
							int[] q = new int[23];
							for (int i = 0; i <= 22; i++) {
								if (i != 4)
									q[i] = Integer.valueOf(c[i]);
							}
							GameController.start_own(q, c[4]);
							System.out.println("成功");
						} else if (a[1].equals("0202")) {// 接受新添加的队友
							System.out.print("开始加载队友数据：");
							System.out.println(info);
							int nums = Integer.valueOf(a[0]);
							int[][] Ene = new int[nums][22];
							String[] name = new String[20];
							for (int i = 0; i < nums; i++) {
								String[] c = b[i + 1].split(":");
								for (int j = 0; j < 7; j++) {
									if (j != 6) {
										Ene[i][j] = Integer.valueOf(c[j]);
									}
								}
								name[i] = c[6];
							}
							GameController.start_Teams(nums, Ene, name);
							System.out.println("添加成功，添加了" + nums + "名队友。");
						} else if (a[1].equals("0203")) {// 聊天内容

						} else if (a[1].equals("0204")) {// 个人物品信息
							Thread.sleep(1000);
							System.out.print("开始加载商品信息：");
							if (Shop.goods.size() == 0) {
								System.out.println("添加失败，重新请求信息。");
								ClientStart.OutStreamAll(GameController.own_cilent_id + ":0501");// 加载商店信息
							} else {
								GameController.own_goods(a[2]);
								System.out.println("添加成功，添加了" + Shop.goods.size() + "商品。");
							}
						} else if (a[1].equals("0205")) {//
//							GameController.own_cilent_people_index = a[0];
						} else if (a[1].equals("0206")) {// 接受服务器加载个人请求
						} else if (a[1].equals("0207")) {// 其他玩家准备状态
							ChooseLeave.OtherPeople(Integer.valueOf(a[0]), Integer.valueOf(a[2]), a[3], a[4]);
						} else if (a[1].equals("0208")) {// 个人坐标初始化
							GameController.own.setX(Integer.valueOf(a[2]));
							GameController.own.setY(Integer.valueOf(a[3]));
						} else if (a[1].equals("0209")) {// 其他玩家坐标进行初始化
							GameController.Change_Teams(Integer.valueOf(a[0]), 0, Integer.valueOf(a[2]));
							GameController.Change_Teams(Integer.valueOf(a[0]), 1, Integer.valueOf(a[3]));
						}
					}
					// 怪物数据:03
					else if (a[1].compareTo("0400") < 0) {
						if (a[1].equals("0301")) {// 怪物掉落装备
							GameController.add_map_goods(Integer.valueOf(a[2]), Integer.valueOf(a[3]), a[4]);
						} else if (a[1].equals("0302")) {
//							GameController.deleteOneEnemy(Integer.valueOf(a[0]));
						}
					}
					// 游戏数据:04
					else if (a[1].compareTo("0500") < 0) {
						if (a[1].equals("0401")) {// 添加怪物数据
							System.out.print("开始加载怪物数据：");
							int leave = Integer.valueOf(a[0]);
							int[][] Ene = new int[leave][23];
							String[] name = new String[21];
							for (int i = 0; i < leave; i++) {
								String[] c = b[i + 1].split(":");
								for (int j = 0; j < 23; j++) {
									if (j != 4)
										Ene[i][j] = Integer.valueOf(c[j]);
								}
								name[i] = c[4];
							}
							GameController.start_enemy(leave, Ene, name);
							System.out.println("添加成功，添加了个" + leave + "怪物。");
						} else if (a[1].equals("0402")) {// 总游戏开始
							new GameStart();
							GameStart.game_thread.start();
							start = true;
						} else if (a[1].equals("0403")) {// 聊天室内容
							MesPanel.messages.add(new Message(Integer.valueOf(a[0]), a[2]));
						} else if (a[1].equals("0404")) {// 允许开始返回关卡界面
							GameStart.game_thread.interrupt();
							for (EnemyPeople e : GameController.Enemys) {
								e.getThread().interrupt();
							}
							new ChooseStart();
						} else if (a[1].equals("0405")) {// 关卡数据：提示
							int size = Integer.valueOf(a[0]);
							for (int i = 0; i < size; i++) {
								String[] c = b[i + 1].split(":");
								GameStart.Infos[i] = c[1];
								GameController.Lastnums[i] = c[2].charAt(0) - '0';
							}
						} else if (a[1].equals("0406")) {// 开始游戏按钮
							ChooseLeave.playGame();
						}
					}
					// 商城物品:05
					else if (a[1].compareTo("0600") < 0) {
						if (a[1].equals("0501")) {// 加载商城数据
							int size = Integer.valueOf(a[0]);
							for (int i = 0; i < size; i++) {
								String[] c = b[i + 1].split(":");
								GameController.add_shop_goods(new Goods(c[0], Integer.valueOf(c[1]),
										Integer.valueOf(c[2]), Integer.valueOf(c[3]), -1, -1, Integer.valueOf(c[4]),
										c[5], Integer.valueOf(c[6]), Integer.valueOf(c[7]), Integer.valueOf(c[8]),
										Integer.valueOf(c[9]), Integer.valueOf(c[10]), Integer.valueOf(c[11]),
										Integer.valueOf(c[12]), Integer.valueOf(c[13]), Integer.valueOf(c[14])));
							}

						}
					}
				}

			}
		} catch (SocketException e) {
			System.out.println("连接中断");
		} catch (Exception e1) {
			System.out.println("请及时修改错误代码");
			e1.printStackTrace();
		} finally {
			System.err.println(info);
		}
	}
}
