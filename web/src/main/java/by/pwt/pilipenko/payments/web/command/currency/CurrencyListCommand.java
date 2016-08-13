package by.pwt.pilipenko.payments.web.command.currency;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CurrencyListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.currencylist");

        CurrencyService currencyService = new CurrencyService();
        Object name = request.getAttribute("currencyName");

        if (name != null) {
            request.setAttribute("currencyList", currencyService.searchEntityByName(name.toString()));
        } else {
            name = request.getParameter("currencyName");
            if (name != null) {
                request.setAttribute("currencyList", currencyService.searchEntityByName(name.toString()));
            } else {
                request.setAttribute("currencyList", currencyService.getAllEntities());
            }
        }
        return page;
    }
}