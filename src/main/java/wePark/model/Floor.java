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
public class Floor {
	
	protected int floorid;
	protected int parkinglotid;
	protected int floor_number;
	protected int max_height_in_inch;
	
	/**
	 * @param floorid
	 * @param parkinglotid
	 * @param floor_number
	 * @param max_height_in_inch
	 */
	public Floor(int floorid, int parkinglotid, int floor_number, int max_height_in_inch) {
		super();
		this.floorid = floorid;
		this.parkinglotid = parkinglotid;
		this.floor_number = floor_number;
		this.max_height_in_inch = max_height_in_inch;
	}

	/**
	 * @return the floorid
	 */
	public int getFloorid() {
		return floorid;
	}

	/**
	 * @param floorid the floorid to set
	 */
	public void setFloorid(int floorid) {
		this.floorid = floorid;
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
	 * @return the floor_number
	 */
	public int getFloor_number() {
		return floor_number;
	}

	/**
	 * @param floor_number the floor_number to set
	 */
	public void setFloor_number(int floor_number) {
		this.floor_number = floor_number;
	}

	/**
	 * @return the max_height_in_inch
	 */
	public int getMax_height_in_inch() {
		return max_height_in_inch;
	}

	/**
	 * @param max_height_in_inch the max_height_in_inch to set
	 */
	public void setMax_height_in_inch(int max_height_in_inch) {
		this.max_height_in_inch = max_height_in_inch;
	}
	
	
	
}
