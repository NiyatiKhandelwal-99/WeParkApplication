package wePark.dal;

import wePark.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class CreditCardsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CreditCardsDao instance = null;
	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
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
	public CreditCards create(CreditCards creditCard) throws Exception {
		String insertCreditCard = "INSERT INTO CreditCards(cardNumber,userid,expirationDate)"
				+ " VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);
			
			//validate input
			if (!validateInput(creditCard.getCardNumber())) {
				throw new Exception("Invalid input, try again");
			}
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, creditCard.getCardNumber());
			insertStmt.setInt(2, creditCard.getUserid());
			insertStmt.setDate(3, (Date) creditCard.getExpirationDate());

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
			return creditCard;
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
	public CreditCards getCreditCardByCardNumber(String cardNumber) throws Exception {
		String selectCreditCard = "SELECT cardNumber,userid,expirationDate"
				+ " FROM CreditCards WHERE cardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			//validate input
			if (!validateInput(cardNumber)) {
				throw new Exception("Invalid input, try again");
			}
			
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setString(1, cardNumber);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultCardNumber = results.getString("cardNumber");
				int userid = results.getInt("userid");
				Date expirationDate = results.getDate("expirationDate");
				CreditCards creditCard = new CreditCards(resultCardNumber, userid, expirationDate);
				return creditCard;
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
	public List<CreditCards> getCreditCardsByUserId(int userid) throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCards = "SELECT cardNumber,userid,expirationDate"
				+ " FROM CreditCards WHERE userid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String cardNumber = results.getString("cardNumber");
				int resultUserid = results.getInt("userid");
				Date expirationDate = results.getDate("expirationDate");
				CreditCards creditCard = new CreditCards(cardNumber, resultUserid, expirationDate);
				creditCards.add(creditCard);
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
		return creditCards;
	}
	
	/**
	 * Update the LastName of the Persons instance.
	 * This runs a UPDATE statement.
	 * @throws Exception 
	 */
	public CreditCards updateUserId(CreditCards creditCard, int newUserId) throws Exception {
		String updateUserId= "UPDATE CreditCards SET userid=? WHERE cardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			//validate input
			if (!validateInput(creditCard.getCardNumber())) {
				throw new Exception("Invalid input, try again");
			}
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUserId);
			updateStmt.setInt(1, newUserId);
			updateStmt.setString(2, creditCard.getCardNumber());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			creditCard.setUserid(newUserId);
			return creditCard;
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
	public CreditCards delete(CreditCards creditCard) throws Exception {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE cardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			//validate input
			if (!validateInput(creditCard.getCardNumber())) {
				throw new Exception("Invalid input, try again");
			}
			
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setString(1, creditCard.getCardNumber());
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
