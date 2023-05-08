package wePark.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import wePark.model.Parkinglot;


public class ParkinglotDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ParkinglotDao instance = null;
	protected ParkinglotDao() {
		connectionManager = new ConnectionManager();
	}
	public static ParkinglotDao getInstance() {
		if(instance == null) {
			instance = new  ParkinglotDao();
		}
		return instance;
	}
	
	public Parkinglot create(Parkinglot parkinglot) throws Exception {
		String insertParkinglot = "INSERT INTO Parkinglot(name, address, city, state, zipcode, latitute, longtitute, is_open, price, remain_space, company)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertParkinglot, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, parkinglot.getName());
			insertStmt.setString(2, parkinglot.getAddress());
			insertStmt.setString(3, parkinglot.getCity());
			insertStmt.setString(4, parkinglot.getState());
			insertStmt.setString(5, parkinglot.getZipcode());
			insertStmt.setString(6, parkinglot.getLatitue());
			insertStmt.setString(7, parkinglot.getLongtitute());
			insertStmt.setBoolean(8, parkinglot.isIs_open());
			insertStmt.setFloat(9, parkinglot.getPrice());
			insertStmt.setInt(10, parkinglot.getRemian_space());
			insertStmt.setString(11, parkinglot.getCompany());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int lotId = -1;
			if(resultKey.next()) {
				lotId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			
			parkinglot.setParkinglotid(lotId);
			return parkinglot;
		
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
	
	public List<Parkinglot> getParkinglotByZip(String zip, String sortBy)throws SQLException {
		String sortOrder = "ASC";
		
		if(!sortBy.equals("price")) {
			sortOrder = "DESC";
		}
		String selectParkinglot = "SELECT parkinglotid, name, address, city, state, zipcode, is_open, price, remain_space"
				+ " FROM Parkinglot WHERE zipcode=? ORDER BY " + sortBy + " " + sortOrder;
		
		List<Parkinglot> parkinglots = new ArrayList<>();
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectParkinglot);
			selectStmt.setString(1, zip);
			results = selectStmt.executeQuery();
			while(results.next()) {
				parkinglots.add(new Parkinglot(
						results.getInt("parkinglotid"),
						results.getString("name"),
						results.getString("address"),
						results.getString("city"),
						results.getString("state"),
						results.getString("zipcode"),
						results.getBoolean("is_open"),
						results.getFloat("price"),
						results.getInt("remain_space")
						
						));
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
		return parkinglots;
	}

	public int getAvailableParkingSlotByLotId(int parkingLotId) throws SQLException {
		String joinStmt = "Select parking_slot_id from (Select parkinglotid, floorid FROM Parkinglot JOIN Floor USING (parkinglotid)) as pf join Parkingslot USING (floorid) where parkinglotid = ? and booking_id is null limit 1" ;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(joinStmt);
			selectStmt.setInt(1,parkingLotId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				return results.getInt("parking_slot_id");
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
		
		
		return 0;
	}

	
		
}