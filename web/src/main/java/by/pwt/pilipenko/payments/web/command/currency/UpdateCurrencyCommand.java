package by.pwt.pilipenko.payments.web.command.currency;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.model.entities.Currency;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class UpdateCurrencyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String error = null;
        Currency currency = null;
        String page = null;
        try {
            String id = request.getParameter("id");
            String code = request.getParameter("code");
            String mnemoCode = request.getParameter("mnemoCode");
            String name = request.getParameter("name");


            currency = new Currency();
            currency.setId(Integer.parseInt(id));
            currency.setName(name);
            currency.setCode(code);
            currency.setMnemoCode(mnemoCode);

            CurrencyService currencyService = new CurrencyService();
            boolean flag = currencyService.updateEntity(currency);
            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.currencylist");

                request.getSession().setAttribute("message", "The currency has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("currency", currency);

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATECURRENCY");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newcurrency");

        }
        return page;
    }
}