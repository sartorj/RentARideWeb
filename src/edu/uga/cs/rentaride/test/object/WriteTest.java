package edu.uga.cs.rentaride.test.object;

import java.sql.Connection;
import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class WriteTest
{
    public static void main(String[] args)
    {
         Connection conn = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;
         
         Administrator        admin;
         Comment        comment;
         Customer        customer;
         HourlyPrice        hourlyPrice;
         Rental      rental;
         RentalLocation      rentalLocation;
         RentARideConfig      config;
         Reservation      reservation;
         User      user;
         UserStatus  userstatus;
         Vehicle	vehicle;
         VehicleCondition	vehicleCondition;
         VehicleStatus	vehicleStatus;
         VehicleType	vehicleType;
         
         // get a database connection
         try {
             conn = DbUtils.connect();
         } 
         catch (Exception seq) {
             System.err.println( "WriteTest: Unable to obtain a database connection" );
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( conn, objectLayer );
         
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence );   

         try {
             
        	 
        	//save rentallocation works
        	 rentalLocation = objectLayer.createRentalLocation("testlocation121233333", "124123 main st", 1);
        	 persistence.storeRentalLocation(rentalLocation);
        	
        	 //need to add customer attribute sto db
        	 
        	 customer = objectLayer.createCustomer("jon", "doe", "jduser", "jd@test.com", "pass", new Date(), new Date(), "GA", "1234", "123 main st", "99999", new Date());
        	 persistence.storeCustomer(customer);
        	 
        	
            
        	 /* no create rentalconfig?
        	 config = objectLayer.
            */
        	 
        	 //save vehicletype works
        	 vehicleType = objectLayer.createVehicleType("Truck");
        	 persistence.storeVehicleType(vehicleType);
        	 
        	 //some sort of sql mismatch
        	
        	 vehicle = objectLayer.createVehicle(vehicleType, "nissan", "frontier", 2006, "123ABC", 40000, new Date(), rentalLocation, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
        	 persistence.storeVehicle(vehicle);
        	 
        	 
        	 /* needs customer first
        	 Date pickupTime = new Date();
        	 int rentalDuration = 2;
			reservation = objectLayer.createReservation(vehicleType, rentalLocation, customer, pickupTime, rentalDuration );
        	 */
        	 
        	 
             System.out.println( "Entity objects created and saved in the persistence module" );
             
         }
         catch( RARException ce) {
             System.err.println( "Exception: " + ce );
             ce.printStackTrace();
         }
         catch( Exception e ) {
             e.printStackTrace();
         }
         finally {
             // close the connection
             try {
                 conn.close();
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }
    }  
}
