package net.ddns.iiiedug02.filter;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Servlet Filter implementation class SameSiteFilter
 */
@WebFilter("/login")
public class SameSiteFilter extends HttpFilter implements Filter {


    private static final long serialVersionUID = 1L;

    public SameSiteFilter() {
        super();
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig fConfig) throws ServletException {}

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Principal p =
                (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null != p) {
            response.getWriter().print(p);
        }
        System.out.println("login---------------------");
        response.setHeader("Set-Cookie", "SameSite=None");
        chain.doFilter(request, response);
    }
}
