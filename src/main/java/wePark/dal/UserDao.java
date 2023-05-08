package wePark.dal;

import wePark.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class UserDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UserDao instance = null;
	protected UserDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	private boolean validateInput(String input) {
		if (input.contains(";")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 * @throws Exception 
	 */
	public User create(User user) throws Exception {
		String insertUser = "INSERT INTO User(username,password)"
				+ " VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser,
					Statement.RETURN_GENERATED_KEYS);
			
			//validate input
			if (!validateInput(user.getUsername())
					|| !validateInput(user.getPassword())) {
				throw new Exception("Invalid input, try again");
			}
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());

			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			resultKey = insertStmt.getGeneratedKeys();
			int userid = -1;
			if(resultKey.next()) {
				userid = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUserid(userid);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 * @throws Exception 
	 */
	public User getUserByUserId(int userid) throws Exception {
		String selectUser = "SELECT userid,username,password,is_regular_custom"
				+ " FROM User WHERE userid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userid);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultUserid = results.getInt("userid");
				String userName = results.getString("username");
				String password = results.getString("password");
				boolean is_regular_custom = results.getBoolean("is_regular_custom");
				User user = new User(resultUserid, userName, password, is_regular_custom);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 * @throws Exception 
	 */
	public User getUserByUserName(String username) throws Exception {
		String selectUser = "SELECT userid,username,password,is_regular_custom"
				+ " FROM User WHERE username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			if (!validateInput(username)) {
				throw new Exception("Invalid input, try again");
			}
			
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, username);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int userid = results.getInt("userid");
				String resultUserName = results.getString("username");
				String password = results.getString("password");
				boolean is_regular_custom = results.getBoolean("is_regular_custom");
				User user = new User(userid, resultUserName, password, is_regular_custom);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	/**
	 * Update the LastName of the Persons instance.
	 * This runs a UPDATE statement.
	 * @throws Exception 
	 */
	public User updatePassword(User user, String newPassword) throws Exception {
		String updatePassword= "UPDATE User SET password=? WHERE userid=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			//validate input
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePassword);
			updateStmt.setString(1, newPassword);
			updateStmt.setInt(2, user.getUserid());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			user.setPassword(newPassword);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	/**
	 * Update the LastName of the Persons instance.
	 * This runs a UPDATE statement.
	 * @throws Exception 
	 */
	public User updateRegular_custom(User user, boolean newRegular_custom) throws Exception {
		String updatePassword= "UPDATE User SET newRegular_custom=? WHERE userid=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			//validate input
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePassword);
			updateStmt.setBoolean(1, newRegular_custom);
			updateStmt.setInt(2, user.getUserid());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			user.setIs_regular_custom(newRegular_custom);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	/**
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 * @throws Exception 
	 */
	public User delete(User user) throws Exception {
		String deleteUser = "DELETE FROM User WHERE userid=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUsername());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}



}
