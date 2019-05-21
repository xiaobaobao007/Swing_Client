package Controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Panel.InformationP;

@SuppressWarnings("serial")
public class Information extends JPanel implements MouseListener {

    public static JFrame frame;
    public static InformationP informationP;
    private static boolean open = true;

    public Information() {
        super.addMouseListener(this);// ����mouse����
    }

    private static void open_back() {

        if (frame != null) {
            frame.dispose();
            frame = null;
        }
        frame = new JFrame("Information");
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = GameController.panel_width;
        int x = (int) (ScreenSize.getWidth() - width) / 3;
        int height = GameController.panel_height;
        int y = (int) (ScreenSize.getHeight() - height) / 3;
        frame.setLocation(x, y);
        frame.setSize(600, 530);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        informationP = new InformationP();
        frame.add(informationP);
        Image img = new ImageIcon("resouce/image/others/mouse.png").getImage();
        Cursor cu = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), null);
        frame.setCursor(cu);
        frame.setVisible(true);// 30, 550, 260, 35
    }

    public void mouseClicked(MouseEvent e)// ��굥���¼�
    {
        if (e.getButton() == MouseEvent.BUTTON1)// �ж�������������
        {
            if (open) {
                open_back();
                open = false;
            } else {
                frame.dispose();
                open = true;
            }
        }
    }

    public void mouseEntered(MouseEvent e)// ���������
    {
    }

    public void mouseExited(MouseEvent e)// ����˳����
    {
    }

    public void mousePressed(MouseEvent e)// ��갴��
    {
    }

    public void mouseReleased(MouseEvent e)// ����ɿ�
    {
    }

}
