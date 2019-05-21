package ChooseGamer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ChooseChapter.ChooseChapter;
import Controller.GameController;
import Controller.GameJFrame;
import Enity.OwnPeople;
import Client.ClientStart;

public class ChooseController extends JPanel implements MouseListener, Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static int nums = -1;
    private static boolean tf = true;
    private Image choose;
    private Image background;
    private int width = 200;
    private int height = 266;
    private int width1 = 120;
    private int height1 = 160;
    private int x1 = (width - width1) / 2;
    private int y1 = (height - height1) / 2;
    private int z1 = 10;
    private OwnPeople own;
    private boolean b;

    ChooseController(int n, boolean b) {
        own = GameController.Owns.get(n);
        nums++;
        this.b = b;
        this.setLayout(null);
        int[] ys = {100, 100, 500, 500};
        int[] xs = {100, 500, 100, 500};
        this.setBounds(xs[n], ys[n], width, height);
        this.addMouseListener(this);
        this.setBackground(Color.blue);
        choose = new ImageIcon("resouce/image/others/choosegamer.png").getImage();
        background = new ImageIcon("resouce/image/others/choosegamer_background.png").getImage();
        if (b) {
            new Thread(this).start();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, width, height, this);
        if (b) {
            g.drawImage(own.getOwn_img(), x1, y1, width1, height1, this);
        } else {
            g.drawImage(choose, x1, y1, width1, height1, this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//		if(n<3&&nums==n) {
//			chooseStart.add(new ChooseController(n+1,false, chooseStart));
//			chooseStart.repaint();
//		}
//		else if(n<3&&nums!=n){
        GameController.choseOwn(0);
        if (tf) ClientStart.OutStreamAll("0:0204");// ���ظ�����Ʒ��Ϣ
        tf = false;
        GameJFrame.setBack(new Color(148,198,221));
        new ChooseChapter();
//		}
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        x1 -= z1;
        y1 -= z1;
        width1 += 2 * z1;
        height1 += 2 * z1;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        x1 += z1;
        y1 += z1;
        width1 -= 2 * z1;
        height1 -= 2 * z1;
        repaint();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }

    }

}
