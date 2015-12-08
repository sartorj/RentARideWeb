/**
 * 
 */
package edu.uga.cs.rentaride.logic.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import edu.uga.cs.rentaride.persistence.impl.*;

//$$$$$ ADD entity import statements when you add your usecases to this $$$$$$

/**
 * @author team 9
 *
 */
public class LogicLayerImpl 
    implements LogicLayer
{
    private ObjectLayer objectLayer = null;

    public LogicLayerImpl( Connection conn )
    {
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = new PersistenceLayerImpl( conn, objectLayer );
        objectLayer.setPersistence( persistenceLayer );
        System.out.println( "LogicLayerImpl.LogicLayerImpl(conn): initialized" );
    }
    
    public LogicLayerImpl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
        System.out.println( "LogicLayerImpl.LogicLayerImpl(objectLayer): initialized" );
    }

    public List<RentalLocation> findAllRentalLocations() 
            throws RARException
    {
        FindAllRentalLocationsCtrl ctrlFindAllRentalLocations = new FindAllRentalLocationsCtrl( objectLayer );
        return ctrlFindAllRentalLocations.findAllRentalLocations();
    }
/*
    public List<Person> findAllPersons() 
            throws ClubsException
    {
        FindAllPersonsCtrl ctrlFindAllPersons = new FindAllPersonsCtrl( objectLayer );
        return ctrlFindAllPersons.findAllPersons();
    }

    public long joinClub(Person person, Club club) throws ClubsException
    {
        return 0;
    }

    public long joinClub(long personId, String clubName) throws ClubsException
    {
        JoinClubCtrl ctrlJoinClub = new JoinClubCtrl( objectLayer );
        return ctrlJoinClub.joinClub( personId, clubName );
    }

    public long createClub(String clubName, String address, long founderId)
            throws ClubsException
    {
        CreateClubCtrl ctrlCreateClub = new CreateClubCtrl( objectLayer );
        return ctrlCreateClub.createClub( clubName, address, founderId );
    }

    public long createPerson(String userName, String password, String email,
            String firstName, String lastName, String address, String phone)
            throws ClubsException
    {
        CreatePersonCtrl ctrlCreatePerson = new CreatePersonCtrl( objectLayer );
        return ctrlCreatePerson.createPerson( userName, password, email, firstName,
                                              lastName, address, phone );
    }

    public List<Person> findClubMembers(String clubName) throws ClubsException
    {
        FindClubMembersCtrl ctrlFindClubMembers = new FindClubMembersCtrl( objectLayer );
        return ctrlFindClubMembers.findClubMembers( clubName );
    }
    */
    
    public long createRentalLocation(String name, String addr, int capacity)
            throws RARException
    {
        CreateRentalLocationCtrl ctrlCreateRentalLocation = new CreateRentalLocationCtrl (objectLayer);
        return ctrlCreateRentalLocation.createRentalLocation( name, addr, capacity );
    }
    
    public long createVehicle(String registrationTag, Date lastService, String make, int mileage, String model, String rl_str, String vehicleStatus_str, String vehicleType_str, int vehicleYear, String vehicleCondition_str ) 
    		throws RARException
    {
    	//System.out.println(registrationTag + lastService.toString() + make + mileage + model+ rl_str+vehicleStatus_str+vehicleType_str+vehicleYear+vehicleCondition_str);
    	RentalLocation modelRentalLocation = objectLayer.createRentalLocation(rl_str, null, 0);
    	modelRentalLocation.setName(rl_str);
    	
    	VehicleType modelVehicleType = objectLayer.createVehicleType();
    	modelVehicleType.setType(vehicleType_str);
    	
    	//VehicleStatus vehicleStatus = VehicleStatus.valueOf("vehicleStatus_str");
    	VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
    	VehicleType vehicleType = objectLayer.createVehicleType(vehicleType_str);
   
    	VehicleCondition vehicleCondition = VehicleCondition.valueOf(vehicleCondition_str);
    	
    	CreateVehicleCtrl ctrlCreateVehicle = new CreateVehicleCtrl(objectLayer);
    	return ctrlCreateVehicle.createVehicle(registrationTag, lastService, make, mileage, model, modelRentalLocation, VehicleStatus.INLOCATION, modelVehicleType, vehicleYear, VehicleCondition.GOOD);
    }

    public void logout( String ssid ) throws RARException
    {
        SessionManager.logout( ssid );
    }

    public String login( Session session, String userName, String password ) 
            throws RARException
    {
        LoginCtrl ctrlVerifyPerson = new LoginCtrl( objectLayer );
        return ctrlVerifyPerson.login( session, userName, password );
    }
    
    public void createCustomer(String userName, String password) {
    	
    }
    
    public List<VehicleType> findAllVehicleTypes() 
            throws RARException
    {
        FindAllVehicleTypesCtrl ctrlFindAllVehicleTypes = new FindAllVehicleTypesCtrl( objectLayer );
        return ctrlFindAllVehicleTypes.findAllVehicleTypes();
    }
    
    public long createReservation(String pickupTime, int rentalDuration, String rentalLocationStr, String name, String vehicleTypeStr)
            throws RARException
    {
        CreateReservationCtrl ctrlCreateReservation = new CreateReservationCtrl (objectLayer);
        return ctrlCreateReservation.createReservation( pickupTime, rentalDuration, rentalLocationStr, name,  vehicleTypeStr);
    }


	public List<Customer> ViewCustomerInfo(String userName) throws RARException {
		ViewCustomerInfoCtrl ctrlCustomerInfo = new ViewCustomerInfoCtrl(objectLayer);
		return ctrlCustomerInfo.ViewCustomerInfo(userName);
	}
	
	public long createVehicleType(String name)
            throws RARException
    {
        CreateVehicleTypeCtrl ctrlCreateVehicleType = new CreateVehicleTypeCtrl (objectLayer);
        return ctrlCreateVehicleType.createVehicleType( name );
    }
    
	 public List<Vehicle> FindAllVehicles() throws RARException {
		 FindAllVehiclesCtrl ctrlfindVehicles = new FindAllVehiclesCtrl(objectLayer);
		 return ctrlfindVehicles.findAllVehicless();
	 }
	
}
