package by.pwt.pilipenko.payments.web.command.currency;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.pilipenko.payments.model.entities.Currency;

import javax.servlet.http.HttpServletRequest;


public class AddCurrencyCommand implements ActionCommand {
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
            page = ConfigurationManager.getProperty("path.page.currencylist");

            request.getSession().setAttribute("success", "true");

            request.getSession().setAttribute("message", "The new currency has been successfully created.");

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("currency", currency);

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newcurrency");

        }
        return page;
    }
}