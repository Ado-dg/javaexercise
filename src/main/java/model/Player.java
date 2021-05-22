package main.java.model;


import main.java.data.PlayerRepository;

public class Player extends Targetable{
	private int health;
	private Weapon weapon;
	private int xp;
	private PlayerRepository repo;

	
	public Player(int health, Weapon weapon) {
		super(health, weapon, 0);
		repo = new PlayerRepository();
	}
	
	public int getXp() {
		return this.xp;
	}
	
	public void addXp(int xp) {
		this.xp = this.xp + xp;
		repo.saveXp(xp);
	}
	
}
