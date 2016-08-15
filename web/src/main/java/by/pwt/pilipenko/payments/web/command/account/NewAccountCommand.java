package by.pwt.pilipenko.payments.web.command.account;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class NewAccountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newaccount");

        request.setAttribute("agreements", new AgreementService().getAllEntities());
        request.setAttribute("currencies", new CurrencyService().getAllEntities());
        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}