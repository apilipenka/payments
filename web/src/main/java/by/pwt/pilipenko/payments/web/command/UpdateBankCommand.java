package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.servlet.http.HttpServletRequest;

public class UpdateBankCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String error = null;
        Bank bank = null;
        String page = null;
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String unn = request.getParameter("unn");

            bank = new Bank();
            bank.setId(Integer.parseInt(id));
            bank.setName(name);
            bank.setUNN(unn);

            BankService bankService = new BankService();
            boolean flag = bankService.updateEntity(bank);
            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.banklist");

                request.getSession().setAttribute("message", "The bank has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("bank", bank);

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATEBANK");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newbank");

        }
        return page;
    }
}