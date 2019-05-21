package Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Enity.Goods;

public class Goods_infor extends JPanel {

    private static final long serialVersionUID = 1L;
    public static Goods goods;
    private static List<Color> color_leave;
    private static String[] equip = Goods.equip[1];
    private static String[] leave = {"��ͨ", "ǿ��", "ϡ��", "����", "ʷʫ"};

    Goods_infor() {
        this.setSize(400, 650);
        this.setBackground(new Color(34, 43, 64));
    }

    public Goods_infor(String info) {
        color_leave = new ArrayList<>();
        color_leave.add(Color.WHITE);// ��ͨ����ɫ
        color_leave.add(Color.BLUE);// ǿ������ɫ
        color_leave.add(new Color(128, 0, 255));// ϡ�С���ɫ
        color_leave.add(Color.PINK);// ��������ɫ
        color_leave.add(Color.ORANGE);// ʷʫ����ɫ
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(210, 213, 142));
        g.fill3DRect(95, 40, 50, 50, true);
        g.drawImage(goods.getImage(), 95, 40, 50, 50, this);

        int l = goods.getLeave();
        g.setColor(color_leave.get(l - 1));
        g.setFont(new Font("����", Font.BOLD, 20));
        g.drawString(goods.getName(), 155, 57);
        g.drawString(leave[goods.getLeave() - 1] + "   " + equip[goods.getType()], 155, 87);

        g.setColor(new Color(128, 173, 180));
        g.setFont(new Font("����", Font.BOLD, 25));
        g.drawString("��������", 135, 133);
        g.drawString("װ��˵��", 135, 440);

        g.setColor(new Color(186, 187, 181));
        g.setFont(new Font("����", Font.BOLD, 20));
        int y1 = 180;// ��������
        int x1 = 135;
        g.drawString("����:", x1, y1);
        int z1 = 35;
        g.drawString("ħ��:", x1, z1 + y1);
        g.drawString("����:", x1, z1 * 2 + y1);
        g.drawString("�ٶ�:", x1, z1 * 3 + y1);
        g.drawString("����:", x1, z1 * 4 + y1);
        g.drawString("����:", x1, z1 * 5 + y1);
        g.drawString("����:", x1, z1 * 6 + y1);

        g.setColor(new Color(35, 176, 152));
        g.setFont(new Font("����", Font.BOLD, 20));
        int y2 = 180;// ��������
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
            int y3 = 500;// ˵��
            int x3 = 40;
            if (i == lenth)
                g.drawString(info.substring(i * z3), x3, 35 * i + y3);
            else
                g.drawString(info.substring(i * z3, (i + 1) * z3), x3, 35 * i + y3);
        }

        g.setColor(new Color(186, 180, 96));
        g.setFont(new Font("����", Font.BOLD, 20));
        g.drawString("��Ʒ�ۼ�:", 230, 600);
        g.drawString(goods.getMoney() + "", 340, 600);
    }
}