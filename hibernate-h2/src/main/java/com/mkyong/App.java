package com.mkyong;

import com.mkyong.stock.Category;
import com.mkyong.stock.Stock;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class App
{
    public static void main(String[] args)
    {
        System.out.println("Hibernate many to many (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Stock stock = new Stock();
        stock.setStockCode("7052");
        stock.setStockName("PADINI");

        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");

        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);

        stock.setCategories(categories);

        session.save(stock);


        session.getTransaction().commit();

        session.close();
        System.out.println("Done");
    }
}
