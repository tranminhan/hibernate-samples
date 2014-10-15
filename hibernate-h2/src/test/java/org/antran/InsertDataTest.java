package org.antran;

import com.mkyong.util.HibernateUtil;
import org.apache.commons.lang.RandomStringUtils;
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
    private void createAccountCartOrder(Long accountId)
    {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        final Account account1 = new Account(accountId, "user " + RandomStringUtils.randomAlphabetic(7));

        final PurchaseOrder order1 = new PurchaseOrder(accountId);
        order1.add(new OrderItem("cart item 1: " + RandomStringUtils.randomAlphabetic(7), account1));
        order1.add(new OrderItem("cart item 2: " + RandomStringUtils.randomAlphabetic(7), account1));

        final Cart cart1 = new Cart(accountId);
        cart1.add(new CartItem("cart item 1: " + RandomStringUtils.randomAlphabetic(7), account1));
        cart1.add(new CartItem("cart item 2: " + RandomStringUtils.randomAlphabetic(7), account1));
        cart1.setOrder(order1);

        session.save(account1);
        session.save(order1);
        session.save(cart1);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void shouldInsertData()
    {
        createAccountCartOrder(1L);
        createAccountCartOrder(2L);

        final Session session = HibernateUtil.getSessionFactory().openSession();

        final Cart cart = (Cart) session.load(Cart.class, 1L);
        assertNotNull(cart);
        assertEquals(2, cart.getItems().size());

        final Criteria criteria = queryCartByAccountId(2L, session);
        final List carts = criteria.list();
        assertNotNull(carts);
        assertEquals(1, carts.size());

        session.close();
    }

    private Criteria queryCartByAccountId(Long accountId, Session session)
    {
        final Criteria criteria = session.createCriteria(Cart.class, "cart");
        final Criteria orderItem = criteria.createAlias("cart.order.items", "orderItem");
        final Criteria accountCriteria = orderItem.createAlias("orderItem.account", "account");
        accountCriteria.add(Restrictions.eq("account.id", accountId));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    @Test
    public void shouldInsertData2()
    {
        createAccountCartOrder(3L);
        createAccountCartOrder(4L);

        final Session session = HibernateUtil.getSessionFactory().openSession();

        final Long NOT_EXIST_ACCOUNT_ID = 0L;
        final Criteria criteria = queryCartByAccountId(NOT_EXIST_ACCOUNT_ID, session);
        final List carts = criteria.list();
        assertNotNull(carts);
        assertEquals(0, carts.size());

        session.close();
    }
}
