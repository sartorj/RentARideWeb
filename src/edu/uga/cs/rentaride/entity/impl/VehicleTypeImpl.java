package edu.uga.cs.rentaride.entity.impl;


import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.entity.VehicleType;


/** This class represents possible vehicle types of all vehicles in the Rent-A-Ride system.
 * The types should be similar to sedan, pickup, luxury sedan, etc.
 *
 */
public class VehicleTypeImpl
    extends Persistent
    implements VehicleType
{
    // VehicleType attributes
    private String vehicleType;
    
    public VehicleTypeImpl( String type )
    {
        super(-1);
        this.vehicleType = type;
    }
    
    /** Return the description of this vehicle type.
     * @return description of this vehicle type
     */
    public String getType(){
        return vehicleType;
    }
    
    /** Set the description of this vehicle type.
     * @param type the new description of this vehicle type
     */
    public void setType( String type ){
        this.vehicleType = type;
    }
}
