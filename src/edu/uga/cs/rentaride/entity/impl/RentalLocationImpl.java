package edu.uga.cs.rentaride.entity.impl;


import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.entity.RentalLocation;

/** This class represents a rental location in the Rent-A-Ride system.
 *
 */
public class RentalLocationImpl
    extends Persistent
    implements RentalLocation
{
    // RentalLocation attributes
    private String name;
    private String address;
    private int capacity;
    
    /**
     * 
     */
     public RentalLocationImpl(String name, String address, int capacity){
         super(-1);
         this.name = name;
         this.address = address;
         this.capacity = capacity;
     }
     
    /** Return the name of this rental location
     * @return the name of this rental location
     */
    public String getName(){
        return name;
    }
    
    /** Set the name of this rental location
     * @param name the new name of this rental location
     */
    public void setName( String name ){
        this.name = name;
    }
    
    /** Return the address of this rental location
     * @return the address of this rental location
     */
    public String getAddress(){
        return address;
    }
    
    /** Set the address of this rental location
     * @param address the new address for this rental location
     */
    public void setAddress( String address ){
        this.address = address;
    }
    
    /** Return the capacity of this rental location
     * @return the capacity of this rental location
     */
    public int getCapacity(){
        return capacity;
    }
    
    /** Set the capacity of this rental location
     * @param capacity the new capacity of this rental location
     */
    public void setCapacity( int capacity ){
        this.capacity = capacity;
    }
}
