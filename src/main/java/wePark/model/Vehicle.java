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
public class Vehicle {
	
	protected int vehicleid;
	protected int userid;
	protected String brand;
	protected String year;
	protected String platenumber;
	
	/**
	 * @param vehicleid input vehicle id
	 * @param userid input userid
	 * @param brand input brand
	 * @param year input year
	 * @param platenumber input platenumber
	 */
	public Vehicle(int vehicleid, int userid, String brand, String year, String platenumber) {
		super();
		this.vehicleid = vehicleid;
		this.userid = userid;
		this.brand = brand;
		this.year = year;
		this.platenumber = platenumber;
	}
	
	public Vehicle(int vehicleid) {
		this.vehicleid = vehicleid;
	}
	/**
	 * @return the vehicleid
	 */
	public int getVehicleid() {
		return vehicleid;
	}
	/**
	 * @param vehicleid the vehicleid to set
	 */
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
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
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the platenumber
	 */
	public String getPlatenumber() {
		return platenumber;
	}
	/**
	 * @param platenumber the platenumber to set
	 */
	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}
	
	
	
}
