package by.pwt.pilipenko.payments.web.command.account;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.model.entities.Account;
import by.pwt.pilipenko.payments.services.AccountService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static by.pwt.pilipenko.payments.web.command.account.AccountUtil.fillAccountParent;

public class EditAccountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException, ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.newaccount");

        AccountService accountService = new AccountService();
        Object name = request.getAttribute("accountID");

        Account account = new Account();

        try {
            if (name != null) {

                account = accountService.getEntity(Integer.parseInt(name.toString()));

            } else {
                name = request.getParameter("accountID");
                if (name != null) {
                    account = accountService.getEntity(Integer.parseInt(name.toString()));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (account != null) {
            request.setAttribute("account", account.createAccountVO());
        }

        request.setAttribute("command", "UPDATEACCOUNT");
        fillAccountParent(request);
        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}