package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.AccountService;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class CardFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        AccountService accountService = new AccountService();

        try {
            req.setAttribute("accounts", accountService.getAllEntities());
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