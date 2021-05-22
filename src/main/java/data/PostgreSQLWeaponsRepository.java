package main.java.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.model.Weapon;
import main.java.util.exceptions.WeaponException;

public class PostgreSQLWeaponsRepository {
	private static Logger logger = LogManager.getLogger(PlayerRepository.class);
	
	private static final String SELECT = "SELECT * FROM weapons";
	private static final String SELECT_BY_NAME = "SELECT * FROM weapons WHERE name = ?";
	private static final String INSERT = "INSERT INTO weapons(name,min,max) values(?,?,?)";
	private static final String UPDATE = "UPDATE weapons SET min = min * ?, max = max * ?";
	private static final String DELETE = "DELETE FROM weapons WHERE name = ?";

	
	
	/**
	 * Method for retrieving all weapons from the Postgres Database
	 * 
	 * @return List<Weapon>
	 * @throws WeaponException
	 */
	public List<Weapon> getWeapons(){
		try(Connection connection = PostgreSQLConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT);
			ResultSet set = statement.executeQuery()){
			
			List<Weapon> weapons = new LinkedList<Weapon>();
			while(set.next()) {
				weapons.add(resultToWeapon(set));
			}
			return weapons;
		} catch(SQLException ex) {
			logger.error(ex.getMessage());
			throw new WeaponException("Failed to retrieve weapons");
		}
	}
	
	public Weapon getWeaponByName(String name) {
		try(Connection connection = PostgreSQLConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME)){
			statement.setString(1, name);
			try(ResultSet set = statement.executeQuery()){
				if(set.next()) {
					return resultToWeapon(set);
				} else throw new WeaponException("No weapon with name: " + name + " found");
			}
		} catch(SQLException ex) {
			logger.error(ex.getMessage());
			throw new WeaponException("Failed to retrieve weapon");
		}
	}
	
	public void addWeapon(Weapon weapon) {
		try(Connection connection = PostgreSQLConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT)){
			statement.setString(1, weapon.getName());
			statement.setInt(2, weapon.getMinDmg());
			statement.setInt(3, weapon.getMaxDmg());
			statement.executeUpdate();
		} catch(SQLException ex) {
			logger.error(ex.getMessage());
			throw new WeaponException("Failed to add weapon");
		}
	}
	
	public void updateWeaponDamage(int multiplier) {
		try(Connection connection = PostgreSQLConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE)){
			statement.setInt(1, multiplier);
			statement.setInt(2, multiplier);
			statement.executeUpdate();
		} catch(SQLException ex) {
			logger.error(ex.getMessage());
			throw new WeaponException("Failed to update weapon damage");
		}
	}
	
	public void deleteWeapon(Weapon weapon) {
		try(Connection connection = PostgreSQLConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE)){
			statement.setString(1, weapon.getName());
			statement.executeUpdate();
		} catch(SQLException ex) {
			logger.error(ex.getMessage());
			throw new WeaponException("Failed to delete weapon");
		}
	}


	/**
	 * @param set
	 * @return Weapon
	 * @throws SQLException
	 */
	private Weapon resultToWeapon(ResultSet set) throws SQLException{
		return new Weapon(set.getString("name"), set.getInt("min"), set.getInt("max"));
	}
}
