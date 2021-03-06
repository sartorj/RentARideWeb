package edu.uga.cs.rentaride.entity;

import java.util.Date;

import edu.uga.cs.rentaride.persistence.Persistable;


/** This is the base class of all known users of the Rent-A-Ride system.
 *
 */
public interface User
    extends Persistable
{
    /** Return the user's first name.
     * @return the user's first name
     */
    public String getFirstName();
    
    /** Set the user's first name.
     * @param firstName the new first name
     */
    public void   setFirstName( String firstName );
    
    /** Return the user's last name.
     * @return the user's last name
     */
    public String getLastName();
    
    /** Set the user's last name.
     * @param firstName the new last name
     */
    public void   setLastName( String lastName );
    
    /** Return the user's user (login) name.
     * @return the user's user (login) name
     */
    public String getUserName();
    
    /** Set the user's user (login) name.
     * @param userName the new user (login) name
     */
    public void   setUserName( String userName );
    
    /** Return the user's email address.
     * @return the user's email address
     */
    public String getEmailAddress();
    
    /** Set the user's email address.
     * @param emailAddress the new email address
     */
    public void   setEmailAddress( String emailAddress );
    
    /** Return the user's password.
     * @return the user's password
     */
    public String getPassword();
    
    /** Set the user's password.
     * @param password the new password
     */
    public void   setPassword( String password );
    
    /** Return the user's creation date.
     * @return the user's creation date
     */
    public Date   getCreatedDate();
    
    /** Set the user's creation date.
     * @param createDate the new creation date
     */
    public void   setCreateDate( Date createDate );
    
    /** Return the current status of this user (ACTIVE, REMOVED, or TERMINATED)
     * @return the current status of this user
     */
    public UserStatus getUserStatus();
    
    /** Set the current status of this user (must be ACTIVE, REMOVED, or TERMINATED)
     * @param userStatus the new status of this user (must be ACTIVE, REMOVED, or TERMINATED)
     */
    public void setUserStatus( UserStatus userStatus );
     
    /** Return the residence address of this user.
     * @return the address of this user's residence
     */
    public String getResidenceAddress();
    
    /** Set the new residence address of this user.
     * @param residenceAddress the new residence address of this user
     */
    public void   setResidenceAddress( String residenceAddress );
}
