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
public class Parkingslot {
	
	protected int parking_slot_id;
	protected int floorid;
	protected String type;
	protected int booking_id;
	
	/**
	 * @param parking_slot_id
	 * @param floorid
	 * @param type
	 * @param booking_id
	 */
	public Parkingslot(int parking_slot_id, int floorid, String type, int booking_id) {
		super();
		this.parking_slot_id = parking_slot_id;
		this.floorid = floorid;
		this.type = type;
		this.booking_id = booking_id;
	}

	/**
	 * @return the parking_slot_id
	 */
	public int getParking_slot_id() {
		return parking_slot_id;
	}

	/**
	 * @param parking_slot_id the parking_slot_id to set
	 */
	public void setParking_slot_id(int parking_slot_id) {
		this.parking_slot_id = parking_slot_id;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the booking_id
	 */
	public int getBooking_id() {
		return booking_id;
	}

	/**
	 * @param booking_id the booking_id to set
	 */
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	
	
}
