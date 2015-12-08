package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.impl.*;
import edu.uga.cs.rentaride.entity.*;

import java.util.Date;

/** This class represents an Administrator user.  It has no additional attributes, and all are inherited from User.
 *
 */
public class AdministratorImpl
    extends UserImpl      
    implements Administrator
{
    private UserStatus userStatus;

	
    public AdministratorImpl(String fName, String lName, String uName, 
                    String email, String password, Date createDate, UserStatus userStatus,String address){
         
    		super(fName, lName, uName, email, password, createDate, userStatus,address);
     }

	@Override
	public void setUserStatus(UserStatus userStatus) {
		// TODO Auto-generated method stub
		this.userStatus = userStatus;
		
	}
}
