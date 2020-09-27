package Enity;

public class Equipment {

	private Goods Head;//1.头部
	private Goods Neck;//2.脖子
	private Goods Jacket;//3.上衣
	private Goods Trousers;//4.裤子
	private Goods Weapons;//5.武器
	private Goods Shoes;//6.鞋子
	private Goods Rings1;//7.左手指环
	private Goods Rings2;//8.右手指环
	private Goods Gloves;//9.手套
	private Goods Capes;//10.披风

	Equipment() {
		super();
	}

	public Goods getEquip(int i) {
		Goods good = null;
		switch (i) {
			case 0:
				good = this.Head;
				break;
			case 1:
				good = this.Neck;
				break;
			case 2:
				good = this.Jacket;
				break;
			case 3:
				good = this.Trousers;
				break;
			case 4:
				good = this.Weapons;
				break;
			case 5:
				good = this.Shoes;
				break;
			case 6:
				good = this.Rings1;
				break;
			case 7:
				good = this.Rings2;
				break;
			case 8:
				good = this.Gloves;
				break;
			case 9:
				good = this.Capes;
				break;
			default:
				break;
		}
		return good;
	}

	public Goods getHead() {
		return Head;
	}

	boolean setHead(Goods head) {
		if (Head == null) {
			Head = head;
			return true;
		} else
			return false;
	}

	void putHead() {
		OwnPeople.addGoods(Head);
		Head = null;
	}

	public Goods getNeck() {
		return Neck;
	}

	boolean setNeck(Goods neck) {
		if (Neck == null) {
			Neck = neck;
			return true;
		} else
			return false;
	}

	void putNeck() {
		OwnPeople.addGoods(Neck);
		Neck = null;
	}

	public Goods getJacket() {
		return Jacket;
	}

	boolean setJacket(Goods jacket) {
		if (Jacket == null) {
			Jacket = jacket;
			return true;
		} else
			return false;
	}

	void putJacket() {
		OwnPeople.addGoods(Jacket);
		Jacket = null;
	}

	public Goods getTrousers() {
		return Trousers;
	}

	boolean setTrousers(Goods trousers) {
		if (Trousers == null) {
			Trousers = trousers;
			return true;
		} else
			return false;
	}

	void putTrousers() {
		OwnPeople.addGoods(Trousers);
		Trousers = null;
	}

	public Goods getWeapons() {
		return Weapons;
	}

	boolean setWeapons(Goods weapons) {
		if (Weapons == null) {
			Weapons = weapons;
			return true;
		} else
			return false;
	}

	void putWeapons() {
		OwnPeople.addGoods(Weapons);
		Weapons = null;
	}

	public Goods getShoes() {
		return Shoes;
	}

	boolean setShoes(Goods shoes) {
		if (Shoes == null) {
			Shoes = shoes;
			return true;
		} else
			return false;
	}

	void putShoes() {
		OwnPeople.addGoods(Shoes);
		Shoes = null;
	}

	boolean setRing(Goods ring) {
		if (setRings1(ring)) {
			return true;
		} else {
			return setRings2(ring);
		}
	}

	public Goods getRings1() {
		return Rings1;
	}

	private boolean setRings1(Goods rings1) {
		if (Rings1 == null) {
			Rings1 = rings1;
			return true;
		} else
			return false;
	}

	void putRings1() {
		OwnPeople.addGoods(Rings1);
		Rings1 = null;
	}

	public Goods getRings2() {
		return Rings2;
	}

	private boolean setRings2(Goods rings2) {
		if (Rings2 == null) {
			Rings2 = rings2;
			return true;
		} else
			return false;
	}

	void putRings2() {
		OwnPeople.addGoods(Rings2);
		Rings2 = null;
	}

	public Goods getGloves() {
		return Gloves;
	}

	boolean setGloves(Goods gloves) {
		if (Gloves == null) {
			Gloves = gloves;
			return true;
		} else
			return false;
	}

	void putGloves() {
		OwnPeople.addGoods(Gloves);
		Gloves = null;
	}

	public Goods getCapes() {
		return Capes;
	}

	boolean setCapes(Goods capes) {
		if (Capes == null) {
			Capes = capes;
			return true;
		} else
			return false;
	}

	void putCapes() {
		OwnPeople.addGoods(Capes);
		Capes = null;
	}

	private String ToString(Goods good) {
		int type = good.getType();
		String t = type < 10 ? "0" + type : "" + type;
		int id = good.getId();
		String i = id < 10 ? "0" + id : "" + id;
		return t + i + "00";
	}

	String Equipment_info() {
		StringBuilder info = new StringBuilder();
		if (Head != null) {
			info.append(ToString(Head));
		}
		if (Neck != null) {
			info.append(ToString(Neck));
		}
		if (Jacket != null) {
			info.append(ToString(Jacket));
		}
		if (Trousers != null) {
			info.append(ToString(Trousers));
		}
		if (Weapons != null) {
			info.append(ToString(Weapons));
		}
		if (Shoes != null) {
			info.append(ToString(Shoes));
		}
		if (Rings1 != null) {
			info.append(ToString(Rings1));
		}
		if (Rings2 != null) {
			info.append(ToString(Rings2));
		}
		if (Gloves != null) {
			info.append(ToString(Gloves));
		}
		if (Capes != null) {
			info.append(ToString(Capes));
		}
		return info.toString();
	}
}
