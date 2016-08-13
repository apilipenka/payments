package by.pwt.pilipenko.payments.web.command.bank;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteBankCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        BankService bankService = new BankService();
        Object name = request.getAttribute("bankID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                bankService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("bankID");
                if (name != null) {
                    bankService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.banklist");

            request.getSession().setAttribute("message", "The bank has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.banklist");
            request.setAttribute("error", error);
            request.setAttribute("command", "BANKLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}