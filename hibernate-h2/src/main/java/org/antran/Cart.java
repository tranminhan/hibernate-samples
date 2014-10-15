package org.antran;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atran on 10/14/14.
 */
@Entity
public class Cart
{
    @Id
    Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<CartItem> items = new ArrayList<CartItem>();

    @OneToOne
    PurchaseOrder order;

    public Cart(Long id)
    {
        this.id = id;
    }

    public Cart()
    {

    }

    public void add(CartItem item)
    {
        item.setCart(this);
        this.items.add(item);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public List<CartItem> getItems()
    {
        return items;
    }

    public void setItems(List<CartItem> items)
    {
        this.items = items;
    }

    public PurchaseOrder getOrder()
    {
        return order;
    }

    public void setOrder(PurchaseOrder order)
    {
        this.order = order;
    }
}
