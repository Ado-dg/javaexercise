package main.java.model;

public class Goblin extends Targetable{
	private int health;
	private Weapon weapon;
	
	public Goblin(int health, Weapon weapon) {
		super(health, weapon, 1);
	}

	

}
