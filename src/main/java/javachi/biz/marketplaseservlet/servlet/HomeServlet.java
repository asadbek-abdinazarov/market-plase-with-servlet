package javachi.biz.marketplaseservlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;

import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    value = cookie.getValue();
                    break;
                }
            }
        }
        if (value != null) {
            authUserDAO.findByEmail(value).ifPresent(authUser -> {
                req.setAttribute("user", authUser.getEmail());
                req.setAttribute("userRole", authUser.getRole());
                try {
                    req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            resp.sendRedirect("/auth/login");
        }}
}
