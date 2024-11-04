package javachi.biz.marketplaseservlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        /*for (String req : WHITE_LIST) {
            if (!requestURI.equals(req)){
                response.sendRedirect("/auth/login");
            }else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }*/

        if (!isOpen.test(requestURI)) {
            response.sendRedirect("/auth/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
