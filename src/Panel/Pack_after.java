package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;

import com.sun.awt.AWTUtilities;

import Controller.BackPack;
import Controller.GameController;
import Controller.Information;
import Controller.Shop;
import Enity.Goods;
import Enity.Message;
import Enity.OwnPeople;

public class Pack_after extends JPanel implements MouseListener, MouseMotionListener {// x,y,controller,

    private static JFrame frame;
    private static String[] equip = {"ͷ��", "����", "����", "����", "����", "Ь��", "��ָ", "����", "����", "����ҩ", "��Ѫҩ"};
    private int good_location;
    private int x;
    private int y;
    private int sx = 60;
    private int sy = 40;
    private int goods_x = 10;
    private int goods_y = 10;
    private int width = 80;
    private int height = 80;
    private Goods good;
    private Image good_img;
    private Image background;
    private int Screen_width;
    private int Screen_height;

    Pack_after(int good_location, Goods good) {
        super();
        this.good_location = good_location;
        this.good = good;
        this.x = (good_location % 4) * 100;
        this.y = (good_location / 4) * 100;
        super.addMouseListener(this);
        super.addMouseMotionListener(this);
        this.setBounds(x, y, 100, 100);
        good_img = good.getImage();
        background = new ImageIcon("resouce/image/others/pack_small_background.png").getImage();
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Screen_width = (int) ScreenSize.getWidth();
        Screen_height = (int) ScreenSize.getHeight();
        super.repaint();
    }

    private void open_back(int x, int y) {

        if (frame != null) {
            frame.dispose();
            frame = null;
        }
        frame = new JFrame();
        frame.setLocation(x, y);
        frame.setSize(400, 650);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);
        AWTUtilities.setWindowOpaque(frame, false);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.add(new Goods_infor());
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);// 30, 550, 260, 35
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, 100, 100, this);
        g.drawImage(good_img, goods_x, goods_y, width, height, this);
        g.setColor(Color.white);
        g.setFont(new Font("����", Font.BOLD, 15));
        int nums = this.good.getNums();
        if (nums > 1) {
            g.drawString("" + nums, 90, 90);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3)// �ж�������Ҽ�����
        {
            JPopupMenu jp = new JPopupMenu();
            JMenuItem jm1 = new JMenuItem("ʹ��");
            JMenuItem jm11 = new JMenuItem("�滻");
            JMenuItem jm2 = new JMenuItem("����");
            JMenuItem jm3 = new JMenuItem("����");
            jm1.addActionListener(e1 -> {
                if (Information.frame != null) {
                    new InformationP(0);
                }
                boolean TF = OwnPeople.Add_Equip(good);
                int type = good.getType();
                if (type <= 8) {
                    if (TF) {
                        MesPanel.messages.add(new Message(-2, "װ��" + equip[good.getType()] + "�ɹ�"));
                        if (!good.subNums())
                            OwnPeople.own_goods.remove(good_location);
                    } else {
                        MesPanel.messages.add(new Message(-2, "װ��" + equip[good.getType()] + "ʧ��"));
                    }
                } else {
                    if (!good.subNums())
                        OwnPeople.own_goods.remove(good_location);
                    MesPanel.messages.add(new Message(-2, "ҩƷ" + equip[good.getType()] + "ʹ�óɹ�"));
                }
                OwnPeople.save_goods();
                new PackPanel(0);
            });
            jm11.addActionListener(e14 -> {
                Goods good = OwnPeople.own_goods.get(good_location);
                int test = good.getType();
                if (test > 6)
                    test++;
                Goods good1 = OwnPeople.equipment.getEquip(test);
                OwnPeople.Sub_Equip(good1);
                OwnPeople.Add_Equip(good);
                MesPanel.messages.add(new Message(-2, "װ��" + equip[good.getType()] + "�滻�ɹ�"));
                if (!good.subNums())
                    OwnPeople.own_goods.remove(good_location);
                OwnPeople.save_goods();
                if (Information.frame != null) {
                    new InformationP(0);
                }
                new PackPanel(0);
            });
            jm2.addActionListener(e13 -> {
                if (!good.subNums())
                    OwnPeople.own_goods.remove(good_location);
                int money = good.getMoney() / 2;
                GameController.own.addMoney(money);
                MesPanel.messages.add(new Message(-2, "װ��" + good.getName() + "������" + money + "Ԫ"));
                OwnPeople.save_goods();
                new PackPanel(0);
                if (Shop.frame != null) {
                    Shop.frame.repaint();
                }
            });
            jm3.addActionListener(e12 -> {
                OwnPeople.own_goods.remove(good_location);
                good.setX(GameController.own.getX());
                good.setY(GameController.own.getY());
                GameController.map_goods.add(good);
                OwnPeople.save_goods();
                new PackPanel(0);
            });
            int test = this.good.getType();
            if (test > 6)
                test++;
            if (OwnPeople.equipment.getEquip(test) != null) {
                jp.add(jm11);
            } else {
                jp.add(jm1);
            }
            jp.add(jm2);
            jp.add(jm3);
            jp.show(this, e.getX() - 30, e.getY() - 20);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Goods_infor.goods = good;
        int x = BackPack.frame.getX() + e.getX() + this.x + sx;
        int y = BackPack.frame.getY() + e.getY() + this.y + sy;
        if (y > Screen_height - 700)
            y = Screen_height - 700;
        if (x > Screen_width - 450)
            x -= 500;
        if (Pack_after.frame == null) {
            open_back(x, y);
        }
        goods_x -= 5;
        goods_y -= 5;
        width += 10;
        height += 10;
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        goods_x += 5;
        goods_y += 5;
        width -= 10;
        height -= 10;
        if (Pack_after.frame != null) {
            Pack_after.frame.dispose();
            Pack_after.frame = null;
        }
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = BackPack.frame.getX() + e.getX() + this.x + sx;
        int y = BackPack.frame.getY() + e.getY() + this.y + sy;
        if (y > Screen_height - 700)
            y = Screen_height - 700;
        if (x > Screen_width - 450)
            x -= 500;
        if (Pack_after.frame != null) {
            frame.setLocation(x, y);
        }
    }

}
