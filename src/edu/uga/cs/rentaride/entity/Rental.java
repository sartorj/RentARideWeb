package edu.uga.cs.rentaride.entity;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;

/** This class represent a rental event, involving a prior reservation, a vehicle being rented, and 
 * the customer.
 *
 */
public interface Rental 
    extends Persistable
{
    /** Return the date when the vehicle in this rental was picked up.
     * @return the pickup date for this rental
     */
    public Date getPickupTime();
    
    /** Set the date when the vehicle in this rental was picked up.
     * @param pickupTime the new pickup time of this rental
     */
    public void setPickupTime( Date pickupTime );
    
    /** Return the date when the vehicle in this rental was returned.
     * @return the date when the vehicle was returned
     */
    public Date getReturnTime();
    
    /** Set the date when the vehicle in this rental was returned.
     * @param returnTime the return time of this rental
     * @throws RARException in case the return time is not later than the pickup time
     */
    public void setReturnTime( Date returnTime ) throws RARException;
    
    /** Return the reservation for this rental.
     * @return the Reservation object of this rental
     */
    public Reservation getReservation();
    
    /** Set the reservation for this rental.
     * @param reservation the new Reservation object of this rental
     */
    public void setReservation( Reservation reservation );
    
    /** Return the vehicle for this rental.
     * @return the Vehicle object of this rental
     */
    public Vehicle getVehicle();
    
    /** Set the vehicle for this rental.
     * @param vehicle the new Vehicle for this rental
     */
    public void setVehicle( Vehicle vehicle );
    
     /** Get the charges involved in this rental.
     * @return charges the int charges for this rental
     */
    public int getCharges();
    
     /** Set the charges involved in this rental.
     * @param charges the new Customer for this rental
     */
    public void setCharges( int charges );
    
     /** Get the Customer involved in this rental.
     * @return Customer the Customer for this rental
     */
    public Customer setCustomer();
    
    /** Set the Customer involved in this rental.
     * @param Customer the new Customer for this rental
     */
    public void setCustomer( Customer customer );
    
}
