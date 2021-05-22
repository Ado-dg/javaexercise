package main.java;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import main.java.data.CsvWeaponsRepository;
import main.java.data.PostgreSQLWeaponsRepository;
import main.java.model.Dungeon;
import main.java.model.Player;
import main.java.model.Weapon;
import main.java.model.weapons.BronzeSword;
import main.java.util.JAXBPostgresConfig;

public class Application {
	
	private Scanner reader;
	private String input;
	private Player player;
	private PostgreSQLWeaponsRepository postgresRepo;
	private CsvWeaponsRepository csvRepo;
	
	public Application(Scanner in) {
		this.input = null;
		this.reader = in;
		this.player = new Player(50, new BronzeSword());
		this.postgresRepo = new PostgreSQLWeaponsRepository();
		this.csvRepo = new CsvWeaponsRepository();
	}
	
	
	 /**
	 * @param args
	 */
	public void run(String[] args) {
		/*System.out.println("Starting CSV to Postgress migration..." + "\n" + "Postgres table before migration:");
		for(Weapon weapon : postgresRepo.getWeapons()) {
			System.out.println(weapon.toString());
		}
		System.out.println("CSV file:");
		for(Weapon weapon : csvRepo.getWeapons()) {
			System.out.println(weapon.toString());
		}
		System.out.println("Migrating CSV to Postgress...");
		for(Weapon weapon : csvRepo.getWeapons()) {
			postgresRepo.addWeapon(weapon);
		}*//*
		System.out.println("Postgress table after migration:");
		for(Weapon weapon : postgresRepo.getWeapons()) {
			System.out.println(weapon.toString());
		}
		System.out.println("Double damage!");
		postgresRepo.updateWeaponDamage(2);*//*
		System.out.println("Updated postgress table:");
		for(Weapon weapon : postgresRepo.getWeapons()) {
			System.out.println(weapon.toString());
		}
		System.out.println("Spear is too OP, delete it!");
		postgresRepo.deleteWeapon(postgresRepo.getWeaponByName("Spear"));
		System.out.println("Updated postgress table:");
		for(Weapon weapon : postgresRepo.getWeapons()) {
			System.out.println(weapon.toString());
		}*/
			System.out.println("Starting...");
			/*JAXBPostgresConfig config = new JAXBPostgresConfig("jdbc:postgresql://localhost/postgres", "user", "user");
			JAXBContext context = JAXBContext.newInstance(JAXBPostgresConfig.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(config, new File("config.xml"));
			Unmarshaller unMarshaller = context.createUnmarshaller();
			Object unMarshalled = unMarshaller.unmarshal(new File("config.xml"));*/
			for(Weapon weapon : postgresRepo.getWeapons()) {
				System.out.println(weapon.toString());
			}
			/*try {
				System.out.println(JAXBPostgresConfig.getConfig());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			System.out.println("Stopping...");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		List<Weapon> weapons = Weapon.getWeapons();
		for (Weapon weapon : weapons) {
			System.out.println(weapon.toString());
		}
		/*
		this.input = welcome();
		if (this.input.contains("1")) {
			Dungeon dungeon = new Dungeon(player);
			this.input = loadDungeon(dungeon);
			if (this.input.contains("1")) {
			 fightGoblin(dungeon);
			} else {
				this.run(args);
				return;
			}
		}
		System.out.println("Goodbye!");
		reader.close();
		*/
	 }

	
	private void fightGoblin(Dungeon dungeon) {
		while(dungeon.getEnemy().getHealth() > 0) {
			attackEnemy(dungeon);
		}
		int xp = dungeon.getEnemy().dropXp();
		this.player.addXp(xp);
		System.out.println("The " + dungeon.getEnemy().getClass().getSimpleName() + " has died and dropped " + xp + " XP");
	}


	private void attackEnemy(Dungeon dungeon) {
		int damage = player.getWeapon().damage();
		dungeon.getEnemy().getDamage(damage);
		System.out.println("You hit the " + dungeon.getEnemy().getClass().getSimpleName() + " for " + damage + " damage!");
		System.out.println(dungeon.getEnemy().getClass().getSimpleName() + " has " + dungeon.getEnemy().getHealth() + " HP remaining...");

	}


	/**
	 * @return
	 */
	private String loadDungeon(Dungeon dungeon) {
		System.out.println("There is 1 " + dungeon.getEnemy().getClass().getSimpleName() + " in front of you with " + dungeon.getEnemy().getHealth() + " HP" + "\n" + "What do you do?");
		System.out.println("1) Hit with " + this.player.getWeapon().getClass().getSimpleName() + " (" + player.getWeapon().getMinDmg() + "-" + player.getWeapon().getMaxDmg() + " DMG)" + "\n" + "2) Go back");
		return reader.nextLine();
	}
	
	/**
	 * @return
	 */
	private String welcome() {
		System.out.println("Welcome to the dungeon! Choose your next action:");
		System.out.println("1) Enter the dungeon" + "\n" + "2) Leave the dungeon");
		return reader.nextLine();
	}
}
