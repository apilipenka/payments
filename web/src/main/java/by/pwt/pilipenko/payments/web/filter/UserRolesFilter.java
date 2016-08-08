package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.UserRoleService;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class UserRolesFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("userRoleList");
        UserRoleService userRoleService = new UserRoleService();

        if (name == null) {
            name = req.getParameter("userRoleList");
            if (name == null) {
                try {
                    req.setAttribute("userRoleList", userRoleService.getAllEntities());
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }
            }
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}