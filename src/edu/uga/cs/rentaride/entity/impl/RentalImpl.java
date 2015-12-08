package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.Customer;

/** This class represent a rental event, involving a prior reservation, a vehicle being rented, and 
 * the customer.
 *
 */
public class RentalImpl 
    extends Persistent
    implements Rental
{
    // Rental attributes
    private Date pickupTime;
    private Reservation reservation;
    private Vehicle vehicle;
    private Customer customer;
    private Date returnTime;
    private int charges;
    
    
    /**
     *  Constructor for RentalImpl
     */
    public RentalImpl(Date pickupTime, Date returnTime, Reservation reservation, Vehicle vehicle, Customer customer, int charges){
        super(-1);
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.reservation = reservation;
        this.vehicle = vehicle;
        this.customer = customer;
        this.charges = charges;
        
    }
    
    /** Return the date when the vehicle in this rental was picked up.
     * @return the pickup date for this rental
     */
    public Date getPickupTime(){
        return pickupTime;
    }
    
    /** Set the date when the vehicle in this rental was picked up.
     * @param pickupTime the new pickup time of this rental
     */
    public void setPickupTime( Date pickupTime ){
        this.pickupTime = pickupTime;
    }
    
    /** Return the date when the vehicle in this rental was returned.
     * @return the date when the vehicle was returned
     */
    public Date getReturnTime(){
        return returnTime;
    }
    
    /** Set the date when the vehicle in this rental was returned.
     * @param returnTime the return time of this rental
     * @throws RARException in case the return time is not later than the pickup time
     */
    public void setReturnTime( Date returnTime ) throws RARException{
        this.returnTime = returnTime;
    }
    
    /** Return the reservation for this rental.
     * @return the Reservation object of this rental
     */
    public Reservation getReservation(){
        return reservation;
    }
    
    /** Set the reservation for this rental.
     * @param reservation the new Reservation object of this rental
     */
    public void setReservation( Reservation reservation ){
        this.reservation = reservation;
    }
    
    /** Return the vehicle for this rental.
     * @return the Vehicle object of this rental
     */
    public Vehicle getVehicle(){
        return vehicle;
    }
    
    /** Set the vehicle for this rental.
     * @param vehicle the new Vehicle for this rental
     */
    public void setVehicle( Vehicle vehicle ){
        this.vehicle = vehicle;
    }
    
    /** Return the customer involved in this rental.
     * @return the Customer object of this rental
     */
    public Customer getCustomer(){
        return customer;
    }
    
    /** Set the customer involved in this rental.
     * @param customer the new Customer for this rental
     */
    public void setCustomer( Customer customer ){
        this.customer = customer;
    }
    
    /** Return the charges involved in this rental.
     * @return the int charges of this rental
     */
    public int getCharges(){
        return charges;
    }
    
    /** Set the charges involved in this rental.
     * @param charges the new charges for this rental
     */
    public void setCharges( int charges ){
        this.charges = charges;
    }

	@Override
	public Customer setCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

}
