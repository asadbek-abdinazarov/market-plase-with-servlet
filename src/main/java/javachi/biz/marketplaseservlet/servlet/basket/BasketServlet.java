package javachi.biz.marketplaseservlet.servlet.basket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;
import javachi.biz.marketplaseservlet.dao.BasketDAO;
import javachi.biz.marketplaseservlet.dao.ProductDAO;

import java.io.IOException;

@WebServlet(name = "BasketServlet", value = "/basket")
public class BasketServlet extends HttpServlet {
    private final BasketDAO basketDAO = new BasketDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    email = cookie.getValue();
                    break;
                }
            }
        }

        authUserDAO.findByEmail(email)
                .flatMap(
                        authUser -> basketDAO.findBasketByUserId(
                                authUser.getId()
                        )
                ).ifPresent(basket -> {
                            req.setAttribute("products", productDAO.findAllByBasketId(basket.getId()));
                        }
                );

        req.getRequestDispatcher("/views/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(405);
    }
}
