package javachi.biz.marketplaseservlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;
import javachi.biz.marketplaseservlet.dao.VerifyEmailDAO;
import javachi.biz.marketplaseservlet.entity.AuthUser;
import javachi.biz.marketplaseservlet.entity.VerifyEmailEntity;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/verifyPassword")
public class VerifyEmailPassword extends HttpServlet {
    private final VerifyEmailDAO verifyEmailDAO = new VerifyEmailDAO();
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/sendEmailVerification.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String verifyPassword = req.getParameter("verify_password");
        String email = req.getSession(false).getAttribute("user").toString();
        Optional<VerifyEmailEntity> byEmail = verifyEmailDAO.findByEmail(email);
        if (byEmail.isPresent()) {
            VerifyEmailEntity verifyEmailEntity = byEmail.get();
            if (verifyEmailEntity.getVerifyCode().equals(verifyPassword)) {
                req.setAttribute("email_message", "Email verification successful please login with your email");
                Cookie userCookie = new Cookie("user", email);
                userCookie.setMaxAge(60 * 60 * 2);
                userCookie.setPath("/");
                resp.addCookie(userCookie);
                authUserDAO.findByEmail(email).ifPresent(authUser -> {
                    authUser.setStatus(AuthUser.Status.ACTIVE);
                    authUserDAO.update(authUser);
                });
                resp.sendRedirect("/auth/login");
                verifyEmailDAO.deleteVerifyEmail(verifyEmailEntity.getId());
            } else {
                try {
                    req.setAttribute("error_verification", "Email verification failed");
                    resp.sendRedirect("/auth/register");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
