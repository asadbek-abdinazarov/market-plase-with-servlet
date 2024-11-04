package javachi.biz.marketplaseservlet.servlet.auth;

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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "AuthRegisterServlet", value = "/auth/register")
public class AuthRegisterServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("1email");
        String password = req.getParameter("1password");
        String confirm_password = req.getParameter("1confirm_password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");


        Map<String, String> errors = new HashMap<>();

        if (!StringUtils.isEmailValid(email)) {
            errors.put("email_error", "Email is not valid please check and try again.");
        } else {
            authUserDAO.findByEmail(email).ifPresent(
                    (authUser -> {
                        errors.put("email_error", "Email is already in use.");
                    })
            );
        }

        if (password.isBlank() || password.isEmpty()) {
            errors.put("password_error", "Password cannot be empty or null.");
        }
        if (!Objects.equals(confirm_password, password)) {
            errors.put("confirm_password_error", "Password does not match.");
        }

        if (!errors.isEmpty()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/register.jsp");
            errors.forEach(req::setAttribute);
            requestDispatcher.forward(req, resp);
        } else {
            AuthUser user = AuthUser.childBuilder()
                    .email(email)
                    .password(PasswordUtils.encode(password))
                    .firstName(firstname)
                    .lastName(lastname)
                    .status(AuthUser.Status.ACTIVE)
                    .createdAt(LocalDateTime.now())
                    .role("ROLE_USER")
                    .build();

            authUserDAO.save(user);
            resp.sendRedirect("/auth/login");
        }
    }
}
