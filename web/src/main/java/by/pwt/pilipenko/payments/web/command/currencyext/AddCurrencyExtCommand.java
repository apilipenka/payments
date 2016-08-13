package by.pwt.pilipenko.payments.web.command.currencyext;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.Currency;

import javax.servlet.http.HttpServletRequest;


public class AddCurrencyExtCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        String error = null;
        Currency currency1 = null;
        Currency currency = null;
        String page = null;
        try {
            String code = request.getParameter("code");
            String mnemoCode = request.getParameter("mnemoCode");
            String name = request.getParameter("name");

            currency = new Currency();
            currency.setCode(code);
            currency.setMnemoCode(mnemoCode);
            currency.setName(name);


            CurrencyService currencyService = new CurrencyService();

            currency1 = currencyService.insertEntity(currency);
            page = ConfigurationManager.getProperty("path.page.currencyextlist");

            request.getSession().setAttribute("success", "true");

            request.getSession().setAttribute("message", "The new currency has been successfully created.");

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("currency", currency);

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newcurrencyext");

        }
        return page;
    }
}