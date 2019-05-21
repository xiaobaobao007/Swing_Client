package Enity;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Goods {

    public static String[][] equip = {
            {"head", "neck", "jacket", "trousers", "weapon", "shoes", "ring", "gloves", "capes", "magic", "blood"},
            {"头盔", "项链", "外套", "内衣", "武器", "鞋子", "戒指", "手套", "腰带", "回蓝", "回血"}};
    int state = 1;
    String img;
    private String name;
    private int leave;
    private int type;
    private int id;
    private int x;
    private int y;
    private String note;
    private int money;
    private int power;// 力量
    private int magic;// 魔力
    private int skill;// 技巧
    private int speed;// 速度
    private int physical;// 体质
    private int armor;// 护甲
    private int resistance;// 抗性
    private int effect;
    private int effect_leave;
    private int nums = 1;// 数量

    public Goods() {
        super();
    }

    public Goods(String name, int leave, int type, int id, int x, int y, int money, String note, int power, int magic,
                 int skill, int speed, int physical, int armor, int resistance, int effect, int effect_leave) {
        super();
        this.name = name;
        this.leave = leave;
        this.type = type;
        this.id = id;
        this.x = x;
        this.y = y;
        this.money = money;
        this.note = note;
        this.power = power;
        this.magic = magic;
        this.skill = skill;
        this.speed = speed;
        this.physical = physical;
        this.armor = armor;
        this.resistance = resistance;
        this.effect = effect;
        this.effect_leave = effect_leave;
        this.img = "resouce/image/goods/" + equip[0][type] + "/" + equip[0][type] + "" + id + ".png";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Image getImage() {
        return new ImageIcon(img).getImage();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public int getEffect_leave() {
        return effect_leave;
    }

    public void setEffect_leave(int effect_leave) {
        this.effect_leave = effect_leave;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public void addNums() {
        this.nums++;
    }

    public boolean subNums() {
        return --this.nums > 0;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", leave=" + leave +
                ", type=" + type +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", note='" + note + '\'' +
                ", money=" + money +
                ", power=" + power +
                ", magic=" + magic +
                ", skill=" + skill +
                ", speed=" + speed +
                ", physical=" + physical +
                ", armor=" + armor +
                ", resistance=" + resistance +
                ", effect=" + effect +
                ", effect_leave=" + effect_leave +
                ", nums=" + nums +
                ", state=" + state +
                ", img='" + img + '\'' +
                '}';
    }
}
