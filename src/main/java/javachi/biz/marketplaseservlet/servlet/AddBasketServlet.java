package javachi.biz.marketplaseservlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;
import javachi.biz.marketplaseservlet.dao.BasketDAO;
import javachi.biz.marketplaseservlet.dao.ProductDAO;
import javachi.biz.marketplaseservlet.entity.AuthUser;
import javachi.biz.marketplaseservlet.entity.Basket;
import javachi.biz.marketplaseservlet.entity.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AddBasketServlet", urlPatterns = "/add-to-backed/*")
public class AddBasketServlet extends HttpServlet {
    private final BasketDAO basketDAO = new BasketDAO();
    private final AuthUserDAO authUserDAO = new AuthUserDAO();
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/")+ 1);
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    Optional<AuthUser> byEmail = authUserDAO.findByEmail(cookie.getValue());
                    if (byEmail.isPresent()) {
                        AuthUser user = byEmail.get();
                        basketDAO.save(
                                Basket.childBuilder()
                                        .userId(user.getId())
                                        .products(List.of(
                                                        productDAO.findById(productId)
                                                )
                                        )
                                        .createdAt(LocalDateTime.now())
                                        .build()
                        );
                        resp.sendRedirect("/product/list");
                    }
                }
            }
        }
    }
}
