package fr.m2i.webdistributeur.filter;

import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.entity.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RoleFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RoleFilter initialized");
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            this.context.log("RoleFilter no session really weird");
            return;
        }

        Role role = ((User) session.getAttribute("user")).getRole();
        String uri = ((HttpServletRequest) request).getRequestURI();

        if (!Role.checkUri(role, uri.replace("/webdistributeur/", ""))) {
            this.context.log("RoleFilter user is not granted");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/META-INF/showProduct.jsp");
            dispatcher.forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
        //we can close resources here
    }
}
