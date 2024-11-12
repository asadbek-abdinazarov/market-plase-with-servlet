package javachi.biz.marketplaseservlet.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.constraints.NotNull;
import javachi.biz.marketplaseservlet.entity.BaseEntity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDAO<T extends BaseEntity, ID extends Serializable> {

    protected static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("marketPlace");
    protected static final EntityManager entityManager = entityManagerFactory.createEntityManager();


    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public BaseDAO() {
        // This is the correct way to get the generic type
        Type tType = getClass().getGenericSuperclass();
        if (tType instanceof ParameterizedType parameterizedType) {
            this.persistentClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        } else {
            throw new RuntimeException("Class does not have a parameterized type.");
        }
    }

    protected void begin() {
        entityManager.getTransaction().begin();
    }

    protected void commit() {
        entityManager.getTransaction().commit();
    }

    protected void rollback() {
        entityManager.getTransaction().rollback();
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
