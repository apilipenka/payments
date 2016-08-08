package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.UserRoleService;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class UserFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        UserRoleService userRoleService = new UserRoleService();

        try {
            req.setAttribute("roles", userRoleService.getAllEntities());
        } catch (SQLException | NamingException e) {
            // e.printStackTrace();
            // TODO add error handler
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}