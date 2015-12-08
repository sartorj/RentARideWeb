

package edu.uga.cs.rentaride.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;


public class HourlyPriceIterator
    implements Iterator<HourlyPrice>
{
    private ResultSet   rs = null;
    private ObjectLayer objectLayer = null;
    private boolean     more = false;

    // these two will be used to create a new object
    //
    public HourlyPriceIterator( ResultSet rs, ObjectLayer objectLayer )
            throws RARException
    { 
        this.rs = rs;
        this.objectLayer = objectLayer;
        try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            throw new RARException( "HourlyPriceIterator: Cannot create an iterator; root cause: " + e );
        }
    }

    public boolean hasNext() 
    { 
        return more; 
    }

    public HourlyPrice next() 
    {
    	long id;
    	int maxHours;
    	int minHours;
    	int price;
    	VehicleType vehicleType = null;
    	String vt;
    	HourlyPrice hourlyPrice = null;
        
        
        if( more ) {

            try {
            	id = rs.getLong("id");
                maxHours = rs.getInt("maxHours");
                minHours = rs.getInt("minHours");
                price = rs.getInt("price");
                vt = rs.getString("vehicleType");
                vehicleType = objectLayer.createVehicleType(vt); 
                more = rs.next();
            }
            catch( Exception e ) {      // just in case...
                throw new NoSuchElementException( "HourlyPriceIterator: No next HourlyPrice object; root cause: " + e );
            }
            

            try {
            	hourlyPrice = objectLayer.createHourlyPrice(maxHours, minHours, price, vehicleType ); 
            	hourlyPrice.setId(id);
            }
            catch( RARException ce ) {
            	// TODO: Figure out why RARException is not called for VehicleType
               ce.printStackTrace();
               System.out.println( ce );
            }

            return hourlyPrice;
        }
        else {
            throw new NoSuchElementException( "HourlyPriceIterator: No next HourlyPrice object" );
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}
