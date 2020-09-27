package Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

import Client.ClientStart;
import Client.InputStream;
import Controller.GameController;
import Enity.Message;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class Login {

	private static final int login_width = 300;
	private static final int login_height = 220;

	public Login() {
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame loginJF = new JFrame();
		loginJF.setTitle("登陆");
		JPanel loginJP = new JPanel();
		loginJP.setLayout(null);
		loginJP.setBackground(Color.GRAY);
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (ScreenSize.getWidth() - login_width) / 2;
		int y = (int) (ScreenSize.getHeight() - login_height) / 2;
		loginJF.setLocation(x, y);
		loginJF.setSize(login_width, login_height);

		JLabel userLabel = new JLabel("账号:");
		userLabel.setBounds(10, 20, 80, 25);
		loginJP.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(80, 20, 140, 25);
		loginJP.add(userText);

		JLabel passwordLabel = new JLabel("密码:");
		passwordLabel.setBounds(10, 60, 80, 25);
		loginJP.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(80, 60, 140, 25);
		loginJP.add(passwordText);

		userText.setText("1");
		passwordText.setText("1");

		JButton loginButton = new JButton("登陆");
		loginButton.setBounds(30, 100, 90, 25);
		loginJP.add(loginButton);
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String usertext = String.valueOf(userText.getText());
				String passtext = String.valueOf(passwordText.getPassword());
				if (passtext.trim().equals("") || usertext.trim().equals("")) {
					JOptionPane.showMessageDialog(loginJF, "请填写完整信息?", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						ClientStart.dos.writeUTF(ClientStart.ip + ":0000:" + usertext + ":" + passtext);
						Integer login_result;
						while ((login_result = Integer.valueOf(ClientStart.dis.readUTF())) != null) {
							if (login_result > 0) {
								GameController.own_cilent_id = login_result;

								System.out.println("用户ID：" + login_result + "，登陆成功。");
								MesPanel.messages.add(new Message(-1, "欢迎！！！"));
//								JOptionPane.showMessageDialog(loginJF, "登陆成功", "Success",JOptionPane.WARNING_MESSAGE);
								loginJF.dispose();
//								new GameStart();
								ClientStart.OutStreamAll(login_result + ":0201");// 个人数据
								InputStream in = new InputStream();
								new Thread(in).start();
							} else if (login_result == 0) {
								JOptionPane.showMessageDialog(loginJF, "账号密码错误", "Error", JOptionPane.WARNING_MESSAGE);
								userText.setText("");
								passwordText.setText("");
							} else {
								JOptionPane.showMessageDialog(loginJF, "账号已经被登陆，请注册新用户", "Error", JOptionPane.WARNING_MESSAGE);
								userText.setText("");
								passwordText.setText("");
							}
							break;
						}
					} catch (IOException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(loginJF, "账号密码错误", "Error", JOptionPane.WARNING_MESSAGE);
						userText.setText("");
						passwordText.setText("");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		JButton registButton = new JButton("注册");
		registButton.setBounds(140, 100, 90, 25);
//		registButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		loginJP.add(registButton);
		registButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				loginJF.dispose();
				new Regist();
			}
		});
		loginJF.add(loginJP);
		loginJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Image img = new ImageIcon("resouce/image/others/mouse.png").getImage();
		Cursor cu = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), null);
		loginJF.setCursor(cu);

		loginJF.setVisible(true);
	}
}
