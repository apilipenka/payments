package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.CurrencyService;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class ExchangeRateFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        CurrencyService currencyService = new CurrencyService();

        try {
            req.setAttribute("currencies", currencyService.getAllEntities());
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