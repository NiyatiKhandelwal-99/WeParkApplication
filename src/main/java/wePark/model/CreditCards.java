package wePark.model;

import java.util.Date;

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
public class CreditCards {
	
	protected String cardNumber;
	protected int userid;
	protected Date expirationDate;
	
	/**
	 * @param cardNumber
	 * @param userid
	 * @param expirationDate
	 */
	public CreditCards(String cardNumber, int userid, Date expirationDate) {
		super();
		this.cardNumber = cardNumber;
		this.userid = userid;
		this.expirationDate = expirationDate;
	}
	
	public CreditCards(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	
}
