package org.antran;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by atran on 10/14/14.
 */
@Entity
public class Account
{
    @Id
    Long id;
    String name;

    public Account()
    {
    }

    public Account(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
