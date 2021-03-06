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
        Object accountName = request.getAttribute("accountName");
        Object pg = request.getAttribute("pg");
        Object rpp = request.getAttribute("rpp");

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
            request.setAttribute("command", "ACCOUNTLISTP");
            page = ConfigurationManager.getProperty("path.page.accountlistp")+"?command=ACCOUNTLISTP&pg="+(pg!=null&&!pg.equals("")?pg:1)+"&rpp="+(rpp!=null&&!rpp.equals("")?rpp:1)+
                    (accountName!=null&&!accountName.equals("")?"&accountName":"");

            request.getSession().setAttribute("message", "The account has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.accountlistp")+"?command=ACCOUNTLISTP&pg="+(pg!=null&&!pg.equals("")?pg:1)+"&rpp="+(rpp!=null&&!rpp.equals("")?rpp:1)+
                    (accountName!=null&&!accountName.equals("")?"&accountName":"");
            request.setAttribute("error", error);
            request.setAttribute("command", "ACCOUNTLISTP");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}