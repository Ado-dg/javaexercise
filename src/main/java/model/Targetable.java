package main.java.model;

public class Targetable {
	private int maxHealth;
	private int health;
	private Weapon weapon;
	private int baseXp;
	
	public Targetable(int health, Weapon weapon, int xp) {
		this.maxHealth = health;
		this.health = health;
		this.weapon = weapon;
		this.baseXp = xp;
	}

	public int getHealth() {
		return health;
	}

	public Weapon getWeapon() {
		return weapon;
	}
	
	public Targetable getDamage(int damage){
		if (damage > health) {
			health = 0;
		} else {
			health = health - damage;
		}
		return this;
	}
	
	public int dropXp() {
		return (Math.round(this.maxHealth / 5) + this.baseXp);
	}
}
