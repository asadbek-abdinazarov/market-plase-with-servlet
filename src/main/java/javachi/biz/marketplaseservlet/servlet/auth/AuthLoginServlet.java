package javachi.biz.marketplaseservlet.servlet.auth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;
import javachi.biz.marketplaseservlet.entity.AuthUser;
import javachi.biz.marketplaseservlet.utils.PasswordUtils;
import javachi.biz.marketplaseservlet.utils.StringUtils;
import org.postgresql.util.PasswordUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AuthLoginServlet", value = "/auth/login")
public class AuthLoginServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Berilgan email bor bolsa uning passwordi bilan tekshiradi
         * Agar data baseda bunday email email not found chiqarish kerak
         * Agar bolsa Home pagega redirect */
        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();*/
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        /*entityManager.getTransaction().begin();
        AuthUser authUser = entityManager.find(AuthUser.class, email);
        entityManager.getTransaction().commit();*/


        Map<String, String> errors = new HashMap<>();

        if (!StringUtils.isEmailValid(email)) {
            errors.put("email_error", "Invalid email");
        }
        if (PasswordUtils.checkPassword(password, authUserDAO.findByEmail(email).get().getPassword())) {
            resp.sendRedirect("/home");
        }else {
            errors.put("password_error", "Invalid password");
        }

        if (password.isBlank() || password.isEmpty()) {
            errors.put("password_error", "Password cannot be empty or null.");
        }
        if (!errors.isEmpty()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/login.jsp");
            errors.forEach(req::setAttribute);
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/home");
        }
    }
}
