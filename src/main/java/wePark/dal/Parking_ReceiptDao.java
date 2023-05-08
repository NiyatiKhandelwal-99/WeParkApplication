package wePark.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import wePark.model.Booking;
import wePark.model.Parking_Receipt;

public class Parking_ReceiptDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static Parking_ReceiptDao instance = null;
	protected Parking_ReceiptDao() {
		connectionManager = new ConnectionManager();
	}
	public static Parking_ReceiptDao getInstance() {
		if(instance == null) {
			instance = new Parking_ReceiptDao();
		}
		return instance;
	}
	
	public Parking_Receipt create(Parking_Receipt parking_receipt) throws Exception {
		String insertParkingReceipt = "INSERT INTO Parking_Receipt(booking_id, actual_entry_time, actual_exit_time, basic_cost, penalty, total_cost, is_paid)"
				+ "VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertParkingReceipt, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, parking_receipt.getBooking_id());
			insertStmt.setTimestamp(2, parking_receipt.getActual_entry_time());
			insertStmt.setTimestamp(3, parking_receipt.getActual_exit_time());
			insertStmt.setFloat(4, parking_receipt.getBasic_cost());
			insertStmt.setFloat(5, parking_receipt.getPenalty());
			insertStmt.setFloat(6, parking_receipt.getTotal_cost());
			insertStmt.setBoolean(7, parking_receipt.isIs_paid());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int receiptId = -1;
			if(resultKey.next()) {
				receiptId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			
			parking_receipt.setReceiptid(receiptId);
			return parking_receipt;
		
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
	
	
	public HashMap<Parking_Receipt, Booking> getParkingReceiptByUserId(int userid) throws SQLException {
		HashMap<Parking_Receipt, Booking> parkingReceipts = new HashMap<>();
		String selectParkingReceipts = "SELECT receiptid, booking_id, vehicleid, booking_date, parking_slot_id, actual_entry_time, actual_exit_time, basic_cost, penalty, total_cost, is_paid"
				+ " FROM Parking_Receipt JOIN Booking USING (booking_id) WHERE userid=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectParkingReceipts);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			while(results.next()) {
				parkingReceipts.put(new Parking_Receipt(
						results.getInt("receiptid"),
						results.getInt("booking_id"),
						results.getTimestamp("actual_entry_time"),
						results.getTimestamp("actual_exit_time"),
						results.getFloat("basic_cost"),
						results.getFloat("penalty"),
						results.getFloat("total_cost"),
						results.getBoolean("is_paid")
						),
						new Booking(
								results.getInt("booking_id"),
								results.getInt("vehicleid"),
								results.getDate("booking_date"),
								results.getInt("parking_slot_id")
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
		return parkingReceipts;
	}
		
}