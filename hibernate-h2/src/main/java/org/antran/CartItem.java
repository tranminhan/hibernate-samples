package org.antran;

import javax.persistence.*;

/**
 * Created by atran on 10/14/14.
 */
@Entity
public class CartItem
{
    @Id
    @GeneratedValue
    Long id;
    String name;

    @ManyToOne
    Account account;

    @ManyToOne
    Cart cart;

    @OneToOne
    OrderItem orderItem;

    public CartItem()
    {
    }

    public CartItem(Long id, String name, Account account)
    {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public CartItem(String name, Account account)
    {
        this.name = name;
        this.account = account;
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

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public OrderItem getOrderItem()
    {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem)
    {
        this.orderItem = orderItem;
    }
}
