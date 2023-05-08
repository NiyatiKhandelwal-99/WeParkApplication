package wePark.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import wePark.model.Parking_price;


public class ParkingPriceDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ParkingPriceDao instance = null;
	protected ParkingPriceDao() {
		connectionManager = new ConnectionManager();
	}
	public static ParkingPriceDao getInstance() {
		if(instance == null) {
			instance = new ParkingPriceDao();
		}
		return instance;
	}
	
	
	public Parking_price create(Parking_price parking_price) throws Exception {
		String insertContact = "INSERT INTO ParkingPrice(parkinglotid, hour, price)"
				+ " VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertContact, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, parking_price.getParkinglotid());
			insertStmt.setInt(2, parking_price.getHour());
			insertStmt.setFloat(3, parking_price.getPrice());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int parkinglotId = -1;
			if(resultKey.next()) {
				parkinglotId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			parking_price.setParkinglotid(parkinglotId);
			return parking_price;
			
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
	public Parking_price getParkingPriceByParkinglotId(int parkinglotid) throws SQLException {
		Parking_price parkingPrices;
		String selectParkingReceipts = "SELECT parkinglotid, hour, price"
				+ " FROM Parking_price WHERE parkinglotid = ?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectParkingReceipts);
			selectStmt.setInt(1, parkinglotid);
			results = selectStmt.executeQuery();
			if(results.next()) {
				parkingPrices = new Parking_price(
						results.getInt("parkinglotid"),
						results.getInt("hour"),
						results.getFloat("price")
						);
				return parkingPrices;
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
		return null;
	}

}
