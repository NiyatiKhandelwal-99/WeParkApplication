package wePark.model;

/**
 * Persons is a simple, plain old java objects (POJO).
 * 
 * Persons/PersonsDao is the superclass for Administrators/AdministratorsDao and
 * BlogUsers/BlogUsersDao. Our implementation of Persons is a concrete class.
 * This allows us to create records in the Persons MySQL table without having
 * the associated records in the Administrators or BlogUsers MySQL tables.
 * Alternatively, Persons could be an interface or an abstract class, which
 * would force a Persons record to be created only if an Administrators or
 * BlogUsers record is created, too.
 */
public class User {
	
	protected int userid;
	protected String username;
	protected String password;
	protected boolean is_regular_custom;
	
	/**
	 * @param userid input userid
	 * @param username input username
	 * @param password input password
	 * @param is_regular_custom input is_regular_custom
	 */
	public User(int userid, String username, String password, boolean is_regular_custom) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.is_regular_custom = is_regular_custom;
	}
	
	/**
	 * @param userid input userid
	 * @param username input username
	 * @param password input password
	 * @param is_regular_custom input is_regular_custom
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.is_regular_custom = false;
	}
	
	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the is_regular_custom
	 */
	public boolean isIs_regular_custom() {
		return is_regular_custom;
	}

	/**
	 * @param is_regular_custom the is_regular_custom to set
	 */
	public void setIs_regular_custom(boolean is_regular_custom) {
		this.is_regular_custom = is_regular_custom;
	}
	
	
}
