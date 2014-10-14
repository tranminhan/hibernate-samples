package org.antran;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by atran on 10/14/14.
 */
@Entity
public class OrderItem
{
    @Id
    Long id;
    String name;

    @ManyToOne
    Account account;

    @ManyToOne
    PurchaseOrder order;

    public OrderItem()
    {
    }

    public OrderItem(Long id, String name, Account account)
    {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public PurchaseOrder getOrder()
    {
        return order;
    }

    public void setOrder(PurchaseOrder order)
    {
        this.order = order;
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

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }
}
