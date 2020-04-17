package ChooseChapter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameLeave extends JPanel implements MouseListener, MouseWheelListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Image friend;
    private Image background;
    private int width = 200;
    private int height = 266;
    private int width1 = 120;
    private int height1 = 160;
    private int x1 = (width - width1) / 2;
    private int y1 = (height - height1) / 2;
    private int z1 = 10;

    GameLeave(int x, int y) {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
        friend = new ImageIcon("resouce/image/others/choose_leave_icon.png").getImage();
        background = new ImageIcon("resouce/image/others/chooseleave.png").getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, width, height, this);
        g.drawImage(friend, x1, y1, width1, height1, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//		new GameLeave();
        ChooseChapter.ToLeave();
//		if (e.getButton() == MouseEvent.BUTTON1) {
//			System.out.println("1");
//		} else if (e.getButton() == MouseEvent.BUTTON2) {
//			System.out.println("2");
//		} else if (e.getButton() == MouseEvent.BUTTON3) {
//			System.out.println("3");
//		} else {
//			System.out.println("4");
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

//	@Override
//	public void run() {
//		while (true) {
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			this.repaint();
//		}
//
//	}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() == 1) {
            System.out.println("滑轮向前。。。。");
        }
        if (e.getWheelRotation() == -1) {
            System.out.println("滑轮向后....");
        }
    }

}
