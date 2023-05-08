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
public class Parking_price {
	
	protected int parkinglotid;
	protected int hour;
	protected float price;
	
	/**
	 * @param parkinglotid
	 * @param hour
	 * @param price
	 */
	public Parking_price(int parkinglotid, int hour, float price) {
		super();
		this.parkinglotid = parkinglotid;
		this.hour = hour;
		this.price = price;
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
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
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
	
	
}
