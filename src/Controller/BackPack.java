package Controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Panel.PackPanel;

@SuppressWarnings("serial")
public class BackPack extends JPanel implements MouseListener {

    public static int width = 455;
    public static int height = 520;
    public static JFrame frame;
    public static PackPanel packPanel;
    private static boolean open_pack = true;
    private GameController gameContrller;
    private int pack_change;

    public BackPack(GameController gameContrller) {
        super.addMouseListener(this);// 加入mouse监听
        this.gameContrller = gameContrller;
        pack_change = 14;
    }

    private static void open_back() {


        if (frame != null) {
            frame.dispose();
            frame = null;
        }
        frame = new JFrame();
        frame.setTitle("Pack");
        frame.setLayout(null);

        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (ScreenSize.getWidth() - width) / 4 * 3;
        int y = (int) (ScreenSize.getHeight() - height) / 2;
        frame.setLocation(x, y);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        packPanel = new PackPanel();
        frame.add(packPanel);
        Image img = new ImageIcon("resouce/image/others/mouse.png").getImage();
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), null);
        frame.setCursor(cursor);
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
        gameContrller.setPack_height(pack_change, 1);
        gameContrller.setPack_width(pack_change, 1);
        gameContrller.setPack_x(pack_change / 2, 0);
        gameContrller.setPack_y(pack_change / 2, 0);
    }

    public void mouseExited(MouseEvent e)// 鼠标退出组件
    {
        gameContrller.setPack_height(pack_change, 0);
        gameContrller.setPack_width(pack_change, 0);
        gameContrller.setPack_x(pack_change / 2, 1);
        gameContrller.setPack_y(pack_change / 2, 1);
    }

    public void mousePressed(MouseEvent e)// 鼠标按下
    {
    }

    public void mouseReleased(MouseEvent e)// 鼠标松开
    {
    }

}
