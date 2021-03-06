package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.CurrencyService;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class CurrenciesExtFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("currencyExtList");
        CurrencyService currencyService = new CurrencyService();

        if (name == null) {
            name = req.getParameter("currencyExtList");
            if (name == null) {
                try {
                    req.setAttribute("currencyExtList", currencyService.getAllEntities());
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
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