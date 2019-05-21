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
import Client.ClientStart;
import Panel.PackPanel;

public class OwnPeople implements Runnable {

    public static final int width = 100;// 人物宽度
    public static final int height = 150;// 人物高度
    // public static final int minmovewidth = 9;
    // public static final int minmoveheight = 9;
    // public static final int maxmovewidth = 100;
    // public static final int maxmoveheight = 61;
    public static final int minmovewidth = 1;// 移动范围
    public static final int minmoveheight = 1;// 移动范围
    public static final int maxmovewidth = 115;// 移动范围
    public static final int maxmoveheight = 73;// 移动范围
    private static final int scope = 10;// 攻击距离==
    public static List<Goods> own_goods = new ArrayList<Goods>();// 个人物品
    public static Equipment equipment = new Equipment();
    public static StateController stateController = new StateController();
    int smap_x = 937;// 小地图位置
    int smap_y = 20;// 小地图位置
    int paint_x = 0;
    int paint_y = 0;
    List<HurtBlood> hurtBloods;// 伤害提示
    String own_img_string = "resouce/image/specials/special2/special";// 人物图片
    //    String own_weapon_string = "resouce/image/goods/weapons/weapon3/weapon";// 人物武器图片
    String own_weapon_special_string = "resouce/image/specials/special4/special";// 人物武器图片
    boolean weapon_state = false;// 物理攻击CD
    boolean magic_state = false;// 魔法攻击CD
    int sleep_time = 100;// 人物更新间隔
    String skill0 = "resouce/image/specials/special4/skill1.png";// 人物攻击小图片
    String skill1 = "resouce/image/specials/special3/skill1.png";// 人物技能小图片
    int x1 = 176;
    int y1 = 90;
    int y2 = 116;
    private int x;// 当前位置
    private int y;
    private int imgId;
    private String name;
    private int speed;// 移动速度==
    private int money;// 钱
    private int hurt = 5;// 物理伤害==
    private int magic_hurt = 60;// 魔法伤害==
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
    private Attribute attribute;

    public OwnPeople(){

    }

    public OwnPeople(int x, int y, int people, int imgId, String name, int money, Attribute attribute) {
        super();
        this.hurtBloods = new ArrayList<HurtBlood>();
        this.x = x;
        this.y = y;
        this.imgId = imgId;
        this.people = people;
        this.name = name;
        this.money = money;
        setAttribute(attribute);
    }

    public static void save_goods() {
        StringBuffer s = new StringBuffer();
        for (Goods good : own_goods) {
            int type = good.getType();
            String t = type < 10 ? "0" + type : "" + type;
            int id = good.getId();
            String i = id < 10 ? "0" + id : "" + id;
            int nums = good.getNums();
            String n = nums < 10 ? "0" + nums : "" + nums;
            s.append(t + i + n);
        }
        s.append(equipment.Equipment_info());
        ClientStart.OutStreamAll(GameController.own_cilent_id + ":0603:" + s);
    }

    public static boolean Add_Equip(Goods good) {
        boolean TF = false;
        int type = good.getType();
        switch (type) {
            case 0:
                TF = OwnPeople.equipment.setHead(good);
                addAttri(good);
                break;
            case 1:
                TF = OwnPeople.equipment.setNeck(good);
                OwnPeople.equipment.setNeck(good);
                addAttri(good);
                break;
            case 2:
                TF = OwnPeople.equipment.setJacket(good);
                addAttri(good);
                break;
            case 3:
                TF = OwnPeople.equipment.setTrousers(good);
                addAttri(good);
                break;
            case 4:
                TF = OwnPeople.equipment.setWeapons(good);
                addAttri(good);
                break;
            case 5:
                TF = OwnPeople.equipment.setShoes(good);
                addAttri(good);
                break;
            case 6:
                TF = OwnPeople.equipment.setRing(good);
                addAttri(good);
                break;
            case 7:
                TF = OwnPeople.equipment.setGloves(good);
                addAttri(good);
                break;
            case 8:
                TF = OwnPeople.equipment.setCapes(good);
                addAttri(good);
                break;
            case 9:
                GameController.own.addmagic(good);
                break;
            case 10:
                GameController.own.addblood(good);
                break;
            default:
                break;
        }
        return TF;
    }

    public static void addAttri(Goods good) {
        try {
            Attribute a = GameController.own.getAttribute();
            a.setArmor(good.getArmor());
            a.setMagic(good.getMagic());
            a.setPhysical(good.getPhysical());
            a.setPower(good.getPower());
            a.setResistance(good.getResistance());
            a.setSkill(good.getSkill());
            a.setSpeed(good.getSpeed());
        } catch (Exception e) {
            System.out.println(good.toString());
        }
    }

    public static void Sub_Equip(Goods good) {
        int type = good.getType();
        switch (type) {
            case 0:
                OwnPeople.equipment.putHead();
                subAttri(good);
                break;
            case 1:
                OwnPeople.equipment.putNeck();
                subAttri(good);
                break;
            case 2:
                OwnPeople.equipment.putJacket();
                subAttri(good);
                break;
            case 3:
                OwnPeople.equipment.putTrousers();
                subAttri(good);
                break;
            case 4:
                OwnPeople.equipment.putWeapons();
                subAttri(good);
                break;
            case 5:
                OwnPeople.equipment.putShoes();
                subAttri(good);
                break;
            case 6:
                OwnPeople.equipment.putRings1();
                subAttri(good);
                break;
            case 7:
                OwnPeople.equipment.putRings2();
                subAttri(good);
                break;
            case 8:
                OwnPeople.equipment.putGloves();
                subAttri(good);
                break;
            case 9:
                OwnPeople.equipment.putCapes();
                subAttri(good);
                break;
            default:
                break;
        }
    }

    public static void subAttri(Goods good) {
        Attribute a = GameController.own.getAttribute();
        a.setArmor(-1 * good.getArmor());
        a.setMagic(-1 * good.getMagic());
        a.setPhysical(-1 * good.getPhysical());
        a.setPower(-1 * good.getPower());
        a.setResistance(-1 * good.getResistance());
        a.setSkill(-1 * good.getSkill());
        a.setSpeed(-1 * good.getSpeed());
        if (GameController.own.getBlood() > GameController.own.getFull_blood())
            GameController.own.setBlood(GameController.own.getFull_blood());
        if (GameController.own.getMagic() > GameController.own.getFull_magic())
            GameController.own.setMagic(GameController.own.getFull_magic());
    }

    public static void addGoods(Goods goods) {
        int a = goods.getType();
        int b = goods.getId();
        int tf = 0;
        for (Goods g : own_goods) {
            int a1 = g.getType();
            int b1 = g.getId();
            if (a == a1 && b == b1) {
                g.addNums();
                tf = 1;
            }
        }
        if (tf == 0) {
            own_goods.add(goods);
        }
    }

    @Override
    public String toString() {
        return "OwnPeople [x=" + x + ", y=" + y + ", speed=" + speed + ", hurt=" + hurt + ", state=" + state
                + ", blood=" + blood + ", alive=" + alive + ", direct=" + direct + ", people=" + people
                + ", weapon_state=" + weapon_state + "]";
    }

    public void Draw(Graphics g, int x, int y, ImageObserver o) {

        g.drawImage(getOwn_img(), x, y, width, height, o);
        stateController.Draw(g, x, y, o);
        g.drawImage(getSkill0(), 950, 680, 50, 50, o);
        g.drawImage(getSkill1(), 1050, 680, 50, 50, o);
        if (magic_state) {
            int q = 8 - own_magic_state;
            int a = q / 10;
            int b = q % 10;
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体", Font.BOLD, 20));
            g.drawString(a + "." + b, 1061, 715);
        }

        if (own_weapon_special_state != 0) {
            int q = 6 - own_weapon_special_state;
            int a = q / 10;
            int b = q % 10;
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体", Font.BOLD, 20));
            g.drawString(a + "." + b, 961, 715);
            g.drawImage(getOwn_weapon_special(), x - 100, y - 100, 300, 300, o);
        }

        if (state == 1) {
            int rest = (blood * 140 / full_blood);
            if (rest != 100) {
                g.setColor(Color.GRAY);
                g.fill3DRect(x1 + rest, y1, 140 - rest, 25, true);
            }
            g.setColor(Color.GREEN);
            g.fill3DRect(x1, y1, rest, 25, true);

            rest = magic * 140 / full_magic;
            if (rest != 100) {
                g.setColor(Color.GRAY);
                g.fill3DRect(x1 + rest, y2, 140 - rest, 25, true);
            }
            g.setColor(Color.BLUE);
            g.fill3DRect(x1, y2, rest, 25, true);
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体", Font.BOLD, 15));
            g.drawString(name, x + 20, y - 8);
            g.drawString(blood + "/" + full_blood, x1 + 30, y1 + 19);
            g.drawString(magic + "/" + full_magic, x1 + 30, y2 + 19);
            g.setColor(Color.RED);
            g.setFont(new Font("宋体", Font.BOLD, 30));
            for (int i = 0; i < this.hurtBloods.size(); i++) {
                this.hurtBloods.get(i).Draw(g, x, y, o);
                if (this.hurtBloods.get(i).getTime() == 0) {
                    hurtBloods.remove(i);
                    i--;
                }

            }
            g.setColor(Color.green);
            g.fillOval(smap_x + this.x * 2, smap_y + this.y * 2, 20, 20);

        }
        // if (move_state > 4)
        // move_state = 0;
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

    public int getPaint_x() {
        int paint = 0;
        if (move == 0 && paint_x < 4) {
            ++paint_x;
            if (direct == 1) {
                paint = (x - speed) * 20 + 5 * paint_x * speed;
            } else if (direct == 3) {
                paint = (x + speed) * 20 - 5 * paint_x * speed;
            } else {
                paint = x * 20;
            }
        } else {
            paint = x * 20;
        }
        return paint;
    }

//    public Image getOwn_weapon() {
//        return new ImageIcon(own_weapon_string + own_weapon_state + ".png").getImage();
//    }

    public int getPaint_y() {
        int paint = 0;
        if (move == 0 && paint_y < 4) {
            ++paint_y;
            if (direct == 2) {
                paint = (y - speed) * 20 + 5 * paint_y * speed;
            } else if (direct == 0) {
                paint = (y + speed) * 20 - 5 * paint_y * speed;
            } else {
                paint = y * 20;
            }
        } else {
            paint = y * 20;
        }
        return paint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addblood(Goods good) {
        int blood = good.getEffect();
        int a = this.blood + blood;
        this.blood = a > this.full_blood ? this.full_blood : a;
        this.hurtBloods.add(new HurtBlood(blood, 10, 7, ""));
    }

    public void addmagic(Goods good) {
        int magic = good.getEffect();
        int a = this.magic + magic;
        this.magic = a > this.full_magic ? this.full_magic : a;
        this.hurtBloods.add(new HurtBlood(magic, 10, 6, ""));
    }

    public Image getOwn_img() {
        if (state == -1) {
            return new ImageIcon(own_img_string + own_img_state + ".png").getImage();
        } else {
            return new ImageIcon(own_img_string + direct + "/people" + run_state + own_img_state + ".png").getImage();
        }
    }

    public Image getOwn_weapon_special() {
        return new ImageIcon(own_weapon_special_string + own_weapon_special_state + ".png").getImage();
    }

    public Image getSkill0() {
        return new ImageIcon(skill0).getImage();
    }

    public Image getSkill1() {
        return new ImageIcon(skill1).getImage();
    }

    public synchronized int getSpeed() {
        return speed;
    }

    public synchronized void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRun_state() {
        return run_state;
    }

    public void setRun_state(int run_state) {
        this.run_state = run_state;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        ClientStart.OutStreamAll(GameController.own_cilent_id + ":0602:" + this.money);
    }

    public void addMoney(int money) {
        this.money += money;
        ClientStart.OutStreamAll(GameController.own_cilent_id + ":0602:" + this.money);
    }

    public synchronized int getState() {
        return state;
    }

    public synchronized int getBlood() {
        return blood;
    }

    public synchronized void setBlood(int blood) {
        this.blood = blood;
    }

    public synchronized int getFull_blood() {
        return full_blood;
    }

    public synchronized void setFull_blood(int full_blood) {
        this.full_blood = full_blood;
    }

    public int getFull_magic() {
        return full_magic;
    }

    public void setFull_magic(int full_magic) {
        this.full_magic = full_magic;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public synchronized int getPeople() {
        return people;
    }

    public synchronized void setPeople(int people) {
        this.people = people;
    }
    //

    public synchronized boolean isWeapon_state() {
        return weapon_state;
    }

    public synchronized void setWeapon_state(boolean weapon_state) {
        this.weapon_state = weapon_state;
    }

    public synchronized int getDirect() {
        return direct;
    }

    public synchronized void setDirect(int direct) {
        this.direct = direct;
    }

    public synchronized boolean getAlive() {
        return alive;
    }

    public synchronized void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void addHurtBloods(int blood, int time, int type, String info) {
        this.hurtBloods.add(new HurtBlood(blood, time, type, info));
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
        this.blood = attribute.getHP();
        this.full_blood = this.blood;
        this.hurt = attribute.getPhy_attack();
        this.magic_hurt = attribute.getMag_attack();
        this.speed = attribute.getSpeed_action();
        this.magic = attribute.getMagic();
        this.full_magic = this.magic;
    }

    public int getPAttack(int attack) {
        return attack * 300 / (300 + attribute.getPhy_defense());
    }

    public int getMAttack(int attack) {
        return attack * 300 / (300 + attribute.getMag_defense());
    }

    public synchronized void move(int i) {
        switch (i) {
            case 0:
                moveUp();
                break;
            case 1:
                moveLeft();
                break;
            case 2:
                moveDown();
                break;
            case 3:
                moveRight();
                break;
        }
    }

    public void moveUp() {
        if (move == 1 && y_collision(y - speed, people)) {
            direct = 0;
            run_state = 2;
            move = 0;
        }
    }

    public void moveDown() {
        if (move == 1 && y_collision(y + speed, people)) {
            direct = 2;
            run_state = 2;
            move = 0;
        }
    }

    public void moveRight() {
        if (move == 1 && x_collision(x + speed, people)) {
            direct = 1;
            run_state = 2;
            move = 0;
        }
    }

    public void moveLeft() {
        if (move == 1 && x_collision(x - speed, people)) {
            direct = 3;
            run_state = 2;
            move = 0;
        }
    }

    public void pullGoods() {
        ClientStart.OutStreamAll(people + ":0101:3:0");
        for (int i = 0; i < GameController.map_goods.size(); i++) {
            Goods good = GameController.map_goods.get(i);
            if (Math.pow(x - good.getX() + 1, 2) + Math.pow(y - good.getY() + 2, 2) <= 20) {
                GameController.map_goods.remove(i);
                addGoods(good);
                if (BackPack.frame != null) {
                    new PackPanel(0);
                }
            }
        }
        save_goods();
    }


    public boolean x_collision(int x, int people) {
        if (GameMap.gamemap.map_collision(GameController.leave-1, x, this.y)) {
            this.x = x;
            ClientStart.OutStreamAll(people + ":0101:0:" + x);
            return true;
        } else {
            return false;
        }
    }

    public boolean y_collision(int y, int people) {
        if (GameMap.gamemap.map_collision(GameController.leave-1, this.x, y)) {
            this.y = y;
            ClientStart.OutStreamAll(people + ":0101:1:" + y);
            return true;
        } else {
            return false;
        }
    }

    public synchronized void people_attack() {
        if (!weapon_state) {
            new Music(1, 2);
            weapon_state = true;
            own_weapon_special_state = 1;
            skill0 = "resouce/image/specials/special4/skill2.png";
            GameController.hurtController.hurtEnemys(people, x, y, scope, hurt, 1);
            ClientStart.OutStreamAll(people + ":0101:2:0");
        }
    }

    public synchronized void Magic_attack() {
        if (!magic_state) {
            if (magic - 3 > 0) {
                magic -= 3;
                new Music(1, 3);
                magic_state = true;
                skill1 = "resouce/image/specials/special3/skill2.png";
                Skills.add(new Special(1, x, y, 3, direct, magic_hurt, 15));
                ClientStart.OutStreamAll(people + ":0101:3:0");
            }
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
                    if (move == -1) {
                        move = 1;
                        run_state = 1;
                        paint_x = 0;
                        paint_y = 0;
                    } else if (move == 0)
                        move = -1;
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