package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Vehicle;



/** This class represents a vehicle maintained by the Rent-A-Ride system.  In addition to
 * several vehicle attributes, each vehicle
 * has a vehicle type and is assigned to a rental location.  
 *
 */
public class VehicleImpl
    extends Persistent
    implements Vehicle
{
    // Vehicle attributes
    private String make;
    private String model;
    private int year;
    private int mileage;
    private String tag;
    private Date lastServiced;
    private VehicleStatus status;
    private VehicleCondition condition;
    private VehicleType type;
    private RentalLocation location;
    
    public VehicleImpl( VehicleType type, String make, String model, int year, String tag, int mileage, Date lastServiced, 
                        RentalLocation location, VehicleCondition condition, VehicleStatus status) 
    {
        super(-1);
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.tag = tag;
        this.lastServiced = lastServiced;
        this.status = status;
        this.condition = condition;
        this.type = type;
        this.location = location;
    }
    
    /** Return the make of this vehicle.
     * @return the make of this vehicle
     */
    public String getMake(){
        return make;
    }
    
    /** Set the make of this vehicle.
     * @param make the new make of this vehicle
     */
    public void   setMake( String make ){
        this.make = make;
    }
    
    /** Return the model of this vehicle.
     * @return the model of this vehicle
     */
    public String getModel(){
        return model;
    }
    
    /** Set the model of this vehicle.
     * @param model the new model of this vehicle
     */
    public void   setModel( String model ){
        this.model = model;
    }
    
    /** Return the year of this vehicle.
     * @return the year of this vehicle
     */
    public int    getYear(){
        return year;
    }
    
    /** Set the year of this vehicle.
     * @param year the new year of this vehicle
     * @throws RARException in case the year is not positive
     */
    public void   setYear( int year ) throws RARException {
        this.year = year;
    }
    
    /** Return the registration tag of this vehicle.
     * @return the registration tag of this vehicle
     */
    public String getRegistrationTag(){
        return tag;
    }
    
    /** Set the registration tag of this vehicle.
     * @param model the new registration tag of this vehicle
     */
    public void   setRegistrationTag( String registrationTag ){
        this.tag = registrationTag;
    }
    
    /** Return the mileage of this vehicle.
     * @return the mileage of this vehicle
     */
    public int    getMileage(){
        return mileage;
    }
    
    /** Set the mileage of this vehicle.
     * @param mileage the new mileage of this vehicle
     */
    public void   setMileage( int mileage ){
        this.mileage = mileage;
    }
    
    /** Return the last service date of this vehicle.
     * @return the last service date of this vehicle
     */
    public Date   getLastServiced(){
        return lastServiced;
    }
    
    /** Set the last service date of this vehicle.
     * @param lastServiced new last service date for this vehicle
     */
    public void   setLastServiced( Date lastServiced ){
        this.lastServiced = lastServiced;
    }
    
    /** Return the status of this vehicle (INLOCATION or INRENTAL)
     * @return status of this vehicle
     */
    public VehicleStatus getStatus(){
        return status;
    }
    
    /** Set the status of this vehicle (must be INLOCATION or INRENTAL)
     * @param status the new status of this vehicle (must be INLOCATION or INRENTAL)
     */
    public void   setStatus( VehicleStatus status ){
        this.status = status;
    }

    /** Return the condition of this vehicle (GOOD, NEEDSCLEANING, or NEEDSMAINTENANCE)
     * @return the condition of this vehicle
     */
    public VehicleCondition getCondition(){
        return condition;
    }
    
    /** Set the condition of this vehicle (must be GOOD, NEEDSCLEANING, or NEEDSMAINTENANCE)
     * @param condition the new condition of this vehicle (must be GOOD, NEEDSCLEANING, or NEEDSMAINTENANCE)
     */
    public void   setCondition( VehicleCondition condition ){
        this.condition = condition;
    }
    
    /** Return the vehicle type for this vehicle.
     * @return the VehicleType representing the type of this vehicle
     */
    public VehicleType getVehicleType(){
        return type;
    }
    
    /** Set the vehicle type for this vehicle.
     * @param vehicleType the new VehicleType representing the type of this vehicle
     */
    public void setVehicleType( VehicleType vehicleType ){
        this.type = vehicleType;
    }
    /** Return the rental location of this vehicle.
     * @return the rental location of this vehicle
     */
    public RentalLocation getRentalLocation(){
        return location;
    }
    
    /** Set the rental location of this vehicle.
     * @param rentalLocation the new rental location of this vehicle
     */
    public void setRentalLocation( RentalLocation rentalLocation ){
        this.location = rentalLocation;
    }
}
