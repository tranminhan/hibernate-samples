package org.antran;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atran on 10/14/14.
 */
@Entity
public class PurchaseOrder
{
    @Id
    Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> items = new ArrayList<OrderItem>();

    public PurchaseOrder()
    {
    }

    public PurchaseOrder(Long id)
    {
        this.id = id;
    }

    public void add(OrderItem item)
    {
        item.setOrder(this);
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

    public List<OrderItem> getItems()
    {
        return items;
    }

    public void setItems(List<OrderItem> items)
    {
        this.items = items;
    }
}
