package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;


/** This class represents a reservation made by a Rent-A-Ride customer, for a vehicle type at a
 * specific rental location to be rented at a specific date and time.  The requested duration of
 * a rental must be expressed as a positive number of hours, which is less than or equal to 72.
 * Once the customer actually rents a vehicle, the reservation will involve a rental object.
 *
 */
public class ReservationImpl
    extends Persistent
    implements Reservation
{
     
    // Reservatio attributes
    private Date pickupTime;
    private int rentalDuration;
    private Customer customer;
    private VehicleType vehicleType;
    private RentalLocation rentalLocation;
    private Rental rental;
    
    /**
     * 
     */
     public ReservationImpl(Date pickupTime, int rentalDuration, Customer customer, VehicleType vehicleType,
                            RentalLocation rentalLocation){
        super(-1);
        this.pickupTime = pickupTime;
        this.rentalDuration = rentalDuration;
        this.customer = customer;
        this.vehicleType = vehicleType;
        this.rentalLocation = rentalLocation;
     }
    
    /** Return the intended pickup time.
     * @return the pickup time for this reservation
     */
    public Date           getPickupTime(){
        return pickupTime;
    }
    
    /** Set the intended pickup time.
     * @param pickupTime the new pickup time for this reservation
     * @throws RARException in case the pickup time is in the past
     */
    public void           setPickupTime( Date pickupTime ) throws RARException{
        this.pickupTime = pickupTime;
    }
    
    /** Return the intended rental duration (in hours).
     * @return the intended rental duration (in hours)
     */
    public int            getRentalDuration(){
        return rentalDuration;
    }
    
    /** Set the intended rental duration (in hours).
     * @param rentalDuration the new rental duration (in hours)
     * @throws RARException in case duration time is illegal
     */
    public void           setRentalDuration( int rentalDuration ) throws RARException{
        this.rentalDuration = rentalDuration;
    }
    
    /**
     * @return
     */
    public Customer       getCustomer(){
        return customer;
    }
    /**
     * @param customer
     */
    public void           setCustomer( Customer customer ){
        this.customer = customer;
    }
    /**
     * @return
     */
    public VehicleType    getVehicleType(){
        return vehicleType;
    }
    /**
     * @param vehicleType
     */
    public void           setVehicleType( VehicleType vehicleType ){
        this.vehicleType = vehicleType;
    }
    /**
     * @return
     */
    public RentalLocation getRentalLocation(){
        return rentalLocation;
    }
    /**
     * @param rentalLocation
     */
    public void           setRentalLocation( RentalLocation rentalLocation ){
        this.rentalLocation = rentalLocation;
    }
	
    public Rental getRental() 
    {
	return rental;
    }

    public void setRental(Rental rental) 
    {
	this.rental = rental;
    }
}
