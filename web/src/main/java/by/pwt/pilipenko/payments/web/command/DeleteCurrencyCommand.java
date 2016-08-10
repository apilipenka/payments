package by.pwt.pilipenko.payments.web.command;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CurrencyService;

import javax.servlet.http.HttpServletRequest;

public class DeleteCurrencyCommand implements ActionCommand {
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

            page = ConfigurationManager.getProperty("path.page.currencylist");

            request.getSession().setAttribute("message", "The currency has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.currencylist");
            request.setAttribute("error", error);
            request.setAttribute("command", "CURRENCYLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}