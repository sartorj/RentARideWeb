
package edu.uga.cs.rentaride.persistence.impl;

import edu.uga.cs.rentaride.persistence.Persistable;


public abstract class Persistent 
    implements Persistable
{
    private long id;
    
    public Persistent()
    {
        this.id = -1;
    }
    
    public Persistent( long id )
    {
        this.id = id;
    }
    
    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public boolean isPersistent()
    {
        return id >= 0;
    }
}
