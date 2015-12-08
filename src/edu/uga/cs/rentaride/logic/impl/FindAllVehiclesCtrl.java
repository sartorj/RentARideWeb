//
// A control class to implement the 'List all vehicles' use case
//
//


package edu.uga.cs.rentaride.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class FindAllVehiclesCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindAllVehiclesCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Vehicle> findAllVehicless()
            throws RARException
    {
        List<Vehicle> 	    vehicles  = null;
        Iterator<Vehicle> 	vehicleIter = null;
        Vehicle     	      vehicle = null;

        vehicles = new LinkedList<Vehicle>();
        
        // retrieve all Vehicle objects
        //
        vehicleIter = objectLayer.findVehicle( null );
        while( vehicleIter.hasNext() ) {
            vehicle = vehicleIter.next();
            System.out.println( vehicle);
            vehicles.add( vehicle );
        }

        return vehicles;
    }
}