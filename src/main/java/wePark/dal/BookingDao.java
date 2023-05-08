package wePark.dal;

import wePark.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class BookingDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static BookingDao instance = null;
	protected BookingDao() {
		connectionManager = new ConnectionManager();
	}
	public static BookingDao getInstance() {
		if(instance == null) {
			instance = new BookingDao();
		}
		return instance;
	}
	
	
	public Booking create(Booking booking) throws Exception {
		String insertContact = "INSERT INTO Booking(userid, vehicleid, start_time, duration_in_min, booking_date, parking_slot_id)"
				+ " VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertContact, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, booking.getUserid());
			insertStmt.setInt(2, booking.getVehicleid());
			insertStmt.setDate(3, booking.getStart_time());
			insertStmt.setInt(4, booking.getDuration_in_min());
			insertStmt.setDate(5, booking.getBooking_date());
			insertStmt.setInt(6, booking.getParking_slot_id());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int bookingId = -1;
			if(resultKey.next()) {
				bookingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			
			booking.setBooking_id(bookingId);
			return booking;
			
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
	
	public Booking delete(Booking booking) throws SQLException {
		String deleteQuery = "DELETE FROM Booking where booking_id = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteQuery);
			deleteStmt.setInt(1, booking.getBooking_id());
			deleteStmt.executeUpdate();
		}
		catch (SQLException e) {
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
		
		return null;		
	}
	
	public List<Booking> getBookingByUserId(int userid) throws SQLException {
		List<Booking> bookings = new ArrayList<Booking>();
		String selectCreditCards = "SELECT booking_id, userid, vehicleid, start_time, duration_in_min, booking_date, parking_slot_id"
				+ " FROM Booking WHERE userid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			while(results.next()) {
				bookings.add(new Booking(
						results.getInt("booking_id"),
						results.getInt("userid"),
						results.getInt("vehicleid"),
						results.getDate("start_time"),
						results.getInt("duration_in_min"),
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
		return bookings;
	}
	
	
	public Booking getBookingByBookingId(int bookingId) throws SQLException {
		
		String selectCreditCards = "SELECT booking_id, userid, vehicleid, start_time, duration_in_min, booking_date, parking_slot_id"
				+ " FROM Booking WHERE booking_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setInt(1, bookingId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				return new Booking(
						results.getInt("booking_id"),
						results.getInt("userid"),
						results.getInt("vehicleid"),
						results.getDate("start_time"),
						results.getInt("duration_in_min"),
						results.getDate("booking_date"),
						results.getInt("parking_slot_id")
						);
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
	
	
	public Booking updateBooking(Booking booking) throws Exception {
		String updatePassword= "UPDATE Booking SET vehicleid = ?, start_time = ?, duration_in_min = ?, booking_date=? WHERE booking_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			//validate input
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePassword);
			updateStmt.setInt(1, booking.getVehicleid());
			updateStmt.setDate(2, booking.getStart_time());
			updateStmt.setInt(3, booking.getDuration_in_min());
			updateStmt.setDate(4, booking.getBooking_date());
			updateStmt.setInt(5, booking.getBooking_id());
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
		return booking;
	}
	

}
