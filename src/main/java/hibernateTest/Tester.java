/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateTest;

import hibernateTest.pojo.Category;
import hibernateTest.pojo.Manufacturer;
import hibernateTest.pojo.Product;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author three
 */
public class Tester {
    public static void main(String[] args) {
        Session session = HibernateUtils.getFACTORY().openSession();
        
        //Get Criteria Builder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        
        //Join table
        Root<Category> cRoot = query.from(Category.class);
        Root<Product> pRoot = query.from(Product.class);
        query.where(builder.equal(pRoot.get("category"), cRoot.get("id")));
        
        query = query.multiselect(
                cRoot.get("name").as(String.class),
                builder.count(pRoot.get("id").as(Integer.class)),
                builder.max(pRoot.get("price").as(BigDecimal.class))
        );
        query = query.groupBy(cRoot.get("name").as(String.class));
        query = query.orderBy(builder.desc(cRoot.get("name").as(String.class)));
        
        Query q = session.createQuery(query);
        List<Object[]> result = q.getResultList();
        result.forEach(v -> System.out.printf("%s - So luong: %d - Gia cao nhat: %.2f\n", v[0], v[1], v[2]));
        ////////
//        Root root = query.from(Product.class);
//        query = query.multiselect(
//                builder.count(root.get("id").as(Integer.class)),        //0
//                builder.min(root.get("price").as(BigDecimal.class)),    //1
//                builder.max(root.get("price").as(BigDecimal.class)),    //2
//                builder.avg(root.get("price").as(BigDecimal.class))     //3
//        );
//        
//        Query q = session.createQuery(query);
//        Object[] result = (Object[]) q.getSingleResult();
//        System.out.printf("Count = %s\nMin = %s\nMax = %s\n Average = %s", result[0].toString(), result[1].toString(), result[2].toString(), result[3].toString());
        
        
        ////////// Xây dựng CriteriaBuilder
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Product> query = builder.createQuery(Product.class);
//        Root root = query.from(Product.class);
//        query = query.select(root);
//        
//////////Điều kiện description 64GB và giá hơn 13tr
//        Predicate p1 = builder.like(root.get("description").as(String.class), "%64GB%");
//        Predicate p2 = builder.greaterThanOrEqualTo(root.get("price").as(BigDecimal.class), new BigDecimal(13000000));
//        query = query.where(builder.and(p1, p2));
        
/////////Truy vấn
//        Query q = session.createQuery(query);
//        List<Product> LP = q.getResultList();
//////////Xuất
//        LP.forEach(v -> System.out.printf("%d - %s\n", v.getId(), v.getName()));
        
        
/////////Lấy tất cả sản phẩm có manu id = 4
//        Manufacturer m = session.get(Manufacturer.class, 4);
//        m.getProducts().forEach(v -> System.out.printf("%d - %s\n", v.getId(), v.getName()));
//        
//////////Lấy tất cả sản phẩm có category id 1
//        Category c = session.get(Category.class, 1);
//        c.getProducts().forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

/////////Thêm sản phẩm (anh bỏ qua cái này cũng đc tại vì nó phức tạp kiểu 1 sản phẩm thuộc 2 nhà sx)
//        Product p = new Product();
//        p.setName("Asus Gaming Laptop 2021");
//        p.setPrice(new BigDecimal(15555555));
//        Set<Manufacturer> mans = new HashSet<>();
//        mans.add(session.get(Manufacturer.class, 4));
//        mans.add(session.get(Manufacturer.class, 3));
//        p.setManufacturers(mans);
//        p.setCategory(session.get(Category.class, 3));
//        session.save(p);

/////////Thêm category mới
//        Category c = new Category();
//        c.setName("Xin chao");
//        c.setDescription("Toi la test");
//        session.save(c);

//////////Cập nhật tên của category có id 5
//        Category d = session.get(Category.class, 5);
//        d.setName("Tesing testing");
//        session.getTransaction().begin();
//        session.save(p);
//        session.getTransaction().commit();

/////////////Lấy tất cả các category
//        Query q = session.createQuery("FROM Category");
//        List<Category> LC = q.getResultList();
//        LC.forEach(v -> System.out.printf("%d - %s\n", v.getId(), v.getName()));
//        
        session.close();
    }
}
