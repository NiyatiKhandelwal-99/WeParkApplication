package wePark.model;

import java.sql.Date;

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
public class Booking {
	
	protected int booking_id;
	protected int userid;
	protected int vehicleid;
	protected Date start_time;
	protected int duration_in_min;
	protected Date booking_date;
	protected int parking_slot_id;
	
	/**
	 * @param booking_id
	 * @param userid
	 * @param vehicleid
	 * @param start_time
	 * @param duration_in_min
	 * @param booking_date
	 * @param parking_slot_id
	 */
	public Booking(int booking_id, int userid, int vehicleid, Date start_time, int duration_in_min, Date booking_date,
			int parking_slot_id) {
		super();
		this.booking_id = booking_id;
		this.userid = userid;
		this.vehicleid = vehicleid;
		this.start_time = start_time;
		this.duration_in_min = duration_in_min;
		this.booking_date = booking_date;
		this.parking_slot_id = parking_slot_id;
	}
	
	public Booking(int booking_id, int vehicleid, Date booking_date, int parking_slot_id) {
		super();
		this.booking_id = booking_id;
		this.vehicleid = vehicleid;
		this.booking_date = booking_date;
		this.parking_slot_id = parking_slot_id;
	}
	
	public Booking(int userid, int vehicleid, Date start_time, int duration_in_min, Date booking_date,
			int parking_slot_id) {
		this.userid = userid;
		this.vehicleid = vehicleid;
		this.start_time = start_time;
		this.duration_in_min = duration_in_min;
		this.booking_date = booking_date;
		this.parking_slot_id = parking_slot_id;
	}
	
	public Booking(int booking_id) {
		this.booking_id = booking_id;
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
	 * @return the start_time
	 */
	public Date getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	/**
	 * @return the duration_in_min
	 */
	public int getDuration_in_min() {
		return duration_in_min;
	}

	/**
	 * @param duration_in_min the duration_in_min to set
	 */
	public void setDuration_in_min(int duration_in_min) {
		this.duration_in_min = duration_in_min;
	}

	/**
	 * @return the booking_date
	 */
	public Date getBooking_date() {
		return booking_date;
	}

	/**
	 * @param booking_date the booking_date to set
	 */
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
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

}
