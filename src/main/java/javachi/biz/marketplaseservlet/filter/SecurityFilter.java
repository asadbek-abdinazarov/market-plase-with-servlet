package javachi.biz.marketplaseservlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javachi.biz.marketplaseservlet.dao.AuthUserDAO;
import javachi.biz.marketplaseservlet.entity.AuthUser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@WebFilter(filterName = "SecurityFilter", value = "/*")
public class SecurityFilter implements Filter {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    private static final List<String> WHITE_LIST = List.of(
            "/",
            "/product/list",
            "/auth/login",
            "/auth/register"
    );

    private static final Predicate<String> isOpen = WHITE_LIST::contains;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String email = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    email = cookie.getValue();
                    break;
                }
            }
        }

        String requestURI = request.getRequestURI();
        System.out.println("Request URI: " + requestURI);

        if (isOpen.test(requestURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (email != null) {
            if (requestURI.startsWith("/admin")) {
                Optional<AuthUser> user = authUserDAO.findByEmail(email);
                user.ifPresent(authUser -> {
                    if (authUser.getRole().equals("ADMIN")) {
                        try {
                            filterChain.doFilter(servletRequest, servletResponse);
                        } catch (IOException | ServletException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            response.sendError(403);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            response.sendRedirect("/auth/login");
        }
    }
}

