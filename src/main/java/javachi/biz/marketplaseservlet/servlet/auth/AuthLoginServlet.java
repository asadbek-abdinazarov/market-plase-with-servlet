package javachi.biz.marketplaseservlet.servlet.auth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;
import javachi.biz.marketplaseservlet.entity.AuthUser;
import javachi.biz.marketplaseservlet.utils.PasswordUtils;
import javachi.biz.marketplaseservlet.utils.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Map<String, String> errors = new HashMap<>();

        if (email == null || !StringUtils.isEmailValid(email)) {
            errors.put("email_error", "Invalid email");
        }

        Optional<AuthUser> optionalUser = authUserDAO.findByEmail(email);
        if (optionalUser.isPresent()) {
            AuthUser user = optionalUser.get();
            if (!PasswordUtils.checkPassword(password, user.getPassword())) {
                errors.put("password_error", "Invalid password");
            } else {
                // Set user session if authenticated
                req.getSession().setAttribute("user", email);
                Cookie userCookie = new Cookie("user", email);
                userCookie.setMaxAge(60 * 60 * 2);
                userCookie.setPath("/");
                resp.addCookie(userCookie);
            }
        } else {
            errors.put("email_error", "Email not found");
        }

        if (password == null || password.isBlank()) {
            errors.put("password_error", "Password cannot be empty or null.");
        }

        if (!errors.isEmpty()) {
            errors.forEach(req::setAttribute);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/home");
        }
    }
}
