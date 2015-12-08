package edu.uga.cs.rentaride.logic;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.session.Session;

//add needed imports as you add your methods

public interface LogicLayer
{
   /* public List<Club>         findAllClubs() throws ClubsException;
    public List<Person>       findAllPersons() throws ClubsException;
    public long               joinClub( Person person, Club club ) throws ClubsException;
    public long               joinClub( long personId, String clubName ) throws ClubsException;
    public long               createClub( String clubName, String address, long founderId ) throws ClubsException;
    public long               createPerson( String userName, String password, String email, String firstName, 
                                            String lastName, String address, String phone ) throws ClubsException;
    public List<Person>       findClubMembers( String clubName ) throws ClubsException;
    public void               logout( String ssid ) throws ClubsException;
    public String             login( Session session, String userName, String password ) throws ClubsException;
    */
	
	public List<Customer> ViewCustomerInfo(String userName) throws RARException;
	public void               logout( String ssid ) throws RARException;
	public String             login( Session session, String userName, String password ) throws RARException;
    public List<RentalLocation>  findAllRentalLocations() throws RARException;
    public long                  createRentalLocation(String name, String addr, int capacity) throws RARException;
	public long createVehicle(String registrationTag, Date lastService,
			String make, int mileage, String model, String rentalLocation_str,
			String vehicleStatus_str, String vehicleType_str, int vehicleYear,
			String vehicleCondition_str) throws RARException;
	public List<VehicleType> findAllVehicleTypes() throws RARException;
	public long createReservation(String pickupTime, int rentalDuration, String rentalLocationStr, String name, String vehicleTypeStr) 
			throws RARException;
	public long createVehicleType(String name) throws RARException;
	public List<Vehicle>         FindAllVehicles() throws RARException;
    
}
