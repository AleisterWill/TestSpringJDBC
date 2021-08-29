/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateTest;

import hibernateTest.pojo.Category;
import hibernateTest.pojo.Manufacturer;
import hibernateTest.pojo.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author three
 */
public class HibernateUtils {
    private static final SessionFactory FACTORY;
    
    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
//        Properties props = new Properties();
//        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        props.put(Environment.URL, "jdbc:mysql://localhost/saledb");
//        props.put(Environment.USER, "root");
//        props.put(Environment.PASS, "StanLA20");
//        props.put(Environment.SHOW_SQL, true);
//        
//        cfg.setProperties(props);
        cfg.addAnnotatedClass(Category.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Manufacturer.class);
        
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        FACTORY = cfg.buildSessionFactory(registry);
    }
    
    /**
     * @return the FACTORY
     */
    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
