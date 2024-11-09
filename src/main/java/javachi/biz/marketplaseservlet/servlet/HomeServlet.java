package javachi.biz.marketplaseservlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {

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
            req.setAttribute("user", value);
            req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/auth/login");
        }
    }
}
