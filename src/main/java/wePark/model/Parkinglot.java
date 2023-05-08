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
public class Parkinglot {
	
	protected int parkinglotid;
	protected String name;
	protected String address;
	protected String city;
	protected String state;
	protected String zipcode;
	protected String latitue;
	protected String longtitute;
	protected boolean is_open;
	protected float price;
	protected int remian_space;
	protected String company;
	
	/**
	 * @param parkinglotid
	 * @param name
	 * @param address
	 * @param city
	 * @param zipcode
	 * @param latitue
	 * @param longtitute
	 * @param is_open
	 * @param price
	 * @param remian_space
	 * @param company
	 */
	public Parkinglot(int parkinglotid, String name, String address, String city, String state ,String zipcode, String latitue,
			String longtitute, boolean is_open, float price, int remian_space, String company) {
		super();
		this.parkinglotid = parkinglotid;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.latitue = latitue;
		this.longtitute = longtitute;
		this.is_open = is_open;
		this.price = price;
		this.remian_space = remian_space;
		this.company = company;
	}
	
	public Parkinglot(int parkinglotid, String name, String address, String city, String state ,String zipcode, boolean is_open, float price, int remian_space) {
		
		this.parkinglotid = parkinglotid;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.is_open = is_open;
		this.price = price;
		this.remian_space = remian_space;
		
	}
	
	public Parkinglot(int parkinglotid) {
		this.parkinglotid = parkinglotid;
	}
	
	
	/**
	 * @return the parkinglotid
	 */
	public int getParkinglotid() {
		return parkinglotid;
	}
	
	/**
	 * @param parkinglotid the parkinglotid to set
	 */
	public void setParkinglotid(int parkinglotid) {
		this.parkinglotid = parkinglotid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @param city the city to set
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
	 * @return the latitue
	 */
	public String getLatitue() {
		return latitue;
	}
	/**
	 * @param latitue the latitue to set
	 */
	public void setLatitue(String latitue) {
		this.latitue = latitue;
	}
	/**
	 * @return the longtitute
	 */
	public String getLongtitute() {
		return longtitute;
	}
	/**
	 * @param longtitute the longtitute to set
	 */
	public void setLongtitute(String longtitute) {
		this.longtitute = longtitute;
	}
	/**
	 * @return the is_open
	 */
	public boolean isIs_open() {
		return is_open;
	}
	/**
	 * @param is_open the is_open to set
	 */
	public void setIs_open(boolean is_open) {
		this.is_open = is_open;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the remian_space
	 */
	public int getRemian_space() {
		return remian_space;
	}
	/**
	 * @param remian_space the remian_space to set
	 */
	public void setRemian_space(int remian_space) {
		this.remian_space = remian_space;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	
	
	
}
