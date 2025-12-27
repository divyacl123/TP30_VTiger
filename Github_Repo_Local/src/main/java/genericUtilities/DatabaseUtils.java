package genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtils {
	Connection conn = null;
	ResultSet result = null;
	
	/**
	 * This method is used to connect to database
	 * @throws SQLException
	 */
	public void connectToDB() throws SQLException {
		
		//register the database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//get the connection
		conn = DriverManager.getConnection(IpathConstants.dbURL, IpathConstants.dbUN, IpathConstants.dbPwd);
	
	}
	
	/**
	 * This method is used to execute select query
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		
		//create statement
		Statement state = conn.createStatement();
		
		//execute query
		result = state.executeQuery(query);
		
		return result;
	}
	
	/**
	 * This method is used to execute non-select query
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int updateQuery(String query) throws SQLException {
		
		//create statement
		Statement state = conn.createStatement();
		
		//execute query
		int rows = state.executeUpdate(query);
		
		return rows;
		
	}
	
	public void disconnectDB() throws SQLException {
		conn.close();
	}

}
