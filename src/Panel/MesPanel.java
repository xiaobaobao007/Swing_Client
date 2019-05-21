package Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import ChooseChapter.ChooseLeave;
import Enity.Message;

public class MesPanel {
    public static List<Message> messages = new ArrayList<>();
    private Image background;

    public MesPanel() {
        super();
        background = new ImageIcon("resouce/image/map/map_ground.png").getImage();
    }

    public void Draw(Graphics g, ImageObserver o) {

        int y = 400;
        int x = 30;
        g.drawImage(background, x, y, 270, 190, o);

        int size = messages.size();
        int mes_Size = 6;
        int s = size > mes_Size ? mes_Size : size;
        int h = s * 25 + 20;
        for (; s > 0; s--) {
            Message m = messages.get(size - s);
            int id = m.getId();
            if (id < 0) {
                g.setColor(Color.RED);//ϵͳ
            } else {
                g.setColor(Color.WHITE);//�û�
            }
            g.setFont(new Font("΢���ź�", Font.PLAIN, 15));
            String name = "";
            if (id < 0) {
                switch (id) {
                    case -4:
                        name = "�̵�";
                        break;
                    case -3:
                        name = "����װ��";
                        break;
                    case -2:
                        name = "����";
                        break;
                    case -1:
                        name = "ϵͳ";
                        break;
                }
            } else {
                name = ChooseLeave.inToname(m.getId());
            }
            g.drawString("��" + name + "��:" + m.getMes(), x + 5, y + h - s * 25);
        }

    }
}
