package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;

import com.sun.awt.AWTUtilities;

import Controller.BackPack;
import Controller.GameController;
import Controller.Information;
import Enity.Attribute;
import Enity.Goods;
import Enity.Message;
import Enity.OwnPeople;

public class InformationP extends JPanel implements Runnable, MouseListener, MouseMotionListener {

    public static JFrame frame;
    public static List<Goods> goods;
    private Image lock_equip;
    private int Lo_x_1 = 10;
    private int Lo_y_1 = 10;
    private int Lo_x_2 = 250;
    private int Lo_y_2 = 10;
    private int size1 = 60;
    private int size2 = 50;
    private int Screen_width;
    private int Screen_height;
    private String[] goods_name = Goods.equip[1];

    public InformationP() {
        super();
        this.setLayout(null);
        this.setBounds(0, 0, 800, 800);
        this.setBackground(new Color(154, 192, 52));
        lock_equip = new ImageIcon("resouce/image/others/lock_equip.png").getImage();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        new Thread(this).start();
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Screen_width = (int) ScreenSize.getWidth();
        Screen_height = (int) ScreenSize.getHeight();
    }

    InformationP(int info) {
        rest();
    }

    private void rest() {
        InformationP informationP = Information.informationP;
        informationP.removeAll();
        informationP.setLayout(null);
        informationP.setBounds(0, 0, 800, 800);
        informationP.setBackground(new Color(154, 192, 52));
        informationP.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(GameController.own.getOwn_img(), 70, 15, 190, 280, this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.BOLD, 20));
//		"head", "neck", "jacket", "trousers", "weapon", "shoes", "ring", "gloves", "capes"
        if (OwnPeople.equipment.getHead() != null) {
            g.drawImage(OwnPeople.equipment.getHead().getImage(), 10, 10, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 10, 10, 50, 50, this);
            g.drawString(goods_name[0], 15, 40);
        }
        if (OwnPeople.equipment.getNeck() != null) {
            g.drawImage(OwnPeople.equipment.getNeck().getImage(), 10, 70, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 10, 70, 50, 50, this);
            g.drawString(goods_name[1], 15, 100);
        }
        if (OwnPeople.equipment.getJacket() != null) {
            g.drawImage(OwnPeople.equipment.getJacket().getImage(), 10, 130, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 10, 130, 50, 50, this);
            g.drawString(goods_name[2], 15, 160);
        }
        if (OwnPeople.equipment.getTrousers() != null) {
            g.drawImage(OwnPeople.equipment.getTrousers().getImage(), 10, 190, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 10, 190, 50, 50, this);
            g.drawString(goods_name[3], 15, 210);
        }
        if (OwnPeople.equipment.getWeapons() != null) {
            g.drawImage(OwnPeople.equipment.getWeapons().getImage(), 10, 250, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 10, 250, 50, 50, this);
            g.drawString(goods_name[4], 15, 280);
        }
        if (OwnPeople.equipment.getShoes() != null) {
            g.drawImage(OwnPeople.equipment.getShoes().getImage(), 250, 10, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 250, 10, 50, 50, this);
            g.drawString(goods_name[5], 255, 40);
        }
        if (OwnPeople.equipment.getRings1() != null) {
            g.drawImage(OwnPeople.equipment.getRings1().getImage(), 250, 70, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 250, 70, 50, 50, this);
            g.drawString(goods_name[6], 255, 100);
        }
        if (OwnPeople.equipment.getRings2() != null) {
            g.drawImage(OwnPeople.equipment.getRings2().getImage(), 250, 130, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 250, 130, 50, 50, this);
            g.drawString(goods_name[6], 255, 160);
        }
        if (OwnPeople.equipment.getGloves() != null) {
            g.drawImage(OwnPeople.equipment.getGloves().getImage(), 250, 190, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 250, 190, 50, 50, this);
            g.drawString(goods_name[7], 255, 210);
        }
        if (OwnPeople.equipment.getCapes() != null) {
            g.drawImage(OwnPeople.equipment.getCapes().getImage(), 250, 250, 50, 50, this);
        } else {
            g.drawImage(lock_equip, 250, 250, 50, 50, this);
            g.drawString(goods_name[8], 255, 280);
        }

        g.setColor(new Color(63, 88, 235));
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("生 命 值:", 330, 35);
        g.drawString("魔 力 值:", 330, 65);
        g.drawString("物理伤害:", 330, 95);
        g.drawString("法术伤害:", 330, 125);
        g.drawString("物理防御:", 330, 155);
        g.drawString("法术防御:", 330, 185);
        g.drawString("暴    击:", 330, 215);
        g.drawString("命 中 率:", 330, 245);
        g.drawString("闪 避 率:", 330, 275);
        g.drawString("行动速度:", 330, 305);

        OwnPeople o = GameController.own;
        Attribute a = o.getAttribute();
        g.setColor(new Color(214, 232, 157));
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString(o.getBlood() + "/" + a.getHP(), 445, 35);
        g.drawString(o.getMagic() + "/" + a.getMP(), 445, 65);
        g.drawString(a.getPhy_attack() + "", 445, 95);
        g.drawString(a.getMag_attack() + "", 445, 125);
        g.drawString(a.getPhy_defense() + "", 445, 155);
        g.drawString(a.getMag_defense() + "", 445, 185);
        g.drawString(a.getBlowto(), 445, 215);
        g.drawString(a.getShootingto(), 445, 245);
        g.drawString(a.getDodgeto(), 445, 275);
        g.drawString(a.getSpeed_action() + "", 445, 305);

        int x3 = 30;
        int y3 = 10;
        g.setColor(new Color(136, 85, 153));
        g.setFont(new Font("宋体", Font.BOLD, 26));
        g.drawString("力量:", x3 + 40, 335 + y3);
        g.drawString("魔力:", x3 + 185, 335 + y3);
        g.drawString("技巧:", x3 + 330, 335 + y3);
        g.drawString("速度:", x3 + 40, 365 + y3);
        g.drawString("体质:", x3 + 185, 365 + y3);
        g.drawString("护甲:", x3 + 330, 365 + y3);
        g.drawString("抗性:", x3 + 40, 395 + y3);

        g.setColor(new Color(208, 70, 30));
        g.setFont(new Font("宋体", x3 + Font.BOLD, 26));
        g.drawString(a.getPower() + "", x3 + 110, 335 + y3);
        g.drawString(a.getMagic() + "", x3 + 255, 335 + y3);
        g.drawString(a.getSkill() + "", x3 + 405, 335 + y3);
        g.drawString(a.getSpeed() + "", x3 + 110, 365 + y3);
        g.drawString(a.getPhysical() + "", x3 + 255, 365 + y3);
        g.drawString(a.getPhy_defense() + "", x3 + 405, 365 + y3);
        g.drawString(a.getMag_defense() + "", x3 + 110, 395 + y3);

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

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int j = -1;
        for (int i = 0; i < 6; i++) {
            if (x >= Lo_x_1 && x <= Lo_x_1 + size1 && y <= Lo_y_1 + size1 * i + size2 && y >= Lo_y_1 + size1 * i) {
                j = i;
            }
        }
        if (j == -1) {
            for (int i = 0; i < 6; i++) {
                if (x >= Lo_x_2 && x <= Lo_x_2 + size1 && y <= Lo_y_2 + size1 * i + size2 && y >= Lo_y_2 + size1 * i) {
                    j = i + 5;
                }
            }
        }
        if (j != -1) {
            Goods good = OwnPeople.equipment.getEquip(j);
            if (good != null) {
                JPopupMenu jp = new JPopupMenu();
                JMenuItem jm1 = new JMenuItem("卸下");
                jm1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        OwnPeople.Sub_Equip(good);
                        MesPanel.messages.add(new Message(-3, "装备成功卸下"));
                        if (BackPack.frame != null) {
                            new PackPanel(0);
                        }
                        new InformationP(0);
                        OwnPeople.save_goods();
                    }
                });
                jp.add(jm1);
                jp.show(this, e.getX() - 30, e.getY() - 20);
            }
        }
    }

    private void open_back(int x, int y) {

        if (frame != null) {
            frame.dispose();
            frame = null;
        }
        frame = new JFrame();
        frame.setLocation(x, y);
        frame.setSize(400, 650);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);
        AWTUtilities.setWindowOpaque(frame, false);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.add(new Goods_infor());
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);// 30, 550, 260, 35
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int j = -1;
        for (int i = 0; i < 6; i++) {
            if (x >= Lo_x_1 && x <= Lo_x_1 + size1 && y <= Lo_y_1 + size1 * i + size2 && y >= Lo_y_1 + size1 * i) {
                j = i;
            }
        }
        if (j == -1) {
            for (int i = 0; i < 6; i++) {
                if (x >= Lo_x_2 && x <= Lo_x_2 + size1 && y <= Lo_y_2 + size1 * i + size2 && y >= Lo_y_2 + size1 * i) {
                    j = i + 5;
                }
            }
        }
        Goods good = OwnPeople.equipment.getEquip(j);
        if (j != -1 && good != null) {
            int sy = 50;
            int sx = 50;
            if (frame != null) {
                Goods_infor.goods = good;
                x += Information.frame.getX() + sx;
                y += Information.frame.getY() + sy;
                if (y > Screen_height - 700)
                    y = Screen_height - 700;
                if (x > Screen_width - 450)
                    x -= 450;
                frame.setLocation(x, y);
            } else {
                Goods_infor.goods = good;
                x += Information.frame.getX() + sx;
                y += Information.frame.getY() + sy;
                if (y > Screen_height - 700)
                    y = Screen_height - 700;
                if (x > Screen_width - 450)
                    x -= 450;
                open_back(x, y);
            }
        } else {
            if (frame != null) {
                if (frame != null) {
                    frame.dispose();
                    frame = null;
                }
            }
        }
    }
}