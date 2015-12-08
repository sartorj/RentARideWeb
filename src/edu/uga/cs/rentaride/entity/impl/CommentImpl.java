package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

/** This class represents a comment made by a specific Customer regarding a specific Rental experience.
 *
 */
public class CommentImpl
    extends Persistent
    implements Comment
{
    //Comment attributes
    private String  text;
    private Date    date;
    private Customer    customer;
    private Rental  rental;
    
    //When creating a brand new comment, customer and rental need to be persistent
    public CommentImpl(String text, Customer customer, Rental rental) 
    {
        super(-1);
        /*CHECK FOR PERSISTENCE AND THROW ERROR!!*/
        this.text = text;
        this.date = new Date();
        this.customer = customer;
        this.rental = rental;
    }
    
    /** Return the comment string.
     * @return a String this is this comment.
     */
    public String getComment()
    {
        return text;
    }
    
    /** Set the new value of this comment.
     * @param comment a String which is the new comment.
     */
    public void   setComment( String comment )
    {
        this.text = comment;
    }
    
    /** Return the Rental object this comment is linked to.
     * @return the Rental object for this comment.
     */
    public Rental getRental() 
    {
        return rental;
    }
    
    /** Set the new Rental object linked to this comment.
     * @param rental the new Rental object this comment should be linked to.
     */
    public void   setRental( Rental rental )
    {
        this.rental = rental;
    }
    
    /** Returns the customer who commented on a rental.
     * @return the Customer who made this comment
     */
    public Customer getCustomer()
    {
        return customer;
    }
    
    /** Set the new Customer who made this comment.
     * @param customer ths new Customer who made this comment.
     */
    public void   setCustomer( Customer customer )
    {
        this.customer = customer;
    }
    
    /** Returns the Date of the comment on a rental.
     * @return the Date of the comment
     */
    
    
    
    public Date getDate()
    {
        return date;
    }
    
    /** Set the Date of this comment.
     * @param Date ths new Date of the comment.
     */
    public void   setDate( Date date )
    {
        this.date = date;
    }
}
