package wePark.dal;

import wePark.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class ContactDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ContactDao instance = null;
	protected ContactDao() {
		connectionManager = new ConnectionManager();
	}
	public static ContactDao getInstance() {
		if(instance == null) {
			instance = new ContactDao();
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
	public Contact create(Contact contact) throws Exception {
		String insertContact = "INSERT INTO Contact(userid,firstname,lastname,address1,address2"
				+ ",city,state,zipcode,email,phone,driver_license,driver_license_state)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertContact);
			
			//validate input
			if (!validateInput(contact.getFirstname())
					|| !validateInput(contact.getLastname())
					|| !validateInput(contact.getAddress1())
					|| !validateInput(contact.getAddress2())
					|| !validateInput(contact.getCity())
					|| !validateInput(contact.getState())
					|| !validateInput(contact.getZipcode())
					|| !validateInput(contact.getEmail())
					|| !validateInput(contact.getPhone())
					|| !validateInput(contact.getDriver_license())
					|| !validateInput(contact.getDriver_license_state())) {
				throw new Exception("Invalid input, try again");
			}
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, contact.getUserid());
			insertStmt.setString(2, contact.getFirstname());
			insertStmt.setString(3, contact.getLastname());
			insertStmt.setString(4, contact.getAddress1());
			insertStmt.setString(5, contact.getAddress2());
			insertStmt.setString(6, contact.getCity());
			insertStmt.setString(7, contact.getState());
			insertStmt.setString(8, contact.getZipcode());
			insertStmt.setString(9, contact.getEmail());
			insertStmt.setString(10, contact.getPhone());
			insertStmt.setString(11, contact.getDriver_license());
			insertStmt.setString(12, contact.getDriver_license_state());

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
			return contact;
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
	public Contact getContactByUserId(int userid) throws Exception {
		String selectContact = "SELECT userid,firstname,lastname,address1,address2"
				+ ",city,state,zipcode,email,phone,driver_license,driver_license_state"
				+ " FROM Contact WHERE userid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			//validate input
			
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectContact);
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
				int resultUserId = results.getInt("userid");
				String firstname = results.getString("firstname");
				String lastname = results.getString("lastname");
				String address1 = results.getString("address1");
				String address2 = results.getString("address2");
				String city = results.getString("city");
				String state = results.getString("state");
				String zipcode = results.getString("zipcode");
				String email = results.getString("email");
				String phone = results.getString("phone");
				String driver_license = results.getString("driver_license");
				String driver_license_state = results.getString("driver_license_state");
				
				Contact contact = new Contact(resultUserId,firstname,lastname,address1,address2
						,city,state,zipcode,email,phone,driver_license,driver_license_state);
				return contact;
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
	public List<List<String>> getUserNumberByCity() throws SQLException {
		List<List<String>> userNumber = new ArrayList<>();
		String selectUserNumber = "SELECT city, count(userid) as amount"
				+ " FROM Contact"
				+ " group by city"
				+ " order by 2;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserNumber);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				String city = results.getString("city");
				int amount = results.getInt("amount");
				List<String> cityAmount = new ArrayList<>(Arrays.asList(city, String.valueOf(amount)));
				userNumber.add(cityAmount);
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
		return userNumber;
	}
	
	/**
	 * Update the LastName of the Persons instance.
	 * This runs a UPDATE statement.
	 * @throws Exception 
	 */
	public Contact updateContactInfo(Contact contact, int newUserid, String newFirstname, String newLastname,
			String newAddress1, String newAddress2, String newCity,
			String newState, String newZipcode, String newEmail, String newPhone,
			String newDriver_license, String newDriver_license_state) throws Exception {
		String updateUserId= "UPDATE Contact SET userid=?"
				+ ", firstname=?"
				+ ", lastname=?"
				+ ", address1=?"
				+ ", address2=?"
				+ ", city=?"
				+ ", state=?"
				+ ", zipcode=?"
				+ ", email=?"
				+ ", phone=?"
				+ ", driver_license=?"
				+ ", driver_license_state=?"
				+ " WHERE userid=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			//validate input
			if (!validateInput(contact.getFirstname())
					|| !validateInput(contact.getLastname())
					|| !validateInput(contact.getAddress1())
					|| !validateInput(contact.getAddress2())
					|| !validateInput(contact.getCity())
					|| !validateInput(contact.getState())
					|| !validateInput(contact.getZipcode())
					|| !validateInput(contact.getEmail())
					|| !validateInput(contact.getPhone())
					|| !validateInput(contact.getDriver_license())
					|| !validateInput(contact.getDriver_license_state())
					|| !validateInput(newFirstname)
					|| !validateInput(newLastname)
					|| !validateInput(newAddress1)
					|| !validateInput(newAddress2)
					|| !validateInput(newCity)
					|| !validateInput(newState)
					|| !validateInput(newZipcode)
					|| !validateInput(newEmail)
					|| !validateInput(newPhone)
					|| !validateInput(newDriver_license)
					|| !validateInput(newDriver_license_state)) {
				throw new Exception("Invalid input, try again");
			}
			
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUserId);
			updateStmt.setInt(1, newUserid);
			updateStmt.setString(2, newFirstname);
			updateStmt.setString(3, newLastname);
			updateStmt.setString(4, newAddress1);
			updateStmt.setString(5, newAddress2);
			updateStmt.setString(6, newCity);
			updateStmt.setString(7, newState);
			updateStmt.setString(8, newZipcode);
			updateStmt.setString(9, newEmail);
			updateStmt.setString(10, newPhone);
			updateStmt.setString(11, newDriver_license);
			updateStmt.setString(12, newDriver_license_state);
			updateStmt.setInt(13, contact.getUserid());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			contact.setUserid(newUserid);
			contact.setFirstname(newFirstname);
			contact.setLastname(newLastname);
			contact.setAddress1(newAddress1);
			contact.setAddress2(newAddress2);
			contact.setCity(newCity);
			contact.setState(newState);
			contact.setZipcode(newZipcode);
			contact.setEmail(newEmail);
			contact.setPhone(newPhone);
			contact.setDriver_license(newDriver_license);
			contact.setDriver_license_state(newDriver_license_state);
			
			return contact;
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
	public Contact delete(Contact contact) throws Exception {
		String deleteContact = "DELETE FROM Contact WHERE userid=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			//validate input
			
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteContact);
			deleteStmt.setInt(1, contact.getUserid());
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
