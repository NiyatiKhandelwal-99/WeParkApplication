package wePark.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


public class ParkingslotDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ParkingslotDao instance = null;
	protected ParkingslotDao() {
		connectionManager = new ConnectionManager();
	}
	public static ParkingslotDao getInstance() {
		if(instance == null) {
			instance = new  ParkingslotDao();
		}
		return instance;
	}
	
	public void updateBookingId(int parkingslot, int bookingid) throws Exception {
		String updatePassword= "UPDATE Parkingslot SET booking_id=? WHERE parking_slot_id=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePassword);
			if(bookingid == 0) {
				updateStmt.setNull(1, Types.INTEGER);
			}
			else {
				updateStmt.setInt(1, bookingid);
			}
			
			updateStmt.setInt(2, parkingslot);
			updateStmt.executeUpdate();
			
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
	
}