package javachi.biz.marketplaseservlet.dao;

import jakarta.validation.constraints.NotNull;
import javachi.biz.marketplaseservlet.entity.AuthUser;
import javachi.biz.marketplaseservlet.entity.VerifyEmailEntity;

import java.util.Optional;

public class VerifyEmailDAO extends BaseDAO<VerifyEmailEntity, String> {

    public Optional<VerifyEmailEntity> findByEmail(@NotNull String email) {
        try {
            begin();

            VerifyEmailEntity user = entityManager.createQuery("SELECT u FROM VerifyEmailEntity u where u.email ilike :email", VerifyEmailEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
            return Optional.empty();
        }
    }

    public Optional<VerifyEmailEntity> findByPassword(@NotNull String email) {
        try {
            begin();

            VerifyEmailEntity user = entityManager.createQuery("SELECT u FROM VerifyEmailEntity u where u.email ilike :email", VerifyEmailEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
            return Optional.empty();
        }
    }

    public void deleteVerifyEmail(String id) {
        try {
            begin();
            entityManager.createQuery("DELETE FROM VerifyEmailEntity WHERE id = :id").setParameter("id", id).executeUpdate();
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }
}
