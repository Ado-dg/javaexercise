package main.java.model;

import java.util.List;
import java.util.Random;

import main.java.data.CsvWeaponsRepository;

public class Weapon {
	Random random = new Random();
	private String name;
	private int minDmg;
	private int maxDmg;
	private static CsvWeaponsRepository repo = new CsvWeaponsRepository();
	
	public Weapon(String name, int minDmg, int maxDmg) {
		this.name = name;
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
	}

	public String getName() {
		return name;
	}

	public int getMinDmg() {
		return minDmg;
	}

	public int getMaxDmg() {
		return maxDmg;
	}
	
	public int damage() {
		return random.nextInt((maxDmg - minDmg) + 1) + minDmg;
	}
	
	public static List<Weapon> getWeapons() {
		return repo.getWeapons();
	}

	@Override
	public String toString() {
		return "Weapon [name=" + name + ", minDmg=" + minDmg + ", maxDmg=" + maxDmg + "]";
	}
	
	
	
	
}
