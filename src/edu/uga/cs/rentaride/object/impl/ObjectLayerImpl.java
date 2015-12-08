 package edu.uga.cs.rentaride.object.impl;

import java.util.Date;
import java.util.Iterator;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.RentARideConfig;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.impl.AdministratorImpl;
import edu.uga.cs.rentaride.entity.impl.CommentImpl;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.HourlyPriceImpl;
import edu.uga.cs.rentaride.entity.impl.RentARideConfigImpl;
import edu.uga.cs.rentaride.entity.impl.RentalImpl;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.entity.impl.ReservationImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleTypeImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

/**
 * This is the Object Layer subsystem of the Rent-A-Ride system.
 * 
 * For each class, there are four basic operations: create, find, store, and delete.
 * 
 * Each of the find operations, the model argument represents an object with none,
 * all, or just some of the attributes defined.  The find operations will return only the
 * matching objects, i.e., the objects with the exact same values for all of the provided
 * attribute values in the model object: 
 * 
 *     -- if the model argument is null, all objects are returned;
 *     -- if the persistent identifier attribute is provided, only one object is returned;
 *     -- if some of the attributes are provided, some of the matching objects are returned.
 */
public class ObjectLayerImpl
    implements ObjectLayer
{
	PersistenceLayer persistence = null;
    
    public ObjectLayerImpl()
    {
        this.persistence = null;
        System.out.println("ObjectLayerImpl.ObjectLayerImpl(): initialized");
    }
    
    public ObjectLayerImpl( PersistenceLayerImpl persistence)
    {
        this.persistence = persistence;
        System.out.println("ObjectLayerImpl.ObjectLayerImpl(persistence): initialized");
    }
    
    public void setPersistence(PersistenceLayer persistence) {
    	this.persistence = persistence;
    	System.out.println("ObjectLayerImpl.setPersistence(persistence): initialized");
    
    }
    
    /**
     * Create a new Administrator object, given the set of initial attribute values.
     * The UserStatus of the new Administrator object is UserStatus.ACTIVE.
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user name (login name)
     * @param emailAddress the email address
     * @param password the password
     * @param createDate the creation date
     * @return the new Administrator object instance with the given attribute values, UserStatus is UserStatus.ACTIVE
     */
    public Administrator createAdministrator( String firstName, String lastName, String userName, 
                                              String emailAddress, String password, Date createDate )
    {
        return new AdministratorImpl(firstName, lastName, userName, emailAddress, password, createDate, UserStatus.ACTIVE, "admin");
    }

    /**
     * Create a new Administrator object with undefined attribute values.
     * The UserStatus of the new Administrator object is UserStatus.ACTIVE.
     * @return the new Administrator object instance
     */
    public Administrator createAdministrator()
    {
        return new AdministratorImpl(null, null, null, null, null, null, UserStatus.ACTIVE,"admin"); //change the enum instead of using userstatus entity
    }
    
    /**
     * Return an iterator of Administrator objects satisfying the search criteria given in the modelAdministrator object.
     * @param modelAdministrator a model Administrator object specifying the search criteria
     * @return an Iterator of the located Administrator objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Administrator> findAdministrator( Administrator modelAdministrator ) throws RARException
    {
        return persistence.restoreAdministrator( modelAdministrator );
    }
    
    /**
     * Store a given Administrator object in persistent data store.
     * @param administrator the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeAdministrator( Administrator administrator ) throws RARException
    {
        persistence.storeAdministrator( administrator );
    }
    
    /**
     * Delete this Administrator object.
     * @param administrator the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteAdministrator( Administrator administrator ) throws RARException
    {
        persistence.deleteAdministrator( administrator );
    }
    
    /**
     * Create a new Customer object, given the set of initial attribute values.
     * The UserStatus of the new Customer object is UserStatus.ACTIVE.
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user (login) name
     * @param emailAddress the exmail address
     * @param password the password
     * @param createDate the date when the customer has been created
     * @param membershipExpiration the date when the membership expires
     * @param licenseState the issuing state of the driver's license
     * @param licenseNumber the license number
     * @param residenceAddress the residence address
     * @param cardNumber the credit card number
     * @param cardExpiration the expiration date of the credit card
     * @return the new Customer object instance with the given attribute values, UserStatus is UserStatus.ACTIVE
     */
    public Customer createCustomer( String firstName, String lastName, String userName, String emailAddress, 
            String password, Date createDate, Date membershipExpiration, String licenseState, 
            String licenseNumber, String residenceAddress, String cardNumber, Date cardExpiration )
    {
        return new CustomerImpl(membershipExpiration, licenseState, licenseNumber,residenceAddress, cardNumber, 
                        cardExpiration, firstName, lastName, userName, emailAddress, password, createDate, 
                        UserStatus.ACTIVE); //userstatus enum
    }

    /**
     * Create a new Customer object with undefined attribute values.
     * The UserStatus of the new Administrator object is UserStatus.ACTIVE.
     * @return the new Customer object instance
     */
    public Customer createCustomer()
    {
        return new CustomerImpl(null, null, null,null, null, 
                        null, null, null, null, null, null, null, 
                        UserStatus.ACTIVE); //userstatus enum
    }
    
    /**
     * Return an iterator of Customer objects satisfying the search criteria given in the modelCustomer object.
     * @param modelCustomer a model Customer object specifying the search criteria
     * @return an Iterator of the located Customer objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Customer> findCustomer( Customer modelCustomer ) throws RARException 
    {
        return persistence.restoreCustomer( modelCustomer );
    }
    
    /**
     * Store a given Customer object in persistent data store.
     * @param customer the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeCustomer( Customer customer ) throws RARException
    {
        persistence.storeCustomer( customer );
    }
    

    /**
     * Create a new RentalLocation object, given the set of initial attribute values.
     * @param name the name of the location
     * @param address the address of the location
     * @param capacity the capacity of the location
     * @return the new RentalLocation object instance with the given attribute values
     */
    public RentalLocation createRentalLocation( String name, String address, int capacity )
    {
        return new RentalLocationImpl(name, address, capacity);
    }

    /**
     * Create a new RentalLocation object with undefined attribute values.
     * @return the new RentalLocation object instance
     */
    public RentalLocation createRentalLocation()
    {
        return new RentalLocationImpl(null, null, 0); //no constructor taking 3 nulls - use empty strings and 0 for cap maybe?
    }

    /**
     * Return an iterator of RentalLocation objects satisfying the search criteria given in the modelRentalLocation object.
     * @param modelRentalLocation a model RentalLocation object specifying the search criteria
     * @return an Iterator of the located RentalLocation objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<RentalLocation> findRentalLocation( RentalLocation modelRentalLocation ) throws RARException
    {
        return persistence.restoreRentalLocation( modelRentalLocation );
    }
    
    /**
     * Store a given RentalLocation object in persistent data store.
     * @param rentalLocation the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeRentalLocation( RentalLocation rentalLocation ) throws RARException
    {
        persistence.storeRentalLocation( rentalLocation );
    }
    
    /**
     * Delete this RentalLocation object.
     * @param rentalLocation the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteRentalLocation( RentalLocation rentalLocation ) throws RARException
    {
        persistence.deleteRentalLocation( rentalLocation );
    }

    /**
     * Create a new Reservation object, given the set of initial attribute values.
     * @param vehicleType the type of the vehicle reserved
     * @param rentalLocation the location for this reservation
     * @param customer the customer making the reservation
     * @param pickupTime the requested pickup time
     * @param rentalDuration the duration of the requested rental (in hours)
     * @return the new Reservation object instance with the given attribute values
     * @throws RARException in case either the vehicleType, rentalLocation, or customer is null
     */
    public Reservation createReservation( VehicleType vehicleType, RentalLocation rentalLocation, Customer customer,
            Date pickupTime, int rentalDuration ) throws RARException
    {
        return new ReservationImpl( pickupTime, rentalDuration, customer, vehicleType, rentalLocation );
    }

    /**
     * Create a new Reservation object with undefined attribute values.
     * @return the new Reservation object instance
     */
    public Reservation createReservation() 
    {
        return new ReservationImpl( null, 0, null, null, null); 
    }

    /**
     * Return an iterator of Reservation objects satisfying the search criteria given in the modelReservation object.
     * @param modelReservation a model Reservation object specifying the search criteria
     * @return an Iterator of the located Reservation objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Reservation> findReservation( Reservation modelReservation ) throws RARException
    {
        return persistence.restoreReservation ( modelReservation );
    }
    
    /**
     * Store a given Reservation object in persistent data store.
     * @param reservation the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeReservation( Reservation reservation ) throws RARException
    {
        persistence.storeReservation( reservation );
    }
    
    /**
     * Delete this Reservation object.
     * @param reservation the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteReservation( Reservation reservation ) throws RARException
    {
        persistence.deleteReservation( reservation );
    }

    /**
     * Create a new Rental object, given the set of initial attribute values.
     * @param reservation the reservation for this rental
     * @param customer the customer picking up the vehicle
     * @param vehicle the vehicle being rented
     * @param pickupTime the pickup time of the vehicle
     * @return the new Reservation object instance with the given attribute values
     * @throws RARException in case either the reservation, vehicle, or customer is null
     */
    public Rental createRental( Reservation reservation, Customer customer, Vehicle vehicle, Date pickupTime ) throws RARException
    {
        return new RentalImpl( pickupTime, null, reservation, vehicle, customer, 0);
    }

    /**
     * Create a new Rental object with undefined attribute values.
     * @return the new Rental object instance
     */
    public Rental createRental()
    {
        return new RentalImpl( null, null, null, null, null, 0);
    }

    /**
     * Return an iterator of Rental objects satisfying the search criteria given in the modelRental object.
     * @param modelRental a model Rental object specifying the search criteria
     * @return an Iterator of the located Rental objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Rental> findRental( Rental modelRental ) throws RARException
    {
        return persistence.restoreRental( modelRental );
    }
    
    /**
     * Store a given Rental object in persistent data store.
     * @param rental the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeRental( Rental rental ) throws RARException 
    {
        persistence.storeRental(rental);
    }
    
    /**
     * Delete this Rental object.
     * @param rental the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteRental( Rental rental ) throws RARException 
    {
        persistence.deleteRental( rental );
    }
    
    /**
     * Create a new VehicleType object, given the set of initial attribute value.
     * @param type the description of the vehicle type, e.g. "Sedan", "Pickup", etc.
     * @return the new VehicleType object instance with the given attribute value
     */
    public VehicleType createVehicleType( String type ) 
    {
        return new VehicleTypeImpl( type );
    }

    /**
     * Create a new VehicleType object with undefined attribute values.
     * @return the new VehicleType object instance
     */
    public VehicleType createVehicleType()
    {
        return new VehicleTypeImpl( null );
    }

    /**
     * Return an iterator of VehicleType objects satisfying the search criteria given in the modelVehicleType object.
     * @param modelVehicleType a model VehicleType object specifying the search criteria
     * @return an Iterator of the located VehicleType objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<VehicleType> findVehicleType( VehicleType modelVehicleType ) throws RARException
    {
        return persistence.restoreVehicleType( modelVehicleType );
    }
    
    /**
     * Store a given VehicleType object in persistent data store.
     * @param vehicleType the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeVehicleType( VehicleType vehicleType ) throws RARException
    {
        persistence.storeVehicleType( vehicleType );
    }
    
    /**
     * Delete this VehicleType object.
     * @param vehicleType the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteVehicleType( VehicleType vehicleType ) throws RARException
    {
        persistence.deleteVehicleType( vehicleType );
    }

    /**
     * Create a new Vehicle object, given the set of initial attribute value.
     * @param vehicleType the type of the created vehicle (cannot be null)
     * @param make the make of the vehicle
     * @param model the model
     * @param year the year of the vehicle
     * @param registrationTag the registration tag
     * @param mileage the current mileage of the vehicle
     * @param lastServiced the date when the vehicle was last serviced
     * @param rentalLocation the rental location of this vehicle (cannot be null)
     * @param vehicleCondition the condition of this vehicle 
     * @param vehicleStatus the status of this vehicle 
     * @return the new Vehicle object instance with the given attribute values
     * @throws RARException in case either the vehicleType and/or the rentalLocation is null
     */
    public Vehicle createVehicle( VehicleType vehicleType, String make, String model,
            int year, String registrationTag, int mileage, Date lastServiced, 
            RentalLocation rentalLocation, VehicleCondition vehicleCondition, VehicleStatus vehicleStatus ) throws RARException
    {
        return new VehicleImpl( vehicleType, make, model, year, registrationTag, mileage, lastServiced, 
                                 rentalLocation, vehicleCondition, vehicleStatus );
    }

    /**
     * Create a new Vehicle object with undefined attribute values.
     * @return the new Vehicle object instance
     */
    public Vehicle createVehicle() 
    {
        return new VehicleImpl( null, null, null, 0 ,null ,0 , null , null ,null , null);
        		
        	
    }

    /**
     * Return an iterator of Vehicle objects satisfying the search criteria given in the modelVehicle object.
     * @param modelVehicle a model Vehicle object specifying the search criteria
     * @return an Iterator of the located Vehicle objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Vehicle> findVehicle( Vehicle modelVehicle ) throws RARException
    {
        return persistence.restoreVehicle( modelVehicle );
    }
    
    /**
     * Store a given Vehicle object in persistent data store.
     * @param vehicle the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeVehicle( Vehicle vehicle ) throws RARException
    {
        persistence.storeVehicle( vehicle );
    }
    
    /**
     * Delete this Vehicle object.
     * @param vehicle the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteVehicle( Vehicle vehicle ) throws RARException
    {
        persistence.deleteVehicle( vehicle );
    }

    /**
     * Create a new Comment object, given the set of initial attribute value.
     * @param comment the text of the comment
     * @param rental the rental for which the comment is made
     * @param customer the customer making the comment
     * @return the new Comment object instance with the given attribute values
     * @throws RARException in case either the rental and/or the customer is null
     */
    public Comment createComment( String comment, Rental rental, Customer customer ) throws RARException
    {
        return new CommentImpl(comment, customer, rental);
    }

    /**
     * Create a new Comment object with undefined attribute values.
     * @return the new Comment object instance
     */
    public Comment createComment() 
    {
        return new CommentImpl(null, null, null);
    }

    /**
     * Return an iterator of Comment objects satisfying the search criteria given in the modelComment object.
     * @param modelComment a model Comment object specifying the search criteria
     * @return an Iterator of the located Comment objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Comment> findComment( Comment modelComment ) throws RARException
    {
        return persistence.restoreComment( modelComment );
    }
    
    /**
     * Store a given Comment object in persistent data store.
     * @param comment the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeComment( Comment comment ) throws RARException
    {
        persistence.storeComment(comment);
    }
    
    /**
     * Delete this Comment object.
     * @param comment the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteComment( Comment comment ) throws RARException
    {
        persistence.deleteComment(comment);
    }

    /**
     * Create a new HourlyPrice object, given the set of initial attribute values.
     * @param minHrs the minimum number of hours for this price
     * @param maxHrs the maximum number of hours for this price
     * @param price the price for this hourly range
     * @param vehicleType the vehicle type this hourly price is for
     * @return the new HourlyPrice object instance with the given attribute values
     * @throws RARException in case the vehicleType is null
     */
    public HourlyPrice createHourlyPrice( int minHrs, int maxHrs, int price, VehicleType vehicleType ) throws RARException
    {
        return new HourlyPriceImpl( minHrs, maxHrs, price, vehicleType );
    }

    /**
     * Create a new HourlyPrice object with undefined attribute values.
     * @return the new HourlyPrice object instance
     */
    public HourlyPrice createHourlyPrice() 
    {
        return new HourlyPriceImpl( 0, 0, 0, null );
    }

    /**
     * Return an iterator of HourlyPrice objects satisfying the search criteria given in the modelHourlyPrice object.
     * @param modelHourlyPrice a model HourlyPrice object specifying the search criteria
     * @return an Iterator of the located HourlyPrice objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<HourlyPrice> findHourlyPrice( HourlyPrice modelHourlyPrice ) throws RARException
    {
        return persistence.restoreHourlyPrice( modelHourlyPrice );
    }
    
    /**
     * Store a given HourlyPrice object in persistent data store.
     * @param hourlyPrice the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeHourlyPrice( HourlyPrice hourlyPrice ) throws RARException
    {
        persistence.storeHourlyPrice( hourlyPrice );
    }
    
    /**
     * Delete this HourlyPrice object.
     * @param hourlyPrice the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteHourlyPrice( HourlyPrice hourlyPrice ) throws RARException
    {
        persistence.deleteHourlyPrice( hourlyPrice );
    }

    /**
     * Return the RentARideConfig object.  The RentARideConfig class is a singleton class,
     * so only one object will exist.
     * @return the RentARideConfig object
     * @throws RARException 
     */
    public RentARideConfig findRentARideConfig() throws RARException
    {
    	return persistence.restoreRentARideConfig(); 
    	
    }
    
    /**
     * Store a given RentARideConfig object in persistent data store.
     * @param rentARideConfig the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeRentARideCfg( RentARideConfig rentARideCfg ) throws RARException
    {
    	persistence.storeRentARideConfig(rentARideCfg);
    }
    
    // Associations
    //
    
    // Customer--creates-->Reservation;   multiplicity: 1 - *
    //
    /**
     * Create a new link between a Reservation and a Customer who placed it 
     * @param customer the customer placing this reservation
     * @param reservation the reservation
     * @throws RARException in case either the customer and/or the reservation is null
     */
    public void createCustomerReservation( Customer customer, Reservation reservation ) throws RARException
    {
        persistence.storeCustomerReservation( customer, reservation);
    }
    
    /**
     * Return the customer who made the reservation (traverse the link from Reservation to Customer).
     * @param reservation the reservation
     * @return the customer who placed the reservation
     * @throws RARException in case either the reservation is null or another error occurs
     */
    public Customer restoreCustomerReservation( Reservation reservation ) throws RARException
    {
        return persistence.restoreCustomerReservation( reservation );
    }
    
    /**
     * Return the Reservations placed by a given customer (traverse the link from Customer to Reservation).
     * @param customer the customer
     * @return the iterator of Reservations placed by the customer
     * @throws RARException in case either the customer is null or another error occurs
     */
    public Iterator<Reservation> restoreCustomerReservation( Customer customer ) throws RARException
    {
        return persistence.restoreCustomerReservation( customer );
    }
    
    /**
     * Delete a link between a customer and a reservation (sever the link from Reservation to Customer).
     * @param customer the customer
     * @param reservation the reservation
     * @throws RARException in case either the customer or reservation is null or another error occurs
     */
    public void deleteCustomerReservation( Customer customer, Reservation reservation ) throws RARException
    {
        persistence.deleteCustomerReservation( customer, reservation );
    }
    
    // Reservation--hasLocation-->RentalLocation;   multiplicity: * - 1
    //
    /**
     * Create a new link between a Reservation and a RentalLocation involved in it.
     * @param reservation the reservation
     * @param rentalLocation the rental location
     * @throws RARException in case either the rentalLocation or reservation is null or another error occurs
     */
    public void createReservationRentalLocation( Reservation reservation, RentalLocation rentalLocation ) throws RARException
    {
        persistence.storeReservationRentalLocation( reservation, rentalLocation );
    }
    
    /**
     * Return the rental location for the given reservation.
     * @param reservation the reservation
     * @return the RentalLocation involved in a given reservation
     * @throws RARException in case either the reservation is null or another error occurs
     */
    public RentalLocation restoreReservationRentalLocation( Reservation reservation ) throws RARException
    {
        return persistence.restoreReservationRentalLocation( reservation );
    }
    
    /**
     * Return the reservations for the given rental location.
     * @param rentalLocation the rental location
     * @return the iterator of Reservations placed at the rental location
     * @throws RARException in case either the rentalLocation is null or another error occurs
     */
    public Iterator<Reservation> restoreReservationRentalLocation( RentalLocation rentalLocation ) throws RARException
    {
        return persistence.restoreReservationRentalLocation( rentalLocation );
    }
    
    /**
     * Delete a link between a reservation and a rental location.
     * @param reservation the reservation
     * @param rentalLocation the rental location
     * @throws RARException in case either the reservation or rentalLocation is null or another error occurs
     */
    public void deleteReservationRentalLocation( Reservation reservation, RentalLocation rentalLocation ) throws RARException
    {
        persistence.deleteReservationRentalLocation(reservation, rentalLocation );
    }
    
    // Vehicle--locatedAt-->RentalLocation;   multiplicity: * - 1
    //
    /**
     * Create a new link between a vehicle and a rental location where it is located.
     * @param vehicle the vehicle
     * @param rentalLocation the rental location
     * @throws RARException in case either the vehicle or rentalLocation is null or another error occurs
     */
    public void createVehicleRentalLocation( Vehicle vehicle, RentalLocation rentalLocation ) throws RARException
    {
        persistence.storeVehicleRentalLocation(vehicle, rentalLocation );
    }
    
    /**
     * Return the rental location where a given vehicle is located.
     * @param vehicle the vehicle
     * @return the rental location of the vehicle
     * @throws RARException in case either the vehicle is null or another error occurs
     */
    public RentalLocation restoreVehicleRentalLocation( Vehicle vehicle ) throws RARException
    {
        return persistence.restoreVehicleRentalLocation( vehicle );
    }
    
    /**
     * Return the vehicles located at the given rental location.
     * @param rentalLocation the rental location
     * @return the iterator of Vehicles parked at the rental location
     * @throws RARException in case either the rentalLocation is null or another error occurs
     */
    public Iterator<Vehicle> restoreVehicleRentalLocation( RentalLocation rentalLocation ) throws RARException
    {
        return persistence.restoreVehicletRentalLocation( rentalLocation );
    }
    
    /**
     * Delete a link between a vehicle and a rental location.
     * @param vehicle the vehicle
     * @param rentalLocation the rental location
     * @throws RARException in case either the vehicle or rentalLocation is null or another error occurs
     */
    public void deleteVehicleRentalLocation( Vehicle vehicle, RentalLocation rentalLocation ) throws RARException
    {
        persistence.deleteVehicleRentalLocation( vehicle, rentalLocation );
    }
    
    // Vehicle--hasType-->VehicleType   multiplicity: * - 1
    //
    /**
     * Create a new link between a vehicle and a vehicle type.
     * @param vehicle the vehicle
     * @param vehicleType the vehicle type
     * @throws RARException in case either the vehicle or vehicleType is null or another error occurs
     */
    public void createVehicleVehicleType( Vehicle vehicle, VehicleType vehicleType ) throws RARException
    {
        persistence.storeVehicleVehicleType( vehicle, vehicleType );
    }
    
    /**
     * Return the vehicle type of a given vehicle.
     * @param vehicle the vehicle
     * @return vehicle type of the vehicle
     * @throws RARException in case either the vehicle is null or another error occurs
     */
    public VehicleType restoreVehicleVehicleType( Vehicle vehicle ) throws RARException
    {
        return persistence.restoreVehicleVehicleType( vehicle );
    }
    
    /**
     * Return the vehicles of a given vehicle type.
     * @param vehicleType the vehicle type
     * @return the iterator of Vehicles of the given vehicle type
     * @throws RARException in case either the vehicleType is null or another error occurs
     */
    public Iterator<Vehicle> restoreVehicleVehicleType( VehicleType vehicleType ) throws RARException
    {
        return persistence.restoreVehicleVehicleType( vehicleType );
    }
    
    /**
     * Delete a link between a vehicle and a vehicle type.
     * @param vehicle the vehicle
     * @param vehicleType the vehicle type
     * @throws RARException in case either the vehicle or vehicleType is null or another error occurs
     */
    public void deleteVehicleVehicleType( Vehicle vehicle, VehicleType vehicleType ) throws RARException
    {
        persistence.deleteVehicleVehicleType( vehicle, vehicleType );
    }

    // Reservation--hasType-->VehicleType   multiplicity: * - 1
    //
    /**
     * Create a new link between a reservation and a vehicle type.
     * @param reservation the reservation
     * @param vehicleType the vehicle type
     * @throws RARException in case either the reservation or vehicleType is null or another error occurs
     */
    public void createReservationVehicleType( Reservation reservation, VehicleType vehicleType ) throws RARException
    {
        persistence.storeReservationVehicleType( reservation, vehicleType );
    }
    
    /**
     * Return the vehicle type involved in a given reservation.
     * @param reservation the reservation
     * @return the vehicle type involved in the reservation
     * @throws RARException in case either the reservation is null or another error occurs
     */
    public VehicleType restoreReservationVehicleType( Reservation reservation ) throws RARException
    {
        return persistence.restoreReservationVehicleType( reservation );
    }
    
    /**
     * Return the reservations for a given vehicle type.
     * @param vehicleType the vehicle type
     * @return the iterator of Reservations for the given vehicle type
     * @throws RARException in case either the vehicleType is null or another error occurs
     */
    public Iterator<Reservation> restoreReservationVehicleType( VehicleType vehicleType ) throws RARException
    {
        return persistence.restoreReservationVehicleType( vehicleType );
    }
    
    /**
     * Delete a link between a reservation and a vehicle type.
     * @param reservation the reservation
     * @param vehicleType the vehicle type
     * @throws RARException in case either the reservation of the vehicleType is null or another error occurs
     */
    public void deleteReservationVehicleType( Reservation reservation, VehicleType vehicleType ) throws RARException
    {
        persistence.deleteReservationVehicleType( reservation, vehicleType );
    }

    // VehicleType--has-->PriceSetting   multiplicity: 1 - *
    //
    /**
     * Create a new link between a vehicle type and an hourly price setting.
     * @param vehicleType the vehicle type
     * @param priceSetting hourly price setting
     * @throws RARException in case either the vehicleType or the priceSetting is null or another error occurs
     */
    public void createVehicleTypeHourlyPrice( VehicleType vehicleType, HourlyPrice priceSetting ) throws RARException
    {
        persistence.storeVehicleTypeHourlyPrice( vehicleType, priceSetting );
    }
    
    /**
     * Return the vehicle type of the given hourly price setting.
     * @param priceSetting the hourly price setting
     * @return the vehicle type of this hourly price setting
     * @throws RARException in case either the priceSetting is null or another error occurs
     */
    public VehicleType restoreVehicleTypeHourlyPrice( HourlyPrice priceSetting ) throws RARException
    {
        return persistence.restoreVehicleTypeHourlyPrice( priceSetting );
    }
    
    /**
     * Return the hourly price settings of the given vehicle type.
     * @param vehicleType the vehicle type
     * @return the iterator of HourlyPrices for the given vehicle type
     * @throws RARException in case either the vehicleType is null or another error occurs
     */
    public Iterator<HourlyPrice> restoreVehicleTypeHourlyPrice( VehicleType vehicleType ) throws RARException
    {
        return persistence.restoreVehicleTypeHourlyPrice( vehicleType );
    }
    
    /**
     * Delete a link between a vehicle type and an hourly price setting.
     * @param vehicleType the vehicle type
     * @param priceSetting the hourly price setting
     * @throws RARException in case either the vehicleType or the priceSetting is null or another error occurs
     */
    public void deleteVehicleTypeHourlyPrice( VehicleType vehicleType, HourlyPrice priceSetting ) throws RARException
    {
        persistence.deleteVehicleTypeHourlyPrice( vehicleType, priceSetting );
    }
    
    // Rental--describedBy-->Comment   multiplicity: 1 - *
    //
    /**
     * Create a new link between a rental and a comment.
     * @param rental the rental
     * @param comment the comment
     * @throws RARException in case either the rental or the comment is null or another error occurs
     */
    public void createRentalComment( Rental rental, Comment comment ) throws RARException
    {
        persistence.storeRentalComment( rental, comment );
    }
    
    /**
     * Return the rental on which a given comment has been made.
     * @param comment the comment
     * @return the Rental commented on
     * @throws RARException in case either the comment or another error occurs
     */
    public Rental restoreRentalComment( Comment comment ) throws RARException
    {
        return persistence.restoreRentalComment( comment );
    }
    
    /**
     * Return the comments made on a given rental.
     * @param rental the rental
     * @return the iterator of Comments made on a given rental
     * @throws RARException in case either the rental or another error occurs
     */
    public Iterator<Comment> restoreRentalComment( Rental rental ) throws RARException
    {
        return persistence.restoreRentalComment( rental );
    }
    
    /**
     * Delete a link between a rental type and a comment.
     * @param rental the rental
     * @param comment the comment
     * @throws RARException in case either the rental or the comment is null or another error occurs
     */
    public void deleteRentalComment( Rental rental, Comment comment ) throws RARException
    {
        persistence.deleteRentalComment(rental, comment );
    }
    
    //Derived associations!!!!
    
    // Customer--commentedBy-->Comment   multiplicity: 1 - *
    //
    // This is a derived association, so the link should be created and deleted by the primary
    // association's create and delete operations.  In this case, via the Customer creates Reservation to 
    // the Rental association class, and then via describedBy to Comment.  So, this link should be 
    // automatically created when a Comment object is created and linked to a Rental.
    // Similarly, this link should be automatically deleted when a Comment object is deleted 
    // and unlinked from a Rental.
    //
    /**
     * Create a new link between a customer and a comment.
     * @param customer the customer
     * @param comment the comment
     * @throws RARException in case either the customer or the comment is null or another error occurs
     */
    // public void createCustomerComment( Customer customer, Comment comment ) throws RARException;
    
    /**
     * Return the customer who made a given comment.
     * @param comment the comment
     * @return the Customer who made the comment
     * @throws RARException in case either the comment is null or another error occurs
     */
    public Customer restoreCustomerComment( Comment comment ) throws RARException
    {
    	return persistence.restoreCustomerComment( comment );
    }
    
    /**
     * Return the comments made by a given customer.
     * @param customer the customer
     * @return an iterator of Comments made by a given customer
     * @throws RARException in case either the customer is null or another error occurs
     */
    public Iterator<Comment> restoreCustomerComment( Customer customer ) throws RARException
    {
    	return persistence.restoreCustomerComment( customer );
    }
    
    /**
     * Delete a link between a customer type and a comment.
     * @param customer the customer
     * @param comment the comment
     * @throws RARException in case either the customer or the comment is null or another error occurs
     */
    // public void deleteCustomerComment( Customer customer, Comment comment ) throws RARException;

    // Rental--rentedBy-->Customer   multiplicity: * - 1
    //
    // This is a derived association, so the link should be created and deleted by the primary
    // association's create and delete operations.  In this case, via the Customer creates Reservation 
    // and then to the Rental association class.  So, this link should be created automatically
    // when a Rental object is created for a Reservation.  Similarly, this link should be automatically 
    // deleted when the Rental association class object instance is deleted.
    //
    /**
     * Create a new link between a Rental and a Customer involved in it.
     * @param rental the Rental 
     * @param customer the Customer
     * @throws RARException in case either the rental or the customer is null or another error occurs
     */
    // public void createRentalCustomer( Rental rental, Customer customer ) throws RARException;
    
    /** 
     * Return the Customer involved in the given Rental.
     * @param rental the Rental
     * @return the Customer involved in the Rental
     * @throws RARException in case either the rental is null or another error occurs
     */
    public Customer restoreRentalCustomer( Rental rental ) throws RARException
    {
    	return persistence.restoreRentalCustomer( rental );
    }
    
    /** 
     * Return all Rentals by a given Customer.
     * @param customer the Customer
     * @return an iterator of Rentals of the Customer
     * @throws RARException in case either customer is null or another error occurs
     */
    public Iterator<Rental> restoreRentalCustomer( Customer customer ) throws RARException
    {
    	return persistence.restoreRentalCustomer( customer );
    }


	@Override
	public RentARideConfig findRentARideCfg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Vehicle> restoreVehicletRentalLocation(
			RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    /** 
     * Delete a link between a Rental and a Customer involved in it.
     * @param rental the Rental
     * @param customer the Customer
     * @throws RARException in case either the rental or the customer is null or another error occurs
     */
    // public void deleteRentalCustomer( Rental rental, Customer customer ) throws RARException;
}
