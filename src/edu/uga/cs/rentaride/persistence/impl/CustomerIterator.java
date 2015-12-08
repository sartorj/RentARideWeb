

package edu.uga.cs.rentaride.persistence.impl;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.object.ObjectLayer;


public class CustomerIterator
        implements Iterator<Customer>
{
    private ResultSet   rs = null;
    private ObjectLayer objectLayer = null;
    private boolean     more = false;

    // these two will be used to create a new object
    public CustomerIterator( ResultSet rs, ObjectLayer objectLayer )
            throws RARException
    {
        this.rs = rs;
        this.objectLayer = objectLayer;
        try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            throw new RARException( "CustomerIterator: Cannot create an iterator; root cause: " + e );
        }
    }

    public boolean hasNext()
    {
        return more;
    }

    public Customer next()
    {
    	long id;
    	String firstName;
    	String lastName;
    	String userName;
    	String emailAddress;
        String password;
        Date createDate;
        Date membershipExpiration;
        String licenseState;
        String licenseNumber;
        String residenceAddress; 
        String cardNumber;
        Date cardExpiration;
        Customer customer = null;
        

        if( more ) {

            try {
            	id = rs.getLong("id");
            	firstName = rs.getString("firstName");
            	lastName = rs.getString("lastName");
            	userName = rs.getString("userName");
            	emailAddress = rs.getString("emailAddress");
                password = rs.getString("password");
                createDate = rs.getDate("createdDate");
                membershipExpiration = rs.getDate("membershipExpiration");
                licenseState = rs.getString("licenseState");
                licenseNumber = rs.getString("licenseNumber");
                /*
                residenceAddress = rs.getString("residenceAddress");
                cardNumber = rs.getString("cardNumber");
                cardExpiration = rs.getDate("cardExpiration");
                */
                more = rs.next();
            }
            catch( Exception e ) {      // just in case...
                throw new NoSuchElementException( "CustomerIterator: No next Customer object; root cause: " + e );
            }
            
            customer = objectLayer.createCustomer(firstName, lastName, userName, emailAddress, password, createDate, membershipExpiration, licenseState, licenseNumber, "address1", "1234", new Date());
			customer.setId( id );

            return customer;
        }
        else {
            throw new NoSuchElementException( "CustomerIterator: No next Customer object" );
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}
