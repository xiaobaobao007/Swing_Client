package Enity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class HurtBlood {

    private int blood;
    private int time;
    private int type;
    private String info;

    public HurtBlood() {
        super();
    }

    public HurtBlood(int blood, int time, int type, String info) {
        super();
        this.blood = blood;
        this.time = time;
        this.type = type;
        this.info = info;
    }

    public void Draw(Graphics g, int x, int y, ImageObserver o) {
        if (type == 1) {//�����˺���ʾ
            g.setColor(Color.red);
            g.setFont(new Font("����", Font.BOLD, 25));
            g.drawString("-" + blood, x + 35, y + time * 5 - 50);
        } else if (type == 2) {//�����˺���ʾ
            g.setColor(Color.blue);
            g.setFont(new Font("����", Font.BOLD, 25));
            g.drawString("-" + blood, x + 35, y + time * 5 - 50);
        } else if (type == 3) {//��ʵ�˺���ʾ
            g.setColor(Color.white);
            g.setFont(new Font("����", Font.BOLD, 25));
            g.drawString("-" + blood, x + 35, y + time * 5 - 50);
        } else if (type == 4) {//δ������ʾ
            g.setColor(Color.red);
            g.setFont(new Font("����", Font.BOLD, 25));
            g.drawString("δ����", x + 5, y + time * 5 - 50);
        } else if (type == 5) {//������ʾ
            g.setColor(Color.red);
            g.setFont(new Font("����", Font.BOLD + Font.ITALIC, 35));
            g.drawString("-" + blood, x + 35, y + time * 5 - 50);
        } else if (type == 6) {//������ʾ
            g.setColor(Color.blue);
            g.setFont(new Font("����", Font.BOLD, 25));
            g.drawString("+" + blood, x + 35, y + time * 5 - 50);
        } else if (type == 7) {//��Ѫ��ʾ
            g.setColor(Color.green);
            g.setFont(new Font("����", Font.BOLD, 25));
            g.drawString("+" + blood, x + 35, y + time * 5 - 50);
        } else if (type == 8) {//������ʾ
            g.setColor(Color.blue);
            g.setFont(new Font("����", Font.BOLD, 25));
            g.drawString("����", x + 35, y + time * 5 - 50);
        } else {
            g.setColor(Color.yellow);
            g.setFont(new Font("����", Font.BOLD, 20));
            g.drawString("-" + blood, x + 35, y + time * 5 - 50);
        }
        time--;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
