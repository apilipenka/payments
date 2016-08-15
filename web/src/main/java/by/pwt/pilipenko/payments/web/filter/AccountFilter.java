package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.services.CurrencyService;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

import static by.pwt.pilipenko.payments.web.command.account.AccountUtil.fillAccountParent;

public class AccountFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        fillAccountParent((HttpServletRequest) req);
        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}