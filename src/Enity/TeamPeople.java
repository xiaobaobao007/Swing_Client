package Enity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Controller.BackPack;
import Controller.GameController;
import Controller.Music;
import Controller.StateController;
import Panel.PackPanel;

public class TeamPeople implements Runnable {

    public static final int width = 100;// 人物宽度
    public static final int height = 150;// 人物高度
    private static final int scope = 10;// 攻击距离==
    private static StateController stateController = new StateController();
    private int smap_x = 937;// 小地图位置
    private int smap_y = 20;// 小地图位置
    private List<HurtBlood> hurtBloods;// 伤害提示
    private String own_img_string = "resouce/image/specials/special2/special";// 人物图片
    //    String own_weapon_string = "resouce/image/goods/weapons/weapon3/weapon";// 人物武器图片
    private String own_weapon_special_string = "resouce/image/specials/special4/special";// 人物武器图片
    private boolean weapon_state = false;// 物理攻击CD
    private boolean magic_state = false;// 魔法攻击CD
    private String skill0 = "resouce/image/specials/special4/skill1.png";// 人物攻击小图片
    private String skill1 = "resouce/image/specials/special3/skill1.png";// 人物技能小图片
    private int x;// 当前位置
    private int y;
    private int imgId;
    private String name;
    private int state = -1;// 人物状态
    private int full_blood;// 满血==
    private int blood;// 当前血量
    private int full_magic;// 满蓝==
    private int magic;// 当前蓝量
    private boolean alive = true;// 存活
    private int direct = 1;// 方向
    private int people;// 人物id
    private int run_state = 1;// 奔跑状态
    private int move = 1;// 移动状态
    private int own_img_state = 1;// 人物图片状态
    private int own_weapon_state = 1;// 武器图片状态
    private int own_weapon_special_state = 0;// 武器特效状态
    private int own_magic_state = 1;// 魔法图片状态
    private Thread thread;

    public TeamPeople(int magic, int blood, int x, int y, int people, int imgId, String name) {
        this.hurtBloods = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.imgId = imgId;
        this.name = name;
        this.full_blood = blood;
        this.blood = blood;
        this.full_magic = magic;
        this.magic = magic;
        this.people = people;
    }

    public void Draw(Graphics g, int x, int y, ImageObserver o) {

        x += this.x * 20;
        y += this.y * 20;
        g.drawImage(getOwn_img(), x, y, width, height, o);
        if (own_weapon_special_state != 0) {
            g.drawImage(getOwn_weapon_special(), x - 100, y - 100, 300, 300, o);
        }
        if (state == 1) {
            stateController.Draw(g, x, y, o);
            if (own_weapon_state != 0) {
                int q = 7 - own_weapon_state;
                int a = q / 10;
                int b = q % 10;
                g.setColor(Color.WHITE);
                g.setFont(new Font("宋体", Font.BOLD, 20));
                g.drawString(a + "." + b, 961, 715);
            }
            int rest = blood * 100 / full_blood;
            if (rest != 100) {
                g.setColor(Color.GRAY);
                g.fill3DRect(rest + x, -20 + y, 100 - rest, 15, true);
            }
            g.setColor(Color.RED);
            g.fill3DRect(x, -20 + y, rest, 15, true);

            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体", Font.BOLD, 15));
            g.drawString(blood + "/" + full_blood, x + 20, y - 8);
            g.drawString(name, x + 20, y - 28);
            g.setColor(Color.RED);
            g.setFont(new Font("宋体", Font.BOLD, 30));

            for (int i = 0; i < this.hurtBloods.size(); i++) {
                this.hurtBloods.get(i).Draw(g, x, y, o);
                if (this.hurtBloods.get(i).getTime() == 0) {
                    hurtBloods.remove(i);
                    i--;
                }
            }
            g.setColor(Color.BLUE);
            g.fillOval(smap_x + this.x * 2, smap_y + this.y * 2, 20, 20);
        }
    }

    public void RestPeople() {
        own_img_string = "resouce/image/specials/special2/special";
        state = -1;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized boolean getMove() {
        return move == 0;
    }

    public synchronized int getY() {
        return y;
    }

    public synchronized void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    private Image getOwn_img() {
        if (state == -1) {
            return new ImageIcon(own_img_string + own_img_state + ".png").getImage();
        } else {
            return new ImageIcon(own_img_string + direct + "/people" + run_state + own_img_state + ".png").getImage();
        }
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

//    public Image getOwn_weapon() {
//        return new ImageIcon(own_weapon_string + own_weapon_state + ".png").getImage();
//    }

    private Image getOwn_weapon_special() {
        return new ImageIcon(own_weapon_special_string + own_weapon_special_state + ".png").getImage();
    }

    public void pullGoods() {
        for (int i = 0; i < GameController.map_goods.size(); i++) {
            Goods good = GameController.map_goods.get(i);
            if (Math.pow(x - good.getX() + 1, 2) + Math.pow(y - good.getY() + 2, 2) <= 20) {
                GameController.map_goods.remove(i);
                if (BackPack.frame != null) {
                    new PackPanel(0);
                }
            }
        }
    }

    public synchronized void people_attack() {
        if (!weapon_state) {
            new Music(1, 2);
            weapon_state = true;
            own_weapon_special_state = 1;
        }
    }

    public synchronized void Magic_attack() {
        if (!magic_state) {
            new Music(1, 3);
            magic_state = true;
            Skills.add(new Special(1, x, y, 3, direct, 0, 15));
        }
    }

    @Override
    public void run() {
        int thread_num = 1;
        while (true) {
            thread_num++;
            if (thread_num == 17)
                thread_num = 1;
            if (alive) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (thread_num % 1 == 0) {

                    if (this.weapon_state) {
                        this.own_weapon_state++;
                        if (this.own_weapon_state == 5) {
                            this.own_weapon_state = 1;
                            this.weapon_state = false;
                        }
                    }
                    if (own_weapon_special_state != 0) {
                        this.own_weapon_special_state++;
                        if (this.own_weapon_special_state == 7) {
                            this.own_weapon_special_state = 0;
                            skill0 = "resouce/image/specials/special4/skill1.png";
                        }
                    }
                    if (this.magic_state) {
                        this.own_magic_state++;
                        if (this.own_magic_state == 8) {
                            this.own_magic_state = 1;
                            this.magic_state = false;
                            skill1 = "resouce/image/specials/special3/skill1.png";
                        }
                    }
                    if (state == -1) {
                        if (own_img_state++ > 6) {
                            own_img_state = -1;
                            own_img_string = "resouce/image/peoples/peoples" + imgId + "/people";
                            own_img_state = 1;
                            state = 1;
                        }
                    }
                }

                if (thread_num % 2 == 0) {// 200ms移动一次
                }

                if (thread_num % 4 == 0) {
                    if (!stateController.Frozen()) {
                        if (state == 1) {
                            this.own_img_state++;
                            if (this.own_img_state == 5)
                                this.own_img_state = 1;
                        }
                    }
                }

                // if (thread_num % 8 == 0) {
                //
                // }
                //
                // if (thread_num % 16 == 0) {
                //
                // }

            } else {

            }
        }
    }
}