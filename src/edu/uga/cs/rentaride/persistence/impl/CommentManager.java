
package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.impl.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;


class CommentManager
{
    private ObjectLayer objectLayer = null;
    private Connection   conn = null;
    
    public CommentManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(CommentImpl comm) 
            throws RARException
    {
        String               insertCommentSql = "insert into Comments ( commentDate,rental,comment ) values ( ?, ?, ?)";
        String               updateCommentSql = "update Comments set commentDate = ?, rental = ?, comment = ?, comemntID = ? where commentID = ?";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 commentID;

        /*
        if( club.getFounderId() == -1 )
            throw new ClubsException( "ClubManager.save: Attempting to save a Club without a founder" );
            */
                 
        try {

            if( !comm.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertCommentSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateCommentSql );

            if( comm.getDate() != null ) // name is unique unique and non null
                stmt.setString( 1, club.getName() );
            else 
                throw new RARException( "CommentManager.save: can't save a Comment: date undefined" );

            if( comm.getRental() != null )
                stmt.setString( 2, comm.getRental() );
            else
                stmt.setNull( 2, java.sql.Types.VARCHAR );

            if( comm.getComment() != null ) {
                java.util.Date jDate = comm.getDate();
                java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
                stmt.setDate( 3,  sDate );
            }
            else
                stmt.setNull(3, java.sql.Types.DATE);

            if( comm.isPersistent() )
                stmt.setLong( 5, comm.getId() );

            inscnt = stmt.executeUpdate();

            if( !comm.isPersistent() ) {
                if( inscnt >= 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while( r.next() ) {

                            // retrieve the last insert auto_increment value
                            commentID = r.getLong( 1 );
                            if( commentID > 0 )
                                comm.setId( commentID ); // set this person's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RARException( "CommentManager.save: failed to save a Comment" );
            }
            else {
                if( inscnt < 1 )
                    throw new RARException( "CommentManager.save: failed to save a Comment" ); 
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "CommentManager.save: failed to save a Comment: " + e );
        }
    }

       Customer restoreCustomerComment( Comment comment )
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectCSql = "select c.commentID, c.commentDate, c.rental, c.comment " +
                                      " from Comments c, Customers cu where 1 = 1 ";
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectCSql );
        
        if( comment != null ) {
            if( comment.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and c.commentID = " + comment.getId() );
            else if( comment.getRental() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " and c.rental = '" + comment.getRental() + "'" );
            else {

                if( comment.getDate() != null )
                    condition.append( " and c.commentDate = '" + comment.getDate() + "'" );   
                if( comment.getComment() != null )
                    condition.append( " and c.comment = '" + comment.getComment() + "'" );   
       
                /*
                if( club.getEstablishedOn() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " established = '" + club.getEstablishedOn() + "'" );
                }
                */
                
                if( condition.length() > 0 ) {
                    //query.append(  " where " );
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
                return new CommentIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "CommentManager.restore: Could not restore persistent Comment object; Root cause: " + e );
        }

        throw new RARException( "CommentManager.restore: Could not restore persistent Comment object" );
    }

    
    Rental restoreRentalComment( Comment comment ) 
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectCSql = "select r.customer, r.pickupTime, r.returnTime, c.commentID, c.commentDate, c.rental, c.comment " +
                                      " from Comments c, Rentals r where r.rentalNo = c.commentID ";
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectCSql );
        
        if( comment != null ) {
            if( comment.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and c.commentID = " + comment.getId() );
            else if( comment.getRental() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " and c.rental = '" + comment.getRental() + "'" );
            else {

                if( comment.getDate() != null )
                    condition.append( " and c.commentDate = '" + comment.getDate() + "'" );   
                if( comment.getComment() != null )
                    condition.append( " and c.comment = '" + comment.getComment() + "'" );   
       
                /*
                if( club.getEstablishedOn() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " established = '" + club.getEstablishedOn() + "'" );
                }
                */
                
                if( condition.length() > 0 ) {
                    //query.append(  " where " );
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
                return new RentalIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "CommentManager.restore: Could not restore persistent Rental object; Root cause: " + e );
        }

        throw new RARException( "CommentManager.restore: Could not restore persistent Rental object" );
    }

        public Iterator<CommentImpl> restore(CommentImpl comment) 
            throws RARException
    {
        //String       selectClubSql = "select id, name, address, established, founderid from club";
        String       selectCSql = "select c.commentID, c.commentDate, c.rental, c.comment " +
                                      " from Comments c where 1 = 1 ";
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectCSql );
        
        if( comment != null ) {
            if( comment.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and c.commentID = " + comment.getId() );
            else if( comment.getRental() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " and c.rental = '" + comment.getRental() + "'" );
            else {

                if( comment.getDate() != null )
                    condition.append( " and c.commentDate = '" + comment.getDate() + "'" );   
                if( comment.getComment() != null )
                    condition.append( " and c.comment = '" + comment.getComment() + "'" );   
       
                /*
                if( club.getEstablishedOn() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " established = '" + club.getEstablishedOn() + "'" );
                }
                */
                
                if( condition.length() > 0 ) {
                    //query.append(  " where " );
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
                return new CommentIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "CommentManager.restore: Could not restore persistent Comment object; Root cause: " + e );
        }

        throw new RARException( "CommentManager.restore: Could not restore persistent Comment object" );
    }
    
    
    public void delete(CommentImpl comment) 
            throws RARException
    {
        String               deleteCommentSql = "delete from Comments where commentID = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !comment.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSql );         
            stmt.setLong( 1, comment.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "CommentManager.delete: failed to delete a Comment" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "CommentManager.delete: failed to delete a Comment: " + e );        }
    }
}
