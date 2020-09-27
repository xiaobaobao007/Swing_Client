package Controller;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import Client.ClientStart;
import Client.InputStream;

public class GameJFrame {

	public static JFrame GameJFrame;
	public static int top;
	public static int left;
	private static int JFrame_X = 1264;
	private static int JFrame_Y = 895;

	public static void init() {
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int JFrame_Width = 1264;
		JFrame_X = (int) (ScreenSize.getWidth() - JFrame_Width) / 2;
		int JFrame_Height = 895;
		JFrame_Y = (int) (ScreenSize.getHeight() - JFrame_Height) / 2;
		GameJFrame = new JFrame();
		GameJFrame.setLocation(JFrame_X, JFrame_Y);
		GameJFrame.setSize(JFrame_Width, JFrame_Height);
		GameJFrame.setResizable(false);
		GameJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GameJFrame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				JFrame_X = GameJFrame.getX();
				JFrame_Y = GameJFrame.getY();
				if (InputPack.frame != null) {
					int x = JFrame_X + 45;
					int y = JFrame_Y + 593;
					InputPack.frame.setLocation(x, y);
				}
			}
		});
		GameJFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ClientStart.OutStreamAll("1:0305");
				InputStream.TF = false;

				System.exit(0);
			}
		});
		Image img = new ImageIcon("resouce/image/others/mouse.png").getImage();
		Cursor cu = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), null);
		GameJFrame.setCursor(cu);
	}

	public static void restJFrame(String name) {
		GameJFrame.getContentPane().removeAll();
		setBack(new Color(148, 198, 221));
		GameJFrame.setTitle(name);
		GameJFrame.setLayout(null);
		GameJFrame.repaint();
	}

	public static void restJFrame(String name, JPanel jPanel) {
//        KeyListener[] keyListener = GameJFrame.getKeyListeners();
//        if (keyListener != null && keyListener.length > 0) {
//            for (KeyListener k : keyListener) {
//                GameJFrame.removeKeyListener(k);
//            }
//        }
		GameJFrame.getContentPane().removeAll();
		GameJFrame.setTitle(name);
		GameJFrame.setLayout(null);
		GameJFrame.add(jPanel);
	}

	public static void repaintJFrame() {
		GameJFrame.revalidate();
		GameJFrame.repaint();
	}

	public static void toView() {
		GameJFrame.addKeyListener(new KeyListen());
		GameJFrame.setFocusable(true);
		GameJFrame.setVisible(true);
		GameJFrame.requestFocus();
	}

	public static void setBack(Color color) {
		GameJFrame.getContentPane().setBackground(color);
		repaintJFrame();
	}

}
