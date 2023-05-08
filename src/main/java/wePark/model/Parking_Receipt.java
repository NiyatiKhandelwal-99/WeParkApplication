package wePark.model;

import java.sql.Timestamp;

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
public class Parking_Receipt {
	
	protected int receiptid;
	protected int booking_id;
	protected Timestamp actual_entry_time;
	protected Timestamp actual_exit_time;
	protected float basic_cost;
	protected float penalty;
	protected float total_cost;
	protected boolean is_paid;
	
	/**
	 * @param receiptid
	 * @param booking_id
	 * @param actual_entry_time
	 * @param actual_exit_time
	 * @param basic_cost
	 * @param penalty
	 * @param total_cost
	 * @param is_paid
	 */
	public Parking_Receipt(int receiptid, int booking_id, Timestamp actual_entry_time, Timestamp actual_exit_time,
			float basic_cost, float penalty, float total_cost, boolean is_paid) {
		super();
		this.receiptid = receiptid;
		this.booking_id = booking_id;
		this.actual_entry_time = actual_entry_time;
		this.actual_exit_time = actual_exit_time;
		this.basic_cost = basic_cost;
		this.penalty = penalty;
		this.total_cost = total_cost;
		this.is_paid = is_paid;
	}

	/**
	 * @return the receiptid
	 */
	public int getReceiptid() {
		return receiptid;
	}

	/**
	 * @param receiptid the receiptid to set
	 */
	public void setReceiptid(int receiptid) {
		this.receiptid = receiptid;
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
	 * @return the actual_entry_time
	 */
	public Timestamp getActual_entry_time() {
		return actual_entry_time;
	}

	/**
	 * @param actual_entry_time the actual_entry_time to set
	 */
	public void setActual_entry_time(Timestamp actual_entry_time) {
		this.actual_entry_time = actual_entry_time;
	}

	/**
	 * @return the actual_exit_time
	 */
	public Timestamp getActual_exit_time() {
		return actual_exit_time;
	}

	/**
	 * @param actual_exit_time the actual_exit_time to set
	 */
	public void setActual_exit_time(Timestamp actual_exit_time) {
		this.actual_exit_time = actual_exit_time;
	}

	/**
	 * @return the basic_cost
	 */
	public float getBasic_cost() {
		return basic_cost;
	}

	/**
	 * @param basic_cost the basic_cost to set
	 */
	public void setBasic_cost(float basic_cost) {
		this.basic_cost = basic_cost;
	}

	/**
	 * @return the penalty
	 */
	public float getPenalty() {
		return penalty;
	}

	/**
	 * @param penalty the penalty to set
	 */
	public void setPenalty(float penalty) {
		this.penalty = penalty;
	}

	/**
	 * @return the total_cost
	 */
	public float getTotal_cost() {
		return total_cost;
	}

	/**
	 * @param total_cost the total_cost to set
	 */
	public void setTotal_cost(float total_cost) {
		this.total_cost = total_cost;
	}

	/**
	 * @return the is_paid
	 */
	public boolean isIs_paid() {
		return is_paid;
	}

	/**
	 * @param is_paid the is_paid to set
	 */
	public void setIs_paid(boolean is_paid) {
		this.is_paid = is_paid;
	}
	
	
	
}
