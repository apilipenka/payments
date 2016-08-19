package by.pwt.pilipenko.payments.web.command.account;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static by.pwt.pilipenko.payments.web.command.account.AccountUtil.fillAccountParent;

public class NewAccountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newaccount");

        fillAccountParent(request);


        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}