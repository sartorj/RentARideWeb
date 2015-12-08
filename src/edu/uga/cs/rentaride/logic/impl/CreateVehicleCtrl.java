//
// A control class to implement the 'Create Vehicle' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.Iterator;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class CreateVehicleCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateVehicleCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createVehicle( String registrationTag, Date lastService, String make, 
                               int mileage, String model, RentalLocation rl, VehicleStatus vehicleStatus, 
                               VehicleType vehicleType, int vehicleYear, VehicleCondition vehicleCondition)
            throws RARException
    { 
        Vehicle 	       vehicle  = null;
        Vehicle            modelVehicle = null;
        Iterator<Vehicle>  vehicleIter = null;
        RentalLocation  rentalLocation  = null;
        VehicleStatus          status          = null;


        // check if a vehicle with this name already exists (name is unique)
        modelVehicle = objectLayer.createVehicle();
        modelVehicle.setRegistrationTag(registrationTag);
        vehicleIter = objectLayer.findVehicle( modelVehicle );
        if( vehicleIter.hasNext() )
            throw new RARException( "A vehicle with this registration tag already exists: " + registrationTag );

        // create the vehicle
        //rentalLocation = objectLayer.createRentalLocation(rl,null,0);
        //status = VehicleStatus.valueOf("status_str");
        status = VehicleStatus.INLOCATION;
        //vehicleType = objectLayer.createVehicleType("vehicleType_str");
        //vehicleCondition = VehicleCondition.valueOf("vehicleCondition_str");
        vehicleCondition = VehicleCondition.GOOD;
        
        vehicle = objectLayer.createVehicle(vehicleType, make, model, vehicleYear, registrationTag, mileage, lastService, rl, vehicleCondition, status);
        //vehicle = objectLayer.createVehicle( registrationTag, lastService, make, mileage, model, rentalLocation, status, vehicleType,vehicleYear, vehicleCondition);
        objectLayer.storeVehicle( vehicle );

        return vehicle.getId();
    }
}