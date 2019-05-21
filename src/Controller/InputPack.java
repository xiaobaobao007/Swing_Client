package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.sun.awt.AWTUtilities;
import Enity.Message;
import Client.ClientStart;
import Panel.MesPanel;

@SuppressWarnings("serial")
public class InputPack extends JPanel implements MouseListener {

    public static JFrame frame;
    public static String text;
    static int width = GameController.panel_width;
    static int height = GameController.panel_height;
    static boolean open = true;
    public GameController gameContrller;
    Image InputBox;
    int pack_change = 14;

    public InputPack(GameController gameContrller) {
        super.addMouseListener(this);// 加入mouse监听
        this.gameContrller = gameContrller;
    }

    public static void open_back() {

        frame = new JFrame();
        int x = GameJFrame.GameJFrame.getX() + GameController.input_x + GameJFrame.left;
        int y = GameJFrame.GameJFrame.getY() + GameController.input_y + GameJFrame.top;
        frame.setLocation(x, y);
        frame.setSize(Integer.valueOf(GameController.input_width * 3 / 5), GameController.input_height);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);
        AWTUtilities.setWindowOpaque(frame, false);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        JTextField userText = new JTextField(20);
        userText.setOpaque(true);
        userText.setForeground(new Color(226, 196, 135));
        userText.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        Document dt = userText.getDocument();
        dt.addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                text = userText.getText();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                text = userText.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                text = userText.getText();
            }
        });
        userText.setBackground(new Color(55, 49, 37));
        frame.add(userText);
        frame.setVisible(true);// 30, 550, 260, 35
    }

    public void mouseClicked(MouseEvent e)// 鼠标单击事件
    {
        if (e.getButton() == MouseEvent.BUTTON1)// 判断是鼠标左键按下
        {
            if (open) {
                open_back();
                open = false;
            } else {
                if (text != null) {
                    ClientStart.OutStreamAll(GameController.own_cilent_id + ":0203:" + text);
                    MesPanel.messages.add(new Message(GameController.own_cilent_id, text));
                    text = null;
                }
                frame.dispose();
                open = true;
            }
        }
    }

    public void mouseEntered(MouseEvent e)// 鼠标进入组件
    {
        InputBox = new ImageIcon("resouce/image/others/mesinputbox2.png").getImage();
        gameContrller.setInputBox(InputBox);
    }

    public void mouseExited(MouseEvent e)// 鼠标退出组件
    {
        InputBox = new ImageIcon("resouce/image/others/mesinputbox1.png").getImage();
        gameContrller.setInputBox(InputBox);
    }

    public void mousePressed(MouseEvent e)// 鼠标按下
    {
    }

    public void mouseReleased(MouseEvent e)// 鼠标松开
    {
    }

}
