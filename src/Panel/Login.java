package Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

import Client.ClientStart;
import Client.InputStream;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import Controller.GameController;
import Enity.Message;

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
        loginJF.setTitle("µÇÂ½");
        JPanel loginJP = new JPanel();
        loginJP.setLayout(null);
        loginJP.setBackground(Color.GRAY);
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (ScreenSize.getWidth() - login_width) / 2;
        int y = (int) (ScreenSize.getHeight() - login_height) / 2;
        loginJF.setLocation(x, y);
        loginJF.setSize(login_width, login_height);

        JLabel userLabel = new JLabel("ÕËºÅ:");
        userLabel.setBounds(10, 20, 80, 25);
        loginJP.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(80, 20, 140, 25);
        loginJP.add(userText);

        JLabel passwordLabel = new JLabel("ÃÜÂë:");
        passwordLabel.setBounds(10, 60, 80, 25);
        loginJP.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(80, 60, 140, 25);
        loginJP.add(passwordText);

        userText.setText("1");
        passwordText.setText("1");

        JButton loginButton = new JButton("µÇÂ½");
        loginButton.setBounds(30, 100, 90, 25);
        loginJP.add(loginButton);
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                String usertext = String.valueOf(userText.getText());
                String passtext = String.valueOf(passwordText.getPassword());
                if (passtext.trim().equals("") || usertext.trim().equals("")) {
                    JOptionPane.showMessageDialog(loginJF, "ÇëÌîÐ´ÍêÕûÐÅÏ¢?", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ClientStart.dos.writeUTF(ClientStart.ip + ":0000:" + usertext + ":" + passtext);
                        Integer login_result;
                        while ((login_result = Integer.valueOf(ClientStart.dis.readUTF())) != null) {
                            if (login_result > 0) {
                                GameController.own_cilent_id = login_result;

                                System.out.println("ÓÃ»§ID£º" + login_result + "£¬µÇÂ½³É¹¦¡£");
                                MesPanel.messages.add(new Message(-1, "»¶Ó­£¡£¡£¡"));
//								JOptionPane.showMessageDialog(loginJF, "µÇÂ½³É¹¦", "Success",JOptionPane.WARNING_MESSAGE);
                                loginJF.dispose();
//								new GameStart();
                                ClientStart.OutStreamAll(login_result + ":0201");// ¸öÈËÊý¾Ý
                                InputStream in = new InputStream();
                                new Thread(in).start();
                            } else if (login_result == 0) {
                                JOptionPane.showMessageDialog(loginJF, "ÕËºÅÃÜÂë´íÎó", "Error", JOptionPane.WARNING_MESSAGE);
                                userText.setText("");
                                passwordText.setText("");
                            } else {
                                JOptionPane.showMessageDialog(loginJF, "ÕËºÅÒÑ¾­±»µÇÂ½£¬Çë×¢²áÐÂÓÃ»§", "Error", JOptionPane.WARNING_MESSAGE);
                                userText.setText("");
                                passwordText.setText("");
                            }
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(loginJF, "ÕËºÅÃÜÂë´íÎó", "Error", JOptionPane.WARNING_MESSAGE);
                        userText.setText("");
                        passwordText.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        JButton registButton = new JButton("×¢²á");
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