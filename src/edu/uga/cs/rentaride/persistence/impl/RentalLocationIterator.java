

package edu.uga.cs.rentaride.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.object.ObjectLayer;


public class RentalLocationIterator
        implements Iterator<RentalLocation>
{
    private ResultSet   rs = null;
    private ObjectLayer objectLayer = null;
    private boolean     more = false;

    // these two will be used to create a new object
    public RentalLocationIterator( ResultSet rs, ObjectLayer objectLayer )
            throws RARException
    {
        this.rs = rs;
        this.objectLayer = objectLayer;
        try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            throw new RARException( "RentalLocationIterator: Cannot create an iterator; root cause: " + e );
        }
    }

    public boolean hasNext()
    {
        return more;
    }

    public RentalLocation next()
    {
    	long id;
        RentalLocation rentallocation = null;
        String name;
        String address; 
        int capacity;
        

        if( more ) {

            try {
            	id = rs.getLong("rentalId");
                name = rs.getString("locationName");
                address = rs.getString("address");
                capacity = rs.getInt("capacity");
                
                more = rs.next();
            }
            catch( Exception e ) {      // just in case...
                throw new NoSuchElementException( "RentalLocationIterator: No next RentalLocation object; root cause: " + e );
            }
            
            rentallocation = objectLayer.createRentalLocation(name, address, capacity);
			rentallocation.setId( id );

            return rentallocation;
        }
        else {
            throw new NoSuchElementException( "RentalLocationIterator: No next RentalLocation object" );
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}
