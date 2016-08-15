package by.pwt.pilipenko.payments.web.command.card;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class NewCardCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newuser");

        request.setAttribute("roles", new UserRoleService().getAllEntities());
        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}