package wePark.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wePark.model.CreditCards;
import wePark.model.RegularPass;

public class RegularPassDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static RegularPassDao instance = null;

	protected RegularPassDao() {
		connectionManager = new ConnectionManager();
	}

	public static RegularPassDao getInstance() {
		if (instance == null) {
			instance = new RegularPassDao();
		}
		return instance;
	}

	public RegularPass create(RegularPass regularPass) throws Exception {
		String insertContact = "INSERT INTO Regular_pass(userid, parkinglotid, purchase_date, start_date, duration_in_days, cost)"
				+ " VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertContact, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, regularPass.getUserid());
			insertStmt.setInt(2, regularPass.getParkinglotid());
			insertStmt.setDate(3, regularPass.getPurchase_date());
			insertStmt.setDate(4, regularPass.getStart_date());
			insertStmt.setInt(5, regularPass.getDuration_in_days());
			insertStmt.setFloat(6, regularPass.getCost());

			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int regularPassId = -1;
			if (resultKey.next()) {
				regularPassId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			regularPass.setPassid(regularPassId);
			return regularPass;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	// get regular pass by user ID
	public List<RegularPass> getRegularPassByUserId(int userid) throws SQLException {
		List<RegularPass> regularPasses = new ArrayList<RegularPass>();
		String selectRegularPass = "SELECT passid, name, purchase_date, start_date, duration_in_days, cost"
				+ " FROM Regular_pass JOIN Parkinglot USING (parkinglotid) WHERE userid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRegularPass);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			while(results.next()) {
				regularPasses.add(new RegularPass(results.getInt("passid"), userid, results.getDate("purchase_date"), results.getDate("start_date"), results.getInt("duration_in_days"), results.getFloat("cost"), results.getString("name")));
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
		return regularPasses;
	}
	
	// get regular pass by user ID
	public List<RegularPass> deleteRegularPassByRegularPassId(int passid) throws SQLException {
		
		String deleteRegularPass = "DELETE FROM Regular_pass WHERE passid=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRegularPass);
			deleteStmt.setInt(1, passid);
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