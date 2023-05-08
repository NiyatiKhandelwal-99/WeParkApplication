package wePark.dal;

import wePark.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class VehicleDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static VehicleDao instance = null;
	protected VehicleDao() {
		connectionManager = new ConnectionManager();
	}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
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
	public Vehicle create(Vehicle vehicle) throws Exception {
		String insertVehicle = "INSERT INTO Vehicle(userid,brand,year,platenumber)"
				+ " VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertVehicle, Statement.RETURN_GENERATED_KEYS);
			
			//validate input
			if (!validateInput(vehicle.getBrand())
					|| !validateInput(vehicle.getYear())
					|| !validateInput(vehicle.getPlatenumber())) {
				throw new Exception("Invalid input, try again");
			}
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, vehicle.getUserid());
			insertStmt.setString(2, vehicle.getBrand());
			insertStmt.setString(3, vehicle.getYear());
			insertStmt.setString(4, vehicle.getPlatenumber());

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
			int vehicleid = -1;
			if(resultKey.next()) {
				vehicleid = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			vehicle.setUserid(vehicleid);
			return vehicle;
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
	public Vehicle getVehicleByVehicleId(int vehicleid) throws Exception {
		String selectVehicle = "SELECT vehicleid,userid,brand,year,platenumber"
				+ " FROM Vehicle WHERE vehicleid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVehicle);
			selectStmt.setInt(1, vehicleid);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultVehicleid = results.getInt("vehicleid");
				int userid = results.getInt("userid");
				String brand = results.getString("brand");
				String year = results.getString("year");
				String platenumber = results.getString("platenumber");
				Vehicle vehicle = new Vehicle(resultVehicleid, userid, brand, year,platenumber);
				return vehicle;
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
	 * Get the all the BlogPosts for a user.
	 */
	public List<Vehicle> getVehicleByUserId(int userid) throws SQLException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		String selectVehicle = "SELECT vehicleid,userid,brand,year,platenumber"
				+ " FROM Vehicle WHERE userid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVehicle);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int vehicleid = results.getInt("vehicleid");
				int resultUserid = results.getInt("userid");
				String brand = results.getString("brand");
				String year = results.getString("year");
				String platenumber = results.getString("platenumber");
				Vehicle vehicle = new Vehicle(vehicleid, resultUserid, brand, year,platenumber);
				vehicles.add(vehicle);
			}
		} catch (SQLException e) {
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
		return vehicles;
	}
	
	/**
	 * Update the LastName of the Persons instance.
	 * This runs a UPDATE statement.
	 * @throws Exception 
	 */
	public Vehicle updateUserId(Vehicle vehicle, int newUserId) throws Exception {
		String updateUserId= "UPDATE Vehicle SET userid=? WHERE vehicleid=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			//validate input
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUserId);
			updateStmt.setInt(1, newUserId);
			updateStmt.setInt(2, vehicle.getVehicleid());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			vehicle.setUserid(newUserId);
			return vehicle;
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
	public Vehicle updatePlateNumber(Vehicle vehicle, String newPlatenumber) throws Exception {
		String updatePlateNumber= "UPDATE Vehicle SET platenumber=? WHERE vehicleid=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			//validate input
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePlateNumber);
			updateStmt.setString(1, newPlatenumber);
			updateStmt.setInt(2, vehicle.getVehicleid());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			vehicle.setPlatenumber(newPlatenumber);
			return vehicle;
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
	public Vehicle delete(Vehicle vehicle) throws Exception {
		String deleteVehicle = "DELETE FROM Vehicle WHERE vehicleid=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteVehicle);
			deleteStmt.setInt(1, vehicle.getVehicleid());
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
