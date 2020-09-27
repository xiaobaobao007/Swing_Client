package Client;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MapPoint extends JPanel implements MouseListener {

	public static void main(String[] args) {
		new MapPoint();
	}

	int size = 0;
	int[][] points = new int[100][2];

	public static int top;
	public static int left;

	int level = 6;

	MapPoint() {
		JFrame GameJFrame = new JFrame();
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int JFrame_Width = 1264;
		int JFrame_X = (int) (ScreenSize.getWidth() - JFrame_Width) / 2;
		int JFrame_Height = 895;
		int JFrame_Y = (int) (ScreenSize.getHeight() - JFrame_Height) / 2;
		GameJFrame = new JFrame();
		GameJFrame.setLocation(JFrame_X, JFrame_Y);
		GameJFrame.setSize(JFrame_Width, JFrame_Height);
		GameJFrame.addMouseListener(this);
		GameJFrame.add(this);
		this.repaint();
		GameJFrame.setResizable(false);
		GameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameJFrame.setVisible(true);
		Insets insets = GameJFrame.getInsets();
		top = insets.top;
		left = insets.left;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Image background = new ImageIcon("resouce/image/map/map" + level + ".jpg").getImage();
		g.drawImage(background, 0, 0, 1200, 800, this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() - left;
		int y = e.getY() - top;
		points[size][0] = x;
		points[size++][1] = y;
		StringBuffer stringBuffer = new StringBuffer((level - 1) + "/" + size + "{");
		for (int i = 0; i < size; i++) {// {16, 2},
			stringBuffer.append("{").append(points[i][0]).append(",").append(points[i][1]).append("}");
			if (i != size - 1) {
				stringBuffer.append(",");
			}
		}
		stringBuffer.append("}");
		System.out.println(stringBuffer.toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
