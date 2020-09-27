package Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import Controller.GameController;
import Controller.Shop;
import Enity.Goods;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

@SuppressWarnings("serial")
public class ShopPanel extends JPanel {

	public static int x1 = 180;
	public static int y1 = 0;
	public static int button = 1;
	public static int button_type;
	Image background;
	int x0 = 10;
	int y0 = 50;
	int z0 = 80;
	int x2 = 100;
	int y2 = 10;
	int z2 = 50;
	private final int width = Shop.width;
	private final int height = Shop.height;

	public ShopPanel(int index) {
		rest(index);
	}

	public ShopPanel(int index, ShopPanel shopPanel) {
		super();
		add(index, this);
	}

	public void rest(int index) {
		ShopPanel shopPanel = Shop.shopPanel;
		shopPanel.removeAll();
		add(index, shopPanel);
	}

	public void add(int index, ShopPanel shopPanel) {
		shopPanel.setLayout(null);
		shopPanel.setBounds(0, 0, width, height);
		int q = index;
		index *= 4;
		int last = index + 16;
		if (last > Shop.after_goods.size())
			last = Shop.after_goods.size();
		for (int i = index, j = 0; i < last; i++, j++) {
			shopPanel.add(new Shop_after(j, Shop.after_goods.get(i), shopPanel));
		}
		JButton prve = new JButton("上一页");
		prve.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		prve.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (q > 0)
					new ShopPanel(q - 1);
			}
		});
		prve.setBounds(x1 + 60, y1 + 430, 80, 40);
		JButton lastt = new JButton("下一页");
		lastt.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		lastt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (q < Shop.after_goods.size() / 4 - 2)
					new ShopPanel(q + 1);
			}
		});
		lastt.setBounds(x1 + 160, y1 + 430, 80, 40);
		JButton Goods_all = new JButton("全部");
		if (ShopPanel.button == 1) {
			Goods_all.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
		} else {
			Goods_all.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		}
		Goods_all.setBounds(x0, y1 + z0 * 1, 80, 40);
		Goods_all.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Shop.after_goods = Shop.goods;
				ShopPanel.button = 1;
				new ShopPanel(0);
			}
		});
//		Goods_all
		JButton Goods_equip = new JButton("装备");
		if (ShopPanel.button >= 3) {
			Goods_equip.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
			String[] equip = Goods.equip[1];
			for (int i = 0; i < 9; i++) {
				ShopPanel.button_type = i;
				JButton jButton = new JButton(equip[i]);
				if (ShopPanel.button == i + 4) {
					jButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
				} else {
					jButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
				}
				jButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						Shop.after_goods = new ArrayList<Goods>();
						int j = 0;
						for (int i = 0; i < Shop.goods.size(); i++) {
							if (equip[Shop.goods.get(i).getType()].compareTo(jButton.getText()) == 0) {
								j = Shop.goods.get(i).getType();
								Shop.after_goods.add(Shop.goods.get(i));
							}
						}
						ShopPanel.button = 4 + j;
						new ShopPanel(0);
					}
				});
				jButton.setBounds(x2, y2 + z2 * i, 60, 35);
				shopPanel.add(jButton);
			}
		} else {
			Goods_equip.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		}
		Goods_equip.setBounds(x0, y1 + z0 * 2, 80, 40);
		Goods_equip.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Shop.after_goods = new ArrayList<Goods>();
				for (int i = 0; i < Shop.goods.size(); i++) {
					if (Shop.goods.get(i).getType() < 9)
						Shop.after_goods.add(Shop.goods.get(i));
				}
				ShopPanel.button = 3;
				new ShopPanel(0);
			}
		});
//		Goods_equip
		JButton Goods_med = new JButton("药品");
		if (ShopPanel.button == 2) {
			Goods_med.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
		} else {
			Goods_med.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		}
		Goods_med.setBounds(x0, y1 + z0 * 3, 80, 40);
		Goods_med.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Shop.after_goods = new ArrayList<Goods>();
				for (int i = 0; i < Shop.goods.size(); i++) {
					if (Shop.goods.get(i).getType() > 8)
						Shop.after_goods.add(Shop.goods.get(i));
				}
				ShopPanel.button = 2;
				new ShopPanel(0);
			}
		});
//		Goods_med
		shopPanel.add(Goods_all);
		shopPanel.add(Goods_equip);
		shopPanel.add(Goods_med);
		shopPanel.add(prve);
		shopPanel.add(lastt);
		shopPanel.setBackground(Color.RED);
//		background = new ImageIcon("resouce/image/others/pack_big_background.png").getImage();
		shopPanel.repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, width, height, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font("宋体", Font.BOLD, 15));
		g.drawString("你还剩余 ￥:" + GameController.own.getMoney(), x1 + 260, y1 + 455);
	}

}
