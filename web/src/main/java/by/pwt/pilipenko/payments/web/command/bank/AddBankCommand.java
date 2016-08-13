package by.pwt.pilipenko.payments.web.command.bank;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.Bank;

import javax.servlet.http.HttpServletRequest;


public class AddBankCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        String error = null;
        Bank bank1 = null;
        Bank bank = null;
        String page = null;
        try {
            String name = request.getParameter("name");
            String unn = request.getParameter("unn");

            bank = new Bank();

            bank.setName(name);
            bank.setUNN(unn);

            BankService bankService = new BankService();

            bank1 = bankService.insertEntity(bank);
            page = ConfigurationManager.getProperty("path.page.banklist");

            request.getSession().setAttribute("success", "true");

            request.getSession().setAttribute("message", "The new bank has been successfully created.");

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("bank", bank);

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newbank");

        }
        return page;
    }
}