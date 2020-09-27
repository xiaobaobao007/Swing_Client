package Panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import Enity.Goods;

public class Goods_infor extends JPanel {

	private static final long serialVersionUID = 1L;
	public static Goods goods;
	private static List<Color> color_leave;
	private static final String[] equip = Goods.equip[1];
	private static final String[] leave = {"普通", "强化", "稀有", "罕见", "史诗"};

	Goods_infor() {
		this.setSize(400, 650);
		this.setBackground(new Color(34, 43, 64));
	}

	public Goods_infor(String info) {
		color_leave = new ArrayList<>();
		color_leave.add(Color.WHITE);// 普通。白色
		color_leave.add(Color.BLUE);// 强化。蓝色
		color_leave.add(new Color(128, 0, 255));// 稀有。紫色
		color_leave.add(Color.PINK);// 罕见。粉色
		color_leave.add(Color.ORANGE);// 史诗。橙色
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(210, 213, 142));
		g.fill3DRect(95, 40, 50, 50, true);
		g.drawImage(goods.getImage(), 95, 40, 50, 50, this);

		int l = goods.getLeave();
		g.setColor(color_leave.get(l - 1));
		g.setFont(new Font("宋体", Font.BOLD, 20));
		g.drawString(goods.getName(), 155, 57);
		g.drawString(leave[goods.getLeave() - 1] + "   " + equip[goods.getType()], 155, 87);

		g.setColor(new Color(128, 173, 180));
		g.setFont(new Font("宋体", Font.BOLD, 25));
		g.drawString("基础属性", 135, 133);
		g.drawString("装备说明", 135, 440);

		g.setColor(new Color(186, 187, 181));
		g.setFont(new Font("宋体", Font.BOLD, 20));
		int y1 = 180;// 属性名称
		int x1 = 135;
		g.drawString("力量:", x1, y1);
		int z1 = 35;
		g.drawString("魔力:", x1, z1 + y1);
		g.drawString("技巧:", x1, z1 * 2 + y1);
		g.drawString("速度:", x1, z1 * 3 + y1);
		g.drawString("体质:", x1, z1 * 4 + y1);
		g.drawString("护甲:", x1, z1 * 5 + y1);
		g.drawString("抗性:", x1, z1 * 6 + y1);

		g.setColor(new Color(35, 176, 152));
		g.setFont(new Font("宋体", Font.BOLD, 20));
		int y2 = 180;// 属性名字
		int x2 = 195;
		g.drawString(goods.getPower() + "", x2, y2);
		int z2 = 35;
		g.drawString(goods.getMagic() + "", x2, z2 + y2);
		g.drawString(goods.getSkill() + "", x2, z2 * 2 + y2);
		g.drawString(goods.getSpeed() + "", x2, z2 * 3 + y2);
		g.drawString(goods.getPhysical() + "", x2, z2 * 4 + y2);
		g.drawString(goods.getPhysical() + "", x2, z2 * 5 + y2);
		g.drawString(goods.getResistance() + "", x2, z2 * 6 + y2);
		String info = goods.getNote();
		int z3 = 15;
		int lenth = info.length() / z3;
		for (int i = 0; i <= lenth; i++) {
			int y3 = 500;// 说明
			int x3 = 40;
			if (i == lenth)
				g.drawString(info.substring(i * z3), x3, 35 * i + y3);
			else
				g.drawString(info.substring(i * z3, (i + 1) * z3), x3, 35 * i + y3);
		}

		g.setColor(new Color(186, 180, 96));
		g.setFont(new Font("宋体", Font.BOLD, 20));
		g.drawString("商品售价:", 230, 600);
		g.drawString(goods.getMoney() + "", 340, 600);
	}
}
