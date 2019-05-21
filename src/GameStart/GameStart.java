package GameStart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import Client.ClientStart;
import Controller.BackPack;
import Controller.ExitGame;
import Controller.GameController;
import Controller.GameJFrame;
import Controller.Information;
import Controller.InputPack;
import Controller.KeyListen;
import Controller.Shop;
import Panel.Goods_infor;

public class GameStart extends JPanel implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = -1034803873969025610L;
    private static GameController gameContrller;
    public static Thread game_thread;//游戏主流程线程
    public static int width = GameController.panel_width;
    public static int height = GameController.panel_height;
    public static boolean TF = true;
    public static String[] Infos = new String[10];
    private static String Welcome;
    private static String info;
    private int x = 0;
    private int y1 = 600;

    public GameStart() {
        this.setBackground(Color.black);
        this.setBounds(0, 0, width, height);
        GameJFrame.restJFrame("Game", this);
        gameContrller = new GameController();
        gameContrller.setBounds(0, 0, width, height);
        game_thread = new Thread(gameContrller);
        GameJFrame.GameJFrame.addKeyListener(new KeyListen());
        GameJFrame.toView();
        setInfo(GameController.leave);
        new Thread(this).start();
    }

    public static void TF(boolean t) {
        if (TF && !t) ClientStart.OutStreamAll("1:0402");
        TF = t;
    }

    public static void setInfo(int id) {
        info = Infos[id - 1];
        Welcome = "欢迎“" + GameController.own.getName() + "”来到第 " + id + " 关";
    }

    @Override
    public void run() {
        while (TF) {
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ClientStart.OutStreamAll("1:0402");

        GameJFrame.GameJFrame.remove(this);
        GameJFrame.GameJFrame.add(gameContrller);

        BackPack pack = new BackPack(gameContrller);// 背包
        pack.setBounds(1060, 740, 80, 80);
        GameJFrame.GameJFrame.add(pack);

        Shop shop = new Shop(gameContrller);// 商城
        shop.setBounds(900, 740, 80, 80);
        GameJFrame.GameJFrame.add(shop);

        InputPack input = new InputPack(gameContrller);// 输入框
        input.setBounds(30, 550, 260, 35);
        GameJFrame.GameJFrame.add(input);

        ExitGame exit = new ExitGame(gameContrller);
        exit.setBounds(GameController.exit_x, GameController.exit_y, GameController.exit_width, GameController.exit_height);
        GameJFrame.GameJFrame.add(exit);

        Information information = new Information();// 个人信息
        information.setBounds(GameController.info_x, GameController.info_y, GameController.info_width, GameController.info_height);
        GameJFrame.GameJFrame.add(information);

        GameJFrame.repaintJFrame();

        new Goods_infor("start");
    }

    private int calculate(int x) {
        return -x * x + 20 * x + 100;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (x > 19)
            x = 0;
        else
            x++;
        y1--;
        g.setColor(new Color(255, 255, 255, calculate(x)));
        g.setFont(new Font("宋体", Font.BOLD, 40));
        g.drawString(Welcome, 360, 100);
        g.drawString("按Enter键，可快速跳过 ", 700, 800);

        int z1 = 20;
        int lenth = info.length() / z1;
        int y0 = 0;
        int z0;
        for (int i = 0; i <= lenth; i++) {
            int z2 = 45;
            y0 = z2 * i + y1;
            int y3 = 510;
            int y2 = 450;
            if (y0 < y2)
                z0 = 255 - y2 + y0 < 0 ? 0 : 255 - y2 + y0;
            else if (y0 > y3)
                z0 = 255 + y3 - y0 < 0 ? 0 : 255 + y3 - y0;
            else
                z0 = 255;
            g.setColor(new Color(255, 255, 255, z0));
            int x1 = 200;
            if (i == lenth) {
                g.drawString(info.substring(i * z1), x1, y0);
            } else {
                g.drawString(info.substring(i * z1, (i + 1) * z1), x1, y0);
            }
        }
        if (y0 < 400) TF = false;
    }
}