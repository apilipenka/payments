package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserService;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class AgreementFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        BankService bankService = new BankService();

        try {
            req.setAttribute("banks", bankService.getAllEntities());
        } catch (SQLException | NamingException e) {
            // e.printStackTrace();
            // TODO add error handler
        }

        UserService userService = new UserService();

        try {
            req.setAttribute("users", userService.getAllEntities());
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