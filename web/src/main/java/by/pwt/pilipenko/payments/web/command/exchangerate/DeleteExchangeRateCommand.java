package by.pwt.pilipenko.payments.web.command.exchangerate;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.ExchangeRateService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteExchangeRateCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        ExchangeRateService exchangeRateService = new ExchangeRateService();
        Object name = request.getAttribute("exchangeRateID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                exchangeRateService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("exchangeRateID");
                if (name != null) {
                    exchangeRateService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.echangeRateList");

            request.getSession().setAttribute("message", "The exchange rate has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.echangeRateList");
            request.setAttribute("error", error);
            request.setAttribute("command", "EXCHANGERATELIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}