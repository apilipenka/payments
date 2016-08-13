package by.pwt.pilipenko.payments.web.command.currencyext;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteCurrencyExtCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        CurrencyService currencyService = new CurrencyService();
        Object name = request.getAttribute("currencyID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                currencyService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("currencyID");
                if (name != null) {
                    currencyService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.currencyextlist");

            request.getSession().setAttribute("message", "The currency has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.currencyextlist");
            request.setAttribute("error", error);
            request.setAttribute("command", "CURRENCYEXTLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}