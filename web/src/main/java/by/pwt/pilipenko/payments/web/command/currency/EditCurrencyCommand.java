package by.pwt.pilipenko.payments.web.command.currency;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditCurrencyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newcurrency");

        CurrencyService currencyService = new CurrencyService();
        Object name = request.getAttribute("currencyID");

        try {
            if (name != null) {

                request.setAttribute("currency", currencyService.getEntity(Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("currencyID");
                if (name != null) {
                    request.setAttribute("currency", currencyService.getEntity(Integer.parseInt(name.toString())));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("command", "UPDATECURRENCY");
        return page;
    }
}