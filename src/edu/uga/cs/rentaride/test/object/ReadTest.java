package edu.uga.cs.rentaride.test.object;

import java.sql.Connection;
import java.util.Iterator;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class ReadTest
{
    public static void main(String[] args)
    {
         Connection  conn = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;

         // get a database connection
         try {
             conn = DbUtils.connect();
         } 
         catch (Exception seq) {
             System.err.println( "ReadTest: Unable to obtain a database connection" );
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( conn, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
       
         objectLayer.setPersistence(persistence);
           
        try {
             
             System.out.println( "RentalLocation objects:" );             
             Iterator<RentalLocation> rlIter = objectLayer.findRentalLocation( null );
             while( rlIter.hasNext() ) {
                 RentalLocation c = rlIter.next();
                 System.out.println("RL Addr: " + c.getAddress() +"RL Name: " + c.getName() + "\tRL Cap: " + c.getCapacity());
             }
             
             System.out.println("VehicleTypes:");
             
             Iterator<VehicleType> vtIter = objectLayer.findVehicleType(null);
             
             while (vtIter.hasNext()) {
            	 VehicleType vt = vtIter.next();
            	 System.out.println(vt.getType());
             }
             
             Vehicle modelVehicle = objectLayer.createVehicle(null, "nissan", null, 0, null, 0, null, null, null, null);
             
             Iterator<Vehicle> vIter = objectLayer.findVehicle(modelVehicle);
             System.out.println("Vehicles with make nissan: ");
             
             while (vIter.hasNext()) {
            	 Vehicle v = vIter.next();
            	 System.out.println(v.getRegistrationTag());
      
             }
             
             
         }
         catch( RARException ce)
         {
             System.err.println( "RARException: " + ce );
         }
         catch( Exception e)
         {
             System.out.flush();
             System.err.println( "Exception: " + e );
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
