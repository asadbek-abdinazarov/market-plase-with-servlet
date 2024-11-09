package javachi.biz.marketplaseservlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@WebFilter(filterName = "SecurityFilter", value = "/*")
public class SecurityFilter implements Filter {

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

        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    value = cookie.getValue();
                    break;
                }
            }
        }

        String requestURI = request.getRequestURI();
        System.out.println("Request URI: " + requestURI);

        if (isOpen.test(requestURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (value != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/auth/login");
        }
    }
}

