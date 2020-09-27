package Enity;

public class Attribute {

	private int power = 0;// 1.力量
	private int magic = 0;// 2.魔力
	private int skill = 0;// 3.技巧
	private int speed = 0;// 4.速度
	private int physical = 0;// 5.体质
	private int armor = 0;// 6.护甲
	private int resistance = 0;// 7.抗性

	private int con_phy_attack;// 1.物理伤害系数
	private int con_mag_attack;// 2.法术伤害系数
	private int con_MP;// 3.魔力值系数
	private int con_blow;// 4.暴击系数
	private int con_shooting;// 5.命中率系数
	private int con_dodge;// 6.闪避率系数
	private int con_speed_action;// 7.行动速度系数
	private int con_HP;// 8.生命系数
	private int con_phy_defense;// 9.物理防御系数
	private int con_mag_defense;// 10.法术防御系数

	private int phy_attack;// 物理伤害
	private int mag_attack;// 法术伤害
	private int MP;// 魔力值
	private int blow;// 暴击
	private int shooting;// 命中率
	private int dodge;// 闪避率
	private int speed_action;// 行动速度
	private int HP;// 生命
	private int phy_defense;// 物理防御
	private int mag_defense;// 法术防御

	public Attribute(int con_phy_attack, int con_mag_attack, int con_MP, int// 系数设定
																				 con_blow, int con_shooting, int con_dodge, int con_speed_action, int con_HP, int con_phy_defense,
					 int con_mag_defense, int power, int magic, int skill, int speed, int physical, int armor, int resistance) {// 属性设定
		super();
		this.con_phy_attack = con_phy_attack;
		this.con_mag_attack = con_mag_attack;
		this.con_MP = con_MP;
		this.con_blow = con_blow;
		this.con_shooting = con_shooting;
		this.con_dodge = con_dodge;
		this.con_speed_action = con_speed_action;
		this.con_HP = con_HP;
		this.con_phy_defense = con_phy_defense;
		this.con_mag_defense = con_mag_defense;
		setPower(power);
		setMagic(magic);
		setSkill(skill);
		setSpeed(speed);
		setPhysical(physical);
		setArmor(armor);
		setResistance(resistance);
	}

	@Override
	public String toString() {
		return "Attribute [phy_attack=" + phy_attack + ", mag_attack=" + mag_attack + ", MP=" + MP + ", blow=" + blow
					   + ", shooting=" + shooting + ", dodge=" + dodge + ", speed_action=" + speed_action + ", HP=" + HP
					   + ", phy_defense=" + phy_defense + ", mag_defense=" + mag_defense + "]";
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power += power;
		this.phy_attack = this.power * this.con_phy_attack;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic += magic;
		this.mag_attack = this.magic * this.con_mag_attack;
		this.MP = this.magic * this.con_MP;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill += skill;
		this.blow = this.skill * this.con_blow;
		this.shooting = this.skill * this.con_shooting;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed += speed;
		this.dodge = this.speed * this.con_dodge;
		this.speed_action = this.speed * this.con_speed_action;
	}

	public int getPhysical() {
		return physical;
	}

	public void setPhysical(int physical) {
		this.physical += physical;
		this.HP = this.physical * this.con_HP;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor += armor;
		this.phy_defense = this.armor * this.con_phy_defense;

	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance += resistance;
		this.mag_defense = this.resistance * this.con_mag_defense;
	}

	public int getCon_phy_attack() {
		return con_phy_attack;
	}

	public void setCon_phy_attack(int con_phy_attack) {
		this.con_phy_attack = con_phy_attack;
	}

	public int getCon_mag_attack() {
		return con_mag_attack;
	}

	public void setCon_mag_attack(int con_mag_attack) {
		this.con_mag_attack = con_mag_attack;
	}

	public int getCon_MP() {
		return con_MP;
	}

	public void setCon_MP(int con_MP) {
		this.con_MP = con_MP;
	}

	public int getCon_blow() {
		return con_blow;
	}

	public void setCon_blow(int con_blow) {
		this.con_blow = con_blow;
	}

	public int getCon_shooting() {
		return con_shooting;
	}

	public void setCon_shooting(int con_shooting) {
		this.con_shooting = con_shooting;
	}

	public int getCon_dodge() {
		return con_dodge;
	}

	public void setCon_dodge(int con_dodge) {
		this.con_dodge = con_dodge;
	}

	public int getCon_speed_action() {
		return con_speed_action;
	}

	public void setCon_speed_action(int con_speed_action) {
		this.con_speed_action = con_speed_action;
	}

	public int getCon_HP() {
		return con_HP;
	}

	public void setCon_HP(int con_HP) {
		this.con_HP = con_HP;
	}

	public int getCon_phy_defense() {
		return con_phy_defense;
	}

	public void setCon_phy_defense(int con_phy_defense) {
		this.con_phy_defense = con_phy_defense;
	}

	public int getCon_mag_defense() {
		return con_mag_defense;
	}

	public void setCon_mag_defense(int con_mag_defense) {
		this.con_mag_defense = con_mag_defense;
	}

	public int getPhy_attack() {
		return phy_attack;
	}

	public int getMag_attack() {
		return mag_attack;
	}

	public int getMP() {
		return MP;
	}

	public boolean getBlow() {
		return (int) (Math.random() * 1000) <= this.blow;
	}

	public String getBlowto() {
		return blow * 1.0 / 10 + "%";
	}

	public boolean getShooting(int x) {
		int y = this.shooting / (this.shooting + x) * 1000;
		return (int) (Math.random() * 1000) <= y;
	}

	public String getShootingto() {
		return shooting * 1.0 / 10 + "%";
	}

	public int getDodge() {
		return this.dodge;
	}

	public String getDodgeto() {
		return dodge * 1.0 / 10 + "%";
	}

	public int getSpeed_action() {
		return speed_action;
	}

	public int getHP() {
		return HP;
	}

	public int getPhy_defense() {
		return phy_defense;
	}

	public int getMag_defense() {
		return mag_defense;
	}

}
