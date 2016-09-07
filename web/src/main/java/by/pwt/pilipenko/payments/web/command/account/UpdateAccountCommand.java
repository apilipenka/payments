package by.pwt.pilipenko.payments.web.command.account;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.model.entities.Account;
import by.pwt.pilipenko.payments.model.entities.Agreement;
import by.pwt.pilipenko.payments.model.entities.Currency;
import by.pwt.pilipenko.payments.services.AccountService;
import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static by.pwt.pilipenko.payments.web.command.account.AccountUtil.fillAccountParent;

public class UpdateAccountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws ClassNotFoundException {

        String error = null;
        Account account = null;
        String page = null;
        try {
            int id = new Integer(request.getParameter("id"));
            String number = request.getParameter("number");
            String amount = request.getParameter("amount");
            String agreementstr = request.getParameter("agreement");
            String currencystr = request.getParameter("currency");


            AgreementService agreementService = new AgreementService();

            Agreement agreemnet = null;
            try {
                agreemnet = agreementService.getEntity(Integer.parseInt(agreementstr));
            } catch (Exception e) {
                throw e;
            }

            CurrencyService currencyService = new CurrencyService();

            Currency currency = null;
            try {
                currency = currencyService.getEntity(Integer.parseInt(currencystr));
            } catch (Exception e) {
                throw e;
            }


            account = new Account();
            account.setId(id);
            account.setNumber(number);
            account.setAmount(new Double(amount));
            account.setAgreement(agreemnet);
            account.setCurrency(currency);

            AccountService accountService = new AccountService();


            boolean flag = accountService.updateEntity(account);
            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.accountlistp")+"?pg=1&rpp=1";

                request.getSession().setAttribute("message", "The account has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("account", account.createAccountVO());

            fillAccountParent(request);

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATEACCOUNT");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newaccount");

        }
        return page;
    }
}