package Controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import Enity.Goods;
import Panel.ShopPanel;


@SuppressWarnings("serial")
public class Shop extends JPanel implements MouseListener {

    public static int width = 650;
    public static int height = 650;
    public static JFrame frame;
    public static ShopPanel shopPanel;
    public static List<Goods> goods = new ArrayList<Goods>();
    public static List<Goods> after_goods = new ArrayList<Goods>();
    private static boolean open_pack = true;
    public GameController gameContrller;
    int shop_change = 14;
    private int index = 0;

    public Shop(GameController gameContrller) {
        super.addMouseListener(this);// 加入mouse监听
        this.gameContrller = gameContrller;

    }

    public void open_back() {

        frame = new JFrame();
        frame.setTitle("Shop");
        frame.setLayout(null);

        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (ScreenSize.getWidth() - width) / 2;
        int y = (int) (ScreenSize.getHeight() - height) / 2;
        frame.setLocation(x, y);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        after_goods = goods;
        shopPanel = new ShopPanel(index, shopPanel);
        frame.add(shopPanel);
        Image img = new ImageIcon("resouce/image/others/mouse.png").getImage();
        Cursor cu = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), null);
        frame.setCursor(cu);
        frame.setVisible(true);
        open_pack = false;
    }

    public void mouseClicked(MouseEvent e)// 鼠标单击事件
    {
        if (e.getButton() == MouseEvent.BUTTON1)// 判断是鼠标左键按下
        {
            if (open_pack) {
                open_back();
            } else {
                frame.dispose();
                open_pack = true;
            }
        }
    }

    public void mouseEntered(MouseEvent e)// 鼠标进入组件
    {
        gameContrller.setShop_height(shop_change, 1);
        gameContrller.setShop_width(shop_change, 1);
        gameContrller.setShop_x(shop_change / 2, 0);
        gameContrller.setShop_y(shop_change / 2, 0);
    }

    public void mouseExited(MouseEvent e)// 鼠标退出组件
    {
        gameContrller.setShop_height(shop_change, 0);
        gameContrller.setShop_width(shop_change, 0);
        gameContrller.setShop_x(shop_change / 2, 1);
        gameContrller.setShop_y(shop_change / 2, 1);
    }

    public void mousePressed(MouseEvent e)// 鼠标按下
    {
    }

    public void mouseReleased(MouseEvent e)// 鼠标松开
    {
    }

}
