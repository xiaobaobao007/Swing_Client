package Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import Controller.BackPack;
import Controller.GameController;
import Enity.Goods;
import Enity.OwnPeople;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

@SuppressWarnings("serial")
public class PackPanel extends JPanel {

	private final int width = BackPack.width;
	private final int height = BackPack.height;

	public PackPanel() {
		super();
		add(this, 0);
	}

	public PackPanel(int a) {
		rest(a);
	}

	private void rest(int a) {
		PackPanel packPanel = BackPack.packPanel;
		packPanel.removeAll();
		add(packPanel, a);
		BackPack.packPanel.validate();
		BackPack.packPanel.repaint();
	}

	public void add(PackPanel packPanel, int index) {
		packPanel.setLayout(null);
		packPanel.setBounds(0, 0, width, height);
		List<Goods> goods = OwnPeople.own_goods;
		int size = goods.size();
		int j = size - index * 4 > 16 ? 16 : size;
		for (int i = index * 4; i < j; i++) {
			packPanel.add(new Pack_after(i - index * 4, goods.get(i)));
		}
		JButton prve = new JButton("上一页");
		prve.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		prve.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (index > 0)
					new PackPanel(index - 1);
			}
		});
		prve.setBounds(20, 425, 80, 40);
		JButton lastt = new JButton("下一页");
		lastt.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		lastt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (index < OwnPeople.own_goods.size() / 4 - 2)
					new PackPanel(index + 1);
			}
		});
		lastt.setBounds(120, 425, 80, 40);
		JButton Goods_all = new JButton("全部");
		if (ShopPanel.button == 1) {
			Goods_all.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
		} else {
			Goods_all.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		}
		packPanel.add(prve);
		packPanel.add(lastt);
		packPanel.setBackground(Color.pink);
	}

//	public static void main(String[] args) {
//		int index=1;int size=10;
//		System.out.println(size-index*4>9?9:size);
//	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("宋体", Font.BOLD, 15));
		g.drawString("你还剩余 ￥:" + GameController.own.getMoney(), 250, 425);
	}
}
