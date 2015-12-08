//
// A control class to implement the 'List all rental locations' use case
//
//


package edu.uga.cs.rentaride.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class FindAllRentalLocationsCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindAllRentalLocationsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<RentalLocation> findAllRentalLocations()
            throws RARException
    {
        List<RentalLocation> 	    rentalLocations  = null;
        Iterator<RentalLocation> 	rentalLocationIter = null;
        RentalLocation     	      rentalLocation = null;

        rentalLocations = new LinkedList<RentalLocation>();
        
        // retrieve all Rental Location objects
        //
        rentalLocationIter = objectLayer.findRentalLocation( null );
        while( rentalLocationIter.hasNext() ) {
            rentalLocation = rentalLocationIter.next();
            System.out.println( rentalLocation);
            rentalLocations.add( rentalLocation );
        }

        return rentalLocations;
    }
}
