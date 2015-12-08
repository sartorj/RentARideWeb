
package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;


class HourlyPriceManager
{
    private ObjectLayer objectLayer = null;
    private Connection   conn = null;
    
    public HourlyPriceManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(HourlyPrice hourlyPrice) 
            throws RARException
    {
        String               insertHPSql = "insert into HourlyPrice ( maxHours, minHours, price, hourlyPriceID ) values ( ?, ?, ?, ? )";
        String               updateHPSql = "update HourlyPrice set maxHours = ?, minHours = ?, price = ?, hourlyPriceID = ? where id = ?";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 HPId;

        /*
        if( club.getFounderId() == -1 )
            throw new ClubsException( "ClubManager.save: Attempting to save a Club without a founder" );
            */
                 
        try {

            if( !hourlyPrice.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertHPSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateHPSql );

            if( hourlyPrice.getMaxHours()!= 0 ) // name is unique unique and non null
                stmt.setInt( 1, hourlyPrice.getMaxHours() );
            else 
                throw new RARException( "HourlyPriceManager.save: can't save an HourlyPrice: max price undefined" );
            
            if( hourlyPrice.getMinHours() != 0 ) // name is unique unique and non null
                stmt.setInt( 2, hourlyPrice.getMinHours() );
            else 
                throw new RARException( "HourlyPriceManager.save: can't save an HourlyPrice: min price undefined" );
            
            if( hourlyPrice.getPrice() == 0 ){
            	stmt.setInt( 3, hourlyPrice.getPrice() );
            } else {
            	stmt.setNull(3, java.sql.Types.INTEGER);
            }
            
            /*
            if( club.getEstablishedOn() != null ) {
                java.util.Date jDate = club.getEstablishedOn();
                java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
                stmt.setDate( 3,  sDate );
            }
            else
                stmt.setNull(3, java.sql.Types.DATE);
	
            if( club.getFounder() != null && club.getFounder().isPersistent() )
                stmt.setLong( 4, club.getFounder().getId() );
            else 
                throw new ClubsException( "ClubManager.save: can't save a Club: founder is not set or not persistent" );
            */
            
            if( hourlyPrice.isPersistent() )
                stmt.setLong( 4, hourlyPrice.getId() );

            inscnt = stmt.executeUpdate();

            if( !hourlyPrice.isPersistent() ) {
                if( inscnt >= 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while( r.next() ) {

                            // retrieve the last insert auto_increment value
                            HPId = r.getLong( 1 );
                            if( HPId > 0 )
                            	hourlyPrice.setId( HPId ); // set this person's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RARException( "HourlyPriceManager.save: failed to save an HourlyPrice" );
            }
            else {
                if( inscnt < 1 )
                    throw new RARException( "HourlyPriceManager.save: failed to save an HourlyPrice" ); 
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "HourlyPriceManager.save: failed to save a HourlyPrice: " + e );
        }
    }

    public Iterator<HourlyPrice> restore(HourlyPrice hourlyPrice) 
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectHPSql = "select hp.maxHours, hp.minHours, hp.price from HourlyPrice hp where hourlyPriceID = id ";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectHPSql );
        
        if( hourlyPrice != null ) {
            if( hourlyPrice.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and hourlyPriceID = " + hourlyPrice.getId() );
            //else if( hourlyPrice.getName() != null ) // userName is unique, so it is sufficient to get a person
            //   query.append( " and name = '" + club.getName() + "'" );
            else {

                if( hourlyPrice.getMaxHours() != 0 )
                	query.append( " and maxHours = '" + hourlyPrice.getMaxHours() + "'" );   

                if( hourlyPrice.getMinHours() != 0 ) {
                    // if( condition.length() > 0 )
                    //    condition.append( " and" );
                    query.append( " and minHours = '" + hourlyPrice.getMinHours() + "'" );
                }
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
                return new HourlyPriceIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "HourlyPriceManager.restore: Could not restore persistent HourlyPrice object; Root cause: " + e );
        }

        throw new RARException( "HourlyPriceManager.restore: Could not restore persistent HourlyPrice object" );
    }
    
       VehicleType restoreVehicleTypeHourlyPrice( HourlyPrice hourlyPrice )
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectHPSql = "select vt.typeName, hp.maxHours, hp.minHours, hp.price from HourlyPrice hp, VehicleType vt where hp.hourlyPriceID = vt.vehicleTypeId ";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectHPSql );
        
        if( hourlyPrice != null ) {
            if( hourlyPrice.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and hourlyPriceID = " + hourlyPrice.getId() );
            //else if( hourlyPrice.getName() != null ) // userName is unique, so it is sufficient to get a person
            //   query.append( " and name = '" + club.getName() + "'" );
            else {

                if( hourlyPrice.getMaxHours() != 0 )
                	query.append( " and maxHours = '" + hourlyPrice.getMaxHours() + "'" );   

                if( hourlyPrice.getMinHours() != 0 ) {
                    // if( condition.length() > 0 )
                    //    condition.append( " and" );
                    query.append( " and minHours = '" + hourlyPrice.getMinHours() + "'" );
                }
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
                return new VehicleTypeIterator( r, objectLayer ).next();
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "HourlyPriceManager.restore: Could not restore persistent VehicleType object; Root cause: " + e );
        }

        throw new RARException( "HourlyPriceManager.restore: Could not restore persistent VehicleType object" );
    }


    public void delete(HourlyPrice hourlyPrice) 
            throws RARException
    {
        String               deleteHPSql = "delete from HourlyPrice where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !hourlyPrice.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteHPSql );         
            stmt.setLong( 1, hourlyPrice.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "HourlyPriceManager.delete: failed to delete a HourlyPrice" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "HourlyPriceManager.delete: failed to delete a HourlyPrice: " + e );        }
    }
}

