package by.pwt.pilipenko.payments.web.command.account;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.*;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddAccountCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        String error = null;
        Account account1 = null;
        Account account = null;
        String page = null;
        try {
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

            account.setNumber(number);
            account.setAmount(new Double(amount));
            account.setAgreement(agreemnet);
            account.setCurrency(currency);

            AccountService accountService = new AccountService();

            account1 = accountService.insertEntity(account);
            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.accountlist");
            request.getSession().setAttribute("message", "The new account has been successfully created.");


        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("account", account);

            try {
                request.setAttribute("agreements", new AgreementService().getAllEntities());
            } catch (NamingException e1) {
                error += "<br/>" + e1.getMessage();

            } catch (SQLException e1) {
                error += "<br/>" + e1.getMessage();

            }

            try {
                request.setAttribute("currencies", new CurrencyService().getAllEntities());
            } catch (NamingException e1) {
                error += "<br/>" + e1.getMessage();

            } catch (SQLException e1) {
                error += "<br/>" + e1.getMessage();

            }

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newaccount");

        }
        return page;
    }
}