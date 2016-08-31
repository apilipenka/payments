package by.pwt.pilipenko.payments.web.command.currencyext;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.model.VO.ExchangeRateVO;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.services.ExchangeRateService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditCurrencyExtCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newcurrencyext");

        CurrencyService currencyService = new CurrencyService();
        Object name = request.getAttribute("currencyID");

        try {
            if (name != null) {

                request.setAttribute("currency", currencyService.getEntity(Integer.parseInt(name.toString())));

                ExchangeRateService exchangeRateService = new ExchangeRateService();

                List<ExchangeRate> exchangeRateList = null;

                exchangeRateList = exchangeRateService.searchEntityParent(Integer.parseInt(name.toString()), null);

                if (exchangeRateList != null) {
                    List<ExchangeRateVO> bankVOList = new ArrayList<ExchangeRateVO>();
                    for (ExchangeRate exchangeRate : exchangeRateList) {
                        bankVOList.add(exchangeRate.createExchangeRateVO());
                    }
                    request.setAttribute("exchangeRateExtList", bankVOList);
                }

            } else {
                name = request.getParameter("currencyID");
                if (name != null) {
                    request.setAttribute("currency", currencyService.getEntity(Integer.parseInt(name.toString())));
                    ExchangeRateService exchangeRateService = new ExchangeRateService();
                    List<ExchangeRate> exchangeRateList = null;

                    exchangeRateList = exchangeRateService.searchEntityParent(Integer.parseInt(name.toString()), null);

                    if (exchangeRateList != null) {
                        List<ExchangeRateVO> bankVOList = new ArrayList<ExchangeRateVO>();
                        for (ExchangeRate exchangeRate : exchangeRateList) {
                            bankVOList.add(exchangeRate.createExchangeRateVO());
                        }
                        request.setAttribute("exchangeRateExtList", bankVOList);
                    }

                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("command", "UPDATECURRENCYEXT");
        return page;
    }
}