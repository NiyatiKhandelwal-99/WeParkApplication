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
public class RegularPass {
	
	protected int passid;
	protected int userid;
	protected int parkinglotid;
	protected Date purchase_date;
	protected Date start_date;
	protected int duration_in_days;
	protected float cost;
	protected String parkingLotName;


	/**
	 * @param passid
	 * @param userid
	 * @param parkinglotid
	 * @param purchase_date
	 * @param start_date
	 * @param duration_in_days
	 * @param cost
	 */
	public RegularPass(int passid, int userid, int parkinglotid, Date purchase_date, Date start_date,
			int duration_in_days, float cost) {
		super();
		this.passid = passid;
		this.userid = userid;
		this.parkinglotid = parkinglotid;
		this.purchase_date = purchase_date;
		this.start_date = start_date;
		this.duration_in_days = duration_in_days;
		this.cost = cost;
	}
	
	/**
	 * @param passid
	 * @param userid
	 * @param parkinglotid
	 * @param purchase_date
	 * @param start_date
	 * @param duration_in_days
	 * @param cost
	 * @param parkingLotName
	 */
	public RegularPass(int passid, int userid, Date purchase_date, Date start_date,
			int duration_in_days, float cost, String parkingLotName) {
		super();
		this.passid = passid;
		this.userid = userid;
		this.purchase_date = purchase_date;
		this.start_date = start_date;
		this.duration_in_days = duration_in_days;
		this.cost = cost;
		this.parkingLotName = parkingLotName;
	}
	
	public RegularPass(int userid, int parkinglotid, Date purchase_date, Date start_date,
			int duration_in_days, float cost) {
		super();
		this.userid = userid;
		this.parkinglotid = parkinglotid;
		this.purchase_date = purchase_date;
		this.start_date = start_date;
		this.duration_in_days = duration_in_days;
		this.cost = cost;
	}

	/**
	 * @return the passid
	 */
	public int getPassid() {
		return passid;
	}

	/**
	 * @param passid the passid to set
	 */
	public void setPassid(int passid) {
		this.passid = passid;
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
	 * @return the purchase_date
	 */
	public Date getPurchase_date() {
		return purchase_date;
	}

	/**
	 * @param purchase_date the purchase_date to set
	 */
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the duration_in_days
	 */
	public int getDuration_in_days() {
		return duration_in_days;
	}

	/**
	 * @param duration_in_days the duration_in_days to set
	 */
	public void setDuration_in_days(int duration_in_days) {
		this.duration_in_days = duration_in_days;
	}

	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}

	
	public String getParkingLotName() {
		return parkingLotName;
	}

	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	
	
}
