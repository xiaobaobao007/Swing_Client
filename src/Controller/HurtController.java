package Controller;

import Client.ClientStart;
import Enity.EnemyPeople;
import Enity.OwnPeople;
import Enity.Skills;

public class HurtController {

	public HurtController() {
		super();
	}

	// synchronized
	public synchronized void hurtEnemys(int id, int x, int y, int scope, int hurt, int type) {
		try {
			for (EnemyPeople one : GameController.Enemys) {

				if (square_mater(x, y, one.getX(), one.getY(), scope) && one.getAlive()) {
					if (type == 1) {
						hurt = one.getMAttack(hurt);
					} else {
						hurt = one.getPAttack(hurt);
					}
					if (hurt < 0) hurt = 0;
					if (type == 2)
						Skills.specials.remove(id);
					if (one.getAttribute().getShooting(GameController.own.getAttribute().getDodge())) {//怪物躲避
						one.addHurtBloods(0, 20, 4, "");
					} else {//没有躲避
						if (type == 2) {
							one.getStateController().Start_Frozen(3000);
							ClientStart.OutStreamAll(one.getPeople() + ":0303:" + 1 + ":" + 3000);
						}
						boolean blow = GameController.own.getAttribute().getBlow();
						if (blow) {
							hurt *= 2;
						}
						int blood = one.getBlood() - hurt;
						if (blood > 0) {
							ClientStart.OutStreamAll(one.getPeople() + ":0301:" + blood + ":" + id);
							one.setBlood(blood);
							if (blow) one.addHurtBloods(hurt, 20, 5, "");
							else one.addHurtBloods(hurt, 20, type, "");
						} else {
							Music.play(0, 1);
							ClientStart.OutStreamAll(one.getPeople() + ":0302:" + id);
							one.setAlive(false);
						}
					}
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("伤害数据占用，已恢复-hurt");
		}
	}

	public synchronized void hurtOwn(int x, int y, int scope, int hurt, EnemyPeople enemyPeople, int type) {

		OwnPeople own = GameController.own;
		if (square_mater(x, y, own.getX(), own.getY(), scope)) {
			if (type == 1) {
				Music.play(1, 1);
				hurt = own.getMAttack(hurt);
			} else {
				Music.play(1, 1);
				hurt = own.getPAttack(hurt);
			}
			if (hurt < 0) hurt = 0;
			enemyPeople.setWeapon_state(true);
			if (own.getAttribute().getShooting(enemyPeople.getAttribute().getDodge())) {//躲避
				own.addHurtBloods(0, 20, 4, "");
			} else {//没有躲避
				boolean blow = enemyPeople.getAttribute().getBlow();
				if (blow) {
					hurt *= 2;
				}
				int blood = own.getBlood() - hurt;
				if (blood > 0) {
					own.setBlood(blood);
					if (blow) own.addHurtBloods(hurt, 20, 5, "");
					else own.addHurtBloods(hurt, 20, 1, "");
				} else {
					own.setBlood(100);
				}
			}

		}
	}

	private boolean square_mater(int x, int y, int x1, int y1, int s) {
		return Math.pow((x - x1), 2) + Math.pow((y - y1), 2) <= Math.pow(s, 2);
	}
}
