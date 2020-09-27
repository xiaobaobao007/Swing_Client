package ChooseChapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GameFriend extends JPanel implements MouseListener, Runnable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final Image friend;
	private final Image background;
	private final int width = 200;
	private final int height = 266;
	private int width1 = 120;
	private int height1 = 160;
	private int x1 = (width - width1) / 2;
	private int y1 = (height - height1) / 2;
	private final int z1 = 10;

	GameFriend(int x, int y) {
		this.setLayout(null);
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
		friend = new ImageIcon("resouce/image/others/friend_list_icon.png").getImage();
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
