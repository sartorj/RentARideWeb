package edu.uga.cs.rentaride.session;


import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import org.apache.log4j.Logger;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;




/**
 * Based on the modified code from Matthew Eavenson
 * 
 * @author Matthew Eavenson
 */

/** This class provides different operations for the Sessions such as 
 *  creating new sessions, removing, login and logout.
 */
public class SessionManager
{
    /** 
     * Map for the existing sessions
     */
    private static Map<String, Session> sessions;
    
    /** 
     * Map for the currently logged-in users
     */
    private static Map<String, Session> loggedIn;
    
    //private static Logger log = Logger.getLogger( SessionManager.class );
    
    static{
        sessions = new HashMap<String, Session>();
        loggedIn = new HashMap<String, Session>();
    } 
    
    public static Session createSession() 
            throws RARException 
    {
        Connection conn = null;
        Session s = null;
        
        // open a connection to the database
        try {
            conn = DbUtils.connect();
        } catch (Exception seq) {
            throw new RARException( "SessionManager.login: Unable to get a database connection" );
        }
        
        // initialize a new Session object
        // this creates PersistenceLayer, ObjectLayer, and LogicLayer instances
        // The LogicLayer reference is stored with the Session for use in other use cases later.
        s = new Session( conn );
        
        return s; 
    }
    
  
    /****************************************************
     * Logs the user into the system, create a database connection, and create session.
     * @param username the user name for the user.
     * @param password the password for the user.
     * @return         the new session ID created
     * @throws GVException
     */
    /* 
    public static String login( String username, String password ) 
            throws ClubsException 
    {
        Connection conn = null;
        ObjectLayer objectLayer = null;
        LogicLayer logicLayer = null;
        PersistenceLayer persistence = null;
        Person loginPerson = null;
        Person knownPerson = null;
        Session s = null;
        
        try {
            conn = DbUtils.connect();
        } catch (Exception seq) {
            throw new ClubsException( "SessionManager.login: Unable to get a database connection" );
        }
        
        // initialize a new Session object
        s = new Session( conn );
        
        /////////////////////////////////////////////////////////////////////////////////////
        // initialization of the subsystems
        objectLayer = new ObjectLayerImpl();
        
        // the persistence layer must have access to the object layer to create proxy objects
        // when bringing them back from the persistent data store
        persistence = new PersistenceLayerImpl( conn, objectLayer );
        // the object layer needs persistence for obvious reasons
        objectLayer.setPersistence( persistence );
        // the logic layer only needs the object layer access
        // the logic layer reference is stored within the Session object
        // the logic layer will be accessed in boundary classes, which will get
        // the reference to it from the Session object
        logicLayer = new LogicLayerImpl( objectLayer );
        
        // authenticate the user who is attempting to login
        loginPerson = objectLayer.createPerson();
        loginPerson.setUserName( username );
        loginPerson.setPassword( password );
        
        Iterator<Person> persons = persistence.restorePerson( loginPerson );
        if( persons.hasNext() ) {
             knownPerson = persons.next();
             loginPerson = null;
             return establishSession( s, knownPerson );
        } 
        else {
            log.error( "SessionManager.login: Invalid UserName or Password for: " + username );
            throw new ClubsException( "SessionManager.login: Invalid User Name or Password" );
        }
        
    }
    */
    public static String storeSession( Session session ) 
            throws RARException
    {
        User user = session.getUser();
        
        if( loggedIn.containsKey(user.getUserName()) ) {
            Session qs = loggedIn.get(user.getUserName());
            qs.setUser(user);
            return qs.getSessionId();
        }
                
        String ssid = secureHash( "RENTARIDE" + System.nanoTime() );
        session.setSessionId( ssid );
        
        sessions.put( ssid, session );
        loggedIn.put( user.getUserName(), session );
        session.start();
        return ssid;
    }
    
    /****************************************************
     * Establish a new session for the current user
     * @param session Session to be created
     * @param person Current User logged In
     * @return  the new Session Id
     * @throws GVException
     */
    /*
    private static String establishSession( Session session, Person person ) 
            throws ClubsException
    {
        if( person == null ) {
            if( session.getConnection() != null ) {
                try { 
                    session.getConnection().close();
                } 
                catch( SQLException sqlEx ) {
                    log.error( "SessionManager.establishSession: No user found" + sqlEx );
                }
            }
            log.error( "SessionManager.establishSession: Bad username or password" );
            throw new ClubsException( "SessionManager.establishSession: Bad username or password" );
        }
        
        if( loggedIn.containsKey(person.getUserName()) ) {
            Session qs = loggedIn.get(person.getUserName());
            qs.setUser(person);
            return qs.getSessionId();
        }
        
        session.setUser(person);
        
        String ssid = secureHash( "CLUBS" + System.nanoTime() );
        session.setSessionId( ssid );
        
        sessions.put( ssid, session );
        loggedIn.put( person.getUserName(), session );
        session.start();
        return ssid;
    }
    */
    /****************************************************
     * Logout of the current session (based on session)
     * @param  the session being used
     * @throws GVException
     */
    public static void logout(Session s) 
            throws RARException
    {
        s.setExpiration(new Date());
        s.interrupt();
        removeSession(s);
    }
    
    /****************************************************
     * Logout of the current session (based on session)
     * @param  ssid the session being used
     * @throws GVException
     */
    public static void logout(String ssid) 
            throws RARException
    {
        Session s = getSessionById(ssid);
        s.setExpiration(new Date());
        s.interrupt();
        removeSession(s);
    }
    
    /****************************************************
     * Remove the session
     * @param s the current session
     * @throws GVException
     */
    protected static void removeSession( Session s ) 
            throws RARException
    {
        try { 
            s.getConnection().close();
        } 
        catch( SQLException sqe ) { 
            //log.error( "SessionManager.removeSession: cannot close connection", sqe );
            throw new RARException( "SessionManager.removeSession: Cannot close connection" );
        } // try
        sessions.remove( s.getSessionId() );
        loggedIn.remove( s.getUser().getUserName() );
    }
    
    /****************************************************
     * Get the session by session id
     * @param ssid the current session id
     */
    public static Session getSessionById( String ssid ){
        return sessions.get( ssid );
    }
    
    /*********************************************************************
     * Hashes the string input using the SHA1 algorithm.
     * @param   input   string to hash.
     * @return  SHA hash of the string.
     * @throws ClubsException 
     */
    public static String secureHash( String input ) 
            throws RARException
    {
        StringBuilder output = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance( "SHA" );
            md.update( input.getBytes() );
            byte[] digest = md.digest();
            for( int i = 0; i < digest.length; i++ ) {
                String hex = Integer.toHexString( digest[i] );
                if( hex.length() == 1 )
                    hex = "0" + hex;
                hex = hex.substring( hex.length() - 2 );
                output.append( hex );
            }
        }
        catch( Exception e ) {
            //log.error("SessionManager.secureHash: Invalid Encryption Algorithm", e );
            throw new RARException(
                    "SessionManager.secureHash: Invalid Encryption Algorithm" );
        }
        return output.toString();
    }
    
    /**********************************************************************
     * Return the logger object. 
     * @return Logger object. 
     * @author Arsham Mesbah
     */
    
    /*
    public static Logger getLog()
    {
        return log; 
    }
*/
}
