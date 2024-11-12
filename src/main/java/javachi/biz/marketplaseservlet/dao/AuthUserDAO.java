package javachi.biz.marketplaseservlet.dao;

import jakarta.validation.constraints.NotNull;
import javachi.biz.marketplaseservlet.entity.AuthUser;

import java.util.Optional;

public class AuthUserDAO extends BaseDAO<AuthUser, String> {

    public Optional<AuthUser> findByEmail(@NotNull String email) {
        try {
            begin();

            AuthUser user = entityManager.createQuery("SELECT u FROM AuthUser u where u.email ilike :email", AuthUser.class)
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

}
