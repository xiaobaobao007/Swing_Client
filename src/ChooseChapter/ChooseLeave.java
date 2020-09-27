package ChooseChapter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import Client.ClientStart;
import Controller.GameController;
import Controller.GameJFrame;
import GameStart.GameStart;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class ChooseLeave extends JPanel implements MouseWheelListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static int leaves = 0;
	public static String info;
	private static JPanel jPanel;
	private static final Map<Integer, String> id_Name = new HashMap<>();
	private static final Map<Integer, Integer> id_State = new HashMap<>();
	private static JButton playGameButton;
	private static List<JButton> buttons;
	private static int nowLeave = -1;
	private static JButton ready;
	private int first_button = 0;
	private int last_button = 0;

	ChooseLeave(int x, int y) {
		this.setLayout(null);
		int height = 600;
		int width = 800;
		this.setBounds(x, y, width, height);
		this.addMouseWheelListener(this);
		buttons = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			JButton jButton = new JButton("<html>第" + i + "关<br></html>");
			jButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) throws StringIndexOutOfBoundsException {
					int leave = Integer.valueOf(jButton.getText().substring(7, 8));
//                        System.out.println(nowLeave+"|"+leave);
					if (nowLeave == -1) {
						nowLeave = leave;
						GameController.leave = nowLeave;
						ClientStart.OutStreamAll(GameController.own_cilent_id + ":0207:" + leave + ":+:" + info);
						AddButtonInfo(nowLeave, info);
						readyButton();
					} else if (nowLeave == leave) {
						removeReady();
					} else {
						ClientStart.OutStreamAll(GameController.own_cilent_id + ":0207:" + nowLeave + ":-:" + info);
						SubButtonInfo(nowLeave, info);
						ClientStart.OutStreamAll(GameController.own_cilent_id + ":0207:" + leave + ":+:" + info);
						AddButtonInfo(leave, info);
						nowLeave = leave;
						GameController.leave = nowLeave;
						readyButton();
					}
				}
			});
			if (i > leaves) {
				jButton.setEnabled(false);
				jButton.setText("未达到通关条件");
				jButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
			} else {
				jButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
			}
			jButton.setSize(165, 60);
			if (i == 1)
				first_button = 100 * i;
			else
				last_button = 100 * i;
			if (i % 2 == 0)
				jButton.setLocation(50, 100 * i);
			else
				jButton.setLocation(300, 100 * i);
			buttons.add(jButton);
			this.add(jButton);
		}
		JButton back = new JButton("返回上一层");
		back.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (ready != null) {
					id_State.put(GameController.own_cilent_id, 0);
					jPanel.remove(ready);
					ready = null;
				}
				ChooseChapter.ToMain();
				nowLeave = -1;
			}
		});
		back.setBounds(650, 50, 120, 60);
		this.add(back);
		this.setBackground(new Color(167, 83, 90));
		jPanel = this;
	}

	private static void setPlayGameButton() {
		int ready = -1;
		for (Integer i : id_State.keySet()) {
			if (ready == -1) ready = id_State.get(i);
			else if (ready != id_State.get(i)) {
				ready = -1;
				break;
			}
		}
		if (ready > 0) {
			playGameButton = new JButton("开始游戏");
			playGameButton.addActionListener((A) -> {
				playGame();
				ClientStart.OutStreamAll(GameController.own_cilent_id + ":0404");
			});
			playGameButton.setBounds(500, 50, 120, 60);
			playGameButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
			jPanel.add(playGameButton);
		} else {
			if (playGameButton != null)
				jPanel.remove(playGameButton);
		}
		jPanel.repaint();
	}

	public static void playGame() {
		new GameStart();
		removeReady();
	}

//    public static void playGameButton

	public static void removePeople(int id) {
		String name = id_Name.get(id);
		int level = id_State.get(id);
		id_State.remove(id);
		id_Name.remove(id);
		if (level > 0) SubButtonInfo(level, name);
		jPanel.repaint();
	}

	public static void reFreshPanel() {
		if (jPanel != null) {
			jPanel.repaint();
		}
	}

	public static void restPeople(int id, String name) {
		id_Name.put(id, name);
		id_State.put(id, 0);
	}

//    public static void removePeople(int id) {
//        id_Name.remove(id);
//        id_State.remove(id);
//    }

	public static String inToname(int id) {
		return id_Name.get(id);
	}

	public static void changeState(int id, int state) {
		id_State.put(id, state);
		setPlayGameButton();
	}

	private static void removeReady() {
		ClientStart.OutStreamAll(GameController.own_cilent_id + ":0207:" + nowLeave + ":-:" + info);
		SubButtonInfo(nowLeave, info);
		nowLeave = -1;
		changeState(GameController.own_cilent_id, 0);
		jPanel.remove(ready);
		ready = null;
		jPanel.repaint();
	}

	private static void AddButtonInfo(int buttonId, String name) {
		if (buttons != null) {
			JButton jButton = buttons.get(buttonId - 1);
			String a = jButton.getText();
			Dimension xy = jButton.getSize();
			int x = xy.width;
			int y = xy.height + 20;
			int size = a.length();
			a = a.substring(0, size - 7) + "<br>" + name + "</html>";
			jButton.setText(a);
			jButton.setSize(x, y);
		}
	}

	private static void SubButtonInfo(int buttonId, String name) {
		if (buttons != null) {
			JButton jButton = buttons.get(buttonId - 1);
			String a = jButton.getText();
			Dimension xy = jButton.getSize();
			int x = xy.width;
			int y = xy.height - 20;
			a = a.replaceAll("<br>" + name, "");
			jButton.setText(a);
			jButton.setSize(x, y);
			GameJFrame.repaintJFrame();
		}
	}

	public static void OtherPeople(int id, int buttonId, String operation, String name) {
		if (operation.equals("+")) {
			id_State.put(id, buttonId);
			AddButtonInfo(buttonId, name);
		} else if (operation.equals("-")) {
			id_State.put(id, 0);
			SubButtonInfo(buttonId, name);
		}
		reFreshPanel();
		setPlayGameButton();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (id_State.size() > 0) {
			g.setColor(new Color(102, 132, 176));
			int h1 = 350;
			int w1 = 280;
			int y1 = 170;
			int x1 = 480;
			g.fill3DRect(x1, y1, w1, h1, true);
			g.setColor(Color.red);
			g.setFont(new Font("微软雅黑", Font.PLAIN, 22));
			g.drawString("当前所有玩家", x1 + 57, y1 + 40);
			g.setColor(Color.blue);
			g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			g.drawString("ID", x1 + 40, y1 + 85);
			g.drawString("姓名", x1 + 80, y1 + 85);
			g.drawString("准备状态", x1 + 160, y1 + 85);
			int j = 1;
			for (Integer i : id_Name.keySet()) {
				g.setColor(Color.white);
				g.drawString(i + "", x1 + 40, y1 + 90 + 25 * j);
				if (GameController.own_cilent_id == i) {
					g.setColor(Color.MAGENTA);
				}
				g.drawString(id_Name.get(i), x1 + 80, y1 + 90 + 25 * j);
				if (id_State.get(i) == 0) {
					g.setColor(Color.red);
					g.drawString("未准备", x1 + 160, y1 + 90 + 25 * j);
				} else {
					g.setColor(Color.green);
					g.drawString("已准备", x1 + 160, y1 + 90 + 25 * j);
				}
				j++;
			}
		}
	}

	private void readyButton() {
		if (ready == null) {
			changeState(GameController.own_cilent_id, nowLeave);
			ready = new JButton("取消准备");
			ready.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
			ready.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ready != null) {
						removeReady();
					}
				}
			});
			this.add(ready);
		}
		int y = buttons.get(nowLeave - 1).getY();
		if (nowLeave % 2 == 0) {
			ready.setBounds(270, y, 100, 60);
		} else {
			ready.setBounds(160, y, 100, 60);
		}
		this.repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int nums = 6;
		int step = 4;
		if (e.getWheelRotation() == 1) {
			if (last_button > 908) {
				boolean qwe = true;
				for (JButton jButton : buttons) {
					for (int i = 0; i < nums; i++) {
						Point point = jButton.getLocation();
						point.translate(0, -step);
						jButton.setLocation(point);
						if (ready != null && qwe) {
							point = ready.getLocation();
							point.translate(0, -step);
							ready.setLocation(point);
						}
					}
					qwe = false;
				}
				first_button -= step;
				last_button -= step;
			}
		} else if (e.getWheelRotation() == -1) {
			if (first_button < 101) {
				boolean qwe = true;
				for (JButton jButton : buttons) {
					for (int i = 0; i < nums; i++) {
						Point point = jButton.getLocation();
						point.translate(0, step);
						jButton.setLocation(point);
						if (ready != null && qwe) {
							point = ready.getLocation();
							point.translate(0, step);
							ready.setLocation(point);
						}
					}
					qwe = false;
				}
				first_button += step;
				last_button += step;
			}
		}
	}
}
