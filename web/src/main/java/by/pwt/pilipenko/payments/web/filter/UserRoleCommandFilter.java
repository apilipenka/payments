package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.services.UserRoleService;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class UserRoleCommandFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        UserRoleService userRoleService = new UserRoleService();


        try {
            req.setAttribute("roles", userRoleService.getAllEntities());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }


        CommandService commandService = new CommandService();

        try {
            req.setAttribute("commands", commandService.getAllEntities());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}