package javachi.biz.marketplaseservlet.dao;

import javachi.biz.marketplaseservlet.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends BaseDAO<Product, String> {

    public ProductDAO() {
        super();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            begin();
            products = entityManager.createQuery("from Product", Product.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            commit();
        }
        return products;
    }

    public List<Product> findAllByBasketId(String id) {
        List<Product> products = new ArrayList<>();
        try {
            begin();
            products = entityManager
                    .createQuery("from Product where basket.id = :id", Product.class)
                    .setParameter("id", id)
                    .getResultList();
            commit();
            return products;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return products;
    }
}
