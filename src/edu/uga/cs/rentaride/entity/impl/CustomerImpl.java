package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.RARException;


/** This class represents information about a registered customer of Rent-A-Ride.
 *
 */
public class CustomerImpl
    extends UserImpl
    implements Customer
{
    //attributes
    private Date    membershipExpiration;
    private String  licenseState;
    private String  licenseNumber;
    private String  cardNumber;
    private Date    cardExpiration;
    
    public CustomerImpl(Date membershipExpiration, String licenseState, String licenseNumber, 
                        String residenceAddress, String cardNumber, Date cardExpiration, String fName,
                        String lName, String uName, String email, String password, 
                        Date createDate, UserStatus userStatus)
    {
        super(fName, lName, uName, email, password, createDate, userStatus, residenceAddress);
        this.membershipExpiration = membershipExpiration;
        this.licenseState = licenseState;
        this.licenseNumber = licenseNumber;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
    }
    
    /** Return the expiration Date of this Customer's membership in Rent-A-Ride.
     * @return the membership expiration Date for this customer 
     */
    public Date   getMembershipExpiration()
    {
        return membershipExpiration;
    }
    
    /** Set the expiration Date of this Customer's membership in Rent-A-Ride.
     * @param membershipExpiration the new expiration Date for this customer
     * @throws RARException in case the membership date is in the past
     */
    public void   setMembershipExpiration( Date membershipExpiration ) throws RARException
    {
        //check for RARException
        this.membershipExpiration = membershipExpiration;
    }
    
    /** Return the license state for this customer.
     * @return the String representing the state of the customer's license
     */
    public String getLicenseState()
    {
        return licenseState;
    }
    
    /** Set the new license state for this customer.
     * @param state the new state of this customer's license
     */
    public void   setLicenseState( String state )
    {
        this.licenseState = state;
    }
    
    /** Return the license number of this customer.
     * @return the license number of this customer
     */
    public String getLicenseNumber()
    {
        return licenseNumber;
    }
    
    /** Set the new license number of this customer.
     * @param licenseNumber the new license number of this customer
     */
    public void   setLicenseNumber( String licenseNumber )
    {
        this.licenseNumber = licenseNumber;
    }
    
    /** Return the credit card number of this customer.
     * @return the credit card number of this customer
     */
    public String getCreditCardNumber()
    {
        return cardNumber;
    }
    
    /** Set the new credit card number of this customer.
     * @param cardNumber the new credit card number of this customer
     */
    public void   setCreditCardNumber( String cardNumber )
    {
        this.cardNumber = cardNumber;
    }
    
    /** Return the expiration date of this customer's credit card.
     * @return the expiration date of this customer's credit card
     */
    public Date   getCreditCardExpiration()
    {
        return cardExpiration;
    }
    
    /** Set the new expiration date of this customer's credit card.
     * @param cardExpiration the new expiration date of this customer's credit card
     */
    public void   setCreditCardExpiration( Date cardExpiration)
    {
        this.cardExpiration = cardExpiration;
    }

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return super.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return super.getUserName();
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		super.setUserName(userName);
	}

	@Override
	public String getEmailAddress() {
		// TODO Auto-generated method stub
		return super.getEmailAddress();
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		super.setPassword(password);
	}

	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return super.getCreatedDate();
	}

	@Override
	public void setCreateDate(Date createDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserStatus getUserStatus() {
		// TODO Auto-generated method stub
		return super.getUserStatus();
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getResidenceAddress() {
		// TODO Auto-generated method stub
		return super.getResidenceAddress();
	}

	@Override
	public void setResidenceAddress(String residenceAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return super.isPersistent();
	}
}
