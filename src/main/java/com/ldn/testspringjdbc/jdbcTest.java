/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldn.testspringjdbc;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author three
 */
public class jdbcTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    
//        DataSource dataSource = (DataSource) context.getBean("dataSource");
//        JdbcTemplate template = new JdbcTemplate(dataSource);
        JdbcTemplate t = (JdbcTemplate) context.getBean("jdbctemplate");
        
//        template.update("UPDATE category SET description=? WHERE id=?", "Dong ho", 4);
        List<String> cate = t.query("SELECT * FROM category WHERE description is NULL", (rs, i) -> {
            return rs.getString("name"); //To change body of generated lambdas, choose Tools | Templates.
        });
        
        cate.forEach(v -> System.out.println(v));
    }
    
    
    
}
