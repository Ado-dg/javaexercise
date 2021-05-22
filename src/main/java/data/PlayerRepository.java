package main.java.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PlayerRepository {
	private static final String SAVE_XP = "UPDATE Player SET xp = ? WHERE id = 1";
	private static Logger logger = LogManager.getLogger(PlayerRepository.class);
	
	public void saveXp(int xp) {
        try(Connection connection = PostgreSQLConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_XP))
        {
            statement.setInt(1, xp);
            statement.executeUpdate();
            logger.info("XP written to DB");
        }catch (SQLException ex){
        	System.out.println(ex.getMessage());
            //LOGGER.log(Level.SEVERE, ex.getMessage());
            //throw new UserException(ex.getMessage());
        	//TODO
        }
    }
}
