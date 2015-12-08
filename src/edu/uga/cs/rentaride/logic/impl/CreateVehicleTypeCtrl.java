//
// A control class to implement the 'Create Vehicle Type' use case
//
//

package edu.uga.cs.rentaride.logic.impl;

import java.util.Iterator;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class CreateVehicleTypeCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateVehicleTypeCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createVehicleType( String type)
            throws RARException
    { 
        VehicleType	            vehicleType  = null;
        VehicleType              modelVehicleType = null;
        Iterator<VehicleType>    vehicleTypeIter = null;

        // check if a Vehicle Type with this name already exists (name is unique)
        modelVehicleType = objectLayer.createVehicleType();
        modelVehicleType.setType( type );
        vehicleTypeIter = objectLayer.findVehicleType( modelVehicleType );
        if( vehicleTypeIter.hasNext() )
            throw new RARException( "This Vehicle Type already exists: " + type );

        // create the vehicleType
        vehicleType = objectLayer.createVehicleType( type );
        objectLayer.storeVehicleType( vehicleType );

        return vehicleType.getId();
    }
}