//
// A control class to implement the 'View customer info' use case
//
//


package edu.uga.cs.rentaride.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.object.ObjectLayer;


public class ViewCustomerInfoCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public ViewCustomerInfoCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Customer> ViewCustomerInfo(String userName)
            throws RARException
    {
        List<Customer> 	    customers  = null;
        Iterator<Customer> 	customerIter = null;
        Customer     	    customer = null;

        customers = new LinkedList<Customer>();
        
        // retrieve all Customer objects
        //
        Customer modelCust = objectLayer.createCustomer();
        modelCust.setUserName(userName);
        
        customerIter = objectLayer.findCustomer( modelCust );
        while( customerIter.hasNext() ) {
            customer = customerIter.next();
            System.out.println( customer);
            customers.add( customer );
        }

        return customers;
    }
}