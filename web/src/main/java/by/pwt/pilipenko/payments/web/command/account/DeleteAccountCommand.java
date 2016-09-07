package by.pwt.pilipenko.payments.web.command.account;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AccountService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteAccountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        AccountService accountService = new AccountService();
        Object name = request.getAttribute("accountID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                accountService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("accountID");
                if (name != null) {
                    accountService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.accountlistp")+"?pg=1&rpp=1";

            request.getSession().setAttribute("message", "The account has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.accountlistp")+"?pg=1&rpp=1";
            request.setAttribute("error", error);
            request.setAttribute("command", "ACCOUNTLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}