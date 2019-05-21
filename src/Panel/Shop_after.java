package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Controller.Shop;
import Enity.Goods;
import Enity.Message;
import Enity.OwnPeople;

@SuppressWarnings("serial")
public class Shop_after extends JPanel implements MouseMotionListener, MouseListener {// x,y,controller,

    public static int good_location_x;
    public static int good_location_y;
    public static Goods s_goods;
    static JFrame frame;
    int good_location;
    int x;
    int y;
    int x1 = ShopPanel.x1;
    int y1 = ShopPanel.y1;
    int sx = 60;
    int sy = 40;
    int goods_x = 10;
    int goods_y = 10;
    int width = 80;
    int height = 80;
    int Screen_width;
    int Screen_height;
    Image good_img;
    Image background;
    int n = 0;
    long time = 0;
    private Goods goods;

    public Shop_after(int good_location, Goods goods, ShopPanel shopPanel) {
        super();
        this.good_location = good_location;
        this.goods = goods;
        this.x = (good_location % 4) * 100 + x1;
        this.y = (good_location / 4) * 100 + y1;
        super.addMouseMotionListener(this);
        super.addMouseListener(this);
        this.setBounds(x, y, 100, 100);
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Screen_width = (int) ScreenSize.getWidth();
        Screen_height = (int) ScreenSize.getHeight();
        good_img = goods.getImage();
        background = new ImageIcon("resouce/image/others/pack_small_background.png").getImage();
        super.repaint();
    }

    public void open_back(int x, int y) {

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
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.BOLD, 15));
        g.drawString("￥" + this.goods.getMoney(), 10, 90);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3)// 判断是鼠标右键按下
        {
            JPopupMenu jp = new JPopupMenu();
            JMenuItem jm1 = new JMenuItem("购买");
            Shop_after.s_goods = this.goods;
            jm1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    buygoods();
                }
            });
            jp.add(jm1);
            jp.show(this, e.getX() - 30, e.getY() - 20);
        } else if (n == 0) {
            time = System.currentTimeMillis();
            n = 1;
        } else if (n == 1) {
            time = System.currentTimeMillis() - time;
            if (time < 500) {
                Shop_after.s_goods = this.goods;
                buygoods();
            }
            n = 0;
        }
    }

    public void buygoods() {
        Goods goods = Shop_after.s_goods;
        int money = GameController.own.getMoney() - goods.getMoney();
        if (money >= 0) {
            GameController.own.setMoney(money);
            MesPanel.messages.add(new Message(-4, "成功购买商品"));
            OwnPeople.addGoods(goods);
            Shop.shopPanel.repaint();
            if (BackPack.frame != null)
                new PackPanel(0);
        } else {
            MesPanel.messages.add(new Message(-4, "购买商品失败:余额不足"));
        }
        OwnPeople.save_goods();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Goods_infor.goods = this.goods;
        int x = Shop.frame.getX() + e.getX() + this.x + sx;
        int y = Shop.frame.getY() + e.getY() + this.y + sy;
        if (y > Screen_height - 700)
            y = Screen_height - 700;
        if (Shop_after.frame == null) {
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
        if (Shop_after.frame != null) {
            Shop_after.frame.dispose();
            Shop_after.frame = null;
        }
        goods_x += 5;
        goods_y += 5;
        width -= 10;
        height -= 10;
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = Shop.frame.getX() + e.getX() + this.x + sx;
        int y = Shop.frame.getY() + e.getY() + this.y + sy;
        if (y > Screen_height - 700)
            y = Screen_height - 700;
        if (Shop_after.frame != null) {
            frame.setLocation(x, y);
        }
    }

}
