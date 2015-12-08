package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.RentARideConfig;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

/** This class represents the configuration parameters of the Rent-A-Ride system.
 * Note that the prices are always expressed in Cents, not Dollars.
 * This is a singleton class.
 *
 */
public class RentARideConfigImpl
    extends Persistent
    implements RentARideConfig
{

    // Config attricbutes
    private int membershipPrice;
    private int overtimePenalty;
    
    /**
     * 
     */
     public RentARideConfigImpl( int membershipPrice, int overtimePenalty)
     {
         super(-1);
         this.membershipPrice = membershipPrice;
         this.overtimePenalty = overtimePenalty;
     }
    
    /** Return the current price of the Rent-A-Ride membership.
     * @return the Rent-A-Ride current price (in cents) of the Rent-A-Ride membership
     */
    public int getMembershipPrice(){
        return membershipPrice;
    }
    
    /** Set the price of the Rent-A-Ride membership.
     * @param membershipPrice the new price (in cents) of the Rent-A-Ride membership
     */
    public void setMembershipPrice( int membershipPrice ){
        this.membershipPrice = membershipPrice;
    }
    
    /** Return the current overtime penalty of the Rent-A-Ride membership.
     * @return the Rent-A-Ride current overtime penalty (in cents)
     */
    public int getOvertimePenalty(){
        return overtimePenalty;
    }
    
    /** Set the overtime penalty of the Rent-A-Ride membership.
     * @param overtimePenalty the new overtime penalty (in cents)
     */
    public void setOvertimePenalty( int overtimePenalty ){
        this.overtimePenalty = overtimePenalty;
    }
}
