package Panel;

import Client.ClientStart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

class Regist {

	private static final int login_width = 300;
	private static final int login_height = 250;

	Regist() {
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame registJF = new JFrame();
		registJF.setTitle("◊¢≤·");
		JPanel loginJP = new JPanel();
		loginJP.setLayout(null);
		loginJP.setBackground(Color.GRAY);
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (ScreenSize.getWidth() - login_width) / 2;
		int y = (int) (ScreenSize.getHeight() - login_height) / 2;
		registJF.setLocation(x, y);
		registJF.setSize(login_width, login_height);

		JLabel userLabel = new JLabel("’À∫≈:");
		userLabel.setBounds(10, 20, 80, 25);
		loginJP.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		loginJP.add(userText);

		JLabel passwordLabel = new JLabel("√‹¬Î:");
		passwordLabel.setBounds(10, 60, 80, 25);
		loginJP.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 60, 165, 25);
		loginJP.add(passwordText);

		JLabel repasswordLabel = new JLabel("»∑»œ√‹¬Î:");
		repasswordLabel.setBounds(10, 100, 100, 25);
		loginJP.add(repasswordLabel);

		JPasswordField repasswordText = new JPasswordField(20);
		repasswordText.setBounds(100, 100, 165, 25);
		loginJP.add(repasswordText);

		JButton loginButton = new JButton("∑µªÿµ«¬Ω");
		loginButton.setBounds(30, 140, 100, 25);
		loginJP.add(loginButton);
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				registJF.dispose();
				new Login();
			}
		});
		JButton registButton = new JButton("◊¢≤·");
		registButton.setBounds(160, 140, 90, 25);
		registButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String user = String.valueOf(userText.getText());
				String password = String.valueOf(passwordText.getPassword());
				String rePassword = String.valueOf(repasswordText.getPassword());
				if (rePassword.trim().equals("") || password.trim().equals("") || user.trim().equals("")) {

					JOptionPane.showMessageDialog(registJF, "«ÎÃÓ–¥ÕÍ’˚–≈", "Error", JOptionPane.WARNING_MESSAGE);
				} else if (!rePassword.trim().equals(password.trim())) {
					JOptionPane.showMessageDialog(registJF, "¡Ω¥Œ√‹¬Î ‰»Î≤ª“ª", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
//                    try {
//                        ClientStart.dos.writeUTF(ClientStart.ip + ":0001:" + user + ":" + password);
//                        String login_result;
//                        while ((login_result = ClientStart.dis.readUTF()) != null) {
//                            if (login_result.compareTo("1") == 0) {
					JOptionPane.showMessageDialog(registJF, "◊¢≤·≥…π¶", "Success", JOptionPane.WARNING_MESSAGE);
					registJF.dispose();
					new Login();
//                            } else {
//                                JOptionPane.showMessageDialog(registJF, "’À∫≈“—¥Ê‘⁄£°", "Error", JOptionPane.WARNING_MESSAGE);
//                                userText.setText("");
//                                passwordText.setText("");
//                                repasswordText.setText("");
//                            }
//                            break;
//                        }
//                    } catch (IOException e) {
//                        JOptionPane.showMessageDialog(registJF, "◊¢≤· ß∞‹", "Error", JOptionPane.WARNING_MESSAGE);
//                        userText.setText("");
//                        repasswordText.setText("");
//                    }
				}
			}
		});
		loginJP.add(registButton);

		registJF.add(loginJP);
		registJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registJF.setVisible(true);

	}

}
