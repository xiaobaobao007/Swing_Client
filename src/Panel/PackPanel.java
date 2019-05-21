package Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import Controller.BackPack;
import Controller.GameController;
import Enity.Goods;
import Enity.OwnPeople;

@SuppressWarnings("serial")
public class PackPanel extends JPanel {

    private int width = BackPack.width;
    private int height = BackPack.height;

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
        JButton prve = new JButton("��һҳ");
        prve.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        prve.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (index > 0)
                    new PackPanel(index - 1);
            }
        });
        prve.setBounds(20, 425, 80, 40);
        JButton lastt = new JButton("��һҳ");
        lastt.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        lastt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (index < OwnPeople.own_goods.size() / 4 - 2)
                    new PackPanel(index + 1);
            }
        });
        lastt.setBounds(120, 425, 80, 40);
        JButton Goods_all = new JButton("ȫ��");
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
        g.setFont(new Font("����", Font.BOLD, 15));
        g.drawString("�㻹ʣ�� ��:" + GameController.own.getMoney(), 250, 425);
    }
}
