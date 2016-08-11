package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.services.ExchangeRateService;
import by.pwt.pilipenko.payments.services.UserRoleService;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class ExchangeRatesFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("exchangeRateList");
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        if (name == null) {
            name = req.getParameter("exchangeRateList");
            if (name == null) {
                try {
                    req.setAttribute("exchangeRateList", exchangeRateService.getAllEntities());
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