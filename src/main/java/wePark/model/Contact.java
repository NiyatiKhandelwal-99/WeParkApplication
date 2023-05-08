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
public class Contact {
	
	protected int userid;
	protected String firstname;
	protected String lastname;
	protected String address1;
	protected String address2;
	protected String city;
	protected String state;
	protected String zipcode;
	protected String email;
	protected String phone;
	protected String driver_license;
	protected String driver_license_state;
	
	/**
	 * @param userid
	 * @param firstname
	 * @param lastname
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param zipcode
	 * @param email
	 * @param phone
	 * @param driver_license
	 * @param driver_license_state
	 */
	public Contact(int userid, String firstname, String lastname, String address1, String address2, String city,
			String state, String zipcode, String email, String phone, String driver_license,
			String driver_license_state) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.email = email;
		this.phone = phone;
		this.driver_license = driver_license;
		this.driver_license_state = driver_license_state;
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the driver_license
	 */
	public String getDriver_license() {
		return driver_license;
	}

	/**
	 * @param driver_license the driver_license to set
	 */
	public void setDriver_license(String driver_license) {
		this.driver_license = driver_license;
	}

	/**
	 * @return the driver_license_state
	 */
	public String getDriver_license_state() {
		return driver_license_state;
	}

	/**
	 * @param driver_license_state the driver_license_state to set
	 */
	public void setDriver_license_state(String driver_license_state) {
		this.driver_license_state = driver_license_state;
	}
	
	
	
}
