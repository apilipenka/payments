package by.pwt.pilipenko.payments.web.command.exchangerate;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.services.ExchangeRateService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditExchangeRateCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException, ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.newexchangeRate");

        ExchangeRateService exchangeRateService = new ExchangeRateService();
        Object name = request.getAttribute("exchangeRateID");

        ExchangeRate exchangeRate = new ExchangeRate();

        try {
            if (name != null) {

                exchangeRate = exchangeRateService.getEntity(Integer.parseInt(name.toString()));

            } else {
                name = request.getParameter("exchangeRateID");
                if (name != null) {
                    exchangeRate = exchangeRateService.getEntity(Integer.parseInt(name.toString()));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (exchangeRate != null) {
            request.setAttribute("exchangeRate", exchangeRate.createExchangeRateVO());
        }

        request.setAttribute("test1", exchangeRate.createExchangeRateVO().getCurrencyID());
        request.setAttribute("test2", exchangeRate.createExchangeRateVO().getTargetCurrencyID());

        request.setAttribute("currencies", new CurrencyService().getAllEntities());

        request.setAttribute("command", "UPDATEEXCHANGERATE");
        //request.setAttribute("roles", new UserRoleService().getAllEntities());
        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}