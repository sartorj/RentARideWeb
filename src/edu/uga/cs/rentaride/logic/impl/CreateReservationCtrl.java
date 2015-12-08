//
// A control class to implement the 'make a reservation' use case
//
//


package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.Iterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class CreateReservationCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateReservationCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createReservation( String pickupTime, int rentalDuration, String rentalLocationStr, String name, String vehicleTypeStr)
            throws RARException
    { 
        Date 		                   reservationTime = null;
        Reservation 		           reservation  = null;
        //Reservation                modelReservation = null;
       // Iterator<Reservation>      reservationIter = null;
        Customer                   customer = null;
        Customer                   modelCustomer = null;
        Iterator<Customer>         customerIter = null;
        RentalLocation                   rentalLocation = null;
        RentalLocation                   modelRentalLocation = null;
        Iterator<RentalLocation>         rentalLocationIter = null;
        VehicleType                   vehicleType = null;
        VehicleType                   modelVehicleType = null;
        Iterator<VehicleType>         vehicleTypeIter = null;
        
        long						customerId = 0;
        

        // check if a club with this name already exists (name is unique)
        //modelClub = objectLayer.createClub();
        //modelClub.setName( club_name );
        //clubIter = objectLayer.findClub( modelClub );
        //if( clubIter.hasNext() )
        //    throw new ClubsException( "A club with this name already exists: " + club_name );

        // retrieve the founder person
        modelCustomer = objectLayer.createCustomer();
        modelCustomer.setUserName( name );
        customerIter = objectLayer.findCustomer( modelCustomer );
        while( customerIter.hasNext() ) {
            customer = customerIter.next();
        }
        
        customerId = customer.getId();

        // check if the person actually exists
        if( customer == null )
            throw new RARException( "A person with this id does not exist: " + customerId );

        // retrieve the rental location 
        modelRentalLocation = objectLayer.createRentalLocation();
        modelRentalLocation.setName( rentalLocationStr );
        rentalLocationIter = objectLayer.findRentalLocation( modelRentalLocation );
        while( rentalLocationIter.hasNext() ) {
            rentalLocation = rentalLocationIter.next();
        }

        // check if the rental location actually exists
        if( rentalLocation == null )
            throw new RARException( "A Rental location with this name does not exist: " + rentalLocationStr );
            
        // retrieve the founder person
        modelVehicleType = objectLayer.createVehicleType();
        modelVehicleType.setType( vehicleTypeStr );
        vehicleTypeIter = objectLayer.findVehicleType( modelVehicleType );
        while( vehicleTypeIter.hasNext() ) {
            vehicleType = vehicleTypeIter.next();
        }

        // check if the vehicle type actually exists
        if( vehicleType == null )
            throw new RARException( "A Vehicle type with this name does not exist: " + vehicleTypeStr );
            
        // check if the vehicle type actually exists
        if( rentalDuration <= 0 )
            throw new RARException( "Rental Duration is not positive " + rentalDuration );
        
        // create the reservation
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
          reservationTime = format.parse(pickupTime);
        } catch (ParseException e1){
          e1.printStackTrace();
        }
        reservation = objectLayer.createReservation( vehicleType, rentalLocation, customer, reservationTime, rentalDuration );
        objectLayer.storeReservation( reservation );

        return reservation.getId();
    }
}