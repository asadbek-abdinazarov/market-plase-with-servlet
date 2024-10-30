package javachi.biz.marketplaseservlet.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javachi.biz.marketplaseservlet.auth.AuthRole;
import javachi.biz.marketplaseservlet.auth.AuthUser;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "RegisterServlet", value = "/auth/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {


            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String confirm_password = req.getParameter("confirm_password");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");

            entityManager.getTransaction().begin();
            entityManager.persist(
                    AuthUser.builder()
                            .email(email)
                            .password(password)
                            .firstName(firstname)
                            .lastName(lastname)
                            .roles(
                                    Set.of(AuthRole.builder()
                                            .roleName("ROLE_USER")
                                            .build())
                            )
                            .build()
            );

            resp.sendRedirect("/home");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            entityManager.getTransaction().commit();

        }

    }
}
