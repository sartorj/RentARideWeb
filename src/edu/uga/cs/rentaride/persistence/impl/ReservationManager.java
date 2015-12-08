package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;


import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.object.ObjectLayer;


class ReservationManager
{
    private ObjectLayer objectLayer = null;
    private Connection   conn = null;
    
    public ReservationManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(Reservation reservation) 
            throws RARException
    {
    
        String               insertRSql = "insert into Reservations ( customer, pickupTime, rental, rentalDuration, rentalLocation, vehicleType, reservationID ) values ( ?, ?, ?, ?, ?, ?, ? )";
        String               updateRSql = "update Reservations set customer = ?, pickupTime = ?, rental = ?, rentalDuration = ?, vehicleType = ?, reservationID = ? where id = ?";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 RId;

        /*
        if( club.getFounderId() == -1 )
            throw new ClubsException( "ClubManager.save: Attempting to save a Club without a founder" );
            */
                 
        try {

            if( !reservation.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertRSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateRSql );

            if( reservation.getCustomer() != null ) // name is unique unique and non null
                stmt.setString( 1, reservation.getCustomer().getUserName() );  
            else 
                throw new RARException( "ReservationManager.save: can't save a Reservation: Customer undefined" );

            if( reservation.getPickupTime() != null ){
                //stmt.setDate( 2, (Date) reservation.getPickupTime() );   // I thought this was already a Date?
            	stmt.setDate( 2, new Date(reservation.getPickupTime().getTime()) );
            	System.out.println("hello");
            }else
                stmt.setNull( 2, java.sql.Types.DATE );
            
            if( reservation.getRental() != null )
                stmt.setLong( 3, reservation.getRental().getId() );   
            else
                //stmt.setNull( 3, java.sql.Types.INTEGER );
            	stmt.setLong( 3, 1 );
            
            if( reservation.getRentalDuration() != 0 )
                stmt.setInt( 4, reservation.getRentalDuration() );   
            else
                stmt.setNull( 4, java.sql.Types.INTEGER );
            
            if( reservation.getRentalLocation() != null )
                stmt.setString( 5, reservation.getRentalLocation().getAddress() );  
            else
                stmt.setNull( 5, java.sql.Types.VARCHAR );
            
            if( reservation.getVehicleType() != null )
                stmt.setString( 6, reservation.getVehicleType().getType() );  
            else
                stmt.setNull( 6, java.sql.Types.VARCHAR );

            /*
            if( reservation.getEstablishedOn() != null ) {
                java.util.Date jDate = reservation.getEstablishedOn();
                java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
                stmt.setDate( 3,  sDate );
            }
            else
                stmt.setNull(3, java.sql.Types.DATE);
            */
            /*
            if( reservation.getFounder() != null && reservation.getFounder().isPersistent() )
                stmt.setLong( 4, club.getFounder().getId() );
            else 
                throw new ClubsException( "ClubManager.save: can't save a Club: founder is not set or not persistent" );
            */
            if( reservation.isPersistent() )
                stmt.setLong( 7, reservation.getId() );
             else 
            	stmt.setLong(7, 0);
            
            
            inscnt = stmt.executeUpdate();

            if( !reservation.isPersistent() ) {
                if( inscnt >= 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while( r.next() ) {

                            // retrieve the last insert auto_increment value
                            RId = r.getLong( 1 );
                            if( RId > 0 )
                            	reservation.setId( RId ); // set this person's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RARException( "ReservationManager.save: failed to save a Reservation" );
            }
            else {
                if( inscnt < 1 )
                    throw new RARException( "ReservationManager.save: failed to save a Reservation" ); 
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
            System.out.println(insertRSql);
            throw new RARException( "ReservationManager.save: failed to save a Reservation: " + e );
        }
    }

    public Iterator<Reservation> restore(Reservation reservation) 
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectRSql = " select r.customer, r.pickupTime, r.rental, r.rentalDuration, r.rentalLocation, " + 
        							 " r.vehicleType, r.reservationID,   from Reservations r ";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given  object instance
        query.append( selectRSql );
        
        if( reservation != null ) {
            if( reservation.getId() >= 0 ) // id is unique, so it is sufficient to get a 
                query.append( " and id = " + reservation.getId() );
            else if( reservation.getCustomer() != null ) // userName is unique, so it is sufficient to get a 
                query.append( " and customer = '" + reservation.getCustomer() + "'" );
            else {

                if( reservation.getPickupTime() != null )
                    condition.append( " and pickupTime = '" + reservation.getPickupTime() + "'" ); 
                
                if( reservation.getRental() != null )
                    condition.append( " and rental = '" + reservation.getRental() + "'" ); 
                
                if( reservation.getRentalDuration() != 0 )
                    condition.append( " and rentalDuration = '" + reservation.getRentalDuration() + "'" ); 
                
                if( reservation.getRentalLocation() != null )
                    condition.append( " and rentalLocation = '" + reservation.getRentalLocation() + "'" ); 
                
                if( reservation.getVehicleType() != null )
                    condition.append( " and vehicleType = '" + reservation.getVehicleType() + "'" ); 
                /*
                if( club.getEstablishedOn() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " established = '" + club.getEstablishedOn() + "'" );
                }
                */
                /*
                if( condition.length() > 0 ) {
                    query.append(  " where " );
                    query.append( condition );
                }
                */
            }
        }
        
        try {

            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                return new ReservationIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "ReservationManager.restore: Could not restore persistent Reservation object; Root cause: " + e );
        }

        throw new RARException( "ReservationManager.restore: Could not restore persistent Reservation object" );
    }
    
    
    
    
    
    
    public Customer restoreCustomerReservation( Reservation reservation ) 
            throws RARException
    {
        String       selectPersonSql = "select c.firstName, c.lastName, c.userName, c.emailAddress, c.password, c.createdDate, c.userStatus, c.userType from customer c, reservation r where c.id = r.reservationID";              
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Person object instance
        query.append( selectPersonSql );
        
        if( reservation != null ) {
            if( reservation.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and r.reservationID = " + reservation.getId() );
            else if( reservation.getRental() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " and r.rental = '" + reservation.getRental() + "'" );
            else {

                if( reservation.getCustomer() != null )
                    condition.append( " and r.customer = '" + reservation.getCustomer() + "'" );   

                if( reservation.getPickupTime() != null )
                    condition.append( " and r.pickupTime = '" + reservation.getPickupTime() + "'" );   

                if( reservation.getRentalDuration() != 0 )
                    condition.append( " and r.rentalDuration = '" + reservation.getRentalDuration() + "'" );   

                if( reservation.getRentalLocation() != null )
                    condition.append( " and r.rentalLocation = '" + reservation.getRentalLocation() + "'" );   

                if( reservation.getVehicleType() != null )
                    condition.append( " and r.vehicleType = '" + reservation.getVehicleType() + "'" );   

                if( condition.length() > 0 ) {
                    query.append( condition );
                }
            }
        }
                
        try {

            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                Iterator<Customer> custIter = new CustomerIterator( r, objectLayer );
                if( custIter != null && custIter.hasNext() ) {
                    return custIter.next();
                }
                else
                    return null;
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "ReservationManager.restoreCustomerReservation: Could not restore persistent Customer object; Root cause: " + e );
        }

        // if we reach this point, it's an error
        throw new RARException( "ReservationManager.restoreCustomerReservation: Could not restore persistent Customer object" );
    }
    
    
    
    
    
    
    public RentalLocation restoreReservationRentalLocation( Reservation reservation ) 
            throws RARException
    {
        String       selectPersonSql = "select rl.address, rl.capacity, rl.locationName, from rentalLocation rl, reservation r where rl.rentalID = r.reservationID";              
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Person object instance
        query.append( selectPersonSql );
        
        if( reservation != null ) {
            if( reservation.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and r.reservationID = " + reservation.getId() );
            else if( reservation.getRental() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " and r.rental = '" + reservation.getRental() + "'" );
            else {

                if( reservation.getCustomer() != null )
                    condition.append( " and r.customer = '" + reservation.getCustomer() + "'" );   

                if( reservation.getPickupTime() != null )
                    condition.append( " and r.pickupTime = '" + reservation.getPickupTime() + "'" );   

                if( reservation.getRentalDuration() != 0 )
                    condition.append( " and r.rentalDuration = '" + reservation.getRentalDuration() + "'" );   

                if( reservation.getRentalLocation() != null )
                    condition.append( " and r.rentalLocation = '" + reservation.getRentalLocation() + "'" );   

                if( reservation.getVehicleType() != null )
                    condition.append( " and r.vehicleType = '" + reservation.getVehicleType() + "'" );   

                if( condition.length() > 0 ) {
                    query.append( condition );
                }
            }
        }
                
        try {

            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                Iterator<RentalLocation> rentIter = new RentalLocationIterator( r, objectLayer );
                if( rentIter != null && rentIter.hasNext() ) {
                    return rentIter.next();
                }
                else
                    return null;
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "ResercationManager.restoreReservationRentalLocation: Could not restore persistent Rental object; Root cause: " + e );
        }

        // if we reach this point, it's an error
        throw new RARException( "ReservationManager.restoreReservationRentalLocation: Could not restore persistent Rental object" );
    }
    
    
    
    public VehicleType restoreReservationVehicleType( Reservation reservation ) 
            throws RARException
    {
        String       selectPersonSql = "select v.typename from vehivleType v, reservation r where v.vehicleTypeId = r.reservationID";              
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Person object instance
        query.append( selectPersonSql );
        
        if( reservation != null ) {
            if( reservation.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and r.reservationID = " + reservation.getId() );
            else if( reservation.getRental() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " and r.rental = '" + reservation.getRental() + "'" );
            else {

                if( reservation.getCustomer() != null )
                    condition.append( " and r.customer = '" + reservation.getCustomer() + "'" );   

                if( reservation.getPickupTime() != null )
                    condition.append( " and r.pickupTime = '" + reservation.getPickupTime() + "'" );   

                if( reservation.getRentalDuration() != 0 )
                    condition.append( " and r.rentalDuration = '" + reservation.getRentalDuration() + "'" );   

                if( reservation.getRentalLocation() != null )
                    condition.append( " and r.rentalLocation = '" + reservation.getRentalLocation() + "'" );   

                if( reservation.getVehicleType() != null )
                    condition.append( " and r.vehicleType = '" + reservation.getVehicleType() + "'" );   

                if( condition.length() > 0 ) {
                    query.append( condition );
                }
            }
        }
                
        try {

            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                Iterator<VehicleType> vTIter = new VehicleTypeIterator( r, objectLayer );
                if( vTIter != null && vTIter.hasNext() ) {
                    return vTIter.next();
                }
                else
                    return null;
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "ReservationManager.restoreReservationVehicleType: Could not restore persistent VehicleType object; Root cause: " + e );
        }

        // if we reach this point, it's an error
        throw new RARException( "ReservationManager.restoreReservationVehicleType: Could not restore persistent VehicleType object" );
    }
    
    
    public void delete(Reservation reservation) 
            throws RARException
    {
        String               deleteRSql = "delete from reservations where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !reservation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteRSql );         
            stmt.setLong( 1, reservation.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "ReservationManager.delete: failed to delete a Reservation" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "ReservationManager.delete: failed to delete a Reservation: " + e );        }
    }
}
