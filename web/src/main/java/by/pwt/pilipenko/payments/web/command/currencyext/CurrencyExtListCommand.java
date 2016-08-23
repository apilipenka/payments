package by.pwt.pilipenko.payments.web.command.currencyext;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CurrencyExtListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException, ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.currencyextlist");

        CurrencyService currencyService = new CurrencyService();
        Object name = request.getAttribute("currencyExtName");

        if (name != null) {
            request.setAttribute("currencyExtList", currencyService.searchEntityByName(name.toString()));
        } else {
            name = request.getParameter("currencyExtName");
            if (name != null) {
                request.setAttribute("currencyExtList", currencyService.searchEntityByName(name.toString()));
            } else {
                request.setAttribute("currencyExtList", currencyService.getAllEntities());
            }
        }
        return page;
    }
}