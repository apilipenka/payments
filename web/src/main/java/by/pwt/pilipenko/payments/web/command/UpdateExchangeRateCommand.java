package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.services.ExchangeRateService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.entities.Currency;
import by.pwt.plipenko.payments.model.entities.ExchangeRate;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by apilipenka on 8/11/2016.
 */
public class UpdateExchangeRateCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String error = null;
        ExchangeRate exchangeRate = null;
        String page = null;
        try {
            String id = request.getParameter("id");

            String rateDateStr = request.getParameter("ratehDate");
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date rateDate = format.parse(rateDateStr);
            float rate = new Float(request.getParameter("rate"));
            String currencyStr = request.getParameter("currencyId");

            CurrencyService currencyService = new CurrencyService();

            Currency currency = null;
            try {
                currency = currencyService.getEntity(Integer.parseInt(currencyStr));
            } catch (Exception e) {
                throw e;
            }

            exchangeRate = new ExchangeRate();
            exchangeRate.setId(Integer.parseInt(id));
            exchangeRate.setRateDate(rateDate);
            exchangeRate.setRate(rate);
            exchangeRate.setCurrency(currency);

            ExchangeRateService exchangeRateService = new ExchangeRateService();
            boolean flag = exchangeRateService.updateEntity(exchangeRate);
            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.echangeRateList");

                request.getSession().setAttribute("message", "The exchange reate has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("exchangeRate", exchangeRate.createExchangeRateVO());

            try {
                request.setAttribute("currencies", new CurrencyService().getAllEntities());
            } catch (NamingException e1) {
                error += "<br/>" + e1.getMessage();

            } catch (SQLException e1) {
                error += "<br/>" + e1.getMessage();

            }

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATEXCHANGERATE");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newexchangeRate");

        }
        return page;
    }
}
