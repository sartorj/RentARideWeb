package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.entity.User;

/** This is the base class of all known users of the Rent-A-Ride system.
 *
 */
public abstract class UserImpl
    extends Persistent 
    implements User
{
    // User attributes
    private String fName;
    private String lName;
    private String uName;
    private String email;
    private String password;
    private String residenceAddress;
    private Date createDate;
    private UserStatus userStatus;
    
    /**
     * Constructor for UserImpl
     */
    
     public UserImpl(String fName, String lName, String uName, 
                    String email, String password, Date createDate, UserStatus userStatus, String residenceAddress){
         super(-1);
         this.fName = fName;
         this.lName = lName;
         this.uName = uName;
         this.email = email;
         this.password = password;
         this.createDate = createDate;
         this.userStatus = userStatus;
         this.residenceAddress = residenceAddress;
     }
    
    
    /** Return the user's first name.
     * @return the user's first name
     */
    public String getFirstName(){
        return fName;
    }
    
    /** Set the user's first name.
     * @param firstName the new first name
     */
    public void   setFirstName( String firstName ){
        this.fName = firstName;
    }
    
    /** Return the user's last name.
     * @return the user's last name
     */
    public String getLastName(){
        return lName;
    }
    
    /** Set the user's last name.
     * @param firstName the new last name
     */
    public void   setLastName( String lastName ){
        this.lName = lastName;
    }
    
    /** Return the user's user (login) name.
     * @return the user's user (login) name
     */
    public String getUserName(){
        return uName;
    }
    
    /** Set the user's user (login) name.
     * @param userName the new user (login) name
     */
    public void   setUserName( String userName ){
        this.uName = userName;
    }
    
    /** Return the user's email address.
     * @return the user's email address
     */
    public String getEmailAddress(){
        return email;
    }
    
    /** Set the user's email address.
     * @param emailAddress the new email address
     */
    public void   setEmailAddress( String emailAddress ){
        this.email = emailAddress;
    }
    
    /** Return the user's password.
     * @return the user's password
     */
    public String getPassword(){
        return password;
    }
    
    /** Set the user's password.
     * @param password the new password
     */
    public void   setPassword( String password ){
        this.password = password;
    }
    
    /** Return the user's creation date.
     * @return the user's creation date
     */
    public Date   getCreatedDate(){
        return createDate;
    }
    
    /** Set the user's creation date.
     * @param createDate the new creation date
     */
    public void   setCreateDate( Date createDate ){
        this.createDate = createDate;
    }
    
    /** Return the current status of this user (ACTIVE, REMOVED, or TERMINATED)
     * @return the current status of this user
     */
    public UserStatus getUserStatus(){
        return userStatus;
    }
    
    /** Set the current status of this user (must be ACTIVE, REMOVED, or TERMINATED)
     * @param userStatus the new status of this user (must be ACTIVE, REMOVED, or TERMINATED)
     */
    public void setUserStatus( UserStatus userStatus ){
        this.userStatus = userStatus;
    }
    
    /** Return the residence address of this user.
     * @return the address of this user's residence
     */
    public String getResidenceAddress()
    {
        return residenceAddress;
    }
    
    /** Set the new residence address of this user.
     * @param residenceAddress the new residence address of this user
     */
    public void   setResidenceAddress( String residenceAddress )
    {
        this.residenceAddress = residenceAddress;
    }
}
