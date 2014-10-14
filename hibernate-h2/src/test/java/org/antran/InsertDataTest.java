package org.antran;

import com.mkyong.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by atran on 10/14/14.
 */
public class InsertDataTest
{

    private void insertTestData()
    {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

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
        for (OrderItem item : order1.getItems())
        {
            session.save(item);
        }

        session.save(cart1);
        for (CartItem item : cart1.getItems())
        {
            session.save(item);
        }

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void shouldInsertData()
    {
        insertTestData();
        final Session session = HibernateUtil.getSessionFactory().openSession();

        final Cart cart = (Cart) session.load(Cart.class, 1L);
        assertNotNull(cart);
        assertEquals(2, cart.getItems().size());

        final Criteria criteria = session.createCriteria(Cart.class, "cart");
        final Criteria orderItem = criteria.createAlias("cart.order.items", "orderItem");
        final Criteria accountCriteria = orderItem.createAlias("orderItem.account", "account");
        accountCriteria.add(Restrictions.eq("account.id", 1L));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        final List carts = criteria.list();
        assertNotNull(carts);
        assertEquals(1, carts.size());

        session.close();
    }
}
