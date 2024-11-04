package javachi.biz.marketplaseservlet.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.constraints.NotNull;
import javachi.biz.marketplaseservlet.entity.BaseEntity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDAO<T extends BaseEntity, ID extends Serializable> {

    protected static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("marketPlace");
    protected static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public BaseDAO() {
        this.persistentClass =
                (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected void begin() {
        entityManager.getTransaction().begin();
    }

    protected void commit() {
        entityManager.getTransaction().commit();
    }


    public T save(T entity) {
        begin();
        entityManager.persist(entity);
        commit();
        return entity;
    }

    public T update(T entity) {
        begin();
        entityManager.merge(entity);
        commit();
        return entity;
    }

    public T findById(@NotNull ID id) {
        begin();
        T entity = entityManager.find(persistentClass, id);
        commit();
        return entity;
    }

    public boolean delete(@NotNull ID id) {
        begin();
        entityManager.createQuery("delete from " + persistentClass + " t where t.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        commit();
        return true;
    }

    public List<T> findAll() {
        begin();
        List<T> entities = entityManager
                .createQuery("from " + persistentClass, persistentClass).getResultList();
        commit();
        return entities;
    }


}
