package Client;

import ChooseChapter.ChooseLeave;
import ChooseGamer.ChooseStart;
import Controller.GameController;
import Controller.Shop;
import Enity.EnemyPeople;
import Enity.Goods;
import Enity.Message;
import GameStart.GameStart;
import Panel.MesPanel;

import java.net.SocketException;

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
					// �������:00
					if (a[1].compareTo("0100") < 0) {
						if (start && a[1].equals("0000")) {// ���﹥��
							if (GameController.Enemys.size() > 0) {
								GameController.Enemys.get(Integer.valueOf(a[0])).people_attack();
							}
						} else if (start && a[1].equals("0001")) {// �����ƶ�
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
					// ���̲���:01
					else if (a[1].compareTo("0200") < 0) {
						if (a[1].equals("0101")) {// �����û��Ĳ���
							GameController.Change_Teams(Integer.valueOf(a[0]), Integer.valueOf(a[2]),
									Integer.valueOf(a[3]));
						} else if (a[1].equals("0102")) {// �����û��˳�
							GameController.Change_Teams(Integer.valueOf(a[0]));
						}
					}
					// ��������:02
					else if (a[1].compareTo("0300") < 0) {
						if (a[1].equals("0201")) {// ���ܷ������ĸ�������
							System.out.print("��ʼ���ظ�����Ϣ��");
							String[] c = b[1].split(":");
							int[] q = new int[23];
							for (int i = 0; i <= 22; i++) {
								if (i != 4)
									q[i] = Integer.valueOf(c[i]);
							}
							GameController.start_own(q, c[4]);
							System.out.println("�ɹ�");
						} else if (a[1].equals("0202")) {// ��������ӵĶ���
							System.out.print("��ʼ���ض������ݣ�");
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
							System.out.println("��ӳɹ��������" + nums + "�����ѡ�");
						} else if (a[1].equals("0203")) {// ��������

						} else if (a[1].equals("0204")) {// ������Ʒ��Ϣ
							Thread.sleep(1000);
							System.out.print("��ʼ������Ʒ��Ϣ��");
							if (Shop.goods.size() == 0) {
								System.out.println("���ʧ�ܣ�����������Ϣ��");
								ClientStart.OutStreamAll(GameController.own_cilent_id + ":0501");// �����̵���Ϣ
							} else {
								GameController.own_goods(a[2]);
								System.out.println("��ӳɹ��������" + Shop.goods.size() + "��Ʒ��");
							}
						} else if (a[1].equals("0205")) {//
//							GameController.own_cilent_people_index = a[0];
						} else if (a[1].equals("0206")) {// ���ܷ��������ظ�������
						} else if (a[1].equals("0207")) {// �������׼��״̬
							ChooseLeave.OtherPeople(Integer.valueOf(a[0]), Integer.valueOf(a[2]), a[3], a[4]);
						} else if (a[1].equals("0208")) {// ���������ʼ��
							GameController.own.setX(Integer.valueOf(a[2]));
							GameController.own.setY(Integer.valueOf(a[3]));
						} else if (a[1].equals("0209")) {// �������������г�ʼ��
							GameController.Change_Teams(Integer.valueOf(a[0]), 0, Integer.valueOf(a[2]));
							GameController.Change_Teams(Integer.valueOf(a[0]), 1, Integer.valueOf(a[3]));
						}
					}
					// ��������:03
					else if (a[1].compareTo("0400") < 0) {
						if (a[1].equals("0301")) {// �������װ��
							GameController.add_map_goods(Integer.valueOf(a[2]), Integer.valueOf(a[3]), a[4]);
						} else if (a[1].equals("0302")) {
//							GameController.deleteOneEnemy(Integer.valueOf(a[0]));
						}
					}
					// ��Ϸ����:04
					else if (a[1].compareTo("0500") < 0) {
						if (a[1].equals("0401")) {// ��ӹ�������
							System.out.print("��ʼ���ع������ݣ�");
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
							System.out.println("��ӳɹ�������˸�" + leave + "���");
						} else if (a[1].equals("0402")) {// ����Ϸ��ʼ
							new GameStart();
							GameStart.game_thread.start();
							start = true;
						} else if (a[1].equals("0403")) {// ����������
							MesPanel.messages.add(new Message(Integer.valueOf(a[0]), a[2]));
						} else if (a[1].equals("0404")) {// ����ʼ���عؿ�����
							GameStart.game_thread.interrupt();
							for (EnemyPeople e : GameController.Enemys) {
								e.getThread().interrupt();
							}
							new ChooseStart();
						} else if (a[1].equals("0405")) {// �ؿ����ݣ���ʾ
							int size = Integer.valueOf(a[0]);
							for (int i = 0; i < size; i++) {
								String[] c = b[i + 1].split(":");
								GameStart.Infos[i] = c[1];
								GameController.Lastnums[i] = c[2].charAt(0) - '0';
							}
						} else if (a[1].equals("0406")) {// ��ʼ��Ϸ��ť
							ChooseLeave.playGame();
						}
					}
					// �̳���Ʒ:05
					else if (a[1].compareTo("0600") < 0) {
						if (a[1].equals("0501")) {// �����̳�����
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
			System.out.println("�����ж�");
		} catch (Exception e1) {
			System.out.println("�뼰ʱ�޸Ĵ������");
			e1.printStackTrace();
		} finally {
			System.err.println(info);
		}
	}
}
