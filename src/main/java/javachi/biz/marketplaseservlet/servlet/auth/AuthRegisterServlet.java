package javachi.biz.marketplaseservlet.servlet.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;
import javachi.biz.marketplaseservlet.dao.VerifyEmailDAO;
import javachi.biz.marketplaseservlet.entity.AuthUser;
import javachi.biz.marketplaseservlet.entity.VerifyEmailEntity;
import javachi.biz.marketplaseservlet.service.EmailService;
import javachi.biz.marketplaseservlet.utils.PasswordUtils;
import javachi.biz.marketplaseservlet.utils.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@WebServlet(name = "AuthRegisterServlet", value = "/auth/register")
public class AuthRegisterServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();
    private final VerifyEmailDAO verifyEmailDAO = new VerifyEmailDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
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
                    .status(AuthUser.Status.INACTIVE)
                    .createdAt(LocalDateTime.now())
                    .role("USER")
                    .build();
            authUserDAO.save(user);
            String subject = "Verify Email";
            String code = generateVerificationCode();
            String message = "Please verify your email address and your password is " + code;
            try {
                EmailService.sendEmail(email, subject, message);
                resp.getWriter().write("Email sent successfully!");
            } catch (Exception e) {
                resp.getWriter().write("Error while sending email: " + e.getMessage());
            }

            verifyEmailDAO.save(VerifyEmailEntity.childBuilder()
                    .email(email)
                    .verifyCode(code)
                    .build());
            req.getSession().setAttribute("user", email);
            resp.sendRedirect("/verifyPassword");
        }
    }

    public String generateVerificationCode() {
        Random random = new Random();
        int i = random.nextInt(100000, 900000);
        return String.valueOf(i);
    }
}
