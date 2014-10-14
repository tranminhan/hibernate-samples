package org.antran;

import com.logicalpractice.hibernate.MyEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by atran on 10/14/14.
 */
public class InsertDataTest
{
    private SessionFactory sessionFactory;

    @Before
    public void setup()
    {
        AnnotationConfiguration c = new AnnotationConfiguration();
        c.addAnnotatedClass(MyEntity.class);
        c.addAnnotatedClass(Cart.class);
        c.addAnnotatedClass(CartItem.class);
        c.addAnnotatedClass(PurchaseOrder.class);
        c.addAnnotatedClass(OrderItem.class);
        c.addAnnotatedClass(Account.class);

        sessionFactory = c.buildSessionFactory();
    }

    @After
    public void teardown()
    {

    }

    @Test
    public void shouldInsertData()
    {
        Session session = sessionFactory.openSession();

        final Account account1 = new Account(1L, "user 1");

        final PurchaseOrder order1 = new PurchaseOrder(1L);
        order1.add(new OrderItem(1L, "cart item 1", account1));
        order1.add(new OrderItem(2L, "cart item 2", account1));

        final Cart cart1 = new Cart(1L);
        cart1.add(new CartItem(1L, "cart item 1", account1));
        cart1.add(new CartItem(2L, "cart item 2", account1));
        cart1.setOrder(order1);

        session.save(account1);
        session.save(order1);
        session.save(cart1);

        final Cart cart2 = (Cart) session.get(Cart.class, 1L);
        assertNotNull(cart2);
        assertEquals(2, cart2.getItems().size());

        session.close();
    }
}
