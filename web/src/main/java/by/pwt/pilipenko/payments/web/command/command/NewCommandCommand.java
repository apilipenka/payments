package by.pwt.pilipenko.payments.web.command.command;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class NewCommandCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newcommand");

        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}