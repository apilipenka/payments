package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.ExchangeRateService;
import by.pwt.pilipenko.payments.model.VO.ExchangeRateVO;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("exchangeRateList");
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        List<ExchangeRate> exchangeRateList = null;

        if (name == null) {
            name = req.getParameter("exchangeRateList");
            if (name == null) {
                try {
                    exchangeRateList = exchangeRateService.getAllEntities();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (exchangeRateList != null) {
            List<ExchangeRateVO> bankVOList = new ArrayList<ExchangeRateVO>();
            for (ExchangeRate exchangeRate : exchangeRateList) {
                bankVOList.add(exchangeRate.createExchangeRateVO());
            }
            req.setAttribute("exchangeRateList", bankVOList);
        }


        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}