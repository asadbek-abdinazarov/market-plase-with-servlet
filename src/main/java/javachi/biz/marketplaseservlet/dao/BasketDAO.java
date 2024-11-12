package javachi.biz.marketplaseservlet.dao;

import javachi.biz.marketplaseservlet.entity.Basket;
import javachi.biz.marketplaseservlet.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasketDAO extends BaseDAO<Basket, String> {

    public BasketDAO() {
        super();
    }

    public Optional<List<Product>> findProductsByUserIdFromBasketTable(String userId) {
        List<Product> products = new ArrayList<>();
        try {
            begin();

//            entityManager.createQuery("from ")

            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(products);
    }

    public Optional<Basket> findBasketByUserId(String id) {
        try {
            begin();
            Basket basket = entityManager
                    .createQuery("from Basket where authUser.id = :id", Basket.class)
                    .setParameter("id", id).getSingleResult();
            commit();
            return Optional.of(basket);
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
