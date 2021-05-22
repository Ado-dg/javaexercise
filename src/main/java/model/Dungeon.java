package main.java.model;

import java.util.Random;

import main.java.model.weapons.IronGreataxe;

public class Dungeon {
	private Random random = new Random();
	private Player player;
	private Targetable enemy;
	
	public Dungeon(Player player) {
		this.player = player;
		this.enemy = generateEnemy();
	}

	private Targetable generateEnemy() {
		int health = random.nextInt(20 - 5 + 1) + 5;
		return new Goblin(health, new IronGreataxe());
	}

	public Random getRandom() {
		return random;
	}

	public Player getPlayer() {
		return player;
	}

	public Targetable getEnemy() {
		return enemy;
	}
	
	
}
