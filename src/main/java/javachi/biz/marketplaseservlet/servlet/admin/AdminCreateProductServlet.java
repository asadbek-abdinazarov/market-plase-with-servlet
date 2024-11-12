package javachi.biz.marketplaseservlet.servlet.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;
import javachi.biz.marketplaseservlet.dao.BasketDAO;
import javachi.biz.marketplaseservlet.dao.ProductDAO;
import javachi.biz.marketplaseservlet.entity.AuthUser;
import javachi.biz.marketplaseservlet.entity.Basket;
import javachi.biz.marketplaseservlet.entity.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(name = "AdminCreateProductServlet", value = "/admin/create-product")
public class AdminCreateProductServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();
    private final AuthUserDAO authUserDAO = new AuthUserDAO();
    private final BasketDAO basketDAO = new BasketDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/createProduct.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String prodName = req.getParameter("prodName");
            String prodDesc = req.getParameter("prodDesc");
            Double prodPrice = Double.parseDouble(req.getParameter("prodPrice"));
            Integer prodAmount = Integer.parseInt(req.getParameter("prodAmount"));
            String prodType = req.getParameter("consumer-products");
            String product_unit = req.getParameter("product-unit");

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
            Optional<AuthUser> byEmail = authUserDAO.findByEmail(email);

            byEmail.flatMap(authUser -> basketDAO.findBasketByUserId(authUser.getId())).ifPresent(basket -> {
                productDAO.save(
                        Product.childBuilder()
                                .prodName(prodName)
                                .prodDesc(prodDesc)
                                .basket(basket)
                                .prodType(prodType)
                                .amount(prodAmount)
                                .prodPrice(prodPrice)
                                .unit(product_unit)
                                .createdAt(LocalDateTime.now())
                                .build()
                );
            });
            resp.sendRedirect("/product/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}