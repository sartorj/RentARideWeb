//
// A control class to implement the 'Create Rental Location' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.Iterator;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class CreateRentalLocationCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateRentalLocationCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createRentalLocation( String name, String addr, int capacity )
            throws RARException
    { 
        RentalLocation 	            rentalLocation  = null;
        RentalLocation              modelRentalLocation = null;
        Iterator<RentalLocation>    rentalLocationIter = null;

        // check if a rental location with this name already exists (name is unique)
        modelRentalLocation = objectLayer.createRentalLocation();
        modelRentalLocation.setName( name );
        rentalLocationIter = objectLayer.findRentalLocation( modelRentalLocation );
        if( rentalLocationIter.hasNext() )
            throw new RARException( "A rental location with this name already exists: " + name );

        // create the rentalLocation
        rentalLocation = objectLayer.createRentalLocation( name, addr, capacity );
        objectLayer.storeRentalLocation( rentalLocation );

        return rentalLocation.getId();
    }
}
